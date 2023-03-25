package org.example.dao;

import org.example.dao.strategy.AddStatement;
import org.example.dao.strategy.DeleteAllStatement;
import org.example.dao.strategy.StatementStrategy;
import org.example.domain.User;

import java.sql.*;
public class UserDao {
    private ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public UserDao() {
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = connectionMaker.getConnection();
        System.out.println("UserDao add()");

        AddStatement st = new AddStatement(user);
        jdbcContextWithStatementStrategy(st);
    }

    //Delete가 Client 역할을 하고 Context, Strategy를 나눈 코드
    public void deleteAll() throws SQLException, ClassNotFoundException {
            StatementStrategy strategy = new DeleteAllStatement();
            jdbcContextWithStatementStrategy(strategy);
    }

    //Context
    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException , ClassNotFoundException{
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.getConnection();

            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch ( SQLException e) {

                }
            }
            if (c != null) {
                try {c.close();
                } catch (SQLException e) {}
            }
        }
    }


    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.getConnection();
            ps = c.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;

        } catch (SQLException e) {
            throw e;
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }

            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }

            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) {}
            }
        }

    }
    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = connectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id =?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        System.out.println("UserDao get()");

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
