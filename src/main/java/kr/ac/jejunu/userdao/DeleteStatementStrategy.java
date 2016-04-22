package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jhkang on 4/22/16.
 */
public class DeleteStatementStrategy implements StatementStrategy {
    private Long id;

    public DeleteStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}