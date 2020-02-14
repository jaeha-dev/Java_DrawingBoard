package frame;

import menu.GColorMenu;
import menu.GEditMenu;
import menu.GFileMenu;
import menu.GInsertMenu;
import javax.swing.*;

class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GColorMenu colorMenu;
	private GInsertMenu insertMenu;

	GMenuBar() {
		super();
		fileMenu = new GFileMenu();
		this.add(fileMenu);
		editMenu = new GEditMenu();
		this.add(editMenu);
		colorMenu = new GColorMenu();
		this.add(colorMenu);
		insertMenu = new GInsertMenu();
		this.add(insertMenu);
	}

	void initialize(GDrawingPanel dp) {
		fileMenu.initialize(dp);
		editMenu.initialize(dp);
		colorMenu.initialize(dp);
		insertMenu.initialize(dp);
	}
}