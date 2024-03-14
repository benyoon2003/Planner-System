package view;

import java.io.IOException;

import model.Day;
import model.Event;
import model.PlannerModel;
import model.User;

/**
 * This is the text version of the view of a planner model
 */
public class NUPlannerTextView implements PlannerView {

  private PlannerModel model;

  public NUPlannerTextView(PlannerModel model) {
    this.model = model;
  }


  /**
   * This creates a string of the events on a given day
   *
   * @param user with the events being displayed.
   * @param day  the day on which the event s are being pulled.
   * @return the display of the events
   */
  private String daySchedule(String user, Day day) {
    String output = "";
    for (Event e : this.model.scheduleOnDay(user, day)) {
      output += e.toString();
    }
    return output;
  }

  @Override
  public String toString() {
    String output = "";
    for (User user : model.getListOfUser()) {
      output += "User: " + user.toString() + "\n";
      output += "Sunday: \n" + daySchedule(user.toString(), Day.Sunday);
      output += "Monday: \n" + daySchedule(user.toString(), Day.Monday);
      output += "Tuesday: \n" + daySchedule(user.toString(), Day.Tuesday);
      output += "Wednesday: \n" + daySchedule(user.toString(), Day.Wednesday);
      output += "Thursday: \n" + daySchedule(user.toString(), Day.Thursday);
      output += "Friday: \n" + daySchedule(user.toString(), Day.Friday);
      output += "Saturday: \n" + daySchedule(user.toString(), Day.Saturday);
    }
    return output;
  }

  @Override
  public void render() throws IOException {
    System.out.println(this.toString());
  }
}
