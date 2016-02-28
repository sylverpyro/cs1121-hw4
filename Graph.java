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
	private Dimension pref = new Dimension(100,190);
	
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
		
		// set up buttons 1 flow layout
		buttons1.setBackground(Color.black);
		buttons1.add(clear);
		clear.setBackground(Color.white);
		buttons1.add(even_space);
		even_space.setBackground(Color.white);
		buttons1.add(sort);
		sort.setBackground(Color.white);
		buttons1.add(rm_low);
		rm_low.setBackground(Color.white);
		buttons1.add(rm_high);
		rm_high.setBackground(Color.white);

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
	
	public void redraw(int[] x,int[] y)  {
		pos_x = x;
		pos_y = y;
		repaint();

	}// end redraw method
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillOval(pos_x[0],pos_y[0],5,5);
		
	}// end paintComponent method
	
} // end GraphJpanel class

class Listener extends MouseAdapter {

	private int [] pos_x = new int[10];
	private int [] pos_y = new int[10];
	private GraphPanel graph;
	
	public Listener (GraphPanel x) {
		graph = x;
	}// end constructor

	// start MouseEvent method
	public void mouseClicked (MouseEvent event) {

		int next_x = event.getX();
		int next_y = event.getY();

		pos_x[0] = next_x;
		pos_y[0] = next_y;

		graph.redraw(pos_x,pos_y);
		
	} // end mouseclicked method

	
} // end listener class

