package org.example.dao;


public class DaoFactory {
    public UserDao userDao() {
        AConnectionMaker connectionMaker = new AConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        return userDao;
    }
}
