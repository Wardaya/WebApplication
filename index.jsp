<%-- 
    Document   : index
    Created on : 17 Des 16, 10:38:03
    Author     : ASUS PRO
--%>


<%@page import="com.ToResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search for Immobolien</title>
    </head>
    <body>
        <h1>Search for Immobilien</h1>
         <form action="rest/service/input" method="GET">
            <table border="0">
                <tbody>
                    <tr>
                        <td>mind. Budget :</td>
                        <td> <input type="text" name="budget" value="" size="50" /> </td>
                    </tr>
                     <tr>
                        <td>max. Budget :</td>
                        <td> <input type="text" name="maxbudget" value="" size="50" /> </td>
                    </tr>
                     <tr>
                        <td>Location :</td>
                        <td> <input type="text" name="location" value="" size="50" /> </td>
                    </tr>
                     <tr>
                        <td>min. Date :</td>
                        <td> <input type="text" name="date" value="" size="50" /> </td>
                    </tr>
                        
                    
                </tbody>
                
                
            </table>
            <input type="submit" Value="Submit" name="submit" />
            
        </form>
    </body>
</html>
