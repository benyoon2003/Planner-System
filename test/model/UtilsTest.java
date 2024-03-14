package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Event;
import model.Tag;
import model.User;
import model.Utils;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
  PlannerModel example;

  User ben;

  User nico;

  User lucia;

  Event e1;

  private void ExampleNuPlanner(){
    this.example = new NuPlanner(new ArrayList<User>());
    ben = this.example.addUser("Ben");
    nico = this.example.addUser("Nico");
    e1 = this.example.createEvent("Ben", "Working on OOD", "Snell",false,
            Day.Monday, 2000, Day.Thursday, 2059, List.of("Ben", "Nico"));
  }
  

  @Test
  public void testWriteAndReadingXMLFile() {
    ExampleNuPlanner();
    Utils.writeToFile(ben);
    assertEquals(Utils.readXML("Ben", example.getListOfUser()), ben);
  }

}
