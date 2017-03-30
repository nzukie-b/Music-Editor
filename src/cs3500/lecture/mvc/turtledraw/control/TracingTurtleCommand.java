package cs3500.lecture.mvc.turtledraw.control;

    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.TracingTurtleModel;


/**
 * Created by blerner on 10/10/16.
 */
public interface TracingTurtleCommand {
  void go(TracingTurtleModel m);
}
