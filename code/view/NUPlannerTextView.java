package view;

import java.io.IOException;

import model.Day;
import model.Event;
import model.PlannerModel;
import model.User;

public class NUPlannerTextView implements PlannerView {

  private PlannerModel model;

  public NUPlannerTextView(PlannerModel model){
    this.model = model;
  }

  private String daySchedule(String user, Day day){
    String output = "";
    for (Event e : this.model.scheduleOnDay(user, day)){
      output += e.toString();
    }
    return output;
  }

  @Override
  public String toString(){
    String output = "";
    for(User user : model.getListOfUser()){
      output += "name: " + user.toString() + "\n";
      output += "Sunday: " + daySchedule(user.toString(), Day.Sunday);
      output += "Monday: " + daySchedule(user.toString(), Day.Monday);
      output += "Tuesday: " + daySchedule(user.toString(), Day.Tuesday);
      output += "Wednesday: " + daySchedule(user.toString(), Day.Wednesday);
      output += "Thursday: " + daySchedule(user.toString(), Day.Thursday);
      output += "Friday: " + daySchedule(user.toString(), Day.Friday);
      output += "Saturday: " + daySchedule(user.toString(), Day.Saturday);
    }
    return output;
  }
  @Override
  public void render() throws IOException {
    System.out.println(this.toString());
  }
}
