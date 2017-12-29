package kakao.plusfriend.autoreply;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.keyboardVO;
import kakao.plusfriend.autoreply.vo.messageVO;

/** 카카오에서 넘어온 메세지를 변경한다. */
@Controller
public class KakaoMessageDecoProcess {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	KakaoMessage kakaoMsg;
	public void setKakaoMsg(KakaoMessage kakaoMsg) {
		this.kakaoMsg = kakaoMsg;
	}


	/** Home Keyboard API 
	 * <pre>
	 * Method : GET
	 * URL : http(s)://:your_server_url/keyboard
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public @ResponseBody keyboardVO keyboard() {
		log.info("URL : /keyboard, Type : text");
		
		return new keyboardVO("text");
	}

	
	/** 메시지 수신 및 자동응답 API
	 * <pre>
	 * Method : POST
	 * URL : http(s)://:your_server_url/message
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	@RequestMapping(value = "/message" , method = RequestMethod.POST)
	public @ResponseBody messageVO message(@RequestBody kakaoVO vo) {
		//log.info("map vo : " + vo);
		log.info("URL : /message, Content : " + vo.getContent() + ", Key : " + vo.getUser_key() );
		//return kakaoMsg.responseMessage(vo);
		return kakaoMsg.responseMessage(vo);
	}

	
	/** 친구 추가/차단 알림 API
	 * <pre>
	 * Method : POST / DELETE
	 * URL : http(s)://:your_server_url/friend
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	@RequestMapping(value = {"/friend", "/friend/{user_key}"}, method = {RequestMethod.POST, RequestMethod.DELETE})
	public @ResponseBody void friend(@PathVariable String user_key, /*@RequestBody*/ kakaoVO vo) {
		log.info("URL : /friend, Key : " + user_key);
	}

	
	/** 채팅방 나가기
	 * <pre>
	 * Method : DELETE
	 * URL : http(s)://:your_server_url/chat_room/:user_key
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	@RequestMapping(value = "/chat_room/{user_key}", method = RequestMethod.DELETE)
	public @ResponseBody void chat_room(@PathVariable String user_key) {
		log.info("URL : /chat_room, Key : " + user_key);
	}
}
