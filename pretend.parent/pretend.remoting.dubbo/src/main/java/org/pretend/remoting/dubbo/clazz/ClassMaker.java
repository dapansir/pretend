package org.pretend.remoting.dubbo.clazz;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

public class ClassMaker {

	private ClassPool mPool;
	
	private CtClass mCtc;
	
	private String mClassName;
	
	private String mSuperClass;
	
	private Set<String> mInterfaces;
	
    private List<String> mFields;
    
    private List<String> mConstructors;
    
    private List<String> mMethods;
	
    private static final Map<ClassLoader, ClassPool> POOL_MAP = new ConcurrentHashMap<ClassLoader, ClassPool>(); //ClassLoader - ClassPool
    
//    private static final AtomicLong CLASS_NAME_COUNTER = new AtomicLong(0);

    private ClassMaker(ClassPool pool) {
        mPool = pool;
    }

    
    public static ClassPool getClassPool(ClassLoader loader) {
        if (loader == null) {
            return ClassPool.getDefault();
        }

        ClassPool pool = POOL_MAP.get(loader);
        if (pool == null) {
            pool = new ClassPool(true);
            pool.appendClassPath(new LoaderClassPath(loader));
            POOL_MAP.put(loader, pool);
        }
        return pool;
    }
    
    public static ClassMaker newInstance(ClassLoader loader) {
        return new ClassMaker(getClassPool(loader));
    }

    public void addMethod(String method){
    	if(mMethods == null){
    		mMethods = new ArrayList<String>();
    	}
    	mMethods.add(method);
    }
    
    public void addField(String field){
    	if(mFields == null){
    		mFields = new ArrayList<String>();
    	}
    	mMethods.add(field);
    }
    
    public void addmConstructor(String constructor){
    	if(mConstructors == null){
    		mConstructors = new ArrayList<String>();
    	}
    	mConstructors.add(constructor);
    }
    
    public void addmInterface(String sinterface){
    	if(mInterfaces == null){
    		mInterfaces = new HashSet<String>();
    	}
    	mInterfaces.add(sinterface);
    }
    
    public void setSuperClass(String superClass){
    	this.mSuperClass = superClass;
    }
    
    public void setClassName(String className){
    	this.mClassName = className;
    }
    
    public Class<?> toClass(ClassLoader cl, ProtectionDomain pd){
    	if(mCtc != null){
    		mCtc.detach();
    	}
    	Class<?> clazz = null;
    	mCtc = mPool.makeClass(mClassName);
    	mCtc.setModifiers(1);
    	
    	try {
    		CtClass ctcs = mSuperClass == null ? null : mPool.get(mSuperClass);
	        mCtc.setSuperclass(ctcs);
	        if(null != mInterfaces){
	        	for (String string : mInterfaces) {
	        		mCtc.addInterface(mPool.get(string));
                }
	        }
	        if(null != mFields){
	        	for (String string : mFields) {
	        		mCtc.addField(CtField.make(string, mCtc));
	        	}
	        }
	        if(null != mMethods){
	        	for (String string : mMethods) {
	        		mCtc.addMethod(CtNewMethod.make(string, mCtc));
	        	}
	        }
	        clazz = mCtc.toClass(cl,pd);
        } catch (CannotCompileException e) {
	        e.printStackTrace();
        } catch (NotFoundException e) {
	        e.printStackTrace();
        }
		return clazz;
    }
    
    
    public Class<?>  toInterface(ClassLoader cl, ProtectionDomain pd) throws CannotCompileException, NotFoundException{
    	
    	if(mCtc != null){
    		mCtc.detach();
    	}
    	mCtc = mPool.makeInterface(mClassName);
    	mCtc.setModifiers(1);
    	if(null != mInterfaces){
        	for (String string : mInterfaces) {
        		mCtc.addInterface(mPool.get(string));
            }
        }
        if(null != mMethods){
        	for (String string : mMethods) {
        		mCtc.addMethod(CtNewMethod.make(string, mCtc));
        	}
        }
		return mCtc.toClass(cl,pd);
    }
    
}
