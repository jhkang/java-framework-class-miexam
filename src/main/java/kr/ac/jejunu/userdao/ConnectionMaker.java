package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jhkang on 4/22/16.
 */
public class ConnectionMaker {
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
