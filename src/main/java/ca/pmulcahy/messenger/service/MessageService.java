package ca.pmulcahy.messenger.service;

import java.util.List;

import ca.pmulcahy.messenger.model.Message;

public class MessageService {
	public List<Message> getAllMessages() {
		final Message m1 = new Message(1L, "Hello World!", "Mulcahy");
		final Message m2 = new Message(2L, "Hello Jersey!", "Mulcahy");
		final List<Message> list = List.of(m1, m2);
		return list;
	}
}
