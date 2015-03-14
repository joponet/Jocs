package joc.plataforma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Edit {
	int xv;
	int bv;
	int xo;
	int row,col;
	int type;
	int typeSp;
	Input input;
	MouseInput mouseInput;
	boolean active;
	Ground ground;
	
	Edit(Input input, MouseInput mouseInput, Ground ground) {
		this.input=input;
		this.ground=ground;
		this.mouseInput=mouseInput;
	}
	
	void init() {
		xv=0;
		active=false;
		row=0;
		col=0;
		type=1;
		typeSp=ground.blockSizeX+2;
	}
	
	void tick() {
		if (!active) return;
		ground.gridVisibility=true;
		xv=ground.inc(xv,input.xa1*2);
		row=ground.getRow(mouseInput.y-ground.offset);
		col=ground.getCol(mouseInput.x-ground.offset);
		ground.blink(row,col);
		if (row<9) {
			if (mouseInput.buttonR) ground.ground[row][col]=0;
			if (mouseInput.buttonL) ground.ground[row][col]=type;
		}
		if (row==9 && mouseInput.buttonL) {
			if (col==0) type=1;
			if (col==1) type=2;
		}
		ground.tick();
	}
	
	void draw(Graphics g) {
		if (!active) return;
		ground.draw(g);
		
		// status
		int width=200;
		int height=32;
		int x=ground.width-width;
		int y=ground.height;
		g.clearRect(0, ground.height, ground.width, ground.blockSizeY);
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		Font font = new Font(Font.SERIF, Font.PLAIN, 24);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Mp: "+row+","+col, x+5, y+22);
		g.setColor(Color.RED);
		g.drawRect(ground.offset+(type-1)*typeSp-1, ground.height, ground.blockSizeX+1, ground.blockSizeY);
		g.drawImage(ground.plataforma, ground.offset, ground.height, null);
		g.drawImage(ground.porta, ground.offset+typeSp, ground.height, null);
	}
	
	void save() {
		ground.save();
	}
}
