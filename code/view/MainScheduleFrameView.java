package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import model.Day;
import model.Event;
import model.NuPlanner;
import model.ReadOnlyPlannerModel;
import model.User;

public class MainScheduleFrameView extends JFrame implements PlannerView {
  private final ReadOnlyPlannerModel model;
  private JPanel mainPanel;
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
    this.bottom = new MainBottomPanel(this.model, this.planner, this);
    this.mainPanel.add(this.planner);
    this.mainPanel.add(this.bottom);
    this.add(mainPanel);
    makeFileChooser();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

  private void makeFileChooser(){
    JMenuBar mb = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem add = new JMenuItem("Add Calendar");
    JMenuItem save = new JMenuItem("Save Calendar");
    menu.add(add);menu.add(save);
    mb.add(menu);
    this.setJMenuBar(mb);
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser j = new JFileChooser();
        j.showSaveDialog(null);
        String path = j.getSelectedFile().getPath();
        System.out.println(path);
      }
    });
  }


  public void reMakeView(User selected) {
    this.getContentPane().removeAll();
    this.mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(new WeekViewPanel(this.model, selected));
    this.mainPanel.add(this.bottom);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void render() throws IOException {

  }

  public static void main(String[] args) {
    NuPlanner testModel = new NuPlanner(new ArrayList<>());
    testModel.addUser("Ben");
    testModel.addUser("Nico");
    testModel.addUser("Lucia");
    testModel.createEvent("Ben", "Working on OOD", "Snell", false,
            Day.Monday, 1000, Day.Wednesday, 2055, List.of());
    testModel.createEvent("Nico", "Also working on OOD", "Snell", true,
            Day.Thursday, 500, Day.Saturday, 2000, List.of());
    testModel.createEvent("Lucia", "Also working on OOD", "Meserve", true,
            Day.Sunday, 500, Day.Sunday, 900, List.of("Ben", "Nico"));
    new MainScheduleFrameView(testModel);

  }
}