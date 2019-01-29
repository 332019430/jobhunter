package us.codecraft.jobhunter.model;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * @author code4crafer@gmail.com
 * Date: 13-6-23
 * Time: 下午4:28
 */
@Data
@TargetUrl("http://sfsm1.chunfund.cn/*.html")
@HelpUrl("http://sfsm1.chunfund.cn/*.html")
public class LieTouJobInfo implements AfterExtractor {
    private String index="http://sfsm1.chunfund.cn/index.html";

    @ExtractBy("//link/@href")
    private List<String> Css;

    @ExtractBy("//script/@src")
    private List<String> js;

    @ExtractBy("//img/@src")
    private List<String> pic;

    @ExtractBy("//a/@href")
    private List<String> html;


    /*@ExtractBy("//div[@id='details-pic']/p/img/@src")
    private List<String> imags;*/
    /*@ExtractBy("//p[@class='job-item-title']/text()")
    private String salary = "";
    @ExtractBy("//div[@class='title-info']/h3/a/text()")
    private String company = "";
    @ExtractBy("//div[@class='content content-word']/allText()")
    private String description = "";
    private String source = "nvc-berkeley.com.com";
    @ExtractByUrl
    private String url = "";
    private String urlMd5 = "";*/



    @Override
    public void afterProcess(Page page) {
    }
}
