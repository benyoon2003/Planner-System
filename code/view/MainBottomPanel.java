package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.ReadOnlyPlannerModel;
import model.User;


/**
 * This is the panel at the bottom of the main system frame which holds the select box to
 * select user, the create event button, and the schedule event button. This panel allows for
 * more user control in this frame. In this file there are the features of this frame that connect
 * to the relevant EventView which would then be connected to the controller. This
 * panel mutates the entire week view to show the selected users schedule.
 * We thought this should be a feature of the view and not controller as the user is merely
 * shifting their view throughout the model and not modifying any data. Therefore,
 * that functionality is in this class.
 *
 *        @implNote This should be package protected because this panel should not leak
 *        information outside the view package.
 */
class MainBottomPanel extends JPanel {

  private final ReadOnlyPlannerModel model;

  private User selected;
  private MainScheduleFrameView main;


  /**
   * This constructs the bottom panel of the main scheduler frame.
   * which takes in the following as parameters.
   *
   *      @param model the ReadOnlyPlannerModel being viewed
   *      @param main  the MainScheduleFrameView
   *      @implNote The entire view is changed when a new user is selected so the entire
   *      view (main) should also be passed as a parameter so that this panel can change it.
   */
  MainBottomPanel(ReadOnlyPlannerModel model, MainScheduleFrameView main) {
    this.model = Objects.requireNonNull(model);
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(800, -600));
    this.main = main;

    makeSelectUserBox();
    makeEventButtons();

  }


  /**
   * This creates the JComboBox in which the user can select a user to view. Changing
   * the user changes the main schedule frame's view to reflect the schedule of
   * the selected user.
   */
  private void makeSelectUserBox() {
    JComboBox<User> selectedUser = new JComboBox<>(convertToUserArray(model.getListOfUser()));
    this.add(selectedUser);
    this.selected = ((User) selectedUser.getSelectedItem());
    selectedUser.addActionListener(new ActionListener() {
      /**
       * Remakes the schedule view to reflect the change in selected user.
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        selected = (User) selectedUser.getSelectedItem();
        selectedUser.setSelectedIndex(selectedUser.getSelectedIndex());
        main.reMakeView((User) selectedUser.getSelectedItem());
      }
    });
  }

  /**
   * This method converts a given list of users to an array of users
   * to use in the JComboBox and select users. This is used in the
   * mouse clicked method which opens the event dialogue box with the
   * list of users in the event.
   *
   * @param users the list of users in the event
   * @return a mirroring array of users
   */
  private User[] convertToUserArray(List<User> users) {
    User[] usernames = new User[users.size()];
    for (int index = 0; index < users.size(); index++) {
      usernames[index] = users.get(index);
    }
    return usernames;
  }

  /**
   * This method creates the "Create Event" Button and the "Schedule Event" button
   * that should be at the bottom of this panel as specified in the assignment. These
   * buttons once clicked call and open the Event Frame view where a user can modify or
   * create an event.
   */
  private void makeEventButtons() {
    JButton createEvent = new JButton("Create Event");
    createEvent.addActionListener(new ActionListener() {
      /**
       * Displays the EventView frame when action is performed on the button.
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        EventView newEvent = new EventFrameView(selected.toString());
        newEvent.display();
      }
    });

    JButton scheduleEvent = new JButton("Schedule Event");
    scheduleEvent.addActionListener(new ActionListener() {
      /**
       * Displays the EventView frame when action is performed on the button.
       * @param e the event to be processed
       */
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
