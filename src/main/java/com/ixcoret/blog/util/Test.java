package com.ixcoret.blog.util;

/**
 * @author ixcoret
 * @createTime 2021/12/19 0:38
 */
public class Test {
    public static void main(String[] args) {
        String str = "afgerczegerttuytruouyumbvsgks,l;fjggjfghlf;ph[.,dfsdcaqxhhjgfjbggsfequtrjxioop[hndfkhdfffdzbjsrrttfssabcddfdf";

        long start1 = System.currentTimeMillis();
        String[] strs = str.split("abc");
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

        System.out.println(strs[1].substring(0, 5));

        long start2 = System.currentTimeMillis();
        int start = str.indexOf("abc") + 3;
        int end = start + 5;
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);

        System.out.println(str.substring(start, end));
    }
}
