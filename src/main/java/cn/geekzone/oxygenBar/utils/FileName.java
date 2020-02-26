package cn.geekzone.oxygenBar.utils;

import java.io.File;

public class FileName {
	public static String[] getFiles(String path) {

		File file = new File(path);

		// 获取路径下的所有文件
		String[] files = file.list();
		return files;
		/*for (int i = 0; i < files.length; i++) {

			System.out.println("文件：" + files[i]);

		}*/

	}

}
