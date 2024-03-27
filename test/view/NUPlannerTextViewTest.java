package view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.NuPlanner;
import model.PlannerModel;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for the TextView.
 */
public class NUPlannerTextViewTest {

  private PlannerModel model;

  private NUPlannerTextView view;

  private void exampleView() {
    model = new NuPlanner(new ArrayList<>());
    this.model.addUser("Ben");
    this.model.addUser("Nico");
    this.model.createEvent("Ben", "OOD", "Snell", true,
            Day.Monday, 1000, Day.Monday, 1800, List.of("Nico"));
    view = new NUPlannerTextView(model);
  }

  @Test
  public void testView() {
    exampleView();
    System.out.println(view.toString());
    String[] test = view.toString().split("\n");
    assertEquals(test[3], "       name: OOD");
  }

}