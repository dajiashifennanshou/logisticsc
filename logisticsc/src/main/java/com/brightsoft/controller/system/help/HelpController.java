package com.brightsoft.controller.system.help;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.util.CollectionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONException;
import com.brightsoft.model.SysHelp;
import com.brightsoft.model.SysHelpContent;
import com.brightsoft.service.base.ImgService;
import com.brightsoft.service.system.platform.HelpContentServiceImpl;
import com.brightsoft.service.system.platform.HelpServiceImpl;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/system/help/")
public class HelpController {
	@Autowired
	private HelpServiceImpl helpService;
	
	@Autowired
	private HelpContentServiceImpl helpContentService;
	
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	
	@Autowired
	private ImgService imgService;
	/**
	 * 跳转到列表
	 * @return
	 */
	@RequestMapping("jumpHelp")
	public String jumpPartner(){
		return "/system/help/help_list";
	}
	
	@RequestMapping("getHelp")
	@ResponseBody
	public List<SysHelp> getHelp(){
		SysHelp help = helpService.getHelp();
		return help.getHelps();
		
	}
	
	@RequestMapping("doHelp")
	@ResponseBody
	public Result doHelp(SysHelp help){
		Result ret = new Result();
		if(helpService.insertHelp(help)){
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		return ret;
	}
	
	@RequestMapping("deleteHelp")
	@ResponseBody
	public Result deleteHelp(Long id){
		Result ret = new Result();
		if(helpService.delete(id)){
			ret.setResult(true);
		}else{
			ret.setResult(false);
		}
		return ret;
	}
	@RequestMapping("doHelpContent")
	@ResponseBody
	public Result doHelpContent(SysHelpContent content){
		Result ret = new Result();
		if(content.getId() == null ||content.getId() <= 0){
			if(helpContentService.insertHelpContent(content)){
				ret.setResult(true);
			}else{
				ret.setResult(false);
			}
		}else{
			if(helpContentService.updateHelpContent(content)){
				ret.setResult(true);
			}else{
				ret.setResult(false);
			}
		}
		return ret;
	}
	
	@RequestMapping("getHelpContent")
	@ResponseBody
	public Result getHelpContent(Long id,HttpSession session){
		Result ret = new Result();
		SysHelpContent content = null;
		content = helpContentService.selectContent(id);
		if(content == null){
			ret.setResult(false);
		}else{
			ret.setResult(true);
			ret.setData(content);
		}
		return ret;
	}
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @param file
	 * @throws IOException
	 * @throws FileUploadException
	 * @throws JSONException
	 */
	@RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response,MultipartRequest file)
            throws IOException, FileUploadException, JSONException {

        // 文件保存目录路径
        String savePath = bundle.getString("baseUrl")
                + bundle.getString("uploadImg");

/*        // 文件保存目录URL
        String saveUrl = request.getContextPath() + "/uploadFiles/images/";*/

        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        response.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = response.getWriter();

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
  
        savePath += dirName + "/";
       // saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
       // saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        
        Map<String,MultipartFile> map = file.getFileMap();
        Collection<MultipartFile> files =  map.values();
        if(!CollectionUtils.isEmpty(files)){
            for (MultipartFile multipartFile : files) {
                String fileName = multipartFile.getOriginalFilename();
                //
                String fileExt = FilenameUtils.getExtension(fileName).toLowerCase(); 
    
                try{
                    //创建新文件名
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newFileName = df.format(new Date()) + "_"+ new Random().nextInt(1000) + "." + fileExt;
                    //保存文件
                    File target = new File(savePath,newFileName);
                    OutputStream os = new FileOutputStream(target);                 
                    InputStream in = multipartFile.getInputStream();
                    IOUtils.copy(in,os);
                    
                    IOUtils.closeQuietly(os);
                    IOUtils.closeQuietly(in);
                    
                    JSONObject obj = new JSONObject();
                    obj.put("error", 0);
                    
                    String u = "/logisticsc/img/retrive.shtml?resource=";
                    
                    obj.put("url", u + savePath + newFileName);
                    out.println(obj.toString());
                    
                }catch (Exception e) {
                    
                }
    
            }
            
        }
        
    }
	
}
