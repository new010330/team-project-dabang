package spring.teamproject.dabang.service;

import java.util.List;

import spring.teamproject.dabang.domain.board.Board;
import spring.teamproject.dabang.web.dto.board.AddBoardReqDto;
import spring.teamproject.dabang.web.dto.board.GetBoardListRespDto;
import spring.teamproject.dabang.web.dto.board.GetBoardRespDto;
import spring.teamproject.dabang.web.dto.board.UpdateBoardReqDto;


public interface BoardService {
	public boolean addBoard(AddBoardReqDto addBoardReqDto) throws Exception;
	
	public List<Board> getBoardList(int usercode) throws Exception;
	
	public boolean UpdateBoard(UpdateBoardReqDto updateBoardReqDto) throws Exception;
	public boolean deleteBoard(int usercode) throws Exception;
}
