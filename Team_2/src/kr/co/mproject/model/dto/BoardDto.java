package kr.co.mproject.model.dto;


public class BoardDto {
	private int q_board_id;
	private int q_ref_id;
	private int q_reply_level;
	private String q_title;
	private String q_contents;
	private String q_wdate;
	private String u_id;
	private int q_read_cnt;
	private String q_pds_link;
	private String q_secret;
	private String q_use_yn;
	private int count;
	private int q_ref_group;
	

	public int getQ_ref_group() {
		return q_ref_group;
	}
	public void setQ_ref_group(int q_ref_group) {
		this.q_ref_group = q_ref_group;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getQ_board_id() {
		return q_board_id;
	}
	public void setQ_board_id(int q_board_id) {
		this.q_board_id = q_board_id;
	}
	public int getQ_ref_id() {
		return q_ref_id;
	}
	public void setQ_ref_id(int q_ref_id) {
		this.q_ref_id = q_ref_id;
	}
	public int getQ_reply_level() {
		return q_reply_level;
	}
	public void setQ_reply_level(int q_reply_level) {
		this.q_reply_level = q_reply_level;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}
	public String getQ_contents() {
		return q_contents;
	}
	public void setQ_contents(String q_contents) {
		this.q_contents = q_contents;
	}

	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getQ_secret() {
		return q_secret;
	}
	public void setQ_secret(String q_secret) {
		this.q_secret = q_secret;
	}
	public String getQ_wdate() {
		return q_wdate;
	}
	public void setQ_wdate(String q_wdate) {
		this.q_wdate = q_wdate;
	}

	public int getQ_read_cnt() {
		return q_read_cnt;
	}
	public void setQ_read_cnt(int q_read_cnt) {
		this.q_read_cnt = q_read_cnt;
	}
	public String getQ_pds_link() {
		return q_pds_link;
	}
	public void setQ_pds_link(String q_pds_link) {
		this.q_pds_link = q_pds_link;
	}
	public String getQ_use_yn() {
		return q_use_yn;
	}
	public void setQ_use_yn(String q_use_yn) {
		this.q_use_yn = q_use_yn;
	}
	
	
}
