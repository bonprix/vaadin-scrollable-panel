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

	public static final String CLASSNAME = "v-scrollable-panel";
	ScrollEventHandler timedHandler = null;
	VLazyExecutor executor = null;

	int scrollEventDelayMillis = 200;
	ScrollDetail lastSentScrollPos, currentScrollingPos;

	private boolean scrollX = true;
	private boolean scrollY = true;

	public ScrollablePanelWidget() {
		super();
		setTouchScrollingDisabled(true);

		addScrollHandler(new ScrollHandler() {
			@Override
			public void onScroll(final ScrollEvent event) {
				ScrollablePanelWidget.this.currentScrollingPos = new ScrollDetail();

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

	public void setScrollHandlerTimed(final ScrollEventHandler handler) {
		this.timedHandler = handler;
	}

	public void setScrollEventDelayMillis(final int scrollEventDelayMillis) {
		this.scrollEventDelayMillis = scrollEventDelayMillis;
	}

	public boolean isScrollX() {
		return this.scrollX;
	}

	public void setScrollX(final boolean scrollX) {
		getElement().getStyle().setOverflowX(scrollX ? Overflow.AUTO : Overflow.HIDDEN);
		this.scrollX = scrollX;
	}

	public boolean isScrollY() {
		return this.scrollY;
	}

	public void setScrollY(final boolean scrollY) {
		getElement().getStyle().setOverflowY(scrollY ? Overflow.AUTO : Overflow.HIDDEN);
		this.scrollY = scrollY;
	}

}
