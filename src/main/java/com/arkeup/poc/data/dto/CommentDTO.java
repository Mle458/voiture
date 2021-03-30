package com.arkeup.poc.data.dto;

import java.util.Date;

import com.arkeup.poc.data.entity.Car;
import com.arkeup.poc.data.entity.User;

public class CommentDTO {
	
	private Long id;

	private String commentText;

	private Date commentDate;

	private User user;
	
	private Car car;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public CommentDTO() {
	
	}
	
}
