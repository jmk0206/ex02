package org.zerock.controller;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	//@Autowired(BoardService service = new BoardService();
	private BoardService service;
	
	// register(글쓰기)
	// 용도 : 글쓰기 화면용
	@GetMapping("register")
	public void register() {
		logger.info("register...");
	}
	
	// 용도 : 글쓰기 화면에서 글쓰기 버튼을 클릭
	@PostMapping("register")
	public String registerPost(BoardVO board) {
		logger.info("registerPost..."+board);
		// 사용자가 파일선택을 클릭해서 파일업로드를 하나라도 했으면, 
		if(board.getAttachList()!=null) {
			// 그 파일에 대한 정보를 for문을 이용해서 가져와라.
			board.getAttachList().forEach(attach->logger.info("attach 값은 : "+attach));
		}
		service.register(board);
		return "redirect:/board/list"; // list.jsp로 이동
	}

	// list(글목록)
	@GetMapping("list")
	public void list(Model model, Criteria cri) {
		logger.info("list...");
		int count = service.getTotalCount(cri);
//		model.addAttribute("list", service.getList());
		model.addAttribute("list", service.getListWidthPaing(cri));
		model.addAttribute("pageMaker", new PageDTO(cri,count));
	} // 포워딩 방식이기에 model을 사용한다.
	
	// get(상세페이지)
	// Model : Controller에 있는 데이터 값을 .jsp로 보낸다.
	@GetMapping("get")
	public void get(@RequestParam("bno") int bno, Model model) {
		logger.info("get....");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("modify")
	// Board는 기본형 타입 / board는 참조형 타입
	public String modify(BoardVO board, RedirectAttributes rttr) {
		logger.info("modify....");
		if(service.modify(board)) {
			//service.modify(board); // BoardService.java에서 boolean이기에 true 또는 false로 받아온다.
			rttr.addAttribute("result", "success");
			rttr.addAttribute("bno", board.getBno());
		}
		return "redirect:/board/get";
	}
	
	@PostMapping("remove")
	public String remove(@RequestParam("bno") int bno) {
		logger.info("remove....");
		service.remove(bno);
		return "redirect:/board/list";
	}
	
	@GetMapping(value="getAttachList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<ArrayList<BoardAttachVO>> getAttachList(int bno) {
		logger.info("getAttachList = "+bno);
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}

}

