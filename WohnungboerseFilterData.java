
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.text.SimpleDateFormat;

import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS PRO
 */
public class WohnungboerseFilterData {  
    String line; 
    static Set<String> set = new HashSet<String>();
     PrintWriter writer1 = new PrintWriter("the-file-page.txt", "UTF-8");
      ResultSet myResult=null;
            PreparedStatement myResult1=null;
            Connection myConnection;
            int x; 
            double zimmer;
            double m2;
            String city;
            static String datum;
            static Date date;
             static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public WohnungboerseFilterData(String s) throws FileNotFoundException, UnsupportedEncodingException, IOException, ParseException{   
                URL oracle = new URL(s);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null){
            
           //Filter Budget
           
           writer1.println(inputLine);
           if(inputLine.contains(".000")){
           String splitted[] = inputLine.split(".000");
           String money = splitted[0];
            
           String splitted1[] = money.split(" ");
         
           String budget = splitted1[splitted1.length-1];
           x = Integer.valueOf(budget)*1000;
            System.out.println(x); 
           try{
               //Budget in Datenbank speichern            
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                     myResult1 = myConnection.prepareStatement("UPDATE posting SET Budget = ? WHERE linkPosting = ?");
                     myResult1.setInt(1,x);
                     myResult1.setString(2,s);
                     myResult1.executeUpdate();
                     myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                             
                      
          }  
           
           
           //Filter Room and qm
           if(inputLine.contains("von ")){
           String splitted[] = inputLine.split("von ");
           String money = splitted[1];
            
           String splitted1[] = money.split(" ");
         
           String budget = splitted1[0]; 
          try {

          //Room in Datenbank speichern
          zimmer = Double.parseDouble(budget);
           if(zimmer< 10){
               
            System.out.println("Anzahl Zimmer "+zimmer);   
             try{
                           
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 = myConnection.prepareStatement("UPDATE posting SET Room = ? WHERE linkPosting = ?");
                    myResult1.setDouble(1,zimmer);
                    myResult1.setString(2,s);
                    myResult1.executeUpdate();
                    myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
           
           
           
           
           
           
           } 
             if(zimmer>= 10 && zimmer<10000){
                 m2=zimmer;
                System.out.println("qm "+m2); 
                //qm wird in Datenbank gespeichert
             try{
                           
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                     myResult1 = myConnection.prepareStatement("UPDATE posting SET qm = ? WHERE linkPosting = ?");
                     myResult1.setDouble(1,m2);
                     myResult1.setString(2,s);
                        myResult1.executeUpdate();
                        myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
             
             
             
             
             
             
             
             }
             } catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
}
          }  
            
            //Filter Location
            if(inputLine.contains("DE,")){
                String splitted[] = inputLine.split("DE,");
                String money = splitted[1];
            
                String splitted1[] = money.split("</p>");
         
                String stadt = splitted1[0];
                String splitted2[] = stadt.split(" ");
                city=splitted2[1];
                System.out.println("Stadt : "+city);  
             try{
                //Location wird in Datenbank gespeichert           
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 = myConnection.prepareStatement("UPDATE posting SET Location = ? WHERE linkPosting = ?");
                    myResult1.setString(1,city);
                     myResult1.setString(2,s);
                     
                        myResult1.executeUpdate();
                        myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
          }  
             //Filter Datum
                if(inputLine.contains("vom: ")){
                    String splitted[] = inputLine.split("vom: ");
                    String d = splitted[1];
            
                    String splitted1[] = d.split(" -");
         
                    datum = splitted1[0];
       
                    java.util.Date date1 = sdf.parse(datum);
                    java.sql.Date date2 = new java.sql.Date(date1.getTime());
           
          
                    System.out.println("Datum : "+ date2);  
                    
             //Datum wird in Datenbank gespeichert       
             try{
                           
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 = myConnection.prepareStatement("UPDATE posting SET Date = ? WHERE linkPosting = ?");
                   
                    myResult1.setDate(1,date2);
                     myResult1.setString(2,s);
                     
                        myResult1.executeUpdate();
                        myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
          }  
             
           
           
           
            
             } 
    
   }

    private void Cast(String datum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
