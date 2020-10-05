package com.myproject.myboard.controller;

import java.io.File;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.myboard.domain.BoardVO;
import com.myproject.myboard.service.BoardService;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//�� ��� �˻�
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		 List<BoardVO> boardList = boardService.getBoardList();
		 
		// Model ���� ����
		model.addAttribute("boardList",boardList);
		return "boardList"; // View �̸� ����
	}

	// �� �� ��ȸ
	@RequestMapping("/getContent.do")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.getContent(vo)); // Model ���� ����
		return "content"; // View �̸� ����
	}
	
	// �� ����
	@RequestMapping("/insertBoard.do") 
	public String insertBoard(BoardVO vo) throws IOException {
		// ���� ���ε� ó��
			String fileName=null;
			MultipartFile uploadFile = vo.getUploadFile();
			if (!uploadFile.isEmpty()) {
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = FilenameUtils.getExtension(originalFileName);	//Ȯ���� ���ϱ�
				UUID uuid = UUID.randomUUID();	//UUID ���ϱ�
				fileName=uuid+"."+ext;
				uploadFile.transferTo(new File("C:\\upload\\" + fileName));
			}
			vo.setFileName(fileName);
			boardService.insertBoard(vo); 
			return "redirect:getBoardList.do"; 
	}
	
	// �� ���� ������ �̵�
	@RequestMapping("/moveInsertBoard.do") 
	public String moveInsertBoard()throws Exception{
		return "insertBoard";
	}
	 

	// �� ����
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
	}

	// �� ����
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
}
