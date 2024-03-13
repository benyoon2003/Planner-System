package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

  }

  @Test
  public void testAddUserWithExistingUser(){

  }

  @Test
  public void testScheduleOnDay(){

  }

  @Test
  public void testScheduleOnDayWithNoEvents(){
    
  }


}
