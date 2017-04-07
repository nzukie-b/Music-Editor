package cs3500.music.controller;

import cs3500.music.model.MusicModelOps;
import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ModelData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

/**
 * Class to represent the controller.
 */
public class MusicController {
  private IMusicView view;
  private MusicModelOps model;

  /**
   * The constructor for the MusicController.
   *
   * @param model the music model to be used.
   * @param view the view that is being updated.
   */
  public MusicController(MusicModelOps model, IMusicView view) {
    this.model = model;
    this.view = view;

    Timer timer = new Timer(model.getTempo() / 1000, new KeepTime());
    timer.start();

    view.updateView(new ModelData(model.printSheet(),
        model.getSheet(),
        model.measureLength(),
        model.maxNote(),
        model.getTempo()));
    try {
      configureKeyBoardListener();
      configureMouseListener();
    }
    catch (IllegalArgumentException e) {
      //
    }
  }


  /**
   * Method to configure the keyboard listener. Currently has two key events
   * it keeps track of, key left and key right.
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPress = new HashMap<>();
    keyPress.put(KeyEvent.VK_RIGHT, () -> view.setBeat(view.getBeat() + 1));

    keyPress.put(KeyEvent.VK_LEFT, () -> view.setBeat(view.getBeat() - 1));

    keyPress.put(KeyEvent.VK_P, () -> view.toggleMusic());

    keyPress.put(KeyEvent.VK_HOME, new ChangeBeat(0));

    keyPress.put(KeyEvent.VK_END, new ChangeBeat(model.maxNote()));

    KeyboardListener kl = new KeyboardListener();

    kl.setKeyPressedMap(keyPress);

    try {
      view.setKeyListener(kl);
    }
    catch (IllegalArgumentException e) {
      //
    }
  }

  /**
   * Configues the mouse listener to properly convert a mouse click to a note name.
   */
  private void configureMouseListener() {
    Map<Integer, ClickToNN> mouseClick = new HashMap<>();
    mouseClick.put(MouseEvent.BUTTON1, new ClickToNN());

    MouseBoardListener ml = new MouseBoardListener();

    ml.setMouseMap(mouseClick);
    try {
      view.setMouseListener(ml);
    }
    catch (IllegalArgumentException e) {
      //
    }
  }

  /**
   * Private class that updates the beat when Home or End are pressed. Updates the
   * view once the beat has been changed.
   */
  private class ChangeBeat implements Runnable {
    private int beat;

    /**
     * Constructor for a ChangeBeat.
     * @param beat the beat that the view is being updated to reflect.
     */
    ChangeBeat(int beat) {
      this.beat = beat;
    }

    /**
     * Sets the view's beat to the beat that was given and updates the view.
     */
    public void run() {
      try {
        view.setBeat(beat);
        view.updateView(new ModelData(model.printSheet(),
            model.getSheet(),
            model.measureLength(),
            model.maxNote(),
            model.getTempo()));
      }
      catch (IllegalArgumentException e) {
        //
      }
    }

  }

  /**
   * Private class to keep the gui view in sync with the midi view in a combination view.
   */
  private class KeepTime implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        view.scrollWithMusic();
      }
      catch (Exception exc) {
        exc.printStackTrace();
      }
    }
  }

  /**
   * Private class to map the mouse event to the ClickToNN class, where the click is
   * converted into a NoteName.
   */
  private class MouseBoardListener implements MouseListener {
    private Map<Integer, ClickToNN> mouseMap;

    /**
     * The constructor for the KeyboardListener.
     */
    public MouseBoardListener() {
      //Doesn't need anything
    }

    /**
     * Sets the map to be equal to the given one.
     * @param mouseMap the map to be used.
     */
    public void setMouseMap(Map<Integer, ClickToNN> mouseMap) {
      this.mouseMap = mouseMap;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
      if (mouseMap.containsKey(e.getButton())) {
        NoteName nn;
        try {
          nn = mouseMap.get(e.getButton()).getNN(e);
          model.addNote(nn, new Note(view.getBeat(), 1, 80, 1));
          view.setBeat(view.getBeat() + 1);
          view.updateView(new ModelData(model.printSheet(),
              model.getSheet(),
              model.measureLength(),
              model.maxNote(),
              model.getTempo()));
        }
        catch (IllegalArgumentException exc) {
          //don't want to do anything
        }
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      //doesn't do anything
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //doesn't do anything
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //doesn't do anything
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //doesn't do anything
    }
  }
}
