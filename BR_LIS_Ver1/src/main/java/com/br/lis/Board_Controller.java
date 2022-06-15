package com.br.lis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.br.lis.model.board.service.ICalendarBoardService;
import com.br.lis.model.board.service.IFAQBoardService;
import com.br.lis.model.board.service.INoticeBoardService;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.vo.CalendarBoardVo;
import com.br.lis.vo.Notice_FAQBoardVo;


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
	
	//noticeboard & faq & calendar전체 조회 화면 이동
	@RequestMapping(value = "/viewAllBoard.do", method = RequestMethod.GET)
	public String BoardSelect(Model model,String kind) {
		logger.info("Board_Controller BoardSelect 리스트보기");
//		List<Notice_FAQBoardVo> lists = inoticeService.viewAllNotice();
//		model.addAttribute("lists", lists);
		model.addAttribute("kind", kind);
		model.addAttribute("session", "admin");
		if(kind.equals("faq")||kind.equals("notice")) {
			return "noticeboard";
		}
		else if(kind.equals("calendar")){
			return "calendarboard";
		}
		else {
			return "home";
		}
	}
	
	
	//글입력화면 이동
	@RequestMapping(value = "/editor.do",method = RequestMethod.GET)
	public String ckEditorView(String kind,Model model) {
		logger.info("eidotr view 이동");
		model.addAttribute("kind", kind);
		return "insertBoard";
	}
	
	//공지글 수정화면 이동
	@RequestMapping(value = "/modifynotice.do",method = RequestMethod.GET)
	public String modifyNotice(String kind,Model model,String seq) {
		logger.info("modifynotice이동");
		
		model.addAttribute("kind", kind);
		Notice_FAQBoardVo vo = inoticeService.viewDetailNotice(seq);
		model.addAttribute("dto", vo);
				
		return "modifynotice";
	}
	
	
	//공지게시판 새글입력
	@RequestMapping(value = "/insertNotice.do", method = RequestMethod.POST)
	public String insertNoticeBoard(Model model, @RequestParam Map<String, Object> map) {
		logger.info("Board_Controller insertNoticeBoard : {}",map);
		System.out.println(map);
		model.addAttribute("kind", "notice");
		int n = inoticeService.insertNotice(map);
		
		if(n>0) {
			return "redirect:/viewAllBoard.do";
		}else {
			StringBuffer sb= new StringBuffer();
			sb.append("<script>");
			sb.append("alert('입력실패 관리자에게 문의하세요');");
			sb.append("location.href='./home.do'");
			sb.append("</script>");
			return sb.toString();
		}
	}
	
//		//공지게시판 수정
//		@RequestMapping(value = "/modifynotice.do", method = {RequestMethod.GET, RequestMethod.POST})
//		public String modifynotice(@RequestParam Map<String, Object> map, Model model) {
//			logger.info("Board_Controller modifynotice 에디터로 입력받음");
//			logger.info("map:{}", map);
//			model.addAttribute("kind", "notice");
//			int cnt = inoticeService.insertNotice(map);
//			
//			if (cnt>0) {
//				System.out.println("수정 후 이동");
//				List<Notice_FAQBoardVo> lists = inoticeService.viewAllNotice();
//				model.addAttribute("list"+lists);
//				return "noticeboard";
//				
//			}else {
//				return "redirect:/viewAllBoard.do";
//			}
//		}
//	
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
	//noticeboard 상세보기
	@RequestMapping(value = "/detailnotice.do", method = RequestMethod.GET)
	public String viewDetailNotice(Model model,String seq) {
		logger.info("Board_Controller_viewDetailNotice 공지사항 상세보기");
		Notice_FAQBoardVo vo = inoticeService.viewDetailNotice(seq);
		model.addAttribute("dto", vo);
		model.addAttribute("kind", "notice");
		return "detailboard";
	}
	//notice 다중삭제
		@RequestMapping(value = "/multiDelNotice.do", method = {RequestMethod.GET, RequestMethod.POST})
		public String multiDelNotice(@RequestParam List<String> chkBox) {
			logger.info("Board_Controller multiDelNotice:{}", chkBox);
//			if (aVo.getAdmin_id().equals(aVo)) {
				int n = inoticeService.multiDelNotice(chkBox);
//			}else { //세션 붙일경우 고쳐야함
//				return (n>0)?"redirect:/viewAllBoard.do":"redirect:/viewAllBoard.do";
//			}
			return "redirect:/viewAllBoard.do?kind=notice";
		}
