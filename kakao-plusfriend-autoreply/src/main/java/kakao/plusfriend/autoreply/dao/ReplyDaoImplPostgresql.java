package kakao.plusfriend.autoreply.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.vo.kakaoVO;

public class ReplyDaoImplPostgresql implements ReplyDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Object selectRegularContent(kakaoVO vo) {
		return sqlSession.selectOne("selectRegularContent", vo);
	}
}
