package html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
    public static void main(String[] args) throws Exception {
        //6.Jsoup解析html
        //Document document = Jsoup.parse("");
        Document document = Jsoup.connect("https://ncov.dxy.cn/ncovh5/view/pneumonia?scene=2&clicktime=1579584853&enterid=1579584853&from=timeline&isappinstalled=0").get();
        //像js一样，通过标签获取title
        System.out.println(document.getElementsByTag("title").first());
        //像js一样，通过id 获取文章列表元素对象
        Element postList = document.getElementById("getAreaStat");
        String doc = postList.getAllElements().get(0).toString().substring(50, postList.getAllElements().get(0).toString().length() - 11);
        Elements postItems = postList.getElementsByClass("post_item");

        System.out.println(doc);
        //循环处理每篇博客
        for (Element postItem : postItems) {
            //像jquery选择器一样，获取文章标题元素
            Elements titleEle = postItem.select(".post_item_body a[class='titlelnk']");
            System.out.println("文章标题:" + titleEle.text());
            System.out.println("文章地址:" + titleEle.attr("href"));
            //像jquery选择器一样，获取文章作者元素
            Elements footEle = postItem.select(".post_item_foot a[class='lightblue']");
            System.out.println("文章作者:" + footEle.text());
            System.out.println("作者主页:" + footEle.attr("href"));
            System.out.println("*********************************");
        }
    }
}
