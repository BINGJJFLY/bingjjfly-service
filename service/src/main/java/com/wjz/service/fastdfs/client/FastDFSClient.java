package com.wjz.service.fastdfs.client;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;

/**
 * <b>FastDFS客户端接口</b>
 * <p>
 * 具备文件的上传、下载、获得文件信息、删除功能
 * </p>
 * 
 * @author iss002
 *
 */
public interface FastDFSClient {

	String upload(byte[] buff, String suffix, NameValuePair[] metas);

	byte[] download(String path);

	int delete(String path);

	FileInfo getInfo(String path);
}
