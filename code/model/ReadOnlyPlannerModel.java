package model;

import java.util.List;

public interface ReadOnlyPlannerModel {

  /**
   * Select one of the users to display their schedule.
   *
   * @param user a String
   */
  public List<Event> selectSchedule(String user);

  /**
   * See events occurring at a given time for the given user.
   */
  public List<Event> eventsAtThisTime(String user, int time);
  /**
   * Finds and returns a list of events for a specified user on a given day.
   *
   * @param user the given user
   * @param day  the specifc day being requested
   * @return the list of events on that day.
   */
  public List<Event> scheduleOnDay(String user, Day day);

  /**
   * This method returns the list of Users in the database.
   *
   * @return the list of users in the database.
   */
  public List<User> getListOfUser();
}
