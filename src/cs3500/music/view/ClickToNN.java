package cs3500.music.view;

import cs3500.music.model.NoteName;
import cs3500.music.model.Pitch;
import java.awt.event.MouseEvent;

/**
 * Converts the coordinates of a mouse click to a NoteName.
 */
public class ClickToNN {

  public ClickToNN() {
  }

  public NoteName getNN(MouseEvent me) {
    int x = me.getX();
    int y = me.getY();

    int pitch = ((x - 30) / 20) % 7;
    int p2 = (x - 30) % 140;
    int oct = (x - 30) / 140;
    Pitch p;


    if (y > 180 && y < 300) {
      return getWhiteKey(pitch, oct);
    }

    else if (y < 180) {
      if (p2 > 12 && p2 < 22) {
        return new NoteName(Pitch.CS, oct);
      }
      else if (p2 > 32 && p2 < 42) {
        return new NoteName(Pitch.DS, oct);
      }
      else if (p2 > 72 && p2 < 82) {
        return new NoteName(Pitch.FS, oct);
      }
      else if (p2 > 92 && p2 < 102) {
        return new NoteName(Pitch.GS, oct);
      }
      else if (p2 > 112 && p2 < 122) {
        return new NoteName(Pitch.AS, oct);
      }
      else {
        return getWhiteKey(pitch, oct);
      }
    }
    throw new IllegalArgumentException();
  }

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