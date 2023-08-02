package org.edupoll.model.dto.response;

import java.text.SimpleDateFormat;
import java.util.List;

import org.edupoll.model.entity.Attendance;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.Reply;
import org.edupoll.model.entity.User;

public class MoimResponseData {
	String id;
	String managerId;
	String title;
	String cate;
	String description;
	Integer maxPerson;
	Integer currentPerson;
	String targetDate;
	Integer duration;
	
	User manager;
	
	List<Reply> replys;
	
	List<Attendance> attendances;
	
	public MoimResponseData(Moim moim) {
		this.id = moim.getId();
		this.managerId = moim.getManager().getId();
		this.title = moim.getTitle();
		this.cate = moim.getCate();
		this.description = moim.getDescription();
		this.maxPerson = moim.getMaxPerson();
		this.currentPerson = moim.getCurrentPerson();
		SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");
		this.targetDate = dayFmt.format(moim.getTargetDate());
		this.duration = moim.getDuration();
		
		this.manager = moim.getManager();
		this.replys = moim.getReplys(); 
		this.attendances = moim.getAttendances();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentPerson(Integer currentPerson) {
		this.currentPerson = currentPerson;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}
	
}
