import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.ModelData;
import java.awt.Dimension;

/**
 * The mock to log what is happening.
 */
public class JPanelMock extends ConcreteGuiViewPanel {
  private StringBuilder log = new StringBuilder();

  /**
   * Constructor for the mock
   */
  public JPanelMock() {
    super();
    this.setPreferredSize(new Dimension(1000, 1000));
  }

  @Override
  public void updateView(ModelData data) {
    log.append("\nUpdated view: \n");
    log.append(data.getSheetAsString());
    log.append("\n");
  }

  /**
   * Gets the log as a string.
   *
   * @return the log.
   */
  public String getLog() {
    return log.toString();
  }
}
