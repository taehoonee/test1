package kakao.plusfriend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kakao.plusfriend.web.dao.mainDao;
import kakao.plusfriend.web.vo.contentVO;

@Controller
public class mainController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private mainDao dao;
	public void setDao(mainDao dao) {
		this.dao = dao;
	}
	
	/** 페이지 이동 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String start() {
		return "main";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list() {
		return "list";
	}
	
	
	/** 처리 */
	@RequestMapping(value="/addtext", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addtext(@RequestBody contentVO vo){
		Map<String, Object> map = new HashMap<String, Object>();	
		log.info("===insertKakaoTextContent\t" + vo.getTxt_content());
		int result = dao.insertKakaoTextContent(vo);
		
		if (result > 0) {
			map.put("info", "ok");
		} else {
			map.put("info", "fail");
		}
		log.info("===insertKakaoRegContent\t" + map);
		
		return map;
	}
	
	
	@RequestMapping(value="/addreg", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addreg(@RequestBody contentVO vo){
		Map<String, Object> map = new HashMap<String, Object>();	
		log.info("===insertKakaoRegContent\t" + vo.getReg_content());
		int result = dao.insertKakaoRegContent(vo);
		
		if (result > 0) {
			map.put("info", "ok");
		} else {
			map.put("info", "fail");
		}
		log.info("===insertKakaoRegContent\t" + map);
		
		return map;
	}
	
	
	@RequestMapping(value="/addlink", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addlink(@RequestBody contentVO vo){
		Map<String, Object> map = new HashMap<String, Object>();	
		log.info("===insertKakaoTextLink");
		int result = dao.insertKakaoTextLink(vo);
		
		if (result > 0) {
			map.put("info", "ok");
		} else {
			map.put("info", "fail");
		}
		log.info("===insertKakaoTextLink\t" + map);
		
		return map;
	}
	
	
	@RequestMapping(value="/textlist", method=RequestMethod.POST)
	public @ResponseBody List<Object> textlist() {
		log.info("===textlist");
		List<Object> list = dao.selectTextList();
		log.info("===selectTextList\t" + list);
		return list;
	}
	
	@RequestMapping(value="/regularlist", method=RequestMethod.POST)
	public @ResponseBody List<Object> regularlist() {
		log.info("===regularlist");
		List<Object> list = dao.selectRegularList();
		log.info("===selectRegularList\t" + list);
		return list;
	}
}
