package joc.plataforma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Status {
	final int height = 32;
	final int thickness = 3;
	int x,y;
	int width=0;
	Player player;
	boolean active;

	Status (Player player) {
		this.player = player;
	}

	void init(int x, int y, int width) {
		this.x=x;
		this.y=y;
		this.width = width;
		active=false;
	}

	void tick() {	
	}

	void draw(Graphics g) {
		if (!active) return;
		g.setColor(Color.GREEN);
		g.drawRect(x,y,width-1,height-1);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(x+thickness, y+thickness, width-2*thickness, height-2*thickness);
		g.setColor(Color.GREEN);
		Font font = new Font(Font.SERIF, Font.PLAIN, 24);
		g.setFont(font);
		g.drawString("Player: "+player.row+","+player.cole, 10, y+22);
//		g.drawString("Health: "+player.health, 150, y+22);
		g.drawString("Health", 140, y+22);
		g.setColor(Color.BLACK);
		g.drawRect(210, y+thickness+1, 10*8+3, height-2*thickness-3);
		if (player.health>0) {
			g.setColor(Color.BLUE);
			g.fillRect(210+2, y+thickness+3, player.health*8, height-2*thickness-6);
		}
		if (player.health<0) {
			g.setColor(Color.RED);
			g.fillRect(210+2, y+thickness+3, 10*8, height-2*thickness-6);
		}
	}	
}
