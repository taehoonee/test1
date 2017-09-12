package kakao.plusfriend.autoreply.cmd.weather;

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

/** GOOGLE API 를 통해 지역의 좌표값을 가지고 온다. */
public class googleAPIgeocode {

	public static JSONObject getCode(String korAddress) {
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
		


		
		
		return location;
	}
}
