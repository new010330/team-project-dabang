package spring.teamproject.dabang.domain.account;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import spring.teamproject.dabang.domain.account.Account;

@Mapper
public interface AccountRepository {
	
	public List<Account> getAccount(int usercode) throws Exception;

}
