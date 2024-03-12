import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Event;
import model.User;

import static org.junit.Assert.assertEquals;

public class EventTest {

  Event example;

  User user1;

  private void ExampleInvalidTimes(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Invalid Event", "Snell", false,
            Day.Monday, 1800, Day.Monday, 1800, List.of(user1));
  }

  private void ExampleInvalidStartTimes(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Invalid Event", "Snell", false,
            Day.Monday, -1800, Day.Tuesday, 1800, List.of(user1));
  }

  private void ExampleInvalidEndTimes(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Invalid Event", "Snell", false,
            Day.Monday, 1800, Day.Tuesday, 3600, List.of(user1));
  }

  private void ExampleHostUser(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Host Event", "Snell", false,
            Day.Monday, 1000, Day.Tuesday, 1800, List.of(user1));
  }


  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTimes(){
    ExampleInvalidTimes();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidStartTimes(){
    ExampleInvalidStartTimes();
  }
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidEndTimes(){
    ExampleInvalidEndTimes();
  }

  @Test
  public void testHostUser(){
    ExampleHostUser();
    assertEquals(example.getHost(), user1);
  }



}