package org.zerock.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	// 구현메서드
	@Autowired
	private ReplyMapper rmapper;
	@Autowired
	private BoardMapper bmapper;

	// 댓글 목록 리스트
	@Override
	public ArrayList<ReplyVO> getList(int bno) {
		logger.info("get Rely List of a Board : " + bno);
		return rmapper.getList(bno);
	}

	// 댓글 쓰기
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		logger.info("service register"+vo);
		bmapper.updateReplycnt(vo.getBno(), 1);
		return rmapper.insert(vo);
	}

	// 댓글 수정
	@Override
	public int modify(ReplyVO vo) {
		logger.info("service modify"+vo);
		return rmapper.update(vo);
	}

	// 댓글 삭제
	@Transactional
	@Override
	public int remove(int rno) {
		logger.info("service remove"+rno);
		// bmapper.updateReplycnt(vo.getBno(), -1);
		return rmapper.delete(rno);
		
	}
	
	
}
