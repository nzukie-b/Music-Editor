import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.music.controller.ClickToNN;
import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelOps;
import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import cs3500.music.model.NoteNameComp;
import cs3500.music.model.Pitch;
import cs3500.music.view.CombinationView;
import cs3500.music.view.ConcreteGuiViewFrame;
import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IMusicView;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.ModelData;
import cs3500.music.view.PianoPanel;
import cs3500.music.view.SheetPanel;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;


/**
 * The class to test console view.
 */
public class MusicViewTest {

  /**
   * Innitial data.
   */
  MusicModelOps mm1 = new MusicModel(4, 4);
  ConcreteGuiViewPanel concPanel = new JPanelMock();
  IMusicView concView = new ConcreteGuiViewFrame(concPanel);
  MusicController mc1 = new MusicController(mm1, concView);

  GuiViewFrame viewFrame =  new GuiViewFrame();
  MidiViewImpl midi = new MidiViewImpl();
  CombinationView combo = new CombinationView(midi, viewFrame);

  NoteName a2 = new NoteName(Pitch.A, 2);
  NoteName cs2 = new NoteName(Pitch.CS, 3);
  NoteName g1 = new NoteName(Pitch.G, 2);
  NoteName fs1 = new NoteName(Pitch.FS, 2);
  NoteName c0 = new NoteName(Pitch.C, 0);
  NoteName ds0 = new NoteName(Pitch.DS, 0);
  NoteName d5 = new NoteName(Pitch.D, 5);

  NoteNameComp nnc = new NoteNameComp();


  Note n1 = new Note(3, 4, 80, 4);
  Note n2 = new Note(2, 8, 80, 4);
  Note n3 = new Note(8, 2,80, 4);

  KeyEvent keLeft = new KeyEvent(new SheetPanel(createModelData(mm1)), 1, 1, 1,
      KeyEvent.VK_LEFT, 'l');
  KeyEvent keRight = new KeyEvent(new SheetPanel(createModelData(mm1)), 1, 1, 1,
      KeyEvent.VK_RIGHT, 'l');
  KeyEvent keHome = new KeyEvent(new SheetPanel(createModelData(mm1)), 1, 1, 1,
      KeyEvent.VK_HOME, 'l');
  KeyEvent keEnd = new KeyEvent(new SheetPanel(createModelData(mm1)), 1, 1, 1,
      KeyEvent.VK_END, 'l');
  KeyEvent keP = new KeyEvent(new SheetPanel(createModelData(mm1)), 1, 1, 1,
      KeyEvent.VK_P, 'l');

  KeyboardListenerMock kl = new KeyboardListenerMock();

  Map<Integer, Runnable> keyPress = new HashMap<>();


  MouseEventMock meWhite = new MouseEventMock(new PianoPanel(createModelData(mm1)),
      1, 1, 1, 210, 200, 1, 1, 1,
      true, 1);
  MouseEventMock meWhiteTop = new MouseEventMock(new PianoPanel(createModelData(mm1)),
      1, 1, 1, 210, 150, 1, 1, 1,
      true, 1);
  MouseEventMock meMiddle = new MouseEventMock(new PianoPanel(createModelData(mm1)),
      1, 1, 1, 930, 232, 1, 1, 1,
      true, 1);
  MouseEventMock meBlack = new MouseEventMock(new PianoPanel(createModelData(mm1)),
      1, 1, 1, 240, 150, 1, 1, 1,
      true, 1);

  ClickToNN ctnn = new ClickToNN();

