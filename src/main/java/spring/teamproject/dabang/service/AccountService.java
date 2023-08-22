package spring.teamproject.dabang.service;

import java.util.List;

import spring.teamproject.dabang.domain.account.Account;
import spring.teamproject.dabang.domain.board.Board;
import spring.teamproject.dabang.web.dto.board.AddBoardReqDto;
import spring.teamproject.dabang.web.dto.board.GetBoardListRespDto;
import spring.teamproject.dabang.web.dto.board.GetBoardRespDto;
import spring.teamproject.dabang.web.dto.board.UpdateBoardReqDto;


public interface AccountService {
	
	public List<Account> getAccountList(int usercode) throws Exception;
	

}
