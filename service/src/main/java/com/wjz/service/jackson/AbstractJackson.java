package com.wjz.service.jackson;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.wjz.service.exception.ControllerException;
import com.wjz.service.utils.JacksonUtils;

/**
 * <b>Json序列化类</b>
 *
 * @author iss002
 *
 */
public abstract class AbstractJackson {

	private HttpServletResponse response;

	/**
	 * 将目标对象转换为Json形式字符串
	 * 
	 * @param target
	 * @return
	 * @throws ControllerException
	 */
	protected String writeValueAsString(Object target) throws ControllerException {
		try {
			return JacksonUtils.writeValueAsString(target);
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

	/**
	 * 将目标对象转换为Json形式字符串且过滤 {@code null} 为 {@code ""}
	 * 
	 * @param target
	 * @return
	 * @throws ControllerException
	 */
	protected String writeValueAsStringNotNull(Object target) throws ControllerException {
		try {
			return JacksonUtils.writeValueAsStringNotNull(target);
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

	/**
	 * 将目标对象转换为Json形式字符串并输入到输出流中
	 * 
	 * @param target
	 * @throws ControllerException
	 */
	protected void writeValueAsStream(Object target) throws ControllerException {
		response.addHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			JacksonUtils.writeValueAsStream(outputStream, target);
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
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
	 * 将Json形式字节数组转换为目标类型的对象
	 * 
	 * @param bytes
	 * @param type
	 * @return
	 * @throws ControllerException
	 */
	@SuppressWarnings("unchecked")
	protected <T> T readValue(byte[] bytes, Class<T> type) throws ControllerException {
		try {
			return (T) JacksonUtils.readValue(bytes, type);
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

	/**
	 * 将Json形式字符串转换为目标类型的对象
	 * 
	 * @param content
	 * @param type
	 * @return
	 * @throws ControllerException
	 */
	protected <T> T readValue(String content, Class<T> type) throws ControllerException {
		return readValue(content.getBytes(), type);
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
