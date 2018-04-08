package com.ch.biohazard.beans;

public class Human {
	private int life;
	private int moveSpeed;
	private String weapons;
	private int x;
	private int y;
	private boolean isLive=true;
	public boolean getStatus() {
		return isLive;
	}
	public void setStatus(boolean isLive) {
		this.isLive = isLive;
	}
	public Human(int life,int moveSpeed,int x,int y){
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
	public String getWeapons() {
		return weapons;
	}
	public void setWeapons(String weapons) {
		this.weapons = weapons;
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
