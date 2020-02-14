package menu;

import frame.GDrawingPanel;
import global.GConstant.EColorMenus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GColorMenu extends JMenu {
	private GDrawingPanel drawingPanel;

    public GColorMenu() {
        super("Color");
        ActionHandler actionHandler = new ActionHandler();

        for (EColorMenus items: EColorMenus.values()) {
            JMenuItem menuItem = new JMenuItem(items.toString());
            menuItem.setIcon(new ImageIcon(items.getIconName()));

            switch (items) {
                case LineColor:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
                    break;
                case FillColor:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case Texture:
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

    public void initialize (GDrawingPanel dp) {
        drawingPanel = dp;
    }

    private void setLineColor() {
        Color lineColor = JColorChooser.showDialog(null, "Choose line color", Color.BLACK);
        drawingPanel.setLineColor(lineColor);
    }

    private void defaultLineColor() {
        drawingPanel.setLineColor(Color.BLACK);
    }

    private void setFillColor() {
        Color fillColor = JColorChooser.showDialog(null, "Choose fill color", Color.WHITE);
        drawingPanel.setFillColor(fillColor);
    }

    private void defaultFillColor() {
        drawingPanel.setFillColor(Color.WHITE);
    }

    private void setTexture() {
        ImageIcon image = new ImageIcon("resource/texture.png");
        drawingPanel.setTexture(image);
    }

    private void setGradation() {
        // JFrame gradationFrame = new JFrame("Choose gradation color");
        // JColorChooser left = new JColorChooser();
        // JColorChooser right = new JColorChooser();
        // left.createToolTip();
        // left.setDragEnabled(true);
        // gradationFrame.add(left, BorderLayout.WEST);
        // right.setDragEnabled(true);
        // gradationFrame.add(right, BorderLayout.EAST);
        // gradationFrame.pack();
        // gradationFrame.setLocation(600,  400);
        // gradationFrame.setVisible(true);
        // Color selectColor1 = left.getColor();
        // Color selectColor2 = right.getColor();
        Color selectColor1 = JColorChooser.showDialog(null, "Choose line color", Color.BLACK);
        Color selectColor2 = JColorChooser.showDialog(null, "Choose fill color", Color.WHITE);
        drawingPanel.setGradation(selectColor1, selectColor2);
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            switch (EColorMenus.valueOf(actionEvent.getActionCommand())) {
                case LineColor:
                    setLineColor();
                    break;
                case DefaultLineColor:
                    defaultLineColor();
                    break;
                case FillColor:
                    setFillColor();
                    break;
                case DefaultFillColor:
                    defaultFillColor();
                    break;
                case Texture:
                    setTexture();
                    break;
                case Gradient:
                    setGradation();
                    break;
                default:
                    break;
            }
        }
    }
}