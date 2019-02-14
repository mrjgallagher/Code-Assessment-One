package service;

import model.EventDetail;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventLogsService {

    private final Logger logger = LoggerFactory.getLogger(EventLogsService.class);

    private Map<String, JSONObject> eventMap;

    private List<EventDetail> eventDetails;

    public EventLogsService() {
        this.eventMap = new HashMap<>();
        this.eventDetails = new ArrayList<>();
    }

    public List<EventDetail> getEventDetails() {
        return eventDetails;
    }

    public void parseLogFile(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(x -> buildEventMap(x));
            eventDetails.forEach(x -> logger.info(x.toString()));
        } catch (IOException e) {
            logger.error("Input Error Reading Log File");
        }
    }


    private void buildEventMap(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            String id = jsonObj.get("id").toString();
            if (isFirstEvent(id)) {
                storeFirstEventLog(id, jsonObj);
            } else {
                processSecondEventLog(id, jsonObj);
            }
        } catch (JSONException e) {
            logger.error("Error Unpacking Json Object");
        }
    }

    private void processSecondEventLog(String id, JSONObject event2) throws JSONException {
        JSONObject event1 = eventMap.get(id);
        int diff = Math.abs(event1.getInt("timestamp") - event2.getInt("timestamp"));
        boolean alert = (diff > 4);
        String type = findOptionalParam(event1, event2, "type");
        String host = findOptionalParam(event1, event2, "host");
        EventDetail eventDetail = new EventDetail(id, diff, type, host, alert);
        eventDetails.add(eventDetail);
    }

    private void storeFirstEventLog(String id, JSONObject jsonObj) {
        eventMap.put(id, jsonObj);
    }

    private boolean isFirstEvent(String id) {
        return !eventMap.containsKey(id);
    }

    private String findOptionalParam(JSONObject event1, JSONObject event2, String param) throws JSONException {
        if (event1.has(param)) {
            return event1.getString(param);
        } else if (event2.has(param)) {
            return event2.getString(param);
        } else {
            return "";
        }
    }

}
