package org.client.com.util.base64;

public class Base64Test {

    public static void main(String[] args) {
        String s= "YmUzMGExOWFmMTI0NGFjODg0Nzc3ODBlZGQ0NDU5N2E=.eHVlc2Vtb2ZhQDEyNi5jb20=.MTUxODE1NTI1NTY4NA==.VGhlIHN1cnZpdmFsIG9mIHRoZSBkZWFk.dXNlcg==";
        String[] split = s.split(".");
        System.out.println(split.toString());
    }
}
