package adminmain;

public class AdminMain {

	private ViewAdminMain viewAdminMain;
	private IAdminMainService iAdminMainService;
	
	public AdminMain() {
		
		viewAdminMain = new ViewAdminMain();
		iAdminMainService = new AdminMainService();
		
	}
	
	public void start() {
		
		viewAdminMain.loginMenu();
		iAdminMainService.login();
		
		
		
	}
	
}
