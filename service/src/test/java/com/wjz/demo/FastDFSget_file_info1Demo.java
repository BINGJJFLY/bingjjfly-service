package com.wjz.demo;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;

public class FastDFSget_file_info1Demo {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, MyException {
		StorageClient1 client1 = new StorageClient1();
		FileInfo info = client1.get_file_info1("group1/M00/00/00/wKi8gltzFhKAQyFdAADcxhsc1PM040.png");
		System.out.println(info);
		
		String[] parts = new String[2];
		byte b = client1.split_file_id("group1/M00/00/00/wKi8gltzFhKAQyFdAADcxhsc1PM040.png", parts);
		System.out.println(b);
	}
}
