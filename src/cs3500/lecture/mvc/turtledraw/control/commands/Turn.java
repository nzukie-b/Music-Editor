package cs3500.lecture.mvc.turtledraw.control.commands;

    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.TracingTurtleModel;
    import cs3500.lecture.mvc.turtledraw.control.TracingTurtleCommand;

/**
 * Created by blerner on 10/10/16.
 */
public class Turn implements TracingTurtleCommand {
  double d;

  public Turn(Double d) {
    this.d = d;
  }

  @Override
  public void go(TracingTurtleModel m) {
    m.turn(this.d);
  }
}
