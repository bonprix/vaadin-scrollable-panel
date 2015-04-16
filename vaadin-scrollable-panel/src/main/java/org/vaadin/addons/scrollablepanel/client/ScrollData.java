package org.vaadin.addons.scrollablepanel.client;

import java.io.Serializable;

public class ScrollData implements Serializable {

	private int top = 0;
	private int left = 0;
	private int bottom = 0;
	private int right = 0;
	private int scrollHeight = 0;
	private int scrollWidth = 0;

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

	/**
	 * Returns the top scroll amount in pixels (the number of top pixel rows
	 * that are hidden because of the scroll position).
	 *
	 * @return the top scroll amount in pixels
	 */
	public int getTop() {
		return this.top;
	}

	public void setTop(final int top) {
		this.top = top;
	}

	/**
	 * Returns the left scroll amount in pixels (the number of left pixel
	 * columns that are hidden because of the scroll position).
	 *
	 * @return the left scroll amount in pixels
	 */
	public int getLeft() {
		return this.left;
	}

	public void setLeft(final int left) {
		this.left = left;
	}

	/**
	 * Returns the bottom scroll amount in pixels (the number of bottom pixel
	 * rows that are hidden because of the scroll position).
	 *
	 * @return the bottom scroll amount in pixels
	 */
	public int getBottom() {
		return this.bottom;
	}

	public void setBottom(final int bottom) {
		this.bottom = bottom;
	}

	/**
	 * Returns the right scroll amount in pixels (the number of right pixel
	 * columns that are hidden because of the scroll position).
	 *
	 * @return the right scroll amount in pixels
	 */
	public int getRight() {
		return this.right;
	}

	public void setRight(final int right) {
		this.right = right;
	}

	/**
	 * Returns the height of the scrollable area in pixels.
	 *
	 * @return the height of the scrollable area in pixels
	 */
	public int getScrollHeight() {
		return this.scrollHeight;
	}

	public void setScrollHeight(final int scrollHeight) {
		this.scrollHeight = scrollHeight;
	}

	/**
	 * Returns the width of the scrollable area in pixels.
	 *
	 * @return the width of the scrollable area in pixels
	 */
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
		final ScrollData other = (ScrollData) obj;
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
