package adminfunction;

public interface IService_AdminFunction {

	void sOpenMgmtInfo();
	int sOpenMgmtInfoDetails(int num);
	void sOCSmgmtdetails(int num);
	void sOpenMgmtSubjectRegistration(int selectNum);
	void sOpenMgmtAdd();
	void sTeacherEvalQuestionRegister();
	void sTeacherEvalQuestionModifyDelete();
	void sTeacherEvalQuestionModifyDeleteNumber(int choiceNum);
	void sTeacherEvalQuestionModify(int choiceNum);
	void sTeacherEvalQuestionDelete(int choiceNum);
	void sTeacherEvalQuestionResultInfoDelete();
	void sTeacherEvalQuestionResultInfoDeleteInfo(String select);
	void sConsultRequestInfoModify();
	void sConsultRequestModify();
	void sConsultRequestDelete();
	void sConsultRequestCourse();
	void sConsultRequestCourseSelect(String select);
	void sConsultRequestCourseModify();
	void sConsultRequestCourseDelete();
	void sConsultRequestStudentName(String name);
	void sConsultRequestStudentPw(String pw);

	
	
}
