package view;

import java.io.IOException;

/**
 * This is the interface for a Planner view, which allows the user to view the schedule and
 * "modify" any available user's schedule in the model's database.
 */
public interface PlannerView {

  /**
   * Renders the given model.
   *
   * @throws IOException if render fails for some reason
   */
  void render() throws IOException;


}
