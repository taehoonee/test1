package kakao.plusfriend.autoreply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.autoreply.vo.kakaoVO;

public class ReplyDaoImplPostgresql implements ReplyDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<Object> selectRegularContent(kakaoVO vo) {
		List<Object> list = sqlSession.selectList("kakaoTextReply.selectRegularContent", vo);
		/*sqlSession.close();*/
		
		return list;
	}
	
	@Override
	public Object selectRegularContent(String command) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("command", command);
		
		return sqlSession.selectOne("kakaoCommandReply.selectRegularContent", parameter);
	}

}
