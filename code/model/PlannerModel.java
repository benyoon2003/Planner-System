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
   * Create, modify, or remove an event on a user’s schedule,
   * which may affect other user’s schedule.
   */
  public void modifyEvent();

  /**
   * Have the program automatically schedule an event on some users’
   * schedules at some time if possible.
   */
  public void automaticEventSchedule();

  /**
   * See events occurring at a given time for the given user.
   */
  public Event eventsAtThisTime(User selected, int time);

  public void makeUser(String Name);

  public void makeEvent(String name, String location, boolean online,
                        Day startDay, int startTime, Day endDay,
                        int endTime, List<User> invitedUsers);
}
