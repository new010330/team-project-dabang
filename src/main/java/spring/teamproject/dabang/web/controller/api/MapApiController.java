package spring.teamproject.dabang.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.teamproject.dabang.domain.manage.RoomData;
import spring.teamproject.dabang.service.manage.ManageService;
import spring.teamproject.dabang.service.manage.RoomDataService;
import spring.teamproject.dabang.web.dto.CMRespDto;
import spring.teamproject.dabang.web.dto.map.ReadSimpleDataRespDto;

@RestController
@RequestMapping("/api/v1/map")
@RequiredArgsConstructor
public class MapApiController {
	
	private final RoomDataService roomDataService;
	
	@GetMapping("/address")
	public ResponseEntity<?> getAddress() {
		List<String> list = null;
	
		try {
			list = roomDataService.readAddressList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "주소 리스트 불러오기 성공", list));
	}
	
	@GetMapping("/room/{address}")
	public ResponseEntity<?> getSimpleData(@PathVariable String address) {
		List<RoomData> list = null;
		
		try {
			list = roomDataService.readRoomInfoRespDto(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "주소 리스트 불러오기 성공", list));
	}

}
