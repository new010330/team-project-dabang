package spring.teamproject.dabang.service.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.teamproject.dabang.domain.user.User;
import spring.teamproject.dabang.domain.user.UserRepository;
import spring.teamproject.dabang.web.dto.auth.SignupReqDto;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = null;
		try {
			userEntity = userRepository.findUserByUserEmail(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		
		if (userEntity == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
		}
		
		return new PrincipalDetails(userEntity);
	}
	
	public boolean addUser(SignupReqDto signupReqDto) throws Exception {
		User user = signupReqDto.toEntity();
		user.setUser_password(new BCryptPasswordEncoder().encode(signupReqDto.getPassword()));
		return userRepository.save(signupReqDto.toEntity()) > 0;
	}
}
