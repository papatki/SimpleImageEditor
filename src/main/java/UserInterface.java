import javax.swing.*;

/**
 * Created by patrioshka on 10/25/17.
 */
public class UserInterface extends MenuBar {
    public void initializeUI() {
        createMenuBar();
        setTitle("Pimp My Picture");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true); // windows resizing - disabled
        setLocationRelativeTo(null); //center frame

    }
}
