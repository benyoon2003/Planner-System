package model;

import java.util.List;
import java.util.Objects;

import model.Day;

/**
 * This is the event class which represents an event in the system.
 */
 class Event {
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
    for (User attendee : this.invitedUsers){
      attendee.schedule.add(this);
    }
  }

  /**
   * Getter for the name.
   * @return the name of the event
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter for the location.
   * @return the location of the event.
   */
  public String getLocation() {
    return this.location;
  }

  /**
   * Getter for the feild online
   * @return whether the event is online
   */
  public boolean getOnline() {
    return this.online;
  }

  /**
   * Getter for the start day.
   * @return the starting day of the event
   */
  public Day getStartDay() {
    return this.startDay;
  }

  /**
   * Getter for the start time.
   * @return the start time of the event
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Getter for the end day.
   * @return the end day of the event.
   */
  public Day getEndDay() {
    return this.endDay;
  }

  /**
   * Getter for end time.
   * @return the end time of the event.
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Getter for the invited users.
   * @return the invited users of the event
   */
  public List<User> getInvitedUsers() {
    return this.invitedUsers;
  }

  /**
   * Sets the invited users of the event.
   * @param invited the list of inveted users.
   */
  public void setInvitedUsers(List<User> invited){
    if (this.invitedUsers.isEmpty()){
      this.invitedUsers = invited;
      this.host = this.invitedUsers.get(0);
      for (User attendee : this.invitedUsers){
        attendee.schedule.add(this);
      }
    }
  }

  /**
   * Getter for host of the event.
   * @return the host of the event.
   */
  public User getHost(){
    return this.host;
  }
}