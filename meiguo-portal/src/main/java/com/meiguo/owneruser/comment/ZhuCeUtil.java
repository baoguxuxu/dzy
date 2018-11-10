package com.meiguo.owneruser.comment;

import java.util.Random;
@SuppressWarnings("all")
public class ZhuCeUtil {


	/** 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符） */
    private static final char[] r=new char[]{'8','2','0','9','7','5','3','4','6'};
 
    /** 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的） */
    private static final char b='1';
 
    /** 进制长度 */
    private static final int binLen=r.length;
 
    /** 邀请码长度 */
    private static final int s=6;
 
	/** 补位字符串 */
    private static final String e="12421";
 
    /**
     * 根据ID生成六位随机码
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf=new char[32];
        int charPos=32;
 
        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动补全
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(e.subSequence(0, s-str.length()));
            str+=sb.toString();
        }
        return str;
    }
 
    /**
     * 根据随机码生成ID
     * @param 随机码
     * @return ID
     */
    public static long codeToId(String code) {
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }

//    public static void main(String[] args) {
//        String code = toSerialCode(531440);
//        System.out.println(code);
//        System.out.println(codeToId(code));
//    }
    public static void main(String[] args) {
//      String code = toSerialCode(531440);
//      System.out.println(code);
//      System.out.println(codeToId(code));
    	String code = "291242" ;
      long serialCode = ZhuCeUtil.codeToId(code);
      System.out.println(serialCode);
  }

	
}
