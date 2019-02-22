package com.docnet.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

/**
 * @author Puneet Pandey
 *
 */
@Service
@PropertySource("classpath:docnet_common.properties")
public class DocNetClientDetailsService implements ClientDetailsService {

	@Autowired
	private Environment environment;
	
	public ClientDetails loadClientByClientId(String clientId)
			throws OAuth2Exception {
		
		String clientSecret = environment.getProperty("docnet.client.secret");

		if (clientId.equals(environment.getProperty("docnet.client.id"))) {

			List<String> authorizedGrantTypes = new ArrayList<String>();
			authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("client_credentials");

			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId(clientId);
			clientDetails.setClientSecret(clientSecret);
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
			return clientDetails;
		}

		else {
			throw new NoSuchClientException("No client with requested id: "
					+ clientId);
		}
	}

}
