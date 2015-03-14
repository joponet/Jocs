package joc.plataforma;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fire {
	int row,col;
	Image fire1,fire2;
	int mov;
	Ground ground;
	
	Fire(Ground ground) {
		this.ground=ground;
	}
	
	void init(int row, int col) {
		this.row=row;
		this.col=col;
		try {
			fire1 = ImageIO.read(ClassLoader.getSystemResource("images/Fire1.png"));
			fire2 = ImageIO.read(ClassLoader.getSystemResource("images/Fire2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mov=0;
	}
	
	void tick() {
		mov++;
		if (mov>20) mov=0;
	}
	
	void draw(Graphics g) {
		if (mov<10) ground.draw(g, fire1, row, col);
		else ground.draw(g, fire2, row, col);
	}
}
