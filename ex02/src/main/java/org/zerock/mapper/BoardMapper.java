package org.zerock.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	public ArrayList<BoardVO> getList();
	public ArrayList<BoardVO> getListWidthPaing(Criteria cri);
	public void insert(BoardVO board);
	public void insertSelectKey(BoardVO board);
	public BoardVO read(int bno);
	public boolean delete(int bno);
	public boolean update(BoardVO board);
	public int getTotalCount(Criteria cri);
	// 댓글 건수 update
	public void updateReplycnt(@Param("bno") int bno, @Param("amount") int amount);
}

