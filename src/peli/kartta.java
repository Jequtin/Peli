package peli;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class kartta {
	public int map[][];
	public int korkeus;
	public int leveys;
	public kartta(int row, int col) {
		map = new int[row][col];
		for(int i = 0; i < map.length; i++) {
			for(int j=0; j< map[0].length; j++) {
				map[i][j] = 1;
				
			}
		}
		leveys = 540/col;
		korkeus = 150/row;
	}
	public void draw(Graphics2D g) {
		for(int i = 0; i < map.length; i++) {
			for(int j=0; j< map[0].length; j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * leveys +80, i * korkeus + 50, leveys, korkeus);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * leveys +80, i * korkeus+ 50, leveys, korkeus);
				}
			}
		}
	}
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}
