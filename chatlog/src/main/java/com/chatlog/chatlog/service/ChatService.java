package com.chatlog.chatlog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatlog.chatlog.model.Chat;

@Service
public interface ChatService {

	Long save(Chat chat);
		
	ResponseEntity<String> delete(Long id);
	
	void deleteAll(String user) throws Exception;

	List<Chat> get(String user, Integer limit, Long start) throws Exception;
}
