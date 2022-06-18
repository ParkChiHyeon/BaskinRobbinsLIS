package com.br.lis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.br.lis.model.board.service.INoticeBoardService;
import com.br.lis.util.ElasticSearchModule;

//import edu.emory.mathcs.backport.java.util.Arrays;

@Controller
@SessionAttributes("admin")
public class File_Controller {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private INoticeBoardService service;

	@RequestMapping(value = "/fileupload.do", method = RequestMethod.POST)
	public String fileupload(Model model, HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multiFile, @RequestParam MultipartFile file,
			@RequestParam Map<String, Object> map) throws IOException {
		logger.info("Welcome! HomeController fileUpload----------folder address: {} {} {}", map.get("directory_name"), file, multiFile);
		// 랜덤문자 생성
		if (file.getSize() != 0) {
			UUID uid = UUID.randomUUID();

			OutputStream out = null;
//			OutputStream bout = null;
			// PrintWriter printWriter = null;

			// 인코딩
			response.setContentType("text/html; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			try {
				// 파일 이름 가져오기
				String fileName = file.getOriginalFilename();
				System.out.println("파일 이름:" + fileName);
				// 파일 내용을 byte[] 으로 들고옴
				byte[] bytes = file.getBytes();
				// 파일 사이즈 측정(byte로 돌려줌 )
				long size = file.getSize();
				// long kilobyte = size / 1024;
				// long megabyte = size / 1024;

				System.out.println("파일 사이즈:" + size);
				// 파일 확장자 가져오기
				String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				System.out.println("파일 확장자:" + extension);

				// 서버의 물리적인 경로 가져오기
				// 배포시 파일이 다 삭제됨.. 절대경로에 잡아야함
//				String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage/notice/"+directory_name+"/");

				// 서버가 꺼졌을때를 위한 백업경로(절대경로)
				// 업로드 되는 날짜를 구해서 백업경로 폴더 자동으로 생성되게끔 처리

				String absolutePath = "C:\\BR_storage\\notice\\" + map.get("directory_name") + "\\";
//				String absolutePath = "/usr/local/BR_storage/notice/" + directory_name + "/";

//				System.out.println("저장위치 path:"+path);
//				System.out.println("백업위치 back:"+absolutePathLinux);

				// 폴더 공간 만들어주기
//		         File serverPath = new File(path);
				File backPath = new File(absolutePath);
				// 폴더(디렉토리)가 없다면 생성
//		         if(!serverPath.exists()) {
//		            //만드려는 상위디렉토리가 있어야만 생성가능
//		            serverPath.mkdirs();  
//		         }
				if (!backPath.exists()) {
					// 만드려는 상위디렉토리가 없으면 상위도 만들어주고 생성
					backPath.mkdirs();
				}
				// 덮어쓰기 안되게끔 유효아이디(UUID)로 파일이름 생성
//				String uploalName = path+uid+"_"+fileName;
				String backupName = absolutePath + uid + "_" + fileName;

//				File uploadFile = new File(uploalName);
				File backupFile = new File(backupName);

				// 파일 생성
//				bout = new FileOutputStream(uploadFile);
				out = new FileOutputStream(backupFile);

				System.out.println("----------------------------------------------------------------");
				// outputStram에 저장된 데이터를 전송하고 초기화
//				bout.write(bytes);
				out.write(bytes);
//		    	bout.flush();
				out.flush();

				// 응답객체를 얻어서 화면에 링크로 다운받을 수 있게끔
				// printWriter = response.getWriter();
				// 서버에 저장된 내용을 다운로드할 수 있도록 처리
				String fileUrl = "fileName=" + fileName + "&uid=" + uid + "&directory_name=" + map.get("directory_name");

				// 업로드시 메시지 출력
				// printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1,
				// \"url\":\""+fileUrl+"\"}");
				// printWriter.flush();

				map.put("file_path", fileUrl);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			logger.info("------------------------------------------------------ map : {}", map);
			
			if (map.get("notice_seq")==null) {//insert
				int insertCtn = service.insertNotice(map);
				map.remove("directory_name");
				if (insertCtn > 0) {
					ElasticSearchModule elasticInsert = new ElasticSearchModule();
					elasticInsert.insertElasticMap(map,"notice_board", map.get("notice_seq").toString());
					model.addAttribute("kind", "notice");
					return "redirect:/viewAllBoard.do";
				} else {
					StringBuffer sb = new StringBuffer();
					sb.append("<script>");
					sb.append("alert('입력실패 관리자에게 문의하세요');");
					sb.append("location.href='./home.do'");
					sb.append("</script>");
					return sb.toString();
				}
			}else {//update
				imgDelete(map.get("content").toString(), map.get("directory_name").toString());
				int updateCtn = service.modifyNotice(map);
				map.remove("directory_name");
				if (updateCtn > 0) {
					ElasticSearchModule elasticInsert = new ElasticSearchModule();
					elasticInsert.updateElasticMap(map,"notice_board", map.get("notice_seq").toString());
					model.addAttribute("kind", "notice");
					return "redirect:/viewAllBoard.do";
				} else {
					StringBuffer sb = new StringBuffer();
					sb.append("<script>");
					sb.append("alert('입력실패 관리자에게 문의하세요');");
					sb.append("location.href='./home.do'");
					sb.append("</script>");
					return sb.toString();
				}
			}
			// map admin_id
		} else {//첨부파일 없을 경우
			logger.info("------------------------------------------------------ map : {}", map);
			if (map.get("notice_seq")==null) {//insert
				map.put("file_path", "");
				int insertCtn = service.insertNotice(map);
				
				map.remove("directory_name");
				if (insertCtn > 0) {
					ElasticSearchModule elasticInsert = new ElasticSearchModule();
					elasticInsert.insertElasticMap(map,"notice_board", map.get("notice_seq").toString());
					model.addAttribute("kind", "notice");
					return "redirect:/viewAllBoard.do";
				} else {
					StringBuffer sb = new StringBuffer();
					sb.append("<script>");
					sb.append("alert('입력실패 관리자에게 문의하세요');");
					sb.append("location.href='./home.do'");
					sb.append("</script>");
					return sb.toString();
				}
			}else {//update
				imgDelete(map.get("content").toString(), map.get("directory_name").toString());
//				map.put("file_path", "");
				int updateCtn = service.modifyNotice(map);
				map.remove("directory_name");
				if (updateCtn > 0) {
					ElasticSearchModule elasticInsert = new ElasticSearchModule();
					elasticInsert.updateElasticMap(map,"notice_board", map.get("notice_seq").toString());
					model.addAttribute("kind", "notice");
					return "redirect:/viewAllBoard.do";
				} else {
					StringBuffer sb = new StringBuffer();
					sb.append("<script>");
					sb.append("alert('입력실패 관리자에게 문의하세요');");
					sb.append("location.href='./home.do'");
					sb.append("</script>");
					return sb.toString();
				}
			}
		}
	}

	// 이미지 업로드
	@RequestMapping(value = "/fileuploadImg.do", method = RequestMethod.POST)
	public void fileuploadImg(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload, @RequestParam String directory_name) {
		logger.info("Welcome! HomeController imageUpload");
		System.out.println("-------------------------------directory_name" + directory_name);

		// 랜덤문자 생성
		UUID uid = UUID.randomUUID();

		OutputStream out = null;
//	      OutputStream bout = null;
		PrintWriter printWriter = null;

		// 인코딩
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");

		try {
			// 파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			System.out.println("파일 이름:" + fileName);
			// 파일 내용을 byte[] 으로 들고옴
			byte[] bytes = upload.getBytes();
			// 파일 사이즈 측정(byte로 돌려줌 )
			long size = upload.getSize();
			// long kilobyte = size / 1024;
			// long megabyte = size / 1024;

			System.out.println("파일 사이즈:" + size);
			// 파일 확장자 가져오기
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			System.out.println("파일 확장자:" + extension);

			// 서버의 물리적인 경로 가져오기
//	         String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage/notice/"+directory_name+"/");

			// 서버가 꺼졌을때를 위한 백업경로(절대경로)
			// 업로드 되는 날짜를 구해서 백업경로 폴더 자동으로 생성되게끔 처리
			String absolutePath = "C:\\BR_storage\\notice\\" + directory_name + "\\img\\";
//			String absolutePath = "/usr/local/BR_storage/notice/" + directory_name + "/img/";

//	         System.out.println("저장위치 path:"+path);
//	         System.out.println("백업위치 back:"+absolutePathLinux);

			// 폴더 공간 만들어주기
//	         File serverPath = new File(path);
			File backPath = new File(absolutePath);
			// 폴더(디렉토리)가 없다면 생성
//	         if(!serverPath.exists()) {
//	            //만드려는 상위디렉토리가 있어야만 생성가능
//	            serverPath.mkdirs();  
//	         }
			if (!backPath.exists()) {
				// 만드려는 상위디렉토리가 없으면 상위도 만들어주고 생성
				backPath.mkdirs();
			}
			// 덮어쓰기 안되게끔 유효아이디(UUID)로 파일이름 생성
//	         String uploalName = path+uid+"_"+fileName;
			String backupName = absolutePath + uid + "_" + fileName;

//	         File uploadFile = new File(uploalName);
			File backupFile = new File(backupName);

			// 파일 생성
//	         bout = new FileOutputStream(uploadFile);
			out = new FileOutputStream(backupFile);

			// outputStram에 저장된 데이터를 전송하고 초기화
//	         bout.write(bytes);
			out.write(bytes);
//	          bout.flush();
			out.flush();

			// 응답객체를 얻어서 화면에 링크로 다운받을 수 있게끔
			printWriter = response.getWriter();
			// 서버에 저장된 내용을 다운로드할 수 있도록 처리
			String fileUrl = "download.do?uid=" + uid + "&fileName=" + fileName + "&directory_name=" + directory_name;

			// 업로드시 메시지 출력
			printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@RequestMapping(value = "/download.do")
	@ResponseBody
	public byte[] imgPreview(@RequestParam(value = "uid") String uid,
			@RequestParam(value = "fileName") String fileName, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "directory_name") String directory_name) throws IOException {
		byte[] bytes = null;
		logger.info("Welcome! HomeController download : {} {} {}", uid, fileName, directory_name);

		// 서버에 저장된 이미지 경로
		// String path=WebUtils.getRealPath(request.getSession().getServletContext(),
		// "/storage/notice/"+directory_name+"/") ;
		// 절대경로를 통해 다운로드
		String absolutePath = "C:\\BR_storage\\notice\\" + directory_name + "\\img\\";
//		String absolutePath = "/usr/local/BR_storage/notice/" + directory_name + "/img/";

		String sDirPath = absolutePath + uid + "_" + fileName;

		File file = new File(sDirPath);

		bytes = FileCopyUtils.copyToByteArray(file);

		String outputFilename = new String(file.getName().getBytes(), "8859_1");

		/*
		 * Content-disposition : 지정된 파일명을 지정함으로써 더 자세한 파일의 속성을 알려줄 수 있다. attachment :
		 * 브라우저 인식 파일확장자를 포함하여 모든 확장자의 파일들에 대해, 다운로드 시 무조건 '파일다운로드' 대화상자가 뜨도록 하는 해더속성
		 */
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFilename + "\"");
		// 우리가 보내려고 하는 파일의 크기만큼 셋팅
		response.setContentLength(bytes.length);
		// MIME타입, octet-stream : 8비트로 된 파일이라는 의미
		response.setContentType("application/octet-stream");

		return bytes;
	}
	@RequestMapping(value = "/downloadFile.do")
	@ResponseBody
	public byte[] filedownload(@RequestParam(value = "uid") String uid,
			@RequestParam(value = "fileName") String fileName, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "directory_name") String directory_name) throws IOException {
		byte[] bytes = null;
		logger.info("Welcome! HomeController download : {} {} {}", uid, fileName, directory_name);
		
		// 서버에 저장된 이미지 경로
		// String path=WebUtils.getRealPath(request.getSession().getServletContext(),
		// "/storage/notice/"+directory_name+"/") ;
		// 절대경로를 통해 다운로드
		String absolutePath = "C:\\BR_storage\\notice\\" + directory_name + "\\";
//		String absolutePath = "/usr/local/BR_storage/notice/" + directory_name + "/";
		
		String sDirPath = absolutePath + uid + "_" + fileName;
		
		File file = new File(sDirPath);
		
		bytes = FileCopyUtils.copyToByteArray(file);
		
		String outputFilename = new String(file.getName().getBytes(), "8859_1");
		
		/*
		 * Content-disposition : 지정된 파일명을 지정함으로써 더 자세한 파일의 속성을 알려줄 수 있다. attachment :
		 * 브라우저 인식 파일확장자를 포함하여 모든 확장자의 파일들에 대해, 다운로드 시 무조건 '파일다운로드' 대화상자가 뜨도록 하는 해더속성
		 */
		response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFilename + "\"");
		// 우리가 보내려고 하는 파일의 크기만큼 셋팅
		response.setContentLength(bytes.length);
		// MIME타입, octet-stream : 8비트로 된 파일이라는 의미
		response.setContentType("application/octet-stream");
		
