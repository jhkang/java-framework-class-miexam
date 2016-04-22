package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        User user = jdbcWithStatementForGet(connection, statementStrategy);
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        StatementStrategy statementStrategy = new AddStatementStrategy(user);
        Long id = jdbcWithStatementForAdd(connection, statementStrategy);
        return id;
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcWithStatementForUpdate(connection, statementStrategy);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.getConnection();
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcWithStatementForUpdate(connection, statementStrategy);
    }

    private User jdbcWithStatementForGet(Connection connection, StatementStrategy statementStrategy) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    private Long jdbcWithStatementForAdd(Connection connection, StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        PreparedStatement preparedStatement = null;
        Long id = null;
        try {
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

            id = getLastInsertId(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    private void jdbcWithStatementForUpdate(Connection connection, StatementStrategy statementStrategy) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Long getLastInsertId(Connection connection) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getLong(1);
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

}
