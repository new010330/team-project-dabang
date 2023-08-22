package spring.teamproject.dabang.service.manage;

import java.util.List;

import spring.teamproject.dabang.domain.manage.RoomData;
import spring.teamproject.dabang.web.dto.manage.ReadRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.map.ReadSimpleDataRespDto;

public interface RoomDataService {

	public List<RoomData> readRoomInfoRespDto(String address) throws Exception;
	
	public List<String> readAddressList() throws Exception; // kjh 작업중
}
