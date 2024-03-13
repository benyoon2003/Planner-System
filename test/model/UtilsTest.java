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

public class UtilsTest {

  Event event1;
  Event event2;
  Event event3;
  Event event4;
  User user1;
  User user2;
  User user3;
  User user4;

  @Before
  public void setup() {
    event1 = new Event("event1","Snell", true,
            Day.Monday, 600, Day.Tuesday, 800, new ArrayList<>());

    user1 = new User("Ben", List.of(event1));

    event2 = new Event("event2", "Churchill", false,
            Day.Saturday, 300, Day.Monday, 400, new ArrayList<>(List.of(user1)));

    user2 = new User("Nico", List.of(event2));
  }

  // TODO: Test with bigger schedule
  @Test
  public void writeToFile() {
    Utils.writeToFile(user2);
    //System.out.print(Utils.readXML("Nico", Tag.users).toString());
  }



}
