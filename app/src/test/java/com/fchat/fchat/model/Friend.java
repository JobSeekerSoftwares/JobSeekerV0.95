package com.fchat.fchat.model;

import java.io.Serializable;

public class Friend implements Serializable {
	private String id;
	private String name;
	private String photo;
	private String email;

	public Friend(String id, String name, String photo, String email) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoto() {
		return photo;
	}

	public String getEmail() {
		return email;
	}
}
