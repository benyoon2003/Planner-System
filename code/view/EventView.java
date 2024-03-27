package view;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This interface should represent the functionality of the event frame which is the
 * Event dialogue box. This box is used to edit, modify, or create a new event and
 * allows the user to control their planner system. Implementations of this event frame
 * will connect to the controller to control the model but in this assignment they do not.
 */
public interface EventView {

  /**
   * This method displays the event frame view where the event dialogue can be seen.
   */
  void display();

  /**
   * This method calls system.out to output the event details as specified
   * in the assignment for the user to see their details in the console or
   * some other output.
   */
  void outputEventDetails();

}
