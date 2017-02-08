
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Group;
import com.restfb.types.Post;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import java.util.List;

import java.util.Scanner;
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
public class Facebook {

    static String accesstoken = "EAACEdEose0cBAJhPODZBsDzZARGVbH08fUxCVEaT55ZAWZBhVfNg3YBVpFCae44Pj9Rqnb38RfyoYWvj6nYaPrpUmBZCC8oOX4VZCVBrIFjnR7q3j46ZCKm5RtBDkRPscTAwmR5YfjkZA0ovC7OyV1nz24gDYo7q6qSaNdH7rm5cniOXJVg98skcysH3cI9MpOcZD";
    // static String access="EAACEdEose0cBAGCPIdqDFQH5liTcsP1ZAaZCEOvAqHvff75VcsIor2aQUj8KZCZAEO0Q7RKEZCQgZClbv8LJZBd85EHBjwumCBaZCI5M0GZBJMpj3tMW4s0VtagtQlb9ZCPZASAnrQ6ljHZBWMZCGwDHkn80PullZCa0oXtF46S27hnBUZC6QZDZD";

    Scanner input = new Scanner(System.in);
    static String Object;
    static String postId;
    static String groupName;
    static String Geld;
    static int Geld1;
    static Set<String> setCar = new HashSet<String>();
    static FacebookClient fbClient = new DefaultFacebookClient(accesstoken);

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException, ParseException {

        ResultSet myResult = null;
        PreparedStatement myResult1 = null;
        java.sql.Connection myConnection;
        String line;
        PrintWriter writer = new PrintWriter("the-file-car.txt", "UTF-8");
        Connection<Group> results = fbClient.fetchConnection("search", Group.class, Parameter.with("q", "Wohnung Kaufen"), Parameter.with("type", "group"));
        int counter = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2016-10-31");
        
        //alle offentliche Facebook Gruppe wird hier nach Name gefiltert und jeder Post von dem Gruppe wird in eine File Test
        //geschrieben
        
        for (List<Group> pageFeed : results) {//liste alle Gruppe, die Wohnung Kaufen in ihre Title enthaelt
            for (Group aGroup : pageFeed) {
               
                counter++;

                Connection<Post> postFeed = fbClient.fetchConnection(aGroup.getId() + "/feed", Post.class);
                for (List<Post> postPage : postFeed) {//Liste von Posting von jedem Gruppe

                    for (Post aPost : postPage) {

                        if (aPost.getUpdatedTime().compareTo(date1) > 0) {

                           
                            {
                                writer.println(">" + aPost.getId() + "<"); // get Posting ID
                                writer.println("~" + aPost.getUpdatedTime() + "<"); //get Posting Time

                                writer.println("%" + aPost.getFrom().getId() + "<"); //get ID User

                                writer.println("#" + aGroup.getName().toLowerCase() + "<");

                                writer.println("*" + aPost.getMessage() + "<"); //get Inhalt Posting
                                writer.println(".^^^^.");
                            }

                            FacebookFilterData B = new FacebookFilterData("the-file-car.txt");

                        }

                    }

                }
            }

        }

    }
}
