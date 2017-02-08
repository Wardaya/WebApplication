

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
public class FacebookFilterData { 
       static  String Object; 
     static String PostId;
     static String groupName; 
     static String Geld;
     static String Location;
     static String UserId;
     static String Message;
     static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     static String Datum;
     static Date date;
     static String month;
     static int Geld1;
     static String Contain;
     static double qm;
     static double Room;
     static String test;
     static java.sql.Date date2= null;   
     String line;
     Integer counter =0;
     static StringBuilder sb=new StringBuilder();;
     static Set<String> setId = new HashSet<String>(); 
     public FacebookFilterData(String e) throws FileNotFoundException, UnsupportedEncodingException, IOException, ParseException{ 
         FileInputStream fileInput= new FileInputStream(e);
        int x;
        ResultSet myResult=null;
        PreparedStatement myResult1=null;
        java.sql.Connection myConnection;
      
        InputStreamReader streamReader= new InputStreamReader(fileInput, "UTF-8");
        //Hier wird die Datei gelesen die in Facebook Klasse geschrieben ist und die wichtige Daten wird gefiltert
        
        try (BufferedReader br = new BufferedReader(streamReader)) { 
                 line=null;
                
               while ((( line=br.readLine())!=null)) { 
              
                  
                    if(line.contains(">")){
                      Location= null;
                      PostId=null;
                      UserId = null;
                      Geld1 = 0;
                      Datum = null;
                      date = null;
                      
                      
                     //Filter Posting ID 
                       String splitted[] = line.split(">");
                         if(splitted.length>1){
                            String splitted1[] = splitted[1].split("<");
                            PostId=splitted1[0];}
                      
                   
                   } 
                    
                     if(line.contains("*")){
                        sb = new StringBuilder();
                        sb.append(line);
                  
                      
                   
                   } 
                    
                    //Filter UserID
                     if(line.contains("%")){
                       
                       String splitted[] = line.split("%");
                         if(splitted.length>1){
                            String splitted1[] = splitted[1].split("<");
                            UserId=splitted1[0];}
                      } 
                     
                     
                     
                    
                    
                      if(line.contains("~")){
                       
                       //Filter Date   
                           
                       String splitted[] = line.split("~");
                        if(splitted.length>1){
                       String splitted1[] = splitted[1].split("<");
                       
                       Datum=splitted1[0];
                      
                       String[] splitted3 = Datum.split(" ");
                     
                       if(splitted3[1].contains("Jan")){
                       date= sdf.parse(splitted3[5]+"-01-"+splitted3[2]);
                       date2 = new java.sql.Date(date.getTime());
                       }
                       
                       if(splitted3[1].contains("Feb")){
                       date= sdf.parse(splitted3[5]+"-02-"+splitted3[2]);
                        date2 = new java.sql.Date(date.getTime());
                       }
                       
                       if(splitted3[1].contains("Mar")){
                           
                       date= sdf.parse(splitted3[5]+"-03-"+splitted3[2]);
                        date2 = new java.sql.Date(date.getTime());
                       }
                       
                       if(splitted3[1].contains("Apr")){
                       date= sdf.parse(splitted3[5]+"-04-"+splitted3[2]);
                        date2 = new java.sql.Date(date.getTime());
                      }
                       
                       if(splitted3[1].contains("May")){
                       date= sdf.parse(splitted3[5]+"-05-"+splitted3[2]);
                        date2 = new java.sql.Date(date.getTime());
                      }
                       
                       if(splitted3[1].contains("June")){
                       date= sdf.parse(splitted3[5]+"-06-"+splitted3[2]);
                      date2 = new java.sql.Date(date.getTime());
                       }
                       
                       if(splitted3[1].contains("July")){
                       date= sdf.parse(splitted3[5]+"-07-"+splitted3[2]);
                      date2 = new java.sql.Date(date.getTime());
                      }
                       
                       if(splitted3[1].contains("Aug")){
                       date= sdf.parse(splitted3[5]+"-08-"+splitted3[2]);
                       date2 = new java.sql.Date(date.getTime());
                       }
                       
                       if(splitted3[1].contains("Sept")){
                       date= sdf.parse(splitted3[5]+"-09-"+splitted3[2]);
                       date2 = new java.sql.Date(date.getTime());
                       }
                       
                       if(splitted3[1].contains("Oct")){
                       date= sdf.parse(splitted3[5]+"-10-"+splitted3[2]);
                      date2 = new java.sql.Date(date.getTime());
                      }
                       
                       if(splitted3[1].contains("Nov")){
                       date= sdf.parse(splitted3[5]+"-11-"+splitted3[2]);
                       date2 = new java.sql.Date(date.getTime());
                      }
                       
                       if(splitted3[1].contains("Dec")){
                       date= sdf.parse(splitted3[5]+"-12-"+splitted3[2]);
                       date2 = new java.sql.Date(date.getTime());
                       }}
                       
                      
                       
                        
                   }    
                      
                   
                        
                        
                 //Filter Inhalt      
                 if(line.contains(".^^^^.")){
                     Contain = sb.toString();
                     
                     
                      if(Contain.contains(" kaufen ")  ){
                      
                      //Filter Objekt
                       String splitted[] = Contain.split(" ein "); 
                       
                       if(splitted.length>1){
                           String splitted10[] = splitted[1].split(" ");
                       Object=splitted10[0];
                        }
                       
                       
                       //Filter Location
                       
                       if(Contain.contains(" in ")){
                    
                       String splitted3[] = Contain.split(" in ");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[1].split(" ");
                       Location=splitted4[0];}
                      
                       
                   
                   }    
                    //Filter qm
                       if(Contain.contains(" qm ")){
                    
                       String splitted3[] = Contain.split(" qm ");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                        try{
                       Room=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                       
                       
                   
                   }    
                       
                       if(Contain.contains("qm")){
                    
                       String splitted3[] = Contain.split("qm");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                          try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                      }
                       
                      if(Contain.contains(" m2 ")){
                    
                       String splitted3[] = Contain.split(" m2 ");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                       
                   
                   }    
                       
                       if(Contain.contains("m2")){
                    
                       String splitted3[] = Contain.split("m2");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                      }
                      //Filter Room 
                        if(Contain.contains(" Zimmer")){
                    
                       String splitted3[] = Contain.split(" Zimmer");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       Room=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                      }       
                       
                       
                       
                       
                       
                       //Filter Budget
                       
                       if(Contain.contains(".000€")){
                       
                       String splitted1[] = Contain.split(".000€");
                       String splitted2[] = splitted1[0].split(" ");
                     
                       Geld=splitted2[splitted2.length-1];
                    
                       Geld1=Integer.valueOf(Geld)*1000;
                       
                          }
                            
                            
                            
                       if(Contain.contains("000€")){
                       
                       String splitted5[] = Contain.split(".000€");
                       String splitted6[] = splitted5[0].split(" ");
                     
                       Geld=splitted6[splitted6.length-1];
                    
                       Geld1=Integer.valueOf(Geld)*1000;
                       
                          }
                       
                     //Filter PostID  
                     if(setId.contains(PostId)==false){
                            setId.add(PostId);
                                }
                       if(Geld1>0){         
                                    try{
                           
                        myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                        myResult1 = myConnection.prepareStatement("INSERT INTO posting(linkPosting,Budget,Location,qm,Room,Date) VALUES(?,?,?,?,?,?)");
                        myResult1.setString(1,"www.facebook.com/"+UserId);
                        myResult1.setInt(2,Geld1);
                        myResult1.setString(3,Location);
                        myResult1.setDouble(4,qm);
                        myResult1.setDouble(5,Room); 
                        myResult1.setDate(6,date2); 
                        myResult1.executeUpdate();
                        myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                       
                       }}
                                
                     
                  
                          
                 
                      //Falls KeyWord Kaufen erkannt, mache genau die gleiche falls keyword kaufen erkannt ist
                       if(Contain.contains(" Kaufen ")  ){
                      
                      
                       String splitted[] = Contain.split(" ein "); 
                       
                        if(splitted.length>1){
                           String splitted10[] = splitted[1].split(" ");
                       Object=splitted10[0];
                        }
                       
                       
                       
                            if(Contain.contains(" in ")){
                    
                       String splitted3[] = Contain.split(" in ");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[1].split(" ");
                       Location=splitted4[0];}
                      
                       
                   
                   }  
                            
                            
                          //Filter qm
                       if(Contain.contains(" qm ")){
                    
                       String splitted3[] = Contain.split(" qm ");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }
                       
                       }
                      
                       
                   
                   }    
                       
                       if(Contain.contains("qm")){
                    
                       String splitted3[] = Contain.split("qm");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                      }
                       
                      if(Contain.contains(" m2 ")){
                    
                       String splitted3[] = Contain.split(" m2 ");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                       
                   
                   }    
                       
                       if(Contain.contains("m2")){
                    
                       String splitted3[] = Contain.split("m2");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                        try{
                       qm=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }}
                      
                      }
                      //Filter Room 
                        if(Contain.contains(" Zimmer")){
                    
                       String splitted3[] = Contain.split(" Zimmer");
                       if(splitted3.length>1){
                       String splitted4[] = splitted3[0].split(" ");
                         try{
                       Room=Double.parseDouble(splitted4[splitted4.length-1]);}
                       
                       catch (NumberFormatException ex) {
                    System.out.println("String ist keine Zahl");
                       }
                       
                       }
                      
                      }       
                          
                       //Filter Budget 
                       if(Contain.contains(".000€")){
                       
                       String splitted1[] = Contain.split(".000€");
                       String splitted2[] = splitted1[0].split(" ");
                     
                       Geld=splitted2[splitted2.length-1];
                    
                       Geld1=Integer.valueOf(Geld)*1000;
                       
                          }
                            
                            
                            
                       if(Contain.contains("000€")){
                       
                        String splitted5[] = Contain.split(".000€");
                        String splitted6[] = splitted5[0].split(" ");
                     
                        Geld=splitted6[splitted6.length-1];
                    
                        Geld1=Integer.valueOf(Geld)*1000;
                       
                          }
                                if(setId.contains(PostId)==false){
                                    setId.add(PostId);
                               
                                
                                
                                try{
                           
                 myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 = myConnection.prepareStatement("INSERT INTO posting(linkPosting,Budget,Location,qm,Room,Date) VALUES(?,?,?,?,?,?)");
                    myResult1.setString(1,"www.facebook.com/"+UserId);
                    myResult1.setInt(2,Geld1);
                    myResult1.setString(3,Location);
                    myResult1.setDouble(4,qm);
                    myResult1.setDouble(5,Room); 
                    myResult1.setDate(6,date2); 
                    myResult1.executeUpdate();
                    myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                       
                       }
                                
                                
                                
                                
                                
                                
                                
                                }
                     
                  
                        } 
                 
                 
                 
                 
                 
                 
                 
                 
                 } 
                        
               
                        
                        
                      
               
            br.close();     }
          }}
                   




    
    
    
    

    

