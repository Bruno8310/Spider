package edu.csuft.Bruno.spider;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threadpool_test {

	public static void main(String[] args) {
		//test ts=new test(4);
		// TODO Auto-generated method stub
		//����һ���߳�
	    //Thread thread=new Thread(); 
    	//8���߳�-�̶���С
    	//ExecutorService pool=Executors.newFixedThreadPool(4);
    	//����
    	ExecutorService pool = Executors.newCachedThreadPool();
    	//���߳�
    	//pool=Executors.newSingleThreadExecutor();
    	//���̳߳��ύ����
    	ArrayList<Film> list =new ArrayList<>();
    	String url="https://movie.douban.com/top250?start=";
    	
    	for(int i=0;i<10;i++) {
    	//%s=url,%d=i*25
    	String path=String.format("%s%d", url,i*25);
    	pool.submit(new test(path,list));
    	}
    	//�ر��̳߳�
    	pool.shutdown();
    	//�ж��̳߳��������Ƿ����
    	//System.out.println(pool.isTerminated());
    	
    	while(!pool.isTerminated()){
    		try {
    			//��һ����
				Thread.sleep(1000);
			} 
    		catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//���������ϴ�С
    	System.out.println(list.size());
    	
    	for (Film film:list) {
			System.out.println(film.ToCSV());
		}
    	//д���ļ�
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
