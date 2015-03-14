package joc.plataforma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class Ground {
	final int offset=5;
	int x,y;
	int xv,bv,xo;
	int xMax;
	int width, height;
	int blockSizeX, blockSizeY;
	int visibleBlocks;
	boolean gridVisibility;
	int blink;
	int blinkRow, blinkCol;
	Image plataforma;
	Image porta;
	
	int[][] ground;
	final int[][] GROUND_DEFAULT={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,0,1,1,1,0,0,0,0,0,0,0,0,1,0,0,1},
			{0,1,1,0,1,1,0,0,0,1,1,0,1,1,1,0,1},
			{1,1,1,0,1,1,1,0,1,0,1,1,1,0,0,0,1},
			{0,0,0,0,0,1,0,0,0,0,1,1,1,0,0,0,1},
			{1,1,1,0,1,1,1,0,1,1,1,1,1,0,0,0,1},
			{0,1,1,1,1,0,0,0,0,1,1,0,1,1,1,0,1},
			{0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
			};

	Ground () {
		try {
			plataforma = ImageIO.read(ClassLoader.getSystemResource("images/plataforma.png"));
			porta = ImageIO.read(ClassLoader.getSystemResource("images/porta.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void init (int x, int y) {
		this.x=x;
		this.y=y;
		blockSizeX=Properties.blockSizeX;
		blockSizeY=Properties.blockSizeY;
		open();
		visibleBlocks=Properties.visibleBlocks;
		this.width=2*offset+visibleBlocks*blockSizeX;
		this.height=2*offset+ground.length*blockSizeY;
		xMax=(ground[0].length-visibleBlocks)*blockSizeX;
		gridVisibility=false;
		blink=0;
	}
	
	void tick() {
		bv=xv/blockSizeX;
		xo=xv-(bv*blockSizeX);		
	}
	
	void draw (Graphics g) {
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(x, y, width, height);
		g.clearRect(x+offset, y+offset, width-2*offset, height-2*offset);
		int row=ground.length;
		for (int i=0; i<row; i++) {
			int jmax=visibleBlocks+1;
			if (jmax+bv>ground[0].length) jmax--;
			for (int j=0; j<jmax; j++) {
				if (gridVisibility) {
					if (i==blinkRow && j+bv==blinkCol && blink<15) g.setColor(Color.RED);
					else g.setColor(Color.YELLOW);
					g.drawRect(offset-xo+j*blockSizeX, offset+i*blockSizeY, blockSizeX-1, blockSizeY-1);
				}
				g.setColor(Color.GREEN);
				if (ground[i][j+bv]==1) {
					g.drawImage(plataforma, offset-xo+j*blockSizeX, offset+i*blockSizeY, null);
				}
				if (ground[i][j+bv]==2) {
					g.drawImage(porta, offset-xo+j*blockSizeX, offset+i*blockSizeY, null);
				}
			}
		}
		g.setColor(Color.DARK_GRAY);
		for (int i=0; i<offset; i++) {
			g.drawRect(x+i, y+i, width-2*i-1, height-2*i-1);
		}
	}
	
	void draw (Graphics g, Image image, int row, int col) {
		if (col<bv) return;
		if (col>bv+visibleBlocks) return;
		int x= offset - xo + (col-bv)*blockSizeX;
		int y= offset + row*blockSizeY;
		g.drawImage(image, x, y, null);
	}
	
	int inc (int xv, int xa) {
		xv +=xa;
		if (xv<0) xv=0;
		if (xv>xMax) xv=xMax;
		this.xv=xv;
		return xv;
	}
	
	void blink(int row, int col) {
		blink++; if (blink>30) blink=0;
		blinkRow=row;
		blinkCol=col;
	}
	
	int getCol(int x) {
		return ((x+xo)/blockSizeX) + bv;
	}
	
	int getRow(int y) {
		return (y-offset)/blockSizeY;
	}
	
	void open() {
		File file=getFile();
		if (!file.exists()) {
			ground=GROUND_DEFAULT;
			return;
		}

		try {
			FileInputStream fileInput=new FileInputStream(file);
			int rows=fileInput.read();
			int cols=fileInput.read();
			ground=new int[rows][cols];
			for (int i=0; i<rows; i++) {
				for (int j=0; j<cols; j++) {
					ground[i][j]=fileInput.read();
				}
			}
			fileInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	void save() {
		File file=getFile();
		int rows=ground.length;
		int cols=ground[0].length;
		
		try {			
			file.createNewFile();
			FileOutputStream fileOutput = new FileOutputStream(file);
			fileOutput.write(rows);
			fileOutput.write(cols);
			for (int i=0; i<rows; i++) {
				for (int j=0; j<cols; j++) {
					fileOutput.write(ground[i][j]);
				}
			}
			fileOutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	File getFile () {
		FileSystem fileSystem;
		fileSystem=FileSystems.getDefault();

		Path path;
		path=fileSystem.getPath(System.getenv("USERPROFILE"),"java");
		path.toFile().mkdirs();

		path=fileSystem.getPath(System.getenv("USERPROFILE"),"java","plataformes.dat");
		System.out.println(path.toString());
		File file;
		file=path.toFile();
		return file;
	}
}
