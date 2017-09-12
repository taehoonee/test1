package kakao.plusfriend.autoreply;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
			
			// command형식 일 경우 command로 처리한다.
			messagevo = command(vo);
			
			
			// text형식일 경우 text로 처리한다.
			if (messagevo == null) {
				messagevo = text(vo);		
			}
			
		} else {
			/* photo 타입 */
			messagevo = photo(vo);
		}
		
		return messagevo;
	}
	
	
	messageVO command(kakaoVO vo) {
		messageVO messagevo = null;
		String content = vo.getContent();
		String command = (content.indexOf(' ') != -1) ? content.substring(0, content.indexOf(' ')) : null;
		
		Map<String, Object> resultMap = (Map<String, Object>)dao.selectRegularContent(command);
		if (resultMap != null) {
			HttpServletRequest request = 
					((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			
			WebApplicationContext context = 
					WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			
			Object been = context.getBean("kakaoCommand");
			Class kakaoCommandClass = been.getClass();
			String methodName = resultMap.get("method_name").toString();
			
			Class[] paramaterType = new Class[]{String.class};
			
			log.info("===command.method_name : " + methodName);
			
			
			try {
				Object instance = kakaoCommandClass.newInstance();
				Method method = kakaoCommandClass.getDeclaredMethod(methodName, paramaterType);
				
				messagevo = (messageVO)method.invoke(instance, new Object[]{content});
			} catch (InstantiationException e) {
				log.error("===command\t" + e.getLocalizedMessage(), e);
			} catch (IllegalAccessException e) {
				log.error("===command\t" + e.getLocalizedMessage(), e);
			} catch (NoSuchMethodException e) {
				log.error("===command\t" + e.getLocalizedMessage(), e);
			} catch (SecurityException e) {
				log.error("===command\t" + e.getLocalizedMessage(), e);
			} catch (IllegalArgumentException e) {
				log.error("===command\t" + e.getLocalizedMessage(), e);
			} catch (InvocationTargetException e) {
				log.error("===command\t" + e.getLocalizedMessage(), e);
			}
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
		
		if (data != null && data.size() > 0) {
			log.info("===test.random.nextInt(data.size()) : " + random.nextInt(data.size()));
			
			Map<String, Object> map = (Map)data.get(random.nextInt(data.size()));
			messagevo.getMessage().setText(map.get("txt_content").toString());		
		} else {
			messagevo.getMessage().setText("미안하구나...그대가 말하는게 뭔지 모르겠느니라.\n 짐에게 알려주고 싶은게 있으면,"
					+ " http://220.230.124.70/kakao-plusfriend-autoreply/main 로 들어가보라 하는구나!");
		}
		
		return messagevo;
	}
	
	messageVO photo(kakaoVO vo) {
		messageVO messagevo = new messageVO();
		return messagevo;
	}	
}
