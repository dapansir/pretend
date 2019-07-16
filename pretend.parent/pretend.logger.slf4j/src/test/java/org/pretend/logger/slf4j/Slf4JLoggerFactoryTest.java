package org.pretend.logger.slf4j;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.pretend.common.loader.ExtensionLoader;
import org.pretend.common.logger.LoggerFactory;
import org.pretend.logger.slf4j.factory.Slf4JLoggerFactory;

public class Slf4JLoggerFactoryTest {
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@Test
	public void getClassLoggerTest(){
		LoggerFactory fac = ExtensionLoader.getExtensionLoader(LoggerFactory.class).getActiveExtension();
		Assert.assertNotNull(fac.getLogger(Slf4JLoggerFactory.class));
	}
	
	@Test
	public void getStringLoggerTest(){
		LoggerFactory fac = ExtensionLoader.getExtensionLoader(LoggerFactory.class).getActiveExtension();
		Assert.assertNotNull(fac.getLogger(Slf4JLoggerFactory.class.getName()));
	}
	
	@Test
	public void getLoggerTest(){
		thrown.expect(IllegalStateException.class);
		LoggerFactory fac = ExtensionLoader.getExtensionLoader(LoggerFactory.class).getActiveExtension();
		fac.getLogger();
	}
	
}
