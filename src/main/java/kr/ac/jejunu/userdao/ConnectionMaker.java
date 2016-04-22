package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jhkang on 4/22/16.
 */
public interface ConnectionMaker {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
