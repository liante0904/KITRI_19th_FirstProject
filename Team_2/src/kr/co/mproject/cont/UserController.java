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
@WebServlet("/modify.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String code = "";
		HttpSession hs = request.getSession();
		if(request.getParameter("code")!=null){
			code = request.getParameter("code");
		} 

		if(code.equals("mod_ok")){
			//  수정 정보 입력후 submit
			//  DB반영
			System.out.println("11111들어옴");
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
			int join_chk = ud.updateUser(udt);
			if(join_chk == 1){
				move(request, response, "jsp/login/modify_ok.jsp");
			}
		}else if(code.equals("modify")){
			String u_id = (String) hs.getAttribute("u_id");
			UserDAO ud = new UserDAO();
			UserDTO udt = new UserDTO();
			udt = ud.info_id(u_id);
			request.setAttribute("USERS", udt);
			move(request, response, "jsp/login/join.jsp");
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
