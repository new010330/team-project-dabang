package spring.teamproject.dabang.domain.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomInfoRepository {
	
	public RoomInfo getRoomInfoByRoomcode(int roomCode) throws Exception;
	
	public RoomInfo getRoomListByRoomcode(int roomCode) throws Exception;
	
	public int save(RoomInfo roomInfo) throws Exception;
	
	public int saveFiles(List<RoomInfoFile> list) throws Exception;
	
	public int updateRoomInfoByRoomcode(RoomInfo roomInfo) throws Exception;
	
	public int remove(int roomcode) throws Exception;

}
