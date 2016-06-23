package kr.co.mproject.cont;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import kr.co.mproject.model.dao.BoardDao;
import kr.co.mproject.model.dto.BoardDto;



/**
 * Servlet implementation class BoardReadCont
 */
@WebServlet("/Board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String p_code = request.getParameter("p_code");
		System.out.println(p_code);
		HttpSession hs = request.getSession();
		if (p_code.equals("list")) {
			// 1.글목록 호출(list)
			// DB 접근하여 database에 전체 값 받아오기
			int page_cnt=Integer.parseInt(request.getParameter("page_cnt"));
			BoardDao dao = new BoardDao();
			List<BoardDto> list = dao.listBoard(page_cnt);
			int count=dao.pagecont();
			request.setAttribute("list", list);// 대문자
			request.setAttribute("count", count);
			String url = "jsp/board/board_list.jsp";
			move(request, response, url);
		} else if (p_code.equals("contents")) {
			System.out.println("contents");
			BoardDao dao = new BoardDao();
			int q_board_id = Integer.parseInt(request.getParameter("q_board_id"));
			//조회수 업데이트
			if(hs.getAttribute("u_id")!=null){
			int chk=dao.up_read_cnt(q_board_id);
			}
			List<BoardDto> list = dao.viewBoard(q_board_id);
			
			request.setAttribute("list", list);// 대문자
			String url = "jsp/board/board_view.jsp";
			move(request, response, url);
		} else if (p_code.equals("write")) {
			move(request, response, "jsp/board/board_input.jsp");
		}else if(p_code.equals("write_ok")){
			BoardDto dto=new BoardDto();
			dto.setQ_contents(request.getParameter("q_contents"));
			dto.setQ_title(request.getParameter("q_title"));
			if(request.getParameter("q_pds_link")!=null && request.getParameter("q_pds_link")!=""){
				File file=new File(request.getParameter("q_pds_link"));
				String url="/"+request.getContextPath()+"/"+file.getName();
//				fileupload(file, url,request);
				dto.setQ_pds_link(url);
			}else{
				dto.setQ_pds_link("");
			}
			if(request.getParameter("q_secret")!=null){
			dto.setQ_secret(request.getParameter("q_secret"));
			}else{
				dto.setQ_secret("N");
			} //비밀글 세팅
			String u_id= (String) hs.getAttribute("u_id");
			dto.setU_id(u_id);
			if(request.getParameter("q_ref_id")!=null){
				dto.setQ_reply_level(Integer.parseInt(request.getParameter("q_reply_level")));
				if(Integer.parseInt(request.getParameter("q_reply_level"))==1){
				dto.setQ_ref_id(1);
				}else{
					dto.setQ_ref_id(Integer.parseInt(request.getParameter("q_ref_id")));
				}
				dto.setQ_ref_group(Integer.parseInt(request.getParameter("q_ref_group")));
				System.out.println(dto.getQ_ref_group()+":"+dto.getQ_ref_id());
			}
			int chk=new BoardDao().addBoard(dto);
			if(chk==1){
				if(request.getParameter("q_ref_id")==null){
				move(request, response, "Board.do?p_code=list&page_cnt=1");
				}else{
					move(request, response, "Board.do?p_code=contents&q_board_id="+dto.getQ_ref_group());	
				}
				
			}else{
				
			}
			
		}else if (p_code.equals("modify")) {
			int q_board_id= Integer.parseInt(request.getParameter("q_board_id"));
			// db에 접근해서 b_id 값 받아오기
			BoardDao dao = new BoardDao();
			BoardDto dto = dao.view(q_board_id);
			request.setAttribute("dto", dto);
			move(request, response, "jsp/board/board_input.jsp");
		}else if (p_code.equals("delete")) {
			// db에 접근해서 b_id 값 받아오기
			int q_board_id = Integer.parseInt(request.getParameter("q_board_id"));
			BoardDao dao=new BoardDao();
			int chk=dao.removeBoard(q_board_id);
			if(chk==1){
				move(request, response, "Board.do?p_code=list&page_cnt=1");
			}else{
				
			}
		}else if(p_code.equals("reply")){
			BoardDto dto=new BoardDto();
			dto.setQ_ref_group(Integer.parseInt(request.getParameter("q_ref_group")));
			dto.setQ_ref_id(Integer.parseInt(request.getParameter("q_ref_id")));
			dto.setQ_board_id(Integer.parseInt(request.getParameter("q_board_id")));
			dto.setQ_reply_level(Integer.parseInt(request.getParameter("q_reply_level"))+1);
			request.setAttribute("dto", dto);
			request.setAttribute("q_code", "reply");
			request.setAttribute("p_cnt", request.getParameter("p_cnt"));
			move(request, response, "jsp/board/board_input.jsp");
		}else if (p_code.equals("modify_ok")) {
			System.out.println("수정ok");
			int q_ref_group=Integer.parseInt(request.getParameter("q_ref_group"));
			int q_board_id= Integer.parseInt(request.getParameter("q_board_id"));
			// db에 접근해서 b_id 값 받아오기
			BoardDao dao = new BoardDao();
			BoardDto dto = new BoardDto();
			dto.setQ_board_id(Integer.parseInt(request.getParameter("q_board_id")));
			dto.setQ_title(request.getParameter("q_title"));
			dto.setQ_contents(request.getParameter("q_contents"));
			System.out.println(request.getParameter("q_secret"));
			if(request.getParameter("q_secret")!=null){
				dto.setQ_secret(request.getParameter("q_secret"));
			}else{
					dto.setQ_secret("N");
			} //비밀글 세팅
			System.out.println(dto.getQ_secret());
			if(request.getParameter("q_pds_link")!=null && request.getParameter("q_pds_link")!=""){
				File file=new File(request.getParameter("q_pds_link"));
				String url=request.getContextPath()+"/"+file.getName();
				System.out.println("주소"+url);
//				fileinput(file, url);
				dto.setQ_pds_link(url);
			}else{
				dto.setQ_pds_link("");
			}
			int chk=dao.updateBoard(dto);
			
			if(chk==1){
				move(request, response, "Board.do?p_code=contents&q_board_id="+q_ref_group);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void move(HttpServletRequest request, HttpServletResponse response, String url) {
		RequestDispatcher dis = request.getRequestDispatcher(url);
		try {
			dis.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fileupload(File file2, String path,HttpServletRequest request){
		//먼저 WebContent 폴더 밑에 upfolder 폴더를 만든다. (파일이 저장될 위치)
//		String path = "C:/eclipse/workspace/test_fileupload/WebContent/upfolder/";
		DiskFileUpload upload = new DiskFileUpload();
		upload.setSizeMax(1024 * 1024); //파일  업로드  최대  size : 1메가
		upload.setSizeThreshold(4096);//메모리에  저장할  최대  size
		upload.setRepositoryPath(path + "temp"); //파일  임시  저장소
		List items;
		try {
			items = upload.parseRequest(request);
			FileItem item1 = (FileItem) items.get(0); // 첫번째
			String name = item1.getString("euc-kr"); // 한글로 읽는다. 예 : 홍길동
			System.out.println("올린 사람 : "+name + "<br>");
			// 올린 파일 처리
			FileItem item2 = (FileItem) items.get(1); // 두번째
			String fileName = item2.getName();   // 파일명 얻기
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);//경로빼고 파일명만
			long fileSize = item2.getSize();  // 파일 크기 얻기
			File file = new File(path + "/" + fileName); // 경로 + 파일명 지정
			item2.write(file);  // 파일 저장
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 올린 사람 이름 처리
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
