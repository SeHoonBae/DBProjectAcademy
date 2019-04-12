package adminmain;

import java.util.ArrayList;
import java.util.Scanner;

import common.DTO_Admin;

public class AdminMainService implements IAdminMainService{

	private Scanner scan;
	private static String id;
	private static String pw;
	private DAO_AdminMain DAO_adminMain;
	
	public AdminMainService() {
		
		scan = new Scanner(System.in);
		DAO_adminMain = new DAO_AdminMain();
		
	}
	
	@Override
	public void login() {
		
		System.out.print("ID : ");
		id = scan.nextLine();
		System.out.print("PW : ");
		pw = scan.nextLine();
		
		ArrayList<DTO_Admin> loginList = new ArrayList<DTO_Admin>();
		
		loginList = DAO_adminMain.adminLogin();
		
		boolean flag = false;
		
		for(int i = 0; i < loginList.size(); i++) {
			if( id.equals(loginList.get(i).getId()) && pw.equals(loginList.get(i).getPw()) ) {
				flag =true;
			}
		}
		
		
		
		if(flag) {
			ViewAdminMain viewAdminMain = new ViewAdminMain();
			viewAdminMain.adminMainMenu();
			
		}else {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			login();
		}
		
	}

	@Override
	public void service_adminMainMenu() {
		
		int num = scan.nextInt();
		ViewAdminMain viewAdminMain = new ViewAdminMain();
		AdminMainService adminMainService = new AdminMainService();
		
		if(num == 1) {
			
		}
		else if(num == 2) {
			
		}
		else if(num == 3) {
			
		}
		else if(num == 4) {
			
		}
		else if(num == 5) {
			
		}
		else if(num == 6) {
			
		}
		else if(num == 7) {
			
		}
		else if(num == 0) {
			
			viewAdminMain.loginMenu();
			adminMainService.login();
			
		}
		else {
			System.out.println("잘못된 번호를 입력하셨습니다.");
			service_adminMainMenu(); // 아이디 비밀번호 다시 치게끔 함, 기능을 바꿔도됨
		}
		
		
	}
	
	

	
	
}
