import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.*;

public class AbuanCandyCrush extends JFrame {
	int mouseXStart;
	int mouseYStart;
	int iconXStart;
	int iconYStart;
	int iconXEnd;
	int iconYEnd;
	boolean changeExist;
	double score;
	double progressBarValue;
	int finalProgBarValue;
	int moves = 10;
	AudioInputStream audioInputStream;
	Clip shuffle1;
	Clip entered;
	Clip exited;
	Clip clicked;
	Clip swapTrue;
	Clip swapFalse;
	Clip changeIcons;
	Clip mainGame;
	boolean soundEffect = true;
	boolean music = true;

	public AbuanCandyCrush() {
		initComponents();
		backgroundMusic();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void backgroundMusic(){
		try {
			audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/MainGame1.wav"));
			mainGame = AudioSystem.getClip();
			mainGame.open(audioInputStream);
			mainGame.loop(Clip.LOOP_CONTINUOUSLY);
			mainGame.start();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
	}
	
	private void stillExistingChecker(){
		Component candyCol1;
		Component candyCol2;
		Component candyCol3;
		Component candyCol4;
		Component candyCol5;
		Component candyRow1;
		Component candyRow2;
		Component candyRow3;
		Component candyRow4;
		Component candyRow5;

		for(int i=0; i < pnlCandies.getComponentCount(); i++){
			if(i == 0){
				candyCol1 = pnlCandies.getComponent(i);
				candyRow1 = pnlCandies.getComponent(i);
				horizontalChecker(((JLabel)candyRow1));
				verticalChecker(((JLabel)candyCol1));
			}else if(i == 1){
				candyCol2 = pnlCandies.getComponent(i);
				verticalChecker(((JLabel)candyCol2));
			}else if(i == 2){
				candyCol3 = pnlCandies.getComponent(i);
				verticalChecker(((JLabel)candyCol3));
			}else if(i == 3){
				candyCol4 = pnlCandies.getComponent(i);
				verticalChecker(((JLabel)candyCol4));
			}else if(i == 4){
				candyCol5 = pnlCandies.getComponent(i);
				verticalChecker(((JLabel)candyCol5));
			}else if(i == 5){
				candyRow2 = pnlCandies.getComponent(i);
				horizontalChecker(((JLabel)candyRow2));
			}else if(i == 10){
				candyRow3 = pnlCandies.getComponent(i);
				horizontalChecker(((JLabel)candyRow3));
			}else if(i == 15){
				candyRow4 = pnlCandies.getComponent(i);
				horizontalChecker(((JLabel)candyRow4));
			}else if(i == 20){
				candyRow5 = pnlCandies.getComponent(i);
				horizontalChecker(((JLabel)candyRow5));
			}
		}
	}

	private boolean verticalChecker(JLabel targetCandy){
		boolean found = false;
		String firstName = "";
		int count = 0;
		int similar[] = new int[5];
		int a = 0;
		int z = 0;
		int changedCount = 0;

		int x = targetCandy.getX();

		for(int i = 0; i < pnlCandies.getComponentCount(); i++){
			
			if(found == false){
				while(true){
					Component candy1 = pnlCandies.getComponent(z);
					if(((JLabel)candy1).getX() == x){
						i = z;
						found = true;
						break;
					}
					z++;
				}
			}

			Component candy = pnlCandies.getComponent(i);

			if(((JLabel)candy).getIcon().toString().equals(firstName)){
				count++;
				similar[a] = i;
				a++;
			}else{
				firstName = ((JLabel)candy).getIcon().toString();
				if(count < 3){
					count = 1;
					similar[0] = i;
					similar[1] = 0;
					similar[2] = 0;
					similar[3] = 0;
					similar[4] = 0;
					a=1;
				}
			}
			i = i + 4;
		}
		

		if(count >= 3){
			for(int i = 0; i < pnlCandies.getComponentCount(); i++){
				for(int j = 0; j < similar.length; j++){
					if(j < count){
						if(i == similar[j]){
							Component candy = pnlCandies.getComponent(i);
								Random r = new Random();
								int num = r.nextInt(6)+1;
								if(num == 1){
									((JLabel)candy).setIcon(new ImageIcon("Images\\BluePlanet.png"));
								}else if(num == 2){
									((JLabel)candy).setIcon(new ImageIcon("Images\\GreenPillow.png"));
								}else if(num == 3){
									((JLabel)candy).setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
								}else if(num == 4){
									((JLabel)candy).setIcon(new ImageIcon("Images\\PurpleFlower.png"));
								}else if(num == 5){
									((JLabel)candy).setIcon(new ImageIcon("Images\\RedSausage.png"));
								}else{
									((JLabel)candy).setIcon(new ImageIcon("Images\\YellowTearDrop.png"));
								}
							i++;
							changedCount++;
							changeExist = true;
						}
					}
				}	
			}
			
			score = score + (count*200); 
			lblScoreNum.setText((int)score+"");

			progressBarValue = ((score/(Double.parseDouble(lblTargetNum.getText()))))*100;
			finalProgBarValue = (int) progressBarValue;
			if(finalProgBarValue >= 32){
				lblFirstStar.setEnabled(true);
			}
			if(finalProgBarValue >= 64){
				lblSecondStar.setEnabled(true);
			}
			if(finalProgBarValue >= 100){
				lblThirdStar.setEnabled(true);
			}
			ScoreProgressBar.setValue(finalProgBarValue);
		}else{
			changeExist = false;
		}

		if(changeExist == true){
			
			stillExistingChecker();
		}

		if(changedCount > 0){
			if(soundEffect == true){
				try {
					audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/swapSuccessful.wav"));
					swapTrue = AudioSystem.getClip();
					swapTrue.open(audioInputStream);
					swapTrue.start();
				} catch (Exception e3) {
					e3.printStackTrace();
				};
			}
			return true;
		}else{
			if(soundEffect == true){
				try {
					audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/swapfail.wav"));
					swapFalse = AudioSystem.getClip();
					swapFalse.open(audioInputStream);
					swapFalse.start();
				} catch (Exception e3) {
					e3.printStackTrace();
				};
			}
			return false;
		}
	}

	private boolean horizontalChecker(JLabel targetCandy){
		String firstName = "";
		int count = 0;
		int similar[] = new int[5];
		int a = 0;
		int z = 0;
		int changedCount = 0;
		boolean found = false;

		int y = targetCandy.getY();

		for(int i = 0; i < pnlCandies.getComponentCount(); i++){
			if(found == false){
				while(true){
					Component candy1 = pnlCandies.getComponent(z);
					if(((JLabel)candy1).getY() == y){
						i = z;
						found = true;
						break;
					}
					z++;
				}
			}

			Component candy = pnlCandies.getComponent(i);

			if((((JLabel)candy).getY()) != targetCandy.getY()){
				break;
			}
			
			if(((JLabel)candy).getIcon().toString().equals(firstName)){
				count++;
				similar[a] = i;
				a++;
			}else{
				if(count < 3){
					count = 1;
					similar[0] = i;
					similar[1] = 0;
					similar[2] = 0;
					similar[3] = 0;
					similar[4] = 0;
					a=1;
					firstName = ((JLabel)candy).getIcon().toString();
				}
			}
		}

		if(count >= 3){
			for(int i = 0; i < pnlCandies.getComponentCount(); i++){
				for(int j = 0; j < similar.length; j++){
					if(j < count){
						if(i == similar[j]){
							Component candy = pnlCandies.getComponent(i);
								Random r = new Random();
								int num = r.nextInt(6)+1;
								if(num == 1){
									((JLabel)candy).setIcon(new ImageIcon("Images\\BluePlanet.png"));
								}else if(num == 2){
									((JLabel)candy).setIcon(new ImageIcon("Images\\GreenPillow.png"));
								}else if(num == 3){
									((JLabel)candy).setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
								}else if(num == 4){
									((JLabel)candy).setIcon(new ImageIcon("Images\\PurpleFlower.png"));
								}else if(num == 5){
									((JLabel)candy).setIcon(new ImageIcon("Images\\RedSausage.png"));
								}else{
									((JLabel)candy).setIcon(new ImageIcon("Images\\YellowTearDrop.png"));
								}
							i++;
							changedCount++;
							changeExist = true;
						}
					}
				}	
			}
		
			score = score + (count*200); 
			lblScoreNum.setText((int)score+"");

			progressBarValue = (score/(Double.parseDouble(lblTargetNum.getText())))*100;
			finalProgBarValue = (int) progressBarValue;
			if(finalProgBarValue >= 32){
				lblFirstStar.setEnabled(true);
			}
			if(finalProgBarValue >= 64){
				lblSecondStar.setEnabled(true);
			}
			if(finalProgBarValue >= 100){
				lblThirdStar.setEnabled(true);
			}
			ScoreProgressBar.setValue(finalProgBarValue);
		}else{
			changeExist = false;
		}
	
		if(changeExist == true){
			stillExistingChecker();
		}
		
		if(changedCount > 0){
			if(soundEffect == true){
				try {
					audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/swapSuccessful.wav"));
					swapTrue = AudioSystem.getClip();
					swapTrue.open(audioInputStream);
					swapTrue.start();
				} catch (Exception e3) {
					e3.printStackTrace();
				};
			}
			return true;
		}else{
			if(soundEffect == true){
				try {
					audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/swapfail.wav"));
					swapFalse = AudioSystem.getClip();
					swapFalse.open(audioInputStream);
					swapFalse.start();
				} catch (Exception e3) {
					e3.printStackTrace();
				};
			}
			return false;
		}
		
	}

	private void candy11MousePressed(MouseEvent e) {
		
		iconXStart = candy11.getX();
		iconYStart = candy11.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy11MouseDragged(MouseEvent e) {
		
		int x = candy11.getX() + e.getX() - mouseXStart;
		int y = candy11.getY() + e.getY() - mouseYStart;
		candy11.setBounds(x, y, candy11.getWidth(), candy11.getHeight());
		iconXEnd = candy11.getX();
		iconYEnd = candy11.getY();
	}

	private void candy11MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy11.setBounds(iconXStart, iconYStart, candy11.getWidth(), candy11.getHeight());
			Icon temp = candy12.getIcon();
			candy12.setIcon(candy11.getIcon());
			candy11.setIcon(temp);

			boolean swapped = horizontalChecker(candy12);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy12.getIcon();
				candy12.setIcon(candy11.getIcon());
				candy11.setIcon(temp1);
			}
			
			temp = candy12.getIcon();
			candy12.setIcon(candy11.getIcon());
			candy11.setIcon(temp);

			swapped = verticalChecker(candy12);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy12.getIcon();
				candy12.setIcon(candy11.getIcon());
				candy11.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy11.setBounds(iconXStart, iconYStart, candy11.getWidth(), candy11.getHeight());
			Icon temp = candy21.getIcon();
			candy21.setIcon(candy11.getIcon());
			candy11.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy21);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy21.getIcon();
				candy21.setIcon(candy11.getIcon());
				candy11.setIcon(temp1);
			}
			
