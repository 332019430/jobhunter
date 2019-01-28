package utils;

/**
 * @author 小郑
 * @version 1.0
 * @description utils
 * @date 2019/1/28/0028
 */
public class MyStringUtils {
    public static String getPrefix(String indexHtml){
        int i = indexHtml.lastIndexOf("/");
        //todo 以后递归下载页面的其他链接页面需要用到
        String prefix=indexHtml.substring(0,i+1);
        return prefix;
    }
}
