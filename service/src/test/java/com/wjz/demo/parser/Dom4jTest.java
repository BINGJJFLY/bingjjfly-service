package com.wjz.demo.parser;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public class Dom4jTest {
	
	@Test
	public void parse() {
		try {
			HttpServletRequest req = null;
			InputStream is = req.getInputStream();
			byte[] bs = new byte[is.available()];
			is.read(bs);
			Document document = DocumentHelper.parseText(new String(bs, "UTF-8"));
			Element root = document.getRootElement();
			Element head = root.element("Head");
			String tOrderCode = head.elementTextTrim("TradeOrderCode");
		} catch (Exception e) {
			
		}
	}

	@Test
	public void jaxb() {
		try {
			JaxbTest jaxb = new JaxbTest();
			Document document = DocumentHelper.parseText(jaxb.jaxb());
			Element root = document.getRootElement();
			Element head = root.element("Head");
			String tOrderCode = head.elementTextTrim("TradeOrderCode");
			System.out.println(tOrderCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
