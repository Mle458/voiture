package com.arkeup.poc.services.application.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arkeup.poc.data.entity.User;
import com.arkeup.poc.services.repository.UserRepository;

@Service
public class UserASImpl implements UserDetailsService, UserAS {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	String defaultUsername = "defaultuser";
	String defaultPassword = "password";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
		}
		throw new UsernameNotFoundException("Username or password error.");
	}
	
	@PostConstruct
	public void init() {
		importDefaultUser();
	}

	public void importDefaultUser() {
		User user = userRepository.findByUsername(defaultUsername);
		if (user == null) {
			user = new User();
			user.setUsername(defaultUsername);
			user.setPassword(passwordEncoder.encode(defaultPassword));
			userRepository.save(user);
		}
	}

}
