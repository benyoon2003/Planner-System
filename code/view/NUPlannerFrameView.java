package view;

import java.io.IOException;

import javax.swing.*;

public class NUPlannerFrameView extends JFrame implements PlannerView {

  private final JSimonPanel panel;
  public SimpleSimonView(ReadOnlySimon model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JSimonPanel(model);
    this.add(panel);
    this.pack();
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.panel.addFeaturesListener(features);
  }


  @Override
  public void render() throws IOException {

  }
}
