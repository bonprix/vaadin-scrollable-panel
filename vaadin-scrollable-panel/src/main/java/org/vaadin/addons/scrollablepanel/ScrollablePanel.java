package org.vaadin.addons.scrollablepanel;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.vaadin.addons.scrollablepanel.client.ScrollData;
import org.vaadin.addons.scrollablepanel.client.ScrollablePanelServerRpc;
import org.vaadin.addons.scrollablepanel.client.ScrollablePanelState;

import com.vaadin.ui.AbstractSingleComponentContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.util.ReflectTools;

/**
 * The scrollable panel is a simple single component widget which supports reading and writing the scroll position data (when scrolling is enabled).
 *
 * @author Christian Thiel
 *
 */
public class ScrollablePanel extends AbstractSingleComponentContainer {

    private static final long serialVersionUID = -3031229036782875607L;

    /**
     * The client side fires the scroll change after a given delay when no additional scroll event occurred.
     */
    public static final int DEFAULT_SCROLL_EVENT_DELAY = 200;

    private ScrollData scrollData;

    /**
     * Creates a new empty scrollable panel with horizontal and vertical scrolling set to AUTO.
     */
    public ScrollablePanel() {
        this((ComponentContainer) null);
    }

    /**
     * Creates a new empty panel which contains the given content and horizontal and vertical scrolling set to AUTO.
     *
     * @param content the content for the panel.
     */
    public ScrollablePanel(final Component content) {
        registerRpc(this.rpc);
        setContent(content);
        setWidth(100, Unit.PERCENTAGE);
        setScrollEventDelayMillis(ScrollablePanel.DEFAULT_SCROLL_EVENT_DELAY);
    }

    @Override
    protected ScrollablePanelState getState() {
        return (ScrollablePanelState) super.getState();
    }

    /**
     * Returns the top scroll amount in pixels (the number of top pixel rows that are hidden because of the scroll position).
     *
     * @return the top scroll amount in pixels
     */

    public Integer getScrollTop() {
        return this.scrollData != null ? this.scrollData.getTop() : null;
    }

    /**
     * Returns the left scroll amount in pixels (the number of left pixel columns that are hidden because of the scroll position).
     *
     * @return the left scroll amount in pixels
     */
    public Integer getScrollLeft() {
        return this.scrollData != null ? this.scrollData.getLeft() : null;
    }

    /**
     * Returns the width of the scrollable area in pixels.
     *
     * @return the width of the scrollable area in pixels
     */
    public Integer getScrollWidth() {
        return this.scrollData != null ? this.scrollData.getScrollWidth() : null;
    }

    /**
     * Returns the height of the scrollable area in pixels.
     *
     * @return the height of the scrollable area in pixels
     */
    public Integer getScrollHeight() {
        return this.scrollData != null ? this.scrollData.getScrollHeight() : null;
    }

    /**
     * Sets the top scroll position in pixel. This method does not check if the target scroll position is a valid value. If an invalid value (out of scroll
     * range) is given, the result will depend on the executing browser.
     *
     * @param top the top scroll position
     */
    public void setScrollTop(final int top) {
        if (this.scrollData == null) {
            this.scrollData = new ScrollData();
        }

        this.scrollData.setTop(top);
        getState().scrollTop = top;
    }

    /**
     * Sets the left scroll position in pixel. This method does not check if the target scroll position is a valid value. If an invalid value (out of scroll
     * range) is given, the result will depend on the executing browser.
     *
     * @param left the left scroll position
     */
    public void setScrollLeft(final int left) {
        if (this.scrollData == null) {
            this.scrollData = new ScrollData();
        }

        this.scrollData.setLeft(left);
        getState().scrollLeft = left;
    }

    /**
     * Defines, if horizontal scrolling is enabled. If true, the CSS overflow is set to AUTO, otherwise HIDDEN.
     *
     * @param horizontalScrollingEnabled scrollOption
     */
    public void setHorizontalScrollingEnabled(final boolean horizontalScrollingEnabled) {
        getState().horizontalScrollingEnabled = horizontalScrollingEnabled;
    }

