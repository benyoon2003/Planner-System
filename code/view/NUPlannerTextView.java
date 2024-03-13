package view;

import java.io.IOException;

import model.PlannerModel;
import model.User;

public class NUPlannerTextView implements PlannerView {

  private PlannerModel model;

  public NUPlannerTextView(PlannerModel model){
    this.model = model;
  }

  private 

  @Override
  public String toString(){
    String output = "";
    for(User user : model.getListOfUser()){
      output += "name: " + user.toString() + "\n";
      output += "Sunday: " +

    }
  }
  @Override
  public void render() throws IOException {

  }
}
