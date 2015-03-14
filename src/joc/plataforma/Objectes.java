package joc.plataforma;

import java.awt.Graphics;
import java.util.ArrayList;

public class Objectes {
	ArrayList<Fire> fires = new ArrayList<Fire>();
	Fire[][] array;
	Fire fire;
	Ground ground;
	
	Objectes (Ground ground) {
		this.ground=ground;
	}
	
	void init () {
		array = new Fire[ground.ground.length][ground.ground[0].length];		
		int row,col;
		// fire 1
		row=2;col=4;
		fire = new Fire(ground);
		fire.init(row, col);
		fires.add(fire);
		array[row][col]=fire;
		// fire 1
		row=3;col=8;
		fire = new Fire(ground);
		fire.init(row, col);
		fires.add(fire);
		array[row][col]=fire;
	}
	
	void tick() {
		for (int i=0; i<fires.size(); i++) {
			fires.get(i).tick();
		}
	}
	
	void draw(Graphics g) {
		for (int i=0; i<fires.size(); i++) {
			fires.get(i).draw(g);
		}
	}
}
