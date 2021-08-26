package duke;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList list;

    /**
     * Constructor specifying the name of the Chatbot to be created.
     *
     * @param filePath File path to store and retrieve the duke bot information.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.printMessage(e.getMessage());
            this.list = new TaskList();
        }
    }
    
    /**
     * Starts the Chatbot and listens for user input.
     */
    public void start() {
        this.ui.printGreetings();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.getInput();
                Command c = Parser.parse(input);
                c.execute(this.list, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printMessage(e.getMessage());
            }
        }
    }

    /**
     * Initializes a Duke object and runs the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.start();
    }
}