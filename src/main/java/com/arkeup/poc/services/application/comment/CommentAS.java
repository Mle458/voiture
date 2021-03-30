package com.arkeup.poc.services.application.comment;

import java.util.List;

import com.arkeup.poc.data.dto.CommentDTO;

public interface CommentAS {
	
	public CommentDTO commentSave(CommentDTO dto);
	public List<CommentDTO> getCommentsByCar(Long carID, Integer pageNo, Integer pageSize, String sortBy);

}