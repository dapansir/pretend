package org.pretend.tools.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class NetUtil {
	
	 private static final String LOCALHOST = "127.0.0.1";
	 
	 private static final String ANYHOST = "0.0.0.0";
	 
	 private static final Pattern IP_PANTTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
	 
	private NetUtil() {
		
	}
	
	public static boolean isValidAddress(InetAddress address) {
		if(null == address || address.isLoopbackAddress()) {
			return false;
		}
		String name = address.getHostName();
		
		return null != name 
				&& !LOCALHOST.equals(name) 
				&& !ANYHOST.equals(name) 
				&&!IP_PANTTERN.matcher(name).matches() ;
	}

	public static InetAddress getInetAddress() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			if(isValidAddress(address)) {
				return address;
			}
		} catch (UnknownHostException e) {
			// TODO logger
		}
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while(inetAddresses.hasMoreElements()) {
					address = inetAddresses.nextElement();
					if(isValidAddress(address)) {
						return address;
					}
				}
			}
		} catch (SocketException e) {
			// TODO logger
		}
		return address;
	}
	
	public static String getIp() {
		InetAddress address = getInetAddress();
		if(null != address) {
			return address.getHostAddress();
		}
		return null;
	}
}
