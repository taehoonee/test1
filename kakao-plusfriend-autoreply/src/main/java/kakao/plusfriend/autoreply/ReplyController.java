package kakao.plusfriend.autoreply;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.keyboardVO;
import kakao.plusfriend.autoreply.vo.messageVO;

@Controller
public class ReplyController implements Reply{

	@Override
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public @ResponseBody keyboardVO keyboard() {
		keyboardVO keyboard = new keyboardVO();
		
		String type = "text";
		keyboard.setType(type);
		
		ResponseEntity.status(HttpStatus.OK);
		
		return keyboard;
	}

	@Override
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public @ResponseBody messageVO message(kakaoVO vo) {
		
		Class kakao_plusfriend_autoreply_Message;
		Class[] parameterTypes = { kakaoVO.class };
		Object[] paramter = { vo };
		messageVO messageVO = null;
		String methodName = vo.getType();
		
		try {
			kakao_plusfriend_autoreply_Message 
				= Class.forName("kakao.plusfriend.autoreply.Message");
			
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return messageVO;
	}

	@Override
	@RequestMapping(value = {"/friend", "/friend/{user_key}"}, method = {RequestMethod.POST, RequestMethod.DELETE})
	public @ResponseBody void friend(@PathVariable String user_key, kakaoVO vo) {
	}

	@Override
	@RequestMapping(value = "/chat_room/{user_key}", method = RequestMethod.DELETE)
	public @ResponseBody void chat_room(@PathVariable String user_key) {
	}

	
}
