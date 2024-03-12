import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Event;
import model.User;

public class EventTest {

  Event example;

  User user1;

  private void ExampleInvalidTimes(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Invalid model.Event", "Snell", false,
            Day.Monday, 1800, Day.Monday, 1800, List.of(user1));
  }

  private void ExampleInvalidStartTimes(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Invalid model.Event", "Snell", false,
            Day.Monday, -1800, Day.Tuesday, 1800, List.of(user1));
  }

  private void ExampleInvalidEndTimes(){
    this.user1 = new User("User1", new ArrayList<>());
    this.example = new Event("Invalid model.Event", "Snell", false,
            Day.Monday, 1800, Day.Tuesday, -1800, List.of(user1));
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



}