package com.ch.biohazard.beans;

public class Zombie {
	private int life;
	private int moveSpeed;
	private int x;
	private int y;
	
	public Zombie(int life,int moveSpeed,int x,int y){
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
