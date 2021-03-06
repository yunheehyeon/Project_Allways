package edu.spring.project.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.Allwaiser;
import edu.spring.project.domain.User;

@Repository
public class AllwaiserDaoImple implements AllwaiserDao {
	
	private static final String ALLWAISER_MAPPER =
			"edu.spring.project.mappers.AllwaiserMapper";
	
	private final Logger logger = LoggerFactory.getLogger(AllwaiserDaoImple.class);
	
	@Autowired private SqlSession session;
	
	// 내가 구독하는 모든 Allwaiser List 가져오기
	public List<User> readMyAllwaisers(int uno) {
		logger.info("readMyAllwaisers(uno : {}) 호출", uno);
		
		return session.selectList(ALLWAISER_MAPPER + ".selectMylist", uno);
	}
	
	// 내가 구독하는 모든 Allwaiser List size 구하기
	public int countMyAllwaisers(int uno) {
		logger.info("countMyAllwaisers() 호출"); 
		
		return session.selectOne(ALLWAISER_MAPPER + ".countMyList", uno);
	}
	
	// 구독여부 확인
	public int existAllwaiser(Allwaiser allwaiser) {
		logger.info("existAllwaiser() 호출");
		
		return session.selectOne(ALLWAISER_MAPPER + ".existAllwaiser", allwaiser);
	}

	// 새로운 following 추가하기
	public int followAllwaiser(Allwaiser allwaiser) {
		logger.info("followAllwaiser(uno : {}) 호출", allwaiser);
		
		return session.insert(ALLWAISER_MAPPER + ".followAllwaiser", allwaiser);
	}
	
	// following List에서 이름으로 찾기
	public List<User> searchByName(String userName, int uno) {
		logger.info("searchByName(userName : {}, uno : {}) 호출", userName, uno);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("uno", uno);
		
		return session.selectList(ALLWAISER_MAPPER + ".selectMyOne", params);
	}
	
	// following 취소하기
	public int unfollowAllwaiser(Allwaiser allwaiser) {
		logger.info("unfollowAllwaiser(uno : {}) 호출", allwaiser);
		
		return session.delete(ALLWAISER_MAPPER + ".unfollowAllwaiser", allwaiser);
	}

}