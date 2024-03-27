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


  private ReadOnlyPlannerModel model;
  private User selected;

  private Rectangle bounds;

  public WeekViewPanel(ReadOnlyPlannerModel model, User selected) {
    this.model = Objects.requireNonNull(model);
    this.selected = selected;
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    super.paintComponent(g2d);
    this.bounds = getBounds();
    setSize(this.bounds.width,this.bounds.height);
    //drawLines(g2d);
    for (Event e : model.selectSchedule(this.selected.toString())) {
      drawEvent(e, g2d);
    }
    drawLines(g2d);
  }

  private void drawLines(Graphics2D g2d) {
    int  horizontalLineOffset = this.bounds.height / 23;
    for (int line = horizontalLineOffset; line < this.bounds.height;
         line += horizontalLineOffset) {
      if ((line % (horizontalLineOffset * 4)) == 0) {
        g2d.setStroke(new BasicStroke(4));
      } else {
        g2d.setStroke(new BasicStroke(2));
      }
      g2d.setColor(Color.BLACK);
      g2d.drawLine(0, line, this.bounds.width, line);
    }
    int verticalLineOffset = this.bounds.width / 7;
    for (int line = verticalLineOffset; line < this.bounds.width; line += verticalLineOffset) {
      g2d.setColor(Color.BLACK);
      g2d.drawLine(line, 0, line, this.bounds.height);
    }
  }

  private void drawEvent(Event e, Graphics2D g) {
    java.util.List<Day> daysOrder = java.util.List.of(Day.Sunday,
            Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday,
            Day.Friday, Day.Saturday);
    int verticalLineOffset = this.bounds.width / 7;
    int horizontalLineOffset = this.bounds.height / 23;
    int start = (e.observeStartTimeOfEvent() / 100) * horizontalLineOffset;
    int end = this.bounds.height;
    if (e.observeStartDayOfEvent().equals(e.observeEndDayOfEvent())){
      end = (e.observeEndTimeOfEvent() / 100) * horizontalLineOffset;
    }else {
      drawEndOfEvent(e, g, e.observeStartDayOfEvent());
    }
    this.add(new EventRedPanel(e,
            daysOrder.indexOf(e.observeStartDayOfEvent()) * verticalLineOffset, start,
            verticalLineOffset, end - start));

  }

  private void drawEndOfEvent(Event e, Graphics g, Day lastDayDrawn){
    java.util.List<Day> daysOrder = List.of(Day.Sunday,
            Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday,
            Day.Friday, Day.Saturday);
    int verticalLineOffset = this.bounds.width / 7;
    int horizontalLineOffset = this.bounds.height / 23;
    if (daysOrder.get(daysOrder.indexOf(lastDayDrawn) + 1).equals(e.observeEndDayOfEvent())){
      int end = (e.observeEndTimeOfEvent() / 100) * horizontalLineOffset;
      this.add(new EventRedPanel(e,
              daysOrder.indexOf(e.observeEndDayOfEvent()) * verticalLineOffset, 0,
              verticalLineOffset, end));
    }else{
      this.add(new EventRedPanel(e,
              (daysOrder.indexOf(lastDayDrawn) + 1) * verticalLineOffset, 0,
              verticalLineOffset, this.bounds.height));
      drawEndOfEvent(e, g, daysOrder.get(daysOrder.indexOf(lastDayDrawn) + 1));
    }
  }




}