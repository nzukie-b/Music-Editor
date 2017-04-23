package cs3500.music.controller;

import cs3500.music.model.MusicModelOps;
import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import cs3500.music.model.Pitch;
import cs3500.music.model.Repeat;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ModelData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.Timer;

/**
 * Class to represent the controller.
 */
public class MusicController {
  private IMusicView view;
  private MusicModelOps model;
  private Timer timer;
  private Set<NoteName> notes;
  private int oct;
  private Set<Integer> repeatStarts;
  private  Set<Integer> repeatEnds;

  /**
   * The constructor for the MusicController.
   *
   * @param model the music model to be used.
   * @param view the view that is being updated.
   */
  public MusicController(MusicModelOps model, IMusicView view) {
    this.model = model;
    this.view = view;
    this.oct = 0;
    this.notes = new HashSet<>();
    this.repeatEnds = new TreeSet<>();
    this.repeatStarts = new TreeSet<>();
    setRepeats();


    this.timer = new Timer(model.getTempo() / 1000, new KeepTime());
    timer.start();

    view.updateView(new ModelData(model.printSheet(),
        model.getSheet(),
        model.measureLength(),
        model.maxNote(),
        model.getTempo(),
        model.getRepeat()));
    try {
      configureKeyBoardListener();
      configureMouseListener();
    }
    catch (IllegalArgumentException e) {
      //
    }
  }

  private void setRepeats() {
    repeatStarts = new TreeSet<>(model.getRepeat().get(Repeat.START));
    repeatEnds = new TreeSet<>(model.getRepeat().get(Repeat.END));
    if (repeatEnds.size() > repeatStarts.size()) {
      repeatStarts.add(0);
    }

    if (repeatStarts.size() > repeatEnds.size()) {
      repeatStarts.remove(0);
    }
  }


