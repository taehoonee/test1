package kakao.plusfriend.web.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import kakao.plusfriend.web.vo.contentVO;

public class BotDaoImplPostgresql implements BotDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int insertKakaoTextContent(contentVO vo) {
		int insert = sqlSession.insert("kakaoTextReply.insertKakaoTextContent", vo);;
		sqlSession.commit();
		/*sqlSession.close();*/
		
		return insert;
	}
	
	@Override
	public int insertKakaoRegContent(contentVO vo) {
		int insert = sqlSession.insert("kakaoTextReply.insertKakaoRegContent", vo);
		sqlSession.commit();
		/*sqlSession.close();*/
		
		return insert;
	}
	
	@Override
	public int insertKakaoTextLink(contentVO vo) {
		int insert = sqlSession.insert("kakaoTextReply.insertKakaoTextLink", vo);
		sqlSession.commit();
		/*sqlSession.close();*/
		
		return insert;
	}
	
	@Override
	public List<Object> selectTextList() {
		List<Object> list = sqlSession.selectList("selectTextList");
		/*sqlSession.close();*/
		
		return list;
	}
	
	@Override
	public List<Object> selectRegularList() {
		List<Object> list =  sqlSession.selectList("selectRegularList");
		/*sqlSession.close();*/
		
		return list;
	}
	
}