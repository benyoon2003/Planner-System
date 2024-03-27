package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(800,-600));
    makeSelectUserBox();
    makeEventButtons();

  }


  private void makeSelectUserBox() {
    this.selectedUser = new JComboBox<User>(model.getListOfUser().toArray(new User[0]));
    this.selected = model.getListOfUser().get(this.selectedUser.getSelectedIndex());
    this.selectedUser.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        selected = (User) selectedUser.getSelectedItem();
      }
    });
    this.add(this.selectedUser);
  }

  User getSelected(){
    return this.selected;
  }

  private void makeEventButtons(){
    this.createEvent = new JButton("Create Event");
    this.createEvent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        EventView newEvent = new EventFrameView(selected.toString());
        newEvent.display();
      }
    });

    this.scheduleEvent = new JButton("Schedule Event");
    this.scheduleEvent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        EventView newEvent = new EventFrameView(selected.toString());
        newEvent.display();
      }
    });
    this.add(this.createEvent);
    this.add(this.scheduleEvent);
  }

}
