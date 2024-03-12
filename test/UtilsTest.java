import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Day;
import model.Event;
import model.User;
import model.Utils;

public class UtilsTest {

  Event event1;
  User user1;

  @Before
  public void setup() {
    event1 = new Event("event1","Snell", true,
            Day.Monday, 600, Day.Tuesday, 800, new ArrayList<>());
    user1 = new User("Ben", List.of(event1));
  }

  // TODO: Test with bigger schedule
  @Test
  public void writeToFile() {
    Utils.writeToFile(user1);
  }



}
