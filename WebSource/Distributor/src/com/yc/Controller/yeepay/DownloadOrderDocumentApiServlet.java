package com.yc.Controller.yeepay;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.Tool.yeepay.DownloadOrderDocumentUtils;

/**
 * @author: yingjie.wang    
 * @since : 2015-10-04 23:19
 */

public class DownloadOrderDocumentApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadOrderDocumentApiServlet() {
        super();
    }

	public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
	
	//get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//UTF-8编码
		request.setCharacterEncoding("UTF-8");

		//获取请求参数
		String checkDate	= formatStr(request.getParameter("checkDate"));
		String orderType	= formatStr(request.getParameter("orderType"));
		String fileType		= formatStr(request.getParameter("fileType"));
		
		Map<String, String> params	= new HashMap<String, String>();
		params.put("checkDate", checkDate);
		params.put("orderType", orderType);
		params.put("fileType", fileType);

		//获得项目绝对路径	
		String realPath 	= this.getServletConfig().getServletContext().getRealPath("/"); 
		
		//对账文件的存储路径
		String path			= realPath + File.separator + "ZgtOrderDocument";
		//System.out.println("path:" + path);

		//获取对账文件
		String filePath		= DownloadOrderDocumentUtils.getPathOfDownloadOrderDocument(params, path);

		request.setAttribute("filePath", filePath);
		RequestDispatcher view	= request.getRequestDispatcher("jsp/421downloadOrderDocumentApiResponse.jsp");
		view.forward(request, response);
	}
}
