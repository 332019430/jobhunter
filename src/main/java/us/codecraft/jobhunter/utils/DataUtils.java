package us.codecraft.jobhunter.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;


/**
 * @author 小郑
 * @version 1.0
 * @description utils
 * @date 2019/1/28/0028
 */
public class DataUtils {

    public static HashSet<String> saveData(HashSet<String> dataSet,List<String> list,String prefix){
        for (String url : list) {
            if (StringUtils.isNotBlank(url)) {
                int qusetionIndex = url.indexOf("?");
                if (qusetionIndex!=-1){
                    url=url.substring(0,qusetionIndex);
                }
                // ||
                if (url.startsWith("http:")||url.startsWith("https:")){
                    dataSet.add(url);
                }else {
                    if (url.startsWith("/")){
                        url=url.substring(1);
                    }
                    String destUrl = prefix + url;
                    dataSet.add(destUrl);
                }

            }
        }
        return dataSet;
    }


}
