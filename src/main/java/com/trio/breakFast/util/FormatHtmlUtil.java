package com.trio.breakFast.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FormatHtmlUtil {
	public static String formatHtml(String str) {
		Document doc=new Document(str);
		
		Element content = doc.prepend(str);
//		content.select("*").removeAttr("style").removeAttr("class")
//				.removeAttr("bgColor").removeAttr("lang").removeAttr("face")
//				.removeAttr("size").removeAttr("color").removeAttr("background")
//				.removeAttr("alt").removeAttr("title").removeAttr("id")
//				.removeAttr("height").removeAttr("onmousewheel").removeAttr("align")
//				.removeAttr("scope").removeAttr("width").removeAttr("valign")
//				.removeAttr("onload");
		//对a标签处理
//		Elements as = content.select("a");
//		for (Element a : as) {
//			String href=a.attr("href");
//			if (href.equals("#")) {
//				href=a.attr("tempHref");
//			}
//			if ("".equals(href) || href==null) {
//				continue;
//			}else {
//				a.attr("href","#");
//				a.attr("tempHref",href);
//				a.attr("onclick","showMoreMessage('"+href+"')");
//			}
//			
//		}
		//对img标签进行处理
		Elements imgs = content.select("img");
		for (Element img : imgs) {
			//表情图片跳过
			if (img.attr("src").endsWith(".gif") && img.attr("src").startsWith("http://img.baidu.com/hi")) {
				continue;
			}
			img.attr("width", "100%").attr("height", "auto");
		}
		//对p标签进行处理
//		Elements ps = content.select("p");
//		for (Element p : ps) {
//			if (!p.html().contains("<img")&&!p.html().contains("<embed")&&!p.html().contains("<object") && p.text().replaceAll("　", "").replaceAll(" ", "").trim().equals("")) {
//				p.remove();
//			}
//		}
		//对td进行处理
		content.select("table").attr("width","100%");
//		content.select("th").tagName("td");
//		Elements tds = content.select("td");
//		for(Element td:tds){
//			if(td.text().length()<50){
//				td.select("p").unwrap();
//				td.select("div").unwrap();
//				td.select("span").unwrap();
//			}
//		}
		String temp = content.html();
//		temp = temp
//				.replaceAll("&nbsp;", "")
//				.replaceAll("　", "")
//				.replaceAll("<br /><br />", "<br />")
//				.replaceAll("<div>","<div><p style=\"text-indent:2em;\">")
//				.replaceAll("<p>", "<p style=\"text-indent:2em;\">")
//				.replaceAll("<br />", "<p style=\"text-indent:2em;\">")
//				.replaceAll("<p[^(>)]+>(\\s*)<img", "<p><img")
//				.replaceAll("<!--[^(-->)]+-->", "");
//		if (temp != null && !temp.equals("")) {
//			if (!temp.startsWith("<p")&&!temp.startsWith("<img")) {
//				temp = "<p style=\"text-indent:2em;\">" + temp;
//			}
//		}
//		temp=temp.replaceAll("\'", "\"");
		return temp;
	}
//	public static void main(String[] args){
//		String html="<h2>啊实打实大师暗示d暗示</h2><b></b><br><b><i><u>打扫打扫打扫啊飒飒大师ad</u></i></b><br><b><i><u>暗示暗示d是<br></u></i></b><img alt='' src='http://e.hiphotos.baidu.com/image/h%3D360/sign=7cf80cb9003b5bb5a1d726f806d2d523/a6efce1b9d16fdfafb9dcbfcb78f8c5494ee7b2a.jpg'><br><br>";
//		System.out.println(formatHtml(html));
//	}
}
