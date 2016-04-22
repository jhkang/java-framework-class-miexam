package kr.ac.jejunu.userdao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] objects = new Object[] {id};
        User user = null;

        try {
            user = jdbcTemplate.queryForObject(sql, objects, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            });
        } catch (DataAccessException e) {
        }
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo (name, password) values (?, ?)";

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                return preparedStatement;
            }
        }, generatedKeyHolder);
        return (Long) generatedKeyHolder.getKey();
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] objects = new Object[] {user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, objects);
    }

    public void delete(Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from userinfo where id = ?";
        Object[] objects = new Object[] {id};
        jdbcTemplate.update(sql, objects);
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
