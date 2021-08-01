import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;


public class main extends JFrame{
	public JLabel title = new JLabel("수식입력 ");
	public JLabel text = new JLabel(" ");
	
	public class RoundButton extends JButton { // 동그라미 버튼 만들기
		 public RoundButton(String label) { 
			 super(label); 
	 
			 Dimension size = getPreferredSize(); 
			 size.width = size.height = Math.max(size.width, size.height); 
			 setPreferredSize(size); 
	 
			 setContentAreaFilled(false); 
		 } 

		 protected void paintComponent(Graphics g) { 
			 if (getModel().isArmed()) { 
			  g.setColor(Color.black); 
			 } else { 
			  g.setColor(getBackground()); 
			 } 
			 g.fillOval(0, 0, getSize().width-1, getSize().height-1); 		 
			 super.paintComponent(g); 
		 } 

		 protected void paintBorder(Graphics g) { 
			 g.setColor(getForeground()); 
			 g.drawOval(0, 0, getSize().width-1, getSize().height-1); 
		 } 

		 Shape shape; 
		 public boolean contains(int x, int y) { 
			 if (shape == null || !shape.getBounds().equals(getBounds())) { 
			  shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight()); 
			 } 
			 return shape.contains(x, y); 
		 } 
	}
	
	class up extends JPanel{
		public up() {
			setBackground(Color.black);

			Font tt = new Font("맑은 고딕", Font.BOLD, 20);
			title.setFont(tt);
			title.setForeground(Color.white);
			add(title);
			
			Font t = new Font("맑은 고딕", Font.BOLD, 50);
			text.setFont(t);
			text.setForeground(Color.white);
			text.setHorizontalAlignment(JLabel.RIGHT);
			add(text);			
			
			addKeyListener(new Mykey());
			setLayout(new GridLayout(2, 1));
			
			setFocusable(true);
			requestFocus();
		}
		
		class Mykey extends KeyAdapter{ // 키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드키보드
			
			public void keyPressed(KeyEvent e) {
				
				char keyChar = e.getKeyChar();// 문자키이면 유니코드 리턴
				int keyCode = e.getKeyCode(); // 모든 키에 대해 작동 
								
				if(keyCode==10) { // enter
					title.setText(text.getText());		
					String word = text.getText();
					String result = getCalculate(word);
					text.setText(result);
			
					
				}else if (keyCode==27) {// esc
					title.setText("수식 입력 ");
					text.setText(" ");	
				}else if (keyCode == 8) {// backspace
					String sub = text.getText();
					int length = sub.length();
					text.setText(sub.substring(0, length-1));
				}else {
					title.setText("수식을 계산중입니다.");
					String a = text.getText();
					String b = Character.toString(keyChar);
					String c = a + b;
					text.setText(c);
				}
				if(title.getText().equals(text.getText())) {
					title.setText("수식을 계산할 수 없습니다.");
					text.setText(" ");
				}
			}

		
		}
	}
	
	
	
	public JButton[] number = new JButton[10];
	public JButton a[] = {new RoundButton(""), new RoundButton(""), new RoundButton("<-"), new RoundButton("=")};
	public JButton b[] = {new RoundButton("7"), new RoundButton("8"), new RoundButton("9"), new RoundButton("/")};
	public JButton c[] = {new RoundButton("4"), new RoundButton("5"), new RoundButton("6"), new RoundButton("*")};
	public JButton d[] = {new RoundButton("1"), new RoundButton("2"), new RoundButton("3"), new RoundButton("-")};
	public JButton ee[] = {new RoundButton("CE"), new RoundButton("0"), new RoundButton("."), new RoundButton("+")};

	class center extends JPanel{
		
		public center() {
			
			
			setBackground(Color.BLACK);
			setLayout(new GridLayout(5, 4, 5, 5));
			
			Font aa = new Font("맑은 고딕", Font.BOLD, 20);
			
			for(int i=0 ; i<4 ; i++) {
				a[i].setFont(aa);
				a[i].setForeground(Color.black);
				a[i].setBackground(Color.black);
				a[i].addActionListener(new MyAction());
				add(a[i]);
				a[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
				a[i].setFocusPainted(false);
				a[i].setBorderPainted(false);
				a[i].setBorder(null);
			}
			a[2].setBackground(Color.white);
			a[3].setBackground(Color.white);
			
			for(int i=0 ; i<4 ; i++) {
				b[i].setFont(aa);
				b[i].setForeground(Color.white);
				b[i].setBackground(Color.gray);
				b[i].addActionListener(new MyAction());
				add(b[i]);
				b[i].setFocusPainted(false);
				b[i].setOpaque(false);
				b[i].setBorderPainted(false);
				b[i].setBorder(null);
			}
			b[3].setBackground(Color.orange);
			for(int i=0 ; i<4 ; i++) {
				c[i].setFont(aa);
				c[i].setForeground(Color.white);
				c[i].setBackground(Color.gray);
				c[i].addActionListener(new MyAction());
				add(c[i]);
				c[i].setFocusPainted(false);
				c[i].setOpaque(false);
				c[i].setBorderPainted(false);
			}
			c[3].setBackground(Color.orange);
			for(int i=0 ; i<4 ; i++) {
				d[i].setFont(aa);
				d[i].setForeground(Color.white);
				d[i].setBackground(Color.gray);
				d[i].addActionListener(new MyAction());
				add(d[i]);
				d[i].setFocusPainted(false);
				d[i].setOpaque(false);
				d[i].setBorderPainted(false);
			}
			d[3].setBackground(Color.orange);
			for(int i=0 ; i<4 ; i++) {
				ee[i].setFont(aa);
				ee[i].setForeground(Color.white);
				ee[i].setBackground(Color.gray);
				ee[i].addActionListener(new MyAction());
				add(ee[i]);
				ee[i].setFocusPainted(false);
				ee[i].setOpaque(false);
				ee[i].setBorderPainted(false);
			}	
			ee[3].setBackground(Color.orange);
		}
		
		class MyAction implements ActionListener{ // 버튼버튼버튼버튼버버튼버튼버튼튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼버튼
			
			
			
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				String bb = b.getText();
				
				if(bb.equals("=")) { // enter
					title.setText(text.getText());
					
					
					String word = text.getText();
					String result = getCalculate(word);
					text.setText(result);
			
				}else if (bb.equals("CE")) {// esc
					title.setText("수식 입력 ");
					text.setText(" ");
				}else if (bb.equals("<-")) {// backspace
					String sub = text.getText();
					int length = sub.length();
					text.setText(" " + sub.substring(0, length-1));
				}else if (bb.equals("+/-")) {// +/-
					String sub = text.getText();
					int length = sub.length();
					if(sub.substring(0, 0).equals("-")) {
						sub = sub.substring(1, length);
					}else {
						sub = "-" + text.getText();
					}
					text.setText(sub);
				}else {
					title.setText("수식을 계산중입니다.");
					String a = text.getText();
					String bbb = bb;
					String c = a + bbb;
					text.setText(c);
				}
				if(title.getText().equals(text.getText())) {
					title.setText("수식을 계산할 수 없습니다.");
					text.setText(" ");
				}
				
			}
		}
	}
	
	class south extends JPanel implements Runnable{ // 시계
		private Thread thread;
		private JLabel label;
		public south() {
			setLayout(new FlowLayout());
			
			label = new JLabel();
			
			if(thread == null) {
				thread = new Thread(this);
				thread.start();
			}
			add(label);
		}
		@Override
		public void run() {
			while(true) {
				Calendar cal = Calendar.getInstance();
				String now = cal.get(Calendar.YEAR)+"년 "+(cal.get(Calendar.MONTH)+1)+"월 "+cal.get(Calendar.DATE)+"일  "
				+cal.get(Calendar.HOUR)+"시 "+cal.get(Calendar.MINUTE)+"분 "+cal.get(Calendar.SECOND)+"초";
				label.setText(now);
				label.setForeground(Color.white);
				setBackground(Color.black);
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				if(cal.get(Calendar.SECOND)==59 && cal.get(Calendar.MINUTE)==59) {
					a[0].setText("째");
					a[1].setText("깍");
					a[0].setBackground(Color.green);
					a[1].setBackground(Color.GREEN);
				}else {
					a[0].setText("");
					a[1].setText("");
					a[0].setBackground(Color.black);
					a[1].setBackground(Color.black);
				}

			}
			
		}
	}
	
	
	public main() {
		setTitle("2019156037 최혜민");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		
		up u = new up();
		c.add(u, BorderLayout.NORTH);
		center cc = new center();
		c.add(cc, BorderLayout.CENTER);
		
		south s = new south();
		c.add(s, BorderLayout.SOUTH);
				
		setSize(300, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		//JFrame.setDefaultLookAndFeelDecorated(true); // 엥 창 위에 바 이상한걸로 나옴,,,
		new main();

	}
	

	
	public String getCalculate(String content) {
		char[] operationCode = {'+', '-', '*', '/'}; //연산 부호

        ArrayList<String> Fixed = new ArrayList<String>();
        Stack<Character> Stack = new Stack<Character>();
        Stack<String> Calc = new Stack<String>();

        int index = 0;

        for (int i = 0; i < content.length(); i++) {
            for (int j = 0; j < operationCode.length; j++) {
                if (content.charAt(i) == operationCode[j]) { 
                    Fixed.add(content.substring(index, i).trim().replace(" ", "").replace(" ", ""));
                        if (content.charAt(i) == ' ') {
                            while (true) {
                                Fixed.add(Stack.pop().toString());
                                if (Stack.isEmpty()) {
                                    break;
                                }
                            }
                       }

                    if (Stack.isEmpty()) {
                        Stack.push(operationCode[j]);
                    } else {
                        if (opOrder(operationCode[j]) > opOrder(Stack.peek())) {
                            Stack.push(operationCode[j]);
                        } else if (opOrder(operationCode[j]) <= opOrder(Stack.peek())) {
                            Fixed.add(Stack.peek().toString());
                            Stack.pop();//스택 제거
                            Stack.push(operationCode[j]);
                        }
                    }
                    index = i + 1;
                }
            }
        }
        Fixed.add(content.substring(index, content.length()).trim().replace(" ", "").replace(" ", ""));

        if (!Stack.isEmpty()) {
            for (int i = 0; i < Stack.size();) {
                Fixed.add(Stack.peek().toString());
                Stack.pop();
            }
        }

        for (int i = 0; i < Fixed.size(); i++) { // 공백제거
            if (Fixed.get(i).equals("")) {
                Fixed.remove(i);
                i = i - 1;
            }
        }

        Stack.clear();

        for (int i = 0; i < Fixed.size(); i++) {
            Calc.push(Fixed.get(i));
            for (int j = 0; j < operationCode.length; j++) {
                if (Fixed.get(i).charAt(0) == operationCode[j]) { //연산 부호 비교
                    Calc.pop();
                    double s2, s1;
                    String rs;

                    s2 = Double.parseDouble(Calc.pop());
                    s1 = Double.parseDouble(Calc.pop());

                    switch (operationCode[j]) {
                        case '+':
                            rs = String.valueOf(s1 + s2);
                            Calc.push(rs);
                            break;
                        case '-':
                            rs = String.valueOf(s1 - s2);
                            Calc.push(rs);
                            break;
                        case '*':
                            rs = String.valueOf(s1 * s2);
                            Calc.push(rs);
                            break;
                        case '/':
                            rs = String.valueOf(s1 / s2);
                            Calc.push(rs);
                            break;
                    }
                }
            }
        }

        double re = Double.parseDouble(Calc.peek());
        String result = String.format("%.10f", re);

        int num = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '.') {
                num = i;
                break;
            }
        }

        String mok = result.substring(0, num);
        double divde = Double.parseDouble(result) % Double.parseDouble(mok);

        if (divde == 0) {
            result = String.format("%.0f", re);
        }

        return result;
	}
	
	public int opOrder(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
}




