package db;

import model.EventDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.EventLogsService;

import java.sql.*;
import java.util.Iterator;
import java.util.List;


public class HSQLDBConnector {

    private String URL = "jdbc:hsqldb:hsql://localhost/Test";
    private String USER_ID = "SA";
    private String PASS = "";

    private final Logger logger = LoggerFactory.getLogger(EventLogsService.class);

    public HSQLDBConnector() {
        createTable();
    }

    public void insertIntoDatabase(List<EventDetail> EventDetails) {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection con = DriverManager.getConnection(URL, USER_ID, PASS);

            PreparedStatement prepStmt = con.prepareStatement(
                    "insert into EventDetail(id,duration,alert,type,host) values (?,?,?,?,?);");

            Iterator<EventDetail> it = EventDetails.iterator();
            while (it.hasNext()) {
                EventDetail eventDetail = it.next();
                prepStmt.setInt(2, eventDetail.getDuration());
                prepStmt.setBoolean(3, eventDetail.isAlert());
                prepStmt.setString(1, eventDetail.getId());
                prepStmt.setString(4, eventDetail.getType());
                prepStmt.setString(5, eventDetail.getHost());
                prepStmt.addBatch();
            }
            con.commit();
        } catch (Exception e) {
            logger.error("Error On Insertion Of Data to Db", e);
        }
    }

    public void createTable() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection con = DriverManager.getConnection(URL, USER_ID, PASS);
            Statement stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE EventDetail " +
                    "( id VARCHAR(50) NOT NULL, duration INT NOT NULL, alert BOOLEAN NOT NULL, " +
                    "  type VARCHAR(50) NOT NULL, host VARCHAR(50) NOT NULL, PRIMARY KEY (id)); ");
            con.commit();
        } catch (Exception e) {
            logger.error("Error On Creation Of Db Tables", e);
        }
    }

}