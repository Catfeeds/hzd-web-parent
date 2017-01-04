package com.hzcf.platform.mgr.sys.common.security;

import com.hzcf.platform.framework.fastdfs.FastDFSClient;
import com.hzcf.platform.framework.fastdfs.common.FileCommon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * 单元测试
 *
 * @author fengjx. @date：2015/1/6 0006
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-fastdfs.xml")
public class Test1 {

	@Autowired
	private FastDFSClient fastdfsClient;

	@Test
	public void test() throws Exception {
		// String res = fastdfsClient.helloFjx();
		//File folder = new File("C:\\Users\\Administrator\\Desktop\\testpic");
		File folder = new File("D:\\img");
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.exists() && file.isFile()) {
					String file_url = fastdfsClient.upload(FileCommon.File2byte(file), getSuffix(file.getName()), null);
					System.out.println(file.getName() + " : " + file_url);
				}
			}
		}
		// File file=new File("D:\\testpic\\ZT1.jpg");
		// String
		// file_url=fastdfsClient.upload(FileCommon.File2byte(file),"jpg",null);
		// System.out.println(file_url);
		// System.out.println(fastdfsClient.delete(file_url));
		// FileInfo info = null;
		// try {
		// info = fastdfsClient.getFileInfo(file_url);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(info.getSourceIpAddr());
		// System.out.println(info.getFileSize());
		// System.out.println(info.getCreateTimestamp());
		// System.out.println(info.getCrc32());
	}

	private static String getSuffix(String url) {
		if (url != null) {
			int index = url.lastIndexOf(".");
			if (index > 0) {
				return url.substring(index + 1);
			}
		}
		return url;
	}

}
