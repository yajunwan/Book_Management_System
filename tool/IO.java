package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import object.Book;
import view.MainClass;

public class IO{

    public void loadFile(MainClass main,String inputfile){//read file
        
        try{
            File file = new File(inputfile);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lineBuff;
            while((lineBuff = reader.readLine())!=null){
                String [] splitedLine = lineBuff.split(",");
                String bookname = splitedLine[0];
                String author = splitedLine[1];
                float price = Float.parseFloat(splitedLine[2]);
                int publishYear = Integer.parseInt(splitedLine[3]);
                Book book = new Book(bookname,author,price,publishYear);
                MainClass.booklist.add(book);
                MainClass.count ++;
            }
            reader.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
        
    public void writeToFile(MainClass main, String outputfile){//write to file
        String books = "Title,Author,Price,Publish Year\r\n";
        for(int i=0;i<MainClass.booklist.size();i++){
            Book b = (Book)MainClass.booklist.get(i);
            String bookinfo = b.getBookName()+","+b.getAuthorName()+","
                             +b.getBookprice()+","+b.getPublishYear()+"\r\n";
            books+=bookinfo;
        }
        try{
            File outputFile = new File(outputfile);
            FileWriter writer = new FileWriter(outputFile);
            writer.write(books);
            writer.close();
        }catch( IOException e){
            e.printStackTrace();
        }

    }

}