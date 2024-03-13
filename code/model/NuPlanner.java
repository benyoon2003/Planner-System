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
    this.database.add(user);
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
      Event newEvent = new Event(name, location, online, startDay, startTime, endDay
      , endTime, mapUserList(invitedUsers));
      if (!newEvent.getHost().equals(u)){
        throw new IllegalArgumentException("Host is not first on invited list");
      }
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

  @Override
  public List<Event> scheduleOnDay(String user, Day day) {
    User selected = Utils.findUser(user, this.database);
    return selected.eventsOnDay(day);
  }


}
