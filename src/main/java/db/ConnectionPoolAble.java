package db;

import java.sql.Connection;

public interface ConnectionPoolAble {

    Connection getConnection();
}
