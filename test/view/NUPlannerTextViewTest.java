package view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.NuPlanner;
import model.PlannerModel;

import static org.junit.Assert.*;

public class NUPlannerTextViewTest {

  PlannerModel model;

  NUPlannerTextView view;

  private void ExampleView(){
    model = new NuPlanner(new ArrayList<>());
    this.model.addUser("Ben");
    this.model.addUser("Nico");
    this.model.createEvent("Ben", "OOD", "Snell", true
    , Day.Monday, 1800, Day.Wednesday, 1800, List.of("Ben", "Nico"));
    view = new NUPlannerTextView(model);
  }

  @Test
  public void testView(){
    ExampleView();
    System.out.println(view.toString());
  }

}