package com.arkeup.poc.services.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arkeup.poc.data.entity.Car;
import com.arkeup.poc.data.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query("select c from Comment c WHERE c.car = :car")
	Page<Comment> findCommentByVehicle(Car car, Pageable pageable);

}
