package hust.admin.project.Configuration.JwtConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import hust.admin.project.Repository.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	@Autowired
	AccountRepository accountRepository;
//	@Autowired
//	ManageTokenRepository manageTokenRepository;
//	@Value("$(secret_key)")
	private String jwtSecret = "CUDANVUV@10021050";
	private final String LOCALHOST_IPV4 = "127.0.0.1";
	private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
	public static final Long time_expire = 7200000L;

	public String generateToken(String userName) {
		Date date = new Date(System.currentTimeMillis() + JwtProvider.time_expire);
		return Jwts.builder().setSubject(userName).setExpiration(date).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public boolean validateToken(String token) {
//		if (manageTokenRepository.existsByToken(token))
//			return false;
//		String userLogin = getLoginFormToke(token);
//		Account account = accountRepository.findUserByUsername(userLogin);
//		if (!account.getIpAdress().equals(getClientIp()))
//			return false;

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
//			System.out.println("invalid token");
		}
		return false;

	}

	public String getLoginFormToke(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	@SuppressWarnings("deprecation")
	public String getClientIp() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String ipAddress = request.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}

		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}

		if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					ipAddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}

		if (!StringUtils.isEmpty(ipAddress) && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		}

		return ipAddress;
	}

}
