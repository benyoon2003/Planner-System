package view;

import java.awt.*;
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

  public EventFrameView() {
    this("", true, "", Day.Monday, "", Day.Monday, "", new String[]{" "});
  }

  public EventFrameView(String eventName, boolean isOnline, String location,
                        Day startDay, String startTime, Day endDay, String endTime,
                        String[] availUsers) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    switch (day) {
      case Day.Sunday -> this.startingDay.setSelectedIndex(0);
      case Day.Monday -> this.startingDay.setSelectedIndex(1);
      case Day.Tuesday -> this.startingDay.setSelectedIndex(2);
      case Day.Wednesday -> this.startingDay.setSelectedIndex(3);
      case Day.Thursday -> this.startingDay.setSelectedIndex(4);
      case Day.Friday -> this.startingDay.setSelectedIndex(5);
      case Day.Saturday -> this.startingDay.setSelectedIndex(6);
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
    switch (day) {
      case Day.Sunday -> this.endingDay.setSelectedIndex(0);
      case Day.Monday -> this.endingDay.setSelectedIndex(1);
      case Day.Tuesday -> this.endingDay.setSelectedIndex(2);
      case Day.Wednesday -> this.endingDay.setSelectedIndex(3);
      case Day.Thursday -> this.endingDay.setSelectedIndex(4);
      case Day.Friday -> this.endingDay.setSelectedIndex(5);
      case Day.Saturday -> this.endingDay.setSelectedIndex(6);
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
    this.modifyButton = new JButton("Modify event");
    this.removeButton = new JButton("Remove event");
    buttonPanel.add(this.createButton);
    buttonPanel.add(this.modifyButton);
    buttonPanel.add(this.removeButton);
    this.eventPanel.add(buttonPanel);
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  public static void main(String[] args) {
    EventView view = new EventFrameView();
    view.display();
  }
}
