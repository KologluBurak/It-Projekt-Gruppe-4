package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.LoginInfo;

/**
 * Das asynchrone Gegenstück des Interface {@link LoginService}. Es
 * wird semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher
 * erfolgt hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link LoginService}.
 * 
 * @author Raue
 */

public interface LoginServiceAsync {
  void login(String requestUri, AsyncCallback<LoginInfo> async);
}
