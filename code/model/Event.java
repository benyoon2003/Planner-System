package model;

import java.util.List;
import java.util.Objects;

import model.Day;

public class Event {
  private String name;
  private String location;
  private boolean online;
  private Day startDay;
  private int startTime;
  private Day endDay;
  private int endTime;
  private List<User> invitedUsers;

  public Event(String name, String location, boolean online,
        Day startDay, int startTime, Day endDay,
        int endTime, List<User> invitedUsers) {
    if (startDay.equals(endDay) && startTime == endTime){
      throw new IllegalArgumentException("Invalid Times for an model.Event");
    }
    this.name = Objects.requireNonNull(name);
    this.location = Objects.requireNonNull(location);
    this.online = online;
    this.startDay = Objects.requireNonNull(startDay);
    if (startTime < 0){
      this.startTime = startTime;
    } else {
      throw new IllegalArgumentException("Start Time cannot be negative");
    }
    this.endDay = Objects.requireNonNull(endDay);
    if (endTime < 0){
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("Start Time cannot be negative");
    }
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

  public Day getStartDay() {
    return this.startDay;
  }

  public int getStartTime() {
    return this.startTime;
  }

  public Day getEndDay() {
    return this.endDay;
  }

  public int getEndTime() {
    return this.endTime;
  }

  public List<User> getInvitedUsers() {
    return this.invitedUsers;
  }
}
