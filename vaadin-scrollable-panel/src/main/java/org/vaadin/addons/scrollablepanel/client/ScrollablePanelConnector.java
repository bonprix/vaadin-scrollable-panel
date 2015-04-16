package org.vaadin.addons.scrollablepanel.client;

import org.vaadin.addons.scrollablepanel.ScrollablePanel;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractSingleComponentContainerConnector;
import com.vaadin.client.ui.PostLayoutListener;
import com.vaadin.shared.ui.Connect;

@Connect(ScrollablePanel.class)
public class ScrollablePanelConnector extends AbstractSingleComponentContainerConnector implements PostLayoutListener {

	private static final long serialVersionUID = 6116325877427442412L;

	ScrollablePanelServerRpc rpc = RpcProxy.create(ScrollablePanelServerRpc.class, this);

	ScrollEventHandler scrollHandler = new ScrollEventHandler() {

		@Override
		public void onScroll(final ScrollData detail) {
			if (hasEventListener(ScrollablePanelState.EVENT_SCOLLED)) {
				rpc.scrolled(detail);
			}
		}
	};

	private Integer uidlScrollTop;
	private Integer uidlScrollLeft;

	public ScrollablePanelConnector() {
		super();
		getWidget().setTimedScrollHandler(scrollHandler);
	}

	@Override
	public ScrollablePanelState getState() {
		return (ScrollablePanelState) super.getState();
	}

	@Override
	public ScrollablePanelWidget getWidget() {
		return (ScrollablePanelWidget) super.getWidget();
	}

	@Override
	public void updateCaption(final ComponentConnector connector) {
		// NOP: layouts caption, errors etc not rendered in Panel
	}

	@Override
	public void onConnectorHierarchyChange(final ConnectorHierarchyChangeEvent event) {
		// We always have 1 child, unless the child is hidden
		getWidget().setWidget(getContentWidget());
	}

	@Override
	public void onStateChanged(final StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		getWidget().setHorizontalScrollingEnabled(getState().horizontalScrollingEnabled);
		getWidget().setVerticalScrollingEnabled(getState().verticalScrollingEnabled);
		getWidget().setVerticalScrollPosition(getState().scrollTop);
		getWidget().setHorizontalScrollPosition(getState().scrollLeft);

		if (getState().scrollTop != getWidget().getVerticalScrollPosition()) {
			// Sizes are not yet up to date, so changing the scroll position
			// is deferred to after the layout phase
			uidlScrollTop = getState().scrollTop;
		}

		if (getState().scrollLeft != getWidget().getHorizontalScrollPosition()) {
			// Sizes are not yet up to date, so changing the scroll position
			// is deferred to after the layout phase
			uidlScrollLeft = getState().scrollLeft;
		}

	}

	@Override
	public void postLayout() {
		final ScrollablePanelWidget panel = getWidget();
		if (uidlScrollTop != null) {
			panel.setVerticalScrollPosition(uidlScrollTop.intValue());
			// Read actual value back to ensure update logic is correct
			uidlScrollTop = null;
		}

		if (uidlScrollLeft != null) {
			panel.setHorizontalScrollPosition(uidlScrollLeft.intValue());
			// Read actual value back to ensure update logic is correct
			uidlScrollLeft = null;
		}
	}
}
