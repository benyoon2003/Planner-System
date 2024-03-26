package view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import model.Day;
import model.NuPlanner;
import model.ReadOnlyPlannerModel;
public class MainScheduleFrameView extends JFrame implements PlannerView {
  private final ReadOnlyPlannerModel model;

  private final JPanel mainPanel;
  private final WeekViewPanel planner;

  private final MainBottomPanel bottom;

  //private final PlannerPanel bottom;
  public MainScheduleFrameView(ReadOnlyPlannerModel model) {
    super();
    setTitle("Main System View");
    setSize(800, 800);
    this.model = Objects.requireNonNull(model);
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.bottom = new MainBottomPanel(model);
    this.add(this.bottom);
    this.planner = new WeekViewPanel(model);
    this.add(this.planner);
    this.pack();
  }


  @Override
  public void render() throws IOException {

  }

  public static void main(String[] args) {
    NuPlanner testModel = new NuPlanner(new ArrayList<>());
    testModel.addUser("Ben");
    testModel.addUser("Nico");
    testModel.createEvent("Ben", "Working on OOD", "Snell", false,
            Day.Monday, 1000, Day.Wednesday, 2055, List.of("Nico"));
    MainScheduleFrameView frame = new MainScheduleFrameView(testModel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}


