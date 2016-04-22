package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private JdbcContext jdbcContext;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        User user = jdbcContext.jdbcWithStatementForGet(statementStrategy);
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddStatementStrategy(user);
        Long id = jdbcContext.jdbcWithStatementForAdd(statementStrategy);
        return id;
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcWithStatementForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcWithStatementForUpdate(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
