import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by patrioshka on 10/25/17.
 */
public class MenuBar extends JFrame {

    private JMenuBar jMenuBar;
    private JMenu jFile, jEdit, jFilters;
    private JMenuItem open, save, print, exit, undo, redo, grayScale, negative, sepia, sharpen, blur, edgeDetect;
    private FileItems fileItems;


    public void createMenuBar() {
        jMenuBar = new JMenuBar();
        fileItems = new FileItems();

        jFile = new JMenu("File");
        jMenuBar.add(jFile);

        open = new JMenuItem("Open");
        open.setMnemonic(KeyEvent.VK_F);
        open.setToolTipText("Open an image");
        open.addActionListener((ActionEvent) -> fileItems.openingImage());
        jFile.add(open);
        jFile.addSeparator();

        save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_F);
        save.setToolTipText("Save the image");
        save.addActionListener((ActionEvent) -> fileItems.savingImage());
        jFile.add(save);
        jFile.addSeparator();

        print = new JMenuItem("Print");
        print.setMnemonic(KeyEvent.VK_F);
        print.setToolTipText("Print the image");
        print.addActionListener((ActionEvent) -> fileItems.printingImage());
        jFile.add(print);
        jFile.addSeparator();

        exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_F);
        exit.setToolTipText("Exit application");
        exit.addActionListener((ActionEvent) -> fileItems.exitingProgramme());
        jFile.add(exit);


// edit
        jEdit = new JMenu("Edit");
        jMenuBar.add(jEdit);

        undo = new JMenuItem("Undo");
        undo.setMnemonic(KeyEvent.VK_F);
        undo.addActionListener((ActionEvent) -> fileItems.undo());
        jEdit.add(undo);
        jEdit.addSeparator();

        redo = new JMenuItem("Redo");
        redo.setMnemonic(KeyEvent.VK_F);
        redo.addActionListener((ActionEvent) -> fileItems.redo());
        jEdit.add(redo);
        jEdit.add(redo);

// filters
        jFilters = new JMenu("Add filter");
        jMenuBar.add(jFilters);

        grayScale = new JMenuItem("Grayscale");
        grayScale.setMnemonic(KeyEvent.VK_F);
        grayScale.setToolTipText("Make it gray");
        grayScale.addActionListener((ActionEvent) -> fileItems.makeGray());
        jFilters.add(grayScale);
        jFilters.addSeparator();

        negative = new JMenuItem("Negative");
        negative.setMnemonic(KeyEvent.VK_F);
        negative.setToolTipText("Make it negative");
        negative.addActionListener((ActionEvent) -> fileItems.makeNegative());
        jFilters.add(negative);
        jFilters.addSeparator();

        sepia = new JMenuItem("Sepia");
        sepia.setMnemonic(KeyEvent.VK_F);
        sepia.setToolTipText("Make it sepia");
        sepia.addActionListener((ActionEvent) -> fileItems.makeSepia());
        jFilters.add(sepia);
        jFilters.addSeparator();

        sharpen = new JMenuItem("Sharpen");
        sharpen.setMnemonic(KeyEvent.VK_F);
        sharpen.setToolTipText("Make it sharpen");
        sharpen.addActionListener((ActionEvent) -> fileItems.makeSharpen());
        jFilters.add(sharpen);
        jFilters.addSeparator();

        blur = new JMenuItem("Blur");
        blur.setMnemonic(KeyEvent.VK_F);
        blur.setToolTipText("Make it blurry");
        blur.addActionListener((ActionEvent) -> fileItems.makeBlurry());
        jFilters.add(blur);
        jFilters.addSeparator();

        edgeDetect = new JMenuItem("Edge Detection");
        edgeDetect.setMnemonic(KeyEvent.VK_F);
        edgeDetect.setToolTipText("....");
        edgeDetect.addActionListener((ActionEvent) -> fileItems.detectEdges());
        jFilters.add(edgeDetect);

        setJMenuBar(jMenuBar);
        setContentPane(fileItems);
    }

}
