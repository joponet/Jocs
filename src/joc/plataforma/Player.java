package joc.plataforma;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	int width=20;
	int height=32;
	int x;
	int y; // posicio respecte part inferior esquerra recuadre
	int xc;
	int xt;
	int xtmax;
	int offsetE,offsetD;
	int blockSizeX,blockSizeY;
	int sizeg;
	int row,cole,cold;
	int ymax;
	int jump;
	boolean jumping;
	int direccio;
	int mou,moui;
	int health;
	int rest;
	int fall;
	int damage;
	boolean dead;
	boolean active;
	BufferedImage ninotD=null;
	BufferedImage ninotD1=null;
	BufferedImage ninotD2=null;
	BufferedImage ninotE=null;
	BufferedImage ninotE1=null;
	BufferedImage ninotE2=null;
	Input input;
	Level level;
	Ground ground;

	Player (Level level, Input input) {
		this.level = level;
		ground = level.ground;
		this.input = input;
	}
	void init () {
		offsetE=9; offsetD=9;
		blockSizeX=Properties.blockSizeX;
		blockSizeY=Properties.blockSizeY;
		sizeg=Properties.heightGround;
		try {
			ninotD = ImageIO.read(ClassLoader.getSystemResource("images/ninotD.png"));
			ninotD1 = ImageIO.read(ClassLoader.getSystemResource("images/ninotD1.png"));
			ninotD2 = ImageIO.read(ClassLoader.getSystemResource("images/ninotD2.png"));
			ninotE = ImageIO.read(ClassLoader.getSystemResource("images/ninotE.png"));
			ninotE1 = ImageIO.read(ClassLoader.getSystemResource("images/ninotE1.png"));
			ninotE2 = ImageIO.read(ClassLoader.getSystemResource("images/ninotE2.png"));
			width=ninotD.getWidth(null);
			height=ninotD.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		xtmax=(ground.ground[0].length-1)*blockSizeX;
		xc=(ground.visibleBlocks/2)*blockSizeX;
		active=false;
		reset();
	}
	
	void reset() {
		xt=xc;
		x=xc;
		y=ground.ground.length*blockSizeY-1;
		ymax=y;
		jump=0;
		direccio=1;
		moui=0;
		health=10;
		rest=0;
		fall=0;
		damage=0;
		dead=false;
	}

	void tick() {
		if (!active) return;
		if (dead) return;
		
		xt +=input.xa1;
		if (xt<0) xt=0;
		if (xt>xtmax) xt=xtmax;
		level.xv=xt-xc;
		x=xc;
		if (xt<xc) {level.xv=0; x=xt;}
		if (xt>(xtmax-xc)) {level.xv=xtmax-xc-xc; x=xc+xc+xt-xtmax;}
		
		if (input.xa1==0) {
			moui=0;
		}
		else moui +=1;
		if (moui>40) moui=0;
		mou=moui/10;

		if (input.xa1>0) direccio=1;
		if (input.xa1<0) direccio=-1;
		cole=ground.getCol(x+offsetE);
		cold=ground.getCol(x+width-offsetD-1);
		row=ground.getRow(y);
		if (ground.ground[row][cole]==0 && ground.ground[row][cold]==0 ) {
			ymax = (row+2)*blockSizeY-1;
		}
		if (ground.ground[row][cole]>0 || ground.ground[row][cold]>0 ) {
			ymax = (row+1)*blockSizeY-1;
		}
		if (input.up && y==ymax) jumping=true; 
		if (input.up && jumping && jump<blockSizeY*1.1) {
			y--; y--;
			jump++; jump++;
		}
		else {
			if (y<ymax) {
				y++; fall++;
			}
			if (y<ymax) {
				y++; fall++;
			}
			if (y==ymax) {
				if (fall>blockSizeY*2) health -=fall/blockSizeY;
				fall=0;
			}
			if (y>ymax) y=ymax;
		}
		if (!input.up) {jump=0; jumping=false;}
		// health
		if (health<10) rest++;
		if (rest>240) { health++; rest=0;}
		if (level.damage(row, cole) ||level.damage(row, cold)) damage++;
		else damage=0;
		if (damage>10) {health--; damage -=10;}
		if (health<0) dead=true;
	}
	
	void draw(Graphics g) {
		if (!active) return;
		
		int xv=x+ground.offset;
		int yv=y+ground.offset-height-sizeg;
		if (direccio==1) {
			switch (mou) {
			case 0:
			case 2:
				g.drawImage(ninotD, xv, yv, null);
				break;
			case 1:
				g.drawImage(ninotD1, xv, yv, null);
				break;
			case 3:
				g.drawImage(ninotD2, xv, yv, null);
				break;
			}
		}
		if (direccio==-1) {
			switch (mou) {
			case 0:
			case 2:
				g.drawImage(ninotE, xv, yv, null);
				break;
			case 1:
				g.drawImage(ninotE1, xv, yv, null);
				break;
			case 3:
				g.drawImage(ninotE2, xv, yv, null);
				break;
			}
		}
	}
}