//--------------------------------------------FAQ-----------------------------------
	
	//FAQ상세보기
	@RequestMapping(value = "/detailfaq.do", method = RequestMethod.GET)
	public String viewDetailFAQ(Model model,String seq) {
		logger.info("Board_Controller_viewDetailFAQ FAQ 상세보기");
		Notice_FAQBoardVo vo = ifaqService.viewDetailFAQ(seq);
		model.addAttribute("vo", vo);
		model.addAttribute("kind", "faq");
		return "detailboard";
	}
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/modifyNotice.do", method =  RequestMethod.POST, produces = "application/text; charset=UTF-8;")
//	@ResponseBody
//	public String modifyForm(String seq, @SessionAttribute("member") LibMemberVo mVo) {
//		logger.info("Board_Controller modifyForm:{}", seq);
//		int vo = inoticeService.modifyNotice(seq);
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime date = LocalDateTime.parse(vo.getRegdate(),formatter);
//		System.out.println(date.toLocalDate());
//		
//		JSONObject json = new JSONObject();
//		if(mVo.getMember_id().equals(vo.getAdmin_id())) {
//			json.put("isc", "true");
//			json.put("notice_seq", vo.getNotice_seq());
//			json.put("admin_id", vo.getAdmin_id());
//			json.put("title", vo.getTitle());
//			json.put("content", vo.getContent());
//			json.put("file_path", vo.getFile_path());
//			json.put("regdate", vo.getRegdate());
//		}else {
//			json.put("isc", "false");
//		}
//		return json.toString();
//	}
	
	//FAQ게시판 새글 입력화면
	@RequestMapping(value = "/insertFAQ.do", method = RequestMethod.POST)
	public String insertFAQ(Model model, @RequestParam Map<String, Object> map) {
		logger.info("Board_Controller insertNoticeBoard : {}",map);
		System.out.println(map);
		model.addAttribute("kind", "faq");
		int n = ifaqService.insertFAQ(map);
		
		if(n>0) {
			return "redirect:/viewAllBoard.do";
		}else {
			StringBuffer sb= new StringBuffer();
			sb.append("<script>");
			sb.append("alert('입력실패 관리자에게 문의하세요');");
			sb.append("location.href='./home.do'");
			sb.append("</script>");
			return sb.toString();
		}
	}
	
//	http://localhost:8089/BR_LIS_Ver1/modifynotice.do?kind=faq
	
//	//FAQ게시판 수정
//	@RequestMapping(value = "modifynotice.do", method = RequestMethod.POST)
//	public String modifyFAQ(@RequestParam Map<String, Object> map, Model model) {
//		logger.info("Board_Controller modifyCalendar 에디터로 입력받음");
//		logger.info("map:{}", map);
//		model.addAttribute("kind", "faq");
//		Notice_FAQBoardVo vo = new Notice_FAQBoardVo();
//		vo.setContent((String)map.get("content"));
//		vo.setTitle((String)map.get("title"));
//		vo.setFaq_seq((String)map.get("faq_seq"));
//		int cnt = ifaqService.modifyFAQ(vo);
//		
//		if (cnt>0) {
//			System.out.println("수정 후 이동");
//			List<CalendarBoardVo> lists = icalService.viewAllCalendar();
//			model.addAttribute("list"+lists);
//			return "calendarboard";
//			
//		}else {
//			return "calendarboard";
//		}
//	}
//	

	
	//FAQ글 수정화면 이동
	@RequestMapping(value = "/modifyFAQ.do",method = RequestMethod.GET)
	public String modifyFAQ(String kind,Model model,String seq) {
		logger.info("modifyFAQ 이동");
		
		model.addAttribute("kind", kind);
		Notice_FAQBoardVo fVo = ifaqService.viewDetailFAQ(seq);
		model.addAttribute("fVo", fVo);
		
		return "modifynotice";
	}
	
	
	//FAQ 다중삭제
	@RequestMapping(value = "/multiDelFAQ.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String multiDelFAQ(@RequestParam List<String> chkBox) {
		logger.info("Board_Controller multiDel:{}", chkBox);
//		if (aVo.getAdmin_id().equals(aVo)) {
			int n = ifaqService.deleteFAQ(chkBox);
//		}else { //세션 붙일경우 고쳐야함
//			return (n>0)?"redirect:/viewAllBoard.do":"redirect:/viewAllBoard.do";
//		}
		return "redirect:/viewAllBoard.do?kind=faq";
	}
