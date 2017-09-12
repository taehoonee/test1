package kakao.plusfriend.autoreply.cmd;

import kakao.plusfriend.autoreply.vo.messageVO;

public class kakaoCommand {
	
	/** 리스트 페이지로 이동할 수 있게 하는 커맨드 : 테스트용 */
	public messageVO list(String content) {
		messageVO meesagevo = new messageVO();
		meesagevo.getMessage().setText("하하햣! 이몸이 공부한 것들이니라!!\n 시엘이 알고싶으면 "
			+ " http://220.230.124.70/kakao-plusfriend-autoreply/list 로 들어가보라 하는구나!");
		return meesagevo;
	}
}
