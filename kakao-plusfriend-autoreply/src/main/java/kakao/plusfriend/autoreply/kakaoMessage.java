package kakao.plusfriend.autoreply;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.dao.ReplyDao;
import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.messageVO;


public class kakaoMessage {
	@Autowired
	ReplyDao dao;
	
	public kakaoMessage() { }
	
	
	public messageVO responseMessage(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		String type = vo.getType();
		Random random = new Random();
		
		if ( "text".equals(type) ) {
			/* text 타입 */
			//messagevo.getMessage().setText("오호~ 어서오너라!!");
			List<Object> data = dao.selectRegularContent(vo);
			
			if (data != null) {
				Map<String, Object> map = (Map)data.get(random.nextInt(data.size()));
				messagevo.getMessage().setText(map.get("text_content").toString());		
			} else {
				messagevo.getMessage().setText("미안하구나...그대가 말하는게 뭔지 모르겠느니라.");
			}
			
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
