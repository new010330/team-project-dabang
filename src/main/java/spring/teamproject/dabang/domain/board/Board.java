package spring.teamproject.dabang.domain.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import spring.teamproject.dabang.web.dto.board.GetBoardListRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	private int notice_code;
	private int user_code;
	private String notice_title;
	private String notice_content;
	private String notice_nName;
	
	
	private int file_code;
	private String file_name;
	private LocalDateTime create_date;
	
//	private int total_board_count;
	
	public GetBoardListRespDto toListDto() {
		return GetBoardListRespDto.builder()
				.boardCode(notice_code)
				.boardUsercode(user_code)
				.boardNname(notice_nName)
				.boardTitle(notice_title)
				.boardContent(notice_content)
				.createDate(create_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
				.build();
	}
}











