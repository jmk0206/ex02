package org.zerock.service;

import java.util.ArrayList;

import org.zerock.domain.ReplyVO;

public interface ReplyService {
	// 추상메서드
	// 댓글 목록 리스트
	public ArrayList<ReplyVO> getList(int bno);
	// 댓글쓰기
	public int register(ReplyVO vo);
	// 댓글수정
	public int modify(ReplyVO vo);
	// 댓글삭제
	public int remove(int rno);
}