			temp = candy21.getIcon();
			candy21.setIcon(candy11.getIcon());
			candy11.setIcon(temp);
			swapped = verticalChecker(candy21);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy21.getIcon();
				candy21.setIcon(candy11.getIcon());
				candy11.setIcon(temp1);
			}
		}else{
			candy11.setBounds(iconXStart, iconYStart, candy11.getWidth(), candy11.getHeight());
		}
	}

	private void candy43MousePressed(MouseEvent e) {
		
		iconXStart = candy43.getX();
		iconYStart = candy43.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy43MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy43.setBounds(iconXStart, iconYStart, candy43.getWidth(), candy43.getHeight());
			Icon temp = candy44.getIcon();
			candy44.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
			
			temp = candy44.getIcon();
			candy44.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			swapped = verticalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy43.setBounds(iconXStart, iconYStart, candy43.getWidth(), candy43.getHeight());
			Icon temp = candy33.getIcon();
			candy33.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
			
			temp = candy33.getIcon();
			candy33.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			swapped = verticalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy43.setBounds(iconXStart, iconYStart, candy43.getWidth(), candy43.getHeight());
			Icon temp = candy42.getIcon();
			candy42.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
			
			temp = candy42.getIcon();
			candy42.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			swapped = verticalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy43.setBounds(iconXStart, iconYStart, candy43.getWidth(), candy43.getHeight());
			Icon temp = candy53.getIcon();
			candy53.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy53);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy53.getIcon();
				candy53.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
			
			temp = candy53.getIcon();
			candy53.setIcon(candy43.getIcon());
			candy43.setIcon(temp);
			swapped = verticalChecker(candy53);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy53.getIcon();
				candy53.setIcon(candy43.getIcon());
				candy43.setIcon(temp1);
			}
		}else{
			candy43.setBounds(iconXStart, iconYStart, candy43.getWidth(), candy43.getHeight());
		}
	}

	private void candy43MouseDragged(MouseEvent e) {
		
		int x = candy43.getX() + e.getX() - mouseXStart;
		int y = candy43.getY() + e.getY() - mouseYStart;
		candy43.setBounds(x, y, candy43.getWidth(), candy43.getHeight());
		iconXEnd = candy43.getX();
		iconYEnd = candy43.getY();
	}

	private void candy12MousePressed(MouseEvent e) {
		
		iconXStart = candy12.getX();
		iconYStart = candy12.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy12MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy12.setBounds(iconXStart, iconYStart, candy12.getWidth(), candy12.getHeight());
			Icon temp = candy13.getIcon();
			candy13.setIcon(candy12.getIcon());
			candy12.setIcon(temp);

			boolean swapped = horizontalChecker(candy13);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
							Icon temp1 = candy13.getIcon();
				candy13.setIcon(candy12.getIcon());
				candy12.setIcon(temp1);
			}
			
			temp = candy13.getIcon();
			candy13.setIcon(candy12.getIcon());
			candy12.setIcon(temp);
			swapped = verticalChecker(candy13);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy13.getIcon();
				candy13.setIcon(candy12.getIcon());
				candy12.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy12.setBounds(iconXStart, iconYStart, candy12.getWidth(), candy12.getHeight());
			Icon temp = candy11.getIcon();
			candy11.setIcon(candy12.getIcon());
			candy12.setIcon(temp);

			boolean swapped = horizontalChecker(candy11);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy11.getIcon();
				candy11.setIcon(candy12.getIcon());
				candy12.setIcon(temp1);
			}
			
			temp = candy11.getIcon();
			candy11.setIcon(candy12.getIcon());
			candy12.setIcon(temp);
			swapped = verticalChecker(candy11);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy11.getIcon();
				candy11.setIcon(candy12.getIcon());
				candy12.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy12.setBounds(iconXStart, iconYStart, candy12.getWidth(), candy12.getHeight());
			Icon temp = candy22.getIcon();
			candy22.setIcon(candy12.getIcon());
			candy12.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy12.getIcon());
				candy12.setIcon(temp1);
			}
			
			temp = candy22.getIcon();
			candy22.setIcon(candy12.getIcon());
			candy12.setIcon(temp);
			swapped = verticalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy12.getIcon());
				candy12.setIcon(temp1);
			}
		}else{
			candy12.setBounds(iconXStart, iconYStart, candy12.getWidth(), candy12.getHeight());
		}
	}

	private void candy12MouseDragged(MouseEvent e) {
		
		int x = candy12.getX() + e.getX() - mouseXStart;
		int y = candy12.getY() + e.getY() - mouseYStart;
		candy12.setBounds(x, y, candy12.getWidth(), candy12.getHeight());
		iconXEnd = candy12.getX();
		iconYEnd = candy12.getY();
	}

	private void candy13MousePressed(MouseEvent e) {
		
		iconXStart = candy13.getX();
		iconYStart = candy13.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy13MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy13.setBounds(iconXStart, iconYStart, candy13.getWidth(), candy13.getHeight());
			Icon temp = candy14.getIcon();
			candy14.setIcon(candy13.getIcon());
			candy13.setIcon(temp);

			boolean swapped = horizontalChecker(candy14);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy14.getIcon();
				candy14.setIcon(candy13.getIcon());
				candy13.setIcon(temp1);
			}
			
			temp = candy14.getIcon();
			candy14.setIcon(candy13.getIcon());
			candy13.setIcon(temp);
			swapped = verticalChecker(candy14);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy14.getIcon();
				candy14.setIcon(candy13.getIcon());
				candy13.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy13.setBounds(iconXStart, iconYStart, candy13.getWidth(), candy13.getHeight());
			Icon temp = candy12.getIcon();
			candy12.setIcon(candy13.getIcon());
			candy13.setIcon(temp);

			boolean swapped = horizontalChecker(candy12);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy12.getIcon();
				candy12.setIcon(candy13.getIcon());
				candy13.setIcon(temp1);
			}
			
			temp = candy12.getIcon();
			candy12.setIcon(candy13.getIcon());
			candy13.setIcon(temp);
			swapped = verticalChecker(candy12);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy12.getIcon();
				candy12.setIcon(candy13.getIcon());
				candy13.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy13.setBounds(iconXStart, iconYStart, candy13.getWidth(), candy13.getHeight());
			Icon temp = candy23.getIcon();
			candy23.setIcon(candy13.getIcon());
			candy13.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy13.getIcon());
				candy13.setIcon(temp1);
			}
			
			temp = candy23.getIcon();
			candy23.setIcon(candy13.getIcon());
			candy13.setIcon(temp);
			swapped = verticalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy13.getIcon());
				candy13.setIcon(temp1);
			}
		}else{
			candy13.setBounds(iconXStart, iconYStart, candy13.getWidth(), candy13.getHeight());
		}
	}
	

	private void candy13MouseDragged(MouseEvent e) {
		
		int x = candy13.getX() + e.getX() - mouseXStart;
		int y = candy13.getY() + e.getY() - mouseYStart;
		candy13.setBounds(x, y, candy13.getWidth(), candy13.getHeight());
		iconXEnd = candy13.getX();
		iconYEnd = candy13.getY();
	}

	private void candy14MousePressed(MouseEvent e) {
		
		iconXStart = candy14.getX();
		iconYStart = candy14.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy14MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy14.setBounds(iconXStart, iconYStart, candy14.getWidth(), candy14.getHeight());
			Icon temp = candy15.getIcon();
			candy15.setIcon(candy14.getIcon());
			candy14.setIcon(temp);

			boolean swapped = horizontalChecker(candy15);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy15.getIcon();
				candy15.setIcon(candy14.getIcon());
				candy14.setIcon(temp1);
			}
			
			temp = candy15.getIcon();
			candy15.setIcon(candy14.getIcon());
			candy14.setIcon(temp);
			swapped = verticalChecker(candy15);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy15.getIcon();
				candy15.setIcon(candy14.getIcon());
				candy14.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy14.setBounds(iconXStart, iconYStart, candy14.getWidth(), candy14.getHeight());
			Icon temp = candy13.getIcon();
			candy13.setIcon(candy14.getIcon());
			candy14.setIcon(temp);

			boolean swapped = horizontalChecker(candy13);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy13.getIcon();
				candy13.setIcon(candy14.getIcon());
				candy14.setIcon(temp1);
			}
			
			temp = candy13.getIcon();
			candy13.setIcon(candy14.getIcon());
			candy14.setIcon(temp);
			swapped = verticalChecker(candy13);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy13.getIcon();
				candy13.setIcon(candy14.getIcon());
				candy14.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy14.setBounds(iconXStart, iconYStart, candy14.getWidth(), candy14.getHeight());
			Icon temp = candy24.getIcon();
			candy24.setIcon(candy14.getIcon());
			candy14.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy14.getIcon());
				candy14.setIcon(temp1);
			}
			
			temp = candy24.getIcon();
			candy24.setIcon(candy14.getIcon());
			candy14.setIcon(temp);
			swapped = verticalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy14.getIcon());
				candy14.setIcon(temp1);
			}
		}else{
			candy14.setBounds(iconXStart, iconYStart, candy14.getWidth(), candy14.getHeight());
		}
	}

	private void candy14MouseDragged(MouseEvent e) {
		
		int x = candy14.getX() + e.getX() - mouseXStart;
		int y = candy14.getY() + e.getY() - mouseYStart;
		candy14.setBounds(x, y, candy14.getWidth(), candy14.getHeight());
		iconXEnd = candy14.getX();
		iconYEnd = candy14.getY();
	}

	private void candy15MousePressed(MouseEvent e) {
		
		iconXStart = candy15.getX();
		iconYStart = candy15.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy15MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy15.setBounds(iconXStart, iconYStart, candy15.getWidth(), candy15.getHeight());
			Icon temp = candy14.getIcon();
			candy14.setIcon(candy15.getIcon());
			candy15.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy14);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy14.getIcon();
				candy14.setIcon(candy15.getIcon());
				candy15.setIcon(temp1);
			}

			temp = candy14.getIcon();
			candy14.setIcon(candy15.getIcon());
			candy15.setIcon(temp);
			swapped = verticalChecker(candy14);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy14.getIcon();
				candy14.setIcon(candy15.getIcon());
				candy15.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy15.setBounds(iconXStart, iconYStart, candy15.getWidth(), candy15.getHeight());
			Icon temp = candy25.getIcon();
			candy25.setIcon(candy15.getIcon());
			candy15.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy25);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy25.getIcon();
				candy25.setIcon(candy15.getIcon());
				candy15.setIcon(temp1);
			}
			
			temp = candy25.getIcon();
			candy25.setIcon(candy15.getIcon());
			candy15.setIcon(temp);
			swapped = verticalChecker(candy25);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy25.getIcon();
				candy25.setIcon(candy15.getIcon());
				candy15.setIcon(temp1);
			}
		}else{
			candy15.setBounds(iconXStart, iconYStart, candy15.getWidth(), candy15.getHeight());
		}
	}

	private void candy15MouseDragged(MouseEvent e) {
		
		int x = candy15.getX() + e.getX() - mouseXStart;
		int y = candy15.getY() + e.getY() - mouseYStart;
		candy15.setBounds(x, y, candy15.getWidth(), candy15.getHeight());
		iconXEnd = candy15.getX();
		iconYEnd = candy15.getY();
	}

	private void candy21MousePressed(MouseEvent e) {
		
		iconXStart = candy21.getX();
		iconYStart = candy21.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy21MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy21.setBounds(iconXStart, iconYStart, candy21.getWidth(), candy21.getHeight());
			Icon temp = candy22.getIcon();
			candy22.setIcon(candy21.getIcon());
			candy21.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy21.getIcon());
				candy21.setIcon(temp1);
			}
			
			temp = candy22.getIcon();
			candy22.setIcon(candy21.getIcon());
			candy21.setIcon(temp);
			swapped = verticalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy21.getIcon());
				candy21.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy21.setBounds(iconXStart, iconYStart, candy21.getWidth(), candy21.getHeight());
			Icon temp = candy11.getIcon();
			candy11.setIcon(candy21.getIcon());
			candy21.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy11);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy11.getIcon();
				candy11.setIcon(candy21.getIcon());
				candy21.setIcon(temp1);
			}
			
			temp = candy11.getIcon();
			candy11.setIcon(candy21.getIcon());
			candy21.setIcon(temp);
			swapped = verticalChecker(candy11);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy11.getIcon();
				candy11.setIcon(candy21.getIcon());
				candy21.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy21.setBounds(iconXStart, iconYStart, candy21.getWidth(), candy21.getHeight());
			Icon temp = candy31.getIcon();
			candy31.setIcon(candy21.getIcon());
			candy21.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy31);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy31.getIcon();
				candy31.setIcon(candy21.getIcon());
				candy21.setIcon(temp1);
			}
			
			temp = candy31.getIcon();
			candy31.setIcon(candy21.getIcon());
			candy21.setIcon(temp);
			swapped = verticalChecker(candy31);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy31.getIcon();
				candy31.setIcon(candy21.getIcon());
				candy21.setIcon(temp1);
			}
		}else{
			candy21.setBounds(iconXStart, iconYStart, candy21.getWidth(), candy21.getHeight());
		}
	}

	private void candy21MouseDragged(MouseEvent e) {
		
		int x = candy21.getX() + e.getX() - mouseXStart;
		int y = candy21.getY() + e.getY() - mouseYStart;
		candy21.setBounds(x, y, candy21.getWidth(), candy21.getHeight());
		iconXEnd = candy21.getX();
		iconYEnd = candy21.getY();
	}

	private void candy22MousePressed(MouseEvent e) {
		
		iconXStart = candy22.getX();
		iconYStart = candy22.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy22MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy22.setBounds(iconXStart, iconYStart, candy22.getWidth(), candy22.getHeight());
			Icon temp = candy23.getIcon();
			candy23.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
			
			temp = candy23.getIcon();
			candy23.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			swapped = verticalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy22.setBounds(iconXStart, iconYStart, candy22.getWidth(), candy22.getHeight());
			Icon temp = candy12.getIcon();
			candy12.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy12);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy12.getIcon();
				candy12.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
			
			temp = candy12.getIcon();
			candy12.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			swapped = verticalChecker(candy12);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy12.getIcon();
				candy12.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy22.setBounds(iconXStart, iconYStart, candy22.getWidth(), candy22.getHeight());
			Icon temp = candy21.getIcon();
			candy21.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy21);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy21.getIcon();
				candy21.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
			
			temp = candy21.getIcon();
			candy21.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			swapped = verticalChecker(candy21);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy21.getIcon();
				candy21.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy22.setBounds(iconXStart, iconYStart, candy22.getWidth(), candy22.getHeight());
			Icon temp = candy32.getIcon();
			candy32.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
			
			temp = candy32.getIcon();
			candy32.setIcon(candy22.getIcon());
			candy22.setIcon(temp);
			swapped = verticalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy22.getIcon());
				candy22.setIcon(temp1);
			}
		}else{
			candy22.setBounds(iconXStart, iconYStart, candy22.getWidth(), candy22.getHeight());
		}
	}

	private void candy22MouseDragged(MouseEvent e) {
		
		int x = candy22.getX() + e.getX() - mouseXStart;
		int y = candy22.getY() + e.getY() - mouseYStart;
		candy22.setBounds(x, y, candy22.getWidth(), candy22.getHeight());
		iconXEnd = candy22.getX();
		iconYEnd = candy22.getY();
	}

	private void candy23MousePressed(MouseEvent e) {
		
		iconXStart = candy23.getX();
		iconYStart = candy23.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy23MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy23.setBounds(iconXStart, iconYStart, candy23.getWidth(), candy23.getHeight());
			Icon temp = candy24.getIcon();
			candy24.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
			
			temp = candy24.getIcon();
			candy24.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			swapped = verticalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy23.setBounds(iconXStart, iconYStart, candy23.getWidth(), candy23.getHeight());
			Icon temp = candy13.getIcon();
			candy13.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy13);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
							Icon temp1 = candy13.getIcon();
				candy13.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
			
			temp = candy13.getIcon();
			candy13.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			swapped = verticalChecker(candy13);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy13.getIcon();
				candy13.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy23.setBounds(iconXStart, iconYStart, candy23.getWidth(), candy23.getHeight());
			Icon temp = candy22.getIcon();
			candy22.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
			
			temp = candy22.getIcon();
			candy22.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			swapped = verticalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy23.setBounds(iconXStart, iconYStart, candy23.getWidth(), candy23.getHeight());
			Icon temp = candy33.getIcon();
			candy33.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
			
			temp = candy33.getIcon();
			candy33.setIcon(candy23.getIcon());
			candy23.setIcon(temp);
			swapped = verticalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy23.getIcon());
				candy23.setIcon(temp1);
			}
		}else{
			candy23.setBounds(iconXStart, iconYStart, candy23.getWidth(), candy23.getHeight());
		}
	}

	private void candy23MouseDragged(MouseEvent e) {
		
		int x = candy23.getX() + e.getX() - mouseXStart;
		int y = candy23.getY() + e.getY() - mouseYStart;
		candy23.setBounds(x, y, candy23.getWidth(), candy23.getHeight());
		iconXEnd = candy23.getX();
		iconYEnd = candy23.getY();
	}

	private void candy24MousePressed(MouseEvent e) {
		
		iconXStart = candy24.getX();
		iconYStart = candy24.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy24MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy24.setBounds(iconXStart, iconYStart, candy24.getWidth(), candy24.getHeight());
			Icon temp = candy25.getIcon();
			candy25.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy25);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy25.getIcon();
				candy25.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
			
			temp = candy25.getIcon();
			candy25.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			swapped = verticalChecker(candy25);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy25.getIcon();
				candy25.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy24.setBounds(iconXStart, iconYStart, candy24.getWidth(), candy24.getHeight());
			Icon temp = candy14.getIcon();
			candy14.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy14);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy14.getIcon();
				candy14.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
			
			temp = candy14.getIcon();
			candy14.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			swapped = verticalChecker(candy14);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy14.getIcon();
				candy14.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy24.setBounds(iconXStart, iconYStart, candy24.getWidth(), candy24.getHeight());
			Icon temp = candy23.getIcon();
			candy23.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
			
			temp = candy23.getIcon();
			candy23.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			swapped = verticalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy24.setBounds(iconXStart, iconYStart, candy24.getWidth(), candy24.getHeight());
			Icon temp = candy34.getIcon();
			candy34.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
			
			temp = candy34.getIcon();
			candy34.setIcon(candy24.getIcon());
			candy24.setIcon(temp);
			swapped = verticalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy24.getIcon());
				candy24.setIcon(temp1);
			}
		}else{
			candy24.setBounds(iconXStart, iconYStart, candy24.getWidth(), candy24.getHeight());
		}
	}

	private void candy24MouseDragged(MouseEvent e) {
		
		int x = candy24.getX() + e.getX() - mouseXStart;
		int y = candy24.getY() + e.getY() - mouseYStart;
		candy24.setBounds(x, y, candy24.getWidth(), candy24.getHeight());
		iconXEnd = candy24.getX();
		iconYEnd = candy24.getY();
	}

	private void candy25MousePressed(MouseEvent e) {
		
		iconXStart = candy25.getX();
		iconYStart = candy25.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy25MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy25.setBounds(iconXStart, iconYStart, candy25.getWidth(), candy25.getHeight());
			Icon temp = candy15.getIcon();
			candy15.setIcon(candy25.getIcon());
			candy25.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy15);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy15.getIcon();
				candy15.setIcon(candy25.getIcon());
				candy25.setIcon(temp1);
			}
			
			temp = candy15.getIcon();
			candy15.setIcon(candy25.getIcon());
			candy25.setIcon(temp);
			swapped = verticalChecker(candy15);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy15.getIcon();
				candy15.setIcon(candy25.getIcon());
				candy25.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy25.setBounds(iconXStart, iconYStart, candy25.getWidth(), candy25.getHeight());
			Icon temp = candy24.getIcon();
			candy24.setIcon(candy25.getIcon());
			candy25.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy25.getIcon());
				candy25.setIcon(temp1);
			}
			
			temp = candy24.getIcon();
			candy24.setIcon(candy25.getIcon());
			candy25.setIcon(temp);
			swapped = verticalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy25.getIcon());
				candy25.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy25.setBounds(iconXStart, iconYStart, candy25.getWidth(), candy25.getHeight());
			Icon temp = candy35.getIcon();
			candy35.setIcon(candy25.getIcon());
			candy25.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy35.getIcon();
				candy35.setIcon(candy25.getIcon());
				candy25.setIcon(temp1);
			}
			
			temp = candy35.getIcon();
			candy35.setIcon(candy25.getIcon());
			candy25.setIcon(temp);
			swapped = verticalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy35.getIcon();
				candy35.setIcon(candy25.getIcon());
				candy25.setIcon(temp1);
			}
		}else{
			candy25.setBounds(iconXStart, iconYStart, candy25.getWidth(), candy25.getHeight());
		}
	}

	private void candy25MouseDragged(MouseEvent e) {
		
		int x = candy25.getX() + e.getX() - mouseXStart;
		int y = candy25.getY() + e.getY() - mouseYStart;
		candy25.setBounds(x, y, candy25.getWidth(), candy25.getHeight());
		iconXEnd = candy25.getX();
		iconYEnd = candy25.getY();
	}

	private void candy31MousePressed(MouseEvent e) {
		
		iconXStart = candy31.getX();
		iconYStart = candy31.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy31MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy31.setBounds(iconXStart, iconYStart, candy31.getWidth(), candy31.getHeight());
			Icon temp = candy32.getIcon();
			candy32.setIcon(candy31.getIcon());
			candy31.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy31.getIcon());
				candy31.setIcon(temp1);
			}
			
			temp = candy32.getIcon();
			candy32.setIcon(candy31.getIcon());
			candy31.setIcon(temp);
			swapped = verticalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy31.getIcon());
				candy31.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy31.setBounds(iconXStart, iconYStart, candy31.getWidth(), candy31.getHeight());
			Icon temp = candy21.getIcon();
			candy21.setIcon(candy31.getIcon());
			candy31.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy21);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy21.getIcon();
				candy21.setIcon(candy31.getIcon());
				candy31.setIcon(temp1);
			}
			
			temp = candy21.getIcon();
			candy21.setIcon(candy31.getIcon());
			candy31.setIcon(temp);
			swapped = verticalChecker(candy21);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy21.getIcon();
				candy21.setIcon(candy31.getIcon());
				candy31.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy31.setBounds(iconXStart, iconYStart, candy31.getWidth(), candy31.getHeight());
			Icon temp = candy41.getIcon();
			candy41.setIcon(candy31.getIcon());
			candy31.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy41);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy41.getIcon();
				candy41.setIcon(candy31.getIcon());
				candy31.setIcon(temp1);
			}
			
			temp = candy41.getIcon();
			candy41.setIcon(candy31.getIcon());
			candy31.setIcon(temp);
			swapped = verticalChecker(candy41);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy41.getIcon();
				candy41.setIcon(candy31.getIcon());
				candy31.setIcon(temp1);
			}
		}else{
			candy31.setBounds(iconXStart, iconYStart, candy31.getWidth(), candy31.getHeight());
		}
	}

	private void candy31MouseDragged(MouseEvent e) {
		
		int x = candy31.getX() + e.getX() - mouseXStart;
		int y = candy31.getY() + e.getY() - mouseYStart;
		candy31.setBounds(x, y, candy31.getWidth(), candy31.getHeight());
		iconXEnd = candy31.getX();
		iconYEnd = candy31.getY();
	}

	private void candy32MousePressed(MouseEvent e) {
		
		iconXStart = candy32.getX();
		iconYStart = candy32.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy32MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy32.setBounds(iconXStart, iconYStart, candy32.getWidth(), candy32.getHeight());
			Icon temp = candy33.getIcon();
			candy33.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
			
			temp = candy33.getIcon();
			candy33.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			swapped = verticalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy32.setBounds(iconXStart, iconYStart, candy32.getWidth(), candy32.getHeight());
			Icon temp = candy22.getIcon();
			candy22.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
			
			temp = candy22.getIcon();
			candy22.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			swapped = verticalChecker(candy22);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy22.getIcon();
				candy22.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy32.setBounds(iconXStart, iconYStart, candy32.getWidth(), candy32.getHeight());
			Icon temp = candy31.getIcon();
			candy31.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy31);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy31.getIcon();
				candy31.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
			
			temp = candy31.getIcon();
			candy31.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			swapped = verticalChecker(candy31);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy31.getIcon();
				candy31.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy32.setBounds(iconXStart, iconYStart, candy32.getWidth(), candy32.getHeight());
			Icon temp = candy42.getIcon();
			candy42.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
			
			temp = candy42.getIcon();
			candy42.setIcon(candy32.getIcon());
			candy32.setIcon(temp);
			swapped = verticalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy32.getIcon());
				candy32.setIcon(temp1);
			}
		}else{
			candy32.setBounds(iconXStart, iconYStart, candy32.getWidth(), candy32.getHeight());
		}
	}

	private void candy32MouseDragged(MouseEvent e) {
		
		int x = candy32.getX() + e.getX() - mouseXStart;
		int y = candy32.getY() + e.getY() - mouseYStart;
		candy32.setBounds(x, y, candy32.getWidth(), candy32.getHeight());
		iconXEnd = candy32.getX();
		iconYEnd = candy32.getY();
	}

	private void candy33MousePressed(MouseEvent e) {
		
		iconXStart = candy33.getX();
		iconYStart = candy33.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy33MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy33.setBounds(iconXStart, iconYStart, candy33.getWidth(), candy33.getHeight());
			Icon temp = candy34.getIcon();
			candy34.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
			
			temp = candy34.getIcon();
			candy34.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			swapped = verticalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy33.setBounds(iconXStart, iconYStart, candy33.getWidth(), candy33.getHeight());
			Icon temp = candy23.getIcon();
			candy23.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy23);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
			
			temp = candy23.getIcon();
			candy23.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			swapped = verticalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy23.getIcon();
				candy23.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy33.setBounds(iconXStart, iconYStart, candy33.getWidth(), candy33.getHeight());
			Icon temp = candy32.getIcon();
			candy32.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
			
			temp = candy32.getIcon();
			candy32.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			swapped = verticalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy33.setBounds(iconXStart, iconYStart, candy33.getWidth(), candy33.getHeight());
			Icon temp = candy43.getIcon();
			candy43.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
			
			temp = candy43.getIcon();
			candy43.setIcon(candy33.getIcon());
			candy33.setIcon(temp);
			swapped = verticalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy33.getIcon());
				candy33.setIcon(temp1);
			}
		}else{
			candy33.setBounds(iconXStart, iconYStart, candy33.getWidth(), candy33.getHeight());
		}
	}

	private void candy33MouseDragged(MouseEvent e) {
		
		int x = candy33.getX() + e.getX() - mouseXStart;
		int y = candy33.getY() + e.getY() - mouseYStart;
		candy33.setBounds(x, y, candy33.getWidth(), candy33.getHeight());
		iconXEnd = candy33.getX();
		iconYEnd = candy33.getY();
	}

	private void candy34MousePressed(MouseEvent e) {
		
		iconXStart = candy34.getX();
		iconYStart = candy34.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy34MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy34.setBounds(iconXStart, iconYStart, candy34.getWidth(), candy34.getHeight());
			Icon temp = candy35.getIcon();
			candy35.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy35.getIcon();
				candy35.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
			
			temp = candy35.getIcon();
			candy35.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			swapped = verticalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy35.getIcon();
				candy35.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy34.setBounds(iconXStart, iconYStart, candy34.getWidth(), candy34.getHeight());
			Icon temp = candy24.getIcon();
			candy24.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
			
			temp = candy24.getIcon();
			candy24.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			swapped = verticalChecker(candy24);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy24.getIcon();
				candy24.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy34.setBounds(iconXStart, iconYStart, candy34.getWidth(), candy34.getHeight());
			Icon temp = candy33.getIcon();
			candy33.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
							Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
			
			temp = candy33.getIcon();
			candy33.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			swapped = verticalChecker(candy33);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy33.getIcon();
				candy33.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy34.setBounds(iconXStart, iconYStart, candy34.getWidth(), candy34.getHeight());
			Icon temp = candy44.getIcon();
			candy44.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
			
			temp = candy44.getIcon();
			candy44.setIcon(candy34.getIcon());
			candy34.setIcon(temp);
			swapped = verticalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy34.getIcon());
				candy34.setIcon(temp1);
			}
		}else{
			candy34.setBounds(iconXStart, iconYStart, candy34.getWidth(), candy34.getHeight());
		}
	}

	private void candy34MouseDragged(MouseEvent e) {
		
		int x = candy34.getX() + e.getX() - mouseXStart;
		int y = candy34.getY() + e.getY() - mouseYStart;
		candy34.setBounds(x, y, candy34.getWidth(), candy34.getHeight());
		iconXEnd = candy34.getX();
		iconYEnd = candy34.getY();
	}

	private void candy35MousePressed(MouseEvent e) {
		
		iconXStart = candy35.getX();
		iconYStart = candy35.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy35MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy35.setBounds(iconXStart, iconYStart, candy35.getWidth(), candy35.getHeight());
			Icon temp = candy25.getIcon();
			candy25.setIcon(candy35.getIcon());
			candy35.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy25);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy25.getIcon();
				candy25.setIcon(candy35.getIcon());
				candy35.setIcon(temp1);
			}
			
			temp = candy25.getIcon();
			candy25.setIcon(candy35.getIcon());
			candy35.setIcon(temp);
			swapped = verticalChecker(candy25);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy25.getIcon();
				candy25.setIcon(candy35.getIcon());
				candy35.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy35.setBounds(iconXStart, iconYStart, candy35.getWidth(), candy35.getHeight());
			Icon temp = candy34.getIcon();
			candy34.setIcon(candy35.getIcon());
			candy35.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy35.getIcon());
				candy35.setIcon(temp1);
			}
			
			temp = candy34.getIcon();
			candy34.setIcon(candy35.getIcon());
			candy35.setIcon(temp);
			swapped = verticalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy35.getIcon());
				candy35.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy35.setBounds(iconXStart, iconYStart, candy35.getWidth(), candy35.getHeight());
			Icon temp = candy45.getIcon();
			candy45.setIcon(candy35.getIcon());
			candy35.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy45);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy45.getIcon();
				candy45.setIcon(candy35.getIcon());
				candy35.setIcon(temp1);
			}
			
			temp = candy45.getIcon();
			candy45.setIcon(candy35.getIcon());
			candy35.setIcon(temp);
			swapped = verticalChecker(candy45);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy45.getIcon();
				candy45.setIcon(candy35.getIcon());
				candy35.setIcon(temp1);
			}
		}else{
			candy35.setBounds(iconXStart, iconYStart, candy35.getWidth(), candy35.getHeight());
		}
	}

	private void candy35MouseDragged(MouseEvent e) {
		
		int x = candy35.getX() + e.getX() - mouseXStart;
		int y = candy35.getY() + e.getY() - mouseYStart;
		candy35.setBounds(x, y, candy35.getWidth(), candy35.getHeight());
		iconXEnd = candy35.getX();
		iconYEnd = candy35.getY();
	}

	private void candy41MousePressed(MouseEvent e) {
		
		iconXStart = candy41.getX();
		iconYStart = candy41.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy41MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy41.setBounds(iconXStart, iconYStart, candy41.getWidth(), candy41.getHeight());
			Icon temp = candy42.getIcon();
			candy42.setIcon(candy41.getIcon());
			candy41.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy41.getIcon());
				candy41.setIcon(temp1);
			}
			
			temp = candy42.getIcon();
			candy42.setIcon(candy41.getIcon());
			candy41.setIcon(temp);
			swapped = verticalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy41.getIcon());
				candy41.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy41.setBounds(iconXStart, iconYStart, candy41.getWidth(), candy41.getHeight());
			Icon temp = candy31.getIcon();
			candy31.setIcon(candy41.getIcon());
			candy41.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy31);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy31.getIcon();
				candy31.setIcon(candy41.getIcon());
				candy41.setIcon(temp1);
			}
			
			temp = candy31.getIcon();
			candy31.setIcon(candy41.getIcon());
			candy41.setIcon(temp);
			swapped = verticalChecker(candy31);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy31.getIcon();
				candy31.setIcon(candy41.getIcon());
				candy41.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy41.setBounds(iconXStart, iconYStart, candy41.getWidth(), candy41.getHeight());
			Icon temp = candy51.getIcon();
			candy51.setIcon(candy41.getIcon());
			candy41.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy51);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy51.getIcon();
				candy51.setIcon(candy41.getIcon());
				candy41.setIcon(temp1);
			}
			
			temp = candy51.getIcon();
			candy51.setIcon(candy41.getIcon());
			candy41.setIcon(temp);
			swapped = verticalChecker(candy51);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy51.getIcon();
				candy51.setIcon(candy41.getIcon());
				candy41.setIcon(temp1);
			}
		}else{
			candy41.setBounds(iconXStart, iconYStart, candy41.getWidth(), candy41.getHeight());
		}
	}

	private void candy41MouseDragged(MouseEvent e) {
		
		int x = candy41.getX() + e.getX() - mouseXStart;
		int y = candy41.getY() + e.getY() - mouseYStart;
		candy41.setBounds(x, y, candy41.getWidth(), candy41.getHeight());
		iconXEnd = candy41.getX();
		iconYEnd = candy41.getY();
	}

	private void candy42MousePressed(MouseEvent e) {
		
		iconXStart = candy42.getX();
		iconYStart = candy42.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy42MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy42.setBounds(iconXStart, iconYStart, candy42.getWidth(), candy42.getHeight());
			Icon temp = candy43.getIcon();
			candy43.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
			
			temp = candy43.getIcon();
			candy43.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			swapped = verticalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy42.setBounds(iconXStart, iconYStart, candy42.getWidth(), candy42.getHeight());
			Icon temp = candy32.getIcon();
			candy32.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
			
			temp = candy32.getIcon();
			candy32.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			swapped = verticalChecker(candy32);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy32.getIcon();
				candy32.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy42.setBounds(iconXStart, iconYStart, candy42.getWidth(), candy42.getHeight());
			Icon temp = candy41.getIcon();
			candy41.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy41);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy41.getIcon();
				candy41.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
			
			temp = candy41.getIcon();
			candy41.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			swapped = verticalChecker(candy41);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy41.getIcon();
				candy41.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy42.setBounds(iconXStart, iconYStart, candy42.getWidth(), candy42.getHeight());
			Icon temp = candy52.getIcon();
			candy52.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy52);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy52.getIcon();
				candy52.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
			
			temp = candy52.getIcon();
			candy52.setIcon(candy42.getIcon());
			candy42.setIcon(temp);
			swapped = verticalChecker(candy52);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy52.getIcon();
				candy52.setIcon(candy42.getIcon());
				candy42.setIcon(temp1);
			}
		}else{
			candy42.setBounds(iconXStart, iconYStart, candy42.getWidth(), candy42.getHeight());
		}
	}

	private void candy42MouseDragged(MouseEvent e) {
		
		int x = candy42.getX() + e.getX() - mouseXStart;
		int y = candy42.getY() + e.getY() - mouseYStart;
		candy42.setBounds(x, y, candy42.getWidth(), candy42.getHeight());
		iconXEnd = candy42.getX();
		iconYEnd = candy42.getY();
	}

	private void candy44MousePressed(MouseEvent e) {
		
		iconXStart = candy44.getX();
		iconYStart = candy44.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy44MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy44.setBounds(iconXStart, iconYStart, candy44.getWidth(), candy44.getHeight());
			Icon temp = candy45.getIcon();
			candy45.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy45);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy45.getIcon();
				candy45.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
			
			temp = candy45.getIcon();
			candy45.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			swapped = verticalChecker(candy45);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy45.getIcon();
				candy45.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy44.setBounds(iconXStart, iconYStart, candy44.getWidth(), candy44.getHeight());
			Icon temp = candy34.getIcon();
			candy34.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
			
			temp = candy34.getIcon();
			candy34.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			swapped = verticalChecker(candy34);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy34.getIcon();
				candy34.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy44.setBounds(iconXStart, iconYStart, candy44.getWidth(), candy44.getHeight());
			Icon temp = candy43.getIcon();
			candy43.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
			
			temp = candy43.getIcon();
			candy43.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			swapped = verticalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
				}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy44.setBounds(iconXStart, iconYStart, candy44.getWidth(), candy44.getHeight());
			Icon temp = candy54.getIcon();
			candy54.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy54);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy54.getIcon();
				candy54.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
			
			temp = candy54.getIcon();
			candy54.setIcon(candy44.getIcon());
			candy44.setIcon(temp);
			swapped = verticalChecker(candy54);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
				}
			if(swapped == false){
				Icon temp1 = candy54.getIcon();
				candy54.setIcon(candy44.getIcon());
				candy44.setIcon(temp1);
			}
		}else{
			candy44.setBounds(iconXStart, iconYStart, candy44.getWidth(), candy44.getHeight());
		}
	}

	private void candy44MouseDragged(MouseEvent e) {
		
		int x = candy44.getX() + e.getX() - mouseXStart;
		int y = candy44.getY() + e.getY() - mouseYStart;
		candy44.setBounds(x, y, candy44.getWidth(), candy44.getHeight());
		iconXEnd = candy44.getX();
		iconYEnd = candy44.getY();
	}

	private void candy45MousePressed(MouseEvent e) {
		
		iconXStart = candy45.getX();
		iconYStart = candy45.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy45MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy45.setBounds(iconXStart, iconYStart, candy45.getWidth(), candy45.getHeight());
			Icon temp = candy35.getIcon();
			candy35.setIcon(candy45.getIcon());
			candy45.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy35.getIcon();
				candy35.setIcon(candy45.getIcon());
				candy45.setIcon(temp1);
			}
			
			temp = candy35.getIcon();
			candy35.setIcon(candy45.getIcon());
			candy45.setIcon(temp);
			swapped = verticalChecker(candy35);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy35.getIcon();
				candy35.setIcon(candy45.getIcon());
				candy45.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy45.setBounds(iconXStart, iconYStart, candy45.getWidth(), candy45.getHeight());
			Icon temp = candy44.getIcon();
			candy44.setIcon(candy45.getIcon());
			candy45.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy45.getIcon());
				candy45.setIcon(temp1);
			}

			temp = candy44.getIcon();
			candy44.setIcon(candy45.getIcon());
			candy45.setIcon(temp);
			swapped = verticalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy45.getIcon());
				candy45.setIcon(temp1);
			}
		}else if(yResult > 30 && xResult <= 25 && xResult >= -25){//for swapping to the lower icon
			candy45.setBounds(iconXStart, iconYStart, candy45.getWidth(), candy45.getHeight());
			Icon temp = candy55.getIcon();
			candy55.setIcon(candy45.getIcon());
			candy45.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy55);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy55.getIcon();
				candy55.setIcon(candy45.getIcon());
				candy45.setIcon(temp1);
			}

			temp = candy55.getIcon();
			candy55.setIcon(candy45.getIcon());
			candy45.setIcon(temp);
			swapped = verticalChecker(candy55);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy55.getIcon();
				candy55.setIcon(candy45.getIcon());
				candy45.setIcon(temp1);
			}
		}else{
			candy45.setBounds(iconXStart, iconYStart, candy45.getWidth(), candy45.getHeight());
		}
	}

	private void candy45MouseDragged(MouseEvent e) {
		
		int x = candy45.getX() + e.getX() - mouseXStart;
		int y = candy45.getY() + e.getY() - mouseYStart;
		candy45.setBounds(x, y, candy45.getWidth(), candy45.getHeight());
		iconXEnd = candy45.getX();
		iconYEnd = candy45.getY();
	}

	private void candy51MousePressed(MouseEvent e) {
		
		iconXStart = candy51.getX();
		iconYStart = candy51.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy51MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy51.setBounds(iconXStart, iconYStart, candy51.getWidth(), candy51.getHeight());
			Icon temp = candy52.getIcon();
			candy52.setIcon(candy51.getIcon());
			candy51.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy52);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy52.getIcon();
				candy52.setIcon(candy51.getIcon());
				candy51.setIcon(temp1);
			}

			temp = candy52.getIcon();
			candy52.setIcon(candy51.getIcon());
			candy51.setIcon(temp);
			swapped = verticalChecker(candy52);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy52.getIcon();
				candy52.setIcon(candy51.getIcon());
				candy51.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy51.setBounds(iconXStart, iconYStart, candy51.getWidth(), candy51.getHeight());
			Icon temp = candy41.getIcon();
			candy41.setIcon(candy51.getIcon());
			candy51.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy41);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy41.getIcon();
				candy41.setIcon(candy51.getIcon());
				candy51.setIcon(temp1);
			}

			temp = candy41.getIcon();
			candy41.setIcon(candy51.getIcon());
			candy51.setIcon(temp);
			swapped = verticalChecker(candy41);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy41.getIcon();
				candy41.setIcon(candy51.getIcon());
				candy51.setIcon(temp1);
			}
		}else{
			candy51.setBounds(iconXStart, iconYStart, candy51.getWidth(), candy51.getHeight());
		}
	}

	private void candy51MouseDragged(MouseEvent e) {
		
		int x = candy51.getX() + e.getX() - mouseXStart;
		int y = candy51.getY() + e.getY() - mouseYStart;
		candy51.setBounds(x, y, candy51.getWidth(), candy51.getHeight());
		iconXEnd = candy51.getX();
		iconYEnd = candy51.getY();
	}

	private void candy52MousePressed(MouseEvent e) {
		
		iconXStart = candy52.getX();
		iconYStart = candy52.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy52MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy52.setBounds(iconXStart, iconYStart, candy52.getWidth(), candy52.getHeight());
			Icon temp = candy53.getIcon();
			candy53.setIcon(candy52.getIcon());
			candy52.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy53);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy53.getIcon();
				candy53.setIcon(candy52.getIcon());
				candy52.setIcon(temp1);
			}

			temp = candy53.getIcon();
			candy53.setIcon(candy52.getIcon());
			candy52.setIcon(temp);
			swapped = verticalChecker(candy53);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy53.getIcon();
				candy53.setIcon(candy52.getIcon());
				candy52.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy52.setBounds(iconXStart, iconYStart, candy52.getWidth(), candy52.getHeight());
			Icon temp = candy42.getIcon();
			candy42.setIcon(candy52.getIcon());
			candy52.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy52.getIcon());
				candy52.setIcon(temp1);
			}

			temp = candy42.getIcon();
			candy42.setIcon(candy52.getIcon());
			candy52.setIcon(temp);
			swapped = verticalChecker(candy42);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy42.getIcon();
				candy42.setIcon(candy52.getIcon());
				candy52.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy52.setBounds(iconXStart, iconYStart, candy52.getWidth(), candy52.getHeight());
			Icon temp = candy51.getIcon();
			candy51.setIcon(candy52.getIcon());
			candy52.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy51);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy51.getIcon();
				candy51.setIcon(candy52.getIcon());
				candy52.setIcon(temp1);
			}

			temp = candy51.getIcon();
			candy51.setIcon(candy52.getIcon());
			candy52.setIcon(temp);
			swapped = verticalChecker(candy51);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy51.getIcon();
				candy51.setIcon(candy52.getIcon());
				candy52.setIcon(temp1);
			}
		}else{
			candy52.setBounds(iconXStart, iconYStart, candy52.getWidth(), candy52.getHeight());
		}
	}

	private void candy52MouseDragged(MouseEvent e) {
		
		int x = candy52.getX() + e.getX() - mouseXStart;
		int y = candy52.getY() + e.getY() - mouseYStart;
		candy52.setBounds(x, y, candy52.getWidth(), candy52.getHeight());
		iconXEnd = candy52.getX();
		iconYEnd = candy52.getY();
	}

	private void candy53MousePressed(MouseEvent e) {
		
		iconXStart = candy53.getX();
		iconYStart = candy53.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy53MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy53.setBounds(iconXStart, iconYStart, candy53.getWidth(), candy53.getHeight());
			Icon temp = candy54.getIcon();
			candy54.setIcon(candy53.getIcon());
			candy53.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy54);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy54.getIcon();
				candy54.setIcon(candy53.getIcon());
				candy53.setIcon(temp1);
			}

			temp = candy54.getIcon();
			candy54.setIcon(candy53.getIcon());
			candy53.setIcon(temp);
			swapped = verticalChecker(candy54);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy54.getIcon();
				candy54.setIcon(candy53.getIcon());
				candy53.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy53.setBounds(iconXStart, iconYStart, candy53.getWidth(), candy53.getHeight());
			Icon temp = candy43.getIcon();
			candy43.setIcon(candy53.getIcon());
			candy53.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy53.getIcon());
				candy53.setIcon(temp1);
			}

			temp = candy43.getIcon();
			candy43.setIcon(candy53.getIcon());
			candy53.setIcon(temp);
			swapped = verticalChecker(candy43);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy43.getIcon();
				candy43.setIcon(candy53.getIcon());
				candy53.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy53.setBounds(iconXStart, iconYStart, candy53.getWidth(), candy53.getHeight());
			Icon temp = candy52.getIcon();
			candy52.setIcon(candy53.getIcon());
			candy53.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy52);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy52.getIcon();
				candy52.setIcon(candy53.getIcon());
				candy53.setIcon(temp1);
			}

			temp = candy52.getIcon();
			candy52.setIcon(candy53.getIcon());
			candy53.setIcon(temp);
			swapped = verticalChecker(candy52);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy52.getIcon();
				candy52.setIcon(candy53.getIcon());
				candy53.setIcon(temp1);
			}
		}else{
			candy53.setBounds(iconXStart, iconYStart, candy53.getWidth(), candy53.getHeight());
		}
	}

	private void candy53MouseDragged(MouseEvent e) {
		
		int x = candy53.getX() + e.getX() - mouseXStart;
		int y = candy53.getY() + e.getY() - mouseYStart;
		candy53.setBounds(x, y, candy53.getWidth(), candy53.getHeight());
		iconXEnd = candy53.getX();
		iconYEnd = candy53.getY();
	}

	private void candy54MousePressed(MouseEvent e) {
		
		iconXStart = candy54.getX();
		iconYStart = candy54.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy54MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(xResult > 30 && yResult <= 25 && yResult >= -25){//for swapping to the right icon
			candy54.setBounds(iconXStart, iconYStart, candy54.getWidth(), candy54.getHeight());
			Icon temp = candy55.getIcon();
			candy55.setIcon(candy54.getIcon());
			candy54.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy55);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy55.getIcon();
				candy55.setIcon(candy54.getIcon());
				candy54.setIcon(temp1);
			}

			temp = candy55.getIcon();
			candy55.setIcon(candy54.getIcon());
			candy54.setIcon(temp);
			swapped = verticalChecker(candy55);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy55.getIcon();
				candy55.setIcon(candy54.getIcon());
				candy54.setIcon(temp1);
			}
		}else if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy54.setBounds(iconXStart, iconYStart, candy54.getWidth(), candy54.getHeight());
			Icon temp = candy44.getIcon();
			candy44.setIcon(candy54.getIcon());
			candy54.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy54.getIcon());
				candy54.setIcon(temp1);
			}

			temp = candy44.getIcon();
			candy44.setIcon(candy54.getIcon());
			candy54.setIcon(temp);
			swapped = verticalChecker(candy44);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy44.getIcon();
				candy44.setIcon(candy54.getIcon());
				candy54.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy54.setBounds(iconXStart, iconYStart, candy54.getWidth(), candy54.getHeight());
			Icon temp = candy53.getIcon();
			candy53.setIcon(candy54.getIcon());
			candy54.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy53);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy53.getIcon();
				candy53.setIcon(candy54.getIcon());
				candy54.setIcon(temp1);
			}

			temp = candy53.getIcon();
			candy53.setIcon(candy54.getIcon());
			candy54.setIcon(temp);
			swapped = verticalChecker(candy53);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy53.getIcon();
				candy53.setIcon(candy54.getIcon());
				candy54.setIcon(temp1);
			}
		}else{
			candy54.setBounds(iconXStart, iconYStart, candy54.getWidth(), candy54.getHeight());
		}
	}

	private void candy54MouseDragged(MouseEvent e) {
		
		int x = candy54.getX() + e.getX() - mouseXStart;
		int y = candy54.getY() + e.getY() - mouseYStart;
		candy54.setBounds(x, y, candy54.getWidth(), candy54.getHeight());
		iconXEnd = candy54.getX();
		iconYEnd = candy54.getY();
	}

	private void candy55MousePressed(MouseEvent e) {
		
		iconXStart = candy55.getX();
		iconYStart = candy55.getY();
		mouseXStart = e.getX();
		mouseYStart = e.getY();
	}

	private void candy55MouseReleased(MouseEvent e) {
		
		int xResult = iconXEnd - iconXStart;
		int yResult = iconYEnd - iconYStart;

		if(yResult < -30 && xResult <= 25 && xResult >= -25){//for swapping to the upper icon
			candy55.setBounds(iconXStart, iconYStart, candy55.getWidth(), candy55.getHeight());
			Icon temp = candy45.getIcon();
			candy45.setIcon(candy55.getIcon());
			candy55.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy45);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy45.getIcon();
				candy45.setIcon(candy55.getIcon());
				candy55.setIcon(temp1);
			}

			temp = candy45.getIcon();
			candy45.setIcon(candy55.getIcon());
			candy55.setIcon(temp);
			swapped = verticalChecker(candy45);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy45.getIcon();
				candy45.setIcon(candy55.getIcon());
				candy55.setIcon(temp1);
			}
		}else if(xResult < -30 && yResult <= 25 && yResult >= -25){//for swapping to the left icon
			candy55.setBounds(iconXStart, iconYStart, candy55.getWidth(), candy55.getHeight());
			Icon temp = candy54.getIcon();
			candy54.setIcon(candy55.getIcon());
			candy55.setIcon(temp);
			
			boolean swapped = horizontalChecker(candy55);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy54.getIcon();
				candy54.setIcon(candy55.getIcon());
				candy55.setIcon(temp1);
			}

			temp = candy54.getIcon();
			candy54.setIcon(candy55.getIcon());
			candy55.setIcon(temp);
			swapped = verticalChecker(candy54);
			if(swapped == true){
				moves = moves - 1;
				lblMoveNum.setText(moves+"");
				if(Integer.parseInt(lblMoveNum.getText())==0){
					mainGame.close();
					this.dispose();
					new CongratsMessage(lblFirstStar.isEnabled(), lblSecondStar.isEnabled(), lblThirdStar.isEnabled(), score).setVisible(true);
				}
			}
			if(swapped == false){
				Icon temp1 = candy54.getIcon();
				candy54.setIcon(candy55.getIcon());
				candy55.setIcon(temp1);
			}
		}else{
			candy55.setBounds(iconXStart, iconYStart, candy55.getWidth(), candy55.getHeight());
		}
	}

	private void candy55MouseDragged(MouseEvent e) {
		
		int x = candy55.getX() + e.getX() - mouseXStart;
		int y = candy55.getY() + e.getY() - mouseYStart;
		candy55.setBounds(x, y, candy55.getWidth(), candy55.getHeight());
		iconXEnd = candy55.getX();
		iconYEnd = candy55.getY();
	}

	private void lblHelpMouseEntered(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
				entered = AudioSystem.getClip();
				entered.open(audioInputStream);
				entered.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		lblHelp.setIcon(new ImageIcon("Images\\help2.png"));
	}

	private void lblHelpMouseExited(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
				exited = AudioSystem.getClip();
				exited.open(audioInputStream);
				exited.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		lblHelp.setIcon(new ImageIcon("Images\\help.png"));
	}

	private void lblHelpMouseClicked(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
		}
		new HowToPlay().setVisible(true);
	}

	private void lblShuffleMouseEntered(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
				entered = AudioSystem.getClip();
				entered.open(audioInputStream);
				entered.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		lblShuffle.setIcon(new ImageIcon("Images\\shuffle2.png"));
	}

	private void lblShuffleMouseExited(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
				exited = AudioSystem.getClip();
				exited.open(audioInputStream);
				exited.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		lblShuffle.setIcon(new ImageIcon("Images\\shuffle.png"));
	}

	private void lblShuffleMouseClicked(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
		}

		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/shuffle1.wav"));
				shuffle1 = AudioSystem.getClip();
				shuffle1.open(audioInputStream);
				shuffle1.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
		}

		if(Integer.parseInt(lblShuffleNum.getText()) > 0){
			for(int i = 0; i < pnlCandies.getComponentCount(); i++){
				Component candy = pnlCandies.getComponent(i);
				Random r = new Random();
				int num = r.nextInt(6)+1;
				if(num == 1){
					((JLabel)candy).setIcon(new ImageIcon("Images\\BluePlanet.png"));
				}else if(num == 2){
					((JLabel)candy).setIcon(new ImageIcon("Images\\GreenPillow.png"));
				}else if(num == 3){
					((JLabel)candy).setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
				}else if(num == 4){
					((JLabel)candy).setIcon(new ImageIcon("Images\\PurpleFlower.png"));
				}else if(num == 5){
					((JLabel)candy).setIcon(new ImageIcon("Images\\RedSausage.png"));
				}else{
					((JLabel)candy).setIcon(new ImageIcon("Images\\YellowTearDrop.png"));
				}
			}
		lblShuffleNum.setText((Integer.parseInt(lblShuffleNum.getText())-1)+"");
		}else{
			JOptionPane.showMessageDialog(null, "You have no remaing randomize!", "Note!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void lblRetryMouseEntered(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
				entered = AudioSystem.getClip();
				entered.open(audioInputStream);
				entered.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		lblRetry.setIcon(new ImageIcon("Images\\retyMainGame2.png"));
	}

	private void lblRetryMouseExited(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
				exited = AudioSystem.getClip();
				exited.open(audioInputStream);
				exited.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		lblRetry.setIcon(new ImageIcon("Images\\retyMainGame.png"));
	}

	private void lblRetryMouseClicked(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
		}
		mainGame.close();
		this.dispose();
		new AbuanCandyCrush().setVisible(true);
	}

	private void lblSoundEffectMouseEntered(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
				entered = AudioSystem.getClip();
				entered.open(audioInputStream);
				entered.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		if(soundEffect == true){
			lblSoundEffect.setIcon(new ImageIcon("Images\\sfOn2.png"));
		}else{
			lblSoundEffect.setIcon(new ImageIcon("Images\\sfOff2.png"));
		}
	}

	private void lblSoundEffectMouseExited(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
				exited = AudioSystem.getClip();
				exited.open(audioInputStream);
				exited.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		if(soundEffect == true){
			lblSoundEffect.setIcon(new ImageIcon("Images\\sfOn.png"));
		}else{
			lblSoundEffect.setIcon(new ImageIcon("Images\\sfOff.png"));
		}
	}

	private void lblSoundEffectMouseClicked(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
		}
		if(soundEffect == true){
			lblSoundEffect.setIcon(new ImageIcon("Images\\sfOff2.png"));
			soundEffect = false;
		}else{
			lblSoundEffect.setIcon(new ImageIcon("Images\\sfOn2.png"));
			soundEffect = true;
		}

	}

	private void lblMusicMouseEntered(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/entered.wav"));
				entered = AudioSystem.getClip();
				entered.open(audioInputStream);
				entered.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		if(music == true){
			lblMusic.setIcon(new ImageIcon("Images\\MusicOn2.png"));
		}else{
			lblMusic.setIcon(new ImageIcon("Images\\MusicOff2.png"));
		}
	}

	private void lblMusicMouseExited(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/exited.wav"));
				exited = AudioSystem.getClip();
				exited.open(audioInputStream);
				exited.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		if(music == true){
			lblMusic.setIcon(new ImageIcon("Images\\MusicOn.png"));
		}else{
			lblMusic.setIcon(new ImageIcon("Images\\MusicOff.png"));
		}
	}

	private void lblMusicMouseClicked(MouseEvent e) {
		if(soundEffect == true){
			try {
				audioInputStream = AudioSystem.getAudioInputStream(LoadingScreen.class.getResource("Audiofiles/clicked.wav"));
				clicked = AudioSystem.getClip();
				clicked.open(audioInputStream);
				clicked.start();
			} catch (Exception e3) {
				e3.printStackTrace();
			};
		}
		if(music == true){
			lblMusic.setIcon(new ImageIcon("Images\\MusicOff2.png"));
			mainGame.stop();
			music = false;
		}else{
			lblMusic.setIcon(new ImageIcon("Images\\MusicOn2.png"));
			mainGame.start();
			music = true;
		}
	}

	

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
		lblSoundEffect = new JLabel();
		lblMusic = new JLabel();
		lblShuffleNum = new JLabel();
		lblShuffNumbackground = new JLabel();
		lblTargetNum = new JLabel();
		lblMoveNum = new JLabel();
		lblScoreNum = new JLabel();
		lblHelp = new JLabel();
		lblShuffle = new JLabel();
		lblRetry = new JLabel();
		lblFirstStar = new JLabel();
		lblThirdStar = new JLabel();
		lblSecondStar = new JLabel();
		ScoreProgressBar = new JProgressBar();
		lblScoreBoardBackground = new JLabel();
		pnlCandies = new JPanel();
		candy11 = new JLabel();
		candy12 = new JLabel();
		candy13 = new JLabel();
		candy14 = new JLabel();
		candy15 = new JLabel();
		candy21 = new JLabel();
		candy22 = new JLabel();
		candy23 = new JLabel();
		candy24 = new JLabel();
		candy25 = new JLabel();
		candy31 = new JLabel();
		candy32 = new JLabel();
		candy33 = new JLabel();
		candy34 = new JLabel();
		candy35 = new JLabel();
		candy41 = new JLabel();
		candy42 = new JLabel();
		candy43 = new JLabel();
		candy44 = new JLabel();
		candy45 = new JLabel();
		candy51 = new JLabel();
		candy52 = new JLabel();
		candy53 = new JLabel();
		candy54 = new JLabel();
		candy55 = new JLabel();
		lblCandiesBackground = new JLabel();
		textField1 = new JTextField();
		lblbackground = new JLabel();

		//======== this ========
		setTitle("Candy Crush");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- lblSoundEffect ----
		lblSoundEffect.setIcon(new ImageIcon("Images\\sfOn.png"));
		lblSoundEffect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSoundEffectMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSoundEffectMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSoundEffectMouseExited(e);
			}
		});
		contentPane.add(lblSoundEffect);
		lblSoundEffect.setBounds(600, 40, 55, 55);

		//---- lblMusic ----
		lblMusic.setIcon(new ImageIcon("Images\\musicOn.png"));
		lblMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblMusicMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMusicMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMusicMouseExited(e);
			}
		});
		contentPane.add(lblMusic);
		lblMusic.setBounds(530, 40, 55, 55);

		//---- lblShuffleNum ----
		lblShuffleNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblShuffleNum.setFont(new Font("Eras Bold ITC", Font.PLAIN, 18));
		lblShuffleNum.setForeground(new Color(0xcc0066));
		lblShuffleNum.setIcon(null);
		lblShuffleNum.setText("2");
		contentPane.add(lblShuffleNum);
		lblShuffleNum.setBounds(425, 35, 20, 20);

		//---- lblShuffNumbackground ----
		lblShuffNumbackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblShuffNumbackground.setFont(new Font("Eras Bold ITC", Font.PLAIN, 18));
		lblShuffNumbackground.setForeground(new Color(0xcc0066));
		lblShuffNumbackground.setIcon(new ImageIcon("Images\\shuffleNumberbackground.png"));
		contentPane.add(lblShuffNumbackground);
		lblShuffNumbackground.setBounds(425, 35, 20, 20);

		//---- lblTargetNum ----
		lblTargetNum.setText("10000");
		lblTargetNum.setBackground(new Color(0xb2ffcccc, true));
		lblTargetNum.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 35));
		lblTargetNum.setForeground(new Color(0xb2ff0097, true));
		lblTargetNum.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTargetNum);
		lblTargetNum.setBounds(70, 110, 145, 40);

		//---- lblMoveNum ----
		lblMoveNum.setText("10");
		lblMoveNum.setBackground(new Color(0xb2ffcccc, true));
		lblMoveNum.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 30));
		lblMoveNum.setForeground(new Color(0xb2ff0097, true));
		lblMoveNum.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMoveNum);
		lblMoveNum.setBounds(60, 235, 145, 40);

		//---- lblScoreNum ----
		lblScoreNum.setText("0");
		lblScoreNum.setBackground(new Color(0xb2ffcccc, true));
		lblScoreNum.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 35));
		lblScoreNum.setForeground(new Color(0xb2ff0097, true));
		lblScoreNum.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScoreNum);
		lblScoreNum.setBounds(20, 380, 175, 40);

		//---- lblHelp ----
		lblHelp.setIcon(new ImageIcon("Images\\help.png"));
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblHelpMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHelpMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblHelpMouseExited(e);
			}
		});
		contentPane.add(lblHelp);
		lblHelp.setBounds(460, 40, 55, 55);

		//---- lblShuffle ----
		lblShuffle.setIcon(new ImageIcon("Images\\shuffle.png"));
		lblShuffle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblShuffleMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblShuffleMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblShuffleMouseExited(e);
			}
		});
		contentPane.add(lblShuffle);
		lblShuffle.setBounds(390, 40, 55, 55);

		//---- lblRetry ----
		lblRetry.setIcon(new ImageIcon("Images\\retyMainGame.png"));
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
		lblRetry.setBounds(320, 40, 55, 55);

		//---- lblFirstStar ----
		lblFirstStar.setBackground(new Color(0x6666ff));
		lblFirstStar.setIcon(new ImageIcon("Images\\progressBarStar.png"));
		lblFirstStar.setEnabled(false);
		contentPane.add(lblFirstStar);
		lblFirstStar.setBounds(265, 522, 20, 20);

		//---- lblThirdStar ----
		lblThirdStar.setBackground(new Color(0x6666ff));
		lblThirdStar.setIcon(new ImageIcon("Images\\progressBarStar.png"));
		lblThirdStar.setEnabled(false);
		contentPane.add(lblThirdStar);
		lblThirdStar.setBounds(265, 287, 20, 20);

		//---- lblSecondStar ----
		lblSecondStar.setBackground(new Color(0x6666ff));
		lblSecondStar.setIcon(new ImageIcon("Images\\progressBarStar.png"));
		lblSecondStar.setEnabled(false);
		contentPane.add(lblSecondStar);
		lblSecondStar.setBounds(265, 410, 20, 20);

		//---- ScoreProgressBar ----
		ScoreProgressBar.setOrientation(SwingConstants.VERTICAL);
		ScoreProgressBar.setBackground(new Color(0xffcccc));
		ScoreProgressBar.setForeground(new Color(0x0033ff));
		ScoreProgressBar.setFont(ScoreProgressBar.getFont().deriveFont(ScoreProgressBar.getFont().getStyle() | Font.ITALIC));
		contentPane.add(ScoreProgressBar);
		ScoreProgressBar.setBounds(215, 295, 50, 345);

		//---- lblScoreBoardBackground ----
		lblScoreBoardBackground.setIcon(new ImageIcon("Images\\scoreboard.png"));
		contentPane.add(lblScoreBoardBackground);
		lblScoreBoardBackground.setBounds(5, 15, 305, 645);

		//======== pnlCandies ========
		{
			pnlCandies.setBackground(new Color(0x63c0c0c0, true));
			pnlCandies.setOpaque(false);
			pnlCandies.setLayout(null);

			//---- candy11 ----
			candy11.setBackground(new Color(0xa3ccffff, true));
			candy11.setIcon(new ImageIcon("Images\\GreenPillow.png"));
			candy11.setText("1");
			candy11.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy11MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy11MouseReleased(e);
				}
			});
			candy11.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy11MouseDragged(e);
				}
			});
			pnlCandies.add(candy11);
			candy11.setBounds(0, 0, 60, 60);

			//---- candy12 ----
			candy12.setBackground(new Color(0xa3ccffff, true));
			candy12.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy12.setText("2");
			candy12.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy12MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy12MouseReleased(e);
				}
			});
			candy12.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy12MouseDragged(e);
				}
			});
			pnlCandies.add(candy12);
			candy12.setBounds(60, 0, 60, 60);

			//---- candy13 ----
			candy13.setBackground(new Color(0xa3ccffff, true));
			candy13.setIcon(new ImageIcon("Images\\GreenPillow.png"));
			candy13.setText("3");
			candy13.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy13MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy13MouseReleased(e);
				}
			});
			candy13.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy13MouseDragged(e);
				}
			});
			pnlCandies.add(candy13);
			candy13.setBounds(120, 0, 60, 60);

			//---- candy14 ----
			candy14.setBackground(new Color(0xa3ccffff, true));
			candy14.setIcon(new ImageIcon("Images\\GreenPillow.png"));
			candy14.setText("4");
			candy14.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy14MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy14MouseReleased(e);
				}
			});
			candy14.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy14MouseDragged(e);
				}
			});
			pnlCandies.add(candy14);
			candy14.setBounds(180, 0, 60, 60);

			//---- candy15 ----
			candy15.setBackground(new Color(0xa3ccffff, true));
			candy15.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy15.setText("5");
			candy15.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy15MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy15MouseReleased(e);
				}
			});
			candy15.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy15MouseDragged(e);
				}
			});
			pnlCandies.add(candy15);
			candy15.setBounds(240, 0, 60, 60);

			//---- candy21 ----
			candy21.setBackground(new Color(0xa3ccffff, true));
			candy21.setIcon(new ImageIcon("Images\\RedSausage.png"));
			candy21.setText("3");
			candy21.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy21MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy21MouseReleased(e);
				}
			});
			candy21.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy21MouseDragged(e);
				}
			});
			pnlCandies.add(candy21);
			candy21.setBounds(0, 60, 60, 60);

			//---- candy22 ----
			candy22.setBackground(new Color(0xa3ccffff, true));
			candy22.setIcon(new ImageIcon("Images\\BluePlanet.png"));
			candy22.setText("5");
			candy22.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy22MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy22MouseReleased(e);
				}
			});
			candy22.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy22MouseDragged(e);
				}
			});
			pnlCandies.add(candy22);
			candy22.setBounds(60, 60, 60, 60);

			//---- candy23 ----
			candy23.setBackground(new Color(0xa3ccffff, true));
			candy23.setIcon(new ImageIcon("Images\\RedSausage.png"));
			candy23.setText("3");
			candy23.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy23MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy23MouseReleased(e);
				}
			});
			candy23.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy23MouseDragged(e);
				}
			});
			pnlCandies.add(candy23);
			candy23.setBounds(120, 60, 60, 60);

			//---- candy24 ----
			candy24.setBackground(new Color(0xa3ccffff, true));
			candy24.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy24.setText("2");
			candy24.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy24MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy24MouseReleased(e);
				}
			});
			candy24.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy24MouseDragged(e);
				}
			});
			pnlCandies.add(candy24);
			candy24.setBounds(180, 60, 60, 60);

			//---- candy25 ----
			candy25.setBackground(new Color(0xa3ccffff, true));
			candy25.setIcon(new ImageIcon("Images\\GreenPillow.png"));
			candy25.setText("1");
			candy25.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy25MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy25MouseReleased(e);
				}
			});
			candy25.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy25MouseDragged(e);
				}
			});
			pnlCandies.add(candy25);
			candy25.setBounds(240, 60, 60, 60);

			//---- candy31 ----
			candy31.setBackground(new Color(0xa3ccffff, true));
			candy31.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy31.setText("2");
			candy31.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy31MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy31MouseReleased(e);
				}
			});
			candy31.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy31MouseDragged(e);
				}
			});
			pnlCandies.add(candy31);
			candy31.setBounds(0, 120, 60, 60);

			//---- candy32 ----
			candy32.setBackground(new Color(0xa3ccffff, true));
			candy32.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy32.setText("4");
			candy32.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy32MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy32MouseReleased(e);
				}
			});
			candy32.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy32MouseDragged(e);
				}
			});
			pnlCandies.add(candy32);
			candy32.setBounds(60, 120, 60, 60);

			//---- candy33 ----
			candy33.setBackground(new Color(0xa3ccffff, true));
			candy33.setIcon(new ImageIcon("Images\\BluePlanet.png"));
			candy33.setText("5");
			candy33.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy33MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy33MouseReleased(e);
				}
			});
			candy33.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy33MouseDragged(e);
				}
			});
			pnlCandies.add(candy33);
			candy33.setBounds(120, 120, 60, 60);

			//---- candy34 ----
			candy34.setBackground(new Color(0xa3ccffff, true));
			candy34.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy34.setText("2");
			candy34.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy34MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy34MouseReleased(e);
				}
			});
			candy34.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy34MouseDragged(e);
				}
			});
			pnlCandies.add(candy34);
			candy34.setBounds(180, 120, 60, 60);

			//---- candy35 ----
			candy35.setBackground(new Color(0xa3ccffff, true));
			candy35.setIcon(new ImageIcon("Images\\YellowTearDrop.png"));
			candy35.setText("4");
			candy35.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy35MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy35MouseReleased(e);
				}
			});
			candy35.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy35MouseDragged(e);
				}
			});
			pnlCandies.add(candy35);
			candy35.setBounds(240, 120, 60, 60);

			//---- candy41 ----
			candy41.setBackground(new Color(0xa3ccffff, true));
			candy41.setIcon(new ImageIcon("Images\\GreenPillow.png"));
			candy41.setText("1");
			candy41.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy41MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy41MouseReleased(e);
				}
			});
			candy41.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy41MouseDragged(e);
				}
			});
			pnlCandies.add(candy41);
			candy41.setBounds(0, 180, 60, 60);

			//---- candy42 ----
			candy42.setBackground(new Color(0xa3ccffff, true));
			candy42.setIcon(new ImageIcon("Images\\YellowTearDrop.png"));
			candy42.setText("4");
			candy42.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy42MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy42MouseReleased(e);
				}
			});
			candy42.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy42MouseDragged(e);
				}
			});
			pnlCandies.add(candy42);
			candy42.setBounds(60, 180, 60, 60);

			//---- candy43 ----
			candy43.setBackground(new Color(0xa3ccffff, true));
			candy43.setIcon(new ImageIcon("Images\\RedSausage.png"));
			candy43.setText("3");
			candy43.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy43MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy43MouseReleased(e);
				}
			});
			candy43.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy43MouseDragged(e);
				}
			});
			pnlCandies.add(candy43);
			candy43.setBounds(120, 180, 60, 60);

			//---- candy44 ----
			candy44.setBackground(new Color(0xa3ccffff, true));
			candy44.setIcon(new ImageIcon("Images\\GreenPillow.png"));
			candy44.setText("1");
			candy44.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy44MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy44MouseReleased(e);
				}
			});
			candy44.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy44MouseDragged(e);
				}
			});
			pnlCandies.add(candy44);
			candy44.setBounds(180, 180, 60, 60);

			//---- candy45 ----
			candy45.setBackground(new Color(0xa3ccffff, true));
			candy45.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy45.setText("2");
			candy45.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy45MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy45MouseReleased(e);
				}
			});
			candy45.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy45MouseDragged(e);
				}
			});
			pnlCandies.add(candy45);
			candy45.setBounds(240, 180, 60, 60);

			//---- candy51 ----
			candy51.setBackground(new Color(0xa3ccffff, true));
			candy51.setIcon(new ImageIcon("Images\\YellowTearDrop.png"));
			candy51.setText("4");
			candy51.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy51MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy51MouseReleased(e);
				}
			});
			candy51.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy51MouseDragged(e);
				}
			});
			pnlCandies.add(candy51);
			candy51.setBounds(0, 240, 60, 60);

			//---- candy52 ----
			candy52.setBackground(new Color(0xa3ccffff, true));
			candy52.setIcon(new ImageIcon("Images\\BluePlanet.png"));
			candy52.setText("1");
			candy52.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy52MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy52MouseReleased(e);
				}
			});
			candy52.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy52MouseDragged(e);
				}
			});
			pnlCandies.add(candy52);
			candy52.setBounds(60, 240, 60, 60);

			//---- candy53 ----
			candy53.setBackground(new Color(0xa3ccffff, true));
			candy53.setIcon(new ImageIcon("Images\\OrangeLozenge.png"));
			candy53.setText("2");
			candy53.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy53MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy53MouseReleased(e);
				}
			});
			candy53.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy53MouseDragged(e);
				}
			});
			pnlCandies.add(candy53);
			candy53.setBounds(120, 240, 60, 60);

			//---- candy54 ----
			candy54.setBackground(new Color(0xa3ccffff, true));
			candy54.setIcon(new ImageIcon("Images\\BluePlanet.png"));
			candy54.setText("5");
			candy54.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy54MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy54MouseReleased(e);
				}
			});
			candy54.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy54MouseDragged(e);
				}
			});
			pnlCandies.add(candy54);
			candy54.setBounds(180, 240, 60, 60);

			//---- candy55 ----
			candy55.setBackground(new Color(0xa3ccffff, true));
			candy55.setIcon(new ImageIcon("Images\\BluePlanet.png"));
			candy55.setText("5");
			candy55.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					candy55MousePressed(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					candy55MouseReleased(e);
				}
			});
			candy55.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					candy55MouseDragged(e);
				}
			});
			pnlCandies.add(candy55);
			candy55.setBounds(240, 240, 60, 60);

			{
				// compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < pnlCandies.getComponentCount(); i++) {
					Rectangle bounds = pnlCandies.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = pnlCandies.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				pnlCandies.setMinimumSize(preferredSize);
				pnlCandies.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(pnlCandies);
		pnlCandies.setBounds(635, 190, 300, 300);

		//---- lblCandiesBackground ----
		lblCandiesBackground.setOpaque(true);
		lblCandiesBackground.setBackground(new Color(0xb2ffcccc, true));
		lblCandiesBackground.setBorder(new LineBorder(Color.pink, 4, true));
		contentPane.add(lblCandiesBackground);
		lblCandiesBackground.setBounds(630, 185, 310, 310);
		contentPane.add(textField1);
		textField1.setBounds(new Rectangle(new Point(0, 700), textField1.getPreferredSize()));

		//---- lblbackground ----
		lblbackground.setText("text");
		lblbackground.setIcon(new ImageIcon("Images\\MainGameBackground1.jpg"));
		contentPane.add(lblbackground);
		lblbackground.setBounds(0, 0, 1370, 695);

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
		setSize(1370, 725);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Charlie Marzan (Charlie S Marzan)
	private JLabel lblSoundEffect;
	private JLabel lblMusic;
	private JLabel lblShuffleNum;
	private JLabel lblShuffNumbackground;
	private JLabel lblTargetNum;
	private JLabel lblMoveNum;
	private JLabel lblScoreNum;
	private JLabel lblHelp;
	private JLabel lblShuffle;
	private JLabel lblRetry;
	private JLabel lblFirstStar;
	private JLabel lblThirdStar;
	private JLabel lblSecondStar;
	private JProgressBar ScoreProgressBar;
	private JLabel lblScoreBoardBackground;
	private JPanel pnlCandies;
	private JLabel candy11;
	private JLabel candy12;
	private JLabel candy13;
	private JLabel candy14;
	private JLabel candy15;
	private JLabel candy21;
	private JLabel candy22;
	private JLabel candy23;
	private JLabel candy24;
	private JLabel candy25;
	private JLabel candy31;
	private JLabel candy32;
	private JLabel candy33;
	private JLabel candy34;
	private JLabel candy35;
	private JLabel candy41;
	private JLabel candy42;
	private JLabel candy43;
	private JLabel candy44;
	private JLabel candy45;
	private JLabel candy51;
	private JLabel candy52;
	private JLabel candy53;
	private JLabel candy54;
	private JLabel candy55;
	private JLabel lblCandiesBackground;
	private JTextField textField1;
	private JLabel lblbackground;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
