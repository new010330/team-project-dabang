package spring.teamproject.dabang.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserPasswordCheckReqDto {

//	@NotBlank
//	@Size(min = 8, max = 20)
//	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,20}$")
	private String password;
}
