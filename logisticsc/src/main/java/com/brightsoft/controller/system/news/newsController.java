package com.brightsoft.controller.system.news;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.SysNews;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.platform.NewsServiceImpl;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

@Controller
@RequestMapping("/system/news/")
public class newsController {
	
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	@Autowired
	private NewsServiceImpl newsService;
	
	/**
	 * 公司新闻
	 * @return
	 */
	@RequestMapping("jumpNews")
	public String jumpNews(){
		return "/system/news/company_news_list";
	}
	
	/**
	 * 添加 公司新闻
	 * @return
	 */
	@RequestMapping("jumpAddNews")
	public String jumpAddNews(){
		return "/system/news/add_company_news";
	}
	
	@RequestMapping("jumpNewsInformation")
	public String jumpNewsInformation(){
		return "/system/news/news_Info";
	}
	
	@RequestMapping("jumpAddNewsInformation")
	public String jumpAddNewsInformation(){
		return "/system/news/addnews_Info";
	}
	/**
	 * 增加 新闻 和修改
	 * @param news
	 * @param session
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("doGetNews")
	@ResponseBody Result doGetNews(SysNews news,HttpSession session,@RequestParam(value="fileName",required=false)MultipartFile uploadFile){
		Result ret = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		if(news.getId() == null || news.getId() <= 0){
			if(news.getNewsType() == Const.TMS_NEWS_TYPE_0){
				news.setNewsType(Const.TMS_NEWS_TYPE_0);
			}else if(news.getNewsType() == Const.TMS_NEWS_TYPE_1){
				news.setNewsType(Const.TMS_NEWS_TYPE_1);
			}
			String fileName = uploadFile.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
			if(suffix.matches(bundle.getString("reg"))){
				String dirName ="";
            	
        		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
                        + bundle.getString("uploadNews"), fileName);
                /* 根据图片名生成唯一文件路径 */
                File fl = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
                // 写入文件到实际路径
                try {
					uploadFile.transferTo(fl);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
                String filePath = dirName + fl.getName();
                news.setTitlePicture(filePath);
                news.setUserId(user.getId());
                try {
	               if(newsService.addNews(news)){
	            	   ret.setResult(true);
	               }else{
	            	   ret.setResult(false);
	               }
                } catch (Exception e) {
                	ret.setResult(false);
        		}
		}
		}else{
			if(news.getNewsType() == Const.TMS_NEWS_TYPE_0){
				news.setNewsType(Const.TMS_NEWS_TYPE_0);
			}else if(news.getNewsType() == Const.TMS_NEWS_TYPE_1){
				news.setNewsType(Const.TMS_NEWS_TYPE_1);
			}
			if(uploadFile == null){
				try {
	               if(newsService.addNews(news)){
	            	   ret.setResult(true);
	               }else{
	            	   ret.setResult(false);
	               }
                } catch (Exception e) {
                	ret.setResult(false);
        		}
			}else{
				String fileName = uploadFile.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
				if(suffix.matches(bundle.getString("reg"))){
					String dirName ="";
	            	
	        		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
	                        + bundle.getString("uploadNews"), fileName);
	                /* 根据图片名生成唯一文件路径 */
	                File fl = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
	                // 写入文件到实际路径
	                try {
						uploadFile.transferTo(fl);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                String filePath = dirName + fl.getName();
	                news.setTitlePicture(filePath);
	                news.setUserId(user.getId());
	                try {
		               if(newsService.addNews(news)){
		            	   ret.setResult(true);
		               }else{
		            	   ret.setResult(false);
		               }
	                } catch (Exception e) {
	                	ret.setResult(false);
	        		}
			}
		}
	}
		return ret;
	}
	@RequestMapping("/getNews")
	@ResponseBody
	public Result getNews(Page<?> page,SearchParams searchParams){
		return newsService.getNews(searchParams, page);
	}
	
	@RequestMapping("getNewsId")
	public ModelAndView getNewsId(Long id){
		ModelAndView mv = new ModelAndView();
		SysNews news = null;
		news = newsService.selectNewsId(id);
		if(news == null){
			mv.setViewName("/system/news/company_news_list");
		}else{
			mv.addObject("news",news);
			mv.setViewName("/system/news/add_company_news");
		}
		return mv;
	}
	@RequestMapping("/deleteNews")
	@ResponseBody
	public Result deleteNews(String idss){
		Result srt = new Result();
		List<Long> list = JSONArray.parseArray(idss, Long.class);
		if(newsService.deleteNews(list)){
			srt.setResult(true);
		}else{
			srt.setResult(false);
		}
		return srt;
	}
}
