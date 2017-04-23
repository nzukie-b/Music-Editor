package cs3500.music.view;

import cs3500.music.model.NoteName;
import cs3500.music.provider.view.GUIViewOperations;
import cs3500.music.provider.view.View;
import cs3500.music.provider.view.ViewModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Adapts the provider's view to work with our controller/model.
 */
public class ViewAdapter implements IMusicView {

  private View view;
  private ModelAdapter ad;
  private boolean first;

  /**
   * Constructor for a ViewAdapter.
   *
   * @param v The view being adapted to work with this code.
   */
  public ViewAdapter(View v) {
    this.view = v;
    first = true;
  }

  @Override
  public void updateView(ModelData data) {
    ModelAdapter adpt = new ModelAdapter(data);
    this.ad = adpt;
    view.setModel(new ViewModel(ad));

    //only want run to be called once.

    if (first) {
      first = !first;
      view.run();
    }
  }

  @Override
  public void setBeat(int beat) {
    if (beat == 0) {
      view.start();
    }
    else if (beat - view.getCurrentBeat() == 1) {
      view.moveRight();
    }
    else if (beat - view.getCurrentBeat() == -1) {
      view.moveLeft();
    }
    else if (beat == ad.getLength() + 1) {
      view.end();
    }
    else {
      throw new IllegalArgumentException("Wrong.");
    }
  }

  @Override
  public void setKeyListener(KeyListener kl) throws IllegalArgumentException {
    view.setKeys(kl);
  }

  @Override
  public void setMouseListener(MouseListener ml) throws IllegalArgumentException {
    view.setMouse(ml);
  }

  @Override
  public int getBeat() {
    return view.getCurrentBeat().intValue();
  }

  @Override
  public void scrollWithMusic() {
    try {
      if (view.getIsPlaying()) {
        view.moveRight();
      }
    }
    catch (NullPointerException e) {
      //
    }
  }

  @Override
  public void toggleMusic() {
    view.play();
  }


  @Override
  public int getMargin() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getMargin();
    }
    return 1;
  }

  @Override
  public int getWhiteLength() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getPianoLength();
    }
    return 1;
  }

  @Override
  public int getBlackLength() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getBlackLength();
    }
    return 1;
  }

  @Override
  public int getScoreHeight() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getScoreHeight();
    }
    return 5;
  }

  @Override
  public int getBlackWidth() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getBlackWidth();
    }
    return 1;
  }

  @Override
  public int getWhiteWidth() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getWhiteWidth();
    }
    return 1;
  }

  @Override
  public int getOffset() {
    if (view instanceof GUIViewOperations) {
      GUIViewOperations v = (GUIViewOperations) view;
      return v.getBlackWhiteOffset();
    }
    return 1;
  }

  @Override
  public void togglePractice() {
    //
  }

  @Override
  public boolean getPractice() {
    return false;
  }

  @Override
  public Set<NoteName> getCurrentNotes() {
    return new HashSet<>();
  }
}
