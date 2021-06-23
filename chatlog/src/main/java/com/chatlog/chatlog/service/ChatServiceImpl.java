package com.chatlog.chatlog.service;

import java.util.List;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatlog.chatlog.dao.ChatDao;
import com.chatlog.chatlog.model.Chat;


@Service
public class ChatServiceImpl implements ChatService {
	
	@Autowired
	private ChatDao chatDao;

	@Transactional
	@Override
	public Long save(Chat chat) {
		return chatDao.save(chat).getId();
	}

	@Transactional
	@Override
	public List<Chat> get(String user,Integer limit , Long start) throws Exception {
		List<Chat> list =  chatDao.get(user,limit,start);
		if(list == null || list.size() == 0) {
			throw new InvalidInputException("Chat of the user = "+user+" does not exist");
		}
		return list;
	}

	@Transactional
	@Override
	public ResponseEntity<String> delete(Long id) {
		Chat chat = chatDao.get(id);
		if(chat == null) {
			return new  ResponseEntity<>("Their is not field present to delete", HttpStatus.BAD_REQUEST);
		}
		chatDao.delete(chat);
		return new ResponseEntity<>("Entry Deleted Successfully", HttpStatus.OK);
	}

	@Transactional
	@Override
	public void deleteAll(String user)  throws Exception {
		List<Chat> list = chatDao.getAll(user);
		if(list == null || list.size() == 0) {
			throw new InvalidInputException("Chat of the user = "+user+" does not exist");
		}
		chatDao.deleteAll(list);
	}

}
