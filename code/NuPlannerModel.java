import java.util.List;

/**
 * The operations and observations required for a weekly planner system
 * called NuPlanner where someone can manipulate events on multiple users'
 * schedules.
 */
public interface NuPlannerModel {

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
  public void selectSchedule(String user);

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
  public void eventsAtThisTime();
}
