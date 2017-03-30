package cs3500.lecture.mvc.turtledraw.control;

    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.Line;
    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.SmarterTurtle;
    import cs3500.lecture.mvc.turtledraw.TracingTurtleModel.TracingTurtleModel;
    import cs3500.lecture.mvc.turtledraw.control.commands.Move;
    import cs3500.lecture.mvc.turtledraw.control.commands.Square;
    import cs3500.lecture.mvc.turtledraw.control.commands.Trace;
    import cs3500.lecture.mvc.turtledraw.control.commands.Turn;
    import java.util.InputMismatchException;
    import java.util.Scanner;


/**
 * Created by blerner on 10/10/16.
 */
public class CommandController {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    TracingTurtleModel m = new SmarterTurtle();
    TracingTurtleCommand cmd = null;
    while (s.hasNext()) {
      String in = s.next();
      try {
        switch (in) {
          case "q":
          case "quit":
            return;
          case "show":
            for (Line l : m.getLines()) {
              System.out.println(l);
            }
            break;
          case "move":
            cmd = new Move(s.nextDouble());
            break;
          case "trace":
            cmd = new Trace(s.nextDouble());
            break;
          case "turn":
            cmd = new Turn(s.nextDouble());
            break;
          case "square":
            cmd = new Square(s.nextDouble());
            break;
          default:
            System.out.println(String.format("Unknown command %s", in));
            cmd = null;
            break;
        }
        if (cmd != null) cmd.go(m);
      } catch (InputMismatchException ime) {
        System.out.println("Bad length to " + in);
      }
    }
  }
}
