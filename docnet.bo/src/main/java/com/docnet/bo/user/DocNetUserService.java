package com.docnet.bo.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docnet.common.exceptions.DocNetUserDataExistsException;
import com.docnet.common.security.DocNetLoggedInUserUtil;
import com.docnet.common.security.ValidateUserAccess;
import com.docnet.common.util.DateTimeUtil;
import com.docnet.common.util.RandomNumberUtil;
import com.docnet.common.util.SmsGatewayUtil;
import com.docnet.dao.user.DocNetUserManager;
import com.docnet.model.user.User;
@Component
public class DocNetUserService {

	private DocNetUserManager docNetUserManager;
	private ValidateUserAccess validateUserAccess;
	private DocNetLoggedInUserUtil docNetLoggedInUserUtil;
	private SmsGatewayUtil SmsGatewayUtil;
	@Autowired
	public void setSmsGatewayUtil(SmsGatewayUtil smsGatewayUtil) {
		SmsGatewayUtil = smsGatewayUtil;
	}
	@Autowired
	public void setDocNetUserManager(DocNetUserManager docNetUserManager) {
		this.docNetUserManager = docNetUserManager;
	}
	@Autowired
	public void setValidateUserAccess(ValidateUserAccess validateUserAccess) {
		this.validateUserAccess = validateUserAccess;
	}
	@Autowired
	public void setDocNetLoggedInUserUtil(
			DocNetLoggedInUserUtil docNetLoggedInUserUtil) {
		this.docNetLoggedInUserUtil = docNetLoggedInUserUtil;
	}

	public User createUser(User user, String clientId, String clientSecret)
			throws DocNetUserDataExistsException, IOException {
		String mobileNo = user.getPhone();
		validateUserAccess.validateClientSecret(clientId, clientSecret);
		validateUserAccess.checkIfUserDataExists(user);
		user.setDateCreated(DateTimeUtil.getCurrentDateTime());
		user.setDateUpdated(DateTimeUtil.getCurrentDateTime());
		user.setStatus(0);
		int otp = RandomNumberUtil.getRandomNumber();
		user.setOtp(otp);
		User result = docNetUserManager.createUser(user);
		SmsGatewayUtil.sendMessage(mobileNo, otp, "OTP");
		return result;
	}

}
