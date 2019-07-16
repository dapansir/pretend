package org.pretend.tools.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Parameter {

	private Map<String, String> parameters = new ConcurrentHashMap<String, String>();

	// String,byte,char,short,int,long,float,double,boolean
	public Parameter addParametersIfAbsent(String key, String value) {
		String put = parameters.get(key);
		if (null == put) {
			parameters.put(key, value);
		}
		return this;
	}

	public Parameter addParameter(String key, byte value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, char value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, short value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, int value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, long value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, float value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, double value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, boolean value) {
		parameters.put(key, String.valueOf(value));
		return this;
	}

	public Parameter addParameter(String key, String value) {
		parameters.put(key, value);
		return this;
	}

	public String get(String key) {

		return parameters.get(key);
	}

	public String get(String key, String defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return value;
	}

	public byte get(String key, byte defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Byte.parseByte(value);
	}

	public char get(String key, char defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return value.charAt(0);
	}

	public short get(String key, short defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Short.parseShort(value);
	}

	public int get(String key, int defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	public long get(String key, long defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Long.parseLong(value);
	}

	public float get(String key, float defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Float.parseFloat(value);
	}

	public double get(String key, double defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Double.parseDouble(value);
	}

	public boolean get(String key, boolean defaultValue) {
		String value = parameters.get(key);
		if (null == value) {
			return defaultValue;
		}
		return Boolean.parseBoolean(value);
	}
}
