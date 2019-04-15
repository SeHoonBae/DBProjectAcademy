package adminfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import admin2_6_7.DTO_c_oc_r_tc_t_A;
import admin2_6_7.DTO_oc_c_r_A;
import admin2_6_7.DTO_oc_osm_s_A;
import admin2_6_7.DTO_oc_rc_stu_result_A;
import admin2_6_7.DTO_request_rc_oc_c_stu_A;
import admin2_6_7.DTO_request_rc_oc_c_tc_t_stu_A;
import admin2_6_7.DTO_stu_rc_request_oc_A;
import admin2_6_7.DTO_t_tc_oc_c_A;
import admin2_6_7.DTO_teq_t_A;
import adminmain.DBUtil;
import common.DTO_Course;
import common.DTO_Example;
import common.DTO_OpenCourse;
import common.DTO_Room;
import common.DTO_Subject;
import common.DTO_TeacherEval_Q;

public class DAO_AdminFunction {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	
	// 초기화(선행 작업)
	// DB 접속
	public DAO_AdminFunction() {
		
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
	
	public ArrayList<DTO_oc_c_r_A> daoOpenMgmtInfo() {
		
		DTO_oc_c_r_A oc_c_r = new DTO_oc_c_r_A();
		
		ArrayList<DTO_OpenCourse> ifList = new ArrayList<DTO_OpenCourse>();
		
		String sql = "select course_seq from tblOpenCourse";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_OpenCourse oc = new DTO_OpenCourse();
				oc.setCourse_seq(rs.getString("Course_seq"));
				
				ifList.add(oc);
				
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction_daoOpenMgmtInfo() 오류");
			e.printStackTrace();
		}
		
		
		
		ArrayList<DTO_oc_c_r_A> list = new ArrayList<DTO_oc_c_r_A>();
		
		for(int i = 0; i < ifList.size(); i++) {
		
			sql = "SELECT rownum, c.name as courseName, oc.startDate || '~' || oc.endDate as courseduration, r.roomName as className, (SELECT count(*) FROM tblOpenCourse oc INNER JOIN tblOpenSubjectMgmt osm ON oc.openCourse_seq = osm.openCourse_seq WHERE osm.openCourse_seq = " + ifList.get(i).getCourse_seq() + ") as subjectRegistration, (SELECT count(*) FROM tblOpenCourse oc INNER JOIN tblRegiCourse rc ON oc.openCourse_seq = rc.openCourse_seq INNER JOIN tblStudent s ON s.student_seq = rc.student_seq WHERE oc.openCourse_seq = " + ifList.get(i).getCourse_seq() + ") as studentNum FROM tblOpenCourse oc INNER JOIN tblCourse c ON oc.course_seq = c.course_seq INNER JOIN tblRoom r ON oc.room_seq = r.room_seq";
			
			try {
				
				ResultSet rs = stat.executeQuery(sql);
				
				while(rs.next()) {
					
					DTO_oc_c_r_A dto_oc_c_r = new DTO_oc_c_r_A();
					dto_oc_c_r.setRownum(rs.getString("rownum"));
					dto_oc_c_r.setCourseName(rs.getString("courseName"));
					dto_oc_c_r.setCourseduration(rs.getString("courseduration"));
					dto_oc_c_r.setClassName(rs.getString("className"));
					dto_oc_c_r.setSubjectRegistration(rs.getString("subjectRegistration"));
					dto_oc_c_r.setStudentNum(rs.getString("studentNum"));
					
					list.add(dto_oc_c_r);
					
				}
				
				return list;
				
			} catch (SQLException e) {
				System.out.println("DAO_AdminFunction_daoOpenMgmtInfo() 오류");
				e.printStackTrace();
			}
			
		
		}
		return null;
	}
	
	public ArrayList<DTO_OpenCourse> daoOpenMgmtInfoNum(){
		
		ArrayList<DTO_OpenCourse> list = new ArrayList<DTO_OpenCourse>();
		
		String sql = "SELECT openCourse_seq FROM tblOpenCourse";
		
		try {
			
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_OpenCourse oc = new DTO_OpenCourse();
				
				oc.setCourse_seq(rs.getString("opencourse_seq"));
				
				list.add(oc);
				
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtInfoNum() 오류!");
			e.printStackTrace();
		}
		
		return null;
		
	}

