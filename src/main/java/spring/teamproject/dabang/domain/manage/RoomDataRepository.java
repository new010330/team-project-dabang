package spring.teamproject.dabang.domain.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.teamproject.dabang.web.dto.manage.ReadRoomInfoRespDto;

@Mapper
public interface RoomDataRepository {
	public List<RoomData> readRoomData(String address) throws Exception;
	public List<String> getAddressInfo();
}
