package kakao.plusfriend.autoreply;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.dao.ReplyDao;
import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.messageVO;


public class kakaoMessage {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ReplyDao dao;
	
	public void setDao(ReplyDao dao) {
		this.dao = dao;
	}

	public messageVO responseMessage(kakaoVO vo) {
		messageVO messagevo = null;
		String type = vo.getType();
		
		if ( "text".equals(type) ) {
			/* text 타입 */
			messagevo = text(vo);
		} else {
			/* photo 타입 */
			messagevo = photo(vo);
		}
		
		return messagevo;
	}

	messageVO text(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		Random random = new Random();
		
		/* text 타입 */
		log.info("===test.dao : " + dao);
		
		List<Object> data = dao.selectRegularContent(vo);
		log.info("===test.data : " + data);
		
		if (data != null) {
			log.info("===test.random.nextInt(data.size()) : " + random.nextInt(data.size()));
			
			Map<String, Object> map = (Map)data.get(random.nextInt(data.size()));
			messagevo.getMessage().setText(map.get("txt_content").toString());		
		} else {
			messagevo.getMessage().setText("미안하구나...그대가 말하는게 뭔지 모르겠느니라.");
		}
		
		return messagevo;
	}
	
	messageVO photo(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		return messagevo;
	}	
}
