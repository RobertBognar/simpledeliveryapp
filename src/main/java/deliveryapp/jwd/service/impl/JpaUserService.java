package deliveryapp.jwd.service.impl;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import deliveryapp.jwd.dto.UserPasswordChangeDto;
import deliveryapp.jwd.model.User;
import deliveryapp.jwd.repository.UserRepository;
import deliveryapp.jwd.service.UserService;


@Service
public class JpaUserService implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> one(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> all() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) { return userRepository.save(user); }

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> byUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}

	@Override
	public boolean changePassword(Long id, UserPasswordChangeDto changeDto) {
		Optional<User> result = userRepository.findById(id);
		
		if(!result.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		User user = result.get();
		
		if(!user.getUserName().equals(changeDto.getUsername())
			|| !user.getPassword().equals(changeDto.getOldPassword())){
			return false;
		}

		String encodedPass = passwordEncoder.encode(changeDto.getPassword());
		user.setPassword(encodedPass);

		userRepository.save(user);
		
		return true;
	}

	@Override
	public Page<User> all(int pageNum) {
		return userRepository.findAll(PageRequest.of(pageNum, 10));
	}

}
