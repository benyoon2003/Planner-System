package model;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Event;
import model.User;
import model.Utils;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing suite for the utils class.
 */
public class UtilsTest {
  PlannerModel example;

  PlannerModel example2;

  User ben;

  User nico;

  User lucia;
  User patrick;
  User spongebob;
  User squidward;

  Event e1;
  Event e2;
  Event e3;
  Event e4;

  private void ExampleNuPlanner() {
    this.example = new NuPlanner(new ArrayList<User>());
    ben = this.example.addUser("Ben");
    nico = this.example.addUser("Nico");
    e1 = this.example.createEvent("Ben", "Working on OOD", "Snell", false,
            Day.Monday, 2000, Day.Thursday, 2059, List.of("Nico"));

  }

  private void ExampleNuPlanner2() {
    this.example2 = new NuPlanner(new ArrayList<>());
    lucia = this.example2.addUser("Lucia");
    patrick = this.example2.addUser("Patrick");
    spongebob = this.example2.addUser("Spongebob");
    squidward = this.example2.addUser("Squidward");
    e2 = this.example2.createEvent("Lucia", "grading exams", "home", true,
            Day.Monday, 0, Day.Monday, 1, List.of("Squidward"));
    e3 = this.example2.createEvent("Patrick", "eating", "Krusty Krab", false,
            Day.Tuesday, 500, Day.Thursday, 10, List.of("Squidward", "Spongebob"));
    e4 = this.example2.createEvent("Spongebob", "flipping patties", "Krusty Krab",
            false, Day.Friday, 600, Day.Saturday, 700, List.of("Patrick"));
  }


  @Test
  public void testWriteAndReadingXMLFile() {
    ExampleNuPlanner();
    Utils.writeToFile(ben);
    assertEquals(Utils.readXML("Ben", example.getListOfUser()), ben);

    ExampleNuPlanner2();
    Utils.writeToFile(spongebob);
    assertEquals(Utils.readXML("Spongebob", example2.getListOfUser()), spongebob);
  }
}