	public String daoOpenMgmtCourseName(int num) {
		
		String sql = "SELECT c.name as courseName FROM tblOpenCourse oc INNER JOIN tblCourse c ON oc.course_seq = c.course_seq WHERE oc.openCourse_seq = " + num;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("courseName");
				
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtCourseName(int num)  오류!");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public String daoOpenMgmtTeacherName(int num) {
		
		String sql = "SELECT t.name as teacherName FROM tblOpenCourse oc INNER JOIN tblTeacherCourse tc ON oc.openCourse_seq = tc.openCourse_seq INNER JOIN tblTeacher t ON tc.teacher_seq = t.teacher_seq WHERE oc.openCourse_seq = " + num;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("teacherName");
				
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtTeacherName(int num)   오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public String daoOpenMgmtClassName(int num) {
		
		String sql = "SELECT r.roomName as className FROM tblOpenCourse oc INNER JOIN tblRoom r ON oc.room_seq = r.room_seq WHERE oc.openCourse_seq = " + num;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("className");
				
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtCourseNameClassName(int num) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public String daoOpenMgmtcourseDuration(int num) {

		String sql = "SELECT startDate || '~' || endDate as courseDuration FROM tblOpenCourse WHERE openCourse_seq =" + num;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("courseDuration");
				
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtcourseDuration(int num) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_oc_osm_s_A> daoOpenMgmtSubjectInfo(int num) {
		
		ArrayList<DTO_oc_osm_s_A> list = new ArrayList<DTO_oc_osm_s_A>();
		
		String sql = "SELECT s.name as subjectName, s.period || '일' as subjectDuration FROM tblOpenCourse oc INNER JOIN tblOpenSubjectMgmt osm ON oc.openCourse_seq = osm.openCourse_seq INNER JOIN tblSubject s ON osm.subject_seq = s.subject_seq WHERE oc.openCourse_seq = " + num;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_oc_osm_s_A oc_osm_s = new DTO_oc_osm_s_A();
				
				oc_osm_s.setSubjectName(rs.getString("subjectName"));
				oc_osm_s.setSubjectDuration(rs.getString("subjectDuration"));
				
				list.add(oc_osm_s);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtSubjectInfo(int num) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_oc_rc_stu_result_A> daoOpenMgmtstudentInfo(int num) {

		ArrayList<DTO_oc_rc_stu_result_A> list = new ArrayList<DTO_oc_rc_stu_result_A>();
		
		String sql = "SELECT s.name as studentName, s.pw as studentPw, s.tel as studentTel, s.regiDate as studentRegidate, r.state as completion FROM tblOpenCourse oc INNER JOIN tblRegiCourse rc ON oc.openCourse_seq = rc.openCourse_seq INNER JOIN tblStudent s ON rc.student_seq = s.student_seq INNER JOIN tblResult r ON rc.regiCourse_seq = r.regiCourse_seq where oc.openCourse_seq = " + num;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_oc_rc_stu_result_A oc_rc_stu_result = new DTO_oc_rc_stu_result_A();
				
				oc_rc_stu_result.setStudentName(rs.getString("studentName"));
				oc_rc_stu_result.setStudentPw(rs.getString("studentPw"));
				oc_rc_stu_result.setStudentTel(rs.getString("studentTel"));
				oc_rc_stu_result.setStudentRegidate(rs.getString("studentRegidate"));
				oc_rc_stu_result.setCompletion(rs.getString("completion"));
				
				list.add(oc_rc_stu_result);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtstudentInfo(int num) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_Subject> daoOpenMgmtSubjectRegistration() {

		ArrayList<DTO_Subject> list = new ArrayList<DTO_Subject>();
		
		String sql = "select subject_seq, name, period from tblSubject";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_Subject s = new DTO_Subject();
				
				s.setSubject_seq(rs.getString("subject_seq"));
				s.setName(rs.getString("name"));
				s.setPeriod(rs.getString("period"));
				
				list.add(s);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenMgmtSubjectRegistration() 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public int daoOpenSubjectAdd(int num, String startDate, String endDate, int selectNum) {
		
		String sql = "INSERT INTO tblOpenSubjectMgmt  VALUES(OpenSubjectMgmt_seq.nextval,to_date('" + startDate + "', 'yyyy-mm-dd'), to_date('" + endDate + "', 'yyyy-mm-dd'), " + num + ", " + selectNum + ")";
		
		String sql2 = "update tblOpenCourse Set endDate = to_date(' " + endDate + "','yyyy-mm-dd') where opencourse_seq = " + selectNum;
		
		try {
			int result = stat.executeUpdate(sql);
			stat.executeUpdate(sql2);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenSubjectAdd(int num, String startDate, String endDate, int selectNum) 오류!");
			e.printStackTrace();
		}
		return 0;
		
	}

	public ArrayList<DTO_Course> daoSubject() {

		ArrayList<DTO_Course> list = new ArrayList<DTO_Course>();
		
		String sql = "select course_seq, name, period from tblCourse";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_Course course = new DTO_Course();
				
				course.setCourse_seq(rs.getString("course_seq"));
				course.setName(rs.getString("name"));
				course.setPeriod(rs.getString("period"));
				
				list.add(course);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoSubject() 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_Room> daoAvailClassName(String startDate, String endDate) {

		ArrayList<DTO_Room> list = new ArrayList<DTO_Room>();
		
		String sql = "SELECT room_seq, roomName FROM tblRoom WHERE not room_seq in (SELECT r.room_seq FROM tblOpenCourse oc INNER JOIN tblRoom r ON oc.room_seq = r.room_seq WHERE to_date('" + startDate + "', 'yyyy-mm-dd') between oc.startDate and oc.endDate or to_date('" + endDate + "', 'yyyy-mm-dd') between oc.startDate and oc.endDate) order by room_seq";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_Room room = new DTO_Room();
				
				room.setRoom_seq(rs.getString("room_seq"));
				room.setRoomName(rs.getString("roomName"));
				
				list.add(room);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoAvailClassName(String startDate, String endDate) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public int daoOpenCourseAdd(String startDate, String endDate, int num, int classNum) {
		
		String sql = "INSERT INTO tblOpenCourse VALUES(OpenCourse_seq.nextval, to_date('" + startDate + "','yyyy-mm-dd'), to_date('" + endDate +"','yyyy-mm-dd'), "+ num +", "+ classNum +")";
		
		try {
			
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenCourseAdd(String startDate, String endDate, int num, int classNum) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int daoTeacherEvalQuestionRegister(String question, int typeNum) {
		
		String sql = "INSERT INTO tblTeacherEval_Q VALUES (TeacherEval_Q.nextval, '" + question + "', " + typeNum + ")";
		
		try {
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoTeacherEvalQuestionRegister(String question, int typeNum) 오류!");
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<DTO_teq_t_A> daoTeacherEvalQuestionModifyDelete() {
		
		ArrayList<DTO_teq_t_A> list = new ArrayList<DTO_teq_t_A>();
		
String sql = "SELECT teq.evalq_seq as seq, teq.question as questionnaire, t.type as questionType FROM tblTeacherEval_Q teq INNER JOIN tblType t ON teq.type_seq = t.type_seq ORDER BY teq.evalQ_seq";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_teq_t_A teq_t = new DTO_teq_t_A();
				
				teq_t.setEvalq_seq(rs.getString("seq"));
				teq_t.setQuestionnaire(rs.getString("questionnaire"));
				teq_t.setQuestionType(rs.getString("questionType"));
				
				list.add(teq_t);
				
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoTeacherEvalQuestionModifyDelete() 오류!");
			e.printStackTrace();
		}
		
		return null;
		
	}

	public DTO_teq_t_A daossTeacherEvalQuestionModifyDeleteNumber(int choiceNum) {
		
		DTO_teq_t_A teq_t = new DTO_teq_t_A();
		
		String sql = "SELECT teq.evalq_seq as seq, teq.question as questionnaire, t.type as questionType FROM tblTeacherEval_Q teq INNER JOIN tblType t ON teq.type_seq = t.type_seq WHERE evalQ_seq = " + choiceNum;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				teq_t.setEvalq_seq(rs.getString("seq"));
				teq_t.setQuestionnaire(rs.getString("questionnaire"));
				teq_t.setQuestionType(rs.getString("questionType"));
			}
			
			return teq_t;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daossTeacherEvalQuestionModifyDeleteNumber(int choiceNum) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public int daoTeacherEvalQuestionModify(String question, int typeNum, int choiceNum) {
		
		
		
		String sql = String.format("UPDATE tblTeacherEval_Q SET question = '%s', type_seq = %d where evalq_seq = %d", question, typeNum, choiceNum);
		
		try {
			
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스  daoTeacherEvalQuestionModify(String question, int choiceNum) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int daoTeacherEvalQuestionDelete(int choiceNum) {
		
		String sql = String.format("DELETE FROM tblTeacherEval_Q\r\n" + 
				"    WHERE evalQ_seq = %d",  choiceNum);
		
		try {
			
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스  daoTeacherEvalQuestionModify(String question, int choiceNum) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int daoOpenCourseNum() {
		
		String sql = "SELECT COUNT(*) as numberOpenCourse FROM tblOpenCourse";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("numberOpenCourse"));
				
				return cnt;
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoOpenCourseNum() 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<DTO_t_tc_oc_c_A> daoTeacherEvalQuestionResultInfoDelete(int cnt) {

		ArrayList<DTO_t_tc_oc_c_A> list = new ArrayList<DTO_t_tc_oc_c_A>();
		
		System.out.println("cnt : " + cnt);
		
		for(int i = 0; i < cnt; i++) {
		
		String sql = String.format("select oc.opencourse_seq as openCourseNum, c.name as courseName, t.name as teacherName, (SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq where rc.openCourse_seq = %d) as answerVariables from tblteacher t inner join tblteachercourse tc on t.teacher_seq = tc.teacher_seq inner join tblopencourse oc on oc.opencourse_seq = tc.opencourse_seq inner join tblcourse c on c.course_seq = oc.course_seq where oc.opencourse_seq = %d", i+1, i+1);
		
			try {
				ResultSet rs = stat.executeQuery(sql);
				
				while(rs.next()) {
					
					DTO_t_tc_oc_c_A t_tc_oc_c = new DTO_t_tc_oc_c_A();
					
					t_tc_oc_c.setOpenCourse_seq(rs.getString("openCourseNum"));
					t_tc_oc_c.setCourseName(rs.getString("courseName"));
					t_tc_oc_c.setTeacherName(rs.getString("teacherName"));
					t_tc_oc_c.setAnswerVariables(rs.getString("answerVariables"));
					
					list.add(t_tc_oc_c);
					
				}
				
			} catch (SQLException e) {
				System.out.println("DAO_AdminFunction클래스 daoTeacherEvalQuestionResultInfoDelete(int cnt) 오류!");
				e.printStackTrace();
			}
		
		}
		
		return list;
	}

	public String daoCourseName(String select) {

		String sql = "SELECT c.name as courseName FROM tblOpenCourse oc INNER JOIN tblCourse c ON oc.course_seq = c.course_seq WHERE oc.openCourse_seq = " + select;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				String courseName = rs.getString("courseName");
				
				return courseName;
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoCourseName(String select) 오류!");
			e.printStackTrace();
		}
		
		return null;
		
	}

	public String daoTeacherName(String select) {

		String sql = "SELECT t.name as teacherName FROM tblOpenCourse oc INNER JOIN tblCourse c ON oc.course_seq = c.course_seq INNER JOIN tblTeacherCourse tc ON oc.openCourse_seq = tc.openCourse_seq INNER JOIN tblTeacher t ON tc.teacher_seq = t.teacher_seq WHERE oc.openCourse_seq = " + select;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				String teacherName = rs.getString("teacherName");
				
				return teacherName;
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoTeacherName(String select) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_Example> daoobjectiveFormEx() { // 객관식 질문 한줄로 출력
		
		ArrayList <DTO_Example> objectiveFormlist = new ArrayList<DTO_Example>();
		
		String sql = "SELECT content FROM tblExample";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_Example example = new DTO_Example();
				
				example.setContent(rs.getString("content"));
				
				objectiveFormlist.add(example);
				
			}
			
			return objectiveFormlist;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoobjectiveFormEx() 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_TeacherEval_Q> daoObjectiveList(String select) {
		
		ArrayList <DTO_TeacherEval_Q> objectiveFormResultlist = new ArrayList<DTO_TeacherEval_Q>();
		
		int i = 1;
			
			String sql = String.format("SELECT teqfirst.evalq_seq, teqfirst.question as questionnaire , (SELECT round((SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq WHERE tea.objective_A = 1 and rc.openCourse_seq = %s and teq.evalq_seq = %d) /(SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq where rc.openCourse_seq = %s and teq.type_seq = 2 and teq.evalq_seq = %d),3)*100 FROM dual) as ratio1 ,(SELECT round((SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq WHERE tea.objective_A = 2 and rc.openCourse_seq = %s and teq.evalq_seq = %d) /(SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq where rc.openCourse_seq = %s and teq.type_seq = 2 and teq.evalq_seq = %d),3)*100 FROM dual) as ratio2, (SELECT round((SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq WHERE tea.objective_A = 3 and rc.openCourse_seq = %s and teq.evalq_seq = %d) /(SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq where rc.openCourse_seq = %s and teq.type_seq = 2 and teq.evalq_seq = %d),3)*100 FROM dual) as ratio3, (SELECT round((SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq WHERE tea.objective_A = 4 and rc.openCourse_seq = %s and teq.evalq_seq = %d) /(SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq where rc.openCourse_seq = %s and teq.type_seq = 2 and teq.evalq_seq = %d),3)*100 FROM dual) as ratio4, (SELECT round((SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq WHERE tea.objective_A = 5 and rc.openCourse_seq = %s and teq.evalq_seq = %d) /(SELECT COUNT(*) FROM tblTeacherEval_A tea INNER JOIN tblTeacherEval_Q teq ON tea.evalQ_seq = teq.evalQ_seq INNER JOIN tblRegiCourse rc ON tea.regiCourse_seq = rc.regiCourse_seq where rc.openCourse_seq = %s and teq.type_seq = 2 and teq.evalq_seq = %d),3)*100 FROM dual) as ratio5 FROM tblTeacherEval_Q teqfirst WHERE teqfirst.type_seq = 2", select, i, select, i, select, (i+1), select, (i+1), select, (i+2), select, (i+2), select, (i+3), select, (i+3), select, (i+4), select, (i+5));
		
		
			try {
				ResultSet rs = stat.executeQuery(sql);
				
				while(rs.next()) {
					
					DTO_TeacherEval_Q teq = new DTO_TeacherEval_Q();
					
					teq.setQuestion(rs.getString("questionnaire"));
					teq.setRatio1(rs.getString("ratio1"));
					teq.setRatio2(rs.getString("ratio2"));
					teq.setRatio3(rs.getString("ratio3"));
					teq.setRatio4(rs.getString("ratio4"));
					teq.setRatio5(rs.getString("ratio5"));
					
					
					objectiveFormResultlist.add(teq);
					
				}
				
				return objectiveFormResultlist;
				
			} catch (SQLException e) {
				System.out.println("DAO_AdminFunction클래스 daoObjectiveList(String select) 오류!");
				e.printStackTrace();
			}
		return null;
	}

	public ArrayList<DTO_TeacherEval_Q> daoSubjectiveEx() {

		ArrayList <DTO_TeacherEval_Q> subjectiveEx = new ArrayList<DTO_TeacherEval_Q>();
		
		String sql = "select evalq_seq, question from tblTeacherEval_q where type_seq = 1";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_TeacherEval_Q teq = new DTO_TeacherEval_Q();
				
				teq.setEvalQ_seq(rs.getString("evalq_seq"));
				teq.setQuestion(rs.getString("question"));
				
				subjectiveEx.add(teq);
				
			}
			
			return subjectiveEx;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoSubjectiveEx() 오류!");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public ArrayList<DTO_request_rc_oc_c_tc_t_stu_A> daoConsultRequestInfoModify() {

ArrayList<DTO_request_rc_oc_c_tc_t_stu_A> infoList = new ArrayList<DTO_request_rc_oc_c_tc_t_stu_A>();
		
		String sql = "SELECT cr.consult_seq as seq , stu.name as studentName, t.name as teacherName, c.name as courseName, stu.tel as studentTel, stu.major as studentMajor, cr.requestDate as callRequestDate, cr.requestContent as callRequestDetails FROM tblConsultRequest cr INNER JOIN tblRegiCourse rc ON cr.regiCourse_seq = rc.regiCourse_seq INNER JOIN tblOpenCourse oc ON rc.openCourse_seq = oc.openCourse_seq INNER JOIN tblCourse c ON oc.course_seq = c.course_seq INNER JOIN tblTeacherCourse tc ON oc.openCourse_seq = tc.openCourse_seq INNER JOIN tblTeacher t ON tc.teacher_seq = t.teacher_seq INNER JOIN tblStudent stu ON rc.student_seq = stu.student_seq";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_request_rc_oc_c_tc_t_stu_A info = new DTO_request_rc_oc_c_tc_t_stu_A();
				
				info.setRownum(rs.getString("seq"));
				info.setStudentName(rs.getString("studentName"));
				info.setTeacherName(rs.getString("teacherName"));
				info.setCourseName(rs.getString("courseName"));
				info.setStudentTel(rs.getString("studentTel"));
				info.setStudentMajor(rs.getString("studentMajor"));
				info.setCallRequestDate(rs.getString("callRequestDate"));
				info.setCallRequestDetails(rs.getString("callRequestDetails"));
				
				infoList.add(info);
				
			}
			return infoList;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestInfoModify() 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public int daoConsultRequestModify(int num, String date, String content) {

		String sql = String.format("UPDATE tblConsultRequest SET requestDate = '%s', requestContent = '%s' WHERE consult_seq = %d", date, content, num);
		
		try {
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestModify(int num, String date, String content) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int daoConsultRequestDelete(int num) {

		String sql = String.format("DELETE FROM tblConsultRequest WHERE consult_seq = %d", num);
		String sql2 = String.format("delete from tblConsultRecord where consult_seq = %d", num);
		
		try {
			stat.executeUpdate(sql2);
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestDelete(int num) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<DTO_c_oc_r_tc_t_A> daoConsultRequestCourse() {

		ArrayList<DTO_c_oc_r_tc_t_A> list = new ArrayList<DTO_c_oc_r_tc_t_A>();
		
		String sql = "SELECT oc.course_seq as seq, c.name as courseName, oc.startDate || '~' || oc.endDate as coureseDuration, t.name as teacherName, r.roomName as className FROM tblCourse c INNER JOIN tblOpenCourse oc ON c.course_seq = oc.openCourse_seq INNER JOIN tblRoom r ON oc.room_seq = r.room_seq INNER JOIN tblTeacherCourse tc ON oc.openCourse_seq = tc.openCourse_seq INNER JOIN tblTeacher t ON tc.teacher_seq = t.teacher_seq";
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_c_oc_r_tc_t_A dto = new DTO_c_oc_r_tc_t_A();
				
				dto.setRownum(rs.getString("seq"));
				dto.setCoureseDuration(rs.getString("courseName"));
				dto.setCoureseDuration(rs.getString("coureseDuration"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setClassName(rs.getString("className"));
			
				list.add(dto);
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestCourse() 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public String daoConsultRequestCourseTeacherName(String select) {
		
		String sql = "SELECT t.name as teacherName FROM tblTeacher t INNER JOIN tblTeacherCourse tc ON t.teacher_seq = tc.teacher_seq INNER JOIN tblOpenCourse oc ON tc.openCourse_seq = oc.openCourse_seq WHERE oc.openCourse_seq = " + select;
		
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				String teacherName = rs.getString("teacherName");
				
				return teacherName;
			}
			
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestCourseTeacherName(String select) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public String daoConsultRequestCourseCourseName(String select) {

String sql = "SELECT c.name as courseName FROM tblCourse c INNER JOIN tblOpenCourse oc ON c.course_seq = oc.course_seq WHERE oc.openCourse_seq = " + select;
		
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				String courseName = rs.getString("courseName");
				
				return courseName;
			}
			
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestCourseCourseName(String select) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_stu_rc_request_oc_A> daoConsultRequestCourseSelect(String select) {
		String sql = "SELECT cr.consult_seq as seq , stu.name as studentName, stu.tel as studentTel, stu.major as studentMajor, cr.requestDate as callRequestDate, cr.requestContent as callrequestDetails FROM tblStudent stu INNER JOIN tblRegiCourse rc ON stu.student_seq = rc.student_seq INNER JOIN tblConsultRequest cr ON rc.regiCourse_seq = cr.regiCourse_seq INNER JOIN tblOpenCourse oc ON oc.openCourse_seq = rc.openCourse_seq WHERE oc.openCourse_seq = " + select;
		
		ArrayList<DTO_stu_rc_request_oc_A> list = new ArrayList<DTO_stu_rc_request_oc_A>();
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_stu_rc_request_oc_A dto = new DTO_stu_rc_request_oc_A();
				
				dto.setRownum(rs.getString("seq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setStudentTel(rs.getString("studentTel"));
				dto.setStudentMajor(rs.getString("studentMajor"));
				dto.setCallRequestDate(rs.getString("callRequestDate"));
				dto.setCallrequestDetails(rs.getString("callrequestDetails"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestCourseSelect(String select) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public int daoConsultRequestCourseModify(String num, String date, String content) {
		
		String sql = "UPDATE tblConsultRequest SET requestDate = '" + date + "', requestContent = '" + content + "' WHERE consult_seq = " + num;
		
		try {
			
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestCourseModify(String num, String date, String content) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int daoConsultRequestCourseDelete(String num) {

		String sql = "DELETE FROM tblConsultRequest WHERE consult_seq = " + num;
		String sql2 = "delete from tblconsultrecord where consult_seq = " +num;
		
		try {
			stat.executeUpdate(sql2);
			int result = stat.executeUpdate(sql);
			
			return result;
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestCourseDelete(String num) 오류!");
			e.printStackTrace();
		}
		
		return 0;
	}


	public String sConsultRequestStudentNameInfoCourseName(String choice) {
		
		String sql = "SELECT c.name as courseName FROM tblCourse c INNER JOIN tblOpenCourse oc ON c.course_seq = oc.course_seq INNER JOIN tblRegiCourse rc ON oc.openCourse_seq = rc.openCourse_seq INNER JOIN tblConsultRequest cr ON rc.regiCourse_seq = cr.regiCourse_seq WHERE cr.consult_seq = " + choice;
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				String courseName = rs.getString("courseName");
				return courseName;
			}
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 sConsultRequestStudentNameInfoCourseName(String choice) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_request_rc_oc_c_stu_A> daoConsultRequestStudentName(String name) {
		
String sql = "SELECT cr.consult_seq, stu.name as studentName, stu.tel as studentTel, stu.major as studentMajor, cr.requestDate as callrequestDate, cr.requestContent as callrequestDetails FROM tblConsultRequest cr INNER JOIN tblRegiCourse rc ON cr.regiCourse_seq = rc.regiCourse_seq INNER JOIN tblOpenCourse oc ON rc.openCourse_seq = oc.openCourse_seq INNER JOIN tblCourse c ON oc.course_seq = c.course_seq INNER JOIN tblStudent stu ON stu.student_seq = rc.student_seq WHERE stu.name = '" + name +"'";
		
		ArrayList<DTO_request_rc_oc_c_stu_A> list = new ArrayList<DTO_request_rc_oc_c_stu_A>();
		
		try {
			
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_request_rc_oc_c_stu_A dto = new DTO_request_rc_oc_c_stu_A();
				
				dto.setconsult_Seq(rs.getString("consult_seq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setStudentTel(rs.getString("studentTel"));
				dto.setStudentMajor(rs.getString("studentMajor"));
				dto.setCallrequestDate(rs.getString("callrequestDate"));
				dto.setCallrequestDetails(rs.getString("callrequestDetails"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestStudentName(String name) 오류!");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<DTO_request_rc_oc_c_stu_A> daoConsultRequestStudentPw(String pw) {
		
		String sql = "SELECT cr.consult_seq, stu.name as studentName, stu.tel as studentTel, stu.major as studentMajor, cr.requestDate as callrequestDate, cr.requestContent as callrequestDetails FROM tblConsultRequest cr INNER JOIN tblRegiCourse rc ON cr.regiCourse_seq = rc.regiCourse_seq INNER JOIN tblOpenCourse oc ON rc.openCourse_seq = oc.openCourse_seq INNER JOIN tblCourse c ON oc.course_seq = c.course_seq INNER JOIN tblStudent stu ON stu.student_seq = rc.student_seq WHERE stu.pw = '" + pw +"'";
		
		ArrayList<DTO_request_rc_oc_c_stu_A> list = new ArrayList<DTO_request_rc_oc_c_stu_A>();
		
		try {
			
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				
				DTO_request_rc_oc_c_stu_A dto = new DTO_request_rc_oc_c_stu_A();
				
				dto.setconsult_Seq(rs.getString("consult_seq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setStudentTel(rs.getString("studentTel"));
				dto.setStudentMajor(rs.getString("studentMajor"));
				dto.setCallrequestDate(rs.getString("callrequestDate"));
				dto.setCallrequestDetails(rs.getString("callrequestDetails"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
			
		} catch (SQLException e) {
			System.out.println("DAO_AdminFunction클래스 daoConsultRequestStudentName(String name) 오류!");
			e.printStackTrace();
		}
		
		
		return null;
	}







	
	
	
	
}
