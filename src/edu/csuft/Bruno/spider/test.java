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
 * ץȡҳ��ӰƬ��Ϣ
 */
public class test implements Runnable {
    //ҳ��·��
	String url;
	//�洢ץȡ������
	ArrayList<Film> list;
	//���������
	static private  FileOutputStream outputStream=null;
	//����������
	static private  InputStream inputStream=null;
	//����������
	static private  BufferedInputStream bis=null;
	//ͼƬ���ر���·��
	static public   String outpath="F://spider_code//outpath//";
	//���췽��
	public test(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}
	//ִ��Ҫץȡ������
	public void run() {
		//��ȡ�߳�����
	  String name=Thread.currentThread().getName();
	  System.out.println(name+"�̣߳�����"+url);
	  //Jsoup
	  try { 
		 Document doc=Jsoup.connect(url).get();
		 Elements es=doc.select(".grid_view li");
		 for(Element e :es)
	      {
	    	  Film film=new Film();
	    	  //��ȡÿ����Ӱurl��a��ǩ
	    	  Element mov_url=e.select(".info a").first();
	    	  //��ȡÿ����Ӱurl
	    	  Document  mov_doc=Jsoup.connect(mov_url.attr("href")).get();
	    	  
	    	 
	    	  //��ȡtitle 
	    	  Elements title=mov_doc.select("span[property='v:itemreviewed']");
	    	  film.setTitle(title.text().toString());
	    	  //��ȡ����
	    	  int num =Integer.parseInt( e.select(".item em").first().text());
	    	  film.setNum(num);    	  
	    	  //��ȡ����
	    	  String  quote=e.select(".inq").text().toString();
	    	  film.setQuote(quote);
	    	  //��ȡ��ϸ��Ϣ
	    	  String info1=getInfo(mov_doc);
	    	  film.setInfo(info1);
	    	  
	    	  //��ȡ����
	    	  double rating=Double.parseDouble(mov_doc.select(".rating_num").first().text());
	    	  film.setRating(rating);
	    	  //��ȡ��ӳ����
	    	  Element day=mov_doc.select("span[property='v:initialReleaseDate']").last();
	    	  //ʱ�����ı����� 
	    	  String day1=day.text().toString(); 
	    	  film.setDay(day1);
	    	  //��������
	    	  int number=Integer.parseInt(mov_doc.select("span[property='v:votes']").text());
	    	  film.setNumber(number);
	    	  //����
	    	  String poster=e.select("img").first().attr("src").toString();
	    	  film.setPoster(poster);
	    	  
	    	  //��������
	    	  URL imgUrl=new URL(poster);
	    	  //��ȡ�ֽ�������
	    	  inputStream =imgUrl.openConnection().getInputStream();
	    	  //�����������ݷ��뻺����
	    	  bis=new BufferedInputStream(inputStream);
	    	  //��ȡ�ֽ���
	    	  byte [] buf=new byte[1024];
	    	  //���ɱ����ļ�
	    	  outputStream=new FileOutputStream(outpath+num+".jpg");
	    	  //
	    	  int size =0;
	    	  //�߶���д
	    	  while((size=bis.read(buf))!=-1)
	    	  {
	    	    outputStream.write(buf,0,size);
	    	  }
	    	    film.setPoster(num+".jpg");
	    	    //outputStream.flush();	  
	    	  	    	  
	    	  System.out.println(name+":"+film);
	    	  list.add(film);
	    	  
	    	  }
		  System.out.println(name+"�̣߳���ɣ�"+url);
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
		String info="����"+mov.select("a[rel='v:diectedBy']").text()+"\n";
	     info+= "���"+mov.select(".attrs").get(1).text()+"\n";
	     info+="����"+mov.select(".attrs").get(2).text()+"\n";
	     info+="����"+mov.select("span[property='v:genre']").text();
		 return info;
		 
	  }
	}

	



     

