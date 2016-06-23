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

import kr.co.mproject.model.dao.ProDAO;
import kr.co.mproject.model.dao.UserDAO;
import kr.co.mproject.model.dto.ProDTO;
import kr.co.mproject.model.dto.Typedto;
import kr.co.mproject.model.dto.UserDTO;

/**
 * Servlet implementation class monitorCont
 */
@WebServlet("/monitorCont.do")
public class monitorCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession hs = request.getSession();
		if(hs.getAttribute("u_id")==null){
			move(request, response, "login.do?code=error");
		}
		else{
			UserDAO ud = new UserDAO();
			ProDAO pd = new ProDAO();
			List<UserDTO> user_list = ud.getList(1); //사용자용이므로 AUTH_YN이 'N'인 메소드 호출
			List<ProDTO> pro_list = pd.getList();
			List<Typedto> type_list = pd.getTypeList();
			Typedto type_tt = pd.Typett();
			String url = "jsp/project/monitor.jsp";	
			request.setAttribute("PROJECTS", pro_list);
			request.setAttribute("USERS", user_list);
			request.setAttribute("TYPE", type_list);
			request.setAttribute("TYPEtt", type_tt);
			request.setAttribute("TYPECNT", type_list.size());
			move(request, response, url);
		}
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void move(HttpServletRequest req, HttpServletResponse resp, String url){
		RequestDispatcher rd = req.getRequestDispatcher(url);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
