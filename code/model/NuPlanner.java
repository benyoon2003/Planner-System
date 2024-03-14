package model;

import java.util.ArrayList;
import java.util.List;

import model.PlannerModel;

public class NuPlanner implements PlannerModel {
  private List<User> database;

  public NuPlanner() {
    this.database = new ArrayList<>();
  }

  public NuPlanner(List<User> database) {
    this.database = database;
  }

  @Override
  public void uploadSchedule(String path) {
    User user = Utils.readXML(path, this.database);
    this.addUser(user);
  }

  @Override
  public void saveSchedule() {
    for (User user : this.database) {
      Utils.writeToFile(user);
    }
  }

  List<User> mapUserList(List<String> users){
    List<User> userList = new ArrayList<>();
    for (String user : users){
      userList.add(Utils.findUser(user, this.database));
    }
    return userList;
  }

  @Override
  public List<Event> selectSchedule(String user) {
    return Utils.findUser(user, this.database).schedule;
  }

  @Override
  public Event createEvent(String user, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<String> invitedUsers) {
    User u = Utils.findUser(user, this.database);
    if(this.database.contains(u)){
      List<String> invitedUserCopy = new ArrayList<>(invitedUsers);
      invitedUserCopy.addFirst(user);
      Event newEvent = new Event(name, location, online, startDay, startTime, endDay
      , endTime, mapUserList(invitedUserCopy));
      newEvent.sendInvite();
      return newEvent;
    } else {
      throw new IllegalArgumentException("User is not in system");
    }
  }


  @Override
  public void removeEvent(String user, Event e) {
    User u = Utils.findUser(user, this.database);
      if (this.database.contains(u)){
        if (e.getHost().equals(u)){
          e.removeAll();
        }else {
          u.schedule.remove(e);
        }
      }
  }


  @Override
  public void modifyEvent(Event e, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<String> invitedUsers, String host) {
    e.setName(name);
    e.setLocation(location);
    e.setOnline(online);
    e.setStartDay(startDay);
    e.setStartTime(startTime);
    e.setEndDay(endDay);
    e.setEndTime(endTime);
    e.setInvitedUsers(mapUserList(invitedUsers));
    e.setHost(Utils.findUser(host, this.database));
  }


  @Override
  public Event eventsAtThisTime(String user, int time) {
    User selected = Utils.findUser(user, this.database);
    Event specifcEvent = null;
    for (Event e : selected.schedule){
      if (e.getStartTime() == time){
        e = specifcEvent;
      }
    }
    if (specifcEvent == null){
      throw new IllegalArgumentException("No event at this time");
    }else {
      return specifcEvent;
    }
  }

  /**
   * Adds a default user to the database only if the given username does not exist.
   * @param Name the uid of the user
   * @return the created User
   */
  @Override
  public User addUser(String Name) {
    try{
      Utils.findUser(Name, this.database);
      throw new IllegalArgumentException("Given Name already exists");
    } catch (IllegalArgumentException e){
      User newUser = new User(Name, List.of());
      this.database.add(newUser);
      return newUser;
    }
  }

  /**
   * Adds a User to the database of users and checks if the user already exists in the database.
   * If it does, the given user's schedule is compared to the existing user's schedule and
   * the events of the schedule are only added if none of them conflict with the pre-existing
   * schedule. If the user does not already exist in the database, it is simply added to
   * the database.
   * @param user a User
   * @return the newly created User or the modified pre-existing User
   */
  public void addUser(User user) {
    try{
      User userInDatabase = Utils.findUser(user.uid, this.database);
      List<Event> copyOfUserInDatabaseSchedule = userInDatabase.schedule;

      // Add event from new schedule if it doesn't conflict with pre-existing user's schedule
      for (Event e : user.schedule) {
        try {
          userInDatabase.addEvent(e);
        }
        catch (IllegalArgumentException ignored) {
          // Reset schedule if any of the events from the given user
          // conflict with the pre-existing user
          userInDatabase.schedule = copyOfUserInDatabaseSchedule;
          throw new IllegalArgumentException("The given user conflicts with the pre-existing user's" +
                  "schedule.");
        }
      }
    } catch (IllegalArgumentException e){
      this.database.add(user);
    }
  }

  @Override
  public List<Event> scheduleOnDay(String user, Day day) {
    User selected = Utils.findUser(user, this.database);
    return selected.eventsOnDay(day);
  }

  public List<User> getListOfUser() {
    return this.database;
  }
}
