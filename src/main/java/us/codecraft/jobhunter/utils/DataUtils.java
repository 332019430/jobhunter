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
        for (String s : list) {
            if (StringUtils.isNotBlank(s)) {
                /*if (!s.startsWith("http:")){
                    s = prefix + s;
                    dataSet.add(s);
                }*/
                if (s.startsWith("http:")||s.startsWith("https:")){
                    dataSet.add(s);
                }else {
                    s = prefix + s;
                    dataSet.add(s);
                }

            }
        }
        return dataSet;
    }


}
