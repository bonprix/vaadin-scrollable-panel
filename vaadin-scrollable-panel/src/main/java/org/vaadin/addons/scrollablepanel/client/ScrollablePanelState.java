package org.vaadin.addons.scrollablepanel.client;

import com.vaadin.shared.AbstractComponentState;

@SuppressWarnings("serial")
public class ScrollablePanelState extends AbstractComponentState {

	public static final String EVENT_SCOLLED = "scrollablePanelScrolled";

	{
		primaryStyleName = "v-scrollable-panel";
	}
	public int scrollEventDelayMillis;
	public boolean scrollX = true;
	public boolean scrollY = true;

	public int scrollTop = 0;
	public int scrollLeft = 0;
}
