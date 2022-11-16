package org.mygeneric.jwt_basic_security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="my-security")
public class MySecurityProperties {
	private String apiWhiteList;
	private String apiReadOnlyWhiteList;
	private String apiProtectedList;
	

	@Override
	public String toString() {
		return "MySecurityProperties [apiWhiteList=" + apiWhiteList + ", apiReadOnlyWhiteList=" + apiReadOnlyWhiteList
				+ ", apiProtectedList=" + apiProtectedList + "]";
	}

	public MySecurityProperties() {
		super();
	}
	
	public String getApiWhiteList() {
		return apiWhiteList;
	}
	public void setApiWhiteList(String apiWhiteList) {
		this.apiWhiteList = apiWhiteList;
	}
	public String getApiReadOnlyWhiteList() {
		return apiReadOnlyWhiteList;
	}
	public void setApiReadOnlyWhiteList(String apiReadOnlyWhiteList) {
		this.apiReadOnlyWhiteList = apiReadOnlyWhiteList;
	}
	public String getApiProtectedList() {
		return apiProtectedList;
	}
	public void setApiProtectedList(String apiProtectedList) {
		this.apiProtectedList = apiProtectedList;
	}
	
	
}
