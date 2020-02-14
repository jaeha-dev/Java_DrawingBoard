package frame;

import javax.swing.*;
import java.awt.*;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GDrawingToolBar drawingToolBar;
	private GDrawingPanel drawingPanel;

	public GMainFrame(String title) {
		super(title);
		this.setLocation(800,  200);
		this.setSize(800,  600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);

		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);

		this.drawingToolBar = new GDrawingToolBar();
		this.add(drawingToolBar, BorderLayout.NORTH);

		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);

		drawingToolBar.setDrawingPanel(drawingPanel);
	}

	public void initialize() {
		this.menuBar.initialize(drawingPanel);
		this.drawingToolBar.initialize();
		this.drawingPanel.initialize();
	}
}