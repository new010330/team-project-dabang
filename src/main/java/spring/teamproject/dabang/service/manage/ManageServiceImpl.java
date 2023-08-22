package spring.teamproject.dabang.service.manage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import org.apache.commons.io.IOExceptionWithCause;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.domain.manage.RoomInfo;
import spring.teamproject.dabang.domain.manage.RoomInfoFile;
import spring.teamproject.dabang.domain.manage.RoomInfoRepository;
import spring.teamproject.dabang.web.dto.manage.CreateRoomInfoReqDto;
import spring.teamproject.dabang.web.dto.manage.CreateRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.manage.ReadRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.manage.UpdateRoomInfoReqDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManageServiceImpl implements ManageService{
	
	@Value("${file.path}")
	private String filePath;
	
	private final RoomInfoRepository roomInfoRepository;

	@Override
	public ReadRoomInfoRespDto readRoomInfo(int roomcode) throws Exception {
		
		return roomInfoRepository.getRoomInfoByRoomcode(roomcode).toReadRoomInfoDto();
	}
	
	@Override
	public ReadRoomInfoRespDto getRoomList(int roomcode) throws Exception {
		return roomInfoRepository.getRoomListByRoomcode(roomcode).toGetRoomInfoDto();
	}

	@Override
	public int createRoomInfo(CreateRoomInfoReqDto createRoomInfoReqDto) throws Exception {
		
		Predicate<String> predicate = (filename) -> !filename.isBlank();		
		
		List<Integer> facAircndList = convertStringToList(createRoomInfoReqDto.getFacAircnd());
		createRoomInfoReqDto.setFacAircndList(facAircndList);
		List<Integer> facCommList = convertStringToList(createRoomInfoReqDto.getFacComm());
	    createRoomInfoReqDto.setFacCommList(facCommList);
	    List<Integer> facSecList = convertStringToList(createRoomInfoReqDto.getFacSecurity());
	    createRoomInfoReqDto.setFacSecList(facSecList);
	    List<Integer> facOtherList = convertStringToList(createRoomInfoReqDto.getFacOther());
	    createRoomInfoReqDto.setFacOtherList(facOtherList);
		/*
		String[] facAircndArray = createRoomInfoReqDto.getFacAircnd().split(",");
		List<Integer> facAircndList = new ArrayList<>();
		  
		for(String s : facAircndArray) { facAircndList.add(Integer.parseInt(s)); }
		  
		createRoomInfoReqDto.setFacAircndList(facAircndList);
		 
	    String[] facCommArray = createRoomInfoReqDto.getFacComm().split(",");
	    List<Integer> facCommList = new ArrayList<>();
	    
	    for(String s : facCommArray) {
	    	facCommList.add(Integer.parseInt(s));
	    }
	    createRoomInfoReqDto.setFacCommList(facCommList);
	    
	    String[] facSecArray = createRoomInfoReqDto.getFacSecurity().split(",");
	    List<Integer> facSecList = new ArrayList<>();
	    
	    for(String s : facSecArray) {
	    	facSecList.add(Integer.parseInt(s));
	    }
	    createRoomInfoReqDto.setFacSecList(facSecList);
	    
	    String[] facOtherArray = createRoomInfoReqDto.getFacOther().split(",");
	    List<Integer> facOtherList = new ArrayList<>();
	    
	    for(String s : facOtherArray) {
	    	facOtherList.add(Integer.parseInt(s));
	    }
	    createRoomInfoReqDto.setFacOtherList(facOtherList);
	    */
		
	    RoomInfo roomInfo = null;
	    log.info("Impl 전 >>> {}", roomInfo);
	    
	    roomInfo = createRoomInfoReqDto.toEntity();
	    roomInfoRepository.save(roomInfo);
	    log.info("Impl 후 >>>{}",roomInfo);
	    
		
		if(predicate.test(createRoomInfoReqDto.getFile().get(0).getOriginalFilename())) {
			List<RoomInfoFile> roomInfoFiles = new ArrayList<RoomInfoFile>();
			
			for(MultipartFile file : createRoomInfoReqDto.getFile()) {
				String originFilename = file.getOriginalFilename();
				String tempFilename = UUID.randomUUID().toString().replace("-", "") + "_" + originFilename;
				log.info("tempFilename : " + tempFilename);
				
				Path uploadPath = Paths.get(filePath, tempFilename);
				
				/*
				 * File f = new File(filePath + "roomPhoto/"); if(!f.exists()) { f.mkdir(); }
				 */
				
				// 로깅을 위한 절대 경로 확인
				log.info("Absolute path: " + uploadPath.toAbsolutePath().toString());
				log.info("Final upload path: " + uploadPath.toString());

				// 파일 존재 여부 확인 로깅
				if (Files.exists(uploadPath)) {
				    log.warn("File already exists: " + uploadPath.toString());
				} else {
				    log.info("File doesn't exist, creating a new one.");
				}

				// 파일 쓰기 시도
				log.info("Attempting to write to file: " + uploadPath.toString());
				
				if (!Files.exists(uploadPath.getParent())) {
				    Files.createDirectories(uploadPath.getParent());
				}
				
				try {
				Files.write(uploadPath, file.getBytes());
				} catch (IOException e) {
					log.error("file writing error: ", e);
				}
				
				roomInfoFiles.add(RoomInfoFile.builder()
												.room_code(roomInfo.getRoom_code())
												.photo_filename(tempFilename)
												.build());
				/*
				 * roomInfoFiles.add(RoomInfoFile.builder() .photo_filename(tempFilename)
				 * .room_code(roomInfo.getRoom_code()) .build());
				 */
			}
			
			roomInfoRepository.saveFiles(roomInfoFiles);
		}
		
		/* RoomInfoFile RoomInfoFileEntity = createRoomInfoReqDto.toFileEntity(); */
				
		
		
		return roomInfo.getRoom_code();
				
			
	}

	@Override
	public boolean updateRoomInfo(UpdateRoomInfoReqDto updateRoomInfoReqDto) throws Exception {
		
		List<Integer> facAircndList = convertStringToList(updateRoomInfoReqDto.getFacAircnd());
		updateRoomInfoReqDto.setFacAircndList(facAircndList);
		
		List<Integer> facCommList = convertStringToList(updateRoomInfoReqDto.getFacComm());
		updateRoomInfoReqDto.setFacCommList(facCommList);

	    List<Integer> facSecList = convertStringToList(updateRoomInfoReqDto.getFacSecurity());
	    updateRoomInfoReqDto.setFacSecList(facSecList);

	    List<Integer> facOtherList = convertStringToList(updateRoomInfoReqDto.getFacOther());
	    updateRoomInfoReqDto.setFacOtherList(facOtherList);
		
		RoomInfo roomInfoEntity = updateRoomInfoReqDto.toEntity();
		
		return roomInfoRepository.updateRoomInfoByRoomcode(roomInfoEntity) > 0;
	}

	@Override
	public boolean deleteRoomInfo(int roomcode) throws Exception{
		
		return roomInfoRepository.remove(roomcode) > 0;
	}
	
	// 반복구문을 메서드로 분리.
	private List<Integer> convertStringToList(String data) {
		String[] dataArray = data.split(",");
	    List<Integer> dataList = new ArrayList<>();

	    for(String s : dataArray) {
	        dataList.add(Integer.parseInt(s));
	    }

	    return dataList;
	}
	
	
}



