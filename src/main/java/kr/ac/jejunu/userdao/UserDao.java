package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);

            return preparedStatement;
        };
        User user = jdbcContext.jdbcWithStatementForGet(statementStrategy);
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };
        Long id = jdbcContext.jdbcWithStatementForAdd(statementStrategy);
        return id;
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] objects = new Object[] {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, objects);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from userinfo where id = ?";
        Object[] objects = new Object[] {id};
        jdbcContext.update(sql, objects);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
