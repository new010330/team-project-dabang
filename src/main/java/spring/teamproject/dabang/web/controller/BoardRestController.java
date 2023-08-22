package spring.teamproject.dabang.web.controller;

import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.domain.board.Board;
import spring.teamproject.dabang.service.BoardService;
import spring.teamproject.dabang.web.dto.CMRespDto;
import spring.teamproject.dabang.web.dto.board.AddBoardReqDto;
import spring.teamproject.dabang.web.dto.board.GetBoardRespDto;
import spring.teamproject.dabang.web.dto.board.UpdateBoardReqDto;

@RestController
@RequestMapping("/api/v1/board")
@Slf4j
@RequiredArgsConstructor
public class BoardRestController {
	
	private final BoardService boardService;
	
	@Value("${file.path}")
	private String filePath;
	
	@PostMapping("/post")
	public ResponseEntity<?> addBoard(AddBoardReqDto addBoardReqDto) {
		log.info(">>>{}:", addBoardReqDto);
		log.info(">>> fileName: {}", addBoardReqDto.getFile().get(0).getOriginalFilename());
		log.info("filePath: {}", filePath);
		
		boolean boardCode = false;
		try {
			boardCode = boardService.addBoard(addBoardReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "Failed", boardCode));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "complete creation", boardCode));
	}
	
	@GetMapping("/get/{usercode}")
	public ResponseEntity<?> getTodoList(@PathVariable int usercode){
		List<Board> list = null;
		try {
			list = boardService.getBoardList(usercode);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "Failed", list));
	}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "complete creation", list));
	}
	
	@GetMapping("/file/download/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
		Path path = Paths.get(filePath + "notice/" + fileName);
		String contentType = Files.probeContentType(path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment")
														.filename(fileName, StandardCharsets.UTF_8)
														.build());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		
		return ResponseEntity.ok().headers(headers).body(resource);
	}
	
	@PutMapping("/put/{usercode}")
	public ResponseEntity<?> setContentTodo(@PathVariable int usercode, @RequestBody UpdateBoardReqDto updateBoardReqDto){
		
		boolean status = false;
		updateBoardReqDto.setUsercode(usercode);
		try {
			status = boardService.UpdateBoard(updateBoardReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"failed",status));
		}
		return ResponseEntity.internalServerError().body(new CMRespDto<>(1,"success",status));
	}
	
	@DeleteMapping("/delete/{usercode}")
	public ResponseEntity<?> setdeleteTodo(@PathVariable int usercode){
		
		boolean status = false;
		
		try {
			status = boardService.deleteBoard(usercode);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1,"failed",status));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success",status));
	}
	

	
	
	
}







