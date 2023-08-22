package spring.teamproject.dabang.web.dto.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.message.Message;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import spring.teamproject.dabang.domain.user.User;

@Data
public class SignupReqDto {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank()
	@Size(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,20}$")
	private String password;
	
	@NotBlank
	private String checkpassword;
	
	private String nName;

	@AssertTrue()
	private boolean	 checkUseremailFlag;
	
	public User toEntity() {
		return User.builder()
				.user_email(email)
				.user_password(new BCryptPasswordEncoder().encode(password))
				.user_checkpassword(checkpassword)
				.user_roles("ROLE_USER")
				.nName(nName)
				.build();
				
					
	}
}
