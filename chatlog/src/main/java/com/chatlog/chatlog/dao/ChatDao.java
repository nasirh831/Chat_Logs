package com.chatlog.chatlog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chatlog.chatlog.model.Chat;

@Repository
public interface ChatDao {

	Chat save(Chat chat);
	void delete(Chat chat);
	void deleteAll(List<Chat> list);
	List<Chat> get(String user, Integer limit, Long start);
	Chat get(Long id);
	List<Chat> getAll(String user);
}
