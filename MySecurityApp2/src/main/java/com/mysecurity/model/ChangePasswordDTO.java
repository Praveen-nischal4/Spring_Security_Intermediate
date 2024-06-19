package com.mysecurity.model;

public class ChangePasswordDTO {

	private String oldPassword;
	private String newPassword;
	private String confirmPasword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPasword() {
		return confirmPasword;
	}
	public void setConfirmPasword(String confirmPasword) {
		this.confirmPasword = confirmPasword;
	}
	@Override
	public String toString() {
		return "ChangePasswordDTO [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", confirmPasword="
				+ confirmPasword + "]";
	}
	
	
}
