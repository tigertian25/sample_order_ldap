package com.artwell.sampleordersheet.web.login;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haulmont.cuba.gui.components.Frame.NotificationType;
import com.haulmont.cuba.security.auth.LoginPasswordCredentials;
import com.haulmont.cuba.security.auth.RememberMeCredentials;
import com.haulmont.cuba.security.global.InternalAuthenticationException;
import com.haulmont.cuba.security.global.LoginException;
import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.app.loginwindow.AppLoginWindow;

public class ExtAppLoginWindow extends AppLoginWindow {
	
	private static final Logger log = LoggerFactory.getLogger(ExtAppLoginWindow.class);
	
	@Override
	 protected void doLogin() {
	        String login = loginField.getValue();
	        String password = passwordField.getValue() != null ? passwordField.getValue() : "";
	        if(login.indexOf("@")>0) {
	        	login=login.substring(0, login.indexOf("@"));
	        }
	        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
	            showNotification(messages.getMainMessage("loginWindow.emptyLoginOrPassword"), NotificationType.WARNING);
	            return;
	        }

	        try {
	            Locale selectedLocale = localesSelect.getValue();
	            app.setLocale(selectedLocale);

	            if (loginByRememberMe && webConfig.getRememberMeEnabled()) {
	                doLogin(new RememberMeCredentials(login, password, selectedLocale));
	            } else {
	                doLogin(new LoginPasswordCredentials(login, password, selectedLocale));
	            }

	            // locale could be set on the server
	            if (connection.getSession() != null) {
	                Locale loggedInLocale = connection.getSession().getLocale();

	                if (globalConfig.getLocaleSelectVisible()) {
	                    app.addCookie(App.COOKIE_LOCALE, loggedInLocale.toLanguageTag());
	                }
	            }
	        } catch (InternalAuthenticationException e) {
	            log.error("Internal error during login", e);

	            showUnhandledExceptionOnLogin(e);
	        } catch (LoginException e) {
	            log.info("Login failed: {}", e.toString());

	            String message = StringUtils.abbreviate(e.getMessage(), 1000);
	            showLoginException(message);
	        } catch (Exception e) {
	            log.warn("Unable to login", e);

	            showUnhandledExceptionOnLogin(e);
	        }
	    }
}