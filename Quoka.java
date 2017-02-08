/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS PRO
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Quoka { 
     static String linkComplete;
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException, FileNotFoundException, UnsupportedEncodingException, SQLException{ 
        int x=10; 
        int Budget; 
        String budget; 
         ResultSet myResult=null;
            PreparedStatement myResult1=null;
            Connection myConnection;
       
            
            
            
            
       PrintWriter writer = new PrintWriter("the-file.txt", "UTF-8");
       // Hier wird Quoka seite fuer kaufgesuche durch gelaufen, und die gefundene Link,
       //die /immobilien-haeuser/ enthaelt wird in QuokaFilterLink als parameter erzeugt
        for(int i=4;i<=x;i++){
        
                  Document doc = Jsoup.connect("http://www.quoka.de/kleinanzeigen/cat_27_2703_ct_0_page_"+i+".html").get();
                   // System.out.println(doc);
                 
                
                   Elements hrefs = doc.select("a");
               
                    
                    for(Element e : hrefs){
                        
                        if(e.attr("href").contains("/immobilien-haeuser/")){
                       
                             linkComplete="http://www.quoka.de"+e.attr("href");
                               QuokaFilterLink A = new QuokaFilterLink(linkComplete); 
                            
                 
                    }}
                            
                 
            
        }}
        
        
       
     
    }
        
        
        
        
        
    
    
    

