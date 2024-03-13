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

  NuPlanner model1;
  Event event1;
  Event event2;
  Event event3;
  Event event4;
  User user1;
  User user2;
  User user3;
  User user4;

  List<User> database1;
  List<User> database2;

  @Before
  public void setup() {
    event1 = new Event("event1","Snell", true,
            Day.Monday, 600, Day.Tuesday, 800, new ArrayList<>());

    user1 = new User("Ben", List.of(event1));

    event2 = new Event("event2", "Churchill", false,
            Day.Saturday, 300, Day.Monday, 400, List.of(user1));

    user2 = new User("Nico", List.of(event2));
    event3 = new Event("event3", "Matthews", true,
            Day.Wednesday, 300, Day.Wednesday, 400, List.of(user1, user2));
    user3 = new User("Jack", List.of(event3));
    database1 = List.of(user1);
    database2 = List.of(user1, user2, user3);

    model1 = new NuPlanner(database1);
  }

  // TODO: Test with bigger schedule
  @Test
  public void testWriteToFile() {
    Utils.writeToFile(user2);
  }

  @Test
  public void testReadXML() {
    Utils.readXML("Lucia", database1);
  }
}
