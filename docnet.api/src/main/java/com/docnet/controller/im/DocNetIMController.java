package com.docnet.controller.im;

import org.igniterealtime.restclient.entity.GroupEntity;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.ParticipantEntities;
import org.igniterealtime.restclient.entity.RosterEntities;
import org.igniterealtime.restclient.entity.RosterItemEntity;
import org.igniterealtime.restclient.entity.UserEntities;
import org.igniterealtime.restclient.entity.UserEntity;
import org.igniterealtime.restclient.entity.UserGroupsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docnet.im.chatroom.DocNetChatRoomIMService;
import com.docnet.im.group.DocNetGroupIMService;
import com.docnet.im.user.DocNetOnlineUserIMService;
import com.docnet.im.user.DocNetUserIMService;

@Controller
@RequestMapping("/im")
public class DocNetIMController {

	DocNetChatRoomIMService docNetChatRoomIMService;
	DocNetGroupIMService docNetGroupIMService;
	DocNetUserIMService docNetUserIMService;
	DocNetOnlineUserIMService docNetOnlineUserIMService;

	@Autowired
	public void setDocNetChatRoomIMService(
			DocNetChatRoomIMService docNetChatRoomIMService) {
		this.docNetChatRoomIMService = docNetChatRoomIMService;
	}

	@Autowired
	public void setDocNetGroupIMService(
			DocNetGroupIMService docNetGroupIMService) {
		this.docNetGroupIMService = docNetGroupIMService;
	}

	@Autowired
	public void setDocNetUserIMService(DocNetUserIMService docNetUserIMService) {
		this.docNetUserIMService = docNetUserIMService;
	}

	@Autowired
	public void setDocNetOnlineUserIMService(
			DocNetOnlineUserIMService docNetOnlineUserIMService) {
		this.docNetOnlineUserIMService = docNetOnlineUserIMService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserEntities getUsers() {
		return docNetUserIMService.getUsers();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserEntity getUser(@RequestParam("userName") String userName) {
		return docNetUserIMService.getUser(userName);
	}

	@RequestMapping(value = "/users/search", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserEntities searchUsers(@RequestParam("userName") String userName) {
		return docNetUserIMService.searchUsers(userName);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public UserEntity createUser(@RequestBody UserEntity user) {
		return docNetUserIMService.createUser(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public UserEntity updateUser(@RequestBody UserEntity user) {
		return docNetUserIMService.updateUser(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@RequestParam("userName") String userName) {
		return docNetUserIMService.deleteUser(userName);
	}

	@RequestMapping(value = "/users/groups", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public UserGroupsEntity getUserGroups(
			@RequestParam("userName") String userName) {
		return docNetUserIMService.getUserGroups(userName);
	}

	@RequestMapping(value = "/users/groups", method = RequestMethod.POST)
	@ResponseBody
	public String addUserToGroup(@RequestParam("userName") String userName,
			@RequestParam("groupName") String groupName) {
		return docNetUserIMService.addUserToGroup(userName, groupName);
	}

	@RequestMapping(value = "/users/groups", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUserToGroup(@RequestParam("userName") String userName,
			@RequestParam("groupName") String groupName) {
		return docNetUserIMService.deleteUserFromGroup(userName, groupName);
	}

	@RequestMapping(value = "/groups", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GroupEntity getGroup(@RequestParam("groupName") String groupName) {
		return docNetGroupIMService.getGroup(groupName);
	}

	@RequestMapping(value = "/groups", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public GroupEntity createGroup(@RequestParam("name") String name,
			@RequestParam("description") String description) {
		GroupEntity groupEntity = docNetGroupIMService.createGroup(name,
				description);
		return groupEntity;
	}

	@RequestMapping(value = "/groups", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public GroupEntity updateGroup(String name, String description) {
		return docNetGroupIMService.updateGroup(name, description);
	}

	@RequestMapping(value = "/groups", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteGroup(String groupName) {
		return docNetGroupIMService.deleteGroup(groupName);
	}

	@RequestMapping(value = "/online/contacts", method = RequestMethod.GET)
	@ResponseBody
	public RosterEntities getOnlineContacts(
			@RequestParam("userName") String userName) {
		return docNetOnlineUserIMService.getOnlineContacts(userName);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RosterItemEntity addContacts(@RequestParam("email") String email,
			@RequestParam("nickName") String nickName,
			@RequestParam("subscriptionType") Integer subscriptionType,
			@RequestParam("userName") String userName) {
		return docNetOnlineUserIMService.addContacts(email, nickName,
				subscriptionType, userName);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public RosterItemEntity updateContacts(String email, String nickName,
			int subscriptionType, String userName) {
		return docNetOnlineUserIMService.updateContacts(email, nickName,
				subscriptionType, userName);
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteContacts(@RequestParam("userName") String userName,
			@RequestParam("email") String email) {
		return docNetOnlineUserIMService.deleteContacts(userName, email);
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public MUCRoomEntity addChatRoom(@RequestParam("roomName") String roomName,
			@RequestParam("naturalName") String naturalName,
			@RequestParam("description") String description) {
		return docNetChatRoomIMService.addChatRoom(roomName, naturalName,
				description);
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public MUCRoomEntity updateChatRoom(
			@RequestParam("roomName") String roomName,
			@RequestParam("naturalName") String naturalName,
			@RequestParam("description") String description) {
		return docNetChatRoomIMService.updateChatRoom(roomName, naturalName,
				description);
	}

	@RequestMapping(value = "/chatroom", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteChatRoom(@RequestParam("roomName") String roomName) {
		return docNetChatRoomIMService.deleteChatRoom(roomName);
	}

	@RequestMapping(value = "/chatroom/owner", method = RequestMethod.POST)
	@ResponseBody
	public String addOwnerRoleToChatRoom(
			@RequestParam("chatRoom") String chatRoom,
			@RequestParam("userName") String userName) {
		return docNetChatRoomIMService.addOwnerRoleToChatRoom(chatRoom,
				userName);
	}

	@RequestMapping(value = "/chatroom/admin", method = RequestMethod.POST)
	@ResponseBody
	public String addAdminRoleToChatRoom(
			@RequestParam("chatRoom") String chatRoom,
			@RequestParam("userName") String userName) {
		return docNetChatRoomIMService.addAdminRoleToChatRoom(chatRoom,
				userName);
	}

	@RequestMapping(value = "/chatroom/member", method = RequestMethod.POST)
	@ResponseBody
	public String addMemberToChatRoom(
			@RequestParam("chatRoom") String chatRoom,
			@RequestParam("userName") String userName) {
		return docNetChatRoomIMService.addMemberToChatRoom(chatRoom, userName);
	}

	@RequestMapping(value = "/chatroom/participants", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ParticipantEntities getChatRoomParticipants(
			@RequestParam("chatRoom") String chatRoom) {
		return docNetChatRoomIMService.getChatRoomParticipants(chatRoom);
	}
}
