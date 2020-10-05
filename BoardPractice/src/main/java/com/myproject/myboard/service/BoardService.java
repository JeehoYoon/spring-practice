package com.myproject.myboard.service;

import java.util.List;

import com.myproject.myboard.domain.BoardVO;

public interface BoardService {
	
	// �� ��� ��ȸ
	List<BoardVO> getBoardList();
	
	// �� �� ��ȸ
	BoardVO getContent(BoardVO vo);
	
	// �� ���
	void insertBoard(BoardVO vo);

	// �� ����
	void updateBoard(BoardVO vo);

	// �� ����
	void deleteBoard(BoardVO vo);
	
}