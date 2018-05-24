package com.artwell.sampleordersheet.web.login;

import static com.haulmont.cuba.web.security.ExternalUserCredentials.EXTERNAL_AUTH_USER_SESSION_ATTRIBUTE;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.security.crypto.codec.Base64;

import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.core.global.ClientType;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.sys.ConditionalOnAppProperty;
import com.haulmont.cuba.security.auth.AbstractClientCredentials;
import com.haulmont.cuba.security.auth.AuthenticationDetails;
import com.haulmont.cuba.security.auth.AuthenticationService;
import com.haulmont.cuba.security.auth.Credentials;
import com.haulmont.cuba.security.auth.LoginPasswordCredentials;
import com.haulmont.cuba.security.auth.TrustedClientCredentials;
import com.haulmont.cuba.security.global.LoginException;
import com.haulmont.cuba.web.auth.WebAuthConfig;
import com.haulmont.cuba.web.security.LoginProvider;
import com.haulmont.cuba.web.security.ldap.LdapLoginProvider;
import com.haulmont.cuba.web.security.ldap.WebLdapConfig;

@ConditionalOnAppProperty(property = "cuba.web.ldap.enabled", value = "true")
@ConditionalOnAppProperty(property = "cuba.web.externalAuthentication", value = "false", defaultValue = "false")
public class LDAPAuthProvider implements LoginProvider, Ordered {
	 private final Logger log = LoggerFactory.getLogger(LdapLoginProvider.class);

	    @Inject
	    protected AuthenticationService authenticationService;
	    @Inject
	    protected WebLdapConfig webLdapConfig;
	    @Inject
	    protected WebAuthConfig webAuthConfig;

	    @Inject
	    protected Messages messages;

	    protected LdapContextSource ldapContextSource;

	    protected LdapTemplate ldapTemplate;
	    
	@Nullable
    @Override
    public AuthenticationDetails login(Credentials credentials) throws LoginException {
		 LoginPasswordCredentials loginPasswordCredentials = (LoginPasswordCredentials) credentials;
System.out.println("enter ldap login");
	        if (webAuthConfig.getStandardAuthenticationUsers().contains(loginPasswordCredentials.getLogin())) {
	            log.debug("User {} is not allowed to use external login");
	            return null;
	        }

	        String login = loginPasswordCredentials.getLogin();
	        String password = loginPasswordCredentials.getPassword();

	        Locale locale = loginPasswordCredentials.getLocale();
	        if (locale == null) {
	            locale = messages.getTools().getDefaultLocale();
	        }
	        
//	        if (!ldapTemplate.authenticate(LdapUtils.emptyLdapName(), buildPersonFilter(login), password)) {
	        if (!authentication(login,password)) {
	            throw new LoginException(
	                    messages.formatMessage(LdapLoginProvider.class, "LoginException.InvalidLoginOrPassword",
	                            locale, login)
	            );
	        }

	        TrustedClientCredentials tcCredentials = new TrustedClientCredentials(
	                loginPasswordCredentials.getLogin(),
	                webAuthConfig.getTrustedClientPassword(),
	                loginPasswordCredentials.getLocale(),
	                loginPasswordCredentials.getParams()
	        );

	        tcCredentials.setClientInfo(loginPasswordCredentials.getClientInfo());
	        tcCredentials.setClientType(ClientType.WEB);
	        tcCredentials.setIpAddress(loginPasswordCredentials.getIpAddress());
	        tcCredentials.setOverrideLocale(loginPasswordCredentials.isOverrideLocale());
	        tcCredentials.setSyncNewUserSessionReplication(loginPasswordCredentials.isSyncNewUserSessionReplication());

	        Map<String, Serializable> sessionAttributes = ((AbstractClientCredentials) credentials).getSessionAttributes();
	        Map<String, Serializable> targetSessionAttributes;
	        if (sessionAttributes != null
	                && !sessionAttributes.isEmpty()) {
	            targetSessionAttributes = new HashMap<>();
	            targetSessionAttributes.putAll(sessionAttributes);
	            targetSessionAttributes.put(EXTERNAL_AUTH_USER_SESSION_ATTRIBUTE, true);
	        } else {
	            targetSessionAttributes = ImmutableMap.of(EXTERNAL_AUTH_USER_SESSION_ATTRIBUTE, true);
	        }

	        tcCredentials.setSessionAttributes(targetSessionAttributes);

	        return authenticationService.login(tcCredentials);
		
	}
	private final static String mailDomainName = "@artwell-hk.com";
	public boolean authentication(String userName, String pwd) {
		boolean correct_flg = false;
		userName = userName+mailDomainName;
		LdapQuery query = LdapQueryBuilder.query().base("ou=Users,domainName=artwell-hk.com")
				.attributes("mail", "userpassword").where("objectclass").is("mailUser").and("mail").is(userName);
		/**
		 * @return ldap password
		 */
		String ldapPwd = null;
		List<String> rs = ldapTemplate.search(query, new AttributesMapper<String>() {
			@Override
			public String mapFromAttributes(Attributes attrs) throws NamingException {
				Object tempPwd = attrs.get("userpassword").get();
				System.out.println("attrs:"+attrs);
				byte[] pwd = (byte[]) tempPwd;
				return new String(pwd);
			}
		});

		if (rs != null && rs.size() > 0) {
			ldapPwd = rs.get(0);
		} else {
			return false;
		}

		correct_flg = verifyByPwd(ldapPwd, pwd);
		return correct_flg;
	}

