package org.client.com.util.base64;

public class Base64Test {

    public static void main(String[] args) {
        String str = "2018-01-01";
//        String str = "ᔌᔁᔑᔇᔑᔙᔛᔒᔕᕅᕄᕅᕇ";
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (c[i] ^ 300);
        }
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (c[i] ^ 2000);
        }
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (c[i] ^ 5000);
        }
        System.out.println(new String(c));
    }
}
