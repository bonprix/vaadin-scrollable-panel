package org.vaadin.addons.scrollablepanel;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.vaadin.addons.scrollablepanel.client.ScrollDetail;
import org.vaadin.addons.scrollablepanel.client.ScrollablePanelServerRpc;
import org.vaadin.addons.scrollablepanel.client.ScrollablePanelState;

import com.vaadin.ui.AbstractSingleComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.util.ReflectTools;

public class ScrollablePanel extends AbstractSingleComponentContainer {

	private static final long serialVersionUID = -3031229036782875607L;
	public static final int DEFAULT_SCROLL_EVENT_DELAY = 200;

	private ScrollDetail scrollDetail;

	/**
	 * Creates a new empty panel.
	 */
	public ScrollablePanel() {
		this((ComponentContainer) null);
	}

	/**
	 * Creates a new empty panel which contains the given content.
	 *
	 * @param content
	 *            the content for the panel.
	 */
	public ScrollablePanel(final Component content) {
		registerRpc(rpc);
		setContent(content);
		setWidth(100, Unit.PERCENTAGE);
		setScrollEventDelayMillis(DEFAULT_SCROLL_EVENT_DELAY);
	}

	@Override
	protected ScrollablePanelState getState() {
		return (ScrollablePanelState) super.getState();
	}

	public Integer getScrollTop() {
		return scrollDetail != null ? scrollDetail.getTop() : null;
	}

	public Integer getScrollLeft() {
		return scrollDetail != null ? scrollDetail.getLeft() : null;
	}

	public Integer getScrollWidth() {
		return scrollDetail != null ? scrollDetail.getScrollWidth() : null;
	}

	public Integer getScrollHeight() {
		return scrollDetail != null ? scrollDetail.getScrollHeight() : null;
	}

	public void setScrollTop(final int top) {
		if (scrollDetail == null) {
			scrollDetail = new ScrollDetail();
		}

		scrollDetail.setTop(top);
		getState().scrollTop = top;
	}

	public void setScrollLeft(final int left) {
		if (scrollDetail == null) {
			scrollDetail = new ScrollDetail();
		}

		scrollDetail.setLeft(left);
		getState().scrollLeft = left;
	}

	public void setScrollX(final boolean scrollX) {
		getState().scrollX = scrollX;
	}

	public void setScrollY(final boolean scrollY) {
		getState().scrollY = scrollY;
	}

	public Integer getScrollEventDelayMillis() {
		return getState().scrollEventDelayMillis;
	}

	public void setScrollEventDelayMillis(final int millis) {
		getState().scrollEventDelayMillis = millis;
	}

	// Listener
	@SuppressWarnings("serial")
	private final ScrollablePanelServerRpc rpc = new ScrollablePanelServerRpc() {

		@Override
		public void scrolled(final ScrollDetail detail) {
			scrollDetail = detail;

			((ScrollablePanelState) getState(false)).scrollTop = detail.getTop();
			((ScrollablePanelState) getState(false)).scrollLeft = detail.getLeft();

			ScrollablePanel.this.fireEvent(new ScrolledEvent(ScrollablePanel.this, detail));
		}
	};

	public static class ScrolledEvent extends Component.Event {

		private static final long serialVersionUID = -4717161002326588670L;
		private final ScrollDetail detail;

		public ScrolledEvent(final Component component, final ScrollDetail detail) {
			super(component);
			this.detail = detail;
		}

		/**
		 * The ScrollablePanel itself
		 *
		 * @return
		 */
		public ScrollablePanel getScrollablePanel() {
			return (ScrollablePanel) getSource();
		}

		public Integer getTop() {
			return detail.getTop();
		}

		public Integer getLeft() {
			return detail.getLeft();
		}

		public Integer getScrollHeight() {
			return detail.getScrollHeight();
		}

		public Integer getScrollWidth() {
			return detail.getScrollWidth();
		}

		public ScrollDetail getDetail() {
			return detail;
		}
	};

	public interface ScrolledListener extends Serializable {
		public static final Method SCROLLPANEL_SCROLLED_METHOD = ReflectTools.findMethod(ScrolledListener.class, "scrolled", ScrolledEvent.class);

		public void scrolled(final ScrolledEvent event);
	}

	public void addScrolledListener(final ScrolledListener listener) {
		addListener(ScrollablePanelState.EVENT_SCOLLED, ScrolledEvent.class, listener, ScrolledListener.SCROLLPANEL_SCROLLED_METHOD);
	}

}
