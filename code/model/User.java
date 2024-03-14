package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


/**
 * This is the class for Users in the planner system.
 */
  public class User {
  String uid;
  List<Event> schedule;

  /**
   * This is the constructor for a user which takes in a uid and a list of events
   * schedule. If the schedule has conflicts then an exception is thrown.
   * @param uid the unique identifier of the user
   * @param schedule the list of events that the user participates in
   * @throws IllegalArgumentException if the given schedule has conflicts
   */
  public User(String uid, List<Event> schedule) {
    this.uid = uid;
    this.schedule = new ArrayList<>();
    if (conflict(schedule)){
      throw new IllegalArgumentException("Schedule has conflicts");
    }else {
      this.schedule = new ArrayList<>(schedule);
      this.sortEvents();
    }
  }

  /**
   * This method determines whether the given schedule has a conflict of events in it.
   * @param schedule the given schedule
   * @return a boolean value to whether there is a conflict, true for conflict false for
   * no conflict.
   */
  private boolean conflict(List<Event> schedule){
    List<Event> copy = new ArrayList<Event>(schedule);
    for (Event event : schedule){
      copy.remove(event);
      if (copy.isEmpty()){
        return false;
      }
      for (int remainingEvents = 0; remainingEvents < copy.size(); remainingEvents++){
        if (conflictHelper(event, copy.get(remainingEvents))){
          return true;
        }
      }
    }
    return false;
  }
  private boolean conflictHelper(Event one, Event two){
    if (one.equals(two)){
      return false;
    }
    List DaysOrder = List.of(Day.Sunday, Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday
    , Day.Friday, Day.Saturday);
    int startTimeOfOne = DaysOrder.indexOf(one.getStartDay()) * 2400 + one.getStartTime();
    int startTimeOfTwo = DaysOrder.indexOf(two.getStartDay()) * 2400 + two.getStartTime();
    int endTimeOfOne = DaysOrder.indexOf(one.getEndDay()) * 2400 + one.getEndTime();
    int endTimeOfTwo = DaysOrder.indexOf(two.getEndDay()) * 2400 + two.getEndTime();
    if (startTimeOfOne > endTimeOfOne){
      endTimeOfOne = endTimeOfOne + 10080;
    }
    if (startTimeOfTwo > endTimeOfTwo){
      endTimeOfTwo = endTimeOfTwo + 10080;
    }
    if(startTimeOfOne <= startTimeOfTwo && endTimeOfOne > startTimeOfTwo){
      return true;
    }
    if(startTimeOfTwo <= startTimeOfOne && endTimeOfTwo > startTimeOfOne){
      return true;
    }else{
      return false;
    }
  }

  private void sortEvents(){
    this.schedule.sort(new EventComparator());
  }

  private int convertEventToStartTime(Event e){
    List DaysOrder = List.of(Day.Sunday, Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday
            , Day.Friday, Day.Saturday);
    return DaysOrder.indexOf(e.getStartDay()) * 2400 + e.getStartTime();
  }

  private class EventComparator implements Comparator<Event>{
    @Override
    public int compare(Event e1, Event e2) {
      return convertEventToStartTime(e1) - convertEventToStartTime(e2);
    }

  }


  /**
   * This is a method to add an event to a user while also ensuring the integrity of the
   * schedule.
   * The
   * @param e the event wanting to be added
   */
  void addEvent(Event e){
    List<Event> copy = new ArrayList<>(this.schedule);
    copy.add(e);
    if (!conflict(copy)){
      this.schedule.add(e);
      this.sortEvents();
    }else {
      throw new IllegalArgumentException("Event conflicts with schedule");
    }
  }

  /**
   * This finds and returns a list of events on a given day.
   * @param day the day given
   * @return a list of events on that day.
   */
  List<Event> eventsOnDay(Day day){
    List<Event> events = new ArrayList<>();
    for (Event e : this.schedule){
      if(e.getStartDay().equals(day) || e.getEndDay().equals(day)){
        events.add(e);
      }
    }
    return events;
  }

  @Override
  public String toString(){
    return this.uid;
  }

  @Override
  public boolean equals(Object o) {
    User u = (User) o;
    boolean sameSchedule = true;
    for (int index = 0; index < this.schedule.size(); index++) {
      if (!this.schedule.get(index).equals(u.schedule.get(index))) {
        sameSchedule = false;
      }
    }
    return this.uid.equals(u.uid) && sameSchedule;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.uid, this.schedule);
  }
}
