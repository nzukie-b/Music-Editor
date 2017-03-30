package cs3500.lecture.mvc.turtledraw.control.commands;


    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.TracingTurtleModel;
    import cs3500.lecture.mvc.turtledraw.control.TracingTurtleCommand;

/**
 * Created by ashesh on 10/28/2016.
 */
public class Save implements TracingTurtleCommand {

  @Override
  public void go(TracingTurtleModel m) {
    m.save();
  }
}