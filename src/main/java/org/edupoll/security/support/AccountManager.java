package org.edupoll.security.support;

import java.util.Optional;

import org.edupoll.model.entity.User;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountManager implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		Optional<User> user = userRepository.findById(username);
//		if(user.isPresent()) {
//			User got = user.get();
//			return new Account(got);
//			Account account = userRepository.findById(username).map(t -> new Account(t)).get();
//			return account;
//		}else {
//			throw new UsernameNotFoundException("notfound : " + username);
//		}
		
		
		return userRepository.findById(username).map(t -> new Account(t))
				.orElseThrow(()-> new UsernameNotFoundException("not found : " + username));
	}
}
