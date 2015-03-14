package joc.plataforma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import joc.plataforma.Input;
import joc.plataforma.Menu;
import joc.plataforma.Player;
import joc.plataforma.Sound;

public class Game extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062104212432193514L;
	JFrame frame;
	boolean running=true;
	int gameFinish=10;

	Input input;
	MouseInput mouseInput;
	Level level;
	Player player;
	GameOver gameOver;
	Sound sound;
	Menu menu;
	Status status;
	Edit edit;

	Game() {
		// frame
		frame = new JFrame();
		frame.setLocation(100, 100);
		frame.setResizable(false);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// game classes
		input = new Input();
		mouseInput = new MouseInput();
		sound = new Sound();
		level = new Level();
		player = new Player(level,input);
		gameOver = new GameOver(player);
		menu = new Menu();
		status = new Status(player);
		edit = new Edit(input, mouseInput, level.ground);
		
		
		// this.canvas
		this.setBackground(Color.GRAY);
		this.addKeyListener(input);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
	}
	
	void Run() {
		// init classes
		level.init(0,0);
		player.init();		
		status.init(level.ground.x, level.ground.y+level.ground.height, level.ground.width);
		edit.init();
		gameOver.init();
		
		// init frame
		frame.setSize(level.ground.width+6, level.ground.height+status.height+28);
		frame.setVisible(true);		
		menu.init(this.getWidth(),this.getHeight(),input);
		this.requestFocus();
		level.active=true;
		player.active=true;
		status.active=true;
		
		// game time
		final double nstick = 1000000000D / 120D;
		double delay = 0;
		
		// graphics
		BufferStrategy buffer;
		this.createBufferStrategy(2);
		buffer = this.getBufferStrategy();
		
		// running
		espera(1);
		long lasttime = System.nanoTime();
		menu.active=false;
		while (running) {
			long now = System.nanoTime();
			if (input.pause) lasttime=now;
			delay += (now-lasttime) / nstick;
			lasttime = now;
			if (delay>=1) {
				Graphics g = buffer.getDrawGraphics();
				draw(g);
				g.dispose();
				buffer.show();
				delay--;
			}
			if (input.escape) running = false;
			if (input.enter && !menu.active) {
				input.enter=false;
				level.active=false;
				status.active=false;
				player.active=false;
				menu.active=true;
				edit.active=false;
			}
			// menu
			if (menu.enter) {
				menu.enter=false;
				switch (menu.selected) {
				case 0:
					menu.active=false;
					level.active=true;
					status.active=true;
					player.active=true;
					player.reset();
					break;					
				case 1:
					menu.active=false;
					level.active=true;
					status.active=true;
					player.active=true;
					break;
				case 2:
					running=false;
					break;
				case 3:
					menu.active=false;
					edit.active=true;
					break;
				case 4:
					edit.save();
					menu.active=false;
					edit.active=true;
					break;
				}
			}
			ticktime();
		}
		System.exit(0);
	}
	
	public void draw(Graphics g) {
		// clear
		
		// tick
		player.tick();
		level.tick();
		menu.tick();
		status.tick();
		gameOver.tick();
		edit.tick();
		
		// draw
		level.draw(g);
		player.draw(g);
		menu.draw(g);
		status.draw(g);
		gameOver.draw(g);
		edit.draw(g);
	}
		
	void espera(long temps) {
		long ms = temps*1000L;
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void ticktime () {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public void paint(Graphics g) {
		draw(g);
	}
}
