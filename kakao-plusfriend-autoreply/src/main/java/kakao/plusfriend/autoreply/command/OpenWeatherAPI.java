package kakao.plusfriend.autoreply.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import kakao.plusfriend.autoreply.vo.messageVO;

public class OpenWeatherAPI {
	// openweathermap 에서 가져온 appid 
	private final String appid = "6cc14edeb67a82ce1478e3d0a967827e";
		
	
	/** 해당 지역의 날씨 정보를 받아온다. */
	public messageVO run(String content) {
		messageVO messagevo = new messageVO();
		String address = (content.indexOf(' ') != -1) ? content.substring(content.indexOf(' ') + 1) : null;
		StringBuilder builder = new StringBuilder();
		
		if (address != null) {
			JSONArray data = getWeatherData(getLocationCode(address));
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
				
				String line = "%s, %s, 최저%sº 최고%sº\n";
				line = String.format(line, dt_text, status, temp_min, temp_max);
				
				builder.append(line);
			}
			
			messagevo.getMessage().setText(builder.toString());
				
		} else {
			messagevo.getMessage().setText("지역정보를 입력하지 않았느리라...");
		}
		
		
		return messagevo;
	}
	
	// http://openweathermap.org/appid
	/** 날시정보를 가져옴 */
	public JSONArray getWeatherData(JSONObject location) {
		JSONArray data = null;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		
		String urlStr = "http://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=Metric&lang=kr";
		String lat = location.get("lat").toString();
		String lng = location.get("lng").toString();
		
		try {
		urlStr = String.format(urlStr, lat, lng, appid);
		URL url = new URL(urlStr);
		
		connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(1000);
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			stringBuilder = new StringBuilder();
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			
			Object object =  JSONValue.parse(stringBuilder.toString());
			location = (JSONObject) object;
			
			data = (JSONArray) location.get("list");

		}
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	/** 위치코드를 가져옴 */
	public JSONObject getLocationCode(String korAddress) {
		JSONObject location = null;
		String urlStr;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			urlStr = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=%s";
			urlStr = String.format(urlStr, URLEncoder.encode(korAddress, "utf-8"));
			URL url = new URL(urlStr);
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(1000);
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				stringBuilder = new StringBuilder();
				
				String line = null;
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line + "\n");
				}
				
				Object object =  JSONValue.parse(stringBuilder.toString());
				location = (JSONObject) object;
				
				JSONArray arr = (JSONArray) location.get("results");
				
				location = (JSONObject) arr.get(0);
				location = (JSONObject) location.get("geometry");
				location = (JSONObject) location.get("location");

			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
		
		return getGridXY(location);
	}
	
	/** 위경도 좌표로 격자 X Y 좌표 구하기 */
	private JSONObject getGridXY(JSONObject location) {
		
    	double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기1준점 Y좌표(GRID)
        
        try {
        	
			 double DEGRAD = Math.PI / 180.0;
		        // double RADDEG = 180.0 / Math.PI;
		 
			 double re = RE / GRID;
			 double slat1 = SLAT1 * DEGRAD;
			 double slat2 = SLAT2 * DEGRAD;
			 double olon = OLON * DEGRAD;
			 double olat = OLAT * DEGRAD;
			 
			 double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
			 sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
			 double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
			 sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
			 double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
			 ro = re * sf / Math.pow(ro, sn);
			 
			 double lat = Double.parseDouble(location.get("lat").toString());
			 double lng = Double.parseDouble(location.get("lng").toString());
			 
			 double ra = Math.tan(Math.PI * 0.25 + (lat) * DEGRAD * 0.5);
			 ra = re * sf / Math.pow(ra, sn);
			 double theta = lng * DEGRAD - olon;
			 if (theta > Math.PI)
				 theta -= 2.0 * Math.PI;
			 if (theta < -Math.PI)
				 theta += 2.0 * Math.PI;
			 theta *= sn;
		 
			 location.put("nx", Math.floor(ra * Math.sin(theta) + XO + 0.5));
			 location.put("ny", Math.floor(ro - ra * Math.cos(theta) + YO + 0.5));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return location;
	}
}
