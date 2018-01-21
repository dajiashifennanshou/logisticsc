package com.yc.Controller.yeepay;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.yc.Tool.yeepay.AESUtil;
import com.yc.Tool.yeepay.Digest;
import com.yc.Tool.yeepay.ZGTUtils;

/**
 * @author: yingjie.wang
 * @since : 2015-10-03 22:00
 */

public class UpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext sc;

	public UpLoadServlet() {
		super();
	}

	public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}

	// get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void init(ServletConfig config) {
		// 在web.xml中设置的一个初始化参数
		sc = config.getServletContext();
	}

	// post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//UTF-8编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out	= response.getWriter();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		File file = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
					map.put(item.getFieldName(), item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						System.out.println("上传文件的名称:" + item.getName());
						File tempFile = new File(item.getName());
						System.out.println(sc.getRealPath("/"));
						file = new File(sc.getRealPath("/") + "path", tempFile.getName());
						item.write(file);
					} else {
						System.out.println("upload.message: 没有选择上传文件！");
						out.println("upload.message: 没有选择上传文件！");
						return;
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取请求参数
		String key = ZGTUtils.getKeyForHmac();
		String customernumber = ZGTUtils.getCustomernumber();
		String uploadurl = ZGTUtils.getRequestUrl(ZGTUtils.UPLOADAPI_NAME);
		System.out.println("key=" + key);
		System.out.println("customernumber=" + customernumber);
		System.out.println("uploadurl=" + uploadurl);
		String ledgerno 		= formatStr(map.get("ledgerno"));
		String filetype 		= formatStr(map.get("filetype"));

		StringBuffer signature = new StringBuffer();
		signature.append(customernumber).append(ledgerno).append(filetype);
		String hmac = Digest.hmacSign(signature.toString(), key);
		System.out.println(hmac);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("customernumber", customernumber);
		dataMap.put("ledgerno", ledgerno);
		dataMap.put("filetype", filetype);
		dataMap.put("hmac", hmac); // hmac 按照 properties 中声明的顺序进行签名
		String dataJsonString = com.alibaba.fastjson.JSON.toJSONString(dataMap); // map 中数据转为 json 格式
		String content = AESUtil.encrypt(dataJsonString, key);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customernumber", customernumber);
		params.put("data", content);// 加密 json 格式数据作为 value 赋值给 data 参数
		params.put("file", file);
		System.out.println("params==="+params);
		ZGTUtils test = new ZGTUtils();
		try {
			String data = test.uploadFile(params, uploadurl);
			System.out.println("data==="+data);
			System.out.println("datastr="+JSON.parseObject(data).get("data").toString());
			Map<String, String> responseDataMap	= ZGTUtils.decryptData(JSON.parseObject(data).get("data").toString());
			System.out.println("responseDataMap="+responseDataMap);

			if(!"1".equals(responseDataMap.get("code"))) {
				out.println("code = " + responseDataMap.get("code") + "<br>");
				out.println("msg  = " + responseDataMap.get("msg"));
				return;
			}
			
			request.setAttribute("responseDataMap", responseDataMap);
			RequestDispatcher view	= request.getRequestDispatcher("jsp/uploadresponse.jsp");
			view.forward(request, response);
			file.delete();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// String[] person = { "ID_CARD_FRONT", "ID_CARD_BACK",
		// "BANK_CARD_FRONT", "PERSON_PHOTO" };
		String[] person = { "ID_CARD_FRONT" };
		for (String p : person) {
			String customernumber = "10012438801";
			String ledgerno = "10012909010";
			String filetype = p;
			String key = "574584H38Msx80980026QKzbX588Zv0xh95ph8ZG67dj7x69k5091xvm0013";

			// String customernumber = "10040020653";
			// String ledgerno = "10040022229";
			// String filetype = p;
			// String key =
			// "Kgrj87D6h0Fh6jnm4a66hI7qU2n065488xzv0nKm0J99AE9X95n72Y3jt497";

			StringBuffer signature = new StringBuffer();
			signature.append(customernumber).append(ledgerno).append(filetype);
			String hmac = Digest.hmacSign(signature.toString(), key);
			System.out.println(hmac);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("customernumber", customernumber);
			dataMap.put("ledgerno", ledgerno);
			dataMap.put("filetype", filetype);
			dataMap.put("hmac", hmac); // hmac 按照 properties 中声明的顺序进行签名
			String dataJsonString = com.alibaba.fastjson.JSON.toJSONString(dataMap); // map
																						// 中数据转为
																						// json
																						// 格式
			String content = AESUtil.encrypt(dataJsonString, key);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("customernumber", customernumber);
			params.put("data", content);// 加密 json 格式数据作为 value 赋值给 data 参数
			File file = new File("/Users/wangjie/Desktop/demos/zgt-java/WebContent/path/feifei.jpg");
			params.put("file", file);
			System.out.println(params);
			// UpLoadApiServlet test = new UpLoadApiServlet();
			ZGTUtils test = new ZGTUtils();
			try {
				String data = test.uploadFile(params, "http://o2o.yeepay.com/zgt-api/api/uploadLedgerQualifications");
				String result = AESUtil.decrypt(JSON.parseObject(data).get("data").toString(), key);
				System.out.println(result);
			} catch (Throwable e) {
				e.printStackTrace();

			}

		}

	}
}
