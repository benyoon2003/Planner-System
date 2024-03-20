package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import model.NuPlanner;
import model.ReadOnlyPlannerModel;

public class WeekViewPanel extends JPanel implements PlannerPanel {


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
    Rectangle bounds = getBounds();
    int horizontalLineOffset = bounds.height / 23;
    for (int line = horizontalLineOffset; line < bounds.height; line += horizontalLineOffset) {
      if ((line % (horizontalLineOffset * 4)) == 0){
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


  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      WeekViewPanel.this.mouseIsDown = true;
      Point clicked = e.getPoint();
    }

  }

}
