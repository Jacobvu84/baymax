package thich.thong.lac.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.google.common.base.Strings;

public class DBConnect
{
    // JDBC driver name and database URL
    static String JDBC_DRIVER = null;

    // Database credentials
    static String DB_NAME     = null;
    static String USER_NAME   = null;
    static String PASS_WORD   = null;
    static String IP_SERVER   = null;
    static String SERVER_PORT = null;

    private static Connection conn = null;
    private static Statement  stmt = null;
    private static ResultSet  rs   = null;

    public static Properties CONFIG = null;

    public static void connect()
    {
        try
        {
            CONFIG = LoadObject.loading_config_sys("serenity.properties");

            JDBC_DRIVER = System.getProperty("db.jdbc");
            if (Strings.isNullOrEmpty(JDBC_DRIVER))
            {
                JDBC_DRIVER = CONFIG.getProperty("db.jdbc");
                if (Strings.isNullOrEmpty(JDBC_DRIVER))
                    JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";
            }

            IP_SERVER = System.getProperty("db.url");
            if (Strings.isNullOrEmpty(IP_SERVER))
            {
                IP_SERVER = CONFIG.getProperty("db.url");
                if (Strings.isNullOrEmpty(IP_SERVER))
                    IP_SERVER = "172.18.0.21";
            }

            SERVER_PORT = System.getProperty("db.port");
            if (Strings.isNullOrEmpty(SERVER_PORT))
            {
                SERVER_PORT = CONFIG.getProperty("db.port");
                if (Strings.isNullOrEmpty(SERVER_PORT))
                    SERVER_PORT = "50000";
            }

            DB_NAME = System.getProperty("db.name");
            if (Strings.isNullOrEmpty(DB_NAME))
            {
                DB_NAME = CONFIG.getProperty("db.name");
                if (Strings.isNullOrEmpty(DB_NAME))
                    DB_NAME = "AUTO5";
            }

            USER_NAME = System.getProperty("db.usr");
            if (Strings.isNullOrEmpty(USER_NAME))
            {
                USER_NAME = CONFIG.getProperty("db.usr");
                if (Strings.isNullOrEmpty(USER_NAME))
                    USER_NAME = "db2admin";
            }

            PASS_WORD = System.getProperty("db.pwd");
            if (Strings.isNullOrEmpty(PASS_WORD))
            {
                PASS_WORD = CONFIG.getProperty("db.pwd");
                if (Strings.isNullOrEmpty(PASS_WORD))
                    PASS_WORD = "x";
            }

            LogWork.log_debug(">> debug: | Register JDBC driver");
            Class.forName(JDBC_DRIVER);

            String DB_URL = "jdbc:db2://" + IP_SERVER + ":" + SERVER_PORT + "/" + DB_NAME + "";
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void connect(String nameDB,String ipServer)
    {
        try
        {
            CONFIG = LoadObject.loading_config_sys("serenity.properties");

            DB_NAME = nameDB;
            
            IP_SERVER = ipServer;

            JDBC_DRIVER = System.getProperty("db.jdbc");
            if (Strings.isNullOrEmpty(JDBC_DRIVER))
            {
                JDBC_DRIVER = CONFIG.getProperty("db.jdbc");
                if (Strings.isNullOrEmpty(JDBC_DRIVER))
                    JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";
            }

//            IP_SERVER = System.getProperty("db.url");
//            if (Strings.isNullOrEmpty(IP_SERVER))
//            {
//                IP_SERVER = CONFIG.getProperty("db.url");
//                if (Strings.isNullOrEmpty(IP_SERVER))
//                    IP_SERVER = "172.18.0.21";
//            }

            SERVER_PORT = System.getProperty("db.port");
            if (Strings.isNullOrEmpty(SERVER_PORT))
            {
                SERVER_PORT = CONFIG.getProperty("db.port");
                if (Strings.isNullOrEmpty(SERVER_PORT))
                    SERVER_PORT = "50000";
            }

            USER_NAME = System.getProperty("db.usr");
            if (Strings.isNullOrEmpty(USER_NAME))
            {
                USER_NAME = CONFIG.getProperty("db.usr");
                if (Strings.isNullOrEmpty(USER_NAME))
                    USER_NAME = "db2admin";
            }

            PASS_WORD = System.getProperty("db.pwd");
            if (Strings.isNullOrEmpty(PASS_WORD))
            {
                PASS_WORD = CONFIG.getProperty("db.pwd");
                if (Strings.isNullOrEmpty(PASS_WORD))
                    PASS_WORD = "Taskhub1";
            }

            LogWork.log_debug(">> debug: | Register JDBC driver");
            Class.forName(JDBC_DRIVER);

            String DB_URL = "jdbc:db2://" + IP_SERVER + ":" + SERVER_PORT + "/" + DB_NAME + "";
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASS_WORD);

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public DBConnect()
    {
        connect();
    }

    public DBConnect(String connectionString, String username, String password)
    {
        try
        {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            conn = DriverManager.getConnection(connectionString, username, password);
        } catch (ClassNotFoundException e)
        {
            //Assert.fail("Database connect classnot found: " + connectionString, e.getCause());
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // use for update & delete statement
    public static void executeUpdate(String sql)
    {
        try
        {
            LogWork.log_debug(">> debug: | Execute a query update: " + sql);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            // Handle errors for JDBC
            e.printStackTrace();
            LogWork.log_debug(">> debug: | SQLException execute a query update: " + sql);
        } catch (Exception e)
        {
            // Handle errors for Class.forName
            LogWork.log_debug(">> debug: | Handle errors for Class.forName");
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql)
    {
        try
        {
            LogWork.log_debug(">> debug: | Execute a query: " + sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            LogWork.log_debug("Executed: " + sql);
            return rs;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            LogWork.log_debug(">> debug: | Handle errors for Class.forName");
            e.printStackTrace();
        }
        return null;
    }

    public static void close()
    {
        try
        {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e)
        {

        }
    }
   
}
