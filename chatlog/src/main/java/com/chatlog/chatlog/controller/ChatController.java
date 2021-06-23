package com.chatlog.chatlog.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chatlog.chatlog.model.Chat;
import com.chatlog.chatlog.service.ChatService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value = "/chatlogs/{user}", method = RequestMethod.POST,consumes = "application/json")
	public Map<String,Object> saveChat(@RequestBody Map<String,Object> requestmap,@PathVariable("user") String user) throws JsonParseException, JsonMappingException, IOException{
		
		String encodedString = (String) requestmap.get("data");

		byte[] valueDecoded = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(valueDecoded);
		
		ObjectMapper mapper = new ObjectMapper();
		Chat chat = mapper.readValue(decodedString, Chat.class);
		
		chat.setUser(user);
		long id = chatService.save(chat);
		Map<String,Object> result = new HashMap<>();
		result.put("message Id",id);
		return result;
	}	
	
	@SuppressWarnings({ "finally", "unchecked" })
	@RequestMapping(value = "/chatlogs/{user}", method = RequestMethod.GET)
	public Map<String,Object> getChat(@PathVariable("user") String user,@Nullable Integer limit ,@Nullable Long start) throws Exception{
		limit = limit == null ? 10 : limit;
		start = start == null ? 0 : start;
		List<Chat> chats;
		try {
			chats = chatService.get(user,limit,start);
			String decode = Base64.getEncoder().encodeToString(chats.toString().getBytes());
			Map<String,Object> result = new HashMap<>();
			result.put("data",decode);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	@RequestMapping(value = "/chatlogs/{user}", method = RequestMethod.DELETE)
	public void deleteAllChat(@PathVariable("user") String user) throws Exception{
		chatService.deleteAll(user);
	}
	
	@RequestMapping(value = "/chatlogs/{user}/{msgid}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteChat(@PathVariable("user") String user, @PathVariable("msgid") Long msgid){
			return chatService.delete(msgid);
	}
}
