package com.ch.biohazard.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
		jf.setTitle("生化大作战");
		jf.setBounds(700,300,800,600);
		jp.setBackground(Color.white);
		jf.add(jp);
		jf.setVisible(true);
		
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
		Human human=new Human(100,15,600,100);
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
