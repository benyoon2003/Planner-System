package model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

  User example;

  Event one;

  Event two;

  private void ExampleSchedule(){
    one = new Event("Host Event", "Snell", false,
            Day.Monday, 1000, Day.Tuesday, 1800, List.of());
    two = new Event("Host Event", "Snell", false,
            Day.Sunday, 1200, Day.Monday, 800, List.of());
    example = new User("Example", List.of(one, two));
    one.setInvitedUsers(List.of(example));
    two.setInvitedUsers(List.of(example));
  }

  private void ExampleConflictSchedule(){
    one = new Event("Host Event", "Snell", false,
            Day.Monday, 1000, Day.Thursday, 1800, List.of());
    two = new Event("Host Event", "Snell", false,
            Day.Monday, 1200, Day.Tuesday, 1800, List.of());
    example = new User("Conflict", List.of(one, two));
    one.setInvitedUsers(List.of(example));
    two.setInvitedUsers(List.of(example));
  }

  private void Example2ConflictSchedule(){
    one = new Event("Host Event", "Snell", false,
            Day.Friday, 1000, Day.Thursday, 1800, List.of());
    two = new Event("Host Event", "Snell", false,
            Day.Saturday, 1200, Day.Saturday, 1800, List.of());
    example = new User("Conflict", List.of(one, two));
    one.setInvitedUsers(List.of(example));
    two.setInvitedUsers(List.of(example));
  }

  private void ExampleAddEvent(){
    one = new Event("Host Event", "Snell", false,
            Day.Monday, 1000, Day.Tuesday, 1800, List.of());
    two = new Event("Host Event", "Snell", false,
            Day.Sunday, 1200, Day.Sunday, 1800, List.of());
    example = new User("Example", List.of(one));
    one.setInvitedUsers(List.of(example));
    two.setInvitedUsers(List.of(example));
  }

  private void ExampleAddEventInvalid(){
    one = new Event("Host Event", "Snell", false,
            Day.Monday, 1000, Day.Thursday, 1800, List.of());
    two = new Event("Host Event", "Snell", false,
            Day.Monday, 1200, Day.Tuesday, 1800, List.of());
    example = new User("Conflict", List.of(one));
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

  @Test
  public void testSortEvents(){
    ExampleSchedule();
    assertEquals(this.example.schedule.get(0), two);
  }

  @Test
  public void testAddEvent(){
    ExampleAddEvent();
    this.example.addEvent(two);
    assertEquals(this.example.schedule.get(0), two);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddEvent(){
    ExampleAddEventInvalid();
    this.example.addEvent(two);
  }

  @Test
  public void testEventOnDay(){
    ExampleSchedule();
    assertEquals(this.example.eventsOnDay(Day.Monday), List.of(two, one));
  }
}