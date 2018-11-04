package com.wjz.service.jackson;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class AbstractJackson {

	private static final Logger log = LoggerFactory.getLogger(AbstractJackson.class);

	private HttpServletResponse response;

	/**
	 * 将目标对象转换为Json形式字符串并输入到输出流中
	 * 
	 * @param target
	 *            目标对象
	 */
	protected void writeJson(Object target) {
		response.addHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			final ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			ObjectWriter objectWriter = objectMapper.writer();
			objectWriter.writeValue(jsonGenerator, target);

			jsonGenerator.flush();
			outputStream.flush();
		} catch (IOException e) {
			log.error("【将目标对象转换为Json形式字符串并输入到输出流中时异常】", e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					outputStream = null;
				}
			}
		}
	}

	/**
	 * 将目标对象转换为Json形式字节数组并返回
	 * 
	 * @param target
	 *            目标对象
	 * @return
	 */
	protected byte[] returnJsonAsBytes(Object target) {
		byte[] bytes = null;
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			bytes = objectMapper.writeValueAsBytes(target);
		} catch (JsonProcessingException e) {
			log.error("【将目标对象转换为Json形式字节数组并返回时异常】", e);
		}
		return bytes;
	}

	/**
	 * 将目标对象转换为Json形式字符串并返回
	 * 
	 * @param target
	 *            目标对象
	 * @return
	 */
	protected String returnJsonAsString(Object target) {
		byte[] bytes = returnJsonAsBytes(target);
		return new String(bytes);
	}

	/**
	 * 将Json形式字节数组转换为目标类型的对象
	 * 
	 * @param bytes
	 *            字节数组
	 * @param type
	 *            目标类型
	 * @return
	 */
	protected <T> T readJson2Instance(byte[] bytes, Class<T> type) {
		T instance = null;
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			instance = objectMapper.readValue(bytes, type);
		} catch (IOException e) {
			log.error("【将Json形式字节数组转换为目标类型的对象】", e);
		}
		return instance;
	}

	/**
	 * 将Json形式字符串转换为目标类型的对象
	 * 
	 * @param content
	 * @param type
	 * @return
	 */
	protected <T> T readJson2Instance(String content, Class<T> type) {
		return readJson2Instance(content.getBytes(), type);
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
