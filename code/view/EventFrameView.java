package view;

import java.awt.*;

import javax.swing.*;

import model.Day;
import model.User;


public class EventFrameView extends JFrame implements EventView {
  private final JPanel eventPanel;
  private final JTextArea name;
  private final JComboBox<String> isOnline;
  private final JTextArea location;
  private final JComboBox<Day> startingDay;
  private final JTextArea startingTime;
  private final JComboBox<Day> endingDay;
  private final JTextArea endingTime;
  private final JButton modifyButton;
  private final JButton removeButton;
  private final JList<User> availUser;

  public EventFrameView() {
    this.setSize(500, 800);
    this.setLocation(200, 200);

    this.eventPanel = new JPanel();

    JLabel nameLabel = new JLabel("Event name:");
    this.name = new JTextArea();

    JPanel locationPanel = new JPanel(new FlowLayout());
    JLabel locationLabel = new JLabel("Location:");
    String[] isOnline = {"Is online", "Not online"};
    this.isOnline = new JComboBox<>(isOnline);
    this.location = new JTextArea();
    locationPanel.add(this.isOnline);
    locationPanel.add(this.location);

    JPanel startingDayPanel = new JPanel(new FlowLayout());
    JLabel startingDayLabel = new JLabel("Starting Day:");
    Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday,
            Day.Thursday, Day.Friday, Day.Saturday, Day.Sunday};
    this.startingDay = new JComboBox<>(days);
    startingDayPanel.add(startingDayLabel);
    startingDayPanel.add(this.startingDay);

    JPanel startingTimePanel = new JPanel(new FlowLayout());
    JLabel startingTimeLabel = new JLabel("Starting time:");
    this.startingTime = new JTextArea();
    //startingTimePanel.add

    JLabel endingDayLabel = new JLabel("Ending Day:");
    this.endingDay = new JComboBox<>(days);

    JLabel endingTimeLabel = new JLabel("Ending time:");
    this.endingTime = new JTextArea();

    this.modifyButton = new JButton("Modify event");
    this.removeButton = new JButton("Remove event");

    JLabel availUserLabel = new JLabel("Available users");
    this.availUser = new JList<>();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.eventPanel.add(nameLabel);
    this.eventPanel.add(this.name);
    this.eventPanel.add(locationLabel);
    this.eventPanel.add(locationPanel);
    this.eventPanel.add(startingDayPanel);



    this.eventPanel.add(this.availUser);
    this.eventPanel.add(this.modifyButton);
    this.eventPanel.add(this.removeButton);
    this.eventPanel.setLayout(new BoxLayout(this.eventPanel, BoxLayout.PAGE_AXIS));
    this.add(this.eventPanel);
    this.pack();
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
