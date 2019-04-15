package adminmain;

import java.util.Scanner;

import adminfunction.*;

public class AdminMain {

	private ViewAdminMain viewAdminMain;
	private IAdminMainService iAdminMainService;
	private IService_AdminFunction_A adminFunction;
	private View_AdminFunction_A viewAdminFunction;
	private View_AdminFunction2_A viewAdminFunction2;
	private IService_AdminFunction2_A adminFunction2;
	private Scanner scan;
	public static String id;
	public static String pw;

	public AdminMain() {

		viewAdminMain = new ViewAdminMain();
		iAdminMainService = new AdminMainService();
		adminFunction = new Service_AdminFunction_A();
		viewAdminFunction = new View_AdminFunction_A();
		viewAdminFunction2 = new View_AdminFunction2_A();
		adminFunction2 = new Service_AdminFunction2_A();
		scan = new Scanner(System.in);

	}

	public void cStart() {
		
		viewAdminMain.vInitialMenu();
		viewAdminMain.vNumSelect(); // 번호 : 출력
		
		int num = scan.nextInt();
		scan.nextLine();
		
		cLogin(num);
		
	}
	
	public void cLogin(int num) {
		
		boolean flag = true;
		
		while(flag) {
			if(num == 1) {
				flag = false;
				
				boolean loginFlag = false;
				
				while(!loginFlag) {
					viewAdminMain.loginMenu();
					viewAdminMain.vIdSelect(); // 아이디(이름) : 출력
					id = scan.nextLine();
					viewAdminMain.vPwSelect(); // 비밀번호(주민번호뒷자리) : 출력
					pw = scan.nextLine();
					
					loginFlag = iAdminMainService.sLogin(id, pw); // 아이디와 비밀번호가 있는지 확인
					
					if(!loginFlag)
						System.out.println("아이디 또는 비밀번호가 없습니다.");
					else {
						cAdminMainMenu();
					}
				}
			}else if(num == 0){
				flag = false;
				// 관리자 초기화면으로 돌아가기
				
			}
			if(flag) {
				viewAdminMain.vWrong(); // 잘못입력표시
			}
			
		}
	}
	
	public void cAdminMainMenu() {
		
		boolean flag = true;
		
		while(flag) {
			
			viewAdminMain.vAdminMainMenu();
			int num = scan.nextInt();
			scan.nextLine();
			
			if(num == 1) {
				
				flag = false;
				
			}else if(num == 2){
				
				flag = false;
				// 관리자 2번기능
				
				adminFunction.sOpenMgmtInfo();
				String select = scan.nextLine();
				
				amOCSmgmt(select);
				
			}else if(num == 3){
				
				flag = false;
				// 관리자 3번기능
				
			}else if(num == 4){
				
				flag = false;
				// 관리자 4번기능
				
			}else if(num == 5){
				
				flag = false;
				cAttendanceMgmt();
				
			}else if(num == 6){
				
				flag = false;
				cTeacherEvalMgmt();
				
			}else if(num == 7){
				
				flag = false;
				cConsult();
				
			}else if(num == 0){
				
				flag = false;
				cStart();
				
			}else {
				viewAdminMain.vWrong(); // 잘못입력 표시
			}
		}
		
	}

