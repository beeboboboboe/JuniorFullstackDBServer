/*
Modification
------------

April 15, 2022 - EP
Add replace_all function to remove single and double quotes and backslash
character that was breaking the JSON interpolations

May 23, 2022 - EP
Modified replace_all function to remove any character with an ascii value 
less the 32.

*/

import java.sql.*;

public class Database{
    private String url;
    private Connection conn;

    public Database(String url){
        this.url = url;
    }
    private boolean connect(){
        boolean success = true;
        try{
            //Create a connection to the database
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection to SQLite has been established.");    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        } 
        return success;
    }
    private boolean close(){
       boolean success = true;
        try{
            //Close the connection to the database
            conn.close();   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success; 
    }
    public String selectData(String sql){
        String result = null;
        connect();
        //System.out.println(sql);
        try (Statement stmt  = conn.createStatement()){
            ResultSet rs    = stmt.executeQuery(sql);
            result = jsonify(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        return result;
    }
    public boolean runQuery(String sql){
        boolean success = true;
        connect();
        try (Statement stmt  = conn.createStatement()){
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        } finally {     
            close();
            
        }return success;
    }
    public static String jsonify(ResultSet rs){
		String result = "";
        try{       
            //Get field names            
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();   

            String field, value, build = "[";
            // loop through the result set
            while (rs.next()) {
                build += "{";
                for (int i = 1; i <= columnCount; i++) {
                    field = metadata.getColumnName(i);
                    value = rs.getString(field);
                    value= replace_all(value);
                  
                    build += "\"" + field + "\":\"" + value + "\",";
                }
                build = build.substring(0,build.length()-1) + "},";
            }
            result = build.substring(0,build.length()-1) + "]";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
	}

  private static String replace_all(String s){
    String build="";
      if(s==null){
        return("");
      }
      for(int i=0; i< s.length(); i++){
        if(s.charAt(i)=='"')
          build+="'";
        else if (s.charAt(i)=='\\')
          build+="-";
        else if (s.charAt(i) < 32)
          build+="";
        else
          build+=s.charAt(i);
      }

    return build;
  }
}