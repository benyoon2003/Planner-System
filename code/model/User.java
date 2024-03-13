package model;

import java.util.ArrayList;
import java.util.List;

import model.Event;

public class User {
  String uid;
  List<Event> schedule;

  public User(String uid, List<Event> schedule) {
    this.uid = uid;
    this.schedule = new ArrayList<>();
    if (conflict(schedule)){
      throw new IllegalArgumentException("Schedule has conflicts");
    }else {
      this.schedule = schedule;
      for (Event e : this.schedule) {
        e.getInvitedUsers().addFirst(this);
      }
    }
  }

  private boolean conflict(List<Event> schedule){
    List<Event> copy = new ArrayList<Event>(schedule);
    for (Event event: schedule){
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
    if(startTimeOfOne < startTimeOfTwo && endTimeOfOne > startTimeOfTwo){
      return true;
    }
    if(startTimeOfTwo < startTimeOfOne && endTimeOfTwo > startTimeOfOne){
      return true;
    }else{
      return false;
    }
  }
}
