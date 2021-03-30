package com.arkeup.poc.services.application.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.poc.common.utils.CarMapper;
import com.arkeup.poc.common.utils.CommentMapper;
import com.arkeup.poc.data.dto.CommentDTO;
import com.arkeup.poc.data.entity.Car;
import com.arkeup.poc.data.entity.Comment;
import com.arkeup.poc.data.entity.User;
import com.arkeup.poc.services.repository.CarRepository;
import com.arkeup.poc.services.repository.CommentRepository;
import com.arkeup.poc.services.repository.UserRepository;

@Service
public class CommentASImpl implements CommentAS {
	
	@Autowired
	CommentMapper commentMapper;

	@Autowired
	CarMapper carMapper;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CarRepository carRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public CommentDTO commentSave(CommentDTO dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepository.findByUsername(currentPrincipalName);
		dto.setUser(user);
		dto.setCommentDate(new Date());
		Comment comment = commentRepository.save(commentMapper.map(dto));
		dto.setId(comment.getId());
		return dto;
	}

	@Override
	public List<CommentDTO> getCommentsByCar(Long carID, Integer pageNo, Integer pageSize, String sortBy) {
		Optional<Car> car = carRepository.findById(carID);
		if (car.isPresent()) {
			Pageable paging;
			if (StringUtils.isEmpty(sortBy)) {
				sortBy = "id";
			}
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
			Page<Comment> commentsPaged = commentRepository.findCommentByVehicle(car.get(), paging);
			if (commentsPaged.hasContent()) {
				return commentMapper.map(commentsPaged.getContent());
			}
		}
		
		return new ArrayList<CommentDTO>();
		
	}

}
