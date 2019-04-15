package adminmain;

import java.util.ArrayList;
import java.util.Scanner;

import common.DTO_Admin;

public class AdminMainService implements IAdminMainService{

	private AdminMain_DAO DAO_adminMain;
	
	public AdminMainService() {
		
		DAO_adminMain = new AdminMain_DAO();
		
	}
	
	@Override
	public boolean sLogin(String id, String pw) {
		
		ArrayList<DTO_Admin> loginList = new ArrayList<DTO_Admin>();
		
		loginList = DAO_adminMain.adminLogin();
		
		boolean flag = false;
		
		for(int i = 0; i < loginList.size(); i++) {
			if( id.equals(loginList.get(i).getId()) && pw.equals(loginList.get(i).getPw()) ) {
				flag =true;
			}
			
		}
		
		return flag;
		
	}



}
