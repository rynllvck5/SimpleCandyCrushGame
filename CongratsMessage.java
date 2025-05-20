import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class CongratsMessage extends JFrame {
	int timerLeft = 5;
	int timerCenter = 5;
	int timerRigth = 5;
	int fontSize = 150;
	double score;
	AudioInputStream audioInputStream;
	Clip entered;
	Clip exited;
	Clip clicked;
	Clip bgm;
	
	public CongratsMessage(boolean left, boolean center, boolean right, double score) {
		initComponents();
		backgroundMusic();
		stars(left, center, right);
		this.score = score;
		setScore();
		showScore();
	}

	private void backgroundMusic(){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/CongratsMessage.wav"));
			bgm = AudioSystem.getClip();
			bgm.open(audioInputStream);
			bgm.loop(Clip.LOOP_CONTINUOUSLY);
			bgm.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}

	private void showScore(){
		lblScore.setFont(new Font("Harlow Solid Italic", Font.PLAIN, fontSize));
		Timer t=new Timer();
		t.schedule(new TimerTask(){
			public void run(){
				lblScore.setVisible(true);
				if(fontSize >= 35){
					fontSize = fontSize - 5;
					lblScore.setFont(new Font("Harlow Solid Italic", Font.PLAIN, fontSize));
				}
			}
		}, 2000,25);
	}

	private void setScore(){
		lblScore.setText((int)score+"");

	}

	private void stars(boolean left, boolean center, boolean right){
		if(left == true){
			lblLeftStar.setVisible(true);
			lblLeftStar.setBounds(lblLeftStar.getX()-100, lblLeftStar.getY()-100, lblLeftStar.getWidth(), lblLeftStar.getHeight());
			Timer t=new Timer();
			t.schedule(new TimerTask(){
				public void run(){
					if(timerLeft <= 100){
						lblLeftStar.setBounds(lblLeftStar.getX()+5, lblLeftStar.getY()+5, lblLeftStar.getWidth(), lblLeftStar.getHeight());
						timerLeft = timerLeft + 5;
					}else{
						t.cancel();
					}
				}
			}, 500,25);
		}else{
			lblLeftStar.setVisible(false);
		}

		if(center == true){
			lblCenterStar.setVisible(true);
			lblCenterStar.setBounds(lblCenterStar.getX()-100, lblCenterStar.getY()-100, lblCenterStar.getWidth(), lblCenterStar.getHeight());
			Timer t=new Timer();
			t.schedule(new TimerTask(){
				public void run(){
					if(timerCenter <= 100){
						lblCenterStar.setBounds(lblCenterStar.getX()+5, lblCenterStar.getY()+5, lblCenterStar.getWidth(), lblCenterStar.getHeight());
						timerCenter = timerCenter + 5;
					}else{
						t.cancel();
					}
				}
			}, 1000,25);
		}else{
			lblCenterStar.setVisible(false);
		}

		if(right == true){
			lblRightStar.setVisible(true);
			lblRightStar.setBounds(lblRightStar.getX()-100, lblRightStar.getY()-100, lblRightStar.getWidth(), lblRightStar.getHeight());
			Timer t=new Timer();
			t.schedule(new TimerTask(){
				public void run(){
					if(timerRigth <= 100){
						lblRightStar.setBounds(lblRightStar.getX()+5, lblRightStar.getY()+5, lblRightStar.getWidth(), lblRightStar.getHeight());
						timerRigth = timerRigth + 5;
					}else{
						t.cancel();
					}
				}
			}, 1500,25);
		}else{
			lblRightStar.setVisible(false);
		}
	}

	private void lblRetryMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblRetry.setBounds(lblRetry.getX()-5, lblRetry.getY()-5, lblRetry.getWidth(), lblRetry.getHeight());
	}

	private void lblRetryMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblRetry.setBounds(lblRetry.getX()+5, lblRetry.getY()+5, lblRetry.getWidth(), lblRetry.getHeight());
	}

	private void lblExitMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblExit.setBounds(lblExit.getX()-5, lblExit.getY()-5, lblExit.getWidth(), lblExit.getHeight());
	}

	private void lblExitMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblExit.setBounds(lblExit.getX()+5, lblExit.getY()+5, lblExit.getWidth(), lblExit.getHeight());
	}

	private void lblRetryMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		};

		
		bgm.close();
		this.dispose();
		new AbuanCandyCrush().setVisible(true);
	}

	private void lblExitMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		};

		bgm.close();
		this.dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
		lblScore = new JLabel();
		lblRetry = new JLabel();
		lblExit = new JLabel();
		lblRightStar = new JLabel();
		lblCenterStar = new JLabel();
		lblLeftStar = new JLabel();
		lblBackground = new JLabel();

		//======== this ========
		setTitle("Congratulations!");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- lblScore ----
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblScore.setForeground(Color.white);
		lblScore.setVisible(false);
		contentPane.add(lblScore);
		lblScore.setBounds(35, 70, 400, 180);

		//---- lblRetry ----
		lblRetry.setIcon(new ImageIcon("Images\\retry.png"));
		lblRetry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblRetryMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRetryMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRetryMouseExited(e);
			}
		});
		contentPane.add(lblRetry);
		lblRetry.setBounds(240, 265, 150, 46);

		//---- lblExit ----
		lblExit.setIcon(new ImageIcon("Images\\exit.png"));
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblExitMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExitMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExitMouseExited(e);
			}
		});
		contentPane.add(lblExit);
		lblExit.setBounds(60, 265, 150, 46);

		//---- lblRightStar ----
		lblRightStar.setIcon(new ImageIcon("Images\\rightStar.png"));
		contentPane.add(lblRightStar);
		lblRightStar.setBounds(317, 33, 100, 100);

		//---- lblCenterStar ----
		lblCenterStar.setIcon(new ImageIcon("Images\\centerStar.png"));
		contentPane.add(lblCenterStar);
		lblCenterStar.setBounds(188, 5, 100, 100);

		//---- lblLeftStar ----
		lblLeftStar.setIcon(new ImageIcon("Images\\leftStar.png"));
		contentPane.add(lblLeftStar);
		lblLeftStar.setBounds(60, 35, 100, 100);

		//---- lblBackground ----
		lblBackground.setIcon(new ImageIcon("Images\\CongratsMessage.jpg"));
		contentPane.add(lblBackground);
		lblBackground.setBounds(0, 0, 470, 325);

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
		setSize(470, 355);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
	private JLabel lblScore;
	private JLabel lblRetry;
	private JLabel lblExit;
	private JLabel lblRightStar;
	private JLabel lblCenterStar;
	private JLabel lblLeftStar;
	private JLabel lblBackground;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
