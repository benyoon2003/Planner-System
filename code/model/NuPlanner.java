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
  public void uploadSchedule() {


  }

  @Override
  public void saveSchedule() {
    for (User user : this.database) {
      Utils.writeToFile(user);
    }
  }

  private User findUser(String uid) {
    for (User u : this.database) {
      if (u.uid.equals(uid)) {
        return u;
      }
    }
    throw new IllegalArgumentException("User not found");
  }

  @Override
  public List<Event> selectSchedule(String user) {
    return findUser(user).schedule;
  }

  @Override
  public void createEvent(String user, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<String> invitedUsers) {
    User u = findUser(user);
    if(this.database.contains(u)){
      Event newEvent = new Event(name, location, online, startDay, startTime, endDay
      , endTime, invitedUsers);
      if (!newEvent.getHost().equals(u)){
        throw new IllegalArgumentException("Host is not first on invited list");
      }
      newEvent.sendInvite();
    } else {
      throw new IllegalArgumentException("User is not in system");
    }
  }


  @Override
  public void removeEvent(String user, Event e) {
    User u = findUser(user);
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
                          int endTime, List<User> invitedUsers, String host) {
    e.setName(name);
    e.setLocation(location);
    e.setOnline(online);
    e.setStartDay(startDay);
    e.setStartTime(startTime);
    e.setEndDay(endDay);
    e.setEndTime(endTime);
    e.setInvitedUsers(invitedUsers);
    e.setHost(findUser(host));
  }


  @Override
  public Event eventsAtThisTime(String user, int time) {
    User selected = findUser(user);
    Event specifcEvent = null;
    for (Event e : selected.schedule){
      if (e.getStartTime() == time){
        e = specifcEvent;
      }
    }
    if (specifcEvent == null){
      throw new IllegalArgumentException("Not valid user or time");
    }else {
      return specifcEvent;
    }
  }

  @Override
  public void addUser(String Name) {
    User newUser = new User(Name, List.of());
    this.database.add(newUser);
  }


}
