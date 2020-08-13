package org.pretend.common.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.pretend.common.util.Assert;

public class PretendDirectory {

	private String name;

	private String path;

	private File root;

	private Map<String, File> files = new HashMap<>();

	private Map<String, File> allfiles = new HashMap<>();

	private Map<String, List<File>> sameNameFiles = new HashMap<>();

	private Map<String, PretendDirectory> subDirs = new HashMap<>();

	private Map<String, PretendDirectory> allSubDirs = new HashMap<>();

	private Map<String, List<PretendDirectory>> sameNameDirs = new HashMap<>();

	public PretendDirectory(String absolutePath) {
		Assert.hasText(absolutePath, "文件名不能为空");
		init(new File(absolutePath));
	}

	public PretendDirectory(File source) {
		Assert.notNull(source, "入参不能为空");
		init(source);
	}

	private void init(File source) {
		if (!source.exists()) {
			throw new IllegalArgumentException("文件不存在");
		}
		if (!source.isDirectory()) {
			throw new IllegalArgumentException("不是一个目录");
		}
		this.root = source;
		this.path = source.getAbsolutePath();
		this.name = source.getName();

		// 整理文件
		files = getFiles(root, false).stream().collect(Collectors.toMap(File::getName, file -> file));
		List<File> subFiles = getFiles(root, true);
		subFiles.stream().forEach(file -> {
			String fileName = file.getName();
			allfiles.put(file.getAbsolutePath(), file);
			List<File> list = sameNameFiles.get(fileName);
			if (null == list) {
				list = new ArrayList<>();
				sameNameFiles.put(fileName, list);
			}
			list.add(file);
		});

		// 整理子目录
		subDirs = getSubDirs(root, false).stream().collect(Collectors.toMap(File::getAbsolutePath, file -> new PretendDirectory(file)));
		List<File> dirs = getSubDirs(root, true);
		dirs.stream().forEach(dir -> {
			String dirName = dir.getName();
			allSubDirs.put(dir.getAbsolutePath(), new PretendDirectory(dir));
			List<PretendDirectory> list = sameNameDirs.get(dirName);
			if (null == list) {
				list = new ArrayList<>();
				sameNameDirs.put(dirName, list);
			}
			list.add(new PretendDirectory(dir));
		});
	}

	/**
	 * 筛选所有文件,包括子目录下的文件
	 * 
	 * @param root
	 * @return
	 */
	private List<File> getSubDirs(File root, boolean nested) {
		List<File> fileLsit = new ArrayList<File>();
		File[] files = root.listFiles();
		if (null != files && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					fileLsit.add(file);
				} else {
					if (nested) {
						fileLsit.addAll(getFiles(file, true));
					}

				}
			}
			return fileLsit;
		}
		return Collections.emptyList();
	}

	/**
	 * 筛选所有文件,包括子目录下的文件
	 * 
	 * @param root
	 * @return
	 */
	private List<File> getFiles(File root, boolean nested) {
		List<File> fileLsit = new ArrayList<File>();
		File[] files = root.listFiles();
		if (null != files && files.length > 0) {
			for (File file : files) {
				if (file.isFile()) {
					fileLsit.add(file);
				} else {
					if (nested) {
						fileLsit.addAll(getFiles(file, true));
					}

				}
			}
			return fileLsit;
		}
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean hasFile(String fileName) {

		return hasFile(fileName, false);
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean hasFile(File file) {

		if (!file.exists()) {

			return false;
		}

		return hasFile(file.getAbsolutePath(), true);
	}

	/**
	 * 
	 * @param fileName
	 * @param withPath
	 * @return
	 */
	public boolean hasFile(String fileName, boolean withPath) {
		List<File> files = sameNameFiles.get(fileName);
		if (!withPath) {
			return null != files && !files.isEmpty();
		}
		return files.stream().filter(file -> file.getAbsolutePath().equals(fileName)).count() >= 1;
	}

	/**
	 * 从当前目录下根据文件名获取文件
	 * 
	 * @param fileName
	 * @return
	 */
	public File getFile(String fileName) {

		return files.get(fileName);
	}

	/**
	 * 获取当前目录和所有子目录下指定名字的所有文件
	 * 
	 * @param fileName
	 * @return
	 */
	public List<File> getFiles(String fileName) {

		return sameNameFiles.get(fileName);
	}

	/**
	 * 获取当前目录下所有的文件
	 * 
	 * @return
	 */
	public Collection<File> getFiles() {

		return files.values();
	}

	/**
	 * 获取当前目录和子目录下的所有文件
	 * 
	 * @return
	 */
	public Collection<File> getAllFiles() {

		return allfiles.values();
	}

	/**
	 * 获取当前目录下的所有子目录
	 * 
	 * @param name
	 * @return
	 * @throws FileNotFoundException
	 */
	public PretendDirectory getSubDir(String name) throws FileNotFoundException {
		if (null == name || "".equals(name.trim())) {
			return this;
		}
		return subDirs.get(name);
	}

	/**
	 * 获取目录下所有名字都相同的子目录
	 * 
	 * @param name
	 * @return
	 */
	public Collection<PretendDirectory> getSubDirs(String name) {

		return sameNameDirs.get(name);
	}

	public Collection<PretendDirectory> getAllSubDirs() {

		return allSubDirs.values();
	}

	public String getPath() {

		return this.path;
	}

	public String getName() {

		return this.name;
	}

	public File getSource() {

		return this.root;
	}

	public void clearUpProjectFiles(String desDirectory) throws IOException {

		Collection<File> allProjectFiles = getAllFiles();

		for (File file : allProjectFiles) {
			String fileName = file.getName();

			String path = desDirectory + File.separator;
			if (fileName.endsWith("Dao.java")) {
				path += "dao";
			} else if (fileName.endsWith("Mapper.xml")) {
				path += "mapper";
			} else if (fileName.endsWith("BO.java")) {
				path += "bo";
			} else if (fileName.endsWith("Service.java")) {
				path += "service";
			} else if (fileName.endsWith("ServiceImpl.java")) {
				path += "serviceImpl";
			} else if (fileName.endsWith("Controller.java")) {
				path += "controller";
			} else if (fileName.endsWith("VO.java")) {
				path += "vo";
			} else {
				RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
				String line = randomAccessFile.readLine();
				randomAccessFile.close();
				if (line.endsWith(".domain;")) {
					path += "domain";
				} else {
					continue;
				}
			}
			path = path + File.separator + fileName;
			File desFile = new File(path);
			File parentFile = desFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			if (desFile.exists()) {
				desFile.delete();
			}
			Files.copy(file.toPath(), desFile.toPath());
		}
	}

}
