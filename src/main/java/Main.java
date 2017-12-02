import java.awt.*;

/**
 * Created by patrioshka on 10/25/17.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            UserInterface ui = new UserInterface();
            ui.initializeUI();
            ui.setVisible(true);
        });
    }
}
