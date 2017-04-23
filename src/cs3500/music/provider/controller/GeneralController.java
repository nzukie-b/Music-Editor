package cs3500.music.provider.controller;

import cs3500.music.provider.model.MusicEditorOperations;
import cs3500.music.provider.view.View;
import cs3500.music.provider.view.ViewModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


/**
 * A general controller with basic functionality needed for any view.
 */
public class GeneralController implements ControllerOperations {

  View view;
  MusicEditorOperations model;

  public GeneralController(View view, MusicEditorOperations model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void run() {
    view.setModel(new ViewModel(model));
    view.run();
  }
  
  /**
   * Configures the keyboard controls for the handling of user input.
   */
  private void configureKeyboard() {
    Map<Integer, Runnable> pressed = new HashMap<>();

    pressed.put(KeyEvent.VK_LEFT, () -> view.moveLeft());
    pressed.put(KeyEvent.VK_RIGHT, () -> view.moveRight());
    pressed.put(KeyEvent.VK_SPACE, () -> view.play());
    pressed.put(KeyEvent.VK_HOME, () -> view.start());
    pressed.put(KeyEvent.VK_END, () -> view.end());

    KeyListener keyboard = new Keyboard(new HashMap<>(), pressed, new HashMap<>());
    view.setKeys(keyboard);
  }
}
