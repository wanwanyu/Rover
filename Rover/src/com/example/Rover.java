package com.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Rover {
	private int x;
	private int y;
	private String direction;
	private static int Max_x;
	private static int Max_y;
	
	public Rover(int x, int y, String direction){
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
				String direction = lineValue[2];
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
		}else {
			System.out.println("rover's origin position isn't correct");
		}
		System.out.println(this.x + " "+ this.y + " "+ this.direction);	
	}
	
	public void turnLeft() {
		if(this.direction.equals("N")) {
			this.direction = "W";			
		}else if(this.direction.equals("S")) {
			this.direction = "E";
		}else if(this.direction.equals("W")) {
			this.direction = "S";
		}else if(this.direction.equals("E")) {
			this.direction = "N";
		}
	}
	
	public void turnRight() {
		if(this.direction.equals("N")) {
			this.direction = "E";
		}else if(this.direction.equals("S")) {
			this.direction = "W";
		}else if(this.direction.equals("W")) {
			this.direction = "N";
		}else if(this.direction.equals("E")) {
			this.direction = "S";
		}
	}
	
	public void movePosition() {
		if(this.direction.equals("N")) {
			this.y++;
		}else if(this.direction.equals("S")) {
			this.y--;
		}else if(this.direction.equals("W")) {
			this.x--;
		}else if(this.direction.equals("E")) {
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
