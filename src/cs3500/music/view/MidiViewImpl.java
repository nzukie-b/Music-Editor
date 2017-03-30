package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import java.awt.event.KeyListener;
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

  /**
   * Constructor for the MidiViewImpl.
   */
  public MidiViewImpl() {
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

  public MidiViewImpl(Sequencer sequencer) {
    this.seq = sequencer;
  }

  @Override
  public void updateView(ModelData data) {
    NoteName[] a = data.getSheetData().keySet()
        .toArray(new NoteName[data.getSheetData().keySet().size()]);
    int tempo = 60000000 / data.getTempo();
    seq.setTempoInBPM(tempo);
    for (int i = 0; i < data.getMaxBeat(); i++) {
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
    }

    this.seq.start();
    try {
      Thread.sleep(data.getMaxBeat() * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.seq.close();
  }

  @Override
  public void setBeat(int beat) {
    //not relevant for this view
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    //not relevant for this view
  }


  @Override
  public int getBeat() {
    return 0;
  }

}