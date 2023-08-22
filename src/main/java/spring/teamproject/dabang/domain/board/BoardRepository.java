package spring.teamproject.dabang.domain.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
	
	public int saveBoard(Board board) throws Exception;
	public int saveBoardFiles(List<BoardFile> list) throws Exception; 
	public List<Board> getBoard(int usercode) throws Exception;
	public int updateBoardByBoardCode(Board board) throws Exception;
	public int remove(int usercode) throws Exception;
}
