//********************************************************\\
//* Name: Game                                           *\\
//* Desc: For program calculations and mechanics.        *\\
//* - Method: Update() : Game/program mechanics go here. *\\
//*                      Is run repeatedly on a thread.  *\\
//********************************************************\\

package com.probad.bee;

import com.probad.bee.world.*;
import com.probad.bee.gui.*;

import java.util.Random;

public class Game {
	
	protected EntityWorld world;
	
	public int time;
	public double cam_x = 0;
	public double cam_y = 0;
	
	Random rand = new Random();
	
	public void init(Screen s) {
		world = new EntityWorld(s);
	}
	
	public void update(InputHandler inputHandler) {
		// Game loop
		
		world.update(17);
		
		time++;
		//System.out.println(time);
   }
	
	public EntityWorld getWorld() {
		return world;
	}
}
