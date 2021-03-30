package com.arkeup.poc.common.utils;

import java.util.List;

import org.mapstruct.Mapper;

import com.arkeup.poc.data.dto.CommentDTO;
import com.arkeup.poc.data.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
	
	CommentDTO mp(Comment comment);

	Comment map(CommentDTO dto);

	List<CommentDTO> map(List<Comment> comment);

}
