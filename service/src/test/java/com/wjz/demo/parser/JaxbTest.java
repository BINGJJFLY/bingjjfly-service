package com.wjz.demo.parser;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

public class JaxbTest {

	@Test
	public void create() {
		System.out.println(jaxb());
	}

	public String jaxb() {
		Request request = new Request();
		Header header = new Header();
		header.setTradeOrderCode("YFK20190523001");
		request.setHeader(header);
		request.setSignature("akwe172321kdmvz82");
		try {
			return beanToXml(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String beanToXml(Object obj) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringWriter writer = new StringWriter();
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		marshaller.marshal(obj, writer);
		return writer.toString();
	}

	@XmlRootElement(name = "Request")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Request {

		@XmlElement(name = "Head")
		private Header header;

		@XmlElement(name = "Signature")
		private String signature;

		public Header getHeader() {
			return header;
		}

		public void setHeader(Header header) {
			this.header = header;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

	}

	@XmlRootElement(name = "Head")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Header {

		@XmlElement(name = "TradeOrderCode")
		private String tradeOrderCode;

		public String getTradeOrderCode() {
			return tradeOrderCode;
		}

		public void setTradeOrderCode(String tradeOrderCode) {
			this.tradeOrderCode = tradeOrderCode;
		}

	}
}
