# ScrollablePanel Add-on for Vaadin 7

The ScrollablePanel is a panel layout component which allows the user to read the current scroll position of the scrollable content.

Features:
- scroll notification listener: get notified on server-side when the user scrolls the content
- scroll control: set the scroll position from server-side
- theme: no further default CSS-styling, no borders, captions and paddings (unlike the default Panel component)


## Online demo

Pending...

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/vaadin-scrollable-panel

## Usage

### Maven

```xml
<dependency>
    <groupId>org.vaadin.addons</groupId>
	<artifactId>vaadin-scrollable-panel</artifactId>
	<version>1.0</version>
</dependency>
```

###Widgetset
```xml
<inherits name="org.vaadin.addons.scrollablepanel.WidgetSet" />
```

## Building and running demo

git clone https://github.com/bonprix/vaadin-scrollable-panel
mvn clean install
cd vaadin-scrollable-panel-demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/
 
## Release notes

### Version 1.0
- initial release of the addon.

## Roadmap

There is no future roadmap for this widget.

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under MIT License. For license terms, see LICENSE.txt.

vaadin-scrollable-panel is written by members of Bonprix Handelsgesellschaft mbh:
- Christian Thiel

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java

// Initialize the scroll panel with a inner component bigger than the available space
final ScrollablePanel scrollablePanel = new ScrollablePanel(scrollContent);
scrollablePanel.setSizeFull();

scrollablePanel.setScrollTop(100); // set some vertical scroll position

// add a scroll listener
scrollablePanel.addScrollListener(new ScrollListener() {
	
	@Override
	public void onScroll(final ScrollEvent event) {
		Notification.show("Scrolled: " + event.getScrollData().toString());
	}
});


```

For a more comprehensive example, see src/main/java/org/vaadin/addons/scrollablepanel/demo/DemoUI.java
