package view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import model.Day;
import model.Event;
import model.NuPlanner;
import model.ReadOnlyPlannerModel;
import model.User;

public class MainScheduleFrameView extends JFrame implements PlannerView {
  private final ReadOnlyPlannerModel model;
  private final JPanel mainPanel;
  private final WeekViewPanel planner;

  private final MainBottomPanel bottom;

  private User selected;

  //private final PlannerPanel bottom;

  /**
   * Creates a default main frame view that displays the schedule of the first
   * User in the database.
   * @param model
   */
  public MainScheduleFrameView(ReadOnlyPlannerModel model) {
    super();
    setTitle("Main System View");
    setSize(800, 800);
    this.model = Objects.requireNonNull(model);
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.selected = this.model.getListOfUser().get(0);
    this.planner = new WeekViewPanel(this.model, this.selected);
    this.bottom = new MainBottomPanel(this.model, this.planner);
    this.mainPanel.add(this.planner);
    this.mainPanel.add(this.bottom);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Creates a main frame view that displays the given user's schedule.
   * @param model
   * @param selected
   */
  public MainScheduleFrameView(ReadOnlyPlannerModel model, User selected) {
    super();
    setTitle("Main System View");
    setSize(800, 800);
    this.model = Objects.requireNonNull(model);
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.selected = selected;
    this.planner = new WeekViewPanel(this.model, this.selected);
    this.bottom = new MainBottomPanel(this.model, this.planner);
    this.mainPanel.add(this.planner);
    this.mainPanel.add(this.bottom);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }



  @Override
  public void render() throws IOException {

  }

  public static void main(String[] args) {
    NuPlanner testModel = new NuPlanner(new ArrayList<>());
    testModel.addUser("Ben");
    testModel.addUser("Nico");
    testModel.createEvent("Ben", "Working on OOD", "Snell", false,
            Day.Monday, 1000, Day.Wednesday, 2055, List.of());
    testModel.createEvent("Nico", "Also working on OOD", "Snell", true,
            Day.Thursday, 500, Day.Saturday, 2000, List.of());
    MainScheduleFrameView frame = new MainScheduleFrameView(testModel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}