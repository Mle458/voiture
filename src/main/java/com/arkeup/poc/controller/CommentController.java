package com.arkeup.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.poc.data.dto.CommentDTO;
import com.arkeup.poc.services.application.comment.CommentAS;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
	
	@Autowired
	CommentAS commentAS;

	@PreAuthorize("hasRole('ROLE_USER')")
	@ApiOperation(value = "Save the comment of the connected user about a car", notes = "Save the comment of the connected user about a car")
	@PostMapping("/save")
	public ResponseEntity<CommentDTO> saveCarComment(@RequestBody CommentDTO dto) throws Exception {
		return new ResponseEntity<CommentDTO>(commentAS.commentSave(dto), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@ApiOperation(value = "Get comments about a car", notes = "Get comments about a car")
	@GetMapping("/list")
	public ResponseEntity<List<CommentDTO>> getCarComments(
			@ApiParam(name = "carID") @RequestParam(defaultValue = "0") Long carID,
			@ApiParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
			@ApiParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
		return new ResponseEntity<>(commentAS.getCommentsByCar(carID, pageNo, pageSize, null), HttpStatus.OK);
	}

}
