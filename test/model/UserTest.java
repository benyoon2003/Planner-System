package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

  User example;

  private void ExampleConflictSchedule(){
    Event one = new Event("Host Event", "Snell", false,
            Day.Monday, 1000, Day.Thursday, 1800, List.of());
    Event two = new Event("Host Event", "Snell", false,
            Day.Monday, 1200, Day.Tuesday, 1800, List.of());
    example = new User("Conflict", List.of(one, two));
    one.setInvitedUsers(List.of(example));
    two.setInvitedUsers(List.of(example));
  }

  private void Example2ConflictSchedule(){
    Event one = new Event("Host Event", "Snell", false,
            Day.Friday, 1000, Day.Thursday, 1800, List.of());
    Event two = new Event("Host Event", "Snell", false,
            Day.Saturday, 1200, Day.Saturday, 1800, List.of());
    example = new User("Conflict", List.of(one, two));
    one.setInvitedUsers(List.of(example));
    two.setInvitedUsers(List.of(example));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConflict(){
    ExampleConflictSchedule();
  }
  @Test(expected = IllegalArgumentException.class)
  public void test2Conflict(){
    Example2ConflictSchedule();
  }
}