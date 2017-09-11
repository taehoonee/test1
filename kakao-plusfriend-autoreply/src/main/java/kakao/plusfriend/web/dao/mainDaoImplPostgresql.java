package kakao.plusfriend.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.web.vo.contentVO;

public class mainDaoImplPostgresql implements mainDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int insertKakaoTextContent(contentVO vo) {
		return sqlSession.insert("kakaoTextReply.insertKakaoTextContent", vo);
	}
	
	@Override
	public int insertKakaoRegContent(contentVO vo) {
		return sqlSession.insert("kakaoTextReply.insertKakaoRegContent", vo);
	}
	
	@Override
	public int insertKakaoTextLink(contentVO vo) {
		return sqlSession.insert("kakaoTextReply.insertKakaoTextLink", vo);
	}
	
	@Override
	public List<Object> selectTextList() {
		return sqlSession.selectList("selectTextList");
	}
	
	@Override
	public List<Object> selectRegularList() {
		return sqlSession.selectList("selectRegularList");
	}

	@Override
	public List<Object> selectTextAndRegularContentsList(contentVO vo) {
		return sqlSession.selectList("selectTextAndRegularContentsList", vo);
	}
	
}