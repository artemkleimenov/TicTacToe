import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class TicTacToeGUI {
	
	private static JFrame frame;
	private static TicTacToe ticTacToeGUI;
	private static JMenuBar menuBar;
	private static JPanel statusPanel;
	private static JLabel statusLabel;
	
	private static JPanel panel;
	
	private static JButton[] listX0Buttons;
	
	public static void createAndShowFUI() {
		
		frame = new JFrame("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
		panel.setLayout(new GridLayout(0, 3, 3, 3));
		
		Arrays.stream(listX0Buttons).forEach(b -> panel.add(b));

		frame.add(panel);
		frame.pack();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);		
		frame.setVisible(true);
		
		frame.setJMenuBar(menuBar);
	}
	
	public static void createAndShowMenuBar() {
		menuBar = new JMenuBar();
        
        JMenu menuGame = new JMenu("Game");
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem quitGameItem = new JMenuItem("Quit");
        
        menuGame.add(newGameItem);
        menuGame.add(quitGameItem);
        
        JMenu menuAbout = new JMenu("About");
        JMenuItem aboutGameItem = new JMenuItem("About TicTacToe"); 
        
        menuAbout.add(aboutGameItem);
        
        menuBar.add(menuGame);
        menuBar.add(menuAbout);
        
        quitGameItem.addActionListener(e -> System.exit(0));
        
        newGameItem.addActionListener(e -> {
        	ticTacToeGUI.reset();
        	 
        	Arrays.stream(listX0Buttons).forEach(b -> {
        		b.setEnabled(true);
				b.setText(null);
        	});
        	
        	statusLabel.setText("Round! FIGHT!");
        });
        
        JDialog aboutDialog = new JDialog(frame, "About TicTacToe");
        
        aboutDialog.setSize(200, 200);
    	aboutDialog.setLocationRelativeTo(null);
    	aboutDialog.setResizable(false);
    	
        String labelText = "TicTacToe game." + "<br>" +
        		"Have fun." + "<br>" + "<br>" +
        		"version 0.1" + "<br>" +
        		"developed by Artem Kleimenov" + "<br>";
        JLabel aboutLabel = new JLabel("<html><div style='text-align: center;'>" 
        		+ labelText + "</div></html>", SwingConstants.CENTER);
        
    	aboutDialog.add(aboutLabel);
    	
        aboutGameItem.addActionListener(e -> {
        	aboutDialog.setVisible(true);
        });
	}
	
	public static void createAndShowButtons() {	
		listX0Buttons = Stream.generate(JButton::new).limit(9).toArray(JButton[]::new);
		
		AtomicInteger index = new AtomicInteger(1);
		
		Arrays.stream(listX0Buttons).forEach(b -> {
			b.setName(String.valueOf(index.getAndIncrement()));
		});
		
		listX0Buttons[0].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.black));
		listX0Buttons[1].setBorder(BorderFactory.createMatteBorder(0, 3, 3, 3, Color.black));
		listX0Buttons[2].setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, Color.black));
		listX0Buttons[3].setBorder(BorderFactory.createMatteBorder(3, 0, 3, 3, Color.black));
		listX0Buttons[4].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
		listX0Buttons[5].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 0, Color.black));
		listX0Buttons[6].setBorder(BorderFactory.createMatteBorder(3, 0, 0, 3, Color.black));
		listX0Buttons[7].setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, Color.black));
		listX0Buttons[8].setBorder(BorderFactory.createMatteBorder(3, 3, 0, 0, Color.black));
		
		Arrays.stream(listX0Buttons).forEach(b -> b.setEnabled(false));
				
		ticTacToeGUI = new TicTacToe();
		
        Consumer<JButton> addActList = b -> b.addActionListener(e -> {
        	ticTacToeGUI.setCellNumber(Integer.parseInt(b.getName()));
        	ticTacToeGUI.makeTurn();
        	b.setText(ticTacToeGUI.getXO());
        	b.setEnabled(false);
        	gameStatus();
		});
        
        Arrays.stream(listX0Buttons).forEach(addActList::accept);
	}
	
	
	
	public static void statusPanel() {
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 20));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		statusLabel = new JLabel("LET'S START THE GAME!");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
	}
	
	private static void gameStatus() {
		if (ticTacToeGUI.gameStatus()) {
			statusLabel.setText(ticTacToeGUI.getGameResult());
			Arrays.stream(listX0Buttons).forEach(b -> b.setEnabled(false));
		}
	}
}