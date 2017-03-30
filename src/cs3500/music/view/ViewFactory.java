package cs3500.music.view;

/**
 * Class to create a view based on what is typed into the console.
 */
public class ViewFactory {

  /**
   * Constructor for the ViewFactory.
   */
  public ViewFactory() {
    //nothing to initialize
  }

  /**
   * Creates a view based on string that is typed. Can create a visual view - the
   * GUI with the "staff" and the piano, a console view - the notes rendered as
   * text, and midi - plays the notes so you can hear the music.
   *
   * @param str the type of view to be created.
   * @return a different kind of view.
   */
  public static IMusicView create(String str) {
    if (str.equals("visual")) {
      return new GuiViewFrame();
    }
    if (str.equals("console")) {
      return new ConcreteGuiViewFrame();
    }
    if (str.equals("midi")) {
      return new MidiViewImpl();
    }
    else {
      throw new IllegalArgumentException("Not a valid music type. ");
    }
  }
}
