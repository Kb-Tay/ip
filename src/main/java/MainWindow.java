import duke.Duke;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Anakin.jpg"));

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Obi_wan.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        assert !response.equals("") : "Response should either be action or error message";

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));

        if (response.equals("Bye. May the force be with you!")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }

        userInput.clear();
    }

    /**
     * Creates a dialogue box containing Duke's getting.
     */
    public void startGui() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.startBot(), dukeImage));
    }
}