		return bytes;
	}

	@RequestMapping(value = "/fileDelete.do",method = RequestMethod.POST)
	@ResponseBody
	public void fileDelete(@RequestParam(value = "uid") String uid, @RequestParam(value = "fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "directory_name") String directory_name)
			throws IOException {
		logger.info("Welcome! HomeController fileDelete : {} {} {}", uid, fileName, directory_name);

		// 서버에 저장된 이미지 경로
		// String path=WebUtils.getRealPath(request.getSession().getServletContext(),
		// "/storage/notice/"+directory_name+"/") ;
		// 절대경로를 통해 다운로드
		String absolutePath = "C:\\BR_storage\\notice\\" + directory_name + "\\";
//		String absolutePath= "/usr/local/BR_storage/notice/" + directory_name + "/";

		String sDirPath = absolutePath + uid + "_" + fileName;

		File file = new File(sDirPath);

		if (file.exists()) {
			file.delete();
		}

	}
	
	//<img src=\"download.do?uid=cc8ee125-5a91-47a2-9867-d0dbaf9a0c72&amp;fileName=image-20220618111003-1.gif&amp;directory_name=admin001_202206170023\" style=\"height:360px; width:360px\" />
    public void imgDelete(String str, String directory_name)	throws IOException {
    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		logger.info("Welcome! HomeController imgDelete");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
		
//		String directory_name=map.get(1).get("directory_name").toString();  
//		String uid= map.get("");
		// 서버에 저장된 이미지 경로
		// String path=WebUtils.getRealPath(request.getSession().getServletContext(),
		// "/storage/notice/"+directory_name+"/") ;
		// 절대경로를 통해 다운로드
		String absolutePath = "C:\\BR_storage\\notice\\" + directory_name + "\\img\\";
//		String absolutePath = "C:\\BR_storage\\notice\\admin001_202206170023\\";
//		String absolutePath= "/usr/local/BR_storage/notice/" + directory_name + "/";
		
		File dir = new File(absolutePath);
		String[] filenames = dir.list(); //기존 이미지 파일 배열

		String[] arr = str.split("<img src=\"download.do?");
		String[] fileName=new String[arr.length]; // 수정 글 img 배열
	
		//수정시 해당글의 기존 file 리스트를 가지고온다
		//수정할 때 저장할 콘텐츠에 들어있는 이미지 리스트와 비교
		
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].contains("uid")) {
				fileName[i] =arr[i].substring(arr[i].indexOf("uid=")+4,arr[i].indexOf("&amp;"))+"_"+arr[i].substring(arr[i].indexOf("fileName=")+9,arr[i].indexOf("&amp;",arr[i].indexOf("fileName")));
			}
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println(Arrays.toString(fileName));
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < filenames.length; i++) {
			if(!Arrays.asList(fileName).contains(filenames[i])) {
				String sDirPath = absolutePath + filenames[i];
				File file = new File(sDirPath);
				if (file.exists()) {
					file.delete();
				}
			}
		}
 	
		
	}

}
