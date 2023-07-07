/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exe.springdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 * @author root
 */
public class Util {

    public static SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static String sconnection = "jdbc:ignite:thin://127.0.0.1:10800";

    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(sconnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String executeSQL(String sql, String smessage) {
        Connection conn = Util.getConnection();
        SimpleDateFormat sdf = Util.simpledateformat;
        if (conn == null) {
            return "Cannot connect to database";
        } else {
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                int ires = stmt.executeUpdate();
                Util.closeConnection(conn);
                return smessage + (ires == 1 ? "success" : "fail");
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

}
