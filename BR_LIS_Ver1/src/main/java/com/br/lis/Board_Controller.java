package com.br.lis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.br.lis.model.board.service.ICalendarBoardService;
import com.br.lis.model.board.service.IFAQBoardService;
import com.br.lis.model.board.service.INoticeBoardService;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.Notice_FAQBoardVo;
import com.google.gson.Gson;



@Controller
@SessionAttributes("pageNum")
public class Board_Controller {
	
	/*
	 * 게시판 흐름제어 클래스
	 */

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICalendarBoardService icalService;
	@Autowired
	private IFAQBoardService ifaqService;
	@Autowired
	private INoticeBoardService inoticeService;
	
	@Autowired
	private IAdminService iadminService;
	
	//공지게시판 새글입력화면
	@RequestMapping(value = "modifynotice.do", method = RequestMethod.GET)
	public String noticeboard(Locale locale, Model model) {
		logger.info("-----------공지게시판 새글입력------");
		
		return "modifynotice";
		
	}
	
	//희망도서 엑셀 업로드
	@RequestMapping(value = "/fileupload.do", method = RequestMethod.POST)
	public void fileupload(HttpServletRequest request, HttpServletResponse response,
							MultipartHttpServletRequest multiFile,
							@RequestParam MultipartFile upload) {
		logger.info("*************Board_Controller Class_fileupload*************");
		//랜덤문자 생성
		
		UUID uid = UUID.randomUUID();
		
		OutputStream out = null;
		OutputStream bout = null;
		PrintWriter printWriter = null;
		
		//인코딩
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		
		try {
		//파일 이름 가져오기
		String fileName = upload.getOriginalFilename();
		System.out.println("파일 이름:"+fileName);
		//파일 내용을 byte[]로 들고옴
		byte[] bytes = upload.getBytes();
		//파일 사이즈 측정(byte로 돌려줌)
		long size = upload.getSize();

		System.out.println("파일 사이즈:"+size);
		//파일 확장자 가져오기
		String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		System.out.println("파일 확장자:"+extension);
		
		//서버의 물리적인 경로 가져오기
			String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage/");
			
			//서버가 꺼졌을 때를 위한 백업경로(절대경로)
			//업로드 되는 날짜를 구해서 백업경로 폴더 자동으로 생성되게끔 처리
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY\\MM\\dd\\");
			String nowFormat = now.format(formatter);
			System.out.println("파일 업로드 날짜:"+nowFormat);
			String back = "C:\\Users\\haeli\\git\\BaskinRobbinsLIS\\BR_LIS_Ver1\\src\\main\\resources\\back\\"+nowFormat;
			System.out.println("저장위치 path:"+path);
			System.out.println("백업위치 back:"+path);
			
			//폴더 공간 만들어주기
			File serverPath = new File(path);
			File backPath = new File(back);
			//폴더(디렉토리)가 없다면 생성
			if (!serverPath.exists()) {
				//만드려는 상위 디렉토리가 있어야만 생성 가능
				serverPath.mkdir();
			}
			
			if (!backPath.exists()) {
				//만드려는 상위 디렉토리가 없으면 상위도 만들어주고 생성
				backPath.mkdirs();
			}
			//덮어쓰기 안되게끔 유효아이디(UUID)로 파일 이름 생성
			String uploadName = path+uid+"_"+fileName;
			String backupName = back+uid+"_"+fileName;
			
			File uploadFile = new File(uploadName);
			File backupFile = new File(backupName);
			
			
			//파일 생성
			bout = new FileOutputStream(uploadFile);
			out = new FileOutputStream(backupFile);
			
			//outputStream에 저장된 데이터를 전송하고 초기화
			bout.write(bytes);
			out.write(bytes);
			bout.flush();
			out.flush();
			//응답 객체를 얻어서 화면에 링크로 다운받을 수 있게끔
			printWriter = response.getWriter();
			//서버에 저장된 내용을 다운로드할 수 있도록 처리
			String fileUrl = "download.do?uid=" + uid +"&fileName=" + fileName;
			
			//업로드시 메시지 출력
			printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		
		}finally {
			
			try {
				if (out!=null) {out.close();}
				if (printWriter !=null) {printWriter.close();}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return;
		
		
	}
	
	@RequestMapping(value = "/download.do")
	@ResponseBody
	public byte[] filedownload(@RequestParam(value = "uid") String uid, 
								@RequestParam(value = "fileName") String fileName,
								HttpServletRequest request, HttpServletResponse response
								) throws IOException {
		byte[] bytes = null;
		logger.info("****************Board_Controller_filedownload*************", uid, fileName);
		
		// 서버에 저장된 이미지 경로
		String path  = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage/");
		String sDirPath = path + uid+ "_"+fileName;
		File file = new File(sDirPath);
		bytes = FileCopyUtils.copyToByteArray(file);
		String outputFilename = new String(file.getName().getBytes(),"8859_1");
		
		/*
		 * Content=disposition : 지정된 파일명을 지정함으로써 더 자세한 파일의 속성을 알려줄 수 있다.
		 * attachment : 브라우저 인식 파일확장자를 포함하여 모든 확장자의 파일들에 대해, 
		 * 				다운로드시 무조건 '파일다운로드' 대화상자가 뜨도록 하는 헤더속성
		 */
		
		response.setHeader("content-Disposition", "attachment: filename=\""+outputFilename+"\"");
		//우리가 보내려고 하는 파일의 크기만큼 셋팅
		response.setContentLength(bytes.length);
		//MIME타입, octet-stream:8비트로 된 파일이라는 의미
		response.setContentType("application/octet-stream");
			
		return bytes;
	
		
	}

	@RequestMapping(value = "modifynotice.do", method = RequestMethod.POST)
	public String modifynotice(@RequestParam Map<String, Object> map, Model model) {
		logger.info("Board_Controller modifynotice 에디터로 입력받음");
		logger.info("map:{}", map);
		int cnt = inoticeService.insertNotice(map);
		
		if (cnt>0) {
			System.out.println("수정 후 이동");
			List<Notice_FAQBoardVo> lists = inoticeService.viewAllNotice();
			model.addAttribute("list"+lists);
			return "noticeboard";
			
		}else {
			return "redirect:/modifynotice.do";
		}
	}
	
	//noticeboard메인화면 data tables
	
	@RequestMapping(value = "/noticeboard.do", method = RequestMethod.GET)
	public String noticeBoardSelect(Model model) {
		logger.info("Board_Controller noticeBoardSelect 리스트보기");
		List<Notice_FAQBoardVo> lists = inoticeService.viewAllNotice();
		String str = "notice";
		model.addAttribute("kind", str);
		model.addAttribute("lists", lists);
		return "noticeboard";
	}

	@RequestMapping(value = "/faqboard.do", method = RequestMethod.GET)
	public String faqBoardSelect(Model model) {
		logger.info("Board_Controller faqBoardSelect");
		List<Notice_FAQBoardVo> lists = ifaqService.viewAllFAQ();
		String str = "faq";
		model.addAttribute("kind", str);
		model.addAttribute("lists", lists);
		return "noticeboard";
	}
	
	
	
	@RequestMapping(value = "/noticeMain.do", method = RequestMethod.GET)
	public String noticeMain(Model model, @SessionAttribute("pageNum") Map<String, Object> map, HttpSession session) {
		logger.info("-----메인화면이동------");
		List<Notice_FAQBoardVo> list = inoticeService.selectPaging(map);
		model.addAttribute("list", list);
		return "noticeMain";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modifyNotice.do", method =  RequestMethod.POST, produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String modifyForm(String seq, @SessionAttribute("member") LibMemberVo mVo) {
		logger.info("Board_Controller modifyForm:{}", seq);
		Notice_FAQBoardVo vo = inoticeService.viewDetailNotice(seq);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(vo.getRegdate(),formatter);
		System.out.println(date.toLocalDate());
		
		JSONObject json = new JSONObject();
		if(mVo.getMember_id().equals(vo.getAdmin_id())) {
			json.put("isc", "true");
			json.put("notice_seq", vo.getNotice_seq());
			json.put("admin_id", vo.getAdmin_id());
			json.put("title", vo.getTitle());
			json.put("content", vo.getContent());
			json.put("file_path", vo.getFile_path());
			json.put("regdate", vo.getRegdate());
		}else {
			json.put("isc", "false");
		}
		return json.toString();
		
				
	}
	
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST, produces = "application/text; chatset=UTF-8;")
	@ResponseBody
	public String modify(String seq, @SessionAttribute("member")AdminVo vo){
		logger.info("Board_Controller modify : {}", seq);
		Map<String, String> rmap = new HashMap<String, String>();
//		int n = inoticeService.modifyNotice(vo);
		
//		String str = rmap.put("isc",(n==1) ? "true":"false");
		
		return "";
		
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, Object> map, @SessionAttribute("admin") AdminVo aVo,
						HttpServletResponse response, HttpSession session) throws IOException {
		map.put("id", aVo.getAdmin_id());
		logger.info("noticeboard_Controller write:{}", map);
		
		int n = inoticeService.insertNotice(map);
		
		return "redirect:/mainJsp.do";
	}



	@RequestMapping(value = "/multiDel.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String multiDel(@RequestParam ArrayList<String> chk, @SessionAttribute("admin") AdminVo aVo,
							HttpServletResponse response) throws IOException {
		logger.info("Board_Controller multiDel:{}", chk);
		int n = 0;
		if (aVo.getAdmin_id().equals(aVo)) {
			n = inoticeService.deleteNotice(chk);
		
		}else {
			return (n>0)?"redirect:/mainJsp.do":"redirect:/logout.do";
		}
		return "redirect:/logout.do";
		
	}
	
	
	
	
}
