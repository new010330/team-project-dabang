package spring.teamproject.dabang.service.auth;

import javax.validation.Valid;

import spring.teamproject.dabang.web.dto.auth.LoginReqDto;
import spring.teamproject.dabang.web.dto.auth.SignupReqDto;
import spring.teamproject.dabang.web.dto.auth.UserEmailCheckReqDto;
import spring.teamproject.dabang.web.dto.auth.UserPasswordCheckReqDto;

public interface AuthService {
	public boolean checkUserEmail(UserEmailCheckReqDto userEmailCheckReqDto) throws Exception;
	public boolean checkUserPassword(UserPasswordCheckReqDto userPasswordCheckReqDto) throws Exception;
	public boolean signup(SignupReqDto signupReqDto) throws Exception;
	public boolean login(LoginReqDto loginReqDto) throws Exception;
}
