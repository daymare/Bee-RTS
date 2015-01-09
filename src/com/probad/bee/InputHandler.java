package com.probad.bee;

import java.awt.event.*;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
	
	public double xspeed = 0;
	public double yspeed = 0;
	public double x = 0;
	public double y = 0;
	public int mouse_x = 0;
	public int mouse_y = 0;
	
	public boolean[] keys = new boolean[65536];

	@Override
	public void mouseDragged(MouseEvent arg0) {
	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x = e.getX()/BeeComponent.SCALE;
		mouse_y = e.getY()/BeeComponent.SCALE;
		System.out.println("X: " + mouse_x + ", Y: " + mouse_y);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		for (int i=0; i<keys.length; i++) {
			keys[i] = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) { 
		int code = e.getKeyCode();
		System.out.println(code);
		if (code>0 && code<keys.length) {
			keys[code] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode(); 
		if (code>0 && code<keys.length) {
			keys[code] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public void update(Game game) {
		if(keys[38] || keys[37] || keys[40] || keys[39] || keys[87] || keys[65] || keys[83] || keys[68]) {
		if (keys[38] || keys[87]) yspeed -= 0.1;
		if (keys[37] || keys[65]) xspeed -= 0.1;
		if (keys[40] || keys[83]) yspeed += 0.1;
		if (keys[39] || keys[68]) xspeed += 0.1;
		xspeed = Math.max(Math.min(xspeed, 1),-1);
		yspeed = Math.max(Math.min(yspeed, 1),-1);
		} else {
			xspeed /= 2;
			yspeed /= 2;
		}
		game.cam_x += xspeed/4;
		game.cam_y += yspeed/4;
	}
}