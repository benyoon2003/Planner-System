import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.NuPlanner;
import view.MainScheduleFrameView;

/**
 * This is the class to run the planner system. Right now the system has not controller and
 * because of that the view has no effect on the model but the controller will be added
 * soon.
 */
public final class PlannerRunner {
  /**
   * This method is used to test the view and see it on a test model of users.
   * We used this to see how our implementation looked and what bugs there were.
   * @param args
   */
  public static void main(String[] args) {
    NuPlanner testModel = new NuPlanner(new ArrayList<>());
    testModel.addUser("Ben");
    testModel.addUser("Nico");
    testModel.addUser("Lucia");
    testModel.createEvent("Ben", "Working on OOD", "Snell", false,
            Day.Monday, 1000, Day.Wednesday, 2055, List.of());
    testModel.createEvent("Nico", "Also working on OOD", "Snell", true,
            Day.Thursday, 500, Day.Saturday, 2000, List.of());
    testModel.createEvent("Lucia", "Also working on OOD", "Meserve", true,
            Day.Sunday, 500, Day.Sunday, 900, List.of("Ben", "Nico"));
    new MainScheduleFrameView(testModel);

  }
}
