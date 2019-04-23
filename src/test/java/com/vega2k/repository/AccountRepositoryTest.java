package com.vega2k.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vega2k.entity.Account;

/*
 * @SpringBootTest는 모든 bean들을 다 등록하고 ,테스트를 실행하기 때문에  application.properties에 접속정보가 설정된
 * MySQL 데이터베이스를 가져오고 
 * @DataJpaTest는 슬라이싱 테스트 방식이므로  H2 데이터베이스 정보를 가져온다. (훨씬 빠르다)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
public class AccountRepositoryTest {
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void info() throws Exception {
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("===> DB 정보 <===");
			System.out.println(dataSource.getClass().getName());
			System.out.println(connection.getMetaData().getURL());
			System.out.println(connection.getMetaData().getUserName());
		}
	}

	@Test
	@Ignore
	public void delete() throws Exception {
//		Optional<Account> existAcct = accountRepository.findByUsername("soyul");
//		assertThat(existAcct).isEmpty();
//		if(existAcct.isPresent()) {
//			Account account = existAcct.get();
//				System.out.println("삭제시작 " + accountRepository.count());
//				accountRepository.delete(account);
//				accountRepository.flush();
//				System.out.println(accountRepository.count());
//		}
	}

	@Test
	//@Ignore
	public void account() throws Exception {
		// 레코드 저장
		Account account = new Account();
		account.setUsername("vega2k1");
		account.setPassword("pass");

		Account newAcct = accountRepository.save(account);
		System.out.println(newAcct.getId() + " " + newAcct.getUsername());
		assertThat(newAcct).isNotNull();
		
//		 Account existAcct = accountRepository.findByUsername(newAcct.getUsername());
//		 assertThat(existAcct).isNotNull();
//
//		 Account notExistAcct = accountRepository.findByUsername("test");
//		 assertThat(notExistAcct).isNull();

		
		Optional<Account> existAcct = accountRepository.findByUsername(newAcct.getUsername());
		assertThat(existAcct).isNotEmpty();
		Optional<Account> notExistAcct = accountRepository.findByUsername("test");
		assertThat(notExistAcct).isEmpty();
		 
		 
	}

}