  /**
   *  Add some note into music model mm for the further tests.
   * @param mm Music Model.
   */
  private void addNote(MusicModelOps mm) {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n1);
    mm.addNote(g1, n2);
    mm.addNote(fs1, n3);
  }

  private ModelData createModelData(MusicModelOps model) {
    return new ModelData(model.printSheet(),
        model.getSheet(),
        model.measureLength(),
        model.maxNote(),
        model.getTempo());
  }



  @Test
  public void testME1() {
    assertEquals(nnc.compare(ctnn.getNN(meWhite), c0), 0);
  }

  @Test
  public void testME2() {
    assertEquals(nnc.compare(ctnn.getNN(meWhiteTop), c0), 0);
  }

  @Test
  public void testME3() {
    assertEquals(nnc.compare(ctnn.getNN(meBlack), ds0), 0);
  }

  @Test
  public void testME4() {
    assertEquals(nnc.compare(ctnn.getNN(meMiddle), d5), 0);
  }

  @Test
  public void testKE1() {
    Map<Integer, Runnable> keyPress = new HashMap<>();

    RunnableMock rightRun = new RunnableMock();
    RunnableMock leftRun = new RunnableMock();
    RunnableMock homeRun = new RunnableMock();
    RunnableMock endRun = new RunnableMock();
    RunnableMock pRun = new RunnableMock();

    keyPress.put(KeyEvent.VK_RIGHT, rightRun);
    keyPress.put(KeyEvent.VK_LEFT, leftRun);
    keyPress.put(KeyEvent.VK_HOME, homeRun);
    keyPress.put(KeyEvent.VK_END, endRun);
    keyPress.put(KeyEvent.VK_P, pRun);

    kl.setKeyPressedMap(keyPress);
    kl.keyPressed(keRight);
    assertEquals(rightRun.getString(), "Run");
  }

  @Test
  public void testKE2() {
    Map<Integer, Runnable> keyPress = new HashMap<>();

    RunnableMock rightRun = new RunnableMock();
    RunnableMock leftRun = new RunnableMock();
    RunnableMock homeRun = new RunnableMock();
    RunnableMock endRun = new RunnableMock();
    RunnableMock pRun = new RunnableMock();

    keyPress.put(KeyEvent.VK_RIGHT, rightRun);
    keyPress.put(KeyEvent.VK_LEFT, leftRun);
    keyPress.put(KeyEvent.VK_HOME, homeRun);
    keyPress.put(KeyEvent.VK_END, endRun);
    keyPress.put(KeyEvent.VK_P, pRun);

    kl.setKeyPressedMap(keyPress);
    kl.keyPressed(keLeft);
    assertEquals(leftRun.getString(), "Run");
  }

  @Test
  public void testKE3() {
    Map<Integer, Runnable> keyPress = new HashMap<>();

    RunnableMock rightRun = new RunnableMock();
    RunnableMock leftRun = new RunnableMock();
    RunnableMock homeRun = new RunnableMock();
    RunnableMock endRun = new RunnableMock();
    RunnableMock pRun = new RunnableMock();

    keyPress.put(KeyEvent.VK_RIGHT, rightRun);
    keyPress.put(KeyEvent.VK_LEFT, leftRun);
    keyPress.put(KeyEvent.VK_HOME, homeRun);
    keyPress.put(KeyEvent.VK_END, endRun);
    keyPress.put(KeyEvent.VK_P, pRun);

    kl.setKeyPressedMap(keyPress);
    kl.keyPressed(keHome);
    assertEquals(homeRun.getString(), "Run");
  }

  @Test
  public void testKE4() {
    Map<Integer, Runnable> keyPress = new HashMap<>();

    RunnableMock rightRun = new RunnableMock();
    RunnableMock leftRun = new RunnableMock();
    RunnableMock homeRun = new RunnableMock();
    RunnableMock endRun = new RunnableMock();
    RunnableMock pRun = new RunnableMock();

    keyPress.put(KeyEvent.VK_RIGHT, rightRun);
    keyPress.put(KeyEvent.VK_LEFT, leftRun);
    keyPress.put(KeyEvent.VK_HOME, homeRun);
    keyPress.put(KeyEvent.VK_END, endRun);
    keyPress.put(KeyEvent.VK_P, pRun);

    kl.setKeyPressedMap(keyPress);
    kl.keyPressed(keEnd);
    assertEquals(endRun.getString(), "Run");
  }

  @Test
  public void testKE5() {
    Map<Integer, Runnable> keyPress = new HashMap<>();

    RunnableMock rightRun = new RunnableMock();
    RunnableMock leftRun = new RunnableMock();
    RunnableMock homeRun = new RunnableMock();
    RunnableMock endRun = new RunnableMock();
    RunnableMock pRun = new RunnableMock();

    keyPress.put(KeyEvent.VK_RIGHT, rightRun);
    keyPress.put(KeyEvent.VK_LEFT, leftRun);
    keyPress.put(KeyEvent.VK_HOME, homeRun);
    keyPress.put(KeyEvent.VK_END, endRun);
    keyPress.put(KeyEvent.VK_P, pRun);

    kl.setKeyPressedMap(keyPress);
    kl.keyPressed(keP);
    assertEquals(pRun.getString(), "Run");
  }

  @Test
  public void vf1() {
    addNote(mm1);
    viewFrame.updateView(createModelData(mm1));
    assertEquals(viewFrame.getBeat(), 0);
  }

  @Test
  public void vf2() {
    addNote(mm1);
    viewFrame.updateView(createModelData(mm1));
    viewFrame.setBeat(3);
    assertEquals(viewFrame.getBeat(), 3);
  }

  @Test
  public void vf3() {
    addNote(mm1);
    combo.updateView(createModelData(mm1));
    assertEquals(combo.getBeat(), 0);
  }

  @Test
  public void vf4() {
    addNote(mm1);
    combo.updateView(createModelData(mm1));
    combo.setBeat(9);
    assertNotEquals(combo.getBeat(), 9);
    combo.toggleMusic();
    combo.setBeat(9);
    assertEquals(combo.getBeat(), 9);
  }
}