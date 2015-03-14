package joc.plataforma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	int xa1=0;
	int ya1=0;
	int xa2=0;
	int ya2=0;
	boolean escape=false;
	boolean pause=false;
	boolean start=false;
	boolean up=false;
	boolean down=false;
	boolean enter=false;
	boolean left=false;
	boolean right=false;
	
	Input () {
	}

	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_RIGHT) {right=true; xa1=1; }
        if (e.getKeyCode()== KeyEvent.VK_LEFT) {left=true; xa1=-1; }
        if (e.getKeyCode()== KeyEvent.VK_UP) {up=true; ya1=-1; }
        if (e.getKeyCode()== KeyEvent.VK_DOWN) {down=true; ya1=1; }
        
        if (e.getKeyCode()== KeyEvent.VK_ESCAPE) escape=true;
        if (e.getKeyCode()== KeyEvent.VK_SPACE) {start=true; up=true;}
        if (e.getKeyCode()== KeyEvent.VK_ENTER) enter=true;
    }
	
	public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_RIGHT) {right=false; if (left) xa1=-1; else xa1=0; } 
        if (e.getKeyCode()== KeyEvent.VK_LEFT) {left=false; if (right) xa1=1; else xa1=0; }
        if (e.getKeyCode()== KeyEvent.VK_UP) {up=false; ya1=0; }
        if (e.getKeyCode()== KeyEvent.VK_DOWN) ya1=0;

        if (e.getKeyCode()== KeyEvent.VK_SPACE) {up=false;}
	}
	public void keyTyped(KeyEvent e) { }
}
