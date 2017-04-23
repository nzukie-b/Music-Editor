package cs3500.music.model;

/**
 * Created by emily on 4/22/17.
 */
public enum Repeat {
  START, END;

  public int getType() {
    return ordinal();
  }
}
