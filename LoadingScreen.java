import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class LoadingScreen extends JFrame {
	private int loadingValue;
	AudioInputStream audioInputStream;
	Clip entered;
	Clip exited;
	Clip clicked;
	Clip htp;
	
	public LoadingScreen() {
		initComponents();
		backgroundMusic();
		loading();
	}

	private void backgroundMusic(){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/HowToPlay.wav"));
			htp = AudioSystem.getClip();
			htp.open(audioInputStream);
			htp.loop(Clip.LOOP_CONTINUOUSLY);
			htp.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}
	private void loading(){
		Timer t=new Timer();
		t.schedule(new TimerTask(){
			public void run(){
				loadingValue=loadingValue+1;
				LoadingBar.setValue(loadingValue);
				lblCar.setBounds(lblCar.getX()+8, lblCar.getY(), lblCar.getWidth(), lblCar.getHeight());
				if(loadingValue % 10 == 0){
					lblLoading.setVisible(false);
				}else{
					lblLoading.setVisible(true);
				}
				if(loadingValue==100){
					lblCar.setVisible(false);
					lblLoading.setVisible(false);
					lblPlay.setVisible(true);
					lblHowToPlay.setVisible(true);
					t.cancel();
				}
			}
		}, 1000,100);
	}

	private void lblPlayMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		htp.close();
		this.dispose();
		new AbuanCandyCrush().setVisible(true);
	}

	private void lblPlayMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}

		lblPlay.setBounds(lblPlay.getX()-5, lblPlay.getY()-5, lblPlay.getWidth(), lblPlay.getHeight());
	}

	private void lblPlayMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblPlay.setBounds(lblPlay.getX()+5, lblPlay.getY()+5, lblPlay.getWidth(), lblPlay.getHeight());
	}

	private void lblHowToPlayMouseEntered(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
			entered = AudioSystem.getClip();
			entered.open(audioInputStream);
			entered.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblHowToPlay.setBounds(lblHowToPlay.getX()-5, lblHowToPlay.getY()-5, lblHowToPlay.getWidth(), lblHowToPlay.getHeight());
	}

	private void lblHowToPlayMouseExited(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
			exited = AudioSystem.getClip();
			exited.open(audioInputStream);
			exited.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		lblHowToPlay.setBounds(lblHowToPlay.getX()+5, lblHowToPlay.getY()+5, lblHowToPlay.getWidth(), lblHowToPlay.getHeight());
	}

	private void lblHowToPlayMouseClicked(MouseEvent e) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
			clicked = AudioSystem.getClip();
			clicked.open(audioInputStream);
			clicked.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		};
		new HowToPlay().setVisible(true);
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
		lblHowToPlay = new JLabel();
		lblPlay = new JLabel();
		lblCar = new JLabel();
		lblLoading = new JLabel();
		LoadingBar = new JProgressBar();
		lblBackground = new JLabel();

		//======== this ========
		setTitle("AbuanCandyCrush");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- lblHowToPlay ----
		lblHowToPlay.setIcon(new ImageIcon("Images\\howtoplay.png"));
		lblHowToPlay.setVisible(false);
		lblHowToPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblHowToPlayMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHowToPlayMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblHowToPlayMouseExited(e);
			}
		});
		contentPane.add(lblHowToPlay);
		lblHowToPlay.setBounds(215, 340, 410, 60);

		//---- lblPlay ----
		lblPlay.setIcon(new ImageIcon("Images\\PlayButton.png"));
		lblPlay.setVisible(false);
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblPlayMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPlayMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPlayMouseExited(e);
			}
		});
		contentPane.add(lblPlay);
		lblPlay.setBounds(295, 265, 230, 60);

		//---- lblCar ----
		lblCar.setIcon(new ImageIcon("Images\\CarLoading.png"));
		contentPane.add(lblCar);
		lblCar.setBounds(-150, 175, 315, 275);

		//---- lblLoading ----
		lblLoading.setText("Loading...");
		lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoading.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 48));
		lblLoading.setForeground(Color.orange);
		contentPane.add(lblLoading);
		lblLoading.setBounds(250, 170, 345, 115);

		//---- LoadingBar ----
		LoadingBar.setBackground(new Color(0x3fff32ff, true));
		LoadingBar.setForeground(new Color(0x6a336a));
		contentPane.add(LoadingBar);
		LoadingBar.setBounds(35, 445, 800, 14);

		//---- lblBackground ----
		lblBackground.setIcon(new ImageIcon("Images\\LoadingScreenBackground.jpg"));
		contentPane.add(lblBackground);
		lblBackground.setBounds(0, 0, 870, 470);

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
		setSize(870, 505);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
	private JLabel lblHowToPlay;
	private JLabel lblPlay;
	private JLabel lblCar;
	private JLabel lblLoading;
	private JProgressBar LoadingBar;
	private JLabel lblBackground;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
