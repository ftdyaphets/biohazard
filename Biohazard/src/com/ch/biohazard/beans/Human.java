package com.ch.biohazard.beans;

import java.awt.event.KeyEvent;

public class Human {
	private int life;
	private int moveSpeed;
	private String weapons;
	private int x;
	private int y;
	private boolean isLive=true;
	public enum Direction{L,R,U,D,LU,RU,LD,RD,STOP};
	private Direction direction=Direction.STOP;
	private int modelWidth=60;
	private int modelHeight=60;
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
	
	public void setDirection(KeyEvent e){
		//System.out.println("presse "+e.getKeyCode());
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			this.direction=Direction.U;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			this.direction=Direction.D;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			this.direction=Direction.L;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			this.direction=Direction.R;
			break;
		default:
			this.direction=Direction.STOP;
			break;
		}
	}
	
	public void move(){
		System.out.println("direction: "+this.direction);
		int x=this.x;
		int y=this.y;
		switch(this.direction){
		case L:this.x-=this.moveSpeed;
		break;
		case R:this.x+=this.moveSpeed;
		break;
		case U:this.y-=this.moveSpeed;
		break;
		case D:this.y+=this.moveSpeed;
		break;
		case LU:;
		case RU:;
		case LD:;
		case RD:;
		case STOP:;
		}
		System.out.println("x "+this.x);
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
	
}
