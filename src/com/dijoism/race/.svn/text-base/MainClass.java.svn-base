package com.dijoism.race;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainClass extends JFrame {
  public static void main(String args[]) {
    MainClass app = new MainClass();
  }

  public MainClass() {
    super("Draw2D");
    add("Center", new MyCanvas());

    setSize(100, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  class MyCanvas extends Canvas {
    public void paint(Graphics graphics) {
      Graphics2D g = (Graphics2D) graphics;
      float alpha = 1f;
      AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
      g.setComposite(composite);
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Image image0 = toolkit.getImage("dirt.gif");
      Image image1 = toolkit.getImage("token1.gif");
      g.drawImage(image0, 0, 0, this);
      g.drawImage(image1, 10, 10, this);
     /* JLabel poo;
      poo();*/
    }
  }
}
