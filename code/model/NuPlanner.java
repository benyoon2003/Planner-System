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

  @Override
  public List<Event> selectSchedule(String user) {
    List<Event> selected = new ArrayList<>();
    for (User u : this.database){
      if (u.uid.equals(user)){
        selected = u.schedule;
      }
    }
    if (selected.isEmpty()){
      throw new IllegalArgumentException("Not a Valid User");
    }else{
      return selected;
    }
  }

  @Override
  public void createEvent(User u, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<User> invitedUsers) {
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
  public void removeEvent(User u, Event e) {
      if (this.database.contains(u)){
        if (e.getHost().equals(u)){
          
        }
      }
  }


  @Override
  public void modifyEvent() {

  }


  @Override
  public Event eventsAtThisTime(User selected, int time) {
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
  public void makeUser(String Name) {
    User newUser = new User(Name, List.of());
    this.database.add(newUser);
  }


}
