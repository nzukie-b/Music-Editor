package cs3500.lecture.mvc.turtledraw.control.commands;



    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.TracingTurtleModel;
    import cs3500.lecture.mvc.turtledraw.control.TracingTurtleCommand;

/**
 * Created by blerner on 10/10/16.
 */
public class Square implements TracingTurtleCommand {
  double d;

  public Square(Double d) {
    this.d = d;
  }

  @Override
  public void go(TracingTurtleModel m) {
    for (int i = 0; i < 4; i++) {
      m.trace(this.d);
      m.turn(90);
    }
  }
}