package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import model.Event;

import model.User;

public class EventRedPanel extends JPanel implements MouseListener {


  private Event event;

  private int x;

  private int y;

  private int width;

  private int height;

  /**
   * This is package protected because there should not be any leakage of the Event panel
   * information and this should be contained in the view.
   * @param e the given event being drawn
   */
  EventRedPanel(Event e,int x, int y, int width, int height) {
    this.event = Objects.requireNonNull(e);
    this.setBounds(x, y, width, height);
    this.addMouseListener(this);
    this.x = x;
    this.y = y;
    this.height = height;
    //this.setBackground(Color.RED);
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

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    super.paintComponent(g2d);
    drawLines(g2d);
    this.setBackground(Color.RED);
  }

  private void drawLines(Graphics2D g2d) {
    AffineTransform old = g2d.getTransform();
    int  horizontalLineOffset = 29;
    for (int line = horizontalLineOffset; line < this.height;
         line += horizontalLineOffset) {
      if ((line % (horizontalLineOffset * 4)) == 0) {
        g2d.setStroke(new BasicStroke(4));
      } else {
        g2d.setStroke(new BasicStroke(2));
      }
      g2d.setColor(Color.BLACK);
      g2d.drawLine(0, line, 110, line);
    }
    g2d.setTransform(old);
  }
}