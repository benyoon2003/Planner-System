package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Day;

/**
 * The EventFrameView is a custom JFrame that implements EventView. It contains components
 * that allows the user to input details for an event and create it
 * as well as read details that already exist within the event. The details within an event
 * include the following:
 * the name of the event, whether or not the event is available online, the location of the
 * event, the starting day, starting time, ending day, ending time, and a list of users invited
 * to the event.
 *
 * @implNote The eventPanel member variable is final because components are placed on top of it
 *           and the overarching panel itself does not change.
 *           The components that are laid on top of the eventPanel are the ones being modified,
 *           thus they are not final. The start time and end time are Strings to make it easier
 *           to modify components. This is package protected because there should not be any
 *           leakage of the Event panel information and this should be contained in the view.
 */
class EventFrameView extends JFrame implements EventView {
  private final JPanel eventPanel;
  private JTextArea name;
  private JComboBox<String> isOnline;
  private JTextArea location;
  private JComboBox<Day> startingDay;
  private JTextArea startingTime;
  private JComboBox<Day> endingDay;
  private JTextArea endingTime;
  private JList<String> availUser;

  /**
   * Constructs a default EventFrameView that contains default components of an event and
   * sets the host according to the given username.
   * @param host a username in the form of a String
   */
 EventFrameView(String host) {
    this("", true, "", Day.Monday, "",
            Day.Monday, "", new String[]{host});
  }

  /**
   * Constructs a EventFrameView using the pre-existing details of the event allowing
   * for the user to choose to modify parts of an existing event.
   * @param eventName a String
   * @param isOnline a boolean
   * @param location a String
   * @param startDay a Day
   * @param startTime a String
   * @param endDay a Day
   * @param endTime a Day
   * @param availUsers String array
   */
  EventFrameView(String eventName, boolean isOnline, String location,
                        Day startDay, String startTime, Day endDay, String endTime,
                        String[] availUsers) {
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.eventPanel = new JPanel();
    this.eventPanel.setLayout(new BoxLayout(this.eventPanel, BoxLayout.Y_AXIS));

    makeNamePanel(eventName);
    makeLocationPanel(isOnline, location);
    makeStartDayPanel(startDay);
    makeStartTimePanel(startTime);
    makeEndDayPanel(endDay);
    makeEndTimePanel(endTime);
    makeAvailUserPanel(availUsers);
    makeButtonPanel();

    this.add(this.eventPanel);
    this.pack();
  }

  /**
   * Creates the name panel that allows modification of the event name. '
   * Rests on top of the main event panel.
   * @param eventName a String
   */
  private void makeNamePanel(String eventName) {
    JPanel nameLabelPanel = new JPanel();
    JPanel namePanel = new JPanel();
    namePanel.setLayout(new BorderLayout());
    JLabel nameLabel = new JLabel("Event name:");
    this.name = new JTextArea(eventName, 1, 10);
    this.name.setLineWrap(true);
    this.name.setWrapStyleWord(true);
    namePanel.add(this.name);
    nameLabelPanel.add(nameLabel);
    nameLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.eventPanel.add(nameLabelPanel);
    this.eventPanel.add(namePanel);
  }

  /**
   * Creates the location panel which contains whether or not the event is available online
   * as well as the location of the event. Rests on top of the main event panel.
   * @param isOnline boolean
   * @param location String
   */
  private void makeLocationPanel(boolean isOnline, String location) {
    JPanel locationLabelPanel = new JPanel();
    JPanel locationPanel = new JPanel();
    JLabel locationLabel = new JLabel("Location:");
    String[] isOnlineList = {"Is online", "Not online"};
    this.isOnline = new JComboBox<>(isOnlineList);
    if (isOnline) {
      this.isOnline.setSelectedIndex(0);
    }
    else {
      this.isOnline.setSelectedIndex(1);
    }
    this.location = new JTextArea(location, 1, 10);
    this.location.setLineWrap(true);
    this.location.setWrapStyleWord(true);
    locationPanel.add(this.isOnline);
    locationPanel.add(this.location);
    locationLabelPanel.add(locationLabel);
    locationLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.eventPanel.add(locationLabelPanel);
    this.eventPanel.add(locationPanel);
  }

  /**
   * Creates the start day panel which allows modification of the start day of the event.
   * Rests on top of the main event panel.
   * @param day a Day
   */
  private void makeStartDayPanel(Day day) {
    JPanel startingDayPanel = new JPanel();
    JLabel startingDayLabel = new JLabel("Starting Day:");
    Day[] days = {Day.Sunday, Day.Monday, Day.Tuesday, Day.Wednesday,
            Day.Thursday, Day.Friday, Day.Saturday};
    this.startingDay = new JComboBox<>(days);
    switch (day.toString()) {
      case "Sunday":
        this.startingDay.setSelectedIndex(0);
        break;
      case "Monday":
        this.startingDay.setSelectedIndex(1);
        break;
      case "Tuesday":
        this.startingDay.setSelectedIndex(2);
        break;
      case "Wednesday":
        this.startingDay.setSelectedIndex(3);
        break;
      case "Thursday":
        this.startingDay.setSelectedIndex(4);
        break;
      case "Friday":
        this.startingDay.setSelectedIndex(5);
        break;
      case "Saturday":
        this.startingDay.setSelectedIndex(6);
        break;
    }
    startingDayPanel.add(startingDayLabel);
    startingDayPanel.add(this.startingDay);
    this.eventPanel.add(startingDayPanel);
  }

