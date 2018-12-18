package edu.csuft.Bruno.spider;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threadpool_test {

	public static void main(String[] args) {
		//test ts=new test(4);
		// TODO Auto-generated method stub
		//创建一个线程
	    //Thread thread=new Thread(); 
    	//8个线程-固定大小
    	//ExecutorService pool=Executors.newFixedThreadPool(4);
    	//无限
    	ExecutorService pool = Executors.newCachedThreadPool();
    	//单线程
    	//pool=Executors.newSingleThreadExecutor();
    	//给线程池提交任务
    	ArrayList<Film> list =new ArrayList<>();
    	String url="https://movie.douban.com/top250?start=";
    	
    	for(int i=0;i<10;i++) {
    	//%s=url,%d=i*25
    	String path=String.format("%s%d", url,i*25);
    	pool.submit(new test(path,list));
    	}
    	//关闭线程池
    	pool.shutdown();
    	//判断线程池中任务是否结束
    	//System.out.println(pool.isTerminated());
    	
    	while(!pool.isTerminated()){
    		try {
    			//隔一秒检测
				Thread.sleep(1000);
			} 
    		catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//最后输出集合大小
    	System.out.println(list.size());
    	
    	for (Film film:list) {
			System.out.println(film.ToCSV());
		}
    	//写入文件
    	String file="F:/film.cvs";
    	//file=film.csv;
    	//io
    	try(FileWriter out=new FileWriter(file)) {
			for (Film film : list) {
				out.write(film.ToCSV());
			}
			System.out.println("successful");
		} 
    	catch (Exception e) {
			// TODO: handle exception
		}
    	
	}

}
