
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class QuokaFilterLink {
     String line; 
    static Set<String> set = new HashSet<String>();
     PrintWriter writer1 = new PrintWriter("the-file-page.txt", "UTF-8");
      ResultSet myResult=null;
       static Set<String> setLink = new HashSet<String>(); 
            PreparedStatement myResult1=null;
            Connection myConnection;
            int x; 
            double zimmer;
            double m2;
            String city;
            static String datum;
            static Date date;
             static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
     
            
    
    
    
    
    public QuokaFilterLink(String link)throws FileNotFoundException, UnsupportedEncodingException, IOException, ParseException, SQLException{
    
        URL oracle = new URL(link);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        
        String inputLine;
        
        //Link von parameter wird hier gelesen und der Link hat Beschreibung von Miete oder Provision wird ganze prozess gebrochen
        //sonst wird die Link als Parameter fuer klass QuokaFilterData erzeugt 
        while ((inputLine = in.readLine()) != null){
            if(inputLine.contains("href=")){
                String[] splitted= inputLine.split("href=");
                if(splitted.length>1){
                    String[] splitted1 = splitted[1].split("class");
                   
                    String[] splitted2=splitted1[0].split("im");
                    if(splitted2.length>1){
                    String[] splitted3=splitted2[1].split(".html");
                    link="http://www.quoka.de/i"+splitted3[0]+".html";}
                   
                }
            
            
            }
            
            if(inputLine.contains("Provision")||inputLine.contains("inkl.MwSt")){
            
                break;
            }
            if(inputLine.contains("description")){
                String[] split= inputLine.split("description");
                if(split.length>1){
                    String[] splitt1 = split[1].split("</span>");
                    if(splitt1[0].contains("miete")||splitt1[0].contains("Miete")||splitt1[0].contains("MIETE")){
                        break;
                    
                    }
                    
                String[] splitt= inputLine.split("title");
                if(splitt.length>1){
                   
                    if(splitt[1].contains("miete")||splitt[1].contains("Miete")||splitt[1].contains("MIETE")){
                        break;
                    
                    }    
                    
                    
                    
                    
                    if(splitt1[0].contains("such")||splitt1[0].contains("Such")||splitt1[0].contains("SUCH")){
                        if(splitt1[0].contains("miete")||splitt1[0].contains("Miete")||splitt1[0].contains("MIETE")){
                            break;
                        }
                        
                        else{
                         if(setLink.contains(link)==false)
                            {   setLink.add(link);
                                System.out.println(link);
                                QuokaFilterData part3 = new QuokaFilterData(link);}
                        }
                    
                    }
                }
            
            
            }
    
    
}
        }}}