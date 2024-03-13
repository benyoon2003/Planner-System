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
  public void uploadSchedule();

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
   * @param u host of the event
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
  public void createEvent(User u, String name, String location, boolean online,
                          Day startDay, int startTime, Day endDay,
                          int endTime, List<User> invitedUsers);

  /**
   * Remove an event from a user's schedule.
   * @param u the given user
   */
  public void removeEvent(User u, Event e);

  /**
   * Create, modify, or remove an event on a user’s schedule,
   * which may affect other user’s schedule.
   */
  public void modifyEvent();


  /**
   * See events occurring at a given time for the given user.
   */
  public Event eventsAtThisTime(User selected, int time);

  /**
   * Creates a user with no events.
   * @param Name the uid of the user
   */
  public void makeUser(String Name);

}
