package com.brightsoft.service.system.platform;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.system.SysNewsMapper;
import com.brightsoft.model.SysNews;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service
public class NewsServiceImpl {
	
	@Autowired
	private SysNewsMapper newsMapper;
	
	public boolean addNews(SysNews news){
		if(news.getId() == null || news.getId() <= 0){
			news.setNewsTime(new Date());
			if(newsMapper.insert(news)>0){
				return true;
			}
		}else{
			if(newsMapper.updateByPrimaryKeySelective(news) > 0){
				return true;
			}
		}
		return false;
	}
	
	public Result getNews(SearchParams searchParams,Page<?> page){
		Result result = new  Result();
		try {
			int results = newsMapper.countRows(searchParams);
			List<SysNews> list = newsMapper.selectByCondition(page,searchParams);
			result.setResults(results);
			result.setRows(list);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}
	
	public SysNews selectNewsId(long id){
		return newsMapper.selectByPrimaryKey(id);
	}
	
	public boolean deleteNews(List<Long> ids){
		if(newsMapper.deleteNews(ids) > 0){
			return true;
		}
		return false;
	}
	public List<SysNews> selectNewsList(SysNews news){
		List<SysNews> sysNews = newsMapper.selectNewsList(news);
		return sysNews;
	}
	/**
	 * 货运交易系统使用分页新闻
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Page<?> selectBySelectedCondition(
			SearchParams searchParams, Page<?> page) {
		int results = newsMapper.countRows(searchParams);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<SysNews> list = newsMapper.selectByCondition(page,searchParams);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, list);
		page.setParams(map);
		return page;
	}
}
