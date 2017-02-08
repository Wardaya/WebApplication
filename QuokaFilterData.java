
import java.io.BufferedReader;
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
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS PRO
 */
public class QuokaFilterData {
     String line; 
     static Set<String> set = new HashSet<String>();
     PrintWriter writer1 = new PrintWriter("the-file-page.txt", "UTF-8");
     ResultSet myResult=null;
     PreparedStatement myResult1=null;
     Connection myConnection;
     int budget; 
     double zimmer;
     double m2;
     static double Room;
     static double qm;
     static String city;
     static String datum;
     static Date date;
     static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
             
    public QuokaFilterData(String s) throws FileNotFoundException, UnsupportedEncodingException, IOException, ParseException, SQLException{   
        //Parameter in QuokaFilterData ist die link von Leute, die ein Immobilien kaufen wollen
        URL oracle = new URL(s);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        System.out.println(s);
           try{
                //Link wird in Datenbank gespeichert
                           
                 myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                 myResult1 = myConnection.prepareStatement("INSERT INTO posting(linkPosting,Budget,Location,qm,Room,Date) VALUES(?,?,?,?,?,?)");
                 myResult1.setString(1,s);
                 myResult1.setString(2,null);
                 myResult1.setString(3,null);
                 myResult1.setString(4,null);
                 myResult1.setString(5,null); 
                 myResult1.setDate(6,null); 
                    
                 myResult1.executeUpdate();
                 myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            
            writer1.println(inputLine);
          
          //Filter Location
           if(inputLine.contains("og:locality")){
              String[] splitted = inputLine.split("og:locality");
              if(splitted.length>1){
                  String[] splitted1 = splitted[1].split("content=");
                  if(splitted1.length>1){
                      String[] splitted2 = splitted1[1].split(" >");
                      city = splitted2[0].substring(1,splitted2[0].length()-1);
                      System.out.println(city);
                      
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
              }
          }
         
         
         
         //Filter Budget
          if(inputLine.contains("price")){
              String[] splitted = inputLine.split("price");
              if(splitted.length>1){
                  if(splitted[1].contains("content=")){
                      String[] splitted1 = splitted[1].split("content=");
                        if(splitted1.length>1){
                            String[] splitted2=splitted1[1].split(" />");
                            //System.out.println("Real "+splitted2[0]);
                            if(splitted2[0].length()>1){
                             String money = splitted2[0].substring(1,splitted2[0].length()-1);
                             budget = Integer.valueOf(money);
                             if(budget>5000){
                                System.out.println("Budget "+budget);
                                   try{
                 //Budget wird in Datenbank gespeichert          
                 myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 =  myResult1 = myConnection.prepareStatement("UPDATE posting SET Budget = ? WHERE linkPosting = ?");
                    myResult1.setInt(1,budget);
                    myResult1.setString(2,s);
                    
                    myResult1.executeUpdate();
                    myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                                } }
               
                  }
              }
          }
    
}
           //Filter Room 
                        if(inputLine.contains(" Zimmer")){
                    
                       String splitted3[] = inputLine.split(" Zimmer");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                      try{
                       Room=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                       
                            try{
                 //Room wird in Datenbank gespeichert          
                    myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 =  myResult1 = myConnection.prepareStatement("UPDATE posting SET Room = ? WHERE linkPosting = ?");
                    myResult1.setDouble(1,Room);
                    myResult1.setString(2,s);
                    
                    myResult1.executeUpdate();
                    myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                      
                      }      
                        
                        
                    //Filter qm 
                        if(inputLine.contains(" qm")){
                    
                       String splitted3[] = inputLine.split(" qm");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                      try{
                       Room=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                       
                            try{
                 //Room wird in Datenbank gespeichert          
                    myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 =  myResult1 = myConnection.prepareStatement("UPDATE posting SET qm = ? WHERE linkPosting = ?");
                    myResult1.setDouble(1,qm);
                    myResult1.setString(2,s);
                    
                    myResult1.executeUpdate();
                    myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                      
                      }          
                        
                        
                        
                        
              
                       
                       }
           
          
          
          
          
    }}