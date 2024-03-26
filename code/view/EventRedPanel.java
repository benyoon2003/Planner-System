package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import model.Day;
import model.Event;
import model.ReadOnlyPlannerModel;
import model.User;

public class EventRedPanel extends JPanel implements MouseListener {


  private Event event;

  private Graphics g;
  private Rectangle bounds;

  private boolean mouseIsDown;

  /**
   * This is package protected because there should not be any leakage of the Event panel
   * information and this should be contained in the view.
   * @param e the given event being drawn
   */
<<<<<<< HEAD
  EventRedPanel(Event e, int x, int y, int width, int height) {
    this.event = Objects.requireNonNull(e);
    super.addMouseListener(this);
    this.setBounds(x, y, width, height);
    this.setVisible(true);
=======
  EventRedPanel(Event e, Graphics g, Rectangle bounds) {
    this.event = Objects.requireNonNull(e);
    this.g = g;
    this.bounds = bounds;
    this.paintComponent(g);
    this.addMouseListener(this);
>>>>>>> 4fede1b72e3e5d4b51caa099c6b22a43cf950ee7
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    EventView event = new EventFrameView(this.event.observeName(), this.event.observeOnline(),
            this.event.observeLocation(), this.event.observeStartDayOfEvent(),
            Integer.toString(this.event.observeStartTimeOfEvent()),
            this.event.observeEndDayOfEvent(),
            Integer.toString(this.event.observeEndTimeOfEvent()),
            convertToStringArray(this.event.observeInvitedUsers()));
    event.display();
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  private String[] convertToStringArray(List<User> users) {
    String[] usernames = new String[users.size()];
    for (int index = 0; index < users.size(); index++) {
      usernames[index] = users.get(index).toString();
    }
    return usernames;
  }
  



}