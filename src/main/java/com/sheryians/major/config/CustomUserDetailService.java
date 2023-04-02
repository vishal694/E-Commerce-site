package com.sheryians.major.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.CustomerUserDetails;
import com.sheryians.major.model.UserModel;
import com.sheryians.major.repo.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserModel> user = userRepo.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("We are not found"));
		return user.map(CustomerUserDetails::new).get();
	}

}
