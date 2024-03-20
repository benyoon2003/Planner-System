package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

import model.NuPlanner;
import model.ReadOnlyPlannerModel;
public class MainScheduleFrameView extends JFrame implements PlannerView {
  private final ReadOnlyPlannerModel model;

  private final JPanel mainPanel;
  private final PlannerPanel planner;

  //private final PlannerPanel bottom;
  public MainScheduleFrameView(ReadOnlyPlannerModel model) {
    super();
    setTitle("Main System View");
    setSize(800, 800);
    this.model = Objects.requireNonNull(model);
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.planner = new WeekViewPanel(model);

  }




  @Override
  public void render() throws IOException {

  }

  public static void main(String[] args) {
    ReadOnlyPlannerModel testModel = new NuPlanner(new ArrayList<>());
    MainScheduleFrameView frame = new MainScheduleFrameView(testModel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}


