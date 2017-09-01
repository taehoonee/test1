package kakao.plusfriend.web.dao;

import java.util.List;

import kakao.plusfriend.web.vo.contentVO;

public interface BotDao {
	public int insertKakaoTextContent(contentVO vo);
	public int insertKakaoRegContent(contentVO vo);
	public int insertKakaoTextLink(contentVO vo);
	
	public List<Object> selectTextList();
	public List<Object> selectRegularList();
}
