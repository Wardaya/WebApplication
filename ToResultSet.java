package com;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.net.URI;

import java.sql.*; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Iterator; 
import java.util.List;

import javax.swing.JOptionPane; 
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang.StringUtils;
import org.json.XML;
import static org.jsoup.Connection.Method.GET;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ASUS PRO
 */
@Path("service") 

public final class ToResultSet { 

    private Connection connection=null; 
    private Statement statement=null; 
    private ResultSet resultSet=null; 
    URI uri=null;
    List result = new ArrayList(); 
    org.json.JSONObject xmlJSONObj;
    static String outPut;
    private String host = "localhost:3306"; 
    private String dbName = "location?autoReconnect=true&useSSL=false"; 
    private String dbTable = "posting"; 
    private String user = "root"; 
    private String pass = "dreamhigh"; 
    Integer test=null;
     StringBuilder sb = new StringBuilder();
    Date minDate;
    Document doc;
   
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     
    //Hier wird restAPI tabelle programmiert. der liefert am Ende sichtbare Informationen von jedem PersonDaten in Web Browser
    //als HTML 
    @Path("/tabelle")
    @GET
    
   public String toResultSetTabelle(@QueryParam("budget")String x,@QueryParam("maxbudget")String y,@QueryParam("location") String location, @QueryParam("date") String minDateString) throws ParserConfigurationException, TransformerException, ParseException { 
      ArrayList<String[]> result1 = new ArrayList<String[]>();
      
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
        } catch (ClassNotFoundException e) { 
            System.out.println("Fehler bei MySQL-JDBC-Bridge" + e); 
           
        } 
        
    //SQL Query wird hier erzeugt je nach dem Daten, die angegeben werden
        
