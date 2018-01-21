package com.brightsoft.dao.platform;

import java.util.ArrayList;
import java.util.List;

import com.brightsoft.model.PlatformUserEvaluation;

public class PlatformEvaluationCount {
	
	private Integer praisePeople; //好评人数
	  
	private Integer commonlyPeople; //中评人数
	  
	private Integer badPeople; //差评人数
	  
	private Integer praise; //好评百分比
	  
	private Integer commonly; //中评百分比
	  
	private Integer bad; //差评百分比
	
	List<PlatformUserEvaluation> evaluations = new ArrayList<PlatformUserEvaluation>();

	public Integer getPraisePeople() {
		return praisePeople;
	}

	public void setPraisePeople(Integer praisePeople) {
		this.praisePeople = praisePeople;
	}

	public Integer getCommonlyPeople() {
		return commonlyPeople;
	}

	public void setCommonlyPeople(Integer commonlyPeople) {
		this.commonlyPeople = commonlyPeople;
	}

	public Integer getBadPeople() {
		return badPeople;
	}

	public void setBadPeople(Integer badPeople) {
		this.badPeople = badPeople;
	}

	

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getCommonly() {
		return commonly;
	}

	public void setCommonly(Integer commonly) {
		this.commonly = commonly;
	}

	public Integer getBad() {
		return bad;
	}

	public void setBad(Integer bad) {
		this.bad = bad;
	}

	public List<PlatformUserEvaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<PlatformUserEvaluation> evaluations) {
		this.evaluations = evaluations;
	}

	
}
