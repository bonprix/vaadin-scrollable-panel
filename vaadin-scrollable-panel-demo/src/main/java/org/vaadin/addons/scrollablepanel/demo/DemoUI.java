package org.vaadin.addons.scrollablepanel.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.scrollablepanel.ScrollablePanel;
import org.vaadin.addons.scrollablepanel.ScrollablePanel.ScrolledEvent;
import org.vaadin.addons.scrollablepanel.ScrollablePanel.ScrolledListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("ScrollablePanel Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addons.scrollablepanel.demo.DemoWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(final VaadinRequest request) {

		final VerticalLayout scrollContent = new VerticalLayout();

		for (int i = 0; i < 50; i++) {
			final Label label = new Label("Label number " + i);
			label.setHeight(50, Unit.PIXELS);
			scrollContent.addComponent(label);
		}

		// Initialize our new UI component
		final ScrollablePanel component = new ScrollablePanel(scrollContent);
		component.setSizeFull();

		component.addScrolledListener(new ScrolledListener() {

			@Override
			public void scrolled(final ScrolledEvent event) {
				Notification.show(event.getDetail().toString());
			}
		});

		// Show it in the middle of the screen
		final VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		layout.addComponent(component);
		setContent(layout);

	}

}
