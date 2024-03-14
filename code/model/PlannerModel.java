package model;

import java.util.List;

/**
 * The operations and observations required for a weekly planner system
 * called PlannerModel where someone can manipulate events on multiple users'
 * schedules.
 */
public interface PlannerModel {

  /**
   * Upload an XML file representing a single user's schedule.
   */
  public void uploadSchedule(String path);

  /**
   * Save each user’s schedule to an XML file.
   */
  public void saveSchedule();

  /**
   * Select one of the users to display their schedule.
   * @param user a String
   */
  public List<Event> selectSchedule(String user);

  /**
   * Create an event to which the given user is the host.
   * @param user host of the event
   * @param name of the event
   * @param location of the event
   * @param online whether the event is online or not
   * @param startDay of the event
   * @param startTime of the event
   * @param endDay of the event
   * @param endTime of the event
   * @param invitedUsers of the event
   * @throws IllegalArgumentException if host is not at the start of invited list
   */
  public Event createEvent(String user, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<String> invitedUsers);

  /**
   * Remove an event from a user's schedule.
   * @param user the given user
   */
  public void removeEvent(String user, Event e);

  /**
   * Create, modify, or remove an event on a user’s schedule,
   * which may affect other user’s schedule.
   */
  public void modifyEvent(Event e, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<String> invitedUsers, String user);


  /**
   * See events occurring at a given time for the given user.
   */
  public List<Event> eventsAtThisTime(String user, int time);

  /**
   * Creates a user with no events.
   * @param Name the uid of the user
   */
  public User addUser(String Name);

  /**
   * Creates a user with no events.
   * @param user a user
   */
  public void addUser(User user);

  /**
   * Finds and returns a list of events for a specified user on a given day.
   * @param user the given user
   * @param day the specifc day being requested
   * @return the list of events on that day.
   */
  public List<Event> scheduleOnDay(String user, Day day);


  public List<User> getListOfUser();
}
