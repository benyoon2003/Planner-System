package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import model.ReadOnlyPlannerModel;
import model.User;

public class MainBottomPanel extends JPanel {

  private ReadOnlyPlannerModel model;

  private User selected;

  private WeekViewPanel weekView;
  private MainScheduleFrameView main;


  /**
   * This should be package protected
   * because this panel should not leak information outside of the view package.
   * @param model the given model of the planner system
   */
  MainBottomPanel(ReadOnlyPlannerModel model, WeekViewPanel weekView, MainScheduleFrameView main){
    this.model = Objects.requireNonNull(model);
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(800,-600));
    this.weekView = weekView;
    this.main = main;

    makeSelectUserBox();
    makeEventButtons();

  }

  public ReadOnlyPlannerModel observeModel() {
    return this.model;
  }


  private void makeSelectUserBox() {
    JComboBox selectedUser = new JComboBox<>(convertToUserArray(model.getListOfUser()));
    this.add(selectedUser);
    this.selected = ((User) selectedUser.getSelectedItem());
    selectedUser.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        selected = (User) selectedUser.getSelectedItem();
        selectedUser.setSelectedIndex(selectedUser.getSelectedIndex());
        main.reMakeView((User) selectedUser.getSelectedItem());

      }
    });
  }

  private User[] convertToUserArray(List<User> users) {
    User[] usernames = new User[users.size()];
    for (int index = 0; index < users.size(); index++) {
      usernames[index] = users.get(index);
    }
    return usernames;
  }

  User getSelected(){
    return this.selected;
  }

  private void makeEventButtons(){
    JButton createEvent = new JButton("Create Event");
    createEvent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        EventView newEvent = new EventFrameView(selected.toString());
        newEvent.display();
      }
    });

    JButton scheduleEvent = new JButton("Schedule Event");
    scheduleEvent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        EventView newEvent = new EventFrameView(selected.toString());
        newEvent.display();
      }
    });
    this.add(createEvent);
    this.add(scheduleEvent);
  }

}
