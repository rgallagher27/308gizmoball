package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Timer;

import model.Absorber;
import model.IOverlord;
import model.iBall;
import model.iGizmo;
import physics.*;

import view.AbsCanvas;

public class PlayController implements MouseListener, KeyListener, ActionListener {
	
	private IOverlord overlord;
	private AbsCanvas ab;
	private Timer time;
	
	public PlayController(AbsCanvas abs, IOverlord ov){
		ab = abs;
		overlord = ov;
		time = new Timer(5, this);
		time.setActionCommand("physics");
	}
	
	public int getGizX(String name){
		return overlord.getGizmo(name).getLocation().getX();
	}

	public int getGizY(String name){
		return overlord.getGizmo(name).getLocation().getY();
	}
	
	public int getGizWidth(String name){
		return overlord.getGizmo(name).getWidth();
	}
	
	public int getGizHeight(String name){
		return overlord.getGizmo(name).getHeight();
	}
	
	public float getBallX(String name){
		return overlord.getBall(name).getLocation().getX();
	}

	public float getBallY(String name){
		return overlord.getBall(name).getLocation().getY();
	}
	
	public double getBallRadius(String name){
		return overlord.getBall(name).getRadius();
	}
	
	
	
	

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_SPACE){
			for(iGizmo giz : overlord.getGizmos()){
				if(giz.getName().contains("A")){
					System.out.println(giz.getName() + " is launching a ball!");
					giz.doAction();
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println("MouseX: " + me.getX() / 40.0 + "MouseY: " + me.getY() / 40.0);
		System.out.println("Source : " + me.getSource());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand() == null){
			time.start();
		}else{
	
		ArrayList<iBall> balls =  (ArrayList<iBall>) overlord.getBalls();
		Circle circle;
		ArrayList<String> collisions;
		ArrayList<LineSegment> segments;
		double timeUntilCol;
		double vOldX;
		double vOldY;
		double newVX;
		double newVY;
		
		for(iBall ball : balls){
			if(ball.getReleased()){  //ball is released and moving.
				//System.out.println(ball.getName());
				vOldX = ball.getVelocityX();
				vOldY = ball.getVelocityY();
				newVX = (vOldX * (1 - overlord.getFrictionMu() * (0.005) - overlord.getFrictionMu2() * Math.abs(vOldX) * (0.005)));
				newVY = (vOldY * (1 - overlord.getFrictionMu() * (0.005) - overlord.getFrictionMu2() * Math.abs(vOldY) * (0.005)));
			
				if(newVX > 200){
					newVX = 200;
				}else if(newVX < 0){
					newVX = 0;
				}
				if(newVY > 200){
					newVY = 200;
				}else if(newVY < 0){
					newVY = 0;
				}
				System.out.println("vOldX: " + vOldX + " vOldY: " + vOldY + " newVX: " + newVX + " newVY: " + newVY);
				
				ball.setVelocity(newVX, newVY);
				//Vnew = Vold * (1 - mu * delta_t - mu2 * |Vold| * delta_t) 
			//	System.out.println("vOldX = " + vOldX + " vOldY = " + vOldY);
				vOldX = ball.getVelocityX();
				vOldY = ball.getVelocityY();
				newVX = (vOldX + overlord.getGravity()*(0.005));
				newVY = (vOldY + overlord.getGravity()*(0.005));
				if(newVX > 200){
					newVX = 200;
				}else if(newVX < 0){
					newVX = 0;
				}
				if(newVY > 200){
					newVY = 200;
				}else if(newVY < 0){
					newVY = 0;
				}
				
				System.out.println("vOldX: " + vOldX + " vOldY: " + vOldY + " newVX: " + newVX + " newVY: " + newVY);
				ball.setVelocity(newVX, newVY);
				//Vnew = Vold + Gravity*delta_t 	
				
				for(iGizmo giz : overlord.getGizmos()){
							timeUntilCol = giz.timeTillCol(ball);
							if(timeUntilCol < Double.POSITIVE_INFINITY && timeUntilCol < (0.005) /*&& ball.getImmunity() < 0*/){
								overlord.setBallLocation(ball.getName(), (ball.getLocation().getX() + (float)(ball.getVelocityX() * timeUntilCol)), (ball.getLocation().getY() + (float)(ball.getVelocityY() * timeUntilCol)));
								
								giz.collide(ball);
								double leftTime = Math.abs(timeUntilCol - (0.005));
								if(leftTime > 0){
									overlord.setBallLocation(ball.getName(), (ball.getLocation().getX() + (float)(ball.getVelocityX() * leftTime)), (ball.getLocation().getY() + (float)(ball.getVelocityY() * leftTime)));
									
								}
									
							}else{
								ball.setImmunity(ball.getImmunity()-1);
								overlord.setBallLocation(ball.getName(),(ball.getLocation().getX() + (float)(ball.getVelocityX() * (0.005))), (ball.getLocation().getY() + (float)(ball.getVelocityY() * (0.005))));
							}
						}
			}
		}
		}
		
		
	}
	
	
	private boolean getDirection(double d){
		if(d < 0) return true;
		return false;
		
	}

}
