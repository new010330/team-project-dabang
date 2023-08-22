package spring.teamproject.dabang.domain.manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.teamproject.dabang.web.dto.manage.CreateRoomInfoRespDto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomInfoFile {
	
	private int room_code;
	private int file_code;
	private String photo_filename;
	

}
