package com.laminf.code.service;

import java.util.Optional;

import com.laminf.code.model.User;

public interface IUserService {

	User saveUser(User user);

	Optional<User> findByUsername(String username);

	void makeAdmin(String username);

}
