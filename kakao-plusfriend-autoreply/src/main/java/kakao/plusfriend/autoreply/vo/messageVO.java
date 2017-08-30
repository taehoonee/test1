package kakao.plusfriend.autoreply.vo;

import kakao.plusfriend.autoreply.vo.buttonVO;
import kakao.plusfriend.autoreply.vo.photoVO;

public class messageVO {
	private String text;
	private photoVO photo;
	private buttonVO button;
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public photoVO getPhoto() {
		return photo;
	}

	public void setPhoto(photoVO photo) {
		this.photo = photo;
	}

	public buttonVO getButton() {
		return button;
	}

	public void setButton(buttonVO button) {
		this.button = button;
	}
}
