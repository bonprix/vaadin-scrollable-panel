package org.vaadin.addons.scrollablepanel.client;

import com.vaadin.shared.communication.ServerRpc;

public interface ScrollablePanelServerRpc extends ServerRpc {

	public void scrolled(ScrollDetail details);

}
