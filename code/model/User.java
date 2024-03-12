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
  }
}
