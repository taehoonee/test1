package test;

import kakao.plusfriend.autoreply.cmd.weather.googleAPIgeocode;
import kakao.plusfriend.autoreply.cmd.weather.kmsQueryDFS;

public class springTest {

	public static void main(String[] args) {
		String content = "!날씨 서울시 마포구";
		String command = (content.indexOf(' ') != -1) ? content.substring(content.indexOf(' ') + 1) : content;
		
		System.out.println(command + "\t" + kmsQueryDFS.getDFS(googleAPIgeocode.getCode(command)));
	}
}
