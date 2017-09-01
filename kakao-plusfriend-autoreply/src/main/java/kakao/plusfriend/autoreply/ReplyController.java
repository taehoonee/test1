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


@Controller
public class ReplyController implements Reply{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	kakaoMessage kakaoMsg;
	
	@Override
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public @ResponseBody keyboardVO keyboard() {
		keyboardVO keyboard = new keyboardVO();
		log.info("===keyboard call");
		
		String type = "text";
		keyboard.setType(type);
		
		log.info("===type\t" + keyboard.getType());
		return keyboard;
	}

	@Override
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public @ResponseBody messageVO message(@RequestBody kakaoVO vo) {
		log.info("===message call");
		log.info("===user_key\t" + vo.getUser_key());
		log.info("===type\t" + vo.getType());
		log.info("===content\t" + vo.getContent());
		
		/*
		Class kakao_plusfriend_autoreply_Message;
		Class[] parameterTypes = { kakaoVO.class };
		Object[] paramter = { vo };
		messageVO messageVO = null;
		String methodName = vo.getType();
		
		try {
			kakao_plusfriend_autoreply_Message 
				= kakao.plusfriend.autoreply.Message.class;
			
			
			Object instance 
				= kakao_plusfriend_autoreply_Message.newInstance();
			Method method 
				= kakao_plusfriend_autoreply_Message.getMethod(methodName, parameterTypes);
			messageVO = (messageVO)method.invoke(instance, paramter);
			
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		}
		*/
		
		return kakaoMsg.responseMessage(vo);
	}

	@Override
	@RequestMapping(value = {"/friend", "/friend/{user_key}"}, method = {RequestMethod.POST, RequestMethod.DELETE})
	public @ResponseBody void friend(@PathVariable String user_key, @RequestBody kakaoVO vo) {
	}

	@Override
	@RequestMapping(value = "/chat_room/{user_key}", method = RequestMethod.DELETE)
	public @ResponseBody void chat_room(@PathVariable String user_key) {
	}

	
}
