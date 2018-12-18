package edu.csuft.Bruno.spider;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 抓取页面影片信息
 */
public class test implements Runnable {
    //页面路径
	String url;
	//存储抓取的数据
	ArrayList<Film> list;
	//输出流对象
	static private  FileOutputStream outputStream=null;
	//输入流对象
	static private  InputStream inputStream=null;
	//缓冲流对象
	static private  BufferedInputStream bis=null;
	//图片下载本地路径
	static public   String outpath="F://spider_code//outpath//";
	//构造方法
	public test(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}
	//执行要抓取的任务
	public void run() {
		//获取线程名字
	  String name=Thread.currentThread().getName();
	  System.out.println(name+"线程，处理："+url);
	  //Jsoup
	  try { 
		 Document doc=Jsoup.connect(url).get();
		 Elements es=doc.select(".grid_view li");
		 for(Element e :es)
	      {
	    	  Film film=new Film();
	    	  //获取每部电影url的a标签
	    	  Element mov_url=e.select(".info a").first();
	    	  //获取每部电影url
	    	  Document  mov_doc=Jsoup.connect(mov_url.attr("href")).get();
	    	  
	    	 
	    	  //获取title 
	    	  Elements title=mov_doc.select("span[property='v:itemreviewed']");
	    	  film.setTitle(title.text().toString());
	    	  //获取排名
	    	  int num =Integer.parseInt( e.select(".item em").first().text());
	    	  film.setNum(num);    	  
	    	  //获取短评
	    	  String  quote=e.select(".inq").text().toString();
	    	  film.setQuote(quote);
	    	  //获取详细信息
	    	  String info1=getInfo(mov_doc);
	    	  film.setInfo(info1);
	    	  
	    	  //获取评分
	    	  double rating=Double.parseDouble(mov_doc.select(".rating_num").first().text());
	    	  film.setRating(rating);
	    	  //获取上映日期
	    	  Element day=mov_doc.select("span[property='v:initialReleaseDate']").last();
	    	  //时间中文本内容 
	    	  String day1=day.text().toString(); 
	    	  film.setDay(day1);
	    	  //评价人数
	    	  int number=Integer.parseInt(mov_doc.select("span[property='v:votes']").text());
	    	  film.setNumber(number);
	    	  //海报
	    	  String poster=e.select("img").first().attr("src").toString();
	    	  film.setPoster(poster);
	    	  
	    	  //创建链接
	    	  URL imgUrl=new URL(poster);
	    	  //获取字节输入流
	    	  inputStream =imgUrl.openConnection().getInputStream();
	    	  //将输入流数据放入缓存区
	    	  bis=new BufferedInputStream(inputStream);
	    	  //读取字节数
	    	  byte [] buf=new byte[1024];
	    	  //生成本地文件
	    	  outputStream=new FileOutputStream(outpath+num+".jpg");
	    	  //
	    	  int size =0;
	    	  //边读边写
	    	  while((size=bis.read(buf))!=-1)
	    	  {
	    	    outputStream.write(buf,0,size);
	    	  }
	    	    film.setPoster(num+".jpg");
	    	    //outputStream.flush();	  
	    	  	    	  
	    	  System.out.println(name+":"+film);
	    	  list.add(film);
	    	  
	    	  }
		  System.out.println(name+"线程，完成："+url);
	  }
			
	  catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();} 
	  finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bis!=null)
			{
				try {
					bis.close();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inputStream!=null)
			{
				try {
					inputStream.close();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	  }
	   
	}
	
    public static String getInfo(Element mov)
	  {  
		String info="导演"+mov.select("a[rel='v:diectedBy']").text()+"\n";
	     info+= "编剧"+mov.select(".attrs").get(1).text()+"\n";
	     info+="主演"+mov.select(".attrs").get(2).text()+"\n";
	     info+="类型"+mov.select("span[property='v:genre']").text();
		 return info;
		 
	  }
	}

	



     

