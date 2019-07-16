package org.pretend.common.util;

public class ClassHelper {

	
	private ClassHelper(){
		
	}
	
	public static ClassLoader getClassLoader(Class<?> type){
		ClassLoader classLoader = null;
		try {
			classLoader = Thread.currentThread().getContextClassLoader();
		} catch (Exception e) {
			try {
				classLoader = type.getClassLoader();
			} catch (Exception e2) {
				try {
					classLoader = ClassLoader.getSystemClassLoader();
				} catch (Exception e3) {
					//没办法了
				}
			}
		}
		return classLoader;
	}
}
