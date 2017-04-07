import java.awt.Component;
import java.awt.event.MouseEvent;

/**
 * Mocks the MouseEvent.
 */
public class MouseEventMock extends MouseEvent {

  private int xClicked;
  private int yClicked;

  /**
   * Constructor for a Mouse Event.
   * @param source not relevant
   * @param id not relevant
   * @param when not relevant
   * @param modifiers not relevant
   * @param x The x position that was clicked.
   * @param y The y position that was clicked.
   * @param xAbs not relevant
   * @param yAbs not relevant
   * @param clickCount not relevant
   * @param popupTrigger not relevant
   * @param button not relevant
   */
  public MouseEventMock(Component source, int id, long when, int modifiers, int x, int y,
      int xAbs, int yAbs, int clickCount, boolean popupTrigger, int button) {
    super(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, button);
    this.xClicked = x;
    this.yClicked = y;
  }

  @Override
  public int getX() {
    return xClicked;
  }

  @Override
  public int getY() {
    return yClicked;
  }
}
