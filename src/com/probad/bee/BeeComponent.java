package src.com.probad.bee;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import src.com.probad.bee.Game;
import src.com.probad.bee.BeeComponent;
import src.com.probad.bee.gui.Screen;

public class BeeComponent extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int SCALE = 2;
	private static final int WIDTH = 512;
	private static final int HEIGHT = 318;
	
	private boolean running;
	private Thread thread;
	
	private Game game;
	private Screen screen;
	private BufferedImage img;
	private int[] pixels;
	private InputHandler inputHandler;
	
	public BeeComponent() {
	    Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
	    setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		game = new Game();
		game.init(screen);
		
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		
		inputHandler = new InputHandler();

		addKeyListener(inputHandler);
		addFocusListener(inputHandler);
		addMouseListener(inputHandler);
		addMouseMotionListener(inputHandler);
	}
	
	public synchronized void start() {
		if (running) return;
	    running = true;
	    thread = new Thread(this);
	    thread.start();
    }
	
	public synchronized void stop() {
		System.out.println("Thread Has Stopped");
		if(running) return;
	    running = false;
	    try {
	       thread.join();
	    } 
	    catch (InterruptedException e) {
	       e.printStackTrace();
	    }
	}
	
	public void run() {
		while(running) {
			update();
			render();
		}
	}
	
	private void update() {
		game.update(inputHandler);
	}
	
	private void render() {
	      BufferStrategy bs = getBufferStrategy();
	      if (bs == null) {
	         createBufferStrategy(3);
	         return;
	      }
	      
	      screen.render(game);
	      game.world.render(game.cam_x, game.cam_y);
	      
	      for (int i = 0; i < WIDTH * HEIGHT; i++) {
	    	  pixels[i] = screen.pixels[i];
	      }
	      
	      Graphics g = bs.getDrawGraphics();
	      g.fillRect(0, 0, getWidth(), getHeight());
	      g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
	      g.dispose();
	      bs.show();
	}
	
	public static void main(String[] args) {
		BeeComponent game = new BeeComponent();
	    JFrame frame = new JFrame("Main");
	    
	    frame.add(game);
	    frame.pack();
	    frame.setResizable(false);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    game.start();
	}
}
