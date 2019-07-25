package org.pretend.common;

public class Bytes {
	
	private Bytes(){
		
		
	}
	
	public static void long2bytes(long v,byte[] b,int off){
		b[off+7] = (byte)v;
		b[off+6] = (byte)(v >>> 8);
		b[off+5] = (byte)(v >>> 16);
		b[off+4] = (byte)(v >>> 24);
		b[off+3] = (byte)(v >>> 32);
		b[off+2] = (byte)(v >>> 40);
		b[off+1] = (byte)(v >>> 48);
		b[off+0] = (byte)(v >>> 56);
	}
	
	public static long bytes2long(byte[] b,int off){
		System.out.println(b[off + 4] & 0xFFL<< 24);
		return ((b[off + 7] & 0xFFL) << 0) +
        ((b[off + 6] & 0xFFL) << 8) +
        ((b[off + 5] & 0xFFL) << 16) +
        ((b[off + 4] & 0xFFL) << 24) +
        ((b[off + 3] & 0xFFL) << 32) +
        ((b[off + 2] & 0xFFL) << 40) +
        ((b[off + 1] & 0xFFL) << 48) +
        (((long) b[off + 0]) << 56);
	}


}
