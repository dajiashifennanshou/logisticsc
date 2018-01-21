package com.brightsoft.controller.vo;

import java.util.List;

public class PlatformUserSearchParams extends BaseSearchParams{

	private List<String> userTypes;

	public List<String> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<String> userTypes) {
		this.userTypes = userTypes;
	}

}
