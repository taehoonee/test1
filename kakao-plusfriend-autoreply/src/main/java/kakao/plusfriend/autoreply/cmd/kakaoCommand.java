package kakao.plusfriend.autoreply.cmd;

import java.util.Map;

import org.json.simple.JSONArray;

import kakao.plusfriend.autoreply.cmd.weather.googleAPIgeocode;
import kakao.plusfriend.autoreply.cmd.weather.kmsQueryDFS;
import kakao.plusfriend.autoreply.vo.messageVO;

public class kakaoCommand {
	
	/** 해당 지역의 날씨 정보를 받아온다. */
	public messageVO weather(String content) {
		messageVO messagevo = new messageVO();
		String address = (content.indexOf(' ') != -1) ? content.substring(content.indexOf(' ') + 1) : null;
		StringBuilder builder = new StringBuilder();
		
		if (address != null) {
			JSONArray dfs = kmsQueryDFS.getDFS(googleAPIgeocode.getCode(address));
			int loop = (7 < dfs.size()) ? 7 : dfs.size();
			
			builder.append("시엘이 미래를 보고 왔다는구나~ 한번 보자구나.\n");
			for (int i=0; i<loop; i++) {
				Map<String, Object> map = (Map<String, Object>)dfs.get(i);
				
				String hour = map.get("hour").toString();
				String wfkor = map.get("wfkor").toString();
				String tmx = map.get("tmx").toString();
				String tmn = map.get("tmn").toString();
				
				builder.append(hour);
				builder.append("시,");
				builder.append(wfkor);
				builder.append(",최대");
				builder.append(tmn + "도 최소");
				builder.append(tmx + "도\n");	
			}
			
			messagevo.getMessage().setText(builder.toString());
				
		} else {
			messagevo.getMessage().setText("지역정보를 입력하지 않았느리라...알려주지 않으면 시엘이 찾을 수 없다구나!");
		}
		
		
		return messagevo;
	}
	
	
	/** 리스트 페이지로 이동할 수 있게 하는 커맨드 : 테스트용 */
	public messageVO list(String content) {
		messageVO messagevo = new messageVO();
		messagevo.getMessage().setText("하하햣! 이몸이 공부한 것들이니라!!\n 시엘이 알고싶으면 "
			+ " http://220.230.124.70/kakao-plusfriend-autoreply/list 로 들어가보라 하는구나!");
		return messagevo;
	}
	
	
}
