import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;

/**
 * Mocks a Sequence.
 */
public class SequenceMock extends Sequence {

  /**
   * Constructor for a mockSequenc.
   * @throws InvalidMidiDataException if midi data is unavailable.
   */
  public SequenceMock()
      throws InvalidMidiDataException {
    super(PPQ, 10);
  }

}
