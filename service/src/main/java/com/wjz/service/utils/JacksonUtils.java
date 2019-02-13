package com.wjz.service.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import net.sf.json.JSONNull;

/**
 * <b>Json序列化工具类</b>
 *
 * @author iss002
 *
 */
public abstract class JacksonUtils {

	private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);

	/**
	 * 将目标对象转换为Json形式字符串
	 * 
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static String writeValueAsString(Object target) throws Exception {
		if (target != null) {
			final ObjectMapper objectMapper = new ObjectMapper();
			try {
				return objectMapper.writeValueAsString(target);
			} catch (JsonProcessingException e) {
				log.error("【序列化对象为Json形式的字符串时异常】", e);
				throw e;
			}
		}
		return null;
	}

	/**
	 * 将目标对象转换为Json形式字符串且过滤 {@code null} 为 {@code ""}
	 * 
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static String writeValueAsStringNotNull(Object target) throws Exception {
		String result = writeValueAsString(target);
		if (result != null) {
			final JSONObject jsonObject = new JSONObject(result);
			result = filterNull(jsonObject).toString();
		}
		return result;
	}

	private static JSONObject filterNull(JSONObject jsonObject) {
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			Object value = jsonObject.get(key);
			filterNull(key, value, jsonObject);
		}
		return jsonObject;
	}

	private static void filterNull(String key, Object value, JSONObject jsonObject) {
		if (value instanceof JSONObject) {
			filterNull(jsonObject);
		} else if (value instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) value;
			for (int i = 0; i < jsonArray.length(); i++) {
				Object jsonObj = jsonArray.get(i);
				if (jsonObj instanceof JSONObject) {
					filterNull((JSONObject) jsonObj);
				}
			}
		} else {
			notNull(key, value, jsonObject);
		}
	}

	private static void notNull(String key, Object value, JSONObject jsonObject) {
		if (value == null || value.equals(null) || value instanceof JSONNull) {
			jsonObject.put(key, "");
		}
	}

	/**
	 * 将目标对象转换为Json形式字节数组并返回
	 * 
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static byte[] writeValueAsBytes(Object target) throws Exception {
		byte[] bytes = null;
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			bytes = objectMapper.writeValueAsBytes(target);
		} catch (JsonProcessingException e) {
			log.error("【将目标对象转换为Json形式字节数组并返回时异常】", e);
			throw e;
		}
		return bytes;
	}

	/**
	 * 将目标对象转换为Json形式字符串并输入到输出流中
	 * 
	 * @param outputStream
	 * @param target
	 * @throws Exception
	 */
	public static void writeValueAsStream(OutputStream outputStream, Object target) throws Exception {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			ObjectWriter objectWriter = objectMapper.writer();
			objectWriter.writeValue(jsonGenerator, target);

			jsonGenerator.flush();
			outputStream.flush();
		} catch (IOException e) {
			log.error("【将目标对象转换为Json形式字符串并输入到输出流中时异常】", e);
			throw e;
		}
	}

	/**
	 * 将Json形式字节数组转换为目标类型的对象
	 * 
	 * @param bytes
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Object readValue(byte[] bytes, Class<?> type) throws Exception {
		Object instance = null;
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			instance = objectMapper.readValue(bytes, type);
		} catch (IOException e) {
			log.error("【将Json形式字节数组转换为目标类型的对象】", e);
			throw e;
		}
		return instance;
	}

	/**
	 * 将Json形式字符串转换为目标类型的对象
	 * 
	 * @param content
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Object readValue(String content, Class<?> type) throws Exception {
		return readValue(content.getBytes(), type);
	}

}
