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
import com.ch.biohazard.beans.Zombie;

public class MainInterface extends JFrame {
	
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
		MyJPanel mjp=new MyJPanel(zombieList, human);
		Thread t=new Thread(mjp);
		t.start();
		jf.setTitle("生化大作战");
		jf.setBounds(700,300,800,600);
		mjp.setBackground(Color.white);
		
		jf.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
			}
			public void keyReleased(KeyEvent e) {
				
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
		public MyJPanel(List<Zombie> zombieList,Human human){
			this.zombieList=zombieList;
			this.human=human;
		}
		public void paint(Graphics g){
			super.paint(g);
			Color color=g.getColor();
			g.clearRect(0, 0, 800, 600);
			//Image image=Toolkit.getDefaultToolkit().getImage("images/models/zoombie.jpg");
			//g.drawImage(image,300,400,60,60,null);
			for(int i=0;i<this.zombieList.size();i++){
				ImageIcon zoombieImage = new ImageIcon("images/models/zoombie.jpg");
				g.drawImage(zoombieImage.getImage(),this.zombieList.get(i).getX(),this.zombieList.get(i).getY(),60,60,null);
			}
			
			
			ImageIcon humanImage = new ImageIcon("images/models/human.jpg");
			g.drawImage(humanImage.getImage(),this.human.getX(),this.human.getY(),60,60,null);
			
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
	
	
	
	public static void main(String[] args) {
		MainInterface mi=new MainInterface();
		Human human=new Human(100,10,600,100);
		List<Zombie>zombieList=new ArrayList<Zombie>();
		Zombie zombie=new Zombie(2000,20, 100, 100);
		zombieList.add(zombie);
		Zombie zombie2=new Zombie(2000,20, 400, 100);
		zombieList.add(zombie2);
		mi.loadFrame(zombieList,human);
		while(human.getStatus()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=0;i<zombieList.size();i++){
				System.out.println("zombie["+i+"]in action:");
				zombieList.get(i).action(mi.getDistance(zombieList.get(i), human),human);
				mi.repaint();
			}
		}
		
		
	}

}
