package com.wjz.service.fastdfs;

import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.wjz.service.pool.AbstractBasePooledObjectFactory;

/**
 * <b>{@code TrackerServer} 工厂类</b>
 * 
 * @author iss002
 *
 */
public class TrackerServerFactory extends AbstractBasePooledObjectFactory<TrackerServer> {

	@Override
	public TrackerServer create() throws Exception {
		// TrackerClient
		TrackerClient trackerClient = new TrackerClient();
		// TrackerServer
		TrackerServer trackerServer = trackerClient.getConnection();

		return trackerServer;
	}

}
