package ca.pmulcahy.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ca.pmulcahy.messenger.database.DatabaseClass;
import ca.pmulcahy.messenger.model.Message;

public class MessageService {
	
	private static Map<Long, Message> messages = DatabaseClass.getMessages();
	
	static {
		messages.put(1L, new Message(1, "Hello World", "Mulcahy"));
		messages.put(2L, new Message(2, "Hello Jersey", "Mulcahy"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		return messages.values()
					   .stream()
					   .filter(message -> message.getCreated()
							   					 .getYear() == year)
					   .collect(Collectors.toList());
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		var list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()) {
			return new ArrayList<Message>();
		}
		return list.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
