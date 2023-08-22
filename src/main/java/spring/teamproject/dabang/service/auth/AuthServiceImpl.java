package spring.teamproject.dabang.service.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.domain.user.User;
import spring.teamproject.dabang.domain.user.UserRepository;
import spring.teamproject.dabang.web.dto.auth.LoginReqDto;
import spring.teamproject.dabang.web.dto.auth.SignupReqDto;
import spring.teamproject.dabang.web.dto.auth.UserEmailCheckReqDto;
import spring.teamproject.dabang.web.dto.auth.UserPasswordCheckReqDto;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	
	@Override
	public boolean checkUserEmail(UserEmailCheckReqDto userEmailCheckReqDto) throws Exception {
		System.out.println("AuthSer " +userEmailCheckReqDto.getEmail());
		
		return userRepository.findUserByUserEmail(userEmailCheckReqDto.getEmail()) == null;
	}
//
//	public User login(SignupReqDto signupReqDto) {
//
//	}
//	

	@Override
	public boolean signup(SignupReqDto signupReqDto) {
	    User user = User.builder()
	            .user_email(signupReqDto.getEmail())
	            .user_password(new BCryptPasswordEncoder().encode(signupReqDto.getPassword()))
	            .user_checkpassword(new BCryptPasswordEncoder().encode(signupReqDto.getPassword()))
	            .oauth2_id(null)
	            .user_roles("ROLE_USER")
	            .user_provider(null)
	            .nName(generateRandomNickname())
	            .build();
	    try {
			userRepository.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return true;
	}


	@Override
	public boolean checkUserPassword(UserPasswordCheckReqDto userPasswordCheckReqDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public boolean login(LoginReqDto loginReqDto) {
        // 단계 1: 제공된 이메일을 사용하여 데이터베이스에서 사용자 정보를 가져옵니다.
        String email = loginReqDto.getEmail();
        User user = null;
		try {
			user = userRepository.findUserByUserEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("au서비스 임플리에서 ");
		}

        if (user == null) {
            // 데이터베이스에서 사용자를 찾을 수 없는 경우, 로그인 실패
        	System.out.println("사용자 찾을 수 없음");
            return false;
        }

        // 단계 2: 제공된 비밀번호와 데이터베이스에 저장된 해시된 비밀번호를 비교합니다.
        String providedPassword = loginReqDto.getPassword();
        String hashedPassword = user.getUser_password(); // 사용자 엔티티에 해시된 비밀번호가 저장된 필드를 가정합니다.

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(providedPassword, hashedPassword)) {
            // 비밀번호가 일치하는 경우, 로그인 성공
        	System.out.println("로그인 성공");
        	
        	 // 권한 확인 로직 추가
            String userRoles = user.getUser_roles();
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
            
         // Spring Security의 인증 객체 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, hashedPassword, authorities);

            // 인증 처리
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return true;
        } else {
            // 비밀번호가 일치하지 않는 경우, 로그인 실패
            return false;
        }
    }
	
	 private String generateRandomNickname() {
	        List<String> adjectives = Arrays.asList(
	            "기분나쁜", "기분좋은", "신바람나는", "상쾌한", "짜릿한", "그리운", "자유로운", "서운한",
	            "울적한", "비참한", "위축되는", "긴장되는", "두려운", "당당한", "배부른", "수줍은",
	            "창피한", "멋있는", "열받은", "심심한", "잘생긴", "이쁜", "시끄러운"
	        );

	        List<String> animals = Arrays.asList(
	            "사자", "코끼리", "호랑이", "곰", "여우", "늑대", "너구리", "침팬치", "고릴라",
	            "참새", "고슴도치", "강아지", "고양이", "거북이", "토끼", "앵무새", "하이에나",
	            "돼지", "하마", "원숭이", "물소", "얼룩말", "치타", "악어", "기린", "수달",
	            "염소", "다람쥐", "판다"
	        );

	        Collections.shuffle(adjectives);
	        Collections.shuffle(animals);

	        return adjectives.get(0) + animals.get(0);
	    }

}
