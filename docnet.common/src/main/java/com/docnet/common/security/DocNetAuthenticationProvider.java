package com.docnet.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.kernel.impl.nioneo.store.InvalidRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.docnet.common.exceptions.DocNetBadCredentialsException;
import com.docnet.common.exceptions.DocNetUserNotExistsException;
import com.docnet.common.util.RandomNumberUtil;
import com.docnet.common.util.SmsGatewayUtil;
import com.docnet.dao.user.DocNetUserManager;
import com.docnet.model.user.User;

/**
 * @author Puneet Pandey
 *
 */
public class DocNetAuthenticationProvider implements AuthenticationProvider {
	
	private DocNetUserManager docNetUserManager;
	private SmsGatewayUtil smsGatewayUtil;
	@Autowired
	public void setSmsGatewayUtil(SmsGatewayUtil smsGatewayUtil) {
		this.smsGatewayUtil = smsGatewayUtil;
	}
	@Autowired
	public void setDocNetUserManager(DocNetUserManager docNetUserManager) {
		this.docNetUserManager = docNetUserManager;
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		DocNetPasswordAuthenticationToken userPassAuthToken = null;
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String userName = (String) authentication.getPrincipal();
		User user = docNetUserManager.getUserByMobileNo(userName);
		
		if(user != null) {
			String passwordInput = (String) authentication.getCredentials();
			Integer otp = user.getOtp();
			String otpPassword = String.valueOf(otp);
			
			if(passwordInput == null) {
				int otpNumber = RandomNumberUtil.getRandomNumber();
				docNetUserManager.setUserOTP(userName, otpNumber);
				try {
					smsGatewayUtil.sendMessage(userName, otpNumber, "otp");
				} catch (IOException e) {
					throw new DocNetBadCredentialsException("Send another OTP request");
				}
				throw new DocNetBadCredentialsException("OTP not provided! Please check your inbox now!");
			} else {
				if (passwordInput.equals(otpPassword)) {
					userPassAuthToken = new DocNetPasswordAuthenticationToken(authentication.getPrincipal(),
							authentication.getCredentials(), grantedAuthorities);
				} else {
					throw new DocNetBadCredentialsException("Bad User Credentials.");
				}
			}
		} else {
			throw new DocNetUserNotExistsException("User Does Not Exist");
		}
		return userPassAuthToken;
	}

	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}

}
