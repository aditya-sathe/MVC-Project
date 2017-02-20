package com.mymvc.ws.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.hello.service.StockClient;
import com.hello.wsdl.GetISDResponse;
import com.mymvc.ws.model.DataModel;


@Controller
public class HomeController {
	
	@Autowired
	public StockClient client;
	
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/stockquote")
	@ResponseBody
	public String getQuote(String code) throws IOException{
		GetISDResponse resp = client.getQuote(code);
		String qresp = resp.getGetISDResult();
		System.out.println("Resp: " + qresp); 
		if(qresp.equalsIgnoreCase("exception")) 
			throw new IOException("failed");
		return getISD(qresp);
	}
	
	@RequestMapping(value="/restpost", method= RequestMethod.POST , produces = "application/json")
	@ResponseBody
	public String getDataPost() throws IOException{
		String uri = "http://jsonplaceholder.typicode.com/posts";
		String data = "{title: 'foo',body: 'bar',userId: 1}";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject( uri, data, String.class);
	 
	    System.out.println(result);
		return result;
	}
	
	@RequestMapping(value="/restput", method= RequestMethod.PUT , produces = "application/json")
	@ResponseBody
	public void getDataPut() throws IOException{
		String uri = "http://jsonplaceholder.typicode.com/posts/1";
		String data = "{id:1, title:'foo', body:'bar', userId: 1}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put( uri, data);
	}
	
	private String getISD(String respXML){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		String isd = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(new ByteArrayInputStream(respXML.getBytes()));
		    
		    XPath xPath =  XPathFactory.newInstance().newXPath();
		    String expression = "/NewDataSet/Table/code";
		    //read a string value
		    isd = xPath.compile(expression).evaluate(document);
		} catch (Exception e) {
		    e.printStackTrace();  
		}
		return isd;
	}
}
