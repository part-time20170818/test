package com.wg.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


public class FileUtil {

	private static final String FILE_TEMP = new Properties().getProperty("tempdir");

	/**
	 * 获得Web根路径“/”在当前应用服务器的真实路径
	 * @param request
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 判断某文件夹下是否存在文件，存在返回true
	 * 
	 * @param objectPath
	 * @return
	 */
	public static boolean existFileInDirectory(String objectPath) {
		boolean flag = false;
		File filePath = new File(objectPath);
		if (filePath.exists()) {

			if (filePath.isDirectory()) {
				File[] list = filePath.listFiles();
				if (list != null && list.length != 0) {
					flag = true;
				}
			}
		}

		return flag;
	}

	/**
	 * 创建文件夹,如果有则不创建
	 * 
	 * @param objectPath
	 * @return 文件夹已存在或创建成功true，否则false。
	 */
	public static boolean creareDirectory(String objectPath) {
		boolean flag = true;

		File filePath = new File(objectPath);

		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		if (!filePath.isDirectory()) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 删除备份文件和存放备份文件的文件夹
	 * 
	 * @param dirName
	 */
	public static void deleteFileAndDirectory(String dirName) {
		String ObjectPath;
		if (!FILE_TEMP.endsWith(File.separator)) {
			ObjectPath = FILE_TEMP + File.separator + dirName;
		} else {
			ObjectPath = FILE_TEMP + dirName;
		}

		File filePath = new File(ObjectPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		} else {
			if (filePath.isDirectory()) {
				File[] list = filePath.listFiles();

				for (int i = 0; i < list.length; i++) {
					list[i].delete();
				}
			}
			filePath.delete();
		}
	}

	/**
	 * 删除某个文件夹
	 * 
	 * @param objectPath
	 */
	public static void deleteDirectory(String objectPath) {
		File filePath = new File(objectPath);
		if (filePath.exists()) {
			filePath.delete();
		}
	}

	/**
	 * 将已存在的文件删除
	 * 
	 * @param objectPath
	 * @param fileName
	 * @return
	 */
	public static boolean deleteExistedFile(String objectPath,
			final String fileName) {
		boolean flag = false;
		File filePath = new File(objectPath);
		if (filePath.exists()) {
			if (filePath.isDirectory()) {
				File[] list = filePath.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.equals(fileName);
					}
				});
				for (int i = 0; i < list.length; i++) {
					list[i].delete();
				}
				flag = true;
			}
		}

		return flag;
	}
	
	/**
	 * 删除指定文件
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static void deleteTheFile(String fullPath) {
		File file =new File(fullPath);
		file.delete();
	}

	/**
	 * 查看某文件夹下面是否有文件,有文件则创建一个temp文件夹,将文件拷贝到temp目录下(备份文件) 没有文件则什么都不做
	 * 备份后，把原文件夹里文件删除
	 * 
	 * @param objectPath
	 * @param dirName
	 */
	public static void backupFile(String objectPath, String dirName) {
		String backupPath;

		if (!FILE_TEMP.endsWith(File.separator)) {
			backupPath = FILE_TEMP + File.separator + dirName;
		} else {
			backupPath = FILE_TEMP + dirName;
		}

		File backupFilePath = new File(backupPath);
		if (!backupFilePath.exists()) {
			backupFilePath.mkdirs();
		}
		File filePath = new File(objectPath);
		if (!filePath.exists()) {
		} else {
			if (filePath.isDirectory()) {
				File[] list = filePath.listFiles();
				if (list != null && list.length != 0) {
					copyFolder(objectPath, backupPath);// 文件备份
					for (int i = 0; i < list.length; i++) {
						list[i].delete();
					}
				}
			}
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param fromPath
	 *            源文件路径
	 * @param toPaht
	 *            目标路径
	 * @param fileName
	 *            文件全名
	 */
	public static boolean copyFile(String fromPath, String fromName, String toPaht, String toName) {
		String from;
		String to;
		
		if(!fromPath.endsWith(File.separator)) {
			from = fromPath + File.separator + fromName;
		} else {
			from = fromPath + fromName;
		}
		if(!toPaht.endsWith(File.separator)) {
			to = toPaht + File.separator + toName;
		} else {
			to = toPaht + toName;
		}
		
		if (!creareDirectory(toPaht)){
		    return false;
		};
		
		try {
			FileInputStream input = new FileInputStream(from);
			FileOutputStream output = new FileOutputStream(to);

			byte[] b = new byte[1024 * 5];
            int len;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }

            output.flush();
            output.close();
            input.close();
		} catch (IOException e) {
            return false;
		}

		return true;
	}
	
	/**
	 * 复原文件，把文件从备份文件夹拷贝到原来文件夹
	 * 
	 * @param objectPath
	 * @param dirName
	 */
	public static void recoverFile(String objectPath, String dirName) {
		String backupPath;
		if (objectPath.endsWith(File.separator)) {
			objectPath = new StringBuffer(objectPath).append(dirName)
					.toString();
		} else {
			objectPath = new StringBuffer(objectPath)
					.append(File.separatorChar).append(dirName).toString();
		}

		if (!FILE_TEMP.endsWith(File.separator)) {
			backupPath = FILE_TEMP + File.separator + dirName;
		} else {
			backupPath = FILE_TEMP + dirName;
		}
		File backupFilePath = new File(backupPath);
		if (!backupFilePath.exists()) {
			backupFilePath.mkdirs();
		}
		File filePath = new File(objectPath);
		if (!filePath.exists()) {
		} else {
			if (filePath.isDirectory()) {
				File[] list = filePath.listFiles();
				if (list != null && list.length != 0) {
					copyFolder(backupPath, objectPath);// 文件复原
				}
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdir(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + temp.getName().toString());

					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 创建文件
	 * @param path
	 * @return
	 */
	public static File createFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}