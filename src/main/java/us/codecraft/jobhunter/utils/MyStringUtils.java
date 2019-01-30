package us.codecraft.jobhunter.utils;

/**
 * @author 小郑
 * @version 1.0
 * @description utils
 * @date 2019/1/28/0028
 */
public class MyStringUtils {
    public static String getPrefix(String indexHtml){
        int i = indexHtml.lastIndexOf("/");
        String prefix=indexHtml.substring(0,i+1);
        return prefix;
    }
}
