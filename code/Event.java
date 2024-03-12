import java.util.List;

public class Event {
  private String name;
  private String location;
  private boolean online;
  private String startDay;
  private int startTime;
  private String endDay;
  private int endTime;
  private List<User> invitedUsers;

  public Event(String name, String location, boolean online,
        String startDay, int startTime, String endDay,
        int endTime, List<User> invitedUsers) {
    this.name = name;
    this.location = location;
    this.online = online;
    this.startDay = startDay;
    this.startTime = startTime;
    this.endDay = endDay;
    this.endTime = endTime;
    this.invitedUsers = invitedUsers;
  }

  public String getName() {
    return this.name;
  }

  public String getLocation() {
    return this.location;
  }

  public boolean getOnline() {
    return this.online;
  }

  public String getStartDay() {
    return this.startDay;
  }

  public int getStartTime() {
    return this.startTime;
  }

  public String getEndDay() {
    return this.endDay;
  }

  public int getEndTime() {
    return this.endTime;
  }

  public List<User> getInvitedUsers() {
    return this.invitedUsers;
  }
}
