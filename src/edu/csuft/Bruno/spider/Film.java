package edu.csuft.Bruno.spider;

import java.util.Date;

public class Film {
  /*
   * ӰƬ����
  * */	
    private String title;
    /*
     * �����Ϣ
     * */
    private String info;
    /*
     * ����
     * */
    private double rating;
    /*
     * ����
     * */
    private int num;
   /**
    * ����
    */
    private String poster;
   /**
    * ����
    */
    private String quote;
   /**��������
    * 
    * @return
    */
    private int number;
   
   /**��ӳ����
    * 
    * @return
    */
    private String day;
   
   
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}
public double getRating() {
	return rating;
}
public void setRating(double rating) {
	this.rating = rating;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getPoster() {
	return poster;
}
public void setPoster(String poster) {
	this.poster = poster;
}
public String getQuote() {
	return quote;
}
public void setQuote(String quote) {
	this.quote = quote;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public String ToCSV() {
	
	return String.format("%d,%s,%d,%.1f,%s,%s\n",num,title,number,rating,quote,day);
}

public String toString() {
	return "Film [title=" + title + ", info=" + info + ", rating=" + rating + ", num=" + num + ", poster=" + poster
			+ ", quote=" + quote + ", number=" + number + ", day=" + day + "]";
}
   
    
}
