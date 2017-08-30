package kakao.plusfriend.autoreply.dao;

import kakao.plusfriend.autoreply.vo.kakaoVO;

public interface ReplyDao {
	public Object selectRegularContent(kakaoVO vo);
}
