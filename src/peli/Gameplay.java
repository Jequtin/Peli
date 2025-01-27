package peli;



import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer timer;
	private int delay = 8;
	private int playerX = 310;
	Random rand = new Random();
	private int ballPositionX = rand.nextInt((550 - 50)+1) + 50;
	private int ballPositionY = 350;
	private int ballxDir = -1;
	private int ballyDir = -2;
	
	private kartta map;
	
	public Gameplay() {
		map = new kartta(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		
	}
	
	
	public void paint(Graphics g) {
		//Background
		g.setColor(Color.black);
		g.fillRect(1,  1,  692, 592);
		
		// kartta
		map.draw((Graphics2D)g);
		
		//Borders
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 592, 30);
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// Ball
		g.setColor(Color.yellow);
		g.fillOval(ballPositionX, ballPositionY, 20, 20);
		
		
		if(totalBricks <= 0) {
			play = false;
			ballxDir = 0;
			ballyDir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You WON!, Score: " + score, 190, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
			
		}
		
		if(ballPositionY > 570) {
			play = false;
			ballxDir = 0;
			ballyDir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Yrit� uudelleen, Pisteet: " + score, 190, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Paina 'Enter' aloittaaksesi uuden pelin", 185, 350);
			
		}
		
		g.dispose();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		timer.start();
		
		if(play) {
			if(new Rectangle(ballPositionX, ballPositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballyDir = -ballyDir;
			}
			
			
			A: for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickx = j * map.leveys + 80;
						int bricky = i * map.korkeus + 50;
						int leveys = map.leveys;
						int korkeus = map.korkeus;
						
						Rectangle rect = new Rectangle(brickx, bricky, leveys, korkeus);
						Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 20 ,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,  i,  j);
							totalBricks--;
							score += 1;
							
							if(ballPositionX +19 <= brickRect.x || ballPositionX +1 >= brickRect.x + brickRect.width) {
								ballxDir = -ballxDir;
							
							} else {
								ballyDir = -ballyDir;
							}
							
							break A;
						}
					}
				}
			}
			
			ballPositionX += ballxDir;
			ballPositionY += ballyDir;
			if(ballPositionX < 0){
				ballxDir = -ballxDir;
			}
			if(ballPositionY < 0){
				ballyDir = -ballyDir;
			}
			if(ballPositionX > 670){
				ballxDir = -ballxDir;
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
		}
		}
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(!play) {
					play = true;
					ballPositionX = rand.nextInt((550 - 50)+1) + 50;;
					ballPositionY = 350;
					ballxDir = -1;
					ballyDir = -2;
					playerX = 310;
					
					score = 0;
					totalBricks = 21;
					map = new kartta(3, 7);
					
					repaint();
				}
			}
		}
		
		
	
	public void moveRight() {
		play = true;
		playerX+=70;
	}
	public void moveLeft() {
		play = true;
		playerX-=70;
	}
		

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
