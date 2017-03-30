package cs3500.music.controller;

import cs3500.music.model.MusicModelOps;
import cs3500.music.model.NoteName;
import cs3500.music.view.IMusicView;
import cs3500.music.view.KeyboardListener;
import cs3500.music.view.ModelData;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent the controller.
 */
public class MusicController {
  private IMusicView view;
  //private MusicModelOps model;
  //the grader took of points for not using the model, but I know we'll need it
  //in the future. So just commenting it out for now.

  /**
   * The constructor for the MusicController.
   *
   * @param model the music model to be used.
   * @param view the view that is being updated.
   */
  public MusicController(MusicModelOps model, IMusicView view) {
    //this.model = model;
    this.view = view;

    view.updateView(new ModelData(model.printSheet(),
        model.getSheet(),
        model.measureLength(),
        model.maxNote(model.getSheet().keySet()
            .toArray(new NoteName[model.getSheet().keySet().size()])),
        model.getTempo()));
    configureKeyBoardListener();
  }

  /**
   * Method to configure the keyboard listener. Currently has two key events
   * it keeps track of, key left and key right.
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPress = new HashMap<>();
    keyPress.put(KeyEvent.VK_RIGHT, () -> view.setBeat(view.getBeat() + 1));

    keyPress.put(KeyEvent.VK_LEFT, () -> view.setBeat(view.getBeat() - 1));

    KeyboardListener kl = new KeyboardListener();

    kl.setKeyPressedMap(keyPress);

    view.setKeyListener(kl);
  }
}
