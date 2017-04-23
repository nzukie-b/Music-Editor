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
   * Combination view added in assignment 7 so that when "combo" is passed in a
   * combination view is created.
   * Returns view adaptor if using the provider's view.
   *
   * @param str the type of view to be created.
   * @return a different kind of view.
   */
  public static IMusicView create(String str, String viewType) {
    if (viewType.equals("provider")) {
      cs3500.music.provider.view.ViewFactory vf = new cs3500.music.provider.view.ViewFactory();
      if (str.equals("combo")) {
        return new ViewAdapter(vf.create("composite"));
      }
      return new ViewAdapter(vf.create(str));
    }

    if (viewType.equals("normal")) {
      if (str.equals("visual")) {
        return new GuiViewFrame();
      }
      if (str.equals("console")) {
        return new ConcreteGuiViewFrame();
      }
      if (str.equals("midi")) {
        return new MidiViewImpl();
      }
      if (str.equals("combo")) {
        return new CombinationView(new MidiViewImpl(), new GuiViewFrame());
      }
      else {
        throw new IllegalArgumentException("Invalid music type.");
      }
    }
    else {
      throw new IllegalArgumentException("Not a valid view type. ");
    }
  }
}
