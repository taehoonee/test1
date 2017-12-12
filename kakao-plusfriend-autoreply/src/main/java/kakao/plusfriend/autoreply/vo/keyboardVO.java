package kakao.plusfriend.autoreply.vo;

import java.util.ArrayList;
import java.util.List;

public class keyboardVO {
	private String type;
	private List<String> buttons = new ArrayList<String>();
	
	public keyboardVO(){}
	public keyboardVO(String type) {
		this.type = type;
	}
	
	public List<String> getButtons() {
		return buttons;
	}
	public void setButtons(List<String> buttons) {
		this.buttons = buttons;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}