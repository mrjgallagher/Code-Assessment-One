import db.HSQLDBConnector;
import model.EventDetail;
import service.EventLogsService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EventLogsService eventLogsService = new EventLogsService();
        eventLogsService.parseLogFile(args[0]);
        List<EventDetail> eventDetails = eventLogsService.getEventDetails();

        HSQLDBConnector hsqldbConnector = new HSQLDBConnector();
        hsqldbConnector.insertIntoDatabase(eventDetails);
    }
}