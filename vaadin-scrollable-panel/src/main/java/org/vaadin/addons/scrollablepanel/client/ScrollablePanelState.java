package org.vaadin.addons.scrollablepanel.client;

import com.vaadin.shared.ui.AbstractSingleComponentContainerState;

@SuppressWarnings("serial")
public class ScrollablePanelState extends AbstractSingleComponentContainerState { // AbstractComponentState

    public static final String EVENT_SCOLLED = "scrollablePanelScrolled";

    {
        this.primaryStyleName = "v-scrollable-panel";
    }
    public int scrollEventDelayMillis;
    public boolean horizontalScrollingEnabled = true;
    public boolean verticalScrollingEnabled = true;

    public int scrollTop = 0;
    public int scrollLeft = 0;
}
