package com.wjz.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.exception.ControllerException;

/**
 * <b>Ini文件内容获取工具类</b>
 * 
 * @author iss002
 *
 */
public abstract class IniUtils {

	private static final Logger log = LoggerFactory.getLogger(IniUtils.class);

	public static Ini loadIni(String resourcePath) {
		final Ini ini = new Ini();

		InputStream is = null;
		try {

			if (log.isDebugEnabled()) {
				log.debug("Opening input stream for path [" + resourcePath + "]...");
			}

			is = ResourceUtils.getInputStreamForPath(resourcePath);

			if (log.isDebugEnabled()) {
				log.debug("Loading ini from path [" + resourcePath + "]...");
			}

			ini.load(is);
		} catch (IOException e) {
			throw new ControllerException("Error reading ini path [" + resourcePath + "].", e);
		} finally {
			ResourceUtils.close(is);
		}
		return ini;
	}

	public static Ini fromResourcePath(String resourcePath) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Loading ini from path [" + resourcePath + "]...");
			}

			return Ini.fromResourcePath(resourcePath);
		} catch (Exception e) {
			throw new ControllerException("Error reading ini path [" + resourcePath + "].", e);
		}
	}

	public static Map<String, String> ini2Map(Ini ini) {
		return ini2Map(ini, Ini.DEFAULT_SECTION_NAME);
	}

	public static Map<String, String> ini2Map(Ini ini, String sectionName) {
		Map<String, String> result = null;
		if (ini != null) {
			Ini.Section section = ini.getSection(sectionName);
			if (CollectionUtils.isEmpty(section)) {
				section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
			}
			Map<String, String> map = section;

			if (!CollectionUtils.isEmpty(map)) {
				result = new LinkedHashMap<>();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String key = entry.getKey().trim();
					String value = entry.getValue().trim();
					result.put(key, value);

					if (log.isTraceEnabled()) {
						log.trace("Processing ini - key: [" + key + "], value: [" + value + "].");
					}
				}
			}
			return result;
		}
		return null;
	}
}
