package joc.plataforma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener, MouseListener{
	
	int x,y;
	boolean buttonL;
	boolean buttonR;
	
	void prova() {
		x=0;
		y=0;
		buttonL=false;
		buttonR=false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton()==MouseEvent.BUTTON1) buttonL=true;
		if (e.getButton()==MouseEvent.BUTTON3) buttonR=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton()==MouseEvent.BUTTON1) buttonL=false;		
		if (e.getButton()==MouseEvent.BUTTON3) buttonR=false;		
	}

}
