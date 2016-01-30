package de.hdm.itProjektGruppe4.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import de.hdm.itProjektGruppe4.shared.LoginService;
import de.hdm.itProjektGruppe4.shared.bo.LoginInfo;

/**
	 * Die Klasse LoginServiceImpl erbt von der Klasse RemoteServiceServlet und 
	 * implementiert das Interface LoginService. 
	 * 
	 * @author Raue
	 */

public class LoginServiceImpl extends RemoteServiceServlet implements
LoginService {

	
	private static final long serialVersionUID = 1L;

public LoginInfo login(String requestUri) {
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();
LoginInfo loginInfo = new LoginInfo();

if (user != null) {
  loginInfo.setLoggedIn(true);
  loginInfo.setEmailAddress(user.getEmail());
  loginInfo.setNickname(user.getNickname());
  loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
} else {
  loginInfo.setLoggedIn(false);
  loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
}
return loginInfo;
}

}