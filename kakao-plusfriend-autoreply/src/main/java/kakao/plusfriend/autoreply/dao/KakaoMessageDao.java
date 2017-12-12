package kakao.plusfriend.autoreply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.vo.kakaoVO;

public class KakaoMessageDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}



	public List<Object> selectRegularContent(kakaoVO vo) {
		List<Object> list = sqlSession.selectList("kakao.selectRegularContent", vo);
		return list;
	}
}
