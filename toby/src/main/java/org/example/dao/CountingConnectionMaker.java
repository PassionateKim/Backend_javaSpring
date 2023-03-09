package org.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    int count = 0;
    private ConnectionMaker realConnectMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectMaker) {
        this.realConnectMaker = realConnectMaker;
    }
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        this.count++;

        return realConnectMaker.getConnection();
    }

    public int getCount() {
        return this.count;
    }
}
