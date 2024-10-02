package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dbconfig {
    private Connection con;
    private String url;
    private String bddApp;
    private String user;
    private String pwd;

    public Dbconfig() {
        this.url = "jdbc:mysql://localhost/";
        this.user = "root";
        this.pwd = "";
        this.bddApp = "tp_mycrm";
    }

    public Connection getCon() {
        return con;
    }

    public void getConnexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url+bddApp, user, pwd);
            this.con = conn;
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> selectRequest(String sql) throws SQLException{
        getConnexion();

        List<Map<String, Object>> results = new ArrayList<>();

        try{
            PreparedStatement pstmt = this.con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            int nbColumn = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= nbColumn; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    row.put(columnName, columnValue);
                }
                results.add(row);
            }
        }finally {
            this.con.close();
        }
        return results;
    }

    public void executeCudRequest(String sql, ArrayList<Data> datas) throws SQLException {
        getConnexion();
        PreparedStatement pstmt = this.con.prepareStatement(sql);
        for (Data data : datas) {
            if(data.type.equals("int")) {
                pstmt.setInt(data.position, Integer.parseInt(data.valeur));
            }
            else {
                pstmt.setString(data.position, data.valeur);
            }
        }
        pstmt.execute();
        pstmt.close();
        this.con.close();
    }
}
