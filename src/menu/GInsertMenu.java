package menu;

import frame.GDrawingPanel;
import global.GConstant.EInsertMenus;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GInsertMenu extends JMenu {
    private GDrawingPanel drawingPanel;

    public GInsertMenu() {
        super("Insert");
        ActionHandler actionHandler = new ActionHandler();
        for (EInsertMenus items: EInsertMenus.values()) {
            JMenuItem menuItem = new JMenuItem(items.toString());
            menuItem.setIcon(new ImageIcon(items.getIconName()));
            menuItem.setActionCommand(items.toString());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);
        }
    }

    public void initialize(GDrawingPanel dp) {
        this.drawingPanel = dp;
    }

    private void insertImage() {
        JFileChooser fileChooser = new JFileChooser(new File("data"));

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) { //APPROVE_OPTION == 0
            System.out.println("Insert image");
            File file = fileChooser.getSelectedFile();
            Image image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.drawingPanel.insertImage(image);
        } else if (returnValue == JFileChooser.CANCEL_OPTION) { //CANCEL_OPTION == 1
            System.out.println("Cancel");
        }
    }

    private void insertAudio() {
        this.drawingPanel.insertAudio();
    }

    private void insertVideo() {
        this.drawingPanel.insertVideo();
    }

    private void insertTextBox() {
        this.drawingPanel.insertTextBox();
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            switch (EInsertMenus.valueOf(actionEvent.getActionCommand())) {
                case Image:
                    insertImage();
                    break;
                case Audio:
                    insertAudio();
                    break;
                case Video:
                    insertVideo();
                    break;
                case TestBox:
                    insertTextBox();
                    break;
                default:
                    break;
            }
        }
    }
}