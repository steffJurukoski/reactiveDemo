package com.example.reactiveDemo.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Books {

	@Id
	private String _id;
    private int id;
    private String title;
    private String author;
    
    // constructor, getters and setters
    
    public Books() {
    	
    }

	public Books(String _id, int id, String title, String author) {
		super();
		this._id = _id;
		this.id = id;
		this.title = title;
		this.author = author;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	
	
	
    
    
    
}

