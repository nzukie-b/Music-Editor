package cs3500.music.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import java.awt.event.MouseListener;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


/**
 * A skeleton Frame (i.e., a window) in Swing, used to draw the music on a "staff"
 * and on a piano.
 */
public class GuiViewFrame extends javax.swing.JFrame implements IMusicView {


  private final SheetPanel shtPanel;
  private final PianoPanel pianoPanel;
  private boolean paused;
  private JScrollPane scrollPane;

  /**
   * Creates new GuiView.
   */
  public GuiViewFrame() {
    super();
    this.setTitle("MusicEditor");
    this.setSize(new Dimension(1802, 800));
    this.paused = false;

    ModelData data = new ModelData("", new TreeMap<>(), 4, 0, 60);
    this.shtPanel = new SheetPanel(data);
    this.pianoPanel = new PianoPanel(data);

    shtPanel.setBackground(Color.WHITE);
    pianoPanel.setBackground(Color.GRAY);

    this.scrollPane = new JScrollPane(shtPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPane);

    this.setLayout(new BorderLayout());
    this.add(scrollPane, BorderLayout.NORTH);
    this.add(pianoPanel, BorderLayout.CENTER);

    this.setVisible(true);
    this.setFocusable(true);
    this.setResizable(false);
  }

  @Override
  public void updateView(ModelData modelData) {
    shtPanel.updateSheet(modelData);
    pianoPanel.updateSheet(modelData);
    scrollPane.getHorizontalScrollBar().setMaximum(modelData.getMaxBeat() * 40 + 82);
    if (shtPanel.getBeat() == modelData.getMaxBeat()) {
      scrollPane.getHorizontalScrollBar().setValue(shtPanel.getBeat() * 40);
    }
  }

  @Override
  public void setBeat(int beat) {
    int prevBeat = shtPanel.getBeat();
    shtPanel.updateBeat(beat);
    pianoPanel.updateBeat(beat);
    if (shtPanel.getBeat() % 40 == 0) {
      if (prevBeat < beat) {
        scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar()
            .getValue() + 1600);
      }
      else {
        scrollPane.getHorizontalScrollBar().setValue((shtPanel.getBeat() - 1) * 40 - 1600);
      }
    }
    repaint();
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    shtPanel.addKeyListener(kl);
    pianoPanel.addKeyListener(kl);
    this.addKeyListener(kl);
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    pianoPanel.addMouseListener(ml);
  }

  @Override
  public int getBeat() {
    return shtPanel.getBeat();
  }

  @Override
  public void toggleMusic() {
    paused = !paused;
  }

  @Override
  public void scrollWithMusic() {
    //throw new IllegalArgumentException();
  }
}
