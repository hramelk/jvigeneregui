import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vigenere_GUI {
	
	public static class Vigenere {
		public static String encode(String openText, String key) {
			String encoded = "";
			int keysize = key.length();
			int keyIndex = 0;
			for (int i = 0; i < openText.length(); i++) {
				char cp = openText.charAt(i);
				int index = getIndexByRange(cp);
				if (index == 0) {
					encoded += cp;
					continue;
				}
				int num = cp - index;
				char keyChar = key.charAt(keyIndex);
				int keyNum = getNumVal(keyChar);
				num = (num + keyNum) % 26;
				encoded += (char) (num + index);
				keyIndex = (keyIndex + 1) % keysize;
			}
			return encoded;
		}
		
		public static String decode(String encodedText, String key) {
			String decoded = "";
			int keysize = key.length();
			int keyIndex = 0;
			for (int i = 0; i < encodedText.length(); i++) {
				char cp = encodedText.charAt(i);
				int index = getIndexByRange(cp);
				if (index == 0) {
					decoded += cp;
					continue;
				}
				int num = cp - index;
				char keyChar = key.charAt(keyIndex);
				int keyNum = getNumVal(keyChar);
				num = (26 + num - keyNum) % 26;
				decoded += (char) (num + index);
				keyIndex = (keyIndex + 1) % keysize;
			}
			return decoded;
		}
		
		private static int getIndexByRange(char cp) {
			if (cp >= 'a' && cp <= 'z') {
				return 97;
			} else if (cp >= 'A' && cp <= 'Z') {
				return 65;
			} else {
				return 0;
			}
		}
		
		private static int getNumVal(char cp) {
			return cp - getIndexByRange(cp);
		}
		
	}
	
	public static class Vigenere_G extends JFrame implements ActionListener {
		private static final long	serialVersionUID	= 1L;
		
		private int					columnSize			= 32;
		private int					keySizeIndex		= 4;
		private FlowLayout			leftFlow			= new FlowLayout(
																FlowLayout.LEFT);
		private JPanel				mainPanel			= new JPanel(leftFlow);
		private Color				mainColor			= Color.lightGray;
		
		private JPanel				inputPanel			= new JPanel(leftFlow);
		private JTextArea			inputText			= new JTextArea(7,
																columnSize);
		private JScrollPane			inputScrollPane		= new JScrollPane();
		private JLabel				keyLabel			= new JLabel("Key: ");
		private JTextField			keyText				= new JTextField(
																columnSize
																		- keySizeIndex);
		
		private JPanel				resultPanel			= new JPanel(leftFlow);
		private JTextArea			resultText			= new JTextArea(7,
																columnSize);
		private JScrollPane			resultScrollPane	= new JScrollPane();
		
		private JPanel				buttonPanel			= new JPanel(leftFlow);
		private JButton				encodeButton		= new JButton("Encode");
		private JButton				decodeButton		= new JButton("Decode");
		
		public Vigenere_G() throws HeadlessException {
			super("Vigenere");
			
			setLayout(leftFlow);
			
			inputText.setLineWrap(true);
			inputText.setWrapStyleWord(true);
			inputPanel.setBackground(mainColor);
			inputPanel.setPreferredSize(new Dimension(360, 150));
			// inputPanel.add(inputText);
			inputScrollPane.setViewportView(inputText);
			inputPanel.add(inputScrollPane);
			inputPanel.add(keyLabel);
			inputPanel.add(keyText);
			
			resultText.setLineWrap(true);
			resultText.setWrapStyleWord(true);
			resultPanel.setBackground(mainColor);
			resultPanel.setPreferredSize(new Dimension(360, 130));
			resultScrollPane.setViewportView(resultText);
			resultPanel.add(resultScrollPane);
			// resultPanel.add(resultText);
			
			encodeButton.addActionListener(this);
			decodeButton.addActionListener(this);
			buttonPanel.setBackground(mainColor);
			buttonPanel.add(encodeButton);
			buttonPanel.add(decodeButton);
			
			mainPanel.add(inputPanel);
			mainPanel.add(buttonPanel);
			mainPanel.add(resultPanel);
			mainPanel.setPreferredSize(new Dimension(370, 340));
			mainPanel.setBackground(Color.red);
			mainPanel.setBackground(mainColor);
			// setBackground(Color.darkGray);
			getContentPane().setBackground(mainColor);
			
			add(mainPanel);
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationByPlatform(true);
			setSize(400, 400);
			setVisible(true);
		}
		
		private void doEncode() {
			String input = inputText.getText();
			String key = keyText.getText();
			String encoded = Vigenere.encode(input, key);
			resultText.setText(encoded);
			// resultText.setText(Vigenere.encode(inputText.getText(),
			// keyText.getText()));
		}
		
		private void doDecode() {
			String input = inputText.getText();
			String key = keyText.getText();
			String decoded = Vigenere.decode(input, key);
			resultText.setText(decoded);
			// resultText.setText(Vigenere.decode(inputText.getText(),
			// keyText.getText()));
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (inputText.getText().isEmpty() || keyText.getText().isEmpty()) {
				return;
			}
			Object source = arg0.getSource();
			if (source == encodeButton) {
				// System.out.println("encode");
				doEncode();
			} else if (source == decodeButton) {
				// System.out.println("decode");
				doDecode();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Vigenere_G();
	}
	
}
