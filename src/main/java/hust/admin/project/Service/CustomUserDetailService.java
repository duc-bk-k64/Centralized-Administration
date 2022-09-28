package hust.admin.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hust.admin.project.Configuration.JwtConfig.CustomUserDetail;
import hust.admin.project.Entity.Account;
import hust.admin.project.Repository.AccountRepository;


@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = accountRepository.findUserByUsername(username);
		return CustomUserDetail.createCustomUserDetails(account);
	}

}