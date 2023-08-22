package spring.teamproject.dabang.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {	
	private int user_code;
	private String user_email;
	private String user_password;
	private String user_checkpassword;
	private String oauth2_id;
	private String nName;
	private String user_roles;
	private String user_provider;
	
	public List<String> getUserRoles(){
		if(user_roles == null || user_roles.isBlank()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(user_roles.replaceAll(" ","").split(","));
	}
	
}
