package by.epam.cafe.service;

import by.epam.cafe.dao.pool.ConnectionPool;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static final String RESOURCE = "/property/database_reset.properties";

    private static Properties properties = new Properties();


    static {
        init();
    }

    public void reset() {

        try {
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection cn = DriverManager.getConnection(properties.getProperty("url"), properties)) {
                ScriptRunner sr = new ScriptRunner(cn);
                sr.setLogWriter(null);
                runFile(sr, "sql/1_drop_database.sql");
                runFile(sr, "sql/2_create_database.sql");
                runFile(sr, "sql/3.0_create_tables.sql");
                sr.setSendFullScript(true);
                runFile(sr, "sql/3.1_create_trigger_on_create.sql");
                runFile(sr, "sql/3.2_create_trigger_on_update.sql");
                cn.commit();
                sr.setSendFullScript(false);
                runFile(sr, "sql/4_init_tables.sql");
                runFile(sr, "sql/5_fill_tables.sql");


            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void runFile(ScriptRunner sr, String fileName) throws IOException {
        System.out.println("file with name =" + fileName + " successfully executed.");
        InputStream resourceAsStream = DatabaseManager.class
                .getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        sr.runScript(reader);
        reader.close();
    }


    private static void init() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            InputStream is = ConnectionPool.class.getResourceAsStream(RESOURCE);
            properties.load(is);
        } catch (IOException | SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

}
