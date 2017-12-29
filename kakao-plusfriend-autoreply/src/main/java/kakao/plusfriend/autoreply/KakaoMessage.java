package kakao.plusfriend.autoreply;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Random;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.dao.KakaoMessageDao;
import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.messageVO;
import kakao.plusfriend.autoreply.vo.searchVO;


public class KakaoMessage {
	Logger log = Logger.getLogger(this.getClass());
	 
	@Autowired
	private KakaoMessageDao dao;
	
	public void setDao(KakaoMessageDao dao) {
		this.dao = dao;
	}

	/** 메세지 전송 */
	public messageVO responseMessage(kakaoVO vo) {
		String type = vo.getType();
		
		System.out.println("type : " + type);
		if ( "text".equals(type) ) {
			return text(vo);
		} else {
			return photo(vo);
		}
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private messageVO text(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		searchVO searchvo = new searchVO(); 
		Random random = new Random();
		String content = vo.getContent();
		
		try {
		/** 커멘드 */
		if (content.startsWith("/")) 
		{
			
			searchvo.setContent((content.indexOf(' ') != -1) ? content.substring(1, content.indexOf(' ')) : content);
			searchvo.setType("C");
		}
		else 
		{
			searchvo.setType("T");
		}
		
		List<Object> data = dao.selectRegularContent(searchvo);
		
		if (data.size() > 0) {
			Map<String, Object> map = (Map)data.get(random.nextInt(data.size()));
			String type = map.get("type").toString().toUpperCase();
			
			/** 커멘드 */
			if ("C".equals(type)) {
				String classpath = map.get("classpath").toString();
				String method = map.get("method").toString();
				
				
				try {
					Class ResourceClass = Class.forName(classpath);
					Class[] parameterType = new Class[]{String.class};
					
					Object instance = ResourceClass.newInstance();
					Method exec = ResourceClass.getDeclaredMethod(method, parameterType);
					
					messagevo = (messageVO)exec.invoke(instance, new Object[]{content});
				} catch (Exception e) {
					messagevo.getMessage().setText("Server Error : " + e.getLocalizedMessage());
					log.error(e.getLocalizedMessage(), e);
					e.printStackTrace();
				}
						
			}
			/** 텍스트 */
			else 
			{
				messagevo.getMessage().setText(map.get("content").toString());
			}
			
		} else {
			messagevo.getMessage().setText("미안하구나...그대가 말하는게 뭔지 모르겠느니라.");
		}
		} catch (Exception e) {
			messagevo.getMessage().setText("Server Error : " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return messagevo;
	}
	
	messageVO photo(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		messagevo.getMessage().setText("준비중");
		return messagevo;
	}	
}
