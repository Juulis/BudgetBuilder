package net.petrusha.homebudget.gwt.main.client.model;

import java.io.Serializable;


public interface UiModel {
	
	@SuppressWarnings("serial")
	public class ProfileInfo implements Serializable {
		private String id;
		private String name;
		
		public ProfileInfo() {
		}
		
		public ProfileInfo(String id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}

	
	
	
	@SuppressWarnings("serial")
	class UserInfoResult implements Serializable {
		
		private String userName;
		private String loginUrl;
		private String logoutUrl;
		private boolean hasProfile;
		
		public UserInfoResult() {
			
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
		public boolean isHasProfile() {
			return hasProfile;
		}
		public void setHasProfile(boolean hasProfile) {
			this.hasProfile = hasProfile;
		}
		
	}
	

}
