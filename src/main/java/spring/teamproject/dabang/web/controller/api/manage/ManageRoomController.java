package spring.teamproject.dabang.web.controller.api.manage;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.service.manage.ManageService;
import spring.teamproject.dabang.web.dto.CMRespDto;
import spring.teamproject.dabang.web.dto.manage.CreateRoomInfoReqDto;
import spring.teamproject.dabang.web.dto.manage.CreateRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.manage.ReadRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.manage.UpdateRoomInfoReqDto;


@Slf4j
@Data
@RestController
@RequestMapping("api/v1/manage")
@RequiredArgsConstructor
public class ManageRoomController {
	
	private final ManageService manageService;
	
	@Value("${file.path}")
	private String filePath;
	
	@GetMapping("/list/{roomcode}")
	public ResponseEntity<?> getRoomList(@PathVariable int roomcode) {
		ReadRoomInfoRespDto readRoomInfoRespDto = null;		

		try {
			readRoomInfoRespDto = manageService.getRoomList(roomcode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "리스트 불러오기 실패", readRoomInfoRespDto));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "리스트 불러오기 성공", readRoomInfoRespDto));
	}
	
	@GetMapping("/content/{roomcode}")
	public ResponseEntity<?> getRoomInfo(@PathVariable int roomcode) {
		
		ReadRoomInfoRespDto readRoomInfoRespDto = null;
		
		try {
			readRoomInfoRespDto = manageService.readRoomInfo(roomcode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "방정보 조회 실패", readRoomInfoRespDto));

		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "방정보 조회 성공", readRoomInfoRespDto));
	}
	
	@PostMapping("/content")
	public ResponseEntity<?> addRoomInfo(CreateRoomInfoReqDto createRoomInfoReqDto) {
		System.out.println("요청받");
		System.out.println("파일업로드" + createRoomInfoReqDto.getFile());
		//log.info(">>>> fileName: {}", createRoomInfoReqDto.getFile().get(0).getOriginalFilename());
		log.info("filePath: {}", filePath);
		log.info(">>>fileName: {}", createRoomInfoReqDto);		
		
		int roomCode = 0;;
		
		try {
			log.info("컨트롤러-데이터가 전송되는지 확인 : {}", createRoomInfoReqDto);
			roomCode = manageService.createRoomInfo(createRoomInfoReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "방등록 실패", roomCode));

		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "방등록 성공", roomCode));
	}
	
	@PutMapping("/content/{roomcode}")
	public ResponseEntity<?> updateRoomInfo(@PathVariable int roomcode, @RequestBody UpdateRoomInfoReqDto updateRoomInfoReqDto) {
		boolean status = false;
		updateRoomInfoReqDto.setRoomCode(roomcode);

		try {
			status = manageService.updateRoomInfo(updateRoomInfoReqDto);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "수정 실패", status));
		}	
		return ResponseEntity.ok().body(new CMRespDto<>(1, "수정 성공", status));
	}

	@DeleteMapping("/content/delete/{roomcode}")
	public ResponseEntity<?> deleteRoomInfo(@PathVariable int roomcode) {
		
		boolean status = false;
		
		try {
			status = manageService.deleteRoomInfo(roomcode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1,"삭제 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"삭제 성공", status));
	}
	
}
