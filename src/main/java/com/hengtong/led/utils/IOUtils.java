package com.hengtong.led.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

public class IOUtils extends org.apache.commons.io.IOUtils {
	/**
	 * close all io
	 * @param collection
	 */
	public static void closeAll(@SuppressWarnings("rawtypes") Collection collection) {
		if(collection!=null) {
			for(Object o :collection) {
				close(o);
			}
		}
	}


	/**
	 * close all io
	 * @param others
	 */
	public static void closeAll(Object... others) {
		closeAll(others!=null? Arrays.asList(others):null);
	}
	/**
	 * close io
	 * @param <T>
	 * @param io
	 */
	public static <T> void close(T io) {

		try {
			if(io!=null) {
				Method method = null;
				try {
					method = io.getClass().getMethod("close");
				} catch (Exception e) {
					//needless to print
				}
				if(method!=null) {
					method.invoke(io);
					//System.out.println("Called method successfull! ["+method.toGenericString()+"] ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  flush all io
	 * @param collection
	 */
	public static void flushAll(@SuppressWarnings("rawtypes") Collection collection) {
		if(collection!=null) {
			for(Object o :collection) {
				flush(o);
			}
		}
	}
	/**
	 * flush all io
	 * @param others
	 */
	public static void flushAll(Object... others) {
		flushAll(others!=null? Arrays.asList(others):null);
	}
	/**
	 * flush io
	 * @param <T>
	 * @param io
	 */
	public static <T> void flush(T io) {

		try {
			if(io!=null) {
				Method method = null;
				try {
					method = io.getClass().getMethod("flush");
				} catch (Exception e) {
					//needless to print
				}
				if(method!=null) {
					method.invoke(io);
					//System.out.println("Called method successfull! ["+method.toGenericString()+"] ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
