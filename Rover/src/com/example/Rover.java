package com.example;

import java.io.BufferedReader;
import java.io.FileReader;

enum Direction{
	N,E,S,W
}
public class Rover {
	private int x;
	private int y;
	private Direction direction;
	private static int Max_x;
	private static int Max_y;
	
	public Rover(int x, int y, Direction direction){
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
	}

	public static void main(String[] args) {
		//String pathname = "src/com/example/input.txt";
		if(args.length == 0) {
			System.out.println("you need to enter a filename");
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String[] coordinates = br.readLine().split(" ");
			Max_x= Integer.parseInt(coordinates[0]);
			Max_y = Integer.parseInt(coordinates[1]);
			String line = br.readLine();
			while (line != null) {
				String[] lineValue = line.split(" ");
				int x = Integer.parseInt(lineValue[0]);
				int y = Integer.parseInt(lineValue[1]);
				Direction direction = Direction.valueOf(lineValue[2]);
				Rover rover= new Rover(x, y, direction);
				line = br.readLine();
				rover.move(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void move(String instruction) {	
		if(checkPosition()) {
			for(int i = 0; i< instruction.length(); i++) {
				char ins = instruction.charAt(i);
				if("L".equals(String.valueOf(ins))) {
					turnLeft();
				}else if("R".equals(String.valueOf(ins))) {
					turnRight();
				}else if("M".equals(String.valueOf(ins))) {
					movePosition();
				}
			}
			System.out.println(this.x + " "+ this.y + " "+ this.direction);	
		}else {
			System.out.println("Rover's original position isn't correct for x = " + this.x + " and y = " + this.y + " and direction of: " + this.direction);
		}	
	}
	
	public void turnLeft() {
		this.direction = Direction.values()[((this.direction.ordinal()+3)%4)];
	}
	
	public void turnRight() {
		this.direction = Direction.values()[((this.direction.ordinal()+1)%4)];
	}
	
	public void movePosition() {
		if(this.direction == Direction.valueOf("N")) {
			this.y++;
		}else if(this.direction == Direction.valueOf("S")) {
			this.y--;
		}else if(this.direction == Direction.valueOf("W")) {
			this.x--;
		}else if(this.direction == Direction.valueOf("E")) {
			this.x++;
		}
	}
	
	public boolean checkPosition(){
		if(this.x<0 || this.x> Max_x|| this.y<0 || this.y>Max_y){
			return false;
		}
		return true;
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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}