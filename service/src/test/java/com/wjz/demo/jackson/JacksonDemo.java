package com.wjz.demo.jackson;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.ALL;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JacksonDemo {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		HttpServletResponse response = null;
		OutputStream outputStream = null;
		try {
			response.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

			User user = new User();
			outputStream = response.getOutputStream();
			// 在默认情况下（即不对ObjectMapper做任何额外配置，也不对Java对象加任何Annotation），
			// ObjectMapper依赖于Java对象的默认的无参构造函数进行反序列化，
			// 并且严格地通过getter和setter的命名规约进行序列化和反序列化。
			ObjectMapper objectMapper = new ObjectMapper();
			// 纯粹地为了技术方面的原因而添加getter和setter是不好的，可以通过以下方式去除掉对getter和setter的依赖
			// ObjectMapper将通过反射机制直接操作Java对象上的字段
			objectMapper.setVisibility(ALL, NONE)
					.setVisibility(FIELD, ANY);
			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
			ObjectWriter objectWriter = objectMapper.writer();
			objectWriter.writeValue(jsonGenerator, user);
			jsonGenerator.flush();
			outputStream.flush();
			
			String content = objectMapper.writeValueAsString(user);
			JsonNode jsonNode = objectMapper.readTree(content);
			Iterator<Entry<String, JsonNode>> fields = jsonNode.fields();
			Map<String, String> map = new HashMap<>();
			while (fields.hasNext()) {
				Entry<String, JsonNode> field = fields.next();
				map.put(field.getKey(), field.getValue().asText());
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	static class User {

	}
}
