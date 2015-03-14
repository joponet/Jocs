package joc.plataforma;

import java.awt.Graphics;


public class Level {

	int xv;
	boolean active=false;
	Ground ground;
	Objectes objectes;
	Level () {
		ground = new Ground();
		objectes=new Objectes(ground);
	}
	
	void init (int x, int y) {
		xv=0;
		active=false;
		ground.init(x, y);
		objectes.init();
	}
	
	void tick() {
		if (!active) return;
		ground.gridVisibility=false;
		ground.inc(xv, 0);
		ground.tick();
		objectes.tick();
	}
	
	void draw(Graphics g) {
		if (!active) return;
		ground.draw(g);
		objectes.draw(g);
	}
	
	boolean damage(int row, int col) {
		if (objectes.array[row][col]!=null) return true;
		return false;
	}
}
