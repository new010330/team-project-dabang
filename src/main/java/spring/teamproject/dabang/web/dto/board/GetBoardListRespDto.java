package spring.teamproject.dabang.web.dto.board;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetBoardListRespDto {
	private int boardCode;
	private int boardUsercode;
	private int boardType;
	private String boardNname;
	private String boardTitle;
	private String boardContent;
	private int boardNumber;
	private String userId;
	private String createDate;
	private int boardCount;
	private int totalboardCount;
}


