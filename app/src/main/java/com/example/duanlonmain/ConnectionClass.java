package com.example.duanlonmain;

import android.os.StrictMode;
import java.sql.DriverManager; // Import DriverManager
import java.sql.Connection;    // Import java.sql.Connection

public class ConnectionClass {
    String classes = "net.sourceforge.jtds.jdbc.Driver";


    protected static final String ip = "10.0.3.2";
    protected static final String port = "1433";
    protected static final String db = "DUAN_ANDROID";
    protected static final String user = "sa";
    protected static final String pass = "123456";

    // Phương thức nên trả về java.sql.Connection
    public static  Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection conn = null;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String conurl = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + db;
            conn = DriverManager.getConnection(conurl, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
