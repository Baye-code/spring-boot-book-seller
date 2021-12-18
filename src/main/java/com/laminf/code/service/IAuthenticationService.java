package com.laminf.code.service;

import com.laminf.code.model.User;

public interface IAuthenticationService {

	User signInAndReturnJWT(User signInRequest);

}
