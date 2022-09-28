package hust.admin.project.Configuration.JwtConfig;

import static org.springframework.util.StringUtils.hasText;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class AuthEntryPointJWT implements AuthenticationEntryPoint {
//	@Autowired
//	private JwtProvider jwtProvider;
//	@Autowired
//	private AccountRepository accountRepository;
	private static final String AUTHORIZATION = "Authorization";

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJWT.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", authException.getMessage());
//		try {
//			String token = getTokenFromRequest(request);
//			String userName = jwtProvider.getLoginFormToke(token);
//			Account account = accountRepository.findUserByUsername(userName);
//			account.setIpAdress(null);
//			accountRepository.save(account);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}		
		response.sendRedirect("/api/v1/project/auth/authFail");

	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader(AUTHORIZATION);
		if (hasText(bearer)) {
			return bearer;

		}
		return null;
	}
}
