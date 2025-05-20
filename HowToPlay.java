import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class HowToPlay extends JFrame {
	AudioInputStream audioInputStream;
	int htp = 0;
	Clip entered;
	Clip exited;
	Clip clicked;

	public HowToPlay() {
		initComponents();
	}

	private void htpSetter(){
		if(htp == 0){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay.jpg"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(false);
			lblDone.setVisible(false);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(false);
		}else if(htp == 1){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(true);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(false);
			lblDone.setVisible(false);
		}else if(htp == 2){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(true);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(false);
			lblDone.setVisible(false);
		}else if(htp == 3){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(true);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(false);
			lblDone.setVisible(false);
		}else if(htp == 4){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(true);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(false);
			lblDone.setVisible(false);
		}else if(htp == 5){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(true);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(false);
			lblDone.setVisible(false);
		}else if(htp == 6){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(true);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(true);
			lblHTP7.setVisible(false);
			lblDone.setVisible(false);
		}else if(htp == 7){
			lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay4.png"));
			lblNext.setVisible(false);
			lblPrevious.setVisible(true);
			lblHTP1.setVisible(false);
			lblHTP2.setVisible(false);
			lblHTP3.setVisible(false);
			lblHTP4.setVisible(false);
			lblHTP5.setVisible(false);
			lblHTP6.setVisible(false);
			lblHTP7.setVisible(true);
			lblDone.setVisible(true);
		}
	}

	private void lblPreviousMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblPrevious.setIcon(new ImageIcon("Images\\previous2.png"));
	}

	private void lblPreviousMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblPrevious.setIcon(new ImageIcon("Images\\previous.png"));
	}

	private void lblPreviousMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		};

		if(htp > 0 && htp <= 7){
			htp = htp - 1;
		}
		htpSetter();
	}

	private void lblNextMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblNext.setIcon(new ImageIcon("Images\\next2.png"));
	}

	private void lblNextMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblNext.setIcon(new ImageIcon("Images\\next.png"));
	}

	private void lblNextMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		};
		if(htp >= 0 && htp < 7){
			htp = htp + 1;
		}
		htpSetter();
	}

	private void lblDoneMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblDone.setForeground(Color.RED);
		lblDone.setFont(new Font("Segoe UI Black", Font.PLAIN, 80));
	}

	private void lblDoneMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblDone.setForeground(Color.black);
		lblDone.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
	}

	private void lblDoneMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		};
		this.dispose();
	}

	private void thisKeyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
			if(htp >= 0 && htp < 7){
				htp = htp + 1;
			}
			htpSetter();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};

			if(htp > 0 && htp <= 7){
				htp = htp - 1;
			}
			htpSetter();
			}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(lblDone.isVisible()){
					try {
						audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
						clicked = AudioSystem.getClip();
						clicked.open(audioInputStream);
						clicked.start();
					} catch (Exception e3) {
						e3.printStackTrace();
					};
					this.dispose();
				}
			}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(lblDone.isVisible()){
					try {
						audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
						clicked = AudioSystem.getClip();
						clicked.open(audioInputStream);
						clicked.start();
					} catch (Exception e3) {
						e3.printStackTrace();
					};
					this.dispose();
				}else{
					try {
						audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
						clicked = AudioSystem.getClip();
						clicked.open(audioInputStream);
						clicked.start();
					} catch (Exception e3) {
						e3.printStackTrace();
					};
					if(htp >= 0 && htp < 7){
						htp = htp + 1;
					}
					htpSetter();
				}
			}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
		lblDone = new JLabel();
		lblPrevious = new JLabel();
		lblNext = new JLabel();
		lblHTP1 = new JLabel();
		lblHTP2 = new JLabel();
		lblHTP3 = new JLabel();
		lblHTP4 = new JLabel();
		lblHTP5 = new JLabel();
		lblHTP6 = new JLabel();
		lblHTP7 = new JLabel();
		lblHowToPlay = new JLabel();

		//======== this ========
		setTitle("Game Mechanics");
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				thisKeyPressed(e);
			}
		});
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- lblDone ----
		lblDone.setText("DONE");
		lblDone.setHorizontalAlignment(SwingConstants.CENTER);
		lblDone.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
		lblDone.setBackground(new Color(0x66ffcc));
		lblDone.setVisible(false);
		lblDone.setFocusable(false);
		lblDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblDoneMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDoneMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblDoneMouseExited(e);
			}
		});
		contentPane.add(lblDone);
		lblDone.setBounds(335, 450, 275, 140);

		//---- lblPrevious ----
		lblPrevious.setBackground(new Color(0x66ffcc));
		lblPrevious.setIcon(new ImageIcon("Images\\previous.png"));
		lblPrevious.setVisible(false);
		lblPrevious.setFocusable(false);
		lblPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblPreviousMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPreviousMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPreviousMouseExited(e);
			}
		});
		contentPane.add(lblPrevious);
		lblPrevious.setBounds(95, 225, 95, 95);

		//---- lblNext ----
		lblNext.setBackground(new Color(0x66ffcc));
		lblNext.setIcon(new ImageIcon("Images\\next.png"));
		lblNext.setFocusable(false);
		lblNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNextMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNextMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNextMouseExited(e);
			}
		});
		contentPane.add(lblNext);
		lblNext.setBounds(740, 225, 95, 95);

		//---- lblHTP1 ----
		lblHTP1.setIcon(new ImageIcon("Images\\htp1.png"));
		lblHTP1.setVisible(false);
		lblHTP1.setFocusable(false);
		contentPane.add(lblHTP1);
		lblHTP1.setBounds(350, 150, 290, 270);

		//---- lblHTP2 ----
		lblHTP2.setIcon(new ImageIcon("Images\\htp2.png"));
		lblHTP2.setVisible(false);
		lblHTP2.setFocusable(false);
		contentPane.add(lblHTP2);
		lblHTP2.setBounds(345, 120, 280, 315);

		//---- lblHTP3 ----
		lblHTP3.setIcon(new ImageIcon("Images\\htp3.png"));
		lblHTP3.setVisible(false);
		lblHTP3.setFocusable(false);
		contentPane.add(lblHTP3);
		lblHTP3.setBounds(340, 150, 295, 245);

		//---- lblHTP4 ----
		lblHTP4.setIcon(new ImageIcon("Images\\htp4.png"));
		lblHTP4.setVisible(false);
		lblHTP4.setFocusable(false);
		contentPane.add(lblHTP4);
		lblHTP4.setBounds(340, 90, 285, 365);

		//---- lblHTP5 ----
		lblHTP5.setIcon(new ImageIcon("Images\\htp5.png"));
		lblHTP5.setVisible(false);
		lblHTP5.setFocusable(false);
		contentPane.add(lblHTP5);
		lblHTP5.setBounds(320, 160, 360, 240);

		//---- lblHTP6 ----
		lblHTP6.setIcon(new ImageIcon("Images\\htp6.png"));
		lblHTP6.setVisible(false);
		lblHTP6.setFocusable(false);
		contentPane.add(lblHTP6);
		lblHTP6.setBounds(315, 165, 375, 230);

		//---- lblHTP7 ----
		lblHTP7.setIcon(new ImageIcon("Images\\htp7.png"));
		lblHTP7.setVisible(false);
		lblHTP7.setFocusable(false);
		contentPane.add(lblHTP7);
		lblHTP7.setBounds(325, 85, 355, 370);

		//---- lblHowToPlay ----
		lblHowToPlay.setIcon(new ImageIcon("Images\\HowToPlay.jpg"));
		lblHowToPlay.setFocusable(false);
		contentPane.add(lblHowToPlay);
		lblHowToPlay.setBounds(5, 5, 970, 590);

		{
			// compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		setSize(970, 635);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
	private JLabel lblDone;
	private JLabel lblPrevious;
	private JLabel lblNext;
	private JLabel lblHTP1;
	private JLabel lblHTP2;
	private JLabel lblHTP3;
	private JLabel lblHTP4;
	private JLabel lblHTP5;
	private JLabel lblHTP6;
	private JLabel lblHTP7;
	private JLabel lblHowToPlay;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
