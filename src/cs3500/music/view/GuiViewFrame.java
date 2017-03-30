package cs3500.music.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * A skeleton Frame (i.e., a window) in Swing, used to draw the music on a "staff"
 * and on a piano.
 */
public class GuiViewFrame extends javax.swing.JFrame implements IMusicView {


  private final SheetPanel shtPanel;
  private final PianoPanel pianoPanel;

  /**
   * Creates new GuiView.
   */
  public GuiViewFrame() {
    super();
    this.setTitle("MusicEditor");
    this.setPreferredSize(new Dimension(1000, 1000));

    ModelData data = new ModelData("", new TreeMap<>(), 4, 0, 60);
    this.shtPanel = new SheetPanel(data);
    this.pianoPanel = new PianoPanel(data);

    shtPanel.setBackground(Color.white);
    pianoPanel.setBackground(Color.gray);

    JScrollPane scrollBar = new JScrollPane(shtPanel);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().add(shtPanel);

    this.add(shtPanel, BorderLayout.NORTH);
    this.getContentPane().add(scrollBar);
    this.add(pianoPanel, BorderLayout.CENTER);
    this.setSize(getPreferredSize());

    this.setVisible(true);
    this.setFocusable(true);
    this.pack();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(Math.max(500, shtPanel.getWidth()), 500 + shtPanel.getHeight());
  }

  @Override
  public void updateView(ModelData modelData) {
    shtPanel.updateSheet(modelData);
    pianoPanel.updateSheet(modelData);
  }

  @Override
  public void setBeat(int beat) {
    shtPanel.updateBeat(beat);
    pianoPanel.updateBeat(beat);
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    shtPanel.addKeyListener(kl);
    pianoPanel.addKeyListener(kl);
    this.addKeyListener(kl);
  }

  @Override
  public int getBeat() {
    return shtPanel.getBeat();
  }
}
