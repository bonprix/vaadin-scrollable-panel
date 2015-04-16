package org.vaadin.addons.scrollablepanel.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScrollDetail implements Serializable {

	private int top = 0, left = 0, bottom = 0, right = 0, scrollHeight = 0, scrollWidth = 0;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("top: ").append(getTop()).append("\n");
		sb.append("left: ").append(getLeft()).append("\n");
		sb.append("bottom: ").append(getBottom()).append("\n");
		sb.append("right: ").append(getRight()).append("\n");
		sb.append("scrollHeight: ").append(getScrollHeight()).append("\n");
		sb.append("scrollWidth: ").append(getScrollWidth()).append("\n");

		return sb.toString();
	}

	public int getTop() {
		return this.top;
	}

	public void setTop(final int top) {
		this.top = top;
	}

	public int getLeft() {
		return this.left;
	}

	public void setLeft(final int left) {
		this.left = left;
	}

	public int getBottom() {
		return this.bottom;
	}

	public void setBottom(final int bottom) {
		this.bottom = bottom;
	}

	public int getRight() {
		return this.right;
	}

	public void setRight(final int right) {
		this.right = right;
	}

	public int getScrollHeight() {
		return this.scrollHeight;
	}

	public void setScrollHeight(final int scrollHeight) {
		this.scrollHeight = scrollHeight;
	}

	public int getScrollWidth() {
		return this.scrollWidth;
	}

	public void setScrollWidth(final int scrollWidth) {
		this.scrollWidth = scrollWidth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.left;
		result = prime * result + this.scrollHeight;
		result = prime * result + this.scrollWidth;
		result = prime * result + this.top;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ScrollDetail other = (ScrollDetail) obj;
		if (this.left != other.left) {
			return false;
		}
		if (this.scrollHeight != other.scrollHeight) {
			return false;
		}
		if (this.scrollWidth != other.scrollWidth) {
			return false;
		}
		if (this.top != other.top) {
			return false;
		}
		return true;
	}

}
