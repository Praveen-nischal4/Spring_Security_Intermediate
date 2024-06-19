package com.mysecurity.dao;

import com.mysecurity.model.SignupDTO;

public interface SingupDAO {

	void saveUser(SignupDTO signupDTO);
}
