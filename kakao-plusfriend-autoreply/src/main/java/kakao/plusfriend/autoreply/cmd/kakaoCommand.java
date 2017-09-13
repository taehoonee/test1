package kakao.plusfriend.autoreply.cmd;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kakao.plusfriend.autoreply.cmd.weather.googleAPIgeocode;
import kakao.plusfriend.autoreply.cmd.weather.openweathermapData;
import kakao.plusfriend.autoreply.vo.messageVO;

public class kakaoCommand {
	
	/** 해당 지역의 날씨 정보를 받아온다. */
	public messageVO weather(String content) {
		messageVO messagevo = new messageVO();
		String address = (content.indexOf(' ') != -1) ? content.substring(content.indexOf(' ') + 1) : null;
		StringBuilder builder = new StringBuilder();
		
		if (address != null) {
			JSONArray data = openweathermapData.getData(googleAPIgeocode.getCode(address));
			int loop = (7 < data.size()) ? 7 : data.size();
			
			builder.append("시엘이 미래를 보고 왔다는구나~ \n");
			for (int i=0; i<loop; i++) {
				JSONObject object = (JSONObject)data.get(i);
				
				String dt_text = object.get("dt_txt").toString();
				JSONObject main = (JSONObject)object.get("main");
				
				// 최저 , 최고 기온
				String temp_min = main.get("temp_min").toString();
				String temp_max = main.get("temp_max").toString();
				
				JSONArray arr = (JSONArray)object.get("weather");
				JSONObject weather = (JSONObject)arr.get(0);
				// 날씨 상태
				String status = weather.get("description").toString();
				
				// 00일 03시 로 변경
				dt_text = dt_text.substring(dt_text.indexOf("-", 1) + 1);
				dt_text = dt_text.substring(0, dt_text.indexOf(":"));
				dt_text = dt_text + "시";
				
				builder.append(dt_text);
				builder.append(",");
				builder.append(status);
				builder.append(",최저");
				builder.append(temp_min + "º 최고");
				builder.append(temp_max + "º\n");	
			}
			
			messagevo.getMessage().setText(builder.toString());
				
		} else {
			messagevo.getMessage().setText("지역정보를 입력하지 않았느리라...");
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
