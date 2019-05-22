package com.wjz.service.utils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class FreemarkerUtils {
	
	private static final Logger log = LoggerFactory.getLogger(FreemarkerUtils.class);
	
	private static Configuration getConfiguration(String dir) throws IOException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDirectoryForTemplateLoading(new File(dir));
		return configuration;
	}

	public static void print(Writer out, String dir, String templateName, Object data) throws Exception {
		try {
			Template template = getConfiguration(dir).getTemplate(templateName);
			template.process(data, out);
			out.flush();
		} catch (Exception e) {
			log.error("【根据路径获得Freemarker模板时异常】：目录[{}]，文件名[{}]", dir, templateName, e);
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
