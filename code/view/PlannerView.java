package view;

import java.io.IOException;

/**
 * This is the interface for a Planner view.
 */
public interface PlannerView {

  /**
   * Renders the given model.
   *
   * @throws IOException
   */
  void render() throws IOException;


}
