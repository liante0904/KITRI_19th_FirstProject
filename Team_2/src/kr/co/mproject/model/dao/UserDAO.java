package kr.co.mproject.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.mproject.Exception.DuplicateIDException;
import kr.co.mproject.cont.SetupDB;
import kr.co.mproject.model.dto.UserDTO;

public class UserDAO {
	public List<UserDTO> getList(int chk) {
		//chk가 1인 경우 사용자용, 2인경우 관리자용.
		// 구성원 불러오기
		SetupDB sd = new SetupDB();
		String sql = null;
		if(chk==2){sql = "SELECT U_NAME, U_ID, U_EMAIL, AUTH_YN FROM USERS";}
		else if(chk==1){sql = "SELECT U_NAME, U_ID, U_EMAIL, AUTH_YN FROM USERS WHERE AUTH_YN='Y'";}
		ResultSet rs = sd.select(sql);
		List<UserDTO> USERSList = new ArrayList<UserDTO>();
		try {
			while (rs.next()) {
				UserDTO ud = new UserDTO();
				ud.setU_name(rs.getString(1));
				ud.setU_id(rs.getString(2));
				ud.setU_email(rs.getString(3));
				ud.setAUTH_YN(rs.getString(4));
				USERSList.add(ud);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sd.closeDB();
		}

		return USERSList;
	}
	public String getNameList(){
		//구성원 이름만 불러오기.
		return null;
	}
	public int chk_id(String u_id, String u_pw) {
		//로그인 체크.
		SetupDB sd = new SetupDB();
		String sql = "SELECT AUTH_YN FROM USERS WHERE u_id='"+u_id+"' AND u_pw='"+u_pw+"'";
		ResultSet rs = sd.select(sql);
		int login_chk = 0;
		String id_chk = "";
		try {
			if(rs.next()){
				id_chk = rs.getString(1);
			}
			
			if(id_chk.equals("Y")){
				login_chk=1;
			}else if(id_chk.equals("N")){
				//휴먼계정
				login_chk=3;
			}else{
				//검색 결과가 없을 경우.
				login_chk=0;
			}
			System.out.println("DAO내 login_ch:"+login_chk);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sd.closeDB();
		}
		return login_chk;
	}
	
	public UserDTO info_id(String u_id){
		SetupDB sd = new SetupDB();
		String sql = "SELECT u_id, u_name, u_email FROM USERS WHERE U_ID='"+u_id+"'";
		ResultSet rs = sd.select(sql);
		UserDTO ud = new UserDTO();
		try {
			if(rs.next()){
				ud.setU_id(rs.getString(1));
				ud.setU_name(rs.getString(2));
				ud.setU_email(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sd.closeDB();
		}
		return ud;
	}
	
	
	public int chk_id(String u_id) throws DuplicateIDException {
		//매개변수다른 chk_id()
		//회원 아이디 중복 체크.
		//0이 아닐시 아이디 중복 오류 발생.
		SetupDB sd = new SetupDB();
		String sql = "SELECT count(*) FROM USERS WHERE U_ID='"+u_id+"'";
		ResultSet rs = sd.select(sql);
		int login_chk = 0;
		try {
			if(rs.next()){
				login_chk = rs.getInt(1);
			}
			if(login_chk!=0){
				throw new DuplicateIDException(u_id+": 사용하시려는 아이디는 이미 존재하는 아이디입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sd.closeDB();
		}
		return login_chk;
	}
	
	public int del_user(String u_id){
		SetupDB sd = new SetupDB();
		String sql = "UPDATE USERS SET AUTH_YN='N' WHERE u_id='"+u_id+"'";
		int del_chk = sd.update(sql);
		try {
			del_chk = sd.update(sql);
		} finally{
			sd.closeDB();
		}

		return del_chk;
		//1이면 삭제 성공
	}
	
	public int Ndel_user(String u_id){
		SetupDB sd = new SetupDB();
		String sql = "UPDATE USERS SET AUTH_YN='Y' WHERE u_id='"+u_id+"'";
		int del_chk = sd.update(sql);
		try {
			del_chk = sd.update(sql);
		} finally{
			sd.closeDB();
		}

		return del_chk;
		//1이면 삭제 성공
	}
	
	public int join_user(UserDTO uDto) {
		// Insert문을 통해 bDto값 Database에 작성
		System.out.println("join_user들어옴");
		String sql = "INSERT INTO USERS(U_ID, U_NAME, U_PW, U_EMAIL, AUTH_YN) VALUES (?, ?, ?, ?, 'N')";
		//기본적으로 AUTH_YN의 경우 N으로 디폴트. 관리자 페이지에서 휴면계정 활성화 시켜야함.
		SetupDB sd = new SetupDB();
		System.out.println("db셋팅성공");
		PreparedStatement pstmt = null;
		int chk = 0;
		try {
			pstmt = sd.getCon().prepareStatement(sql);
			System.out.println("prepare");
			pstmt.setString(1, uDto.getU_id());
			System.out.println(uDto.getU_id()+"--------------------");
			pstmt.setString(2, uDto.getU_name());
			pstmt.setString(3, uDto.getU_pw());
			pstmt.setString(4, uDto.getU_email());

			chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sd.closeDB();
		}
		return chk;
		//1이면 등록 성공.
	}
	public int grantMod(String u_id, String YN) {
		SetupDB sd = new SetupDB();
		String sql = null;
		sql = "UPDATE USERS SET AUTH_YN='Y' WHERE u_id='"+u_id+"'";
		System.out.println(YN);
		if(YN.equals("Y")) 
		sql = "UPDATE USERS SET AUTH_YN='N' WHERE u_id='"+u_id+"'";
		
		int del_chk =0;
		try {
			del_chk = sd.update(sql);
		} finally{
			sd.closeDB();
		}

		return del_chk;
	
	}
	
	public String AUTH_YN(String u_id){
		SetupDB sd = new SetupDB();
		String sql = "SELECT AUTH_YN FROM USERS WHERE U_ID='"+u_id+"'";
		ResultSet rs = sd.select(sql);
		String chk = null;
		try {
			if(rs.next()){
				chk = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			sd.closeDB();
		}
		return chk;
	}
	
	public int updateUser(UserDTO udto) {
		String sql = "UPDATE USERS SET U_NAME = ?, U_PW = ?, U_EMAIL = ?, AUTH_YN = 'Y' WHERE U_ID = ?";
		int update_chk = 0;
		PreparedStatement pstmt = null;
		SetupDB sd = new SetupDB();
		try {
			pstmt = sd.getCon().prepareStatement(sql);
			pstmt.setString(1, udto.getU_name());
			pstmt.setString(2, udto.getU_pw());
			pstmt.setString(3, udto.getU_email());
			pstmt.setString(4, udto.getU_id());
			update_chk = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sd.closeDB();	
		}
		return update_chk;
	}
}