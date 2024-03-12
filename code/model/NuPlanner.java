package model;

import java.util.ArrayList;
import java.util.List;

import model.PlannerModel;

public class NuPlanner implements PlannerModel {
  private List<User> database;

  public NuPlanner() {
    this.database = new ArrayList<>();
  }

  public NuPlanner(List<User> database) {
    this.database = database;
  }

  @Override
  public void uploadSchedule() {

  }

  @Override
  public void saveSchedule() {
    for (User user : this.database) {
      Utils.writeToFile(user);
    }
  }

  @Override
  public void selectSchedule(String user) {

  }

  @Override
  public void modifyEvent() {

  }

  @Override
  public void automaticEventSchedule() {

  }

  @Override
  public void eventsAtThisTime() {

  }
}
