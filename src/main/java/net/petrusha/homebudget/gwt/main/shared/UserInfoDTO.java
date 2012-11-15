package net.petrusha.homebudget.gwt.main.shared;

import java.io.Serializable;

public class UserInfoDTO implements Serializable {

	private static final long serialVersionUID = 7791794223664349912L;

	private String userName;
	private String loginUrl;
	private String logoutUrl;
	
	public UserInfoDTO() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getLogoutUrl() {
		return logoutUrl;
	}
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	
}
