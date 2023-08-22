package spring.teamproject.dabang.service;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.domain.board.Board;
import spring.teamproject.dabang.domain.board.BoardFile;
import spring.teamproject.dabang.domain.board.BoardRepository;
import spring.teamproject.dabang.web.dto.board.AddBoardReqDto;
import spring.teamproject.dabang.web.dto.board.GetBoardListRespDto;
import spring.teamproject.dabang.web.dto.board.GetBoardRespDto;
import spring.teamproject.dabang.web.dto.board.UpdateBoardReqDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	@Value("${file.path}")
	private String filePath;
	
	private final BoardRepository boardRepository;
	
	@Override
	public boolean addBoard(AddBoardReqDto addBoardReqDto) throws Exception {
		
		Predicate<String> predicate = (filename) -> !filename.isBlank();

		Board board = null;
			
		board = Board.builder()
				.notice_title(addBoardReqDto.getNoticeTitle())
				.notice_content(addBoardReqDto.getNoticeContent())
				.user_code(addBoardReqDto.getUserCode())
				.notice_nName(addBoardReqDto.getNoticeNname())
				.build();
		
		log.info(">>>{}:", board);
		boardRepository.saveBoard(board);
		
		if(predicate.test(addBoardReqDto.getFile().get(0).getOriginalFilename())) {
			
			List<BoardFile> boardFiles = new ArrayList<BoardFile>();
			
			for(MultipartFile file : addBoardReqDto.getFile()) {
				String originFilename = file.getOriginalFilename();
				String tempFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originFilename;
				
				Path uploadPath = Paths.get(filePath, "/board/" + tempFilename);
				
				File f = new File(filePath + "/board");
				if(!f.exists()) {
					f.mkdirs();
				}
					
				Files.write(uploadPath, file.getBytes());
				
				boardFiles.add(BoardFile.builder()
										.notice_code(board.getNotice_code())
										.file_name(tempFilename)
										.build());
				
				
				}
				boardRepository.saveBoardFiles(boardFiles);
			};
		return board != null;
	}
	

	@Override
	public List<Board> getBoardList(int usercode) throws Exception {
		// TODO Auto-generated method stub
		
		List<Board> boardlist = boardRepository.getBoard(usercode);
		
		return boardlist;
	}

	@Override
	public boolean UpdateBoard(UpdateBoardReqDto updateBoardReqDto) throws Exception {
		return boardRepository.updateBoardByBoardCode(updateBoardReqDto.toEntity()) > 0;
	}
	@Override
	public boolean deleteBoard(int usercode) throws Exception {
		return boardRepository.remove(usercode) > 0;
	}
}	







