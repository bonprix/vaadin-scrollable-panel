package org.vaadin.addons.scrollablepanel.client;

import com.google.gwt.event.shared.EventHandler;

public interface ScrollEventHandler extends EventHandler {

	void onScroll(ScrollData details);
}