	public boolean verifyByPwd(String ldappw, String inputpw) {

		// MessageDigest 提供了消息摘要算法，如 MD5 或 SHA，的功能，这里LDAP使用的是SHA-1
		MessageDigest md;
		boolean flg = false;
		try {
			md = MessageDigest.getInstance("SHA-1");

			/**
			 * ldap中的密码 例:
			 */
			// 取出加密字符
			if (ldappw.indexOf("{SSHA}") != -1) {
				ldappw = ldappw.substring(6);
			}

			// 解码BASE64
			byte[] ldappwbyte = Base64.decode(ldappw.getBytes());
			byte[] shacode;
			byte[] salt;

			// 前20位是SHA-1加密段，20位后是最初加密时的随机明文
			if (ldappwbyte.length <= 20) {
				shacode = ldappwbyte;
				salt = new byte[0];
			} else {
				shacode = new byte[20];
				salt = new byte[ldappwbyte.length - 20];
				System.arraycopy(ldappwbyte, 0, shacode, 0, 20);
				System.arraycopy(ldappwbyte, 20, salt, 0, salt.length);
			}

			// 把用户输入的密码添加到摘要计算信息
			md.update(inputpw.getBytes());
			// 把随机明文添加到摘要计算信息
			md.update(salt);

			// 按SSHA把当前用户密码进行计算
			byte[] inputpwbyte = md.digest();

			// 返回校验结果
			flg = MessageDigest.isEqual(shacode, inputpwbyte);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return flg;

	}
	 @Override
	    public boolean supports(Class<?> credentialsClass) {
	        return webLdapConfig.getLdapEnabled()
	                && LoginPasswordCredentials.class.isAssignableFrom(credentialsClass);
	    }

	    @Override
	    public int getOrder() {
	        return HIGHEST_PLATFORM_PRECEDENCE + 40;
	    }

	    protected String buildPersonFilter(String login) {
	        AndFilter filter = new AndFilter();
	        filter.and(new EqualsFilter("objectclass", "person"))
	                .and(new EqualsFilter(webLdapConfig.getLdapUserLoginField(), login));
	        return filter.encode();
	    }

	    protected void checkRequiredConfigProperties(WebLdapConfig webLdapConfig) {
	        List<String> missingProperties = new ArrayList<>();
	        if (StringUtils.isBlank(webLdapConfig.getLdapBase())) {
	            missingProperties.add("cuba.web.ldap.base");
	        }
	        if (webLdapConfig.getLdapUrls().isEmpty()) {
	            missingProperties.add("cuba.web.ldap.urls");
	        }
	        if (StringUtils.isBlank(webLdapConfig.getLdapUser())) {
	            missingProperties.add("cuba.web.ldap.user");
	        }
	        if (StringUtils.isBlank(webLdapConfig.getLdapPassword())) {
	            missingProperties.add("cuba.web.ldap.password");
	        }

	        if (!missingProperties.isEmpty()) {
	            throw new IllegalStateException("Please configure required application properties for LDAP integration: \n" +
	                    StringUtils.join(missingProperties, "\n"));
	        }
	    }

	    @PostConstruct
	    protected void init() {
	        if (webLdapConfig.getLdapEnabled()) {
	            ldapContextSource = new LdapContextSource();

	            checkRequiredConfigProperties(webLdapConfig);

	            ldapContextSource.setBase(webLdapConfig.getLdapBase());
	            List<String> ldapUrls = webLdapConfig.getLdapUrls();
	            ldapContextSource.setUrls(ldapUrls.toArray(new String[ldapUrls.size()]));
	            ldapContextSource.setUserDn(webLdapConfig.getLdapUser());
	            ldapContextSource.setPassword(webLdapConfig.getLdapPassword());

	            ldapContextSource.afterPropertiesSet();

	            ldapTemplate = new LdapTemplate(ldapContextSource);
	            ldapTemplate.setIgnorePartialResultException(true);
	        }
	    }
}
