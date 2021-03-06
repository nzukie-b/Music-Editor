package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * A skeleton for MIDI playback so you can hear the music.
 */
public class MidiViewImpl implements IMusicView {
  private Sequencer seq;
  private Track track;
  private int currBeat;
  private boolean paused;
  private ModelData data;
  private boolean first;

  /**
   * Constructor for the MidiViewImpl.
   */
  public MidiViewImpl() {
    this.currBeat = 0;
    this.paused = false;
    this.first = true;
    try {
      this.seq = MidiSystem.getSequencer();
      Sequence seqnce = null;
      try {
        seqnce = new Sequence(Sequence.PPQ, 10);
        seq.setSequence(seqnce);
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
      track = seqnce.createTrack();
      this.seq.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateView(ModelData data) {
    if (first) {
      this.data = data;
      NoteName[] a = data.getSheetData().keySet()
          .toArray(new NoteName[data.getSheetData().keySet().size()]);
      if (!paused) {
        int tempo = 60000000 / data.getTempo();
        seq.setTempoInBPM(tempo);
        for (int i = currBeat; i < data.getMaxBeat(); i++) {
          for (NoteName nn : a) {
            Set<Note> notes = data.getSheetData().get(nn);
            for (Note n : notes) {
              if (n.isPlaying(i)) {
                try {
                  int instr = n.getInstrument() - 1;
                  int pitch = nn.getValue();
                  int dyn = n.getDynamics();
                  MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instr, pitch, dyn);
                  MidiMessage end = new ShortMessage(ShortMessage.NOTE_OFF, instr, pitch, dyn);
                  track.add(new MidiEvent(start, 10 * n.getStart()));
                  track.add(new MidiEvent(end, 10 * (n.getStart() + n.getDuration())));
                } catch (InvalidMidiDataException e) {
                  e.printStackTrace();
                }
              }
            }
          }
          currBeat = 0;
          this.seq.setTickPosition(currBeat);
        }
      }
      first = !first;
      this.seq.start();
    }
  }

  @Override
  public void setBeat(int beat) {
    seq.setTickPosition(seq.getTickPosition() + (long) (beat * 10 - currBeat));
    this.currBeat = beat * 10;
    seq.setTempoInMPQ(data.getTempo());
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    throw new IllegalArgumentException("No midi KL.");
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    throw new IllegalArgumentException("No midi ML");
  }

  @Override
  public int getBeat() {
    return currBeat / 10;
  }

  @Override
  public void toggleMusic() {

    paused = !paused;
    if (paused) {
      this.seq.stop();
      long val = seq.getTickPosition();
      this.currBeat = Math.toIntExact(val);
    } else {
      this.seq.start();
      int tempo = 60000000 / data.getTempo();
      seq.setTempoInBPM(tempo);
    }
  }

  @Override
  public int getMargin() {
    return 0;
  }

  @Override
  public int getWhiteLength() {
    return 0;
  }

  @Override
  public int getBlackLength() {
    return 0;
  }

  @Override
  public int getScoreHeight() {
    return 0;
  }

  @Override
  public int getBlackWidth() {
    return 0;
  }

  @Override
  public int getWhiteWidth() {
    return 0;
  }

  @Override
  public int getOffset() {
    return 0;
  }

  @Override
  public void scrollWithMusic() {
    //throw new IllegalArgumentException();
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