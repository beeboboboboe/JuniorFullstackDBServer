import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

import java.net.InetSocketAddress;
import java.util.Map;

//For compiling on the shell on repl: Same on mac
//javac -cp sqlite-jdbc-3.23.1.jar: Main.java
//java -cp sqlite-jdbc-3.23.1.jar: Main

//Use for windows
//javac -cp sqlite-jdbc-3.23.1.jar; Main.java
public class Main {

    public static void main(String[] args) throws IOException {

      // create a port - our Gateway
      int port = 8400;
      
      //create the HTTPserver object
      HttpServer server = HttpServer.create(new InetSocketAddress(port),0);
      
      Database db = new Database("jdbc:sqlite:food.db");
      
      //create a route handler to repond the a request
      // this is our default route
      server.createContext("/", new RouteHandler("Default route handler. No route specified.....") );

     
      /*
        /customers
        route gets all customers table
      */
      String sql1 = "select * from Dishes";
      server.createContext("/dishes", new RouteHandler(db,sql1) );
     
      /*
        /playslist/songs
        route gets all customers table
      */
      // String sql2 = "Select playlists.PlayListName, tracks.TrackName,tracks.Composer from playlists inner join playlist_track, tracks on playlists.PlaylistId =playlist_track.PlaylistId and playlist_track.TrackId = tracks.TrackId;";
      String sql2 = "Select * From Ingredients;";
      server.createContext("/ingredients", new RouteHandler(db,sql2) );
   
      
      String sql3 = "Select * From Recipies";
        server.createContext("/recipies", new RouteHandler(db,sql3) );
      
      //Start the server
      server.start();

      System.out.println("Server is listening on port "+port);
      

      

      
    }    
}


