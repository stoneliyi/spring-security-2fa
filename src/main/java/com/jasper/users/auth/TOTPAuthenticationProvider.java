package com.jasper.users.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import com.jasper.users.model.User;

public class TOTPAuthenticationProvider extends DaoAuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(TOTPAuthenticationProvider.class);

	private TOTPAuthenticator totpAuthenticator = new TOTPAuthenticator();

	public TOTPAuthenticationProvider() {
		super();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		super.setPasswordEncoder(passwordEncoder);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {

		super.additionalAuthenticationChecks(userDetails, authentication);

		if (authentication.getDetails() instanceof TOTPWebAuthenticationDetails) {
			String secret = ((User)userDetails).getSecret();
			logger.debug("authenticating with TOTP secret: " + new String(new Base32().decode(secret)));
			if (StringUtils.hasText(secret)) {
				Integer totpKey = ((TOTPWebAuthenticationDetails) authentication.getDetails()).getTotpKey();
				if (totpKey == null) {
					throw new BadCredentialsException ("TOTP code is mandatory");
				}
				logger.debug("TOTP code: " + totpKey);
				try {
					if (! totpAuthenticator.verifyCode(secret, totpKey, 2)) {
						throw new BadCredentialsException("Invalid TOTP code");
					}
					logger.debug("valide TOTP code. authentication success.");
				} catch (InvalidKeyException | NoSuchAlgorithmException e) {
					throw new InternalAuthenticationServiceException("TOTP code verification failed", e);
				}
			}
		}
	}

	public TOTPAuthenticator getTotpAuthenticator() {
		return totpAuthenticator;
	}

	public void setTotpAuthenticator(TOTPAuthenticator totpAuthenticator) {
		this.totpAuthenticator = totpAuthenticator;
	}

}
