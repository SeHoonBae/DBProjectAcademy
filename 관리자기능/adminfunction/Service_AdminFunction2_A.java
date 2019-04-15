package adminfunction;

import java.util.ArrayList;
import java.util.Scanner;

import admin2_6_7.DTO_am_rc_A;
import admin2_6_7.DTO_c_oc_r_tc_t_A;
import admin2_6_7.DTO_stu_rc_oc_admg_A;
import admin2_6_7.DTO_stu_rc_oc_c_r_A;

public class Service_AdminFunction2_A implements IService_AdminFunction2_A{

	private View_AdminFunction2_A view_adminFunction2;
	
	public Service_AdminFunction2_A() {
		view_adminFunction2 = new View_AdminFunction2_A();
	}
	
	@Override
	public void sAttendanceStudentInfoModify() {
		
		DAO_AdminFunction2_A dao_adminFunction2 = new DAO_AdminFunction2_A();
		
		view_adminFunction2.vAttendanceStudentInfoModifyBefore();
		
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		
		ArrayList<DTO_stu_rc_oc_c_r_A> list = new ArrayList<DTO_stu_rc_oc_c_r_A>();
		
		list = dao_adminFunction2.daoAttendanceStudentInfoModify(name);
		
		view_adminFunction2.vAttendanceStudentInfoModifyAfter(list);
		
		
		dao_adminFunction2.close();
	}

	@Override
	public void sAttendanceStudentInfoModifySelect(String choice, String startDate, String endDate) {

		DAO_AdminFunction2_A dao_adminFunction2 = new DAO_AdminFunction2_A();
		
		String name = dao_adminFunction2.StudentName(choice);
		
		ArrayList<DTO_am_rc_A> list = new ArrayList<DTO_am_rc_A>();
		list = dao_adminFunction2.daoAttendanceInfo(choice, startDate, endDate);
		
		view_adminFunction2.vAttendanceStudentInfoModifySelectHead(name, startDate, endDate, list);
		
		dao_adminFunction2.close();
	}

	@Override
	public void sAttendanceStateModify() {

		Scanner scan = new Scanner(System.in);
		DAO_AdminFunction2_A dao_adminFunction2 = new DAO_AdminFunction2_A();
		
		System.out.print("수강신청 번호 : ");
		String regiNum = scan.nextLine();
		System.out.print("날짜 입력(ex 2019-01-01)");
		String date = scan.nextLine();
		System.out.println("근태 상황(정상, 지각, 조퇴, 외출, 병가, 기타) : ");
		String state = scan.nextLine();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("정말로 수정하시겠습니까?(y/n)");
		String check = scan.nextLine();
		
		if(check.equalsIgnoreCase("y")) {
			
			int result = dao_adminFunction2.daoAttendanceStateModify(regiNum, date, state);
			
			if( result == 1) {
				System.out.println("수정 완료!");
			}else if(result == 0) {
				System.out.println("수정 실패!");
			}
			
		}else if(check.equalsIgnoreCase("n")) {
			System.out.println("수정 취소!");
		}
		
		System.out.println("계속하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
		dao_adminFunction2.close();
	}

	@Override
	public void sAttendanceCourseInfo() {
		
		DAO_AdminFunction2_A dao_adminFunction2 = new DAO_AdminFunction2_A();
		
		ArrayList<DTO_c_oc_r_tc_t_A> list = new ArrayList<DTO_c_oc_r_tc_t_A>();
		list = dao_adminFunction2.daoAttendanceCourseInfo();
		
		view_adminFunction2.vAttendanceCourseInfo(list);
		
		dao_adminFunction2.close();
	}

	@Override
	public void sAttendanceCourseView(String select) {

		DAO_AdminFunction2_A dao_adminFunction2 = new DAO_AdminFunction2_A();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("- 조회기간을 입력해주세요.(ex 2019-01-01)");
		String date = scan.nextLine();
		
		ArrayList<DTO_stu_rc_oc_admg_A> list = new ArrayList<DTO_stu_rc_oc_admg_A>();
		list = dao_adminFunction2.daoAttendanceCourseView(select, date);
		
		String name = dao_adminFunction2.daoAttendanceCourseName(select);
		
		view_adminFunction2.vAttendanceCourseView(date, name, list);
				
		
		dao_adminFunction2.close();
	}

	@Override
	public void sAttendanceCourseModify(String choice) {

		DAO_AdminFunction2_A dao_adminFunction2 = new DAO_AdminFunction2_A();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("수강신청 번호 : ");
		String regiNum = scan.nextLine();
		System.out.print("날짜 입력(ex 2019-01-01) : ");
		String date = scan.nextLine();
		System.out.print("근태 상황(정상, 지각, 조퇴, 외출, 병가, 기타) : ");
		String state = scan.nextLine();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("정말로 수정하시겠습니까?(y/n)");
		String check = scan.nextLine();
		
		if(check.equalsIgnoreCase("y")) {
			
			int result = dao_adminFunction2.daoAttendanceStateModify(regiNum, date, state);
			
			if(result == 1) {
				System.out.println("수정 완료!");
			}else if(result == 0){
				System.out.println("수정 실패!");
			}
			
		}else if(check.equalsIgnoreCase("n")) {
			System.out.println("수정이 취소되었습니다.");
		}
		
		System.out.println("계속하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
		dao_adminFunction2.close();
	}

}
