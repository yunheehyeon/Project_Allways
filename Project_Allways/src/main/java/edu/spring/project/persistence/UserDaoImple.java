package edu.spring.project.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.project.domain.User;

@Repository
public class UserDaoImple implements UserDao {

	private static final String USER_MAPPER =
		"edu.spring.project.mappers.UserMapper";
	
	private final Logger logger = LoggerFactory.getLogger(UserDaoImple.class);
	
	@Autowired private SqlSession session;
	
	// 로그인 여부 확인
	public User loginCheck(User user) {
		logger.info("loginCheck({}) 호출", user);
		
		return session.selectOne(USER_MAPPER + ".loginCheck", user);
	}

	// 회원가입시 ID 중복여부 확인
	public boolean checkUser(String userId) {
		boolean result = false;
		
		logger.info("checkUser({}) 호출", userId);
		
		String newId = session.selectOne(USER_MAPPER + ".existUserId", userId);
		
		if (newId != null && !newId.isEmpty()) {
			result = true;
		}
		
		return result;
	}
	
	// ID로 회원번호 찾기
	public int findUnoFromId(String userId) {
		logger.info("findUnoFromId({}) 호출", userId);
		
		return session.selectOne(USER_MAPPER + ".findUnoFromId", userId);
	}
	
	// 회원번호로 정보 찾기
	public User findUserByUno(int uno) {
		logger.info("findIdFromUno({}) 호출", uno);
		
		return session.selectOne(USER_MAPPER + ".findUserByUno", uno);
	}
	
	// 이름으로 회원 찾기
	public List<User> findUserByName(String userName) {
		logger.info("findUserByName({}) 호출", userName);
		
		return session.selectList(USER_MAPPER + ".findUserByName", userName);
	}
	
	// 출신학교로 회원 찾기
	public List<User> findUserByGraduation(String graduation) {
		logger.info("findUserByGraduation({}) 호출", graduation);
		
		return session.selectList(USER_MAPPER + ".findUserByGraduation", graduation);
	}
	
	// 이름 + 출신학교로 회원 찾기
	public List<User> findUserByNameAndGraduation(User user) {
		logger.info("findUserByNameAndGraudation(user : {}) 호출", user);
				
		return session.selectList(USER_MAPPER + ".findUserByNameAndGraduation", user);
	}
	
	// 이름 + Email 로 ID 찾기
	public String findUserId(User user) {
		logger.info("findUserId({}) 호출", user);
		
		return session.selectOne(USER_MAPPER + ".findUserId", user);
	}
	
	// ID + 이름 + Email 로 uno 찾기 
	public int findUserPwd(User user) {
		logger.info("findUserPwd({}) 호출", user);
		
		return session.selectOne(USER_MAPPER + ".findUserPwd" + user);
	}

	// 신규 회원가입
	public int createUser(User user) {	
		logger.info("insertUser({}) 호출", user);
		
		return session.insert(USER_MAPPER + ".registUser", user);
	}

	// 회원 정보 수정
	public int updateUser(User user) {
		logger.info("updateUser({}) 호출", user);
		
		return session.update(USER_MAPPER + ".updateUser", user);
	}
	
	// 감정 표현 수정
	public int updateEmotion(User user) {
		logger.info("updateEmotion({}) 호출", user);
		
		return session.update(USER_MAPPER + ".updateEmotion", user);
	}
	
	// 비밀번호 reset
	public int resetUserPwd(int uno) {
		logger.info("resetUserPwd({}) 호출", uno);
		
		return session.update(USER_MAPPER + ".resetPassword", uno);
	}

	// 회원 탈퇴
	public int deleteUser(int uno) {
		logger.info("deleteUser({}) 호출", uno);
		
		return session.delete(USER_MAPPER + ".quitUser", uno);
	}
}  // end class UserDaoImple
