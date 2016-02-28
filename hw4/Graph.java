//-----------------------------------------------------
//
// Michael Benson
// CS1121
// Spring 05
// 4/24/2005
// Recitation Section 1
// Lab Section 10
//
// Homework Program 4
//
//-----------------------------------------------------

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Graph extends JApplet {

	// properties
	private GraphPanel graph = new GraphPanel();
	private Dimension pref = new Dimension(490,200);
	
	private JButton clear = new JButton("Clear");
	private JButton even_space = new JButton ("Evenly Space");
	private JButton sort = new JButton("Sort Acending");
	private JButton rm_low = new JButton("Delete Low");
	private JButton rm_high = new JButton("Delete High");
	
	private JButton connect = new JButton("Connect");
	private JButton high = new JButton("Highest");
	private JButton low = new JButton("Lowest");
	private JButton inc = new JButton("Increasing");
	private JButton dec = new JButton("Decreasing");
	private JButton inf = new JButton("Inflection");
	
	
	public void init() {

		Container window = getContentPane();
	
		JPanel buttons1 = new JPanel();
		buttons1.setLayout (new FlowLayout());
		
		JPanel buttons2 = new JPanel();
		buttons2.setLayout (new FlowLayout());
		
		JPanel buttons = new JPanel();
		buttons.setLayout (new FlowLayout());
		
		// set up buttons 1 and there listeners to flow layout
		buttons1.setBackground(Color.black);
		
		buttons1.add(clear);
		clear.setBackground(Color.white);
		BListener clearList = new BListener(1,graph);
		clear.addActionListener(clearList);
		
		buttons1.add(even_space);
		even_space.setBackground(Color.white);
		BListener spaceList = new BListener(2,graph);
		even_space.addActionListener(spaceList);
		
		buttons1.add(sort);
		sort.setBackground(Color.white);
		BListener sortList = new BListener(3,graph);
		sort.addActionListener(sortList);
		
		buttons1.add(rm_low);
		rm_low.setBackground(Color.white);
		BListener lowList = new BListener(4,graph);
		rm_low.addActionListener(lowList);
		
		buttons1.add(rm_high);
		rm_high.setBackground(Color.white);
		BListener highList = new BListener(5,graph);
		rm_high.addActionListener(highList);

		// set up buttons 2 flow layout
		buttons2.setBackground(Color.black);
		buttons2.add(connect);
		
		connect.setBackground(Color.green);
		buttons2.add(high);
		
		high.setBackground(Color.green);
		buttons2.add(low);
		
		low.setBackground(Color.green);
		inc.setBackground(Color.green);
		
		buttons2.add(dec);
		dec.setBackground(Color.green);
		
		buttons2.add(inf);
		inf.setBackground(Color.green);
		
		// set up the window
		window.setBackground(Color.black);
		
		window.add(graph,BorderLayout.NORTH);
		graph.setPreferredSize(pref);
		graph.setBackground(Color.white);
			
		buttons.add(buttons1);
		buttons.add(buttons2);
		
		window.add(buttons);
		buttons.setBackground(Color.black);

		// add listeners
		Listener graphList = new Listener(graph);
		graph.addMouseListener(graphList);
		
	}// end init method
	
}// end Graph class

class GraphPanel extends JPanel {

	private int [] pos_x = new int[10];
	private int [] pos_y = new int[10];
	private int points = 0;
	private int i;

	public void new_point(int next_x,int next_y) {
		
		if (points < 10) {
			pos_x[points] = next_x;
			pos_y[points] = next_y;
			
			points++;
			
			repaint();
		} // end if statement

	}// end new_point method

	public void clear() {
		int t = 0;
		while ( t < points ) {
			pos_x[t] = 0;
			pos_y[t] = 0;
			t++;
		} // end while loop
		
		points = 0;
		repaint();

	}// end clear method
	
	public void space() {
		int t = 0;
		int space = 490/points;
		while ( t < points ) {
			pos_x[t] = space * t;
			t++;
		} // end while loop
		repaint();		
	} // end space method
			
	public void sort() {
		
		for (int pos = 0; pos < pos_y.length-1; pos++) {
			int min = pos_y[pos];
			int minPos = pos;
			for (int j = pos+1; j < pos_y.length; j++) {
				if (pos_y[j] < min) {
					min = pos_y[j];
					minPos = j;
				}// end if
			} // end embeded for
			int temp = pos_y[pos];
			pos_y[pos] = pos_y[minPos];
			pos_y[minPos] = temp;
		}
		repaint();
	} // end sort method
	
	public void rm_low() {

		int t = 0;
		int min = pos_y[t];
		int min_pos = t;
		while ( t < points ) {
			if (pos_y[t] > min) {
				min = pos_y[t];
				min_pos = t;
			}// end if
			t++;
		}// end while
		t = min_pos;
		while ( t < points ) {
			pos_y[t] = pos_y[t+1];
			pos_x[t] = pos_x[t+1];
			t++;
		}// end while
			pos_y[points] = 0;
			pos_x[points] = 0;
			points--;
			repaint();
	} // end rm_high method

	
	public void rm_high() {

		int t = 0;
		int max = pos_y[t];
		int max_pos = t;
		while ( t < points ) {
			if (pos_y[t] < max) {
				max = pos_y[t];
				max_pos = t;
			}// end if
			t++;
		}// end while
		t = max_pos;
		while ( t < points ) {
			pos_y[t] = pos_y[t+1];
			pos_x[t] = pos_x[t+1];
			t++;
		}// end while
			pos_y[points] = 0;
			pos_x[points] = 0;
			points--;
			repaint();
	} // end rm_low method
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);

		i = 0;
		while ( i < points ) {
			g.setColor(Color.black);
			g.fillOval(pos_x[i],pos_y[i],5,5);
			g.fillRect(pos_x[i]+2,pos_y[i]+4,1,190-pos_y[i]);
			i++;
		} // end while loop
			
		
	}// end paintComponent method
	
} // end GraphJpanel class

class Listener extends MouseAdapter {

	private int [] pos_x = new int[10];
	private int [] pos_y = new int[10];
	private int points;
	private GraphPanel graph;
	
	public Listener (GraphPanel x) {
		graph = x;
	}// end constructor

	// start MouseEvent method
	public void mouseClicked (MouseEvent event) {

		int next_x = event.getX();
		int next_y = event.getY();

		graph.new_point(next_x,next_y);
		
	} // end mouseclicked method

} // end listener class

class BListener implements ActionListener {

	// properties
	private GraphPanel graph;
	private int ID;
	
	public BListener (int i,GraphPanel g) {
		ID = i;
		graph = g;
		} // end constructor
		
	public void actionPerformed (ActionEvent event) {
		
		if (ID == 1) 
		graph.clear();
		
		if (ID == 2)
		graph.space();
		
		if (ID == 3)
		graph.sort();

		if (ID == 4)
		graph.rm_low();

		if (ID == 5)
		graph.rm_high();
	} // end clear method

} // end ClListener class
