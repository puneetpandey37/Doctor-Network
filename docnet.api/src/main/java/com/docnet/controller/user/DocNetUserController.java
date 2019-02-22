package com.docnet.controller.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docnet.bo.user.DocNetUserService;
import com.docnet.common.exceptions.DocNetUserDataExistsException;
import com.docnet.model.user.User;

@Controller
@RequestMapping("/signup")
public class DocNetUserController {

	DocNetUserService  docNetUserService;
	@Autowired
	public void setDocNetUserService(DocNetUserService docNetUserService) {
		this.docNetUserService = docNetUserService;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public User createUser(@RequestBody User user,
			@RequestHeader(value = "Client-Id") String clientId,
			@RequestHeader(value = "Client-Secret") String clientSecret)
			throws DocNetUserDataExistsException, IOException {
		return docNetUserService.createUser(user, clientId, clientSecret);
	}
}
