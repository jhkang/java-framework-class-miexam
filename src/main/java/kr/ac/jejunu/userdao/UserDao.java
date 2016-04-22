package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {
        //데이터는어디에?   Mysql
        //Driver Class Load
        Class.forName("com.mysql.jdbc.Driver");
        // Connection    접속정보는? localhost jeju id : jeju pw: jejupw
        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf8", "jeju", "jejupw");
        // 쿼리만들고
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        // 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 결과매핑
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        //데이터는어디에?   Mysql
        //Driver Class Load
        Class.forName("com.mysql.jdbc.Driver");
        // Connection    접속정보는? localhost jeju id : jeju pw: jejupw
        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf8", "jeju", "jejupw");
        // 쿼리만들고
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        // 실행
        preparedStatement.executeUpdate();

        // 결과매핑
        PreparedStatement preparedStatement2 = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        //자원을 해지한다.
        resultSet.close();
        preparedStatement2.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
}
