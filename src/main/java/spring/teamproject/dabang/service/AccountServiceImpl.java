package spring.teamproject.dabang.service;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.teamproject.dabang.domain.account.Account;
import spring.teamproject.dabang.domain.account.AccountRepository;



@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	
//	@Value("${file.path}")
//	private String filePath;
	
	private final AccountRepository accountRepository;


	@Override
	public List<Account> getAccountList(int usercode) throws Exception {
		List<Account> accountlist = accountRepository.getAccount(usercode);
		
		return accountlist;
	}

}	







