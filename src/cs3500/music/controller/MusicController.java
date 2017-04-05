package cs3500.music.controller;

import cs3500.music.model.MusicModelOps;
import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import cs3500.music.view.ClickToNN;
import cs3500.music.view.IMusicView;
import cs3500.music.view.KeyboardListener;
import cs3500.music.view.ModelData;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import sun.plugin2.jvm.CircularByteBuffer.Streamer;

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

    keyPress.put(KeyEvent.VK_HOME, new changeBeat(0));

    keyPress.put(KeyEvent.VK_END, new changeBeat(model.maxNote()));

    KeyboardListener kl = new KeyboardListener();

    kl.setKeyPressedMap(keyPress);

    view.setKeyListener(kl);
  }

  private void configureMouseListener() {
    Map<Integer, ClickToNN> mouseClick = new HashMap<>();
    mouseClick.put(MouseEvent.BUTTON1, new ClickToNN());

    MouseBoardListener ml = new MouseBoardListener();

    ml.setMouseMap(mouseClick);
    view.setMouseListener(ml);
  }

  private class changeBeat implements Runnable {
    private int beat;
    changeBeat(int beat) {
      this.beat = beat;
    }

    public void run() {
      view.setBeat(beat);
      view.updateView(new ModelData(model.printSheet(),
          model.getSheet(),
          model.measureLength(),
          model.maxNote(),
          model.getTempo()));
    }

  }

  private class MouseBoardListener implements MouseListener {
    private Map<Integer, ClickToNN> mouseMap;

    /**
     * The constructor for the KeyboardListener.
     */
    public MouseBoardListener() {
    }

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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }
}
