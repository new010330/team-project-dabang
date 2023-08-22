package spring.teamproject.dabang.service.auth;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.domain.user.User;
import spring.teamproject.dabang.domain.user.UserRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("시작");
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info(">>>> ClientRegistration: {} " + userRequest.getClientRegistration());
		log.info(">>>> oAuth2User: {} ", oAuth2User);
		
		String provider = null;
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		Map<String, Object> attributes = oAuth2User.getAttributes();
//		System.out.println(" >>>>>>>>>>>>>>>>>> !!!!!!!!!!!!!!!!!!!!!!!! attributes" + attributes);
		
		provider = clientRegistration.getClientName();
		log.info(">>> provider: {} " + provider);
		User user = getOAuth2User(provider, attributes);
		
		log.info(">>> user3: {} " + user);
		
		return new PrincipalDetails(user, attributes);
	}
	
	private User getOAuth2User(String provider, Map<String, Object> attributes) throws OAuth2AuthenticationException {
	    User user = null;
	    String oauth2_id = null;
	    String id = null;
	    Map<String, Object> response = null;
	    String email = null; // email 변수를 선언합니다.
	    

	    if (provider.equalsIgnoreCase("kakao")) {
	        response = attributes;
	        System.out.println("attribues" + attributes);
	        System.out.println("response" + response);
	        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");
	        email = (String) kakaoAccount.get("email");

	        if (email == null) {
	            // 이메일이 없으면 에러 처리
	            throw new OAuth2AuthenticationException("Email not found in Kakao response!");
	        }

	        id = response.get("id").toString();
	        oauth2_id = provider + "_" + id;
	    } else if (provider.equalsIgnoreCase("facebook")) {
	        response = (Map<String, Object>) attributes.get("response");
	        
	        
	        id = (String) response.get("id");
	        oauth2_id = provider + "_" + id;
	    } else {
	        throw new OAuth2AuthenticationException("DATABASE Error!");
	    }
	    

	    try {
	        user = userRepository.findOAuth2UserByUsername(oauth2_id);
	        if (user == null) {
	            // DB에 사용자 정보가 없는 경우, 새로운 사용자로 등록
	            user = User.builder()
	                    .user_email(email)
	                    .user_password(new BCryptPasswordEncoder().encode(id))
	                    .user_checkpassword(new BCryptPasswordEncoder().encode(id))
	                    .oauth2_id(oauth2_id)
	                    .nName(generateRandomNickname())
	                    .user_roles("ROLE_USER")
	                    .user_provider(provider)
	                    .build();
	            log.info("Before userRepository.save(user)");
	            userRepository.save(user);
	            log.info("After userRepository.save(user)");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new OAuth2AuthenticationException("DATABASE Error");
	    }


	    return user;
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
