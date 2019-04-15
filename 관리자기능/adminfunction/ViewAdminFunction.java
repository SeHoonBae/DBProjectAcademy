package adminfunction;

import java.util.ArrayList;

import admin2_6_7.DTO_c_oc_r_tc_t_A;
import admin2_6_7.DTO_oc_c_r_A;
import admin2_6_7.DTO_oc_osm_s_A;
import admin2_6_7.DTO_oc_rc_stu_result_A;
import admin2_6_7.DTO_request_rc_oc_c_stu_A;
import admin2_6_7.DTO_request_rc_oc_c_tc_t_stu_A;
import admin2_6_7.DTO_stu_rc_request_A;
import admin2_6_7.DTO_stu_rc_request_oc_A;
import admin2_6_7.DTO_t_tc_oc_c_A;
import admin2_6_7.DTO_teq_t_A;
import common.DTO_Course;
import common.DTO_Example;
import common.DTO_Room;
import common.DTO_Subject;
import common.DTO_TeacherEval_Q;

public class ViewAdminFunction {

	public void vOpenMgmt() {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 2. 개설 과정 및 과목 관리");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t번호\t개설과정명\t\t\t\t\t과정기간\t\t\t\t강의실명\t\t개설과목등록여부\t\t학생 등록 인원");
	}
	
	public void vOpenMgmtInfo(ArrayList<DTO_oc_c_r_A> list) {
		
		for(int i = 0; i < list.size(); i++) {
			System.out.print("\t" + list.get(i).getRownum()+".\t");
			System.out.print(list.get(i).getCourseName()+" \t\t");
			System.out.print(list.get(i).getCourseduration() + "\t\t");
			System.out.print(list.get(i).getClassName() + "\t\t\t");
			System.out.print(list.get(i).getSubjectRegistration() + "(과목수)\t\t\t");
			System.out.println(list.get(i).getStudentNum() + "명");
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println("a. 개설과정 정보 등록");
		System.out.println();
		System.out.println("z. 뒤로가기");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("- 상세보기를 원하시면 해당번호 또는 알파벳을 선택해주세요.");
		System.out.println();
		
	}

	public void vOpenMgmtInfoDetails(String courseName, String teacherName, String className, String courseDuration,
			ArrayList<DTO_oc_osm_s_A> subjectList, ArrayList<DTO_oc_rc_stu_result_A> studentList) {
		
		//1. 관리자 - 2. 개설 과정 및 과목 관리 - 1. (상세보기) 출력
		// 과정명, 교사명, 강의실명, 과정기간, 과목명 및 기간, 학생 정보 데이터를 받아옴

		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 2. 개설 과정 및 과목 관리 - 1. (상세보기)");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("■ 과정명 : " + courseName);
		System.out.println("■ 교사명 : " + teacherName);
		System.out.println("■ 강의실명 : " + className);
		System.out.println();
		System.out.println("■ 과정기간");
		System.out.println(courseDuration);
		System.out.println();
		System.out.println("■ 과목명 및 기간");
		
		for(int i = 0; i < subjectList.size(); i++) {
			System.out.println( (i+1) + ". " + subjectList.get(i).getSubjectName()+ " " + subjectList.get(i).getSubjectDuration());
		}
		
		System.out.println();
		System.out.println("■ 학생 정보");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t이름\t주민번호 뒷자리\t\t전화번호\t\t\t\t등록일\t\t\t수료여부");
		
		for(int i = 0; i < studentList.size(); i++) {
			
			System.out.println("\t"+studentList.get(i).getStudentName() + "\t" + studentList.get(i).getStudentPw() + "\t\t\t" + studentList.get(i).getStudentTel() + "\t\t\t" + studentList.get(i).getStudentRegidate() + "\t\t" + studentList.get(i).getCompletion());
			
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		System.out.println("a. 개설과목 정보 등록");
		System.out.println("z. 이전으로 돌아가기");
		
	}

	public void vOpenMgmtSubjectRegistration(ArrayList<DTO_Subject> list) {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 2. 개설 과정 및 과목 관리 - 1. (상세보기) - a. 개설과목 정보 등록");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("과목번호\t\t과목명\t\t\t\t\t\t\t과목기간");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getSubject_seq()+".\t\t" + list.get(i).getName() + "\t\t\t\t\t" + list.get(i).getPeriod() + "일");
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}

	public void vCourse(ArrayList<DTO_Course> list) {

		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("\t\t 1. 관리자 - 2. 개설 과정 및 과목 관리 - a. 개설과정 정보 등록");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("번호\t과정명\t\t\t\t\t\t\t과정기간");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCourse_seq() + ".\t" + list.get(i).getName() + "\t\t\t" + list.get(i).getPeriod() + "일");
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}

	public void vAvailClassName(ArrayList<DTO_Room> roomList) {

		System.out.println();
		System.out.println("등록 가능한 강의실 명");
		System.out.println("번호\t강의실명");
		for(int i = 0; i < roomList.size(); i++) {
			System.out.println(roomList.get(i).getRoom_seq() + ".\t" + roomList.get(i).getRoomName());
		}
		
	}

	public void vTeacherEval() {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("a. 질문지 등록");
		System.out.println("b. 질문지 수정 및 삭제");
		System.out.println("c. 교사 평가 결과 조회 및 삭제");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vTeacherEvalQuestionRegister() {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - a. 질문지 등록");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t질문\t\t\t\t\t유형");
		System.out.println();
		System.out.println("\tEX)좋았던 점\t\t\t\t1.주관식/2.객관식");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}

	public void vTeacherEvalQuestionModifyDelete(ArrayList<DTO_teq_t_A> list) {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - b. 질문지 수정 및 삭제");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("\t질문\t\t\t\t\t유형");
		System.out.println();
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i).getEvalq_seq() + ".\t" + list.get(i).getQuestionnaire() + "\t\t\t\t\t" + list.get(i).getQuestionType());
			
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("■ 질문지 수정 및 삭제를 원하시면 해당 번호를 선택해 주세요.");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vTeacherEvalQuestionModifyDelete(DTO_teq_t_A teq_t) {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - b. 질문지 수정 및 삭제 - 번호 선택");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("\t질문\t\t\t\t\t유형");
		System.out.println();
		System.out.println(teq_t.getQuestionnaire() + "\t\t\t" + teq_t.getQuestionType());
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("1. 수정하기");
		System.out.println("2. 삭제하기");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vTeacherEvalQuestionModify(DTO_teq_t_A teq_t) {

		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - b. 질문지 수정 및 삭제 - 1. 수정하기");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("\t질문\t\t\t\t\t유형");
		System.out.println();
		System.out.println(teq_t.getQuestionnaire() + "\t\t\t" + teq_t.getQuestionType());
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
	}

	public void vTeacherEvalQuestionDelete(DTO_teq_t_A teq_t) {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - b. 질문지 수정 및 삭제 - 2. 삭제하기");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("\t질문\t\t\t\t\t유형");
		System.out.println();
		System.out.println(teq_t.getQuestionnaire() + "\t\t\t" + teq_t.getQuestionType());
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		
	}

	public void vTeacherEvalQuestionResultInfoDelete(ArrayList<DTO_t_tc_oc_c_A> list) {

		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - c. 교사 평가 결과 조회 및 삭제");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("번호\t과정명\t\t\t\t\t\t교사명\t평가답변(개수)");
		
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i).getOpenCourse_seq() + ".\t" + list.get(i).getCourseName() + "\t\t" + list.get(i).getTeacherName() + "\t" + list.get(i).getAnswerVariables() + "개");
			
		}
		
		System.out.println("z. 뒤로가기");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("- 상세보기를 원하시면 해당번호를 선택해주세요.");
		
		
	}

	public void vTeacherEvalQuestionResultInfoDeleteInfo(String courseName, String teacherName, ArrayList<DTO_Example> objectiveFormlist, ArrayList<DTO_TeacherEval_Q> objectiveFormResultlist, ArrayList<DTO_TeacherEval_Q> subjectiveEx) {

		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 6. 교사 평가 관리 및 결과 조회 - c. 교사 평가 결과 조회 및 삭제 - 1. (상세보기)");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("■ " + courseName);
		System.out.println("■ " + teacherName);
		System.out.println();
		System.out.print("\t\t\t평가질문\t\t\t\t");
		
		for(int i = 0; i < objectiveFormlist.size(); i++) {
			System.out.print(objectiveFormlist.get(i).getContent() + "\t");
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < objectiveFormResultlist.size(); i++) {
			System.out.println(objectiveFormResultlist.get(i).getQuestion() + "\t\t"  + objectiveFormResultlist.get(i).getRatio1() + "%\t" + objectiveFormResultlist.get(i).getRatio2() + "%\t" + objectiveFormResultlist.get(i).getRatio3() + "%\t" + objectiveFormResultlist.get(i).getRatio4() + "%\t" + objectiveFormResultlist.get(i).getRatio5() + "%");
		}
		
		for(int i = 0; i < subjectiveEx.size(); i++) {
			
			System.out.println(subjectiveEx.get(i).getEvalQ_seq() + "." + subjectiveEx.get(i).getQuestion());
			
		}
		
		System.out.println("z. 뒤로가기");
		
		
	}

	public void vConsult() {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("a. 상담 요청 내역");
		System.out.println("b. 상담 일지 내역");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vConsultRequestInfo() {
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("a. 일괄 조회 및 수정");
		System.out.println("b. 과정별 조회 및 수정");
		System.out.println("c. 학생별 조회 및 수정");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vConsultRequestInfoModify(ArrayList<DTO_request_rc_oc_c_tc_t_stu_A> list) {
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - a. 일괄 조회 및 수정");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("번호\t학생명\t교사명\t과정명\t\t\t\t\t연락처\t\t\t학과\t\t상담요청일시\t\t상담요청 내용(간략)");
		
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i).getRownum() + ".\t" + list.get(i).getStudentName() + "\t" + list.get(i).getTeacherName() + "\t" + list.get(i).getCourseName() + "\t" + list.get(i).getStudentTel() + "\t" + list.get(i).getStudentMajor() + "\t" + list.get(i).getCallRequestDate() + "\t" + list.get(i).getCallRequestDetails());
			
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("a. 수정하기");
		System.out.println("b. 삭제하기");
		System.out.println();
		System.out.println("z. 뒤로가기");
		
		
	}

	public void vConsultRequestCourse(ArrayList<DTO_c_oc_r_tc_t_A> list) {

		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - a. 일괄 조회 및 수정");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("번호\t과정명\t\t\t과정기간\t교사명\t강의실명");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getRownum() + ".\t" + list.get(i).getClassName() + "\t" + list.get(i).getCoureseDuration() + "\t" + list.get(i).getTeacherName() + "\t" + list.get(i).getClassName());
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		System.out.println("■ 조회 및 수정을 원하시면 해당 번호를 선택해주세요.");
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vConsultRequestCourseSelect(String teacherName, String courseName,
			ArrayList<DTO_stu_rc_request_oc_A> list) {

		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - b. 일괄 조회 및 수정");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.println("■ " + teacherName + "(교사)에게 신청된 상담 요청 내역입니다.");
		System.out.println("■ " + courseName);
		System.out.println();
		System.out.println("요청번호\t학생명\t연락처\t\t\t학과\t\t상담요청일\t\t\t상담요청 내용(간략)");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getRownum() + ".\t" + list.get(i).getStudentName() + "\t" + list.get(i).getStudentTel() + "\t" + list.get(i).getStudentMajor() + "\t" + list.get(i).getCallRequestDate() + "\t" + list.get(i).getCallrequestDetails());
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("a. 수정하기");
		System.out.println("b. 삭제하기");
		System.out.println();
		System.out.println("z. 뒤로가기");
		
		
	}

	public void vConsultStudent() {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 - c. 학생별 조회 및 수정");
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("■ 검색 조건");
		System.out.println();
		System.out.println("a. 학생명");
		System.out.println("b. 주민번호뒷자리");
		System.out.println("c. 전화번호");
		System.out.println("d. 등록일");
		System.out.println("e. 학과");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	//1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정 – 검색 – 조회 및 수정
	public void vConsultRequestStudentName() {
		
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 -c. 학생별 조회 및 수정 - 검색, 조회 및 수정");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.print("학생명 : ");
		
	}

	public void vConsultRequestStudentNameAfter(ArrayList<DTO_request_rc_oc_c_stu_A> list) {
		// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정 – 검색 – 조회 및 수정 뒷부분 출력
		
		System.out.println("요청번호\t학생명\t연락처\t\t학과\t\t상담요청일\t\t\t상담요청내용");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%s\t\t%s\t%s\t%s\t%s\t%s\n"
							,list.get(i).getconsult_Seq()
							,list.get(i).getStudentName()
							,list.get(i).getStudentTel()
							,list.get(i).getStudentMajor()
							,list.get(i).getCallrequestDate()
							,list.get(i).getCallrequestDetails()
							);
		}
		
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("a. 수정하기");
		System.out.println("b. 삭제하기");
		System.out.println();
		System.out.println("z. 뒤로가기");
		
	}

	public void vConsultRequestStudenPw() {
		System.out.println("=====================================================================================================================================================================================");
		System.out.println("\t\t 1. 관리자 - 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 -c. 학생별 조회 및 수정 - 검색, 조회 및 수정");
		System.out.println("=====================================================================================================================================================================================");
		
		System.out.print("주민번호 뒷자리 : ");
		
	}

	public void vConsultRequestStudentPwAfter(ArrayList<DTO_request_rc_oc_c_stu_A> list) {
		// 1. 관리자 – 7. 상담일지 관리 및 조회 - a. 상담 요청 내역 – c. 학생별 조회 및 수정 – 검색 – 조회 및 수정 뒷부분 출력
		
				System.out.println("요청번호\t학생명\t연락처\t\t학과\t\t상담요청일\t\t\t상담요청내용");
				
				for(int i = 0; i < list.size(); i++) {
					System.out.printf("%s\t\t%s\t%s\t%s\t%s\t%s\n"
									,list.get(i).getconsult_Seq()
									,list.get(i).getStudentName()
									,list.get(i).getStudentTel()
									,list.get(i).getStudentMajor()
									,list.get(i).getCallrequestDate()
									,list.get(i).getCallrequestDetails()
									);
				}
				
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("a. 수정하기");
				System.out.println("b. 삭제하기");
				System.out.println();
				System.out.println("z. 뒤로가기");
		
	}

	

	
	
}
