package com.wjz.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.exception.ControllerException;

/**
 * <b>Properties文件内容获取工具类</b>
 * 
 * @author iss002
 *
 */
public abstract class PropertiesUtils {

	private static final Logger log = LoggerFactory.getLogger(PropertiesUtils.class);

	public static Properties loadProperties(String resourcePath) {
		return loadProperties(resourcePath, false);
	}

	public static Properties loadProperties(String resourcePath, boolean useXmlFormat) {
		final Properties props = new Properties();

		InputStream is = null;
		try {

			if (log.isDebugEnabled()) {
				log.debug("Opening input stream for path [" + resourcePath + "]...");
			}

			is = ResourceUtils.getInputStreamForPath(resourcePath);
			if (useXmlFormat) {

				if (log.isDebugEnabled()) {
					log.debug("Loading properties from path [" + resourcePath + "] in XML format...");
				}

				props.loadFromXML(is);
			} else {

				if (log.isDebugEnabled()) {
					log.debug("Loading properties from path [" + resourcePath + "]...");
				}

				props.load(is);
			}

		} catch (IOException e) {
			throw new ControllerException("Error reading properties path [" + resourcePath + "].", e);
		} finally {
			ResourceUtils.close(is);
		}

		return props;
	}

	public static Map<String, String> properties2Map(Properties prop) {
		String s = properties2String(prop);
		Set<String> keyValuePairs = toLines(s);

		if (keyValuePairs == null || keyValuePairs.isEmpty()) {
			return null;
		}

		final Map<String, String> pairs = new HashMap<String, String>();
		try {
			for (String pairString : keyValuePairs) {
				String[] pair = StringUtils.splitKeyValue(pairString);
				if (pair != null) {
					pairs.put(pair[0].trim(), pair[1].trim());
				}
			}
		} catch (ParseException e) {
			throw new ControllerException("Resolve Properties file to java.util.Map failed.", e);
		}
		return pairs;
	}

	@SuppressWarnings("resource")
	public static Set<String> toLines(String s) {
		Set<String> set = null;
		if (!org.springframework.util.StringUtils.isEmpty(s)) {
			set = new LinkedHashSet<String>();
			final Scanner scanner = new Scanner(s);
			while (scanner.hasNextLine()) {
				set.add(scanner.nextLine());
			}
		}
		return set;
	}

	@SuppressWarnings("unchecked")
	private static String properties2String(Properties prop) {
		final StringBuilder propStr = new StringBuilder();

		Enumeration<String> propNames = (Enumeration<String>) prop.propertyNames();

		while (propNames.hasMoreElements()) {

			String key = propNames.nextElement().trim();
			String value = prop.getProperty(key).trim();
			if (log.isTraceEnabled()) {
				log.trace("Processing properties line - key: [" + key + "], value: [" + value + "].");
			}

			propStr.append(key).append(" = ").append(value).append("\n");
		}

		return propStr.toString();
	}
}
