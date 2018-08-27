package com.wjz.service.fastdfs.client;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wjz.service.fastdfs.FastDFSException;
import com.wjz.service.fastdfs.TrackerServerPool;

/**
 * <b>默认的 <tt>FastDFSClient</tt> 实现类</b>
 * 
 * @author iss002
 *
 */
public class DefaultFastDFSClient implements FastDFSClient {

	private static final Logger log = LoggerFactory.getLogger(DefaultFastDFSClient.class);

	private StorageClient1 getStorageClient(TrackerServer trackerServer) {
		return new StorageClient1(trackerServer, null);
	}

	private TrackerServer borrowTrackerServer() throws FastDFSException {
		try {
			return TrackerServerPool.borrowObject();
		} catch (Exception e) {
			log.error("【从对象池中获得对象时异常】", e);
			throw new FastDFSException(e);
		}
	}

	private void returnTrackerServer(TrackerServer trackerServer) {
		try {
			TrackerServerPool.returnObject(trackerServer);
		} catch (Exception e) {
			log.error("【将对象还给对象池时异常】", e);
			throw new FastDFSException(e);
		}
	}

	@Override
	public String upload(byte[] buff, String suffix, NameValuePair[] metas) {
		String path = null;
		try {
			TrackerServer trackerServer = borrowTrackerServer();
			path = getStorageClient(trackerServer).upload_file1(buff, suffix, metas);
			returnTrackerServer(trackerServer);
		} catch (IOException | MyException e) {
			throw new FastDFSException(e);
		}
		return path;
	}

	@Override
	public byte[] download(String path) {
		byte[] bs = null;
		try {
			TrackerServer trackerServer = borrowTrackerServer();
			bs = getStorageClient(trackerServer).download_file1(path);
			returnTrackerServer(trackerServer);
		} catch (IOException | MyException e) {
			throw new FastDFSException(e);
		}
		return bs;
	}

	@Override
	public int delete(String path) {
		int result;
		try {
			TrackerServer trackerServer = borrowTrackerServer();
			result = getStorageClient(trackerServer).delete_file1(path);
			returnTrackerServer(trackerServer);
		} catch (IOException | MyException e) {
			throw new FastDFSException(e);
		}
		return result;
	}

	@Override
	public FileInfo getInfo(String path) {
		FileInfo info = null;
		try {
			TrackerServer trackerServer = borrowTrackerServer();
			info = getStorageClient(trackerServer).get_file_info1(path);
			returnTrackerServer(trackerServer);
		} catch (IOException | MyException e) {
			throw new FastDFSException(e);
		}
		return info;
	}

}
