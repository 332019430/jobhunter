package us.codecraft.jobhunter.pipeline;

import org.springframework.stereotype.Component;
import us.codecraft.jobhunter.dao.JobInfoDAO;
import us.codecraft.jobhunter.model.LieTouJobInfo;
import us.codecraft.jobhunter.utils.DataUtils;
import us.codecraft.jobhunter.utils.DownloadUtils;
import us.codecraft.jobhunter.utils.FileUtils;
import us.codecraft.jobhunter.utils.MyStringUtils;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;


import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * @author code4crafer@gmail.com
 * Date: 13-6-23
 * Time: 下午8:56
 */
@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements PageModelPipeline<LieTouJobInfo> {
    public static void main(String[] args) {
        String a = "a";
        String substring = a.substring(3);
        System.out.println(a);
    }

    @Resource
    private JobInfoDAO jobInfoDAO;

    private final String path = "resource\\";

    @Override
    public void process(LieTouJobInfo lieTouJobInfo, Task task) {
        //所有链接资源
        HashSet<String> dataSet = new HashSet<>();

        //文件相对路径
        String folderPath = path;

        //主页链接
        String indexHtml = lieTouJobInfo.getIndex();
        dataSet.add(indexHtml);

        //链接前缀
        String prefix = MyStringUtils.getPrefix(indexHtml);
        int prefixLength = prefix.length();
        //把链接放进set中去重
        listToSet(lieTouJobInfo, dataSet, prefix);

        //查看一下dataSet数据
        /*for (String s : dataSet) {
            System.out.println("dataSetLink="+s);
        }*/

        System.out.println("抓取完链接开始下载");
        for (String link : dataSet) {
            //去掉sfsm1.chunfund.cn/的前缀的路径
            System.out.println("dataSetLink=" + link);
            String suffix;
            if (link.startsWith(prefix)) {
                suffix = link.substring(prefixLength);
            } else {
                suffix = link;
            }
            //判断是否要生成文件夹
            int i = suffix.lastIndexOf("/");
            if (i != -1) {
                String dirPath = suffix.substring(0, i + 1);
                if (!dirPath.startsWith("http:") && !dirPath.startsWith("https:")) {
                    folderPath = folderPath + dirPath;
                }

                DownloadUtils.download(folderPath, link);
                folderPath = path;
            } else {
                if (!link.endsWith("#")) {
                    DownloadUtils.download(folderPath, link);
                }
            }
        }
        System.out.println("下载完成!");


        //现在把网页上的连接资源下载好了，要开始下载css中隐藏的图片
        File file = new File(folderPath);
        FileReader r = null;
        BufferedReader br = null;
        //把隐藏在css中的图片url截取出来
        FileUtils.downloadCssPic(file, prefix, folderPath, br, r);
    }

    //把链接装进set中
    private void listToSet(LieTouJobInfo lieTouJobInfo, HashSet<String> dataSet, String prefix) {
        List<String> css = lieTouJobInfo.getCss();
        DataUtils.saveData(dataSet, css, prefix);

        List<String> js = lieTouJobInfo.getJs();
        DataUtils.saveData(dataSet, js, prefix);

        List<String> pic = lieTouJobInfo.getPic();
        DataUtils.saveData(dataSet, pic, prefix);

        List<String> html = lieTouJobInfo.getHtml();
        DataUtils.saveData(dataSet, html, prefix);
    }
}
