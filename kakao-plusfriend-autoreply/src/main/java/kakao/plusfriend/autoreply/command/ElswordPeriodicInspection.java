package kakao.plusfriend.autoreply.command;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kakao.plusfriend.autoreply.vo.messageVO;

/** 엘소드 정기점검 데이터 가져오기 */
public class ElswordPeriodicInspection {

	
	public messageVO run(String content) {
		messageVO messagevo = new messageVO();
		//String address = (content.indexOf(' ') != -1) ? content.substring(content.indexOf(' ') + 1) : null;
		StringBuilder builder = new StringBuilder();
		
		try {
			builder.append(getPeriodicInspection(getUID()));
			messagevo.getMessage().setText(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
			messagevo.getMessage().setText(e.getLocalizedMessage());
		}
		return messagevo;
	}
	
	
	public String getPeriodicInspection(String UID) throws IOException {
		Connection.Response response = Jsoup.connect("http://elsword.nexon.com/news/notice/view.aspx?noticearticlesn=" + UID)
                .method(Connection.Method.GET)
                .execute();
		Document pi = response.parse();
		Element contextElement = pi.select("table.bbsV div#editorView").first();
		
		return contextElement.text();
	}
	
	public String getUID() throws IOException {
		Connection.Response response = Jsoup.connect("http://elsword.nexon.com/news/notice/list.aspx?n4pageno=1")
                .method(Connection.Method.GET)
                .execute();
		Document notice = response.parse();
		Element numberElement = notice.select("table.bbsL td.number:first").first();
		
		return numberElement.text();
	}
}
