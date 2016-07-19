package com.cts.eas.ipm.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CXFWSIntegration extends RouteBuilder {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CXFWSIntegration.class);

	@Override
	public void configure() throws Exception {
		from("cxf:bean:greetWS")
			.log(LoggingLevel.INFO,LOGGER,"${body}")
			.bean(this, "processRequest")
		.end();
	}
	
	public String processRequest(Document doc){
		Node node = doc.getElementsByTagName("name").item(0);
	    String name = node.getTextContent();
	    String response = "<ns2:sayHelloResponse xmlns:ns2=\"http://www.ipm.eas.cts.com/api/ws/greet\">"
							+"<response>Hello "+name+", how are you</response>"
						 +"</ns2:sayHelloResponse>";
	    return response;
	}
}