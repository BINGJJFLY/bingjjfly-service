package com.wjz.service.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.SimpleCookie;

/**
 * <b>Cookie工具类</b>
 *
 * @author iss002
 *
 */
public abstract class CookieUtils {

	/**
	 * 获得指定Cookie的值
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		final SimpleCookie cookie = new SimpleCookie(cookieName);
		return cookie.readValue(request, null);
	}

	/**
	 * 创建简单Cookie
	 * <p>
	 * {name:cookieName, value:cookieValue, maxAge:-1, version:-1, httpOnly:true}
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue) {
		final SimpleCookie cookie = new SimpleCookie();
		cookie.setName(cookieName);
		cookie.setValue(cookieValue);
		cookie.saveTo(request, response);
	}

	/**
	 * 删除指定Cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		final SimpleCookie cookie = new SimpleCookie();
		cookie.setName(cookieName);
		cookie.setMaxAge(0);
		cookie.saveTo(request, response);
	}

	/**
	 * 创建指定属性值的Cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param comment
	 * @param domain
	 * @param path
	 * @param maxAge
	 * @param version
	 * @param secure
	 * @param httpOnly
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			String comment, String domain, String path, int maxAge, int version, boolean secure, boolean httpOnly) {
		final SimpleCookie cookie = new SimpleCookie();
		cookie.setName(name);
		cookie.setValue(value);
		cookie.setComment(comment);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setVersion(version);
		cookie.setSecure(secure);
		cookie.setHttpOnly(httpOnly);
		cookie.saveTo(request, response);
	}
}
