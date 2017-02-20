package com.hello.service;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.hello.wsdl.GetISD;
import com.hello.wsdl.GetISDResponse;


public class StockClient extends WebServiceGatewaySupport {


	public GetISDResponse getQuote(String value) {

		GetISD request = new GetISD();
		request.setCountryName(value);

		System.out.println("Requesting ISD for " + value);

		GetISDResponse response = (GetISDResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.webservicex.net/country.asmx",
						request,
						new SoapActionCallback("http://www.webserviceX.NET/GetISD"));

		return response;
	}

}
