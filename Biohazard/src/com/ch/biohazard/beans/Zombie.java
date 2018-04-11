package com.ch.biohazard.beans;

public class Zombie {
	private int maxLife;
	public int getMaxLife() {
		return maxLife;
	}


	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}


	private int life;
	private int moveSpeed;
	private int x;
	private int y;
	private int modelWidth=60;
	private int modelHeight=60;
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
		this.x+=moveSpeed;
		System.out.println("move a step!");
		if(this.x<0){
			this.x=0;
			
		}
		if((this.x+this.modelWidth)>800){
			this.x=800-this.modelWidth;
		}
		if(this.y<0){
			this.y=0;
		}
		if((this.y+this.modelHeight)>600){
			this.y=600-this.modelHeight;
		}
	}
	
	public void attack(Human human){
		System.out.println("attack!");
		human.setStatus(false);
	}
	
	public void action(double distance,Human human){
		if(distance<=20){
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
