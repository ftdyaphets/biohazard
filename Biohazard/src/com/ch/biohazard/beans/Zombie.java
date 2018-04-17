package com.ch.biohazard.beans;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import com.ch.biohazard.beans.Human.Direction;

public class Zombie {
	private int maxLife;
	private int life;
	private int moveSpeed;
	private int x;
	private int y;
	private int modelWidth=60;
	private int modelHeight=60;
	private Direction direction=Direction.R;
	private int steps=0;
	private int maxSteps=5;
	public int getMaxLife() {
		return maxLife;
	}


	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}
	public int getModelWidth() {
		return modelWidth;
	}


	public void setModelWidth(int modelWidth) {
		this.modelWidth = modelWidth;
	}


	public int getModelHeight() {
		return modelHeight;
	}


	public void setModelHeight(int modelHeight) {
		this.modelHeight = modelHeight;
	}


	public Zombie(int life,int moveSpeed,int x,int y){
		this.maxLife=life;
		this.life=life;
		this.moveSpeed=moveSpeed;
		this.x=x;
		this.y=y;
	}
	
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public void move(){
		
		//moveRandomSteps(this.direction);
		//randomChangeDirection(this.direction);
		switch(this.direction){
		case L:
			this.x-=this.moveSpeed;
			break;
		case R:
			this.x+=this.moveSpeed;
			break;
		case U:
			this.y-=this.moveSpeed;
			break;
		case D:
			this.y+=this.moveSpeed;
			break;
		default:
			break;
		}
		
	
		//System.out.println("move a step!");
		if(this.x<0){
			this.x=0;
			randomChangeDirection(this.direction);
		}
		else if((this.x+this.modelWidth)>800){
			this.x=800-this.modelWidth;
			randomChangeDirection(this.direction);
		}
		else if(this.y<0){
			this.y=0;
			randomChangeDirection(this.direction);
		}
		else if((this.y+this.modelHeight)>600){
			this.y=600-this.modelHeight;
			randomChangeDirection(this.direction);
		}
		if(this.steps<this.maxSteps){
			this.steps++;
		}
		else{
			randomChangeDirection(this.direction);
			//this.maxSteps=1+(int)Math.random()*10;
			this.steps=0;
		}
	}
	
	public void moveRandomSteps(Direction direciton){
		int steps=(int)(Math.random()*10);
		for(int i=0;i<steps;i++){
			switch(direction){
			case L:
				this.x-=this.moveSpeed;
				break;
			case R:
				this.x+=this.moveSpeed;
				break;
			case U:
				this.y-=this.moveSpeed;
				break;
			case D:
				this.y+=this.moveSpeed;
				break;
			default:
				break;
			}
		}
		
	}
	
	public void randomChangeDirection(Direction direciton){
		int random;
		
		Direction d=Direction.L;
		do{
			random=(int)(Math.random()*4);
			//System.out.println("random:"+random);
			switch(random){
			case 0:
				d=Direction.L;
				break;
			case 1:
				d=Direction.R;
				break;
			case 2:
				d=Direction.U;
				break;
			case 3:
				d=Direction.D;
				break;
			}
		}while(d==this.direction);
		this.direction=d;
		System.out.println("change direction:"+this.direction);
 	}
	
	public void attack(Human human){
		System.out.println("attack!");
		human.setStatus(false);
		
	}
	
	public void action(double distance,Human human){
		Rectangle zombieRectangle=new Rectangle(this.x,this.y,this.getModelWidth(),this.getModelHeight());
		Rectangle humanRectangle=new Rectangle(human.getX(),human.getY(),human.getModelWidth(),human.getModelHeight());
		
		/*
		if(distance<=20){
			attack(human);
		}*/
		if(zombieRectangle.intersects(humanRectangle)){
			attack(human);
		}
		else{
			move();
		}
	}
	
	public void beingHitted(){
		this.life-=200;
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
}
