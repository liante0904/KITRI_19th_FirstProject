package kr.co.mproject.cont;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.mproject.Exception.DuplicateIDException;
import kr.co.mproject.model.dao.UserDAO;
import kr.co.mproject.model.dto.UserDTO;
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String code = "";
		HttpSession hs = request.getSession();
		if(request.getParameter("code")!=null){
			code = request.getParameter("code");
		} 
		if(hs.getAttribute("u_id")!=null && !code.equals("log_out")){
			move(request, response, "monitorCont.do");
		}

		

		if(code.equals("def")){
			//기본페이지로 이동
			move(request, response, "jsp/login/login_form.jsp");
		}
		else if(code.equals("log_out")){
			//세션 삭제 후 기본 페이지로 이동.
			hs.removeAttribute("u_id");
			hs.invalidate();
			move(request, response, "index.jsp");
		}
		else if(code.equals("login_chk")){
			//로그인 버튼 눌렀을 시
			System.out.println("로그인 시도중.......");
			String u_id=null;
			String u_pw=null;

			//세션 부재시
			u_id = request.getParameter("u_id");
			u_pw = request.getParameter("u_pw");
			UserDAO ud = new UserDAO();
			int chk_id = ud.chk_id(u_id, u_pw);
			//DB에서 데이터 확인 후 로그인 성공 시(chk_id값 1 or 2)
			if(chk_id==1){
				//로그인 성공시
				//일반 사용자, 관리자 사용자
				hs.setAttribute("u_id", u_id);
				hs.setAttribute("u_pw", u_pw);
				move(request, response, "monitorCont.do");
			}
			//로그인 실패시
			else if(chk_id==0){
				//로그인 폼으로 다시 이동
				request.setAttribute("error", "ID와 PW를 다시 확인하세요.");
				move(request, response, "jsp/login/login_form.jsp");
			}
			else if(chk_id==3){
				//휴면 계정인 경우 관리자의 승인이 필요함.
				request.setAttribute("error", "관리자의 승인이 필요합니다. 관리자에게 요청하세요.");
				move(request, response, "jsp/login/login_form.jsp");
			}
		}else if(code.equals("join_ok")){
			//회원가입 처리
			String u_id = request.getParameter("u_id");
			String u_pw = request.getParameter("u_pw");
			String u_email = request.getParameter("u_email");
			String u_name = request.getParameter("u_name");
			UserDAO ud = new UserDAO();
			UserDTO udt = new UserDTO();
			udt.setU_email(u_email);
			udt.setU_id(u_id);
			udt.setU_name(u_name);
			udt.setU_pw(u_pw);
			try{
				ud.chk_id(u_id); //아이디 중복 체크
				ud.join_user(udt); //중복이 아닐시 회원가입 진행
				request.setAttribute("error", u_name+"님, 가입이 성공적으로 완료되었습니다. 관리자 승인 후 사용 가능합니다.");
				move(request, response, "jsp/login/login_form.jsp");
			}catch(DuplicateIDException e){
				//아이디 중복시 오류메시지 전송
				request.setAttribute("error", e.getMessage());
				request.setAttribute("code1", "duplicateid");
				move(request, response, "jsp/login/login_form.jsp");
			}
		}else if(code.equals("error")){
			request.setAttribute("error", "비정상적인 접근입니다.");
			move(request, response, "jsp/login/login_form.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//Dispatch forward
	private void move(HttpServletRequest request, HttpServletResponse response, String url){
		RequestDispatcher dis = request.getRequestDispatcher(url);
		try {
			dis.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