	public void cAttendanceMgmt() {
		
		viewAdminFunction2.vAttendanceMgmt();
		
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("a")) {
			
			// 1. 관리자 - 5. 출결 관리 및 출결조회 -a. 학생별 조회 및 수정
			cAttendanceStudentInfoModify();
			
			
		}else if(select.equalsIgnoreCase("b")) {
			
			// 1. 관리자 - 5. 출결 관리 및 출결조회 -b. 과정별 조회
			cAttendanceCourseInfo();
			
		}else if(select.equalsIgnoreCase("z")) {
			//관리자 초기메뉴로 돌아가기
			cAdminMainMenu();
		}
		
	}

	public void cAttendanceCourseInfo() {
		
		// 1. 관리자 - 5. 출결 관리 및 출결조회 -b. 과정별 조회
		adminFunction2.sAttendanceCourseInfo();
		
		String select = scan.nextLine();
		if(select.equalsIgnoreCase("z")) {
			// 1. 관리자 - 5. 출결 관리 및 출결조회로 이동
			cAttendanceMgmt();
		}else{
			adminFunction2.sAttendanceCourseView(select);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				adminFunction2.sAttendanceCourseModify(choice);
				
				// 1. 관리자 - 5. 출결 관리 및 출결조회 -b. 과정별 조회로 돌아감
				cAttendanceCourseInfo();
				
			}else if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 - 5. 출결 관리 및 출결조회 -b. 과정별 조회로 돌아감
				cAttendanceCourseInfo();
			}
		}
		
	}

	public void cAttendanceStudentInfoModify() {
		// 1. 관리자 - 5. 출결 관리 및 출결조회 -a. 학생별 조회 및 수정
		
		adminFunction2.sAttendanceStudentInfoModify();
		
		String choice = scan.nextLine();
		
		if(choice.equalsIgnoreCase("z")) {
			// 1. 관리자 - 5. 출결 관리 및 출결조회로 이동
			cAttendanceMgmt();
		}else {
			System.out.println("- 조회기간을 입력해 주세요(ex 2018-01-01)");
			System.out.print("시작일(ex 2018-01-01) : ");
			String startDate = scan.nextLine();
			System.out.print("종료일(ex 2018-01-01) : ");
			String endDate = scan.nextLine();
			
			System.out.println("계속하시려면 엔터를 눌러주세요.");
			scan.nextLine();
			
			adminFunction2.sAttendanceStudentInfoModifySelect(choice,startDate, endDate);
			
			String sel = scan.nextLine();
			
			if(sel.equalsIgnoreCase("a")) {
				
				//근태상황 수정하기
				adminFunction2.sAttendanceStateModify();
				
				// 1. 관리자 - 5. 출결 관리 및 출결조회 -a. 학생별 조회 및 수정으로 돌아감
				cAttendanceStudentInfoModify();
			}else if(sel.equalsIgnoreCase("z")) {
				// 1. 관리자 - 5. 출결 관리 및 출결조회 -a. 학생별 조회 및 수정으로 돌아감
				cAttendanceStudentInfoModify();
			}
			
		}
		
	}

	// 상담요청내역
	public void cConsult() {

		// 1. 관리자 - 7. 상담일지 관리 및 조회
		viewAdminFunction.vConsult();
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("a")) {
			
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역
			viewAdminFunction.vConsultRequestInfo();
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - a. 일괄 조회 및 수정
				
				adminFunction.sConsultRequestInfoModify();
				
				String sel = scan.nextLine();
				
				if(sel.equalsIgnoreCase("a")) {
					// 요청내역 수정
					adminFunction.sConsultRequestModify();
					System.out.println("계속하시려면 엔터를 누르세요.");
//					scan.nextLine();
					
					cConsult();
					
				}else if(sel.equalsIgnoreCase("b")) {
					// 요청내역 삭제
					adminFunction.sConsultRequestDelete();
					System.out.println("계속하시려면 엔터를 누르세요.");
					scan.nextLine();
					cConsult();
					
				}else if(sel.equalsIgnoreCase("z")) {
					// 뒤로가기
					cConsult();
				}
				
				
			}else if(choice.equalsIgnoreCase("b")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - b. 과정별 조회 및 수정
				
				cConsultCourse();
				
			}else if(choice.equalsIgnoreCase("c")) {
				//1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
				
				cConsultStudent();
				
			}else if(choice.equalsIgnoreCase("z")) {
				cConsult();
			}
			
		}else if(select.equalsIgnoreCase("b")) { 
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역
			
			cConsultResult();
			
			
		}else if(select.equalsIgnoreCase("z")) {
			cAdminMainMenu();
		}
		
	}

	public void cConsultResult() {
		// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역
		
		viewAdminFunction.vConsultResult();
		
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("a")) {
			
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - a. 일괄 조회 및 수정
			
			adminFunction.sConsultResultInfo();
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - a. 일괄 조회 및 수정 - 수정하기
				adminFunction.sConsultResultModify();
				cConsultResult();
				
			}else if(choice.equalsIgnoreCase("b")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - a. 일괄 조회 및 수정 - 삭제하기
				adminFunction.sConsultResultDelete();
				cConsultResult();
				
			}else if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역으로 돌아감
				cConsultResult();
			}
			
		}else if(select.equalsIgnoreCase("b")) {
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - b. 과정별 조회 및 수정
			adminFunction.sConsultResultInfoCourse();
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역으로 돌아감
				cConsultResult();
			}else {
				
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - b. 과정별 조회 및 수정 - 1. 선택
				adminFunction.sConsultResultInfoCourseViewModify(choice);
				
				 String sel = scan.nextLine();
				 
				 if(sel.equalsIgnoreCase("a")) {
					 
					 // 상담일지 수정
					 adminFunction.sConsultResultModify();
					 
					// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역으로 돌아감
					cConsultResult();
					 
				 }else if(sel.equalsIgnoreCase("b")) {
					 
					 // 상담일지 삭제
					 adminFunction.sConsultResultDelete();
					 
					// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역으로 돌아감
					cConsultResult();
					 
				 }else if(sel.equalsIgnoreCase("z")) {
					// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역으로 돌아감
					cConsultResult();
				 }
				
			}
			
		}else if(select.equalsIgnoreCase("c")) {
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정
			cConsultRecordStudent();
			
			
			
		}else if(select.equalsIgnoreCase("z")) {
			// 1. 관리자 - 7. 상담일지 관리 및 조회로 돌아감
			cConsult();
			
		}
		
	}

	private void cConsultRecordStudent() {
		// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정
		
		viewAdminFunction.vConsultRecordStudentInfoModify();
		
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("a")) {
			
			adminFunction.cConsultRecordStudentName();
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				 
				 // 상담일지 수정
				 adminFunction.sConsultResultModify();
				 
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
				 
			 }else if(choice.equalsIgnoreCase("b")) {
				 
				 // 상담일지 삭제
				 adminFunction.sConsultResultDelete();
				 
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
				 
			 }else if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
			 }
			
		}else if(select.equalsIgnoreCase("b")) {
			
			adminFunction.cConsultRecordStudentPw();
			
String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				 
				 // 상담일지 수정
				 adminFunction.sConsultResultModify();
				 
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
				 
			 }else if(choice.equalsIgnoreCase("b")) {
				 
				 // 상담일지 삭제
				 adminFunction.sConsultResultDelete();
				 
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
				 
			 }else if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
			 }
			
		}else if(select.equalsIgnoreCase("c")) {
			
			adminFunction.cConsultRecordStudentMajor();
			
String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				 
				 // 상담일지 수정
				 adminFunction.sConsultResultModify();
				 
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
				 
			 }else if(choice.equalsIgnoreCase("b")) {
				 
				 // 상담일지 삭제
				 adminFunction.sConsultResultDelete();
				 
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
				 
			 }else if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역 - c. 학생별 조회 및 수정으로 돌아감
				 cConsultRecordStudent();
			 }
			
		}else if(select.equalsIgnoreCase("z")) {
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - b. 상담 일지 내역으로 돌아감
			cConsultResult();
		}
		
	}

	public void cConsultStudent() {
		
		//1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
		viewAdminFunction.vConsultStudent();
		
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("a")) {
			// a. 학생명
			viewAdminFunction.vConsultRequestStudentName();
			
			String name = scan.nextLine();
			
			adminFunction.sConsultRequestStudentName(name);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
				cConsultStudent();
			}else if(choice.equalsIgnoreCase("a")){
				adminFunction.sConsultRequestModify();
				cConsultStudent();
				
			}else if(choice.equalsIgnoreCase("b")) {
				adminFunction.sConsultRequestDelete();
				cConsultStudent();
			}
			
		}else if(select.equalsIgnoreCase("b")) {
			// b. 주민번호 뒷자리
			viewAdminFunction.vConsultRequestStudenPw();
			
			String pw = scan.nextLine();
			
			adminFunction.sConsultRequestStudentPw(pw);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
				cConsultStudent();
			}else if(choice.equalsIgnoreCase("a")){
				adminFunction.sConsultRequestModify();
				cConsultStudent();
				
			}else if(choice.equalsIgnoreCase("b")) {
				adminFunction.sConsultRequestDelete();
				cConsultStudent();
			}
			
		}else if(select.equalsIgnoreCase("c")) {
			// c. 전화번호
			
			viewAdminFunction.vConsultRequestStudenTel();
			
			String tel = scan.nextLine();
			
			adminFunction.sConsultRequestStudentTel(tel);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
				cConsultStudent();
			}else if(choice.equalsIgnoreCase("a")){
				adminFunction.sConsultRequestModify();
				cConsultStudent();
				
			}else if(choice.equalsIgnoreCase("b")) {
				adminFunction.sConsultRequestDelete();
				cConsultStudent();
			}
			
		}else if(select.equalsIgnoreCase("d")) {
			// d. 등록일
			
			viewAdminFunction.vConsultRequestStudenRegdate();
			
			String regdate = scan.nextLine();
			
			adminFunction.sConsultRequestStudentRegdate(regdate);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
				cConsultStudent();
			}else if(choice.equalsIgnoreCase("a")){
				adminFunction.sConsultRequestModify();
				cConsultStudent();
				
			}else if(choice.equalsIgnoreCase("b")) {
				adminFunction.sConsultRequestDelete();
				cConsultStudent();
			}
			
		}else if(select.equalsIgnoreCase("e")) {
			// e. 학과
			viewAdminFunction.vConsultRequestStudenMajor();
			
			String major = scan.nextLine();
			
			adminFunction.sConsultRequestStudentMajor(major);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("z")) {
				// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정
				cConsultStudent();
			}else if(choice.equalsIgnoreCase("a")){
				adminFunction.sConsultRequestModify();
				cConsultStudent();
				
			}else if(choice.equalsIgnoreCase("b")) {
				adminFunction.sConsultRequestDelete();
				cConsultStudent();
			}
			
		}else if(select.equalsIgnoreCase("z")) {
			// z. 뒤로가기
			cConsult();
		}
		
		
	}

	public void cConsultCourse() {
		// 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - b. 과정별 조회 및 수정
		
		adminFunction.sConsultRequestCourse();
		
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("z")) {
			
			cConsult();
			
		}else {
			
			// 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - b. 과정별 조회 및 수정 - 1. 선택
			
			adminFunction.sConsultRequestCourseSelect(select);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {
				adminFunction.sConsultRequestCourseModify();
				cConsultCourse();
			}else if(choice.equalsIgnoreCase("b")) {
				adminFunction.sConsultRequestCourseDelete();
				cConsultCourse();
			}else if(choice.equalsIgnoreCase("z")) {
				cConsultCourse();
			}
			
		}
		
	}

	public void cTeacherEvalMgmt() {
		
		viewAdminFunction.vTeacherEval();
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("a")) {
			
			viewAdminFunction.vTeacherEvalQuestionRegister(); // 교사평가 질문 내용출력
			adminFunction.sTeacherEvalQuestionRegister(); // 교사평가 질문 등록
			System.out.println("계속하시려면 엔터를 입력하세요.");
			scan.nextLine();
			cTeacherEvalMgmt(); // 교사평가 질문 등록후 6번기능 처음으로 돌아감
			
		}else if(select.equalsIgnoreCase("b")) {
			
			cTeacherEvalQuestionModifyDelete(); // 교사평가 질문지 수정 및 삭제
				
				
			
			
		}else if(select.equalsIgnoreCase("c")) {
			
			cTeacherEvalQuestionResultInfoDelete();
			
		}else if(select.equalsIgnoreCase("z")) {
			// 관리자 초기메뉴로 돌아가기
			cAdminMainMenu();
		}
		
	}

	public void cTeacherEvalQuestionResultInfoDelete() {

		adminFunction.sTeacherEvalQuestionResultInfoDelete();
		
		String select = scan.nextLine();
		
		if(select.equalsIgnoreCase("z")) {
			cTeacherEvalMgmt();
		}else {
			
			// z 가 아니면 select는 숫자로 생각하고 개설과정번호로 취급
			adminFunction.sTeacherEvalQuestionResultInfoDeleteInfo(select);
			
		}
		
	}

	public void cTeacherEvalQuestionModifyDelete() {

		adminFunction.sTeacherEvalQuestionModifyDelete(); // 질문지 수정 및 삭제
		String choice = scan.nextLine(); // 질문지 수정 및 삭제에서 기능 선택
		
		if(choice.equalsIgnoreCase("z")) {
			cTeacherEvalMgmt(); // 6번기능 처음으로 돌아감
		}else {
			
			int choiceNum = Integer.parseInt(choice); // z가 아니면 숫자로 바꿈
			adminFunction.sTeacherEvalQuestionModifyDeleteNumber(choiceNum); // 입력한 값에 대해 맞는 질문지 출력 및 수정/삭제 선택
			
			String select = scan.nextLine();
			
			if(select.equalsIgnoreCase("z")) {
				cTeacherEvalQuestionModifyDelete(); // 질문지 수정 및 삭제 처음페이지로 이동
			}else {
				int selectNum = Integer.parseInt(select);
				
				if(selectNum == 1) {
					
					adminFunction.sTeacherEvalQuestionModify(choiceNum);
					
					System.out.println("계속하시려면 엔터를 입력하세요.");
					scan.nextLine();
					
					cTeacherEvalQuestionModifyDelete(); // 수정후 질문지 수정 및 삭제 처음페이지로 이동
					
				}else if(selectNum == 2) {
					
					adminFunction.sTeacherEvalQuestionDelete(choiceNum);
					
					System.out.println("계속하시려면 엔터를 입력하세요.");
					scan.nextLine();
					
					cTeacherEvalQuestionModifyDelete(); // 삭제후 질문지 수정 및 삭제 처음페이지로 이동
				}
				
			}
			
		}
		
	}

	public void amOCSmgmt(String select) {
		
		int selectNum;
		
		if( select.equalsIgnoreCase("a") ) { // 개설과정 정보 등록
			
			adminFunction.sOpenMgmtAdd();
			
			adminFunction.sOpenMgmtInfo();
			select = scan.nextLine();
			
			amOCSmgmt(select);
			
		}else if( select.equalsIgnoreCase("z") ) { // 
			cAdminMainMenu();
		}else {
			selectNum = Integer.parseInt(select);
			
			int num = adminFunction.sOpenMgmtInfoDetails(selectNum); // 개설과정번호(PK)
			
			adminFunction.sOCSmgmtdetails(num);
			
			String choice = scan.nextLine();
			
			if(choice.equalsIgnoreCase("a")) {// 개설과목 정보 등록
				adminFunction.sOpenMgmtSubjectRegistration(selectNum);
				
				adminFunction.sOpenMgmtInfo();
				select = scan.nextLine();
				
				amOCSmgmt(select);
				
			}else if(choice.equalsIgnoreCase("z")) { // 관리자 2번기능 첫화면으로 돌아감
				adminFunction.sOpenMgmtInfo();
				
				select = scan.nextLine();
				
				amOCSmgmt(select);
			}
			
		}
		
	}

}