  /**
   * Method to configure the keyboard listener. Currently has two key events
   * it keeps track of, key left and key right.
   */
  private void configureKeyBoardListener() {
    Map<Integer, Runnable> keyPress = new HashMap<>();
    keyPress.put(KeyEvent.VK_RIGHT, () ->
      {if (!view.getPractice() && (!timer.isRunning())) {
        view.setBeat(view.getBeat() + 1);}
      });

    keyPress.put(KeyEvent.VK_LEFT, () ->
      {if (!view.getPractice() && (!timer.isRunning())) {
        view.setBeat(view.getBeat() - 1);}
      });

    keyPress.put(KeyEvent.VK_P, new PauseMusic());

    keyPress.put(KeyEvent.VK_HOME, new ChangeBeat(0));

    keyPress.put(KeyEvent.VK_END, new ChangeBeat(model.maxNote()));

    keyPress.put(KeyEvent.VK_ESCAPE, new PracticeMode(true));

    keyPress.put(KeyEvent.VK_0, () -> oct = 0);

    keyPress.put(KeyEvent.VK_1, () -> oct = 1);

    keyPress.put(KeyEvent.VK_2, () -> oct = 2);

    keyPress.put(KeyEvent.VK_3, () -> oct = 3);

    keyPress.put(KeyEvent.VK_4, () -> oct = 4);

    keyPress.put(KeyEvent.VK_5, () -> oct = 5);

    keyPress.put(KeyEvent.VK_6, () -> oct = 6);

    keyPress.put(KeyEvent.VK_7, () -> oct = 7);

    keyPress.put(KeyEvent.VK_8, () -> oct = 8);

    keyPress.put(KeyEvent.VK_9, () -> oct = 9);

    keyPress.put(KeyEvent.VK_OPEN_BRACKET, new addRepeat(true));

    keyPress.put(KeyEvent.VK_CLOSE_BRACKET, new addRepeat(false));

    keyPress.put(KeyEvent.VK_A, new addNote(new NoteName(Pitch.C, oct)));

    keyPress.put(KeyEvent.VK_S, new addNote(new NoteName(Pitch.D, oct)));

    keyPress.put(KeyEvent.VK_D, new addNote(new NoteName(Pitch.E, oct)));

    keyPress.put(KeyEvent.VK_F, new addNote(new NoteName(Pitch.F, oct)));

    keyPress.put(KeyEvent.VK_G, new addNote(new NoteName(Pitch.G, oct)));

    keyPress.put(KeyEvent.VK_H, new addNote(new NoteName(Pitch.A, oct)));

    keyPress.put(KeyEvent.VK_J, new addNote(new NoteName(Pitch.B, oct)));

    keyPress.put(KeyEvent.VK_W, new addNote(new NoteName(Pitch.CS, oct)));

    keyPress.put(KeyEvent.VK_E, new addNote(new NoteName(Pitch.DS, oct)));

    keyPress.put(KeyEvent.VK_T, new addNote(new NoteName(Pitch.FS, oct)));

    keyPress.put(KeyEvent.VK_Y, new addNote(new NoteName(Pitch.GS, oct)));

    keyPress.put(KeyEvent.VK_U, new addNote(new NoteName(Pitch.AS, oct)));

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

  private class addRepeat implements Runnable {

    boolean isStart;

    public addRepeat(boolean isStart) {
      this.isStart = isStart;
    }

    public void run() {
      int beat = view.getBeat();
      Integer[] startArray = repeatStarts.toArray(new Integer[repeatStarts.size()]);
      Integer[] endArray = repeatEnds.toArray(new Integer[repeatEnds.size()]);

      if (!timer.isRunning()) {
        for (int i = 0; i < repeatEnds.size(); i++) {
          if (beat <= endArray[i] && beat >= startArray[i]) {
            if ((isStart) && !(repeatEnds.size() == 0)) {
              model.addRepeat(Repeat.START, beat);
            } else if (!isStart) {
              model.addRepeat(Repeat.END, beat);
            }
          }
        }

        if (repeatEnds.size() == 0 && repeatStarts.size() == 0) {
          if (!isStart) {
            model.addRepeat(Repeat.END, beat);
          }
        }
      }
      view.updateView(new ModelData(model.printSheet(),
          model.getSheet(),
          model.measureLength(),
          model.maxNote(),
          model.getTempo(),
          model.getRepeat()));
      setRepeats();
    }
  }



  private class addNote implements Runnable {
    NoteName noteName;
    addNote(NoteName nn) {
      noteName = nn;
    }

    public void run() {
      NoteName nn = new NoteName(noteName.getValue() + 12 * oct);
      Set<NoteName> viewNotes = view.getCurrentNotes();
      if (viewNotes.contains(nn)) {
        notes.add(nn);
      }
      new PracticeMode(false).run();
    }
  }

  private class PracticeMode implements Runnable {
    private boolean toggle;
    public PracticeMode(boolean toggle) {
      this.toggle = toggle;
    }

    public void run() {
      if (toggle) {
        view.togglePractice();
        notes.clear();
      }
      if (timer.isRunning()) {
        timer.stop();
        view.toggleMusic();
      }

      if (view.getPractice()) {
        Set<NoteName> viewNotes = view.getCurrentNotes();
        boolean hasAllNotes = notes.containsAll(viewNotes);

        if (hasAllNotes) {
          notes.clear();
          view.setBeat(view.getBeat() + 1);
          view.toggleMusic();
          try {
            Thread.sleep(model.getTempo() / 1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          view.toggleMusic();
        }
      }
    }
  }

  /**
   * Class added in Assignment 8 to pause the music. Stops/starts the timer as necessary when the
   * music is played/paused.
   */
  private class PauseMusic implements Runnable {

    /**
     * Stops the music if the timer is running, starts it otherwise. Has the view reflect this.
     */
    public void run() {
      if (!view.getPractice()) {
        view.toggleMusic();
        if (timer.isRunning()) {
          timer.stop();
        } else {
          timer.start();
        }
      }
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
      if (!timer.isRunning()) {
        try {
          view.setBeat(beat);
          view.updateView(new ModelData(model.printSheet(),
              model.getSheet(),
              model.measureLength(),
              model.maxNote(),
              model.getTempo(),
              model.getRepeat()));
        } catch (Exception e) {
          //
        }
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
      } catch (Exception exc) {
        exc.printStackTrace();
      }

      int smallStart;
      try {
        Integer[] startArray = repeatStarts.toArray(new Integer[repeatStarts.size()]);
        smallStart = startArray[0];
      } catch (ArrayIndexOutOfBoundsException es) {
        smallStart = 0;
      }
      for (Integer i : repeatEnds) {
        if (i == view.getBeat()) {
          view.setBeat(smallStart);
          repeatStarts.remove(smallStart);
          repeatEnds.remove(i);
        }
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

  /**
   * Converts the coordinates of a mouse click to a NoteName.
   */
  private class ClickToNN {

    /**
     * Converts a mouse event to a the corresponding pitch and octave that were pressed
     * on the piano.
     * Moved to be inside the Controller for Assignment 8 so it had access to the views.
     * Changed to use static values taken from the views instead of having them hard-coded in.
     *
     * @param me Mouse Event
     * @return the NoteName that was clicked on.
     */
    public NoteName getNN(MouseEvent me) {
      int x = me.getX();
      int y = me.getY() - view.getScoreHeight();

      int pitch = ((x - view.getMargin()) / view.getWhiteWidth()) % 7;
      int p2 = (x - view.getMargin()) % (7 * view.getWhiteWidth());
      int oct = (x - view.getMargin()) / (7 * view.getWhiteWidth());
      int offset = view.getOffset();


      if (y > view.getBlackLength() && y < view.getWhiteLength()) {
        return getWhiteKey(pitch, oct);
      }

      else if (y < view.getBlackLength()) {
        if (p2 > offset && p2 < offset + view.getBlackWidth()) {
          return new NoteName(Pitch.CS, oct);
        }
        else if (p2 > (view.getWhiteWidth() + offset)
            && p2 < (view.getWhiteWidth() + offset + view.getBlackWidth())) {
          return new NoteName(Pitch.DS, oct);
        }
        else if (p2 > ((view.getWhiteWidth() * 3) + offset)
            && p2 < ((view.getWhiteWidth() * 3) + offset + view.getBlackWidth())) {
          return new NoteName(Pitch.FS, oct);
        }
        else if (p2 > ((view.getWhiteWidth() * 4) + offset)
            && p2 < ((view.getWhiteWidth() * 4) + offset + view.getBlackWidth())) {
          return new NoteName(Pitch.GS, oct);
        }
        else if (p2 > ((view.getWhiteWidth() * 5) + offset)
            && p2 < ((view.getWhiteWidth() * 5) + offset + view.getBlackWidth())) {
          return new NoteName(Pitch.AS, oct);
        }
        else {
          return getWhiteKey(pitch, oct);
        }
      }
      throw new IllegalArgumentException();
    }

    /**
     * Converts the pitch and the octave to the proper NoteName for white keys.
     *
     * @param pitch the pitch of the NoteName
     * @param oct the octave of the NoteName
     * @return the proper NoteName
     */
    private NoteName getWhiteKey(int pitch, int oct) {
      switch (pitch) {
        case 0:
          return new NoteName(Pitch.C, oct);
        case 1:
          return new NoteName(Pitch.D, oct);
        case 2:
          return new NoteName(Pitch.E, oct);
        case 3:
          return new NoteName(Pitch.F, oct);
        case 4:
          return new NoteName(Pitch.G, oct);
        case 5:
          return new NoteName(Pitch.A, oct);
        case 6:
          return new NoteName(Pitch.B, oct);
        default:
          throw new IllegalArgumentException();
      }
    }
  }
}
