package org.example.dao;

import org.example.domain.User;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = connectionMaker.getConnection();
        System.out.println("UserDao add()");
        PreparedStatement ps = c.prepareStatement("insert into  users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement ps = c.prepareStatement("delete from users");
        System.out.println("UserDao deleteAll()");
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement ps = c.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
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
