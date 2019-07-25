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
	public static long bytes2long1(byte[] b,int off){
		System.out.println(b[off + 4] & 0xFF<< 24);
		return ((b[off + 7] & 0xFF) << 0) +
				((b[off + 6] & 0xFF) << 8) +
				((b[off + 5] & 0xFF) << 16) +
				((b[off + 4] & 0xFF) << 24) +
				((b[off + 3] & 0xFF) << 32) +
				((b[off + 2] & 0xFF) << 40) +
				((b[off + 1] & 0xFF) << 48) +
				(((long) b[off + 0]) << 56);
		
	}
	
	public static void main(String[] args) {
		/*long id = 1231231123212L;
		System.out.println(Long.toBinaryString(id));
		System.out.println(Long.toBinaryString(id >>> 56));
		byte[] b = new byte[16];
		long2bytes(id, b, 0);
		System.out.println(bytes2long(b, 0));
		System.out.println(bytes2long1(b, 0));
		System.out.println((byte)456);*/
		byte a = -35;
		long b = 1000000000L;
		System.out.println((a&0xFF)/* << 24*/);
		System.out.println((a&0xFFL) << 24);
	}

}
