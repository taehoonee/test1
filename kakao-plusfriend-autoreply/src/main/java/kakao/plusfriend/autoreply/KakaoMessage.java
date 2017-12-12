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
		
		if ( "text".equals(type) ) {
			return text(vo);
		} else {
			return photo(vo);
		}
	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private messageVO text(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		Random random = new Random();
		String content = vo.getContent();
		
		/** 커멘드 */
		if (content.startsWith("/")) 
		{
			vo.setContent((content.indexOf(' ') != -1) ? content.substring(0, content.indexOf(' ')) : content);
		}
		
		List<Object> data = dao.selectRegularContent(vo);
		if (data.size() > 0) {
			Map<String, Object> map = (Map)data.get(random.nextInt(data.size()));
			String type = map.get("type").toString().toLowerCase();
			
			/** 커멘드 */
			if ("c".equals(type)) {
				String classpath = map.get("classpath").toString();
				String method = map.get("method").toString();
				
				
				try {
					/*
					 HttpServletRequest request = 
					((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			
			WebApplicationContext context = 
					WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			
			Object been = context.getBean("kakaoCommand");
			Class ResourceClass = been.getClass();
					 */
					
					Class ResourceClass = Class.forName(classpath);
					Class[] parameterType = new Class[]{String.class};
					
					Object instance = ResourceClass.newInstance();
					Method exec = ResourceClass.getDeclaredMethod(method, parameterType);
					
					messagevo = (messageVO)exec.invoke(instance, new Object[]{content});
				} catch (Exception e) {
					log.error(e.getLocalizedMessage(), e);
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
		
		return messagevo;
	}
	
	messageVO photo(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		return messagevo;
	}	
}
