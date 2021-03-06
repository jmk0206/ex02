package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.BoardAttachVO;

import net.coobird.thumbnailator.Thumbnailator;


@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String filename){
		logger.info("fileName : "+filename);
		
		File file = new File("d:\\upload\\"+filename);
		
		logger.info("file : "+file);
		
		ResponseEntity<byte[]>result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@GetMapping("uploadForm")
	public void uploadForm() {
		logger.info("uploadForm");
	}
	
	@PostMapping("uploadFormAction")
	                                 // int[]      // a
	public void uploadFormAction(MultipartFile[] uploadFile) {
		String uploadFolder = "d:\\upload";
		for(MultipartFile multipartFile : uploadFile) {
			logger.info("Upload File Name : " + multipartFile.getOriginalFilename());
			logger.info("Upload File Size : " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				logger.info(e.getMessage());
			}
		} // end for
	}
	
	@GetMapping("uploadAjax")
	public void uploadAjax() {
		logger.info("uploadAjax");
	}
	
	// getFolder ????????? ??????(page 508)
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(); // import java.util.Date; import ?????? ?????? : 20210819
		System.out.println("date: " + date);
		// Thu Aug 19 16:08:39 KST 2021 -> 2021-08-19
		String str = sdf.format(date);
		System.out.println("str: " + str);
		// 2021-08-19 -> 2021\08\19
		return str.replace("-", File.separator); // replace ????????? ????????? ?????? ????????? ?????????.
	}
	
	//checkImageType ????????? ??????(page 513)
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			// ??????????????? ??? ????????? ????????????????????? ??????
			return contentType.startsWith("image");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@PostMapping(value="uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<ArrayList<BoardAttachVO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		logger.info("uploadAjaxAction");
		
		ArrayList<BoardAttachVO> list = new ArrayList<>();
		// ??????????????? ??? ??????
		String uploadFolder = "d:\\upload";
		
		String uploadFolderPath = getFolder(); // 2021\08\19
		// d:\\upload\\2021\\08\\19
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		// d:\\upload\\2021\\08\\19 ????????? ?????????(false)
		if(uploadPath.exists() == false) {
			// d:\\upload\\2021\\08\\19 ?????? ??????(midirs())??????!
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			logger.info("Upload File Name : " + multipartFile.getOriginalFilename());
			logger.info("Upload File Size : " + multipartFile.getSize());
			
			BoardAttachVO attachDTO = new BoardAttachVO();
			// Internet Explorer??? ????????? ?????????????????? ?????????(???uploadFileName=000.png)
			String uploadFileName = multipartFile.getOriginalFilename();
			// Internet Explorer?????????????????? ?????????(???uploadFileName=d:\\users\\GreenArt\\Pictures\\000.png)
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			// System.out.println("uploadFileName: " + uploadFileName);
			attachDTO.setFilename(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			System.out.println("uuid.toString()" + uuid.toString());
			// 4b86258a-2f5a-4a3a-8782-187a0f93b6f2.png
			uploadFileName = uuid.toString() + "_" + uploadFileName;
					
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadpath(uploadFolderPath);
				
				// checkimage type file
				if(checkImageType(saveFile)) { // ???????????? ????????? ????????? ????????????,
					attachDTO.setFiletype(true);
					// FileOutputStream : ????????? ?????? ????????? ????????????. ???????????? ????????? ?????? ???????????????.
																			// d:\\uplod\\2021\\08\\19\\d: s_e4285f25-d6c1-43e6-9191-b0d229ce6fb7
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
					// ???????????? ?????????. ????????? 100x100 - ???????????? ???????????? ???????????? ????????? ????????? 100x100??? ?????? ???????????? ?????? ?????? ?????????????????????.
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				
				list.add(attachDTO);
				
			} catch(Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
