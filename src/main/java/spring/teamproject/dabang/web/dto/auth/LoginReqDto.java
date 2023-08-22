package spring.teamproject.dabang.web.dto.auth;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class LoginReqDto {
	
	    @NotBlank
	    @Email
	    private String email;

	    @NotBlank
	    private String password;

	

}
