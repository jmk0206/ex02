package org.zerock.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

@RestController
@RequestMapping("replies")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService rservice;
	
	// 댓글 쓰기
	@PostMapping(value="new", consumes="application/json", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
//		logger.info("ReplyVO: " + vo);
//		int insertCount = rservice.register(vo);
//		logger.info("Reply INSERT COUNT: " + insertCount);
//		
//		return insertCount==1
//		? new ResponseEntity("success", HttpStatus.OK)
//		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		logger.info("aaaaaa"+vo);
		int insertCount = rservice.register(vo);
		return insertCount==1 ? new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 목록 리스트
	@GetMapping(value="page/{bno}", produces= {MediaType.APPLICATION_XML_VALUE,
												MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ArrayList<ReplyVO>> getList(@PathVariable("bno") int bno) {
		return new ResponseEntity<>(rservice.getList(bno),HttpStatus.OK);
	}
	
	// 댓글 수정
	@PutMapping(value="/{rno}", consumes="application/json", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo,
											@PathVariable("rno") int rno){
		logger.info("rno"+rno);
		vo.setRno(rno);
		int modifyCount = rservice.modify(vo);
		return modifyCount==1 ? new ResponseEntity<>("success", HttpStatus.OK)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 삭제
	@DeleteMapping(value="/{rno}", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
		logger.info("rno"+rno);
		int removeCount = rservice.remove(rno);
		return removeCount==1 ? new ResponseEntity<>("success", HttpStatus.OK)
		:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
}
