package org.zerock.mapper;

import java.util.ArrayList;

import org.zerock.domain.BoardAttachVO;

public interface BoardAttachMapper {
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public ArrayList<BoardAttachVO> findByBno(int bno);
}
