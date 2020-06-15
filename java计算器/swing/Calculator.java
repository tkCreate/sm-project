package swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.math.BigInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator {

	private JFrame frame;
	private JTextField inputTextField;
	private JTextField resultTextField;

	private JPanel panel;

	private JButton clearButton;
	private JButton zero;
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private JButton nine;
	private JButton dotButton;
	private JButton minus;
	private JButton add;
	private JButton mul;
	private JButton div;
	private JButton mod;
	private JButton leftC;
	private JButton rightC;
	private JButton equal;
	private JButton back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setFocusable(true);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("¿¨Æ¬¼ÆËãÆ÷");
		frame.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() > 0) {
					panel.setVisible(true);
					frame.setSize(414, 613);
				} else {
					panel.setVisible(false);
					frame.setSize(414, 204);
				}
			}
		});
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 27) { // esc
					inputTextField.setText("0");
					resultTextField.setText(null);
				}
				if (e.getKeyCode() == 96)
					putNum(zero);
				if (e.getKeyCode() == 97)
					putNum(one);
				if (e.getKeyCode() == 98)
					putNum(two);
				if (e.getKeyCode() == 99)
					putNum(three);
				if (e.getKeyCode() == 100)
					putNum(four);
				if (e.getKeyCode() == 101)
					putNum(five);
				if (e.getKeyCode() == 102)
					putNum(six);
				if (e.getKeyCode() == 103)
					putNum(seven);
				if (e.getKeyCode() == 104)
					putNum(eight);
				if (e.getKeyCode() == 105)
					putNum(nine);
				if (e.getKeyCode() == 110) { // "."dot

					String num = inputTextField.getText();
					if (num.indexOf('.') == -1)
						putNum(dotButton);
				}

				// op
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					equalLog();
				}

				if (e.getKeyCode() == 106 || e.isShiftDown() && e.getKeyCode() == 56)
					op(mul);

				if (e.getKeyCode() == 107)
					op(add);

				if (e.getKeyCode() == 109)
					op(minus);
				if (e.getKeyCode() == 111)
					op(div);

				if (e.isShiftDown() && e.getKeyCode() == 53)
					op(mod);

				if (e.isShiftDown() && e.getKeyCode() == 57) { // £¨
					String num = resultTextField.getText() + leftC.getText();
					resultTextField.setText(num);
				}

				if (e.isShiftDown() && e.getKeyCode() == 48) { // £©
					String num = resultTextField.getText() + inputTextField.getText() + rightC.getText();
					resultTextField.setText(num);
					inputTextField.setText("");
				}

				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (inputTextField.getText().length() > 0) {
						String num = inputTextField.getText();
						StringBuilder sb = new StringBuilder(num);
						sb.deleteCharAt(num.length() - 1);
						num = sb.toString();
						inputTextField.setText(num);
					}
				}

			}

		});
		frame.setResizable(false);
		frame.setBounds(100, 100, 414, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		inputTextField = new JTextField();
		inputTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		inputTextField.setText("0");
		inputTextField.setEditable(false);
		inputTextField.setBackground(Color.WHITE);
		inputTextField.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		inputTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		inputTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 28));
		inputTextField.setBounds(0, 58, 408, 72);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		resultTextField = new JTextField();
		resultTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		resultTextField.setForeground(Color.WHITE);
		resultTextField.setBackground(Color.DARK_GRAY);
		resultTextField.setEditable(false);
		resultTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		resultTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 24));
		resultTextField.setColumns(10);
		resultTextField.setBounds(0, 0, 408, 57);
		frame.getContentPane().add(resultTextField);

		panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		panel.setBounds(0, 169, 414, 409);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(6, 4, 0, 0));

		mod = new JButton("%");
		mod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op(mod);
			}
		});
		mod.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 17));
		panel.add(mod);

		JButton fact = new JButton("n!");
		fact.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		fact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				double tmp = Double.parseDouble(num);

				BigInteger res = new BigInteger("1");
				for (int i = 1; i <= (int) tmp; ++i)
					res = res.multiply(new BigInteger("" + i));

				inputTextField.setText("½á¹û£º" + res);
			}
		});
		fact.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(fact);

		JButton sinButton = new JButton("sin");
		sinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		sinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp = Math.sin(tmp);

				inputTextField.setText("½á¹û£º" + tmp.toString());
			}
		});
		sinButton.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(sinButton);

		JButton cosButton = new JButton("cos");
		cosButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		cosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp = Math.cos(tmp);

				inputTextField.setText("½á¹û£º" + tmp.toString());
			}
		});
		cosButton.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(cosButton);

		JButton tanButton = new JButton("tan");
		tanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		tanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp = Math.tan(tmp);

				inputTextField.setText("½á¹û£º" + tmp.toString());
			}
		});
		tanButton.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(tanButton);

		JButton left = new JButton("<<");
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp *= 2;

				inputTextField.setText("½á¹û£º" + tmp.toString());
			}
		});
		left.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(left);

		JButton right = new JButton(">>");
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp /= 2;

				inputTextField.setText("½á¹û£º" + tmp.toString());
			}
		});
		right.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(right);

		clearButton = new JButton("C");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputTextField.setText("0");
				resultTextField.setText(null);

			}
		});
		clearButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(clearButton);

		back = new JButton("\u2190");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputTextField.getText().length() > 0) {
					String num = inputTextField.getText();
					StringBuilder sb = new StringBuilder(num);
					sb.deleteCharAt(num.length() - 1);
					num = sb.toString();
					inputTextField.setText(num);
				}
			}
		});
		back.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 30));
		panel.add(back);

		add = new JButton("+");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op(add);
			}
		});
		add.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 30));
		panel.add(add);

		JButton log = new JButton("log2(x)");
		log.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp = Math.log(tmp) / Math.log(2);

				inputTextField.setText("½á¹û£º" + tmp.toString());
			}
		});
		log.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 14));
		panel.add(log);

		seven = new JButton("7");
		seven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(seven);
			}
		});
		seven.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(seven);

		eight = new JButton("8");
		eight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(eight);
			}
		});
		eight.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(eight);

		nine = new JButton("9");
		nine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(nine);
			}
		});
		nine.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(nine);

		minus = new JButton("-");
		minus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op(minus);
			}

		});
		minus.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 30));
		panel.add(minus);

		JButton time2 = new JButton("x^2");
		time2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		time2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num;
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					num = sb.toString();

				} else
					num = inputTextField.getText();

				Double tmp = Double.parseDouble(num);
				tmp *= tmp;

				inputTextField.setText("½á¹û£º" + tmp.toString());

			}
		});
		time2.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(time2);

		four = new JButton("4");
		four.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(four);
			}
		});
		four.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(four);

		five = new JButton("5");
		five.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(five);
			}
		});
		five.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(five);

		six = new JButton("6");
		six.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(six);
			}
		});
		six.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(six);

		mul = new JButton("*");
		mul.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op(div);
			}
		});
		mul.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 30));
		panel.add(mul);

		JButton abs = new JButton("abs");
		abs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		abs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputTextField.getText().contains("½á¹û£º")) {
					StringBuilder sb = new StringBuilder(inputTextField.getText());
					sb.delete(0, 3);
					if (sb.toString().contains("-")) {
						sb.delete(0, 1);
						inputTextField.setText(sb.toString());
					}
				}
			}
		});
		abs.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		panel.add(abs);

		one = new JButton("1");
		one.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(one);
			}
		});
		one.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(one);

		two = new JButton("2");
		two.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(two);
			}
		});
		two.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(two);

		three = new JButton("3");
		three.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(three);
			}
		});
		three.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(three);

		div = new JButton("/");
		div.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		div.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op(div);
			}
		});
		div.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 30));
		panel.add(div);

		leftC = new JButton("(");
		leftC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		leftC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = resultTextField.getText() + leftC.getText();
				resultTextField.setText(num);
			}

		});
		leftC.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 24));
		panel.add(leftC);

		rightC = new JButton(")");
		rightC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		rightC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = resultTextField.getText() + inputTextField.getText() + rightC.getText();
				resultTextField.setText(num);
				inputTextField.setText("");
			}
		});
		rightC.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 24));
		panel.add(rightC);

		zero = new JButton("0");
		zero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putNum(zero);
			}

		});
		zero.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 24));
		panel.add(zero);

		dotButton = new JButton(".");
		dotButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		dotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dotButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 30));
		panel.add(dotButton);

		equal = new JButton("=");
		equal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equalLog();
			}
		});
		equal.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 30));
		panel.add(equal);

		JButton btnNewButton_12 = new JButton("\u6298\u53E0\u9762\u677F(C)");
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.requestFocus();
			}
		});
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.isVisible()) {
					panel.setVisible(false);
					frame.setSize(414, 204);
				} else {
					panel.setVisible(true);
					frame.setSize(414, 613);
				}
			}
		});
		btnNewButton_12.setMnemonic('C');
		btnNewButton_12.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.PLAIN, 17));
		btnNewButton_12.setBounds(0, 130, 408, 40);
		frame.getContentPane().add(btnNewButton_12);
	}

	private void putNum(JButton zero) {
		if (inputTextField.getText().contains("ÓÐ¼ÆËã´íÎó") || inputTextField.getText().contains("½á¹û"))
			inputTextField.setText("");

		String num = inputTextField.getText() + zero.getText();
		if (!num.contains("0.")) {
			num = num.replaceAll("^0*", "");
			if (num.isEmpty())
				num = "0";
		}
		inputTextField.setText(num);
	}

	private void op(JButton minus) {
		String num;
		if (inputTextField.getText().contains("½á¹û£º")) {
			StringBuilder sb = new StringBuilder(inputTextField.getText());
			sb.delete(0, 3);
			num = resultTextField.getText() + sb.toString() + minus.getText();
		} else {
			num = resultTextField.getText() + inputTextField.getText() + minus.getText();
		}
		resultTextField.setText(num);
		inputTextField.setText("0");

	}

	private void equalLog() {
		String num = resultTextField.getText() + inputTextField.getText();
		double ans = CalLogic.cal(num);
		if (Double.isInfinite(ans)) {
			inputTextField.setText("ÓÐ¼ÆËã´íÎó");
			resultTextField.setText("");

		} else {
			inputTextField.setText("½á¹û£º" + String.valueOf(ans));
			resultTextField.setText("");
		}
	}
}
