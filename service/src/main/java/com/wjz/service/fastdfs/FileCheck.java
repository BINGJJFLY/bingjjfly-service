package com.wjz.service.fastdfs;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>附件类型检查类</b>
 * 
 * @author iss002
 *
 */
public abstract class FileCheck {

	/**
	 * 路径分隔符
	 */
	public static final String SEPARATOR = "/";

	/**
	 * Point
	 */
	public static final String POINT = ".";

	/**
	 * 所有支持的附件类型
	 */
	private static final List<String> ATTACHMENT_ALL_TYPE = new ArrayList<String>();

	/**
	 * 图片类型
	 */
	private static final List<String> TYPE_IMAGE = new ArrayList<>();
	/**
	 * 文档类型
	 */
	private static final List<String> TYPE_DOC = new ArrayList<>();
	/**
	 * 音频类型
	 */
	private static final List<String> TYPE_VIDEO = new ArrayList<>();
	/**
	 * 压缩文件类型
	 */
	private static final List<String> TYPE_COMPRESS = new ArrayList<>();

	// TODO 这些信息写在配置文件中就更好了
	static {

		TYPE_IMAGE.add("png");
		TYPE_IMAGE.add("gif");
		TYPE_IMAGE.add("jpeg");
		TYPE_IMAGE.add("jpg");

		TYPE_DOC.add("pdf");
		TYPE_DOC.add("ppt");
		TYPE_DOC.add("xls");
		TYPE_DOC.add("xlsx");
		TYPE_DOC.add("pptx");
		TYPE_DOC.add("doc");
		TYPE_DOC.add("docx");

		TYPE_VIDEO.add("mp3");
		TYPE_VIDEO.add("mp4");
		TYPE_VIDEO.add("flv");

		TYPE_COMPRESS.add("zip");
		TYPE_COMPRESS.add("rar");

		ATTACHMENT_ALL_TYPE.addAll(TYPE_IMAGE);
		ATTACHMENT_ALL_TYPE.addAll(TYPE_COMPRESS);
		ATTACHMENT_ALL_TYPE.add("pdf");
		ATTACHMENT_ALL_TYPE.add("xls");
		ATTACHMENT_ALL_TYPE.add("xlsx");
	}

	/**
	 * 检查图片类型. <br>
	 * 默认检查 ['png', 'gif', 'jpeg', 'jpg'] 几种类型
	 * 
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkImage(String filename) {
		return checkImage(null, filename);
	}

	/**
	 * 检查图片类型
	 * 
	 * @param types
	 *            可自行传入文件的类型集合，默认检查 ['png', 'gif', 'jpeg', 'jpg'] 几种类型
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkImage(List<String> types, String filename) {
		List<String> checkTypes = types;
		if (types == null || types.size() == 0) {
			checkTypes = TYPE_IMAGE;
		}

		return checkType(checkTypes, filename);
	}

	/**
	 * 检查文档类型. <br>
	 * 默认检查 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型
	 * 
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkDoc(String filename) {
		return checkDoc(null, filename);
	}

	/**
	 * 检查文档类型
	 * 
	 * @param types
	 *            可自行传入文件的类型集合，默认检查 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc',
	 *            'docx'] 几种类型
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkDoc(List<String> types, String filename) {
		List<String> checkTypes = types;
		if (types == null || types.size() == 0) {
			checkTypes = TYPE_DOC;
		}

		return checkType(checkTypes, filename);
	}

	/**
	 * 检查音频类型. <br>
	 * 默认检查 ['mp3', 'mp4', 'flv'] 几种类型
	 * 
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkVideo(String filename) {
		return checkVideo(null, filename);
	}

	/**
	 * 检查音频类型
	 * 
	 * @param types
	 *            可自行传入文件的类型集合，默认检查 ['mp3', 'mp4', 'flv'] 几种类型
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkVideo(List<String> types, String filename) {
		List<String> checkTypes = types;
		if (types == null || types.size() == 0) {
			checkTypes = TYPE_VIDEO;
		}

		return checkType(checkTypes, filename);
	}

	/**
	 * 检查压缩文件类型. <br>
	 * 默认检查 ['zip', 'rar'] 几种类型
	 * 
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkCompress(String filename) {
		return checkCompress(null, filename);
	}

	/**
	 * 检查压缩文件类型
	 * 
	 * @param types
	 *            可自行传入文件的类型集合，默认检查 ['zip', 'rar'] 几种类型
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean checkCompress(List<String> types, String filename) {
		List<String> checkTypes = types;
		if (types == null || types.size() == 0) {
			checkTypes = TYPE_COMPRESS;
		}

		return checkType(checkTypes, filename);
	}

	/**
	 * 检查支持的附件类型
	 * 
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean check(String filename) {
		return check(null, filename);
	}

	/**
	 * 检查支持的附件类型
	 * 
	 * @param types
	 *            可自行传入附件的类型集合
	 * @param filename
	 *            文件名称
	 * @return
	 */
	public static boolean check(List<String> types, String filename) {
		List<String> checkTypes = types;
		if (types == null || types.size() == 0) {
			checkTypes = ATTACHMENT_ALL_TYPE;
		}

		return checkType(checkTypes, filename);
	}

	/**
	 * 检查类型通用方法
	 */
	private static boolean checkType(List<String> checkTypes, String filename) {
		return checkTypes.contains(getFilenameSuffix(filename));
	}

	/**
	 * 获取文件名称的后缀
	 *
	 * @param filename
	 * @return 文件后缀
	 */
	public static String getFilenameSuffix(String filename) {
		String suffix = null;
		if (StringUtils.isNotBlank(filename) && filename.contains(POINT)) {
			suffix = filename.substring(filename.lastIndexOf(POINT) + 1).toLowerCase();
		}
		return suffix;
	}

	/**
	 * 转换路径中的 '\' 为 '/' <br>
	 * 并把文件后缀转为小写
	 *
	 * @param path
	 *            路径
	 * @return
	 */
	public static String toLocal(String path) {
		if (StringUtils.isNotBlank(path)) {
			path = path.replaceAll("\\\\", SEPARATOR);

			if (path.contains(POINT)) {
				String pre = path.substring(0, path.lastIndexOf(POINT) + 1);
				String suffix = path.substring(path.lastIndexOf(POINT) + 1).toLowerCase();
				path = pre + suffix;
			}
		}
		return path;
	}

}
