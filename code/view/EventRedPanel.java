package view;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import model.Day;
import model.Event;
import model.ReadOnlyPlannerModel;

public class EventRedPanel extends JPanel {


  private Event e;

  private boolean mouseIsDown;

  /**
   * This is package protected because there should not be any leakage of the Event panel
   * information and this should be contained in the view.
   * @param the given event being drawn
   */
  EventRedPanel(Event e) {
    this.e = Objects.requireNonNull(e);
    WeekViewPanel.MouseEventsListener listener = new WeekViewPanel.MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    drawEvent(e, g2d);
  }

  private void drawEvent(Event e, Graphics g) {
    Rectangle bounds = getBounds();
    java.util.List<Day> daysOrder = java.util.List.of(Day.Sunday,
            Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday,
            Day.Friday, Day.Saturday);
    int verticalLineOffset = bounds.width / 7;
    int horizontalLineOffset = bounds.height / 23;
    int start = (e.observeStartTimeOfEvent() / 100) * horizontalLineOffset;
    int end = bounds.height;
    if (e.observeStartDayOfEvent().equals(e.observeEndDayOfEvent())){
      end = (e.observeEndTimeOfEvent() / 100) * horizontalLineOffset;
      g.setColor(Color.RED);
      g.fillRect(daysOrder.indexOf(e.observeStartDayOfEvent()) * verticalLineOffset, start,
              verticalLineOffset, end - start);
    }else {
      g.setColor(Color.RED);
      g.fillRect(daysOrder.indexOf(e.observeStartDayOfEvent()) * verticalLineOffset, start,
              verticalLineOffset, end - start);
      drawEndOfEvent(e, g, e.observeStartDayOfEvent());
    }

  }

  private void drawEndOfEvent(Event e, Graphics g, Day lastDayDrawn){
    java.util.List<Day> daysOrder = List.of(Day.Sunday,
            Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday,
            Day.Friday, Day.Saturday);
    Rectangle bounds = getBounds();
    int verticalLineOffset = bounds.width / 7;
    int horizontalLineOffset = bounds.height / 23;
    if (daysOrder.get(daysOrder.indexOf(lastDayDrawn) + 1).equals(e.observeEndDayOfEvent())){
      int end = (e.observeEndTimeOfEvent() / 100) * horizontalLineOffset;
      g.setColor(Color.RED);
      g.fillRect(daysOrder.indexOf(e.observeEndDayOfEvent()) * verticalLineOffset, 0,
              verticalLineOffset, end);
    }else{
      g.setColor(Color.RED);
      g.fillRect((daysOrder.indexOf(lastDayDrawn) + 1) * verticalLineOffset, 0,
              verticalLineOffset, bounds.height);
      drawEndOfEvent(e, g, daysOrder.get(daysOrder.indexOf(lastDayDrawn) + 1));
    }

  }



}
