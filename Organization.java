package com.wowment.mongo;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Organization")
public class Organization extends BaseModel{

	@Id
	private String id;
	private String name;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", description=" + description + "]";
	}


}