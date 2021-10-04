package com.liu.webui.client.webui.model;

public abstract class CWebUI_AbstractCommonUI {

	// This is the location of element,
	// including left,top,width,height.
	int left;
	int top;
	int width;
	int height;

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setBounds(int ileft, int itop, int iwidth, int iheight) {
		this.left = ileft;
		this.top = itop;
		this.width = iwidth;
		this.height = iheight;

	}
}