        try {        
            
            int minBudget = 0;
            int maxBudget = 0;
            try {
                
                minBudget = Integer.valueOf(x);
            } catch(NumberFormatException e) {
                if(x.length()>0){return "Ihre Budget soll aus Zahlen bestehen";}
               }
             try {
                
                maxBudget = Integer.valueOf(y);
            } catch(NumberFormatException e) {
                if(y.length()>0){return "Ihre Budget soll aus Zahlen bestehen";}
               }
            try{
                test = Integer.valueOf(location);
                if(test>=0){return "Location soll aus String bestehen";}
            }
            catch(Exception e){}
            
            try{
                if(minDateString.substring(2,3).contains("-") && minDateString.length()>0){
                    return "Date Format soll yyyy-MM-dd";
                }
                
                else{
                   java.util.Date date1 = sdf.parse(minDateString);
                   minDate = new java.sql.Date(date1.getTime());
                }
              
                 
                 
               }
            catch(Exception e){
              if(minDateString.length()>0) return "Date Format soll yyyy-MM-dd";}
          
           
            //Connection mit dem SQL wird erzeugt 
            
            String url = "jdbc:mysql://" + host + "/" + dbName; 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh"); 
            statement = connection.createStatement(); 
            
            String sqlQuery = "Select * FROM "+dbTable;
           
            List<String> whereClause = new ArrayList<>();
            
            if(minBudget>0){
                whereClause.add("Budget >= "+minBudget);
            }
             if(maxBudget>0){
                whereClause.add("Budget <= "+maxBudget);
            }
             if(minBudget>maxBudget && maxBudget>0 && minBudget>0){
                 return "Minimal Budget soll kleiner als Maximal Budget";
             }
                
            if(minDate!=null){
                whereClause.add("Date >= '"+minDate+"'");
            }
             if(location!=null && !location.isEmpty()){
                whereClause.add("Location = '"+location+"'");
            }
            
             //Die Daten werden nach dem Budget absteigend sortiert 
             
             if(whereClause.size()>0){
                 sqlQuery+=" WHERE "+StringUtils.join(whereClause," AND ")+" ORDER BY Budget DESC";
             }
            
             
              if(whereClause.size()==0){
                 sqlQuery+=" ORDER BY Budget DESC";
              }
            resultSet = statement.executeQuery(sqlQuery);
            
            int spalten = resultSet.getMetaData().getColumnCount(); 
            System.out.println("Anzahl Spalten: " + spalten); 

            DocumentBuilderFactory factory = 
            DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =factory.newDocumentBuilder();
            doc = builder.newDocument();
            Element results = doc.createElement("Results");
            doc.appendChild(results);
      
     
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh"); 
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int colCount = rsmd.getColumnCount();
            
            while (resultSet.next()) { 
                String[] str = new String[6]; 
                for (int k = 1; k <=spalten; k++) { 
                    str[k - 1] = resultSet.getString(k); 
                    if(result1.contains(str)==false){
                     result1.add(str); }
                    
                } 
                } 
           
           //HTML Datei wird mit Hilfe von String Builder erzeugt 
           sb.append("<html><body>");
            for(Iterator iter = result1.iterator(); iter.hasNext();){
                String[] str =(String[]) iter.next();
                 sb.append("<br>");
                 sb.append("<br>");
                for(int i=0;i<str.length;i++){
                   
                 sb.append(System.lineSeparator());
                   
                
                    if(i==0){
                       
                        sb.append("Link :  <a href="+str[i]+">"+str[i]+"</a><br>");}
                
                  if(i==1){
                      sb.append("Budget : "+str[i]+"<br>");}
                    
                 if(i==2){
                        sb.append("Location : "+str[i]+"<br>");}
                    
                  if(i==3){
                        sb.append("Qm : "+str[i]+"<br>");}
                  if(i==4){
                        sb.append("Room : "+str[i]+"<br>");}
                    
                    if(i==5){
                       sb.append("Date : "+str[i]+"<br>");
}
                    }
             
            
            
            }
                sb.append("</body>");
                sb.append("</html>");
                outPut=sb.toString();
           
    
        } catch (SQLException e) { 
            System.out.println("Fehler bei Tabellenabfrage: " + e); 
            
        } 
  return   outPut=sb.toString(); }
 
     
     
   
    //Hier wird restAPI input programmiert. der liefert am Ende sichtbare Informationen von jedem PersonDaten in Web Browser
    //als Json 
     
    @Path("/input")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
  public String toResultSet(@QueryParam("budget")String x,@QueryParam("maxbudget")String y,@QueryParam("location") String location, @QueryParam("date") String minDateString) throws ParserConfigurationException, TransformerException, ParseException { 
       try { 
            Class.forName("com.mysql.jdbc.Driver"); 
        } catch (ClassNotFoundException e) { 
            System.out.println("Fehler bei MySQL-JDBC-Bridge" + e); 
           
        } 
       
        //SQL Query wird hier erzeugt je nach dem Daten, die angegeben werden
       
        try {        
            
            int minBudget = 0;
            int maxBudget = 0;
            try {
                
                minBudget = Integer.valueOf(x);
            } catch(NumberFormatException e) {
                if(x.length()>0){return "Ihre Budget soll aus Zahlen bestehen";}
               }
             try {
                
                maxBudget = Integer.valueOf(y);
            } catch(NumberFormatException e) {
                if(y.length()>0){return "Ihre Budget soll aus Zahlen bestehen";}
               }
            try{
                test = Integer.valueOf(location);
                if(test>=0){return "Location soll aus String bestehen";}
            }
            catch(Exception e){}
            
            try{
                if(minDateString.substring(2,3).contains("-") && minDateString.length()>0){
                    return "Date Format soll yyyy-MM-dd";
                }
                else{
                    java.util.Date date1 = sdf.parse(minDateString);
                    minDate = new java.sql.Date(date1.getTime());
                }
               
                
                 
               }
            catch(Exception e){
              if(minDateString.length()>0) return "Date Format soll yyyy-MM-dd";}
          
            //Connection mit dem SQL wird erzeugt 
            
            String url = "jdbc:mysql://" + host + "/" + dbName; 
              connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh"); 
            statement = connection.createStatement(); 
            
            String sqlQuery = "Select * FROM "+dbTable;
           
            List<String> whereClause = new ArrayList<>();
            
            if(minBudget>0){
                whereClause.add("Budget >= "+minBudget);
            }
             if(maxBudget>0){
                whereClause.add("Budget <= "+maxBudget);
            }
             if(minBudget>maxBudget && maxBudget>0 && minBudget>0){
                 return "Minimal Budget soll kleiner als Maximal Budget";
             }
                
            if(minDate!=null){
                whereClause.add("Date >= '"+minDate+"'");
            }
             if(location!=null && !location.isEmpty()){
                whereClause.add("Location = '"+location+"'");
            }
            
             
             //Die Daten werden nach dem Budget absteigend sortiert 
             
              if(whereClause.size()>0){
                 sqlQuery+=" WHERE "+StringUtils.join(whereClause," AND ")+" ORDER BY Budget DESC";
             }
             
              if(whereClause.size()==0){
                 sqlQuery+=" ORDER BY Budget DESC";
              }
             
            resultSet = statement.executeQuery(sqlQuery);
            
            int spalten = resultSet.getMetaData().getColumnCount(); 
            System.out.println("Anzahl Spalten: " + spalten); 

            DocumentBuilderFactory factory = 
            DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element results = doc.createElement("Results");
            doc.appendChild(results);

     
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/location?autoReconnect=true&useSSL=false","root","dreamhigh"); 
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int colCount = rsmd.getColumnCount();
            
            
            
            
            
            while (resultSet.next()) { 
                String[] str = new String[8]; 
                for (int k = 1; k <=spalten; k++) { 
                    str[k - 1] = resultSet.getString(k); 
                     result.add(resultSet.getString(k)); 
                } 
            

            //Result wird hier als XML zuerst erzeugt und dann Json
            Element row = doc.createElement("Row");
            results.appendChild(row); 
            for (int ii = 1; ii <= colCount; ii++) { 
                String columnName = rsmd.getColumnName(ii);
                Object value = resultSet.getObject(ii);
                Element node = doc.createElement(columnName);
           
           if(value!=null){
             
           node.appendChild(doc.createTextNode(value.toString())); 
           row.appendChild(node); 
           }
        }
            } 

          System.out.println(getDocumentAsXml(doc));   
           
            
          try {
            xmlJSONObj = XML.toJSONObject(getDocumentAsXml(doc));
              
            outPut = xmlJSONObj.toString(4);
        
            System.out.println(outPut);
       
            return   outPut;
        } catch (JSONException je) {
            
        }

        } catch (SQLException e) { 
            System.out.println("Fehler bei Tabellenabfrage: " + e); 
            
        } 
  return   outPut; }
  
  

  
  public static String getDocumentAsXml(Document doc)
      throws TransformerConfigurationException, TransformerException {
    DOMSource domSource = new DOMSource(doc);
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
    // we want to pretty format the XML output
    // note : this is broken in jdk1.5 beta!
    transformer.setOutputProperty
       ("{http://xml.apache.org/xslt}indent-amount", "4");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    //
    java.io.StringWriter sw = new java.io.StringWriter();
    StreamResult sr = new StreamResult(sw);
    transformer.transform(domSource, sr);
    return sw.toString();
 }

    private boolean toString(JSONException je) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


class AccessCon {
    public Connection getConnection() throws Exception {
    Driver d = (Driver)Class.forName
    ("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
    Connection c = DriverManager.getConnection
    ("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=c:/tech97.mdb");
    return c;
  
  }
  
}
  
      
  
    public static void main(String args[]) throws SQLException, ParserConfigurationException, TransformerException, TransformerException { 
       
    } 
} 
  


