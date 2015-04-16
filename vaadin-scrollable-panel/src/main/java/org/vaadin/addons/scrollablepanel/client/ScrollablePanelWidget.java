package org.vaadin.addons.scrollablepanel.client;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.vaadin.client.ui.VLazyExecutor;

public class ScrollablePanelWidget extends ScrollPanel {

	private ScrollEventHandler timedHandler = null;
	private VLazyExecutor executor = null;

	private int scrollEventDelayMillis = 200;
	private ScrollData lastSentScrollPos, currentScrollingPos;

	private boolean horizontalScrollingEnabled = true;
	private boolean verticalScrollingEnabled = true;

	public ScrollablePanelWidget() {
		super();
		setTouchScrollingDisabled(true);

		addScrollHandler(new ScrollHandler() {
			@Override
			public void onScroll(final ScrollEvent event) {
				ScrollablePanelWidget.this.currentScrollingPos = new ScrollData();

				if (event != null && event.getRelativeElement() != null) {
					final Element e = event.getRelativeElement();

					ScrollablePanelWidget.this.currentScrollingPos.setTop(e.getScrollTop());
					ScrollablePanelWidget.this.currentScrollingPos.setLeft(e.getScrollLeft());
					ScrollablePanelWidget.this.currentScrollingPos.setBottom(e.getScrollHeight() - (e.getScrollTop() + e.getOffsetHeight()));
					ScrollablePanelWidget.this.currentScrollingPos.setRight(e.getScrollWidth() - (e.getScrollLeft() + e.getOffsetWidth()));
					ScrollablePanelWidget.this.currentScrollingPos.setScrollHeight(e.getScrollHeight());
					ScrollablePanelWidget.this.currentScrollingPos.setScrollWidth(e.getScrollWidth());
				}

				startTrigger();
			}
		});

	}

	private void startTrigger() {
		if (this.executor == null) {
			this.executor = new VLazyExecutor(this.scrollEventDelayMillis, new ScheduledCommand() {
				@Override
				public void execute() {
					if (ScrollablePanelWidget.this.timedHandler != null
					        && ScrollablePanelWidget.this.currentScrollingPos != null
					        && (ScrollablePanelWidget.this.lastSentScrollPos == null || !ScrollablePanelWidget.this.currentScrollingPos
					                .equals(ScrollablePanelWidget.this.lastSentScrollPos))) {
						ScrollablePanelWidget.this.timedHandler.onScroll(ScrollablePanelWidget.this.currentScrollingPos);
						ScrollablePanelWidget.this.lastSentScrollPos = ScrollablePanelWidget.this.currentScrollingPos;
					}
				}
			});
		}
		this.executor.trigger();
	}

	@Override
	public HandlerRegistration addScrollHandler(final ScrollHandler handler) {
		return super.addScrollHandler(handler);
	}

	public void setTimedScrollHandler(final ScrollEventHandler handler) {
		this.timedHandler = handler;
	}

	public void setScrollEventDelayMillis(final int scrollEventDelayMillis) {
		this.scrollEventDelayMillis = scrollEventDelayMillis;
	}

	public boolean isHorizontalScrollingEnabled() {
		return this.horizontalScrollingEnabled;
	}

	public void setHorizontalScrollingEnabled(final boolean horizontalScrollingEnabled) {
		getElement().getStyle().setOverflowX(horizontalScrollingEnabled ? Overflow.AUTO : Overflow.HIDDEN);
		this.horizontalScrollingEnabled = horizontalScrollingEnabled;
	}

	public boolean isVerticalScrollingEnabled() {
		return this.verticalScrollingEnabled;
	}

	public void setVerticalScrollingEnabled(final boolean verticalScrollingEnabled) {
		getElement().getStyle().setOverflowY(verticalScrollingEnabled ? Overflow.AUTO : Overflow.HIDDEN);
		this.verticalScrollingEnabled = verticalScrollingEnabled;
	}

}
