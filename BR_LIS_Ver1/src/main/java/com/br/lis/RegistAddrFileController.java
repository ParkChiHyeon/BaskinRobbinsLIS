package com.br.lis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.br.lis.model.member.service.FileValidator;
import com.br.lis.model.member.service.ILibMemberService;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.UploadFileVo;



@Controller
public class RegistAddrFileController{
	
	@Autowired
	private FileValidator fileValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(RegistAddrFileController.class);
	
	@RequestMapping(value = "/upload.do",method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request, Model model, UploadFileVo uploadFileVo, BindingResult result, HttpSession session, String changeAuth2) {
		logger.info("FileUploadController 파일 업로드");
		//BindingResult 화면의 <form:form~~>에 객체를 바인딩한 후 에러가 발생하면 해당 객체의 정보를 받는 객체
		fileValidator.validate(uploadFileVo, result);
		
		LibMemberVo  mVo = (LibMemberVo) session.getAttribute("member");
		
		model.addAttribute("member",mVo);
		model.addAttribute("page","changeAuth2");
		
		
		
		
		if (result.hasErrors()) {
			return "myPage";
		}
		
		//파일 받아 옴
		MultipartFile file = uploadFileVo.getFile();
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★"+file);
		
		String fileName = file.getOriginalFilename();
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★"+fileName);
		
		//정보를 담아 줌
//		UUID uid = UUID.randomUUID();// storedName : originName 매핑해서 저장해야 함
		UploadFileVo fileObj = new UploadFileVo();
		fileObj.setFilename(fileName);
		fileObj.setDesc(uploadFileVo.getDesc());
		
		//파일을 서버에 IO로 업로드(절대경로/상대경로)
		InputStream inputStream =null;
		OutputStream outputStream =null;
		
		String path="";
		try {
			//1)파일을 읽는다.
			inputStream = file.getInputStream();
			
			//2)저장 위치를 만든다.
			path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage");//상대경로
//			path = "C:\\eclipse_web_2020_09\\workspace_web\\20220412_Spring_EduBook\\src\\main\\webapp\\storage"; //절대경로
			 
			System.out.println(request.getSession().getServletContext());
			System.out.println("실제 저장될 업로드 경로"+path);
			
			//3) 만약 저장위치가 없다면 생성한다.
			File storage = new File(path);
			if (!storage.exists()) {
				storage.mkdir();
			}
			
			//4)저장할 파일이 없다면 만들어 주고 있다면 오버라이드 함
			File newFile = new File(path+"/"+fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			
			//5)파일을 쓸 위치를 지정해 줌
			outputStream = new FileOutputStream(newFile);
			
			//6)파일을 대상에 읽고 써줌
			int read = 0;
			byte[] b = new byte[(int)file.getSize()];
			while ((read = inputStream.read(b))!= -1) {
				outputStream.write(b,0,read);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("fileObj", fileObj);
		model.addAttribute("path", path);
		
		return "myPage";
	}
}
     
    
