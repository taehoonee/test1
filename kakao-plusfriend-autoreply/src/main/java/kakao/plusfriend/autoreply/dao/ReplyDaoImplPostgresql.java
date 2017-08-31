package kakao.plusfriend.autoreply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.vo.kakaoVO;

public class ReplyDaoImplPostgresql implements ReplyDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	
	public List<Object> selectRegularContent(kakaoVO vo) {
		System.out.println("sqlSession : " + sqlSession);
		return sqlSession.selectList("kakaoTextReply.selectRegularContent", vo);
	}
}
