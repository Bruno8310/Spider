package edu.csuft.Bruno.spider;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;


public class getElement {
   public static void main(String[] args) {
	  for(int i=0;i<=10;i++) {
	  String url="https://movie.douban.com/top250?start=i*25&filter=";
	  
	
	try {
		 Document doc=Jsoup.connect(url).get();
		 
		 String title=doc.title();
		 String html=doc.html();
		 String text=doc.data();
		 //System.out.println(title);
		 //System.out.println(html);
		 
	 Elements li=doc.select(".info .hd");
	 System.out.println(li.size());
	 ///创建影片列表
	  ArrayList<Film> list=new ArrayList<>();
	 for(Element e:li)
	 {    Film f=new Film();
	  
		 Element t=e.select(".title").first();
		 String num=e.select(".star span").last().text();
	     System.out.println(t.text()+" "+num);
//	     f.id;
//	     f.title;
	     list.add(f);
		 
	 }
	
		 
//		 Elements img=doc.select("img[src]");
//		 System.out.println(img.size());
//		 for(Element e:img)
//		 {
//			 System.out.println(e);
////		 }
//		 Elements hd_a=doc.select(".hd>a");
//		 System.out.println(hd_a.size());
//		 for(Element e:hd_a)
//		 {
//			 System.out.println(e);
//		 }
		
		
		 
		 
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
}
}
}