//-------------------------------------Calendar-------------------------------------
	
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/calendarAjax.do", method = RequestMethod.GET)
		@ResponseBody
		public JSONArray date(Model model) {
				List<CalendarBoardVo> lists = icalService.viewAllCalendar();
				System.out.println("datedatedatedatedate" + lists.toString());
				JSONArray arr = new JSONArray();
				for (CalendarBoardVo vo : lists) {
					JSONObject obj = new JSONObject();
					obj.put("id", vo.getAdmin_id());
					obj.put("seq", vo.getCalendar_seq());
					obj.put("title", vo.getTitle());
					obj.put("content", vo.getContent());
					obj.put("start", vo.getStart_date());
					obj.put("end", vo.getStart_date());
					arr.add(obj);
				}
				logger.info("JSONArray 파싱한 값 : {}", arr);
	
				// return 형태
				// [{},{},{}....]
				return arr;
		}
	
			//일정게시판 상세보기
			@RequestMapping(value = "/detailcalendar.do", method = RequestMethod.GET)
			public String viewDetailCalendar(Model model,String seq) {
				logger.info("Board_Controller_viewDetailCalendar 캘린더 상세보기");
				CalendarBoardVo vo = icalService.viewDetailCalendar(seq);
				model.addAttribute("vo", vo);
				model.addAttribute("kind", "calendar");
				return "detailboard";
			}
	
			//일정게시판 새글입력
			@RequestMapping(value = "/insertCalendar.do", method = RequestMethod.POST)
			public String insertCalendarBoard(Model model, @RequestParam Map<String, Object> map) {
				logger.info("Board_Controller  insertCalendarBoard : {}",map);
				System.out.println(map);
				model.addAttribute("kind", "calendar");
				int n = icalService.insertCalendar(map);
				
				if(n>0) {
					return "redirect:/viewAllBoard.do";
				}else {
					StringBuffer sb= new StringBuffer();
					sb.append("<script>");
					sb.append("alert('입력실패 관리자에게 문의하세요');");
					sb.append("location.href='./home.do'");
					sb.append("</script>");
					return sb.toString();
				}
			}
		
			

			//Calendar글 수정화면 이동
				@RequestMapping(value = "/modifyCalendar.do",method = RequestMethod.GET)
				public String modifyCalendar(String kind,Model model,String seq) {
					logger.info("modifyFAQ 이동");

					model.addAttribute("kind", kind);
					CalendarBoardVo cVo = icalService.viewDetailCalendar(seq);
					model.addAttribute("cVo", cVo);
					
					return "modifynotice";
				}
			
//			//일정게시판 수정
//			@RequestMapping(value = "/modifynotice.do", method = {RequestMethod.GET, RequestMethod.POST})
//			public String modifyCalendar(@RequestParam Map<String, Object> map, Model model) {
//				logger.info("Board_Controller modifyCalendar 에디터로 입력받음");
//				logger.info("map:{}", map);
//				model.addAttribute("kind", "calendar");
//				int cnt = icalService.modifyCalendar(map);
//				
//				if (cnt>0) {
//					System.out.println("수정 후 이동");
//					List<CalendarBoardVo> lists = icalService.viewAllCalendar();
//					model.addAttribute("list"+lists);
//					return "calendarboard";
//					
//				}else {
//					return "calendarboard";
//				}
//			}
			
			//일정게시판 삭제
			@RequestMapping(value = "/multiDelCalendar.do", method = {RequestMethod.GET, RequestMethod.POST})
			public String multiDelCalendar(@RequestParam List<String> chkBox) {
				logger.info("Board_Controller multiDelCalendar:{}", chkBox);
//				if (aVo.getAdmin_id().equals(aVo)) {
					int n = icalService.multiDelCalendar(chkBox);
//				}else { //세션 붙일경우 고쳐야함
//					return (n>0)?"redirect:/viewAllBoard.do":"redirect:/viewAllBoard.do";
//				}
				return "calendarboard";
			}
}
