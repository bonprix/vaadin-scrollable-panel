package org.vaadin.addons.scrollablepanel.client;

import com.vaadin.shared.communication.ServerRpc;

public interface ScrollablePanelServerRpc extends ServerRpc {

	/**
	 * Is called by the client when a scroll event occurs.
	 *
	 * @param scrollData
	 *            the scroll position data
	 */
	public void scrolled(final ScrollData scrollData);

}
