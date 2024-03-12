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

  private User host;

  /**
   * Constructor for an event
   * @param name of the event and cannot be null
   * @param location of the event and cannot be null
   * @param online boolean for whether the event is online or not
   * @param startDay day of the week of the event
   * @param startTime of the event
   * @param endDay of the event
   * @param endTime of the event
   * @param invitedUsers are the users that are a part of the event
   */
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
    if (startTime > 0 && startTime < 2400){
      this.startTime = startTime;
    } else {
      throw new IllegalArgumentException("Invalid Start Time");
    }
    this.endDay = Objects.requireNonNull(endDay);
    if (endTime > 0 && endTime < 2400){
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("Invalid End Time");
    }
    this.invitedUsers = Objects.requireNonNull(invitedUsers);
    if (!this.invitedUsers.isEmpty()){
      this.host = this.invitedUsers.get(0);
    }
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

  public void setInvitedUsers(List<User> invited){
    this.invitedUsers = invited;
    this.host = this.invitedUsers.get(0);
  }

  public User getHost(){
    return this.host;
  }
}