package menu;

import frame.GDrawingPanel;
import global.GConstant.EFileMenus;
import shape.GShape;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.Vector;

public class GFileMenu extends JMenu {
    private GDrawingPanel drawingPanel;

    public GFileMenu() {
        super("File");
        ActionHandler actionHandler = new ActionHandler();
        for (EFileMenus items: EFileMenus.values()) {
            JMenuItem menuItem = new JMenuItem(items.toString());
            menuItem.setIcon(new ImageIcon(items.getIconName()));
            switch (items) {
                case New:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
                    break;
                case Open:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case Save:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case SaveAs:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
                    this.addSeparator();
                    break;
                case Print:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case Exit:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                default:
                    break;
            }
            menuItem.setActionCommand(items.toString());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);
        }
    }

    public void initialize(GDrawingPanel dp) {
        this.drawingPanel = dp;
    }

    private void newFile() {
        drawingPanel.clearDrawingShapes();
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser(new File("data"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("GraphicsEditor files", "gps");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION) { //APPROVE_OPTION == 0
            try {
                System.out.println("Open");
                File file = fileChooser.getSelectedFile();
                ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                Object obj = input.readObject();
                drawingPanel.setDrawingShapes((Vector<GShape>) obj);
                input.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (returnValue == JFileChooser.CANCEL_OPTION) { //CANCEL_OPTION == 1
            System.out.println("Cancel");
        }
    }

    private void closeFile() {
        // ?
    }

    private void saveFile() {
        try {
            File file = new File("data/output");
            ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            output.writeObject(drawingPanel.getDrawingShapes());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAsFile() {
        JFileChooser fileChooser = new JFileChooser(new File("data"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("GraphicsEditor files", "gps");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showSaveDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION) { //APPROVE_OPTION == 0
            System.out.println("Save");
            File renameFile = fileChooser.getSelectedFile();
            String extension = ".gps";
            if(renameFile != null) {
                if (renameFile.getName().contains(extension)) {
                    try {
                        renameFile  = new File(renameFile.getName());
                        ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(renameFile)));
                        output.writeObject(drawingPanel.getDrawingShapes());
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        renameFile  = new File(renameFile.getName() + extension);
                        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(renameFile)));
                        outputStream.writeObject(drawingPanel.getDrawingShapes());
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (returnValue == JFileChooser.CANCEL_OPTION) { //CANCEL_OPTION == 1
            System.out.println("Cancel");
        }
    }

    private void printFile() {
        PrinterJob print = PrinterJob.getPrinterJob();
        boolean returnValue = print.printDialog();
        try {
            print.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
        // PrintService print = PrintServiceLookup.lookupDefaultPrintService();
        // PrintJob job = (PrintJob) print.createPrintJob();
        // job.getGraphics();
    }

    private void exitFile() {
        System.exit(0);
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            switch (EFileMenus.valueOf(actionEvent.getActionCommand())) {
                case New:
                    newFile();
                    break;
                case Open:
                    openFile();
                    break;
                case Close:
                    closeFile();
                    break;
                case Save:
                    saveFile();
                    break;
                case SaveAs:
                    saveAsFile();
                    break;
                case Print:
                    printFile();
                    break;
                case Exit:
                    exitFile();
                    break;
                default:
                    break;
            }
        }
    }
}