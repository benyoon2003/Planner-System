import java.util.List;

public class Event {
  private String name;
  private String location;
  private boolean online;
  private String startDay;
  private int startTime;
  private String endDay;
  private int endTime;
  private List<String> invitedUsers;

  Event(String name, String location, boolean online,
        String startDay, int startTime, String endDay,
        int endTime, List<String> invitedUsers) {
    this.name = name;
    this.location = location;
    this.online = online;
    this.startDay = startDay;
    this.startTime = startTime;
    this.endDay = endDay;
    this.endTime = endTime;
    this.invitedUsers = invitedUsers;
  }
}
