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

  public WeekViewPanel(ReadOnlyPlannerModel model) {
    this.model = Objects.requireNonNull(model);
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




}
