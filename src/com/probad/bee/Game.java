//********************************************************\\
//* Name: Game                                           *\\
//* Desc: For program calculations and mechanics.        *\\
//* - Method: Update() : Game/program mechanics go here. *\\
//*                      Is run repeatedly on a thread.  *\\
//********************************************************\\

package src.com.probad.bee;

import src.com.probad.bee.world.*;
import src.com.probad.bee.gui.*;

import src.com.probad.bee.entity.*;

import java.util.Random;

public class Game {
	
	EntityWorld world;
	
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
}
