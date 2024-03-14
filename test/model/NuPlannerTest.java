package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import view.PlannerView;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing suite for our NUPlanner model. This tests all public functions and
 * some edge cases.
 */
//TODO: Test all public methods
public class NuPlannerTest {

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
  private void ExampleNuPlannerException(){
    this.example = new NuPlanner(new ArrayList<User>());
    ben = this.example.addUser("Ben");
    nico = this.example.addUser("Nico");
    e1 = this.example.createEvent("Ben", "Working on OOD", "Snell",false,
            Day.Monday, 2000, Day.Thursday, 2059, List.of("Ben", "Nico"));
  }


  @Test
  public void testSelectSchedule(){
    ExampleNuPlanner();
    assertEquals(example.selectSchedule("Ben"), List.of(e1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSelectScheduleException(){
    ExampleNuPlanner();
    assertEquals(example.selectSchedule("Lucia"), List.of(e1));
  }

  @Test
  public void testCreateEvent(){

  }

  @Test
  public void testCreateEventButEventConflictsWithHost(){

  }

  @Test
  public void testEventCreatedButConflictsWithOtherUser(){

  }

  @Test
  public void testRemoveEventAsHost(){

  }

  @Test
  public void testRemoveEventAsAttendee(){

  }

  @Test
  public void testModifyEvent(){

  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalModifyEvent(){

  }

  @Test
  public void testeventAtThisTime(){

  }

  @Test(expected = IllegalArgumentException.class)
  public void testEventAtThisTimeWithInvalidTime(){

  }

  @Test
  public void testAddUser(){
    ExampleNuPlanner();
    // Tests if given uid is already in system
    try {
      example.addUser("Ben");
    }
    catch (IllegalArgumentException ignored) {
    }

    // Tests if given uid is not in system
    example.addUser("Lucia");
    assertEquals(Utils.findUser("Lucia", example.getListOfUser()),
            new User("Lucia", List.of()));
  }

  @Test
  public void testAddUserWithExistingUser(){
    ExampleNuPlanner();
    PlannerModel copyOfExample = example;
    Event conflictedGolf = new Event("Golf", "course", false,
            Day.Monday, 2000, Day.Thursday, 2059, List.of());
    Event unconflictedGolf = new Event("Golf", "course", false,
            Day.Friday, 2000, Day.Friday, 2059, List.of());
    User conflictedBen = new User("Ben", List.of(conflictedGolf));
    User unconflictedBen = new User("Ben", List.of(unconflictedGolf));
    User hunter = new User("Hunter", List.of());

    // Tests if the given user contains a conflicting schedule with pre-existing user
    example.addUser(conflictedBen);
    assertEquals(example, copyOfExample);

    // Tests if the given user does not contain a conflicting schedule
    example.addUser(unconflictedBen);
    assertEquals(example.scheduleOnDay("Ben", Day.Friday), List.of(unconflictedGolf));

    // Tests if the given user does not exist in the database
    example.addUser(hunter);
    assertEquals(Utils.findUser("Hunter", example.getListOfUser()),
            new User("Hunter", List.of()));
  }

  @Test
  public void testScheduleOnDay(){

  }

  @Test
  public void testScheduleOnDayWithNoEvents(){
    
  }


}
