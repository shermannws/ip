package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Launcher {

    /**
     * Launches the application.
     *
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
