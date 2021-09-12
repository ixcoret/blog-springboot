package com.ixcoret.blog.util;

/**
 * @author ixcoret
 * @createTime 2021/9/9 17:08
 */
public class PageUtil {
    public static int startIndex(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }
}
