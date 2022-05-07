package ca.pmulcahy.messenger.model;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	private long id;
	private String message;
	private LocalDate created;
	private String author;
	
	public Comment() {}
	
	public Comment(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = LocalDate.now();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
