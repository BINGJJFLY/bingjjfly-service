package com.wjz.demo.crypto;

public class BitDemo {
	
	private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

	public static void main(String[] args) {
		int d = 60;
		int result = d << 1;
		System.out.println(result);
		
		result = d >> 2;
		System.out.println(result);
		
		result = d >>> 2;
		System.out.println(result);
		
		// 0x开头为16进制，0开头为8进制
		int b = 0xF0; // (0*16^0+15*16^1) = 240 = 1111 0000
		
		int c = 0x0F; // (15*16^0) = 15 = 0000 1111
		
		int e = 0x12; // (2*16^0+1*16^1) = 18 = 0001 0010
		
		System.out.println(b);
		System.out.println(c);
		System.out.println(e);
		
		int f = 97;
		System.out.println(b & f);
		
		System.out.println(9 << 1);
		
		char[] cs = encode("abc123456".getBytes());
		System.out.println(cs.length);
		for (int i = 0; i < cs.length; i++) {
			System.out.print(cs[i]);
		}
	}
	
	public static char[] encode(byte[] data) {

        int l = data.length;

        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
    }
}
