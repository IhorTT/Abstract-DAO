package net.ukr.tigor;

import java.sql.Connection;

/**
 * Created by Bios on 29.11.2017.
 */
public class ClientDAOEx extends AbstractDAO<Integer, Client> {
    public ClientDAOEx(Connection conn, String table) {
        super(conn, table);
    }
}
