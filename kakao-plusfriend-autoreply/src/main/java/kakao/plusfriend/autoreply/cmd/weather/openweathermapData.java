package kakao.plusfriend.autoreply.cmd.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
// http://openweathermap.org/appid
public class openweathermapData {
	// openweathermap 에서 가져온 appid 
	private static String appid = "6cc14edeb67a82ce1478e3d0a967827e";
	
	
	public static JSONArray getData(JSONObject location) {
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
}
