package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.LoginInfo;

/**
 * Synchrones Interface für die Klasse LoginServiceImpl.
 *
 * @author Raue
 *
 */

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);
}