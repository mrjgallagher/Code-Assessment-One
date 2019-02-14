package model;

public class EventDetail {

    private String id;

    private int duration;

    private String type;

    private String host;

    private boolean alert;

    public EventDetail(String id, int duration, String type, String host, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }

    public String getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public boolean isAlert() {
        return alert;
    }

    @Override
    public String toString() {
        return "model.EventDetail{" +
                "id='" + id + '\'' +
                ", duration=" + duration +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", alert=" + alert +
                '}';
    }
}
