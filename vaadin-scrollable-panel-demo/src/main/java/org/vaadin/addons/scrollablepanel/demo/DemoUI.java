package org.vaadin.addons.scrollablepanel.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.scrollablepanel.ScrollablePanel;
import org.vaadin.addons.scrollablepanel.ScrollablePanel.ScrollEvent;
import org.vaadin.addons.scrollablepanel.ScrollablePanel.ScrollListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
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

		final GridLayout scrollContent = new GridLayout(50, 50);

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				final Label label = new Label("Label number " + i + "x" + j);
				label.setHeight(300, Unit.PIXELS);
				label.setWidth(300, Unit.PIXELS);
				scrollContent.addComponent(label, i, j);
			}
		}

		// Initialize our new UI component
		final ScrollablePanel scrollablePanel = new ScrollablePanel(scrollContent);
		scrollablePanel.setSizeFull();

		final Slider scrollTopSlider = new Slider("Top scroll position", 0, 1000);
		final Slider scrollLeftSlider = new Slider("Left scroll position", 0, 1000);

		final ValueChangeListener scrollOptionChangeListener = new ValueChangeListener() {

			@Override
			public void valueChange(final ValueChangeEvent event) {
				final int scrollTop = scrollTopSlider.getValue().intValue();
				final int scrollLeft = scrollLeftSlider.getValue().intValue();

				scrollablePanel.setScrollTop(scrollTop);
				scrollablePanel.setScrollLeft(scrollLeft);
			}
		};

		scrollTopSlider.addValueChangeListener(scrollOptionChangeListener);
		scrollTopSlider.setWidth(100, Unit.PERCENTAGE);
		scrollLeftSlider.addValueChangeListener(scrollOptionChangeListener);
		scrollLeftSlider.setWidth(100, Unit.PERCENTAGE);

		final Label scrollTopLabel = createCaptionLabel("Top scroll position:");
		final Label scrollLeftLabel = createCaptionLabel("Left scroll position:");
		final Label scrollRightLabel = createCaptionLabel("Right scroll position:");
		final Label scrollBottomLabel = createCaptionLabel("Bottom scroll position:");
		final Label scrollWidthLabel = createCaptionLabel("Width of scrolling area:");
		final Label scrollHeightLabel = createCaptionLabel("Height of scrolling area:");

		final VerticalLayout scrollOptionsLayout = new VerticalLayout(scrollTopSlider, scrollLeftSlider);
		final FormLayout scrollDataLayout = new FormLayout(scrollTopLabel, scrollLeftLabel, scrollRightLabel, scrollBottomLabel, scrollWidthLabel,
				scrollHeightLabel);
		scrollDataLayout.setMargin(false);
		scrollDataLayout.setSpacing(false);

		final HorizontalLayout optionsLayout = new HorizontalLayout(scrollOptionsLayout, scrollDataLayout);
		optionsLayout.setSpacing(true);
		optionsLayout.setMargin(true);
		optionsLayout.setWidth(100, Unit.PERCENTAGE);

		// Show it in the middle of the screen
		final VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		layout.setSpacing(true);
		layout.addComponent(optionsLayout);
		layout.addComponent(scrollablePanel);
		layout.setExpandRatio(scrollablePanel, 1);
		setContent(layout);

		scrollablePanel.addScrollListener(new ScrollListener() {

			@Override
			public void onScroll(final ScrollEvent event) {
				scrollTopLabel.setValue(Integer.toString(event.getTop()));
				scrollLeftLabel.setValue(Integer.toString(event.getLeft()));
				scrollRightLabel.setValue(Integer.toString(event.getScrollData().getRight()));
				scrollBottomLabel.setValue(Integer.toString(event.getScrollData().getBottom()));
				scrollWidthLabel.setValue(Integer.toString(event.getScrollWidth()));
				scrollHeightLabel.setValue(Integer.toString(event.getScrollHeight()));
			}
		});

	}

	private static Label createCaptionLabel(final String caption) {
		final Label label = new Label();
		label.setCaption(caption);
		return label;

	}

}
