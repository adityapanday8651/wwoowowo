package com.wowment.mongo;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Program")
public class Program extends BaseModel{

	@Id
	private String id;
	private String name;
	private String description;
	private XMLGregorianCalendar startDate;
	private String duration;
	private String frequency;
	private String matching;
	private String status;
	private String mentorList;
	private String menteeList;
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
	public XMLGregorianCalendar getStartDate() {
		return startDate;
	}
	public void setStartDate(XMLGregorianCalendar startDate) {
		this.startDate = startDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getMatching() {
		return matching;
	}
	public void setMatching(String matching) {
		this.matching = matching;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMentorList() {
		return mentorList;
	}
	public void setMentorList(String mentorList) {
		this.mentorList = mentorList;
	}
	public String getMenteeList() {
		return menteeList;
	}
	public void setMenteeList(String menteeList) {
		this.menteeList = menteeList;
	}
	@Override
	public String toString() {
		return "Program [id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", duration=" + duration + ", frequency=" + frequency + ", matching=" + matching + ", status="
				+ status + ", mentorList=" + mentorList + ", menteeList=" + menteeList + "]";
	}
	
	
}