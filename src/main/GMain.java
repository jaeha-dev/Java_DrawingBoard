package main;

import frame.GMainFrame;
import javax.swing.*;

public class GMain {
	public static void main(String[] args) {
		GMainFrame mainFrame = new GMainFrame("GraphicsEditor");
		ImageIcon ic = new ImageIcon("resource/icon.gif");
		mainFrame.setIconImage(ic.getImage());
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}
}