package cs3500.music.provider;

import cs3500.music.provider.controller.GUIController;
import cs3500.music.provider.controller.GeneralController;
import cs3500.music.provider.model.MusicEditorModel;
import cs3500.music.provider.model.MusicEditorOperations;
import cs3500.music.provider.util.CompositionBuilder;
import cs3500.music.provider.util.MusicReader;
import cs3500.music.provider.view.GUIViewOperations;
import cs3500.music.provider.view.View;

import cs3500.music.provider.view.ViewFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor2 {
  /**
   * The main method of the music editor.  Takes in a file to read and a type of view to use
   * and executes the music editor.
   *
   * @param args The file to read and the view to use
   * @throws FileNotFoundException    When the given file is note found.
   * @throws InvalidMidiDataException If the midi player somehow malfunctions.
   */
  public static void main(String[] args) throws InvalidMidiDataException, FileNotFoundException {
    CompositionBuilder<MusicEditorOperations> builder = new MusicEditorModel.Builder();
    MusicEditorOperations model = MusicReader.parseFile(new FileReader(args[0]), builder);
    View view = ViewFactory.create(args[1]);
    if (args[1].equals("visual") || args[1].equals("composite")) {
      new GUIController((GUIViewOperations) view, model).run();
    }
    else {
      new GeneralController(view, model).run();
    }
  }
}
