package kr.co.mproject.model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.mproject.cont.SetupDB;
import kr.co.mproject.model.dto.BoardDto;





public class BoardDao {
	
	//EMP 자료 접근용
	//DB ID 값 비교하여 접근하기.
	

	
	public List<BoardDto> listBoard(int page_cnt) {
		List<BoardDto> list=new ArrayList<BoardDto>();
		SetupDB sd=new SetupDB();
		String sql="SELECT b.* FROM(SELECT rownum nm,a.* FROM(SELECT Q_BOARD_ID,Q_TITLE,u_id,TO_CHAR(Q_WDATE,'YYYY-MM-DD'),Q_read_cnt,q_secret FROM QR_BOARD where Q_use_YN='Y' and Q_REPLY_LEVEL=0 ORDER BY Q_BOARD_ID DESC)a WHERE rownum<=?)b WHERE nm>=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
			try {
				pstmt=sd.getCon().prepareStatement(sql);
				pstmt.setInt(1, page_cnt*10);
				pstmt.setInt(2, (page_cnt-1)*10+1);
				rs=pstmt.executeQuery();
				while(rs.next()){
					BoardDto bd = new BoardDto();
					bd.setQ_board_id(rs.getInt(2));
					bd.setQ_title(rs.getString(3));
					bd.setU_id(rs.getString(4));
					bd.setQ_wdate(rs.getString(5));
					bd.setQ_read_cnt(rs.getInt(6));
					bd.setQ_secret(rs.getString(7));
					list.add(bd);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				if(rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(pstmt!=null)
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				sd.closeDB();
			}
		
		
		return list;
	}

	public List<BoardDto> viewBoard(int q_board_id) {
		List<BoardDto> list=new ArrayList<BoardDto>();
		BoardDto dto=new BoardDto();
		SetupDB sb=new SetupDB();
		String sql="select q_board_id,q_title,q_contents,q_read_cnt,TO_CHAR(Q_WDATE,'YYYY-MM-DD'),q_pds_link,q_reply_level,u_id,q_secret,q_ref_id,q_ref_group,(select max(q_ref_id) from qr_board where q_ref_group=647 group by  q_ref_group) FROM QR_BOARD where q_ref_group="+q_board_id+" and Q_use_YN='Y' order by Q_REF_ID desc,q_reply_level ";
		ResultSet rs=sb.select(sql);
		try {
			while(rs.next()){
				BoardDto bd = new BoardDto();
				bd.setQ_board_id(rs.getInt(1));
				bd.setQ_title(rs.getString(2));
				bd.setQ_contents(rs.getString(3));
				bd.setQ_read_cnt(rs.getInt(4));
				bd.setQ_wdate(rs.getString(5));
				bd.setQ_pds_link(rs.getString(6));
				bd.setQ_reply_level(rs.getInt(7));
				bd.setU_id(rs.getString(8));
				bd.setQ_secret(rs.getString(9));
				bd.setQ_ref_id(rs.getInt(10));
				bd.setQ_ref_group(rs.getInt(11));
				bd.setCount(rs.getInt(12));
				list.add(bd);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sb.closeDB();
		}

		return list;
	}



	public int pagecont() {
		int count=0;
		SetupDB sd=new SetupDB();
		String sql="SELECT count(*) from qr_board where q_use_yn='Y' and q_reply_level=0";
		ResultSet rs=sd.select(sql);
		try {
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sd.closeDB();
		}
		return count;
	}

	public int addBoard(BoardDto dto) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO QR_BOARD(Q_BOARD_ID,Q_REPLY_LEVEL,Q_REF_ID,Q_TITLE,Q_CONTENTS,u_id,Q_PDS_LINK,Q_READ_CNT,Q_SECRET,q_ref_group)";
		SetupDB sb=new SetupDB();
		int chk=0;
		PreparedStatement pstmt=null;
		try {
			
			int a=0;	
		if(dto.getQ_ref_id()==0){
			sql=sql+"VALUES(SEQ_KITRI_BOARD.NEXTVAL,0,1,?,?,?,?,0,?,SEQ_KITRI_BOARD.CURRVAL)";
			pstmt=sb.getCon().prepareStatement(sql);
			a=-2;
		}else{
			sql=sql+"VALUES(SEQ_KITRI_BOARD.NEXTVAL,?,?,?,?,?,?,0,?,?)";
			pstmt=sb.getCon().prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, dto.getQ_reply_level());
			pstmt.setInt(2, dto.getQ_ref_id());
			pstmt.setInt(a+8, dto.getQ_ref_group());
		}
		
		
		pstmt.setString(a+3, dto.getQ_title());
		pstmt.setString(a+4, dto.getQ_contents());
		pstmt.setString(a+5, dto.getU_id());
		pstmt.setString(a+6, dto.getQ_pds_link());
		pstmt.setString(a+7, dto.getQ_secret());
		
		chk=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				}
				sb.closeDB();
		}
		return chk;
	}

	public int removeBoard(int q_board_id) {
		String sql="UPDATE qr_board set q_use_yn='N' WHERE q_board_id=?";
		SetupDB sb=new SetupDB();
		int chk=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=sb.getCon().prepareStatement(sql);
			pstmt.setInt(1, q_board_id);
			
			chk=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
			sb.closeDB();
		}
		return chk;
	
		
	}

	public BoardDto view(int q_board_id) {
		BoardDto bd=new BoardDto();
		SetupDB sd=new SetupDB();
		String sql="SELECT q_board_id,q_title,q_contents,q_read_cnt,TO_CHAR(Q_WDATE,'YYYY-MM-DD'),q_pds_link,q_reply_level,u_id,q_secret,q_ref_id,q_ref_group from qr_board where q_use_yn='Y' and q_board_id="+q_board_id;
		ResultSet rs=sd.select(sql);
		try {
			if(rs.next()){
				bd.setQ_board_id(rs.getInt(1));
				bd.setQ_title(rs.getString(2));
				bd.setQ_contents(rs.getString(3));
				bd.setQ_read_cnt(rs.getInt(4));
				bd.setQ_wdate(rs.getString(5));
				bd.setQ_pds_link(rs.getString(6));
				bd.setQ_reply_level(rs.getInt(7));
				bd.setU_id(rs.getString(8));
				bd.setQ_secret(rs.getString(9));
				bd.setQ_ref_id(rs.getInt(10));
				bd.setQ_ref_group(rs.getInt(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sd.closeDB();
		}
		return bd;
	}

	public int updateBoard(BoardDto dto) {
		String sql="UPDATE qr_board set q_Title=?,q_contents=?,q_pds_link=?, q_secret=? where q_board_id=?";
		SetupDB sb=new SetupDB();
		int chk=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=sb.getCon().prepareStatement(sql);
			pstmt.setString(1, dto.getQ_title());
			pstmt.setString(2, dto.getQ_contents());
			pstmt.setString(3, dto.getQ_pds_link());
			pstmt.setInt(5, dto.getQ_board_id());
			pstmt.setString(4, dto.getQ_secret());
			chk=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
			sb.closeDB();
		}
		return chk;
	}

	public int up_read_cnt(int q_board_id) {
		String sql="UPDATE qr_board set q_read_cnt=q_read_cnt+1 where q_ref_group=?";
		SetupDB sb=new SetupDB();
		int chk=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=sb.getCon().prepareStatement(sql);
			pstmt.setInt(1, q_board_id);
			chk=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
			sb.closeDB();
		}
		return chk;
	}
}
