package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.LoginInfo;

public interface LoginServiceAsync {
  void login(String requestUri, AsyncCallback<LoginInfo> async);
}
