package model;

import java.util.ArrayList;
import java.util.List;

import model.PlannerModel;

/**
 * Implements the planner model. The NUPlanner is a system where someone
 * can visualize multiple users’ schedules and manipulate events on them.
 */
public final class NuPlanner implements PlannerModel {
  private List<User> database;

  /**
   * Constructs an NuPlanner with an empty database.
   */
  public NuPlanner() {
    this.database = new ArrayList<>();
  }

  /**
   * Constructs an NuPlanner with the given database.
   *
   * @param database a list of User
   * @implNote The passed in list of User will always be valid if the planner
   *           is instantiated following the steps described in the README
   */
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

  /**
   * This function takes in a given list of strings with the name of users to convert
   * to a list of users which makes for creating events with strings easier.
   *
   * @param users the list of string of usernames
   * @return the list of users in the same order.
   */
  private List<User> mapUserList(List<String> users) {
    List<User> userList = new ArrayList<>();
    for (String user : users) {
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
    if (this.database.contains(u)) {
      List<String> invitedUserCopy = new ArrayList<>(invitedUsers);
      invitedUserCopy.add(0, user);
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
    if (this.database.contains(u)) {
      if (e.getHost().equals(u)) {
        e.removeAll();
      } else {
        u.schedule.remove(e);
      }
    }
  }


  @Override
  public void modifyEvent(Event e, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<String> invitedUsers, String host) {
    if (startDay.equals(endDay) && startTime == endTime) {
      throw new IllegalArgumentException("Invalid Times for an Event");
    }
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
  public List<Event> eventsAtThisTime(String user, int time) {
    User selected = Utils.findUser(user, this.database);
    List<Event> list = new ArrayList<>();
    for (Event e : selected.schedule) {
      if (e.startTimeOfEvent() == time) {
        list.add(e);
      }
    }
    if (list.isEmpty()) {
      throw new IllegalArgumentException("No event at this time");
    } else {
      return list;
    }
  }

  @Override
  public User addUser(String name) {
    try {
      Utils.findUser(name, this.database);
      throw new IllegalArgumentException("Given Name already exists");
    } catch (IllegalArgumentException e) {
      User newUser = new User(name, List.of());
      this.database.add(newUser);
      return newUser;
    }
  }

  @Override
  public void addUser(User user) {
    try {
      User userInDatabase = Utils.findUser(user.uid, this.database);
      List<Event> copyOfUserInDatabaseSchedule = userInDatabase.schedule;

      // Add event from new schedule if it doesn't conflict with pre-existing user's schedule
      for (Event e : user.schedule) {
        try {
          userInDatabase.addEvent(e);
        } catch (IllegalArgumentException ignored) {

          // Reset schedule if any of the events from the given user
          // conflict with the pre-existing user
          userInDatabase.schedule = copyOfUserInDatabaseSchedule;
          throw new IllegalArgumentException("The given user conflicts with " +
                  "the pre-existing user's schedule.");
        }
      }
    } catch (IllegalArgumentException e) {
      this.database.add(new User(user.uid, user.schedule));
    }
  }

  @Override
  public List<Event> scheduleOnDay(String user, Day day) {
    User selected = Utils.findUser(user, this.database);
    return selected.eventsOnDay(day);
  }

  @Override
  public List<User> getListOfUser() {
    return this.database;
  }


  @Override
  public List<Event> mainSchedule(){
    List<Event> events = new ArrayList<>();
    for (User u : this.database){
      events.addAll(u.schedule);
    }
    return events;
  }
}
