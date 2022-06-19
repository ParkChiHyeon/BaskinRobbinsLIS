package com.br.lis.util;

import java.io.IOException;
import java.util.List;

import com.br.lis.model.purchaseinfo.service.IRequestPurchaseService;
import com.br.lis.vo.RequestPurchaseVo;

public class testMain {
	private static IRequestPurchaseService reqPurcService;
	public static void main(String[] args) throws IOException {
//		File_Controller test = new File_Controller();
//		
//		String absolutePath = "C:\\BR_storage\\notice\\admin001_202206170023\\";
//		File dir = new File(absolutePath);
//		String[] filenames = dir.list(); //기존 이미지 파일 배열
//		
//		String str ="<p>&nbsp;</p>\r\n"
//				+ "\r\n"
//				+ "<p>&nbsp;</p>\r\n"
//				+ "\r\n"
//				+ "<p>&nbsp;</p>\r\n"
//				+ "\r\n"
//				+ "<p>&nbsp;</p>\r\n"
//				+ "\r\n"
//				+ "<p>&nbsp;</p>\r\n"
//				+ "\r\n"
//				+ "<p><img src=\"download.do?uid=e399e1c2-94ae-4e5e-9ae2-6a2667fff317&amp;fileName=image-20220618204652-3.png&amp;directory_name=admin001_202206170023\" style=\"height:312px; width:312px\" /><img src=\"download.do?uid=c9948349-438d-464f-80a4-0b614c0c6f2b&amp;fileName=image-20220618204715-1.png&amp;directory_name=admin001_202206170023\" style=\"height:312px; width:312px\" /></p>\r\n"
//				+ "\r\n"
//				+ "<p>잘되는가</p>\r\n"
//				+ "\r\n"
//				+ "<p>이상해풀 -&gt; 이상해꽃</p>\r\n"
//				+ "\r\n"
//				+ "<p>이상해씨 -&gt; 이상해풀</p>\r\n"
//				+ "\r\n"
//				+ "<p>글 수정 테스트 중입니다.</p>";
////		System.out.println(Arrays.toString(arr));
////		
////		System.out.println(arr.length);
////		System.out.println(arr[1].substring(arr[1].indexOf("uid=")+4,arr[1].indexOf("&amp;")));
//		
//
//		String[] arr = str.split("<img src=\"download.do?");
//		String[] fileName=new String[arr.length]; // 수정 글 img 배열
//	
//		//수정시 해당글의 기존 file 리스트를 가지고온다
//		//수정할 때 저장할 콘텐츠에 들어있는 이미지 리스트와 비교
//		
//		List<String> deleteFiles = new ArrayList<String>();
//		
//		for (int i = 0; i < arr.length; i++) {
//			if(arr[i].contains("uid")) {
//				fileName[i] =arr[i].substring(arr[i].indexOf("uid=")+4,arr[i].indexOf("&amp;"))+"_"+arr[i].substring(arr[i].indexOf("fileName=")+9,arr[i].indexOf("&amp;",arr[i].indexOf("fileName")));
//			}
//		}
//		
//		for (int i = 0; i < filenames.length; i++) {
//			if(!Arrays.asList(fileName).contains(filenames[i])) {
//				deleteFiles.add(filenames[i]);
//			}
//		}
//		
//		System.out.println(deleteFiles);
		
		
 	}

}
