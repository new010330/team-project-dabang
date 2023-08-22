package spring.teamproject.dabang.web.controller;

import java.util.List;

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
import spring.teamproject.dabang.domain.account.Account;
import spring.teamproject.dabang.domain.board.Board;
import spring.teamproject.dabang.service.AccountService;
import spring.teamproject.dabang.service.BoardService;
import spring.teamproject.dabang.web.dto.CMRespDto;
import spring.teamproject.dabang.web.dto.board.AddBoardReqDto;
import spring.teamproject.dabang.web.dto.board.GetBoardRespDto;
import spring.teamproject.dabang.web.dto.board.UpdateBoardReqDto;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
@RequiredArgsConstructor
public class AccountRestController {
	
	private final AccountService accountService;
	
//	@Value("${file.path}")
//	private String filePath;
	

	
	@GetMapping("/get/{usercode}")
	public ResponseEntity<?> getTodoList(@PathVariable int usercode){
		List<Account> list = null;
		try {
			list = accountService.getAccountList(usercode);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "Failed", list));
	}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "complete creation", list));
	}

	
	
	

	
	
	
}







