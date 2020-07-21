package view;

import object.Book;
import tool.IO;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass{
    public static ArrayList<Book>booklist = new ArrayList<Book>();
    public static int count = 0;
    public static int INPUTFILE = 0;
    public static int OUTPUTFILE = 1;
    public boolean parseFile = false;

    public MainClass(String inputF,String outputF){
        Scanner input = new Scanner(System.in);
        IO io  = new IO();
        if (inputF.length()>1){
            io.loadFile(this, inputF);
            parseFile = true;
        }
        mainPage();
        while(true){
            int choice = input.nextInt();
            if (choice == 5){
                if (parseFile){
                    io.writeToFile(this, outputF);
                }
                System.out.println("Successfully exited the system, thanks for using");
                break;
            }

            switch(choice){
                case 1:addBook();
                break;
                case 2:deleteBook();
                break;
                case 3:changeBook();
                break;
                case 4:findBook();
                default:
                System.out.println("Invalid input, choose again between 1-5");
                mainPage();
                continue;
            }
        }
        input.close();
    }
    private void mainPage(){
        System.out.println("Welcome to the book management system");
        System.out.println("Please enter a number from 1-5 to have the following actions:");
        System.out.println("Add a book to the library -- Enter 1");
        System.out.println("Delete a book from the library -- Enter 2");
        System.out.println("Edit the information of an existed book -- Enter 3");
        System.out.println("Find a book you want -- Enter 4");
        System.out.println("To exit the system -- Enter 5");
    }

    private void addBook(){
        System.out.println("There are "+booklist.size()+" book(s) in the library now");
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of the book");
        String bookname = input.nextLine();
        System.out.println("Please enter the name of the author");
        String author = input.nextLine();
        System.out.println("Please enter the price of the book");
        float price = input.nextFloat();
        System.out.println("Please enter the publish year of the book");
        int publishYear = input.nextInt();
        Book newBook = new Book(bookname,author,price,publishYear);
        booklist.add(newBook);
        count++;
        System.out.println("Successfully added the book!");
        showAllBook();
        System.out.println("Enter 1 if you want to add another book, Enter 2 if you want to back to the main page");
        int c = input.nextInt();
        if (c == 1){
            addBook();
        }else if (c == 2){
            mainPage();
        }
        input.close();
    }

    private void deleteBook(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Please enter in which way you want to delete the book: ");
            System.out.println("The index -- Enter 1");
            System.out.println("The name -- Enter 2");
            System.out.println("Back to main menu -- Enter 3");

            int choice = input.nextInt();
            if (choice == 1){
                System.out.println("Please enter the index of the book you want to delete");
                int idx = input.nextInt();
                if (idx>count){
                    System.out.println("The maximum size of the library is "+booklist.size()+", the number you entered has exceeded the capacity.");
                }else{  
                    booklist.remove(idx-1);
                    count--;
                    System.out.println("The book has been successfully deleted!");
                    showAllBook();
                }
            }else if(choice == 2){
                boolean found = false;
                System.out.println("Please enter the name of the book you want to delete");
                String bookname = input.nextLine();
                for (int i=0;i<booklist.size();i++){
                    Book b = (Book)booklist.get(i);
                    if (b.getBookName().equals(bookname)){
                        booklist.remove(i);
                        count--;
                        found = true;
                        break;
                    }
                }
                if (!found){
                    System.out.println("There is no such book named "+bookname+" in the library");
                }else{
                    System.out.println("Successfully deleted the book");
                    showAllBook();
                }
            }else if(choice == 3){
                mainPage();
                break;
            }else{
                System.out.println("Invalid input");
            }
        }
        input.close();
    }

    private void changeBook(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Please enter in which way you want to find the book to edit: ");
            System.out.println("The index -- Enter 1");
            System.out.println("The name -- Enter 2");
            System.out.println("Back to main menu -- Enter 3");

            int choice = input.nextInt();
            if (choice == 1){
                System.out.println("Please enter the index of the book you want to edit");
                int idx = input.nextInt();
                if (idx>count){
                    System.out.println("The maximum size of the library is "+booklist.size()+", the number you entered has exceeded the capacity.");
                }else{  
                    Book b = (Book)booklist.get(idx-1);
                    System.out.println("The information of the book is "+b.bookInfo());
                    System.out.println("Please enter the new name of the book");
                    b.setBookName(input.nextLine());
                    System.out.println("Please enter the author of the book");
                    b.setAuthorName(input.nextLine());
                    System.out.println("Please enter the price of the book");
                    b.setBookprice(input.nextFloat());
                }
            }else if(choice == 2){
                boolean found = false;
                System.out.println("Please enter the name of the book you want to edit");
                String bookname = input.nextLine();
                for (int i=0;i<booklist.size();i++){
                    Book b = (Book)booklist.get(i);
                    if (b.getBookName().equals(bookname)){
                        System.out.println("The information of the book is "+b.bookInfo());
                        System.out.println("Please enter the new name of the book");
                        b.setBookName(input.nextLine());
                        System.out.println("Please enter the author of the book");
                        b.setAuthorName(input.nextLine());
                        System.out.println("Please enter the price of the book");
                        b.setBookprice(input.nextFloat());
                        found = true;
                        break;
                    }
                }
                if (!found){
                    System.out.println("There is no such book named "+bookname+" in the library");
                }else{
                    System.out.println("Successfully edit the book");
                    showAllBook();
                }
            }else if(choice == 3){
                mainPage();
                break;
            }else{
                System.out.println("Invalid input");
            }
        }
        input.close();
    }

    private void findBook(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Please enter in which way you want to find the book: ");
            System.out.println("The index -- Enter 1");
            System.out.println("The name -- Enter 2");
            System.out.println("Back to main menu -- Enter 3");

            int choice = input.nextInt();
            if (choice == 1){
                System.out.println("Please enter the index of the book you want to find");
                int idx = input.nextInt();
                if (idx>count){
                    System.out.println("The maximum size of the library is "+booklist.size()+", the number you entered has exceeded the capacity.");
                }else{  
                    Book b = (Book)booklist.get(idx-1);
                    System.out.println("The information of the book is "+b.bookInfo());
                }
            }else if(choice == 2){
                boolean found = false;
                System.out.println("Please enter the name of the book you want to edit");
                String bookname = input.nextLine();
                for (int i=0;i<booklist.size();i++){
                    Book b = (Book)booklist.get(i);
                    if (b.getBookName().equals(bookname)){
                        System.out.println("The information of the book is "+b.bookInfo());
                        found = true;
                        break;
                    }
                }
                if (!found){
                    System.out.println("There is no such book named "+bookname+" in the library");
                }else{
                    System.out.println("Successfully edit the book");
                    showAllBook();
                }
            }else if(choice == 3){
                mainPage();
                break;
            }else{
                System.out.println("Invalid input");
            }
        }
        input.close();
    }

    void showAllBook(){
        for (int i=0;i<booklist.size();i++){
            Book b = (Book)booklist.get(i);
            System.out.println(b.bookInfo());
        }
    }
    public static void main(String[]args){
        //
        if (args.length>1){
           String inputfile = args[INPUTFILE];
           String outputfile = args[OUTPUTFILE];
           new MainClass(inputfile,outputfile); 
        }else{
           new MainClass("","");
        }
    }
}

