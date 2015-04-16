package org.vaadin.addons.scrollablepanel.client;

import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class MyComponentWidget extends Label {

	public MyComponentWidget() {

		// CSS class-name should not be v- prefixed
		setStyleName("vaadin-scrollable-panel");

		// State is set to widget in MyComponentConnector		
	}

}