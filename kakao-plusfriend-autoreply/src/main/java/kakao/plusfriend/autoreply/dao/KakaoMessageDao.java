package kakao.plusfriend.autoreply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.vo.kakaoVO;
import kakao.plusfriend.autoreply.vo.searchVO;

public class KakaoMessageDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}



	public List<Object> selectRegularContent(searchVO vo) {
		List<Object> list = sqlSession.selectList("kakao.selectRegularContent", vo);
		return list;
	}
}
