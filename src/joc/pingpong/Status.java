package joc.pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Status {
	final int height = 32;
	int width=0;
	Player player1, player2;

	Status (Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	void init(int width) {
		this.width = width;
	}

	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(0,0,width-1,height-1);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(3, 3, width-6, height-6);
		g.setColor(Color.GREEN);
		Font font = new Font(Font.SERIF, Font.PLAIN, 24);
		g.setFont(font);
		g.drawString("Player 1: "+player1.goals, 10, 22);
		g.drawString("Player 2: "+player2.goals, 140, 22);
	}
	
	void clear(Graphics g) {
//		g.clearRect(1,1,width-1,height-1);		
//		g.drawRect(85,0,70,15);
	}
	
	void tick() {
		
	}

}
