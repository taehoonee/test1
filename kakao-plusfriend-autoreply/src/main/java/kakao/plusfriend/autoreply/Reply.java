package kakao.plusfriend.autoreply;

import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.keyboardVO;
import kakao.plusfriend.autoreply.vo.messageVO;

interface Reply {
	
	
	/** Home Keyboard API 
	 * <pre>
	 * Method : GET
	 * URL : http(s)://:your_server_url/keyboard
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	public keyboardVO keyboard();
	
	
	/** 메시지 수신 및 자동응답 API
	 * <pre>
	 * Method : POST
	 * URL : http(s)://:your_server_url/message
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	public messageVO message(kakaoVO vo);
	
	
	/** 친구 추가/차단 알림 API
	 * <pre>
	 * Method : POST / DELETE
	 * URL : http(s)://:your_server_url/friend
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	public void friend(String user_key, kakaoVO vo);
	
	
	/** 채팅방 나가기
	 * <pre>
	 * Method : DELETE
	 * URL : http(s)://:your_server_url/chat_room/:user_key
	 * Content-Type : application/json; charset=utf-8
	 * </pre>
	 */
	public void chat_room(String user_key);

}
