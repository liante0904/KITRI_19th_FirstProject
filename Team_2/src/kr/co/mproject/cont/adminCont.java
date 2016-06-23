package kr.co.mproject.cont;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.mproject.model.dao.UserDAO;
import kr.co.mproject.model.dto.UserDTO;

/**
 * Servlet implementation class adminCont
 */
@WebServlet("/adminCont.do")
public class adminCont extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String code = "";
		if(request.getParameter("code")!=null){
			code = request.getParameter("code");
		}
		HttpSession hs = request.getSession();
		if(hs.getAttribute("u_id")==null){
			move(request, response, "login.do?code=error");
		}else if(!hs.getAttribute("u_id").equals("admin")){
			move(request, response, "login.do?code=error");
		}else if(code.equals("allGrand")){
			String[] u_id = request.getParameterValues("grant");
			UserDAO ud = new UserDAO();
			for(int i=0; i<u_id.length; i++){
				ud.grantMod(u_id[i], ud.AUTH_YN(u_id[i]));
			}
			String url = "jsp/login/admin_del.jsp";
			move(request, response, url);
		}else{
			//관리자 기본 페이지
			System.out.println("2");
			String url = "jsp/login/admin.jsp";
			UserDAO ud = new UserDAO();
			List<UserDTO> user_list = ud.getList(2);
			request.setAttribute("USERS", user_list);
			move(request, response, url);
		}

		System.out.println("3");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
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
