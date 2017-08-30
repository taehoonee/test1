package kakao.plusfriend.autoreply;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.dao.ReplyDao;
import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.messageVO;

public class kakaoMessage {
	/*@Autowired
	ReplyDao dao;
	*/
	public kakaoMessage() { }
	
	
	public messageVO responseMessage(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		String type = vo.getType();
		
		if ( "text".equals(type) ) {
			/* text 타입 */
			messagevo.setText("오호~ 어서오너라!!");
			/*Map<String, Object> data = (Map<String, Object>) dao.selectRegularContent(vo);
			messagevo.setText(data.get("text_content").toString());*/
		} else {
			/* photo 타입 */
			
		}
		
		return messagevo;
	}

	/*public messageVO text(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		messagevo.setText("Hello!! This is Test");	
		return messagevo;
	}
	
	public messageVO photo(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		return messagevo;
	}*/	
}
