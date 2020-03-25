package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *      Getting number of Instagram Subscribers for user
 *      All data will be saved to mySQL database
 *
 *      By Alexander Noyanov
 *      March 25th 2020
 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */


/*

    TODO:
    - Connect MySQL database
    - Save data to the database
    - Get number of subscribers on the page
    - Find number of subscribers on the page

 */


public class Main {

    public static void main(String[] args) {

        String replyText = "";
        String userName = "alex_noyanov";                                           // Name of the user's page

        try{
            PrintWriter out = new PrintWriter("/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/userPage.txt");             // To load page to the text file
            final String USER_AGENT = "Mozilla/5.0";

            // Trying other method:
            String serverPath = "https://www.instagram.com/web/search/topsearch/?query={" + userName + "}";          // Server pathto get JSON4
            // Can't connect directly to the page:
            // String serverPath = "https://www.instagram.com/"+userName+"/";          // Server path
            URL pageUrl = new URL(serverPath);                                         // URL
            HttpURLConnection yc = (HttpURLConnection) pageUrl.openConnection();
            yc.setRequestMethod("GET");                                                // Setting method
            yc.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = yc.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {                           // Sucess
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);                                  // Printing HTML line to the terminal
                    out.println(inputLine);                                         // Saving to the text file

                }
                out.close();
                in.close();

            } else {
                System.out.println("GET request not worked");                       // Printing errors in case of failure
            }
            replyText = "Page successfully downloaded!";
    } catch (Exception e) {
        replyText = "error:" + e.getLocalizedMessage();                             // Catching exceptions
    }

    // Now JSON with all user parameters saved to the text file, getting number of subscribers from this .txt file




    }
}