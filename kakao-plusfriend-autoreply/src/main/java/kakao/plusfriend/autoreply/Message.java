package kakao.plusfriend.autoreply;

import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.messageVO;

public class Message {

	public messageVO text(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		messagevo.setText("Hello!! This is Test");	
		return messagevo;
	}
	
	public messageVO photo(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		return messagevo;
	}	
}
