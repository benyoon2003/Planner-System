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
