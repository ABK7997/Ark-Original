package main;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class FullScreen extends JFrame {
	
	private static final long serialVersionUID = -8343652955877426856L;

	public FullScreen() {
		setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
        setBounds(maximumWindowBounds);
	}
	
}
