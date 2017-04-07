import cs3500.music.controller.KeyboardListener;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Created by emily on 4/6/17.
 */
public class KeyboardListenerMock extends KeyboardListener {
  private Map<Integer, Runnable> keyMap;

  KeyboardListenerMock() {
    super();
  }

  @Override
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    this.keyMap = map;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyMap.containsKey(e.getKeyCode())) {
      keyMap.get(e.getKeyCode()).run();
    }
  }

  public Map<Integer, Runnable> getKeyMap() {
    return keyMap;
  }

}
