package adminmain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DTO_Admin;


public class DAO_AdminMain {

		private Connection conn;
		private Statement stat;
		private PreparedStatement pstat;
		
		// 초기화(선행 작업)
		// DB 접속
		public DAO_AdminMain() {
			
			try {
				DBUtil util = new DBUtil();
				this.conn = util.connect();
				this.stat = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DAO.Constructor");
			}
			
		}
		
		public boolean isConnected() { // 조건문 써먹을수 잇음
			
			try {
				return !this.conn.isClosed();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
					
		}
		
		public void close() {
			try {
				
				this.conn.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		
		public ArrayList<DTO_Admin> adminLogin() {
			
			
			
			ArrayList<DTO_Admin> dto_admin_list = new ArrayList<DTO_Admin>();
			
			String sql = "SELECT id, pw FROM tblAdmin";
			
			try {
				
				ResultSet rs = stat.executeQuery(sql);
				
				while(rs.next()) {
					
					DTO_Admin DTO_admin = new DTO_Admin();
					
					DTO_admin.setId(rs.getString("id"));
					DTO_admin.setPw(rs.getString("pw"));
					
					dto_admin_list.add(DTO_admin);
					
				}
				
				return dto_admin_list;
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return null;
			
			
		}
		
		
	
}
