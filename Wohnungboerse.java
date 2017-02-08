

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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


public class Wohnungboerse { 
     static String linkComplete;
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException{ 
        int x=20; 
        int Budget; 
        String budget; 
         ResultSet myResult=null;
            PreparedStatement myResult1=null;
            Connection myConnection;
       
            
            
            
       //Die gesuchte Link wird gesucht von jeder Pages in for schleife     
       PrintWriter writer = new PrintWriter("the-file.txt", "UTF-8");
        for(int i=8;i<=x;i++){
        
                  Document doc = Jsoup.connect("http://www.wohnungsboerse.net/kaufgesuche/page:"+i).get();
                    Elements hrefs = doc.select("a");
                    for(Element e : hrefs){
                        //Falls richtige Link gefunden ist, wird linkComplete damit initialisiert
                        if(e.attr("href").contains("/kaufgesuche-")){ 
                              System.out.println(e.attr("href")); 
                           
                            linkComplete="http://www.wohnungsboerse.net"+e.attr("href");
      
                    //linkComplete wird in Datenbank gespeichert und als Parameter in WohnungboerseFilterData erzeugt        
                            try{
                           
                    myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh");
                  
                    myResult1 = myConnection.prepareStatement("INSERT INTO posting(linkPosting,Budget,Location,qm,Room) VALUES(?,?,?,?,?)");
                    myResult1.setString(1,linkComplete);
                    myResult1.setString(2,null);
                    myResult1.setString(3,null);
                    myResult1.setString(4,null);
                    myResult1.setString(5,null); 
                    
                        myResult1.executeUpdate();
                        myResult1.close();
                  
                   }
                   catch(SQLException etc){etc.printStackTrace();}
                        WohnungboerseFilterData A = new WohnungboerseFilterData(linkComplete);
                       }
                    }
        
   
            
            }
        
        
       
     
    }
        
        
        
        
        }
    
    
    

