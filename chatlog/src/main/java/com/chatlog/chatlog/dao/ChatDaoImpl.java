package com.chatlog.chatlog.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatlog.chatlog.model.Chat;

@Repository
public class ChatDaoImpl implements ChatDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Chat save(Chat chat) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(chat);
		return chat;
	}

	@Override
	public void delete(Chat chat) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(chat);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAll(List<Chat> list) {
		Session currentSession = entityManager.unwrap(Session.class);
		for(Chat chat : list) {
			currentSession.delete(chat);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chat> get(String user,Integer limit ,Long start) {
		Session currentSession = entityManager.unwrap(Session.class);
		List<Chat> list = currentSession.createQuery("from Chat where user=:user and id>:id ORDER BY timestamp DESC")
				.setParameter("user", user)
				.setParameter("id",start)
				.setMaxResults(limit)
				.list();
		return list;
	}

	@Override
	public Chat get(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Chat chat = currentSession.get(Chat.class, id);
		return chat;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Chat> getAll(String user){
		Session currentSession = entityManager.unwrap(Session.class);
		List<Chat> list = currentSession.createQuery("from Chat where user=:user")
							.setParameter("user", user)
							.list();
		return list;
	}

	
}
