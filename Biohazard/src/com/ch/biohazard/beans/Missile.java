package com.ch.biohazard.beans;

import java.awt.Rectangle;
import java.util.List;

import com.ch.biohazard.beans.Human.Direction;

public class Missile {
	private int moveSpeed=40;
	private int width=5;
	private int height=5;
	private Direction direction;
	private int x;
	private int y;
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Missile(int x,int y,Direction direction){
		this.x=x;
		this.y=y;
		this.direction=direction;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public void move(Direction direction){
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
		case LU:
			break;
		case RU:
			break;
		case LD:
			break;
		case RD:
			break;
		case STOP:
			
			break;
		}
	}
	
	public int hitZombie(List<Zombie>zombieList){
		Rectangle missileRectangle=new Rectangle(this.x,this.y,this.width,this.height);
		Rectangle zombieRectangle;
		for(int i=0;i<zombieList.size();i++){
			zombieRectangle=new Rectangle(zombieList.get(i).getX(),zombieList.get(i).getY(),zombieList.get(i).getModelWidth(),zombieList.get(i).getModelHeight());
			if(missileRectangle.intersects(zombieRectangle)){
				return i;
			}
		}
		return -1;
	}
}
