package view;

import java.io.IOException;

import javax.swing.*;

import model.ReadOnlyPlannerModel;


public class EventFrameView extends JFrame implements EventView{

  private final EventPanel name;
  private final EventPanel location;
  private final EventPanel startingDay;
  private final EventPanel startingTime;
  private final EventPanel endingDay;
  private final EventPanel endingTime;
  
  public EventFrameView(ReadOnlyPlannerModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JPlannerPanel(model);
    this.add(panel);
    this.pack();
  }
}
