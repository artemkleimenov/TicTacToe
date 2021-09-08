public class TicTacToeMain {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				TicTacToeGUI.createAndShowMenuBar();
				TicTacToeGUI.createAndShowButtons();
				TicTacToeGUI.createAndShowFUI();
				TicTacToeGUI.statusPanel();
			}
		});
	}
}