package spring.teamproject.dabang.web.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserEmailCheckReqDto {

	@NotBlank
	@Email
	private String email;
	
}
