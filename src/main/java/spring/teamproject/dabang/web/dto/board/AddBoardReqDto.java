package spring.teamproject.dabang.web.dto.board;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;
import spring.teamproject.dabang.domain.board.Board;

@Data
public class AddBoardReqDto {
	private int noticeCode;
	private String noticeTitle;
	private String noticeContent;
	private String noticeNname;
	private int userCode;
	
	private List<MultipartFile> file;
//	
//	public Board toEntity() {
//		return Board.builder()
//				.notice_title(noticeTitle)
//				.notice_content(noticeContent)
//				.user_code(noticeCode)
//				.notice_nName(noticeNname)
//				.build();
//	}
}

