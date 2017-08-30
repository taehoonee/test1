package kakao.plusfriend.autoreply.dao;

import java.util.List;

import kakao.plusfriend.autoreply.vo.kakaoVO;

public interface ReplyDao {
	public List<Object> selectRegularContent(kakaoVO vo);
}
