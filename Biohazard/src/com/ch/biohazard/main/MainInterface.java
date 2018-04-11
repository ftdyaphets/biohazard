package com.ch.biohazard.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ch.biohazard.beans.Human;
import com.ch.biohazard.beans.Missile;
import com.ch.biohazard.beans.Zombie;

public class MainInterface extends JFrame {
	public static List<Missile>missileList=new ArrayList<Missile>();
	public   int width=800;
	public   int height=600;
	public void loadFrame(List<Zombie> zombieList,Human human){
		JFrame jf=new JFrame();
		/*
		JPanel jp=new JPanel(){
			public void paint(Graphics g){
				super.paint(g);
				//Image image=Toolkit.getDefaultToolkit().getImage("images/models/zoombie.jpg");
				//g.drawImage(image,300,400,60,60,null);
				for(int i=0;i<zombieList.size();i++){
					ImageIcon zoombieImage = new ImageIcon("images/models/zoombie.jpg");
					g.drawImage(zoombieImage.getImage(),zombieList.get(i).getX(),zombieList.get(i).getY(),60,60,null);
				}
				
				
				ImageIcon humanImage = new ImageIcon("images/models/human.jpg");
				g.drawImage(humanImage.getImage(),human.getX(),human.getY(),60,60,null);
				//g.drawRect(100, 100, 100, 100);
			}
		};
		*/
		MyJPanel mjp=new MyJPanel(zombieList, human,missileList);
		
		Thread t=new Thread(mjp);
		t.start();
		jf.setTitle("生化大作战");
		jf.setBounds(700,300,800,600);
		mjp.setBackground(Color.white);
		
		jf.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_J){
					missileList=mjp.human.fire(missileList);
				}
			}
			public void keyPressed(KeyEvent e) {
				mjp.human.setDirection(e);
				mjp.human.move();
				
			}
		});
		
		jf.add(mjp);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setVisible(true);
		
	}
	
	class MyJPanel extends JPanel implements Runnable{
		List<Zombie> zombieList;
		Human human;
		List<Missile> missileList;
		public MyJPanel(List<Zombie> zombieList,Human human,List<Missile> missileList){
			this.zombieList=zombieList;
			this.human=human;
			this.missileList=missileList;
		}
		public void paint(Graphics g){
			
			super.paint(g);
			Color color=g.getColor();
			g.clearRect(0, 0, width, height);
			//Image image=Toolkit.getDefaultToolkit().getImage("images/models/zoombie.jpg");
			//g.drawImage(image,300,400,60,60,null);
			for(int i=0;i<this.zombieList.size();i++){
				Zombie zombie=this.zombieList.get(i);
				ImageIcon zoombieImage = new ImageIcon("images/models/zoombie.jpg");
				g.drawImage(zoombieImage.getImage(),zombie.getX(),zombie.getY(),60,60,null);
				g.drawString("zombie["+i+"]: "+zombie.getLife(), 20+100*i, 30);
				g.setColor(Color.red);
				//g.drawLine(zombie.getX(), zombie.getY()-5, zombie.getX()+zombie.getModelWidth()*(zombie.getLife()/zombie.getMaxLife()), zombie.getY()-5);
				g.fillRect(zombie.getX(), zombie.getY()-5,zombie.getModelWidth()/10*(zombie.getLife()*10/zombie.getMaxLife()),3);
				if(zombie.getLife()<=0){
					g.setFont(new Font("Georgia", Font.BOLD, 24));
					g.drawString("kill a zombie!", 150, 300);
					
				}
				g.setColor(color);
			}
			
			for(int i=0;i<this.missileList.size();i++){
				g.setColor(Color.red);
				g.fillOval(missileList.get(i).getX(), missileList.get(i).getY(), missileList.get(i).getWidth(), missileList.get(i).getHeight());
				g.setColor(color);
			}
			
			ImageIcon humanImage = new ImageIcon("images/models/human.jpg");
			g.drawImage(humanImage.getImage(),this.human.getX(),this.human.getY(),60,60,null);
			
			g.drawString(missileList.size()+" missiles left", 20, 20);
			
			
			if(!human.getStatus()){
				g.setColor(Color.red);
				g.setFont(new Font("Georgia", Font.BOLD, 24));
				g.drawString("Gotcha!The zombie has eaten your brain!", 150, 300);
				g.setColor(color);
			}
			//g.drawRect(100, 100, 100, 100);
		}
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(10);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}
	
	public Zombie CreateZombie(int life,int moveSpeed,int x,int y){
		Zombie zoombie=new Zombie(life,moveSpeed, x, y);
		return zoombie;
	}
	
	public double getDistance(Zombie zombie,Human human){
		double distance;
		int xDistance=zombie.getX()+60-human.getX();
		int yDistance=zombie.getY()-human.getY();
		distance=Math.sqrt(xDistance*xDistance+yDistance*yDistance);
		return distance;
	}
	
	public boolean missileOutOfPanel(Missile missile){
		if(missile.getX()<0||missile.getY()<0||missile.getX()>width||missile.getY()>height){
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		MainInterface mi=new MainInterface();
		Human human=new Human(100,10,600,100);
		List<Zombie>zombieList=new ArrayList<Zombie>();
		Zombie zombie=new Zombie(2000,20, 100, 100);
		zombieList.add(zombie);
		Zombie zombie2=new Zombie(2000,20, 400, 100);
		zombieList.add(zombie2);
		mi.loadFrame(zombieList,human);
		int hittedZombieNum;
		while(human.getStatus()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for(int i=0;i<zombieList.size();i++){
				System.out.println("zombie["+i+"]in action:");
				zombieList.get(i).action(mi.getDistance(zombieList.get(i), human),human);
				if(zombieList.get(i).getLife()<=0){
					zombieList.remove(i);
				}
				mi.repaint();
			}
			
			for(int i=0;i<missileList.size();i++){
			
				//System.out.println("missile["+i+"]move by :"+missileList.get(i).getDirection());
				
				missileList.get(i).move(missileList.get(i).getDirection());
				
				hittedZombieNum=missileList.get(i).hitZombie(zombieList);
				if(mi.missileOutOfPanel(missileList.get(i))){
					missileList.remove(i);
					break;
				}
				if(hittedZombieNum>=0){
					zombieList.get(hittedZombieNum).beingHitted();
					missileList.remove(i);
				}
				
				
			}
		}
		return;
		
		
	}

}
