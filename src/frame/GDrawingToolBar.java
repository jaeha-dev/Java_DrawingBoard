package frame;

import global.GConstant.EShapes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GDrawingToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingPanel;
	void setDrawingPanel(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	GDrawingToolBar() {
		ActionHandler actionHandler = new ActionHandler();
		ButtonGroup buttonGroup = new ButtonGroup();
		for (EShapes eShape: EShapes.values()) {
			JRadioButton button = new JRadioButton();
			buttonGroup.add(button);
			button.setIcon(new ImageIcon(eShape.getIconName()));
			button.setSelectedIcon(new ImageIcon(eShape.getIconSLTName()));
			button.setActionCommand(eShape.toString());
			button.addActionListener(actionHandler);
			this.add(button);
		}
	}

	void initialize() {
		JRadioButton button = (JRadioButton) this.getComponent(EShapes.eSelect.ordinal());
		button.doClick();
	}

	private class ActionHandler implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String actionCommand = actionEvent.getActionCommand();
			drawingPanel.setCurrentTool(EShapes.valueOf(actionCommand).getShape());
		}
	}
}