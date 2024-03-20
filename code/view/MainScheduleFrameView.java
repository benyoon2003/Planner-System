package view;

import java.io.IOException;

import javax.swing.*;

import model.ReadOnlyPlannerModel;
public class MainScheduleFrameView extends JFrame implements PlannerView {
  private final ReadOnlyPlannerModel model;


  public MainScheduleFrameView(ReadOnlyPlannerModel model) {
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

//  @Override
//  public void addFeatureListener(ViewFeatures features) {
//    this.panel.addFeaturesListener(features);
//  }


  @Override
  public void render() throws IOException {

  }
}
