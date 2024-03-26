package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import model.Day;
import model.Event;
import model.ReadOnlyPlannerModel;
import model.User;

public class WeekViewPanel extends JPanel {


  private final ReadOnlyPlannerModel model;

  private boolean mouseIsDown;

  public WeekViewPanel(ReadOnlyPlannerModel model) {
    this.model = Objects.requireNonNull(model);
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    for (Event e : model.mainSchedule()) {
      drawEvent(e, g2d);
    }
    drawlines(g2d);
  }

  private void drawlines(Graphics2D g2d) {
    Rectangle bounds = getBounds();
    int horizontalLineOffset = bounds.height / 23;
    for (int line = horizontalLineOffset; line < bounds.height; line += horizontalLineOffset) {
      if ((line % (horizontalLineOffset * 4)) == 0) {
        g2d.setStroke(new BasicStroke(4));
      } else {
        g2d.setStroke(new BasicStroke(2));
      }
      g2d.setColor(Color.BLACK);
      g2d.drawLine(0, line, bounds.width, line);
    }
    int verticalLineOffset = bounds.width / 7;
    for (int line = verticalLineOffset; line < bounds.width; line += verticalLineOffset) {
      g2d.setColor(Color.BLACK);
      g2d.drawLine(line, 0, line, bounds.height);
    }
  }

  private void drawEvent(Event e, Graphics g) {
    Rectangle bounds = getBounds();
    List<Day> daysOrder = List.of(Day.Sunday,
            Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday,
            Day.Friday, Day.Saturday);
    int verticalLineOffset = bounds.width / 7;
    int horizontalLineOffset = bounds.height / 23;
    int start = (e.observeStartTimeOfEvent() / 100) * horizontalLineOffset;
    int end = bounds.height;
    if (e.observeStartDayOfEvent().equals(e.observeEndDayOfEvent())){
       end = (e.observeEndTimeOfEvent() / 100) * horizontalLineOffset;
     }else {
      drawEndOfEvent(e, g, e.observeStartDayOfEvent());
    }
    g.setColor(Color.RED);
    g.fillRect(daysOrder.indexOf(e.observeStartDayOfEvent()) * verticalLineOffset, start,
            verticalLineOffset, end - start);
}

private void drawEndOfEvent(Event e, Graphics g, Day lastDayDrawn){

}


private class MouseEventsListener extends MouseInputAdapter {
  @Override
  public void mouseClicked(MouseEvent e) {
    WeekViewPanel.this.mouseIsDown = true;
    Point clicked = e.getPoint();
  }

}

}
