package spring.teamproject.dabang.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int save(User user)throws Exception;
	public User findUserByUserEmail(String email) throws Exception;
	
	public User findOAuth2UserByUsername(String oauth2_id) throws Exception;
}
