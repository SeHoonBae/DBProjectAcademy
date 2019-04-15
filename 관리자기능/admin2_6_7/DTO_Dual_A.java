package admin2_6_7;

public class DTO_Dual_A {

	private String ratio; // 객관식 답변 평가비율
	private String timeIf; // 등록할 때 현재시간과 비교해서 미래이면 true 아니면 false

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getTimeIf() {
		return timeIf;
	}

	public void setTimeIf(String timeIf) {
		this.timeIf = timeIf;
	}
	
	
	
}
