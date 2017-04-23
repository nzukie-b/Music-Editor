package cs3500.music;

import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicModel;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;

import cs3500.music.view.ViewFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * Class to run the program based on the inputs.
 */
public class MusicEditor {

  /**
   * Main method that creates the model, view, and controller.
   *
   * @param args An array of strings that will create the type of view specified,
   *             and create the model from the file that is inputted.
   */
  public static void main(String[] args) {
    MusicReader mr = new MusicReader();
    MusicModel model = new MusicModel(4, 4);
    MusicModel.Builder mmbuild = new MusicModel.Builder(model);

    ViewFactory vf = new ViewFactory();
    IMusicView view = vf.create(args[0], args[2]);

    try {
      BufferedReader br = new BufferedReader(new FileReader(args[1]));
      MusicController cntrl = new MusicController(mr.parseFile(br,
              mmbuild), view);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}