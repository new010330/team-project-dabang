package spring.teamproject.dabang.web.dto.account;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetAccountRespDto {
	private int usercode;
	private String username;
	private String phoneNumber;
	private String nName;
}
