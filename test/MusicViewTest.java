import static org.junit.Assert.assertEquals;

import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelOps;
import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import cs3500.music.model.Pitch;
import cs3500.music.view.ConcreteGuiViewFrame;
import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.IMusicView;
import cs3500.music.view.ModelData;
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

  NoteName a2 = new NoteName(Pitch.A, 2);
  NoteName cs2 = new NoteName(Pitch.CS, 3);
  NoteName g1 = new NoteName(Pitch.G, 2);
  NoteName f10 = new NoteName(Pitch.F, 11);
  NoteName b10 = new NoteName(Pitch.B, 10);
  NoteName b2 = new NoteName(Pitch.B, 2);
  NoteName d2 = new NoteName(Pitch.D, 3);
  NoteName fs1 = new NoteName(Pitch.FS, 2);
  NoteName fs10 = new NoteName(Pitch.FS, 11);
  NoteName gs3 = new NoteName(Pitch.GS, 2);

  Note n1 = new Note(3, 4, 80, 4);
  Note n2 = new Note(2, 8, 80, 4);
  Note n3 = new Note(8, 2,80, 4);

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
  public void console1() {
    assertEquals(concPanel.getSheet(),
        "");
  }

  @Test
  public void console2() {
    addNote(mm1);
    concView.updateView(createModelData(mm1));
    assertEquals(concPanel.getSheet(), "");
  }

  @Test
  public void midi1() {

  }
}