package com.docnet.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.docnet.dao.user.DocNetUserManager;
import com.docnet.model.user.User;

@Component
public class DocNetLoggedInUserUtil {

	private DocNetUserManager docNetUserManager;
	@Autowired
	public void setDocNetUserManager(DocNetUserManager docNetUserManager) {
		this.docNetUserManager = docNetUserManager;
	}

	public User getLoggedInUser() {
		String userName = getLoggedInUserName();
		User user = docNetUserManager.getUserByUserName(userName);
		return user;
	}
	
	public String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = (String)authentication.getPrincipal();
		return userName;
	}
	
	public long getLoggedInUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = (String)authentication.getPrincipal();
		User user = docNetUserManager.getUserByUserName(userName);
		return user.getId();
	}
}
