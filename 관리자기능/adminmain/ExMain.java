package adminmain;

public class ExMain {

	public static void main(String[] args) {
		
		ViewAdminMain viewAdminMain = new ViewAdminMain();
		viewAdminMain.loginMenu();
		
		AdminMainService adminMainService = new AdminMainService();
		adminMainService.login();
		
	}
	
}
