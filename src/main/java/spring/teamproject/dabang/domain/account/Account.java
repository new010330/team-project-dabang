package spring.teamproject.dabang.domain.account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import spring.teamproject.dabang.web.dto.account.GetAccountRespDto;
import spring.teamproject.dabang.web.dto.board.GetBoardListRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

	private String user_email;
	private int user_code;
	private String user_password;
	private String user_checkpassword;
	private String oauth2_id;
	private String user_name;
	private String nName;
	private String phone_number;
	private String user_roles;
	
	
	public GetAccountRespDto userDataToDto() {
		return GetAccountRespDto.builder()
				.username(user_name)
				.phoneNumber(phone_number)
				.nName(nName)
				.build();
	}
}