    /**
     * Defines, if vertical scrolling is enabled. If true, the CSS overflow is set to AUTO, otherwise HIDDEN.
     *
     * @param verticalScrollingEnabled scrollOption
     */
    public void setVerticalScrollingEnabled(final boolean verticalScrollingEnabled) {
        getState().verticalScrollingEnabled = verticalScrollingEnabled;
    }

    /**
     * The current event delay in milliseconds.
     *
     * @return the current event delay in milliseconds
     */
    public Integer getScrollEventDelayMillis() {
        return getState().scrollEventDelayMillis;
    }

    /**
     * Sets the event delay in millisenconds. The client side will wait the given amount of milliseconds for any other scroll event on this component before
     * triggering an event.
     *
     * @param millis delay in ms
     */
    public void setScrollEventDelayMillis(final int millis) {
        getState().scrollEventDelayMillis = millis;
    }

    // Listener
    @SuppressWarnings("serial")
    private final ScrollablePanelServerRpc rpc = new ScrollablePanelServerRpc() {

        @Override
        public void scrolled(final ScrollData detail) {
            ScrollablePanel.this.scrollData = detail;

            ((ScrollablePanelState) getState(false)).scrollTop = detail.getTop();
            ((ScrollablePanelState) getState(false)).scrollLeft = detail.getLeft();

            ScrollablePanel.this.fireEvent(new ScrollEvent(ScrollablePanel.this, detail));
        }
    };

    public static class ScrollEvent extends Component.Event {

        private static final long serialVersionUID = -4717161002326588670L;
        private final ScrollData scrollData;

        public ScrollEvent(final Component component, final ScrollData scrollData) {
            super(component);
            this.scrollData = scrollData;
        }

        /**
         * The ScrollablePanel itself
         *
         * @return the scrollable panel
         */
        public ScrollablePanel getScrollablePanel() {
            return (ScrollablePanel) getSource();
        }

        /**
         * Returns the top scroll amount in pixels (the number of top pixel rows that are hidden because of the scroll position).
         *
         * @return the top scroll amount in pixels
         */
        public Integer getTop() {
            return this.scrollData.getTop();
        }

        /**
         * Returns the left scroll amount in pixels (the number of left pixel columns that are hidden because of the scroll position).
         *
         * @return the left scroll amount in pixels
         */
        public Integer getLeft() {
            return this.scrollData.getLeft();
        }

        /**
         * Returns the right scroll amount in pixels (the number of right pixel columns that are hidden because of the scroll position).
         *
         * @return the right scroll amount in pixels
         */
        public Integer getRight() {
            return this.scrollData.getRight();
        }

        /**
         * Returns the bottom scroll amount in pixels (the number of bottom pixel rows that are hidden because of the scroll position).
         *
         * @return the bottom scroll amount in pixels
         */
        public Integer getBottom() {
            return this.scrollData.getBottom();
        }

        /**
         * Returns the height of the scrollable area in pixels.
         *
         * @return the height of the scrollable area in pixels
         */
        public Integer getScrollHeight() {
            return this.scrollData.getScrollHeight();
        }

        /**
         * Returns the width of the scrollable area in pixels.
         *
         * @return the width of the scrollable area in pixels
         */
        public Integer getScrollWidth() {
            return this.scrollData.getScrollWidth();
        }

        /**
         * Returns the scroll data object.
         *
         * @return the scroll data object
         */
        public ScrollData getScrollData() {
            return this.scrollData;
        }
    };

    public interface ScrollListener extends Serializable {
        public static final Method SCROLLPANEL_SCROLL_METHOD = ReflectTools.findMethod(ScrollListener.class, "onScroll", ScrollEvent.class);

        public void onScroll(final ScrollEvent event);
    }

    /**
     * Adds a scroll listener. This listener will be called when a scrollEvent occurs.
     *
     * @param listener the listener to add
     */
    public void addScrollListener(final ScrollListener listener) {
        addListener(ScrollablePanelState.EVENT_SCOLLED, ScrollEvent.class, listener, ScrollListener.SCROLLPANEL_SCROLL_METHOD);
    }

    /**
     * Removes a scroll listener
     *
     * @param listener the listener to remove
     */
    public void removeScrollListener(final ScrollListener listener) {
        removeListener(ScrollEvent.class, listener, ScrollListener.SCROLLPANEL_SCROLL_METHOD);
    }

}
