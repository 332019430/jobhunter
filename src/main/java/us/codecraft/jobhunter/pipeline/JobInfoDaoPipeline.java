package us.codecraft.jobhunter.pipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.jobhunter.dao.JobInfoDAO;
import us.codecraft.jobhunter.model.LieTouJobInfo;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import utils.DataUtils;
import utils.DownloadUtils;
import utils.FileUtils;
import utils.MyStringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午8:56
 */
@Component("JobInfoDaoPipeline")
public class JobInfoDaoPipeline implements PageModelPipeline<LieTouJobInfo> {
    public static void main(String[] args) {

    }
    @Resource
    private JobInfoDAO jobInfoDAO;

    @Override
    public void process(LieTouJobInfo lieTouJobInfo, Task task) {
        //所有链接资源
        HashSet<String> dataSet = new HashSet<>();

        //文件相对路径
        String folderPath = "resourcea/";

        //主页链接
        String indexHtml = lieTouJobInfo.getIndex();
        dataSet.add(indexHtml);

        //链接前缀
        String prefix = MyStringUtils.getPrefix(indexHtml);
        int prefixLength = prefix.length();

        listToSet(lieTouJobInfo, dataSet, prefix);

        for (String s : dataSet) {
            System.out.println("资源链接="+s.substring(prefixLength));
        }
        System.out.println("下载完成!");

    }

    //把链接装进set中
    private void listToSet(LieTouJobInfo lieTouJobInfo, HashSet<String> dataSet, String prefix) {
        List<String> css = lieTouJobInfo.getCss();
        DataUtils.saveData(dataSet,css,prefix);

        List<String> js = lieTouJobInfo.getJs();
        DataUtils.saveData(dataSet,js,prefix);

        List<String> pic = lieTouJobInfo.getPic();
        DataUtils.saveData(dataSet,pic,prefix);

        List<String> html = lieTouJobInfo.getHtml();
        DataUtils.saveData(dataSet,html,prefix);
    }

}
