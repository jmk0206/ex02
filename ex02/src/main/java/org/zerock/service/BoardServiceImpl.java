package org.zerock.service;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

//구현
//스프링에서 클래스 역할을 하는 서비스이다.(@Service)
@Service
public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	// @Autowired는 자바의 BoardMapper mapper = new BoardMapper와 같은 개념.
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
	// 게시픈 글쓰기(register) 구현부
	@Transactional
	public void register(BoardVO board) {
		logger.info("register......."+board);
		// tbl_board테이블 insert
		mapper.insertSelectKey(board);
		
		// 사용자가 파일 선택을 하지 않았을 때는 tbl_attach테이블에 insert할 필요가 없으므로
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return; // 밑으로 내려가서 실행하지 않도록 해야함.
		}
		
		board.getAttachList().forEach(attach->{
			// tbl_attach테이블 insert
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});

	}
	
	//  게시판 상세페이지(get) 구현부
	public BoardVO get(int bno) {
		return mapper.read(bno); // mapper에 get이 아닌 read로 되어있다.
	}
	
	// 게시판 글수정(modify) 구현부
	// boolean : true, false
	public boolean modify(BoardVO board) {
		return mapper.update(board);
	}
	
	// 게시판 글삭제(remove) 구현부
	public boolean remove(int bno) {
		return mapper.delete(bno);
	}
	
	// 게시판 글목록 리스트(getList) 구현부
	public ArrayList<BoardVO> getList() {
		return mapper.getList();
	}
	
	// 게시판 글목록 리스트 + 페이징처리(getList) 구현부
	public ArrayList<BoardVO> getListWidthPaing(Criteria cri) {
		return mapper.getListWidthPaing(cri);
	}
	
	// 게시판 글목록 리스트 + 페이징 처리하는데 필요한 전체 데이터
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	
	// 게시판 상세페이지의 파일 업로드 한 이미지에 대한 데이터를 처리
	public ArrayList<BoardAttachVO> getAttachList(int bno) {
		logger.info("getAttachList......."+bno);
		return attachMapper.findByBno(bno);
	}

}

