package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Day;

/**
 * This is the event class which represents an event in the system.
 */
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
  Event(String name, String location, boolean online,
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

  /**
   * Getter for the name.
   * @return the name of the event
   */
  String getName() {
    return this.name;
  }

  /**
   * Getter for the location.
   * @return the location of the event.
   */
  String getLocation() {
    return this.location;
  }

  /**
   * Getter for the feild online
   * @return whether the event is online
   */
  boolean getOnline() {
    return this.online;
  }

  /**
   * Getter for the start day.
   * @return the starting day of the event
   */
  Day getStartDay() {
    return this.startDay;
  }

  /**
   * Getter for the start time.
   * @return the start time of the event
   */
  int getStartTime() {
    return this.startTime;
  }

  /**
   * Getter for the end day.
   * @return the end day of the event.
   */
  Day getEndDay() {
    return this.endDay;
  }

  /**
   * Getter for end time.
   * @return the end time of the event.
   */
  int getEndTime() {
    return this.endTime;
  }

  /**
   * Getter for the invited users.
   * @return the invited users of the event
   */
  List<User> getInvitedUsers() {
    return this.invitedUsers;
  }

  void setInvitedUsers(List<User> attendees){
    this.invitedUsers = attendees;
  }

  /**
   * Sets this event in each user's schedule
   */
  void sendInvite(){
      for (User attendee : this.invitedUsers){
        if (attendee.equals(this.host)){
          attendee.addEvent(this);
        }else {
          try {
            attendee.addEvent(this);
          } catch(IllegalArgumentException ignored) {};
        }
      }
  }

  void removeAll(){
    for (User attendee : this.invitedUsers){
      attendee.schedule.remove(this);
    }
  }

  /**
   * Getter for host of the event.
   * @return the host of the event.
   */
 User getHost(){
    return this.host;
  }

  /**
   * Setter for the name of the event
   */
  void setName(String name){
   this.name = name;
  }

  /**
   * Setter for the location of the event
   */
  void setLocation(String location){
    this.location = location;
  }

  /**
   * Setter for online field
   */
  void setOnline(boolean online){
    this.online = online;
  }

  /**
   * Setter for the startDay of the event
   */
  void setStartDay(Day startDay){
    this.startDay = startDay;
  }

  /**
   * Setter for the start time of the event
   */
  void setStartTime(int time){
    this.startTime = time;
  }

  /**
   * Setter for the end day of the event
   */
  void setEndDay(Day endDay){
    this.endDay = endDay;
  }

  /**
   * Setter for the end time of the event
   */
  void setEndTime(int time){
    this.endTime = time;
  }

  /**
   * Setter for the host of the event
   */
  void setHost(User newHost){
    this.host = newHost;
  }

  private String convertListOfInvitees(){
    String invitees = "";
    for (User u : this.invitedUsers){
      invitees.concat(u.uid + "\n");
    }
    return invitees;
  }

  /**
   * Creates a string representation of an Event
   */
  @Override
  public String toString(){
    String output = "";
    output += "       name: " + this.name + "\n";
    output += "       time: " + this.startDay.toString() + String.format(": %d -> ", this.startTime);
    output += this.endDay.toString() + String.format(": %d\n", this.endTime);
    output += "       " + this.location + "\n";
    output += "       online: " + this.online + "\n";
    output += "       " + convertListOfInvitees();
    return output;
  }
}