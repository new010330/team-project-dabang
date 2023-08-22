package spring.teamproject.dabang.web.controller.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;

import lombok.RequiredArgsConstructor;
import spring.teamproject.dabang.handler.aop.annotation.ValidCheck;
import spring.teamproject.dabang.service.auth.AuthService;
import spring.teamproject.dabang.service.auth.PrincipalDetails;
import spring.teamproject.dabang.service.auth.PrincipalDetailsService;
import spring.teamproject.dabang.web.dto.CMRespDto;
import spring.teamproject.dabang.web.dto.auth.LoginReqDto;
import spring.teamproject.dabang.web.dto.auth.SignupReqDto;
import spring.teamproject.dabang.web.dto.auth.UserEmailCheckReqDto;
import spring.teamproject.dabang.web.dto.auth.UserPasswordCheckReqDto;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;
	private final PrincipalDetailsService principalDetailsService;
	
	@ValidCheck
	@PostMapping("/welcome/join")
	public ResponseEntity<?> join(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
		
		boolean status = false;
		System.out.println(signupReqDto.getPassword());
		try {
			signupReqDto.setNName(generateRandomNickname());
			status = principalDetailsService.addUser(signupReqDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "회원가입 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공", status));
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
	
	@ValidCheck
	@GetMapping("/join/email/validation")
	public ResponseEntity<?> checkUserEmail(@Valid UserEmailCheckReqDto userEmailCheckReqDto, BindingResult bindingResult) {
		boolean status = false;
		System.out.println("Authcontroller " + userEmailCheckReqDto.getEmail());
		
		try {
			status = authService.checkUserEmail(userEmailCheckReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "이메일 중복검사 실패,서버오류", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "이메일 중복검사 완료", status));
	}
	
	@ValidCheck
	@GetMapping("/join/password/validation")
	public ResponseEntity<?> checkUserPassword(@Valid UserPasswordCheckReqDto userPasswordCheckReqDto, BindingResult bindingResult){
		boolean status = false;
		System.out.println("Authcontroller" + userPasswordCheckReqDto.getPassword());
		
		try {
			status = authService.checkUserPassword(userPasswordCheckReqDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new CMRespDto<>(1, "비밀번호 유효성 검사 성공", status));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(-1, "비밀번호 유효성 검사 실패", status));
	}
	
	
	
	@GetMapping("/principal")
	public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal PrincipalDetails principalDetails){
	    if(principalDetails == null) {
	        return  ResponseEntity.badRequest().body(new CMRespDto<>(-1, "principal is null", null));
	    }
	    return  ResponseEntity.ok().body(new CMRespDto<>(1, "success load", principalDetails.getUser()));
	}

	
	// 로그인 처리를 위한 메서드
	@ValidCheck
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto, BindingResult bindingResult) {
		boolean status = false;

		// Validation 오류 체크
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "입력값이 올바르지 않습니다.", status));
		}

		// 로그인 처리
		try {
			status = authService.login(loginReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "로그인 실패", status));
		}

		if (status) {
			return ResponseEntity.ok().body(new CMRespDto<>(1, "로그인 성공", status));
			
		} else {
			return ResponseEntity.ok().body(new CMRespDto<>(0, "아이디 또는 비밀번호가 일치하지 않습니다.", status));
		}
		
		
		
	}
	
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        boolean status = false;
        
        try {
            // 로그아웃 처리 (세션 무효화 또는 토큰 무효화 등)
            SecurityContextHolder.clearContext(); // 현재 사용자의 SecurityContext를 클리어하여 로그아웃
            // 기타 로그아웃 처리 작업 (세션 무효화 등)

            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status) {
            return ResponseEntity.ok().body(new CMRespDto<>(1, "로그아웃 성공", status));
        } else {
            return ResponseEntity.ok().body(new CMRespDto<>(0, "로그아웃 실패", status));
        }
    }


	

	

 }
	

	



