package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

//Bouncing Ball Program

        public abstract class Main extends JPanel implements ActionListener {

            int width;
            int height;
            float radius = 25;
            float diameter = 100;
            float ballSize1 = 70;
            float ballSize2 = 50;
            float direction1 = 7;
            float direction2 = 7;

            public Main() {

                Thread ball = new Thread(() -> {
                    while (true) {
                        width = getWidth();
                        height = getHeight();

                        ballSize1 = ballSize1 + direction1;
                        ballSize2 = ballSize2 + direction2;

                        if (ballSize1 - radius < 0) {
                            direction1 = -direction1;
                            ballSize1 = radius;
                        } else if (ballSize1 + radius > width) {
                            direction1 = -direction1;
                            ballSize1 = width - radius;
                        }
                        if (ballSize2 - radius < 0) {
                            direction2 = -direction2;
                            ballSize2 = radius;
                        } else if (ballSize2 + radius > height) {
                            direction2 = -direction2;
                            ballSize2 = height - radius;
                        }
                        repaint();

                        try {
                            TimeUnit.MILLISECONDS.sleep(45L);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                ball.start();
            }
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.MAGENTA);
                g.fillOval((int) (ballSize1 - radius), (int) (ballSize2 - radius), (int) diameter, (int) diameter);
            }

            public static void main(String[] args) {
                JFrame frame = new JFrame("Bouncing Ball Program");
                frame.setSize(500, 400);
                frame.setContentPane(new com.company.Main() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }