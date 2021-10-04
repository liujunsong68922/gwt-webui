package com.liu.webui.client;

import com.liu.webui.client.webui.controller.CWebUI_Create;
import com.liu.webui.client.webui.model.CWebUI_RootFlowPanel;
import com.liu.webui.client.webui.view.CWebUI_DrawFunction;
import com.liu.webui.shared.FieldVerifier;

//import java.awt.TextField;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebUIApp implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private final String stext1 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
 +"<Root>"
 +"<FlowPanel>"
 +"<VerticalPanel>"
 +"<Label name='a' value='UI界面绘图'></Label>"	 
 +"<Button name='ABC' onClicked='alert(1234)' value='test button'></Button>"
 +"<HTML>hello,world</HTML>"
 +"<Image url='images/m.jpg'></Image>"
 +"<TextBox name='a' value='TextBox'></TextBox>"
 +"<TextBox name='b' value='TextBox'></TextBox>"
 +"<TextBox name='c' value='TextBox'></TextBox>"
 +"</VerticalPanel>"
 +"</FlowPanel>"
 +"</Root>";     

  private final String stext2 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
 +"<Root>"
 +"<FlowPanel>"
 +"<VerticalPanel>"
 +"<Label name='a' value='UI界面绘图'></Label>"	 
 +"<Button name='ABC' onClicked='alert(1234)' value='test button'></Button>"
 +"<HTML>hello,world</HTML>"
 +"<Image url='images/m.jpg'></Image>"
 +"<TextBox name='a' value='TextBox'></TextBox>"
 +"</VerticalPanel>"
 +"</FlowPanel>"
 +"</Root>";     

  private final String stext3 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
 +"<Root>"
 +"<FlowPanel>"
// +"<VerticalPanel>"
 +"<Label name='a' value='UI界面绘图'></Label>"	 
 +"<Button name='ABC' onClicked='alert(1234)' value='test button'></Button>"
 +"<HTML>hello,world</HTML>"
 +"<Image url='images/m.jpg'></Image>"
// +"</VerticalPanel>"
 +"</FlowPanel>"
 +"</Root>";     

  private final String stext4 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
 +"<Root>"
 +"<FlowPanel>"
 +"<VerticalPanel>"
 +"<Image url='images/m.jpg'></Image>"
 +" </VerticalPanel>"
 +"</FlowPanel>"
 +"</Root>";     
  
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button sendButton = new Button("Draw It (界面绘图)");
    final Button text1button = new Button("text1");
    final Button text2button = new Button("text2");
    final Button text3button = new Button("text3");
    final Button text4button = new Button("text4");
    

    
    final TextBox nameField = new TextBox();
    final TextArea inputtextfield = new TextArea();
    
    text1button.addClickListener(new ClickListener() {

		@Override
		public void onClick(Widget sender) {
			inputtextfield.setText(stext1);
		}});

    text2button.addClickListener(new ClickListener() {

		@Override
		public void onClick(Widget sender) {
			inputtextfield.setText(stext2);
		}});
    text3button.addClickListener(new ClickListener() {

		@Override
		public void onClick(Widget sender) {
			inputtextfield.setText(stext3);
		}});
    text4button.addClickListener(new ClickListener() {

		@Override
		public void onClick(Widget sender) {
			inputtextfield.setText(stext4);
		}});

    nameField.setText("GWT User");
    final Label errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");
  
	  String stext = stext1;  
    
	 inputtextfield.setWidth("600px");
	 inputtextfield.setHeight("100px");
	 RootPanel.get("inputtextContainer").add(inputtextfield); 
	 inputtextfield.setText(stext);
	 
	 CWebUI_Create cc =  new CWebUI_Create();
  	
    sendButton.addClickListener(new ClickListener() {
   
		@Override
		public void onClick(Widget sender) {
			Window.alert("Click Me!");
			
			String stext = inputtextfield.getText();
			
			CWebUI_RootFlowPanel objvo = cc.createByXMLString(stext);
			consoleLog(objvo);
			Window.alert(objvo.toJsonStrig(objvo));
			
			CWebUI_DrawFunction drawfunction = new CWebUI_DrawFunction();
			RootPanel rootpanel = RootPanel.get("dynamicuipanel");
			
			//绘图
			drawfunction.draw(rootpanel, objvo);
			
			
		}});
    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("button1Container").add(text1button);
    RootPanel.get("button2Container").add(text2button);
    RootPanel.get("button3Container").add(text3button);
    RootPanel.get("button4Container").add(text4button);
  }
  
	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "CWebDWUI_ParentDW:" + message );
	}-*/;

	public native void consoleLog(Object object) /*-{
	//alert(message);
	console.log( object );
	}-*/;	
}
