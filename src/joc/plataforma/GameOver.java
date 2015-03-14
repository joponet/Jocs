package joc.plataforma;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {
	boolean active;
	int x,y;
	int blink;
	Player player;
	GameOver (Player player) {
		this.player=player;
	}
	
	void init() {
		active=false;
		x=player.ground.width/2-70;
		y=player.ground.height/2;
		blink=0;
	}
	
	void tick() {
		if (player.dead && player.active) {
			active=true;
			blink++;
			if (blink>90) blink=0;
		}
		else {
			active=false;
			blink=0;
		}
		
	}
	
	void draw(Graphics g) {
		if(!active) return;
		if (blink>60) return;
		g.setColor(Color.red);
		Font font = new Font(Font.SERIF, Font.PLAIN & Font.BOLD, 36);
		g.setFont(font);
		g.drawString("DEAD !!!", x, y);
	}
}
