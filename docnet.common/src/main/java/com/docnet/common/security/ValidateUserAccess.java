package com.docnet.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.docnet.common.exceptions.DocNetUserDataExistsException;
import com.docnet.dao.user.DocNetUserManager;
import com.docnet.model.user.User;

@Component
@PropertySources({
	@PropertySource("classpath:docnet_common.properties"),
	@PropertySource("classpath:docnet_exceptions.properties")
})
public class ValidateUserAccess {
	
	@Autowired
	private Environment environment;

	private DocNetUserManager docNetUserManager;
	@Autowired
	public void setDocNetUserManager(DocNetUserManager docNetUserManager) {
		this.docNetUserManager = docNetUserManager;
	}

	public void validateClientSecret(String clientIdInput,
			String clientSecretInput) throws DocNetUserDataExistsException {
		String clientId = environment.getProperty("docnet.client.id");
		String exceptionMessage = null;
		if (clientId.equals(clientIdInput)) {
			String clientSecret = environment.getProperty("docnet.client.secret");;
			if (!clientSecret.equals(clientSecretInput)) {
				exceptionMessage = "Unauthorized Access";
				throw new DocNetUserDataExistsException(exceptionMessage);
			}
		} else {
			exceptionMessage = "Unauthorized Access";
			throw new DocNetUserDataExistsException(exceptionMessage);
		}
	}

	public void checkIfUserDataExists(User user)
			throws DocNetUserDataExistsException {

		String exceptionMessage = null;
		String userName = user.getLogin();
		String mobileNo = user.getPhone();
		String email = user.getEmail();
		User userByUsername = docNetUserManager.getUserByUserName(userName);
		User userByMobileNo = docNetUserManager.getUserByMobileNo(mobileNo);
		User userByEmail = null;
		if (email != null) {
			userByEmail = docNetUserManager.getUserByEmail(email);
			if (userByEmail != null){
				exceptionMessage = "Email already registered";
				int status = userByEmail.getStatus();
				if(status == 0) {
					exceptionMessage = "User already registered, but authenticity is not veified.";
				}
			}
		}

		if (userByMobileNo != null)
			exceptionMessage = "Mobile No already registered";
		if (userByUsername != null)
			exceptionMessage = "User already registered";

		if (exceptionMessage != null) {
			throw new DocNetUserDataExistsException(exceptionMessage);
		}
	}
}
