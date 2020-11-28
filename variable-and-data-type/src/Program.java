import common.ExerciseGroup;
import common.ExerciseType;
import common.PracticeExerciseGroup;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import common.SampleExerciseGroup;

public class Program {

    private static ExerciseGroup exerciseGroup;

    public static void main(String[] args) {
        checkMode(args);
        int cmd;
        System.out.println("--- Start ---");
        try (InputStream is = System.in) {
            do {
                exerciseGroup.showMenu();
                cmd = is.read();
                exerciseGroup.executeCommand(cmd);
            } while (cmd != KeyEvent.VK_0 && cmd != KeyEvent.VK_ESCAPE);
        } catch (Exception ex) {
            printError(ex);
        }
        System.out.println("--- End ---");
    }

    private static void checkMode(String[] args) {
        if (args.length > 0 && ExerciseType.PRACTICE.name().equalsIgnoreCase(args[0])) {
            exerciseGroup = new PracticeExerciseGroup();
        } else {
            exerciseGroup = new SampleExerciseGroup();
        }
    }

    private static void printError(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        System.err.println("Error ar file: " + stackTrace[stackTrace.length - 2].getFileName());
        System.err.println("Error at method: " + stackTrace[stackTrace.length - 2].getMethodName());
        System.err.println("Error at line: " + stackTrace[stackTrace.length - 2].getLineNumber());
    }

}
