package com.br.lis;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.br.lis.model.board.service.IFAQBoardService;
import com.br.lis.model.board.service.INoticeBoardService;
import com.br.lis.model.member.service.IAdminService;
import com.br.lis.vo.AdminVo;
import com.br.lis.vo.LibMemberVo;
import com.br.lis.vo.Notice_FAQBoardVo;


@Controller
@SessionAttributes("pageNum")
public class Board_Controller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IFAQBoardService ifaqService;
	@Autowired
	private INoticeBoardService inoticeService;
	@Autowired
	private IAdminService iadminService;
	
//	@Autowired
//	private 
	
	@RequestMapping(value = "main.Jsp.do", method = RequestMethod.GET)
	public String main() {
		logger.info("---------메인화면이동--------");
		
		return "main";
		
	}
	
	@RequestMapping(value = "/mainView.do", method = RequestMethod.GET)
	public String mainView(Model model, @SessionAttribute("pageNum") Map<String, Object> map, HttpSession session) {
		logger.info("-----메인화면이동------");
		List<Notice_FAQBoardVo> list = inoticeService.selectPaging(map);
		model.addAttribute("list", list);
		return "main";
	}
	
	@RequestMapping(value = "/vieDoti.do", method = RequestMethod.GET)
	@ResponseBody
	public String bPage(Model model, @RequestParam("start") int start, @RequestParam("end") int end, 
						@RequestParam("pageIndex") int pageIndex, @RequestParam("kind") int kind) {
		logger.info("---------아작스테스트----------------");
		
		int cnt = 0;
		if (kind ==1) {
				cnt = inoticeService.getCountBoardList();
			
		}else {
//			cnt = ILibMemberService.memberTotal();
			
		}
		 
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (cnt%10!=0) {
			cnt/=10;
			cnt+=1;
			
		}else {
			cnt/=10;
			
		}
		
		System.out.println("-------cnt---------:" +cnt);
		
		map.put("start", start);
		map.put("end", end);
		map.put("btnCount", cnt);
		map.put("index", start/10+1);
		map.put("pageIndex", pageIndex);
		
		model.addAttribute("pageNum", map);
		System.out.println("map");
		
		
		
		
		
		
		return "success";
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modifyForm.do", method =  RequestMethod.POST, produces = "application/text; charset=UTF-8;")
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
		logger.info("NoticeBoard_Controller write:{}", map);
		
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
