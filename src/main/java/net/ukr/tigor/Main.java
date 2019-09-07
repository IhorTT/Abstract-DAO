package net.ukr.tigor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        Connection conn = factory.getConnection();
        try {
            ClientDAOEx dao = new ClientDAOEx(conn, "clients");

            Client c = new Client("test", 1);
            dao.add(c);

            c.setName("Vasia");
            c.setAge(45);
            dao.add(c);


            List<Client> list = dao.getAll(Client.class);
            for (Client cli : list)
                System.out.println(cli);

            System.out.println("-----------------");

            Client cl1 = list.get(0);
            cl1.setAge(34);
            dao.update(cl1);

            list = dao.getAll(Client.class);
            for (Client cli : list) {
                System.out.println(cli);
            }
            dao.delete(list.get(0));

            System.out.println("-----------------");

            List<Object[]> res1 = dao.getAll(Client.class,"name","age");
            for (Object[] temp : res1) {
                System.out.println(Arrays.toString(temp));
            }
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}
