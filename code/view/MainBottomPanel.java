package view;

import java.util.Objects;

import javax.swing.*;

import model.ReadOnlyPlannerModel;
import model.User;

public class MainBottomPanel extends JPanel {

  private ReadOnlyPlannerModel model;

  private JComboBox<User> selectedUser;

  private JButton createEvent;

  private JButton scheduleEvent;

  User selected;

  /**
   * This should be package protected
   * because this panel should not leak information outside of the view package.
   * @param model the given model of the planner system
   */
  MainBottomPanel(ReadOnlyPlannerModel model){
    this.model = Objects.requireNonNull(model);

    makeSelectUserBox();
    makeEventButtons();
  }

  private void makeSelectUserBox(){
    this.selectedUser = new JComboBox<User>(model.getListOfUser().toArray(new User[0]));
    this.selected = model.getListOfUser().get(this.selectedUser.getSelectedIndex());
  }

  User getSelected(){
    return this.selected;
  }

  private void makeEventButtons(){
    this.createEvent = new JButton("Create Event");

  }

}
