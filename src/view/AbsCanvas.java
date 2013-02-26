package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

import controller.PlayController;

public class AbsCanvas extends Canvas implements Observer {
	
		private BufferedImage buff;
		private Graphics2D buffGraphic;
		private boolean firstRun;
		private PlayController pc;
		private Timer time;
		private ArrayList<String> updates;
		private String tmp;
		
		AbsCanvas(){
			setSize(800,800);
			setMaximumSize(new Dimension(800,800));
			firstRun = false;
			setVisible(true);
			updates = new ArrayList<String>();
			
		}
		
		public void addController(PlayController pc){
			this.pc = pc;
			addKeyListener(pc);
			addMouseListener(pc);
			time = new Timer(50, pc);
		}
		
		public void start(){
			time.start();
		}
		
		public void paint(Graphics g){
			super.paint(g);
			if(!firstRun){
			buff = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
			buff = (BufferedImage) createImage(800,800);
			firstRun = true;
			buffGraphic = buff.createGraphics();
			buffGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			buffGraphic.setColor(Color.BLACK);
			buffGraphic.fillRect(0, 0, 800, 800);
			//buffGraphic.drawString("RAAAAWWWRRW WRWRWRWRWRWRWRWRW", 340, 400);
			}else{
				//buffGraphic.setColor(Color.BLACK);
				//buffGraphic.fillRect(0, 0, 800, 800);
				Iterator<String> it = updates.iterator();
			while(it.hasNext()){
				tmp = it.next();
			
				if(tmp.contains("A")){
					buffGraphic.setColor(Color.BLUE);
					buffGraphic.fillRect(pc.getGizX(tmp)*40, pc.getGizY(tmp)*40, pc.getGizWidth(tmp)*40, pc.getGizHeight(tmp)*40);
					System.out.println("abs");
				}else if(tmp.contains("B")){
					buffGraphic.setColor(Color.WHITE);
					System.out.println("X: " + pc.getBallX(tmp) + " Y: " + pc.getBallY(tmp) + " Ball : " + tmp);
					buffGraphic.fillOval((int)(pc.getBallX(tmp)*40), (int)(pc.getBallY(tmp)*40), (int)(pc.getBallRadius(tmp)*40), (int)(pc.getBallRadius(tmp)*40));
					
				}
				it.remove();
			}
			}
			//buffGraphic.dispose();
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(buff, 0, 0, this);
			
			
		}

		@Override
		public void update(Observable o, Object arg) {
			String giz = (String) arg;
			updates.add(giz);
			repaint();
		}

}
