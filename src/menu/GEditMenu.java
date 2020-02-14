package menu;

import frame.GDrawingPanel;
import global.GConstant.EEditMenus;
import shape.GGroup;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GEditMenu extends JMenu {
    private GDrawingPanel drawingPanel;

    public GEditMenu() {
        super("Edit");
        ActionHandler actionHandler = new ActionHandler();
        for (EEditMenus items: EEditMenus.values()) {
            JMenuItem menuItem = new JMenuItem(items.toString());
            menuItem.setIcon(new ImageIcon(items.getIconName()));
            switch (items) {
                case UnDo:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK));
                    break;
                case ReDo:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
                    break;
                case Cut:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case Copy:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
                    break;
                case Paste:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
                    break;
                case Delete:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case SelectAll:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
                    this.addSeparator();
                    break;
                case Group:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.ALT_MASK));
                    this.addSeparator();
                    break;
                case UnGroup:
                    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.ALT_MASK));
                    break;
                default:
                    break;
            }
            menuItem.setActionCommand(items.toString());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);
        }
    }

    public void initialize(GDrawingPanel dp) { this.drawingPanel = dp; }
    private void ReDo() { this.drawingPanel.ReDo(); }
    private void UnDo() { this.drawingPanel.UnDo(); }
    private void cutShapes() { this.drawingPanel.cutShapes(); }
    private void copyShapes() { this.drawingPanel.copyShapes(); }
    private void pasteShapes() { this.drawingPanel.pasteShapes(); }
    private void deleteShapes() { this.drawingPanel.deleteShapes(); }
    private void selectAllShapes() { this.drawingPanel.selectAllShapes(); }
    private void groupShapes() { this.drawingPanel.groupShapes(new GGroup()); }
    private void unGroupShapes() { this.drawingPanel.unGroupShapes(); }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            switch (EEditMenus.valueOf(actionEvent.getActionCommand())) {
                case UnDo:
                    UnDo();
                    break;
                case ReDo:
                    ReDo();
                    break;
                case Cut:
                    cutShapes();
                    break;
                case Copy:
                    copyShapes();
                    break;
                case Paste:
                    pasteShapes();
                    break;
                case Delete:
                    deleteShapes();
                    break;
                case SelectAll:
                    selectAllShapes();
                    break;
                case Group:
                    groupShapes();
                    break;
                case UnGroup:
                    unGroupShapes();
                    break;
                default:
                    break;
            }
        }
    }
}