  /**
   * Creates the start time panel which allows for modification of the start time of the event.
   * Rests on top of the main event panel.
   * @param startTime a String
   */
  private void makeStartTimePanel(String startTime) {
    JPanel startingTimePanel = new JPanel();
    JLabel startingTimeLabel = new JLabel("Starting time:");
    this.startingTime = new JTextArea(startTime, 1, 10);
    this.startingTime.setLineWrap(true);
    this.startingTime.setWrapStyleWord(true);
    startingTimePanel.add(startingTimeLabel);
    startingTimePanel.add(this.startingTime);
    this.eventPanel.add(startingTimePanel);
  }

  /**
   * Creates the end day panel which allows modification of the end day of the event.
   * Rests on top of the main event panel.
   * @param day a Day
   */
  private void makeEndDayPanel(Day day) {
    JPanel endingDayPanel = new JPanel();
    JLabel endingDayLabel = new JLabel("Ending Day:");
    Day[] days = {Day.Sunday, Day.Monday, Day.Tuesday, Day.Wednesday,
            Day.Thursday, Day.Friday, Day.Saturday};
    this.endingDay = new JComboBox<>(days);
    switch (day.toString()) {
      case "Sunday":
        this.startingDay.setSelectedIndex(0);
        break;
      case "Monday":
        this.startingDay.setSelectedIndex(1);
        break;
      case "Tuesday":
        this.startingDay.setSelectedIndex(2);
        break;
      case "Wednesday":
        this.startingDay.setSelectedIndex(3);
        break;
      case "Thursday":
        this.startingDay.setSelectedIndex(4);
        break;
      case "Friday":
        this.startingDay.setSelectedIndex(5);
        break;
      case "Saturday":
        this.startingDay.setSelectedIndex(6);
        break;
    }
    endingDayPanel.add(endingDayLabel);
    endingDayPanel.add(this.endingDay);
    this.eventPanel.add(endingDayPanel);
  }

  /**
   * Creates the end time panel which allows for modification of the end time of the event.
   * Rests on top of the main event panel.
   * @param endTime a String
   */
  private void makeEndTimePanel(String endTime) {
    JPanel endingTimePanel = new JPanel();
    JLabel endingTimeLabel = new JLabel("Ending time:");
    this.endingTime = new JTextArea(endTime,1, 10);
    this.endingTime.setLineWrap(true);
    this.endingTime.setWrapStyleWord(true);
    endingTimePanel.add(endingTimeLabel);
    endingTimePanel.add(this.endingTime);
    this.eventPanel.add(endingTimePanel);
  }

  /**
   * Creates the invited user panel which allows for modification of the list of invited users.
   * Rests on top of the main event panel.
   * @param availUsers a String array
   */
  private void makeAvailUserPanel(String[] availUsers) {
    JPanel availUserLabelPanel = new JPanel();
    availUserLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel availUserLabel = new JLabel("Available users");
    JPanel availUserPanel = new JPanel(); // Panel for available users
    availUserPanel.setLayout(new BoxLayout(availUserPanel, BoxLayout.Y_AXIS));
    this.availUser = new JList<>(availUsers);
    availUserPanel.add(this.availUser);
    availUserLabelPanel.add(availUserLabel);
    this.eventPanel.add(availUserLabelPanel);
    this.eventPanel.add(availUserPanel);
  }

  /**
   * Creates the create event, modify event, and remove event buttons, which rest on top
   * of the main event panel.
   */
  private void makeButtonPanel() {
    JPanel buttonPanel = new JPanel();
    JButton createButton = new JButton("Create event");
    createButton.addActionListener(new ActionListener() {
      /**
       // Outputs the details of the event when action is performed on the button.
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (validInput()) {
          outputEventDetails();
        }
        else {
          System.out.print("Enter all of the information first.\n");
        }
      }
    });
    JButton modifyButton = new JButton("Modify event");
    JButton removeButton = new JButton("Remove event");
    removeButton.addActionListener(new ActionListener() {
      /**
       * Outputs a message alerting the user that a event is being removed from the host's
       * schedule and outputs the event details.
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (validInput()) {
          System.out.print("Remove event from " + availUser.getModel().getElementAt(0)
                  + "'s schedule.\n");
          outputEventDetails();
        }
        else {
          System.out.print("Enter all of the information first.\n");
        }
      }
    });
    buttonPanel.add(createButton);
    buttonPanel.add(modifyButton);
    buttonPanel.add(removeButton);
    this.eventPanel.add(buttonPanel);
  }

  /**
   * Checks if any of the required text fields for an event are empty.
   * @return a boolean
   */
  private boolean validInput() {
    return !name.getText().isEmpty() &&
            !location.getText().isEmpty() &&
            !startingTime.getText().isEmpty() &&
            !endingTime.getText().isEmpty();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void outputEventDetails() {
    System.out.print("Create event: \n");
    System.out.print("Event name: ");
    System.out.print("Location:\n");
    System.out.print(this.isOnline.getSelectedItem() + " ");
    System.out.print(this.location.getText());
    System.out.print("\nStarting day: ");
    System.out.print(this.startingDay.getSelectedItem());
    System.out.print("\nStarting time: ");
    System.out.print(this.startingTime.getText());
    System.out.print("\nEnding day: ");
    System.out.print(this.endingDay.getSelectedItem());
    System.out.print("\nEnding time: ");
    System.out.print(this.endingTime.getText());
    System.out.print("\nAvailable users: ");
    for (int item = 0; item < this.availUser.getModel().getSize(); item++) {
      System.out.print("\n" + this.availUser.getModel().getElementAt(item));
    }
    System.out.print("\n");

  }
}