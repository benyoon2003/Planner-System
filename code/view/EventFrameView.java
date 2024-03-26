package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BooleanSupplier;

import javax.swing.*;

import model.Day;
import model.User;


public class EventFrameView extends JFrame implements EventView {
  private JPanel eventPanel;
  private JTextArea name;
  private JComboBox<String> isOnline;
  private JTextArea location;
  private JComboBox<Day> startingDay;
  private JTextArea startingTime;
  private JComboBox<Day> endingDay;
  private JTextArea endingTime;
  private JButton createButton;
  private JButton modifyButton;
  private JButton removeButton;
  private JList<String> availUser;

  public EventFrameView(String host) {
    this("", true, "", Day.Monday, "", Day.Monday, "", new String[]{host});
  }

  public EventFrameView(String eventName, boolean isOnline, String location,
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

  private void makeButtonPanel() {
    JPanel buttonPanel = new JPanel();
    this.createButton = new JButton("Create event");
    this.createButton.addActionListener(new ActionListener() {
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
    this.modifyButton = new JButton("Modify event");
    this.removeButton = new JButton("Remove event");
    this.removeButton.addActionListener(new ActionListener() {
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
    buttonPanel.add(this.createButton);
    buttonPanel.add(this.modifyButton);
    buttonPanel.add(this.removeButton);
    this.eventPanel.add(buttonPanel);
  }

  public boolean validInput() {
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
    System.out.print(this.name.getText() + "\n");
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

  public static void main(String[] args) {
    EventView view = new EventFrameView("Ben");
    view.display();
  }
}