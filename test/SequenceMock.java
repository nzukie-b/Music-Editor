import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

/**
 * Mocks a Sequence.
 */
public class SequenceMock extends Sequence {

  public SequenceMock()
      throws InvalidMidiDataException {
    super(PPQ, 10);
  }

}
