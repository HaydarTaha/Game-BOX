/* This program runs the game called 'Game Box' 
 * and allows it to be played with the mouse.
 *
 * Student Name - ID:
 * Emir BÜÇKÜN - 150119024
 * Haydar Taha TUNÇ - 150119745 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.FadeTransition;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			// Create main panes
			BorderPane menuPane = new BorderPane();
			BorderPane gamePane = new BorderPane();
			BorderPane highScorePane = new BorderPane();

			// Create game pane parts
			BorderPane topPane = new BorderPane();
			GridPane centerPane = new GridPane();
			BorderPane bottomPane = new BorderPane();

			// Create high scores pane part
			GridPane highScoreTable = new GridPane();

			// Set pane styles
			menuPane.setStyle("-fx-background-image: url('images/background.jpg'); -fx-background-size: 400 450;");

			topPane.setPadding(new Insets(1, 5, 1, 5));
			centerPane.setStyle("-fx-background-color: gray;");
			centerPane.setAlignment(Pos.CENTER);
			bottomPane.setPadding(new Insets(1, 5, 1, 5));

			highScoreTable.setAlignment(Pos.CENTER);
			highScoreTable.setHgap(30);
			highScoreTable.setVgap(10);
			highScorePane.setStyle("-fx-background-image: url('images/background.jpg'); -fx-background-size: 400 450;");

			// Create menu labels
			Label gameBox = new Label("GAME BOX");
			Label newGame = new Label("New Game");
			Label resumeGame = new Label("Resume Game");
			Label menuHighScores = new Label("High Scores");
			Label infoText = new Label("(You can click the 'M' button to return the menu)");

			// Create high score header label
			Label highScoresHeader = new Label("High Scores");

			// Set menu and high score label styles
			Font labelFont = Font.font("Times New Roman", FontWeight.BOLD, 20);
			Font headerLabelFont = Font.font("Times New Roman", FontWeight.BOLD, 30);
			Stop[] stops = new Stop[] { new Stop(0, Color.rgb(251, 118, 237, 1)),
					new Stop(1, Color.rgb(79, 191, 230, 1)) };
			LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

			// Set fonts
			gameBox.setFont(headerLabelFont);
			highScoresHeader.setFont(headerLabelFont);
			newGame.setFont(labelFont);
			resumeGame.setFont(labelFont);
			menuHighScores.setFont(labelFont);

			// Add linear gradient
			gameBox.setTextFill(linear);
			newGame.setTextFill(linear);
			resumeGame.setTextFill(linear);
			menuHighScores.setTextFill(linear);
			infoText.setTextFill(linear);
			highScoresHeader.setTextFill(linear);

			// Add the menu labels to centered vertical boxes
			VBox menuLabelBox = new VBox();
			VBox clickableLabelBox = new VBox();

			menuLabelBox.setAlignment(Pos.CENTER);
			menuLabelBox.setSpacing(40);
			clickableLabelBox.setAlignment(Pos.CENTER);
			clickableLabelBox.setSpacing(20);

			clickableLabelBox.getChildren().addAll(newGame, resumeGame, menuHighScores);
			menuLabelBox.getChildren().addAll(gameBox, clickableLabelBox, infoText);

			// Add menu labels to the menu pane
			menuPane.setCenter(menuLabelBox);

			// Add the high score labels to a centered vertical box
			VBox highScoreLabelBox = new VBox();
			highScoreLabelBox.setAlignment(Pos.CENTER);
			highScoreLabelBox.setSpacing(20);

			highScoreLabelBox.getChildren().addAll(highScoresHeader, highScoreTable);

			// Add high score labels to the highScorePane
			highScorePane.setCenter(highScoreLabelBox);

			// Create top labels of game scene
			Label level = new Label("Level #1");
			Label score = new Label("0");
			Label highScore = new Label("High Score: 0");

			// Create bottom labels of game scene
			Label action = new Label("---Text---");
			Label nextLevel = new Label("Next Level >>");
			nextLevel.setVisible(false);

			// Set top labels to the topPane
			topPane.setLeft(level);
			topPane.setCenter(score);
			topPane.setRight(highScore);

			// Set bottom labels to the bottomPane
			bottomPane.setLeft(action);
			bottomPane.setRight(nextLevel);

			// Set the parts of the game pane
			gamePane.setTop(topPane);
			gamePane.setCenter(centerPane);
			gamePane.setBottom(bottomPane);

			// Set the scenes
			Scene menuScene = new Scene(menuPane, 400, 450);
			Scene gameScene = new Scene(gamePane, 400, 450);
			Scene highScoreScene = new Scene(highScorePane, 400, 450);

			// Set the stage
			primaryStage.setTitle("Game BOX");
			primaryStage.setScene(menuScene);
			primaryStage.show();

			// Declare the box images
			Image imageWall = new Image("boxes/Wall Type Box.png");
			Image imageEmpty = new Image("boxes/Empty Type Box.png");
			Image imageMirror = new Image("boxes/Mirror Type Box.png");
			Image imageWood = new Image("boxes/Wood Type Box.png");

			// Set the mouse click event for resume game label
			resumeGame.setOnMouseClicked(e -> {
				if (level.getText() != "Level #1" || score.getText() != "0" || highScore.getText() != "High Score: 0")
					primaryStage.setScene(gameScene);
			});

			// Set the key pressed event for 'M' button to return the menu on game board
			gameScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.M)
					primaryStage.setScene(menuScene);
			});

			// Set the key pressed event for 'M' button to return the menu on highScoreScene
			highScoreScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.M)
					primaryStage.setScene(menuScene);
			});

			// Set the mouse click event for high scores label on menu
			menuHighScores.setOnMouseClicked(e -> {
				primaryStage.setScene(highScoreScene);
			});

			// Mouse click event for 'New Game' label
			newGame.setOnMouseClicked(e -> {
				// Edit label infos
				level.setText("Level #1");
				score.setText("0");
				highScore.setText("High Score: 0");
				action.setText("---Text---");
				nextLevel.setVisible(false);

				// Clear the high score table
				highScoreTable.getChildren().clear();

				// Declare a File instance
				File levelFile = new File("levels/level1.txt");

				// Declare a Scanner for the file
				Scanner input = null;
				try {
					input = new Scanner(levelFile);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				// An ArrayList for already added cells (row, column) and clear the grid pane
				ArrayList<String> addedCells = new ArrayList<String>();
				centerPane.getChildren().clear();

				while (input.hasNext()) {
					// Get the next line -> Get row and column indexes from the line -> Add the
					// indexes to the arraylist
					String boxType = input.next();
					int rowIndex = (int) boxType.charAt(boxType.length() - 3) - '0';
					int columnIndex = (int) boxType.charAt(boxType.length() - 1) - '0';
					addedCells.add(rowIndex + "," + columnIndex);

					// Add boxes to centerPane according to the box type
					if (boxType.substring(0, 4).equals("Wood")) // if it's wood box type
						centerPane.add(setupImage(imageWood), columnIndex, rowIndex);
					else if (boxType.substring(0, 5).equals("Empty")) // if it's empty box type
						centerPane.add(setupImage(imageEmpty), columnIndex, rowIndex);
					else if (boxType.substring(0, 6).equals("Mirror")) // if it's mirror box type
						centerPane.add(setupImage(imageMirror), columnIndex, rowIndex);
				}

				// Close the Scanner
				input.close();

				// Fill the remaining boxes with the wall type box
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (!addedCells.contains(i + "," + j))
							centerPane.add(setupImage(imageWall), j, i);
					}
				}
				primaryStage.setScene(gameScene);
			});

			// Mouse click event for game board
			centerPane.setOnMouseClicked(e -> {
				for (Node box : centerPane.getChildren()) {
					// Control the box for specific properties
					String boxType = getBoxType(box);
					boolean instanceCheck = box instanceof ImageView;
					boolean areaCheck = box.getBoundsInParent().contains(e.getX(), e.getY());
					boolean typeCheck = boxType == "Mirror" || boxType == "Wood";

					// If the control is successful, then take the coordinates of the clicked box
					if (instanceCheck && areaCheck && typeCheck) {
						int i = GridPane.getRowIndex(box);
						int j = GridPane.getColumnIndex(box);

						// Set the clicked box fade animation
						FadeTransition boxReinitializeFade = new FadeTransition();
						boxReinitializeFade.setFromValue(0.1);
						boxReinitializeFade.setToValue(10);
						boxReinitializeFade.setNode(box);

						// Destroy the clicked box
						if (boxType == "Mirror")
							((ImageView) box).setImage(imageEmpty);
						else if (boxType == "Wood")
							((ImageView) box).setImage(imageMirror);
						int destroyedBoxCount = 1;
						boxReinitializeFade.play();

						// Set the action text for clicked box
						String actionText = "Box: " + i + "-" + j;

						// Destroy the neighboring boxes
						for (Node aroundBox : centerPane.getChildren()) {
							// Get coordinates and type of the aroundBox
							int rowIndex = GridPane.getRowIndex(aroundBox);
							int colIndex = GridPane.getColumnIndex(aroundBox);
							String aroundBoxType = getBoxType(aroundBox);

							// Set the around box fade animation
							FadeTransition aroundBoxReinitializeFade = new FadeTransition();
							aroundBoxReinitializeFade.setFromValue(0.1);
							aroundBoxReinitializeFade.setToValue(10);
							aroundBoxReinitializeFade.setNode(aroundBox);

							// Validate the location of the aroundBox
							boolean topBoxCheck = rowIndex == i - 1 && colIndex == j;
							boolean rightBoxCheck = rowIndex == i && colIndex == j + 1;
							boolean bottomBoxCheck = rowIndex == i + 1 && colIndex == j;
							boolean leftBoxCheck = rowIndex == i && colIndex == j - 1;

							boolean locationControl = topBoxCheck || rightBoxCheck || bottomBoxCheck || leftBoxCheck;
							boolean typeControl = aroundBoxType == "Mirror" || aroundBoxType == "Wood";

							// If the box is in one of these places and its type is suitable for destroy
							if (locationControl && typeControl) {
								if (aroundBoxType == "Mirror")
									((ImageView) aroundBox).setImage(imageEmpty);
								else if (aroundBoxType == "Wood")
									((ImageView) aroundBox).setImage(imageMirror);
								destroyedBoxCount++;
								aroundBoxReinitializeFade.play();

								// Set the action text for destroyed neighboring box
								actionText += " - " + "Hit: " + rowIndex + "," + colIndex;
							}
						}

						// Get the earned point amount and add it to the score label
						int earnedPoint = getEarnedPoint(destroyedBoxCount);
						score.setText("" + (Integer.valueOf(score.getText()) + earnedPoint));

						// If the current score is bigger than the high score, set the high score
						int scoreValue = Integer.valueOf(score.getText());
						int highScoreValue = Integer.valueOf(highScore.getText().substring(12));

						if (scoreValue > highScoreValue)
							highScore.setText("High Score: " + score.getText());

						// Set the action text according to the amount of points earned
						if (earnedPoint <= 0)
							actionText += " (" + earnedPoint + " points)";
						else if (earnedPoint > 0)
							actionText += " (+" + earnedPoint + " points)";

						// Show the action text after click event
						action.setText("" + actionText);

						// Check if the level is finished or not
						boolean isFinished = true;
						for (Node allBoxes : centerPane.getChildren()) {
							String allBoxesType = getBoxType(allBoxes);

							if (allBoxesType == "Mirror" || allBoxesType == "Wood") {
								isFinished = false;
								break;
							}
						}

						int currentLevel = Integer.parseInt(level.getText().charAt(7) + "");
						if (isFinished == true) {
							// Before showing the next level label, set the high score to the highScorePane
							Label levelLabel = new Label("Level " + currentLevel);
							Label highScoreOfLevel = new Label("" + Integer.valueOf(highScore.getText().substring(12)));

							Font highScoreLabelFont = Font.font("Times New Roman", FontWeight.BOLD, 20);
							Stop[] highScoreStops = new Stop[] { new Stop(0, Color.rgb(251, 118, 237, 1)),
									new Stop(1, Color.rgb(79, 191, 230, 1)) };
							LinearGradient highScoreLinear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
									highScoreStops);

							levelLabel.setFont(highScoreLabelFont);
							highScoreOfLevel.setFont(highScoreLabelFont);
							levelLabel.setTextFill(highScoreLinear);
							highScoreOfLevel.setTextFill(highScoreLinear);

							highScoreTable.add(levelLabel, 0, currentLevel);
							highScoreTable.add(highScoreOfLevel, 1, currentLevel);

							if (currentLevel != 5)
								nextLevel.setVisible(true);
						}

						break;
					}
				}
			});

			// Mouse click event for 'Next Level >>' label
			nextLevel.setOnMouseClicked(e -> {
				// Take the next level by increasing the level number on the level label
				int nextLevelNumber = 1 + Integer.parseInt(level.getText().charAt(7) + "");

				// Edit label infos
				level.setText("Level #" + nextLevelNumber);
				score.setText("0");
				highScore.setText("High Score: 0");
				action.setText("---Text---");
				nextLevel.setVisible(false);

				// Declare a File instance
				File nextLevelFile = new File("levels/level" + nextLevelNumber + ".txt");

				// Declare a Scanner for the file
				Scanner nextInput = null;
				try {
					nextInput = new Scanner(nextLevelFile);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				// An ArrayList for already added cells (row, column) and clear the grid pane
				ArrayList<String> addedCells = new ArrayList<String>();
				centerPane.getChildren().clear();

				while (nextInput.hasNext()) {
					// Get the next line -> Get row and column indexes from the line -> Add the
					// indexes to the arraylist
					String boxType = nextInput.next();
					int rowIndex = (int) boxType.charAt(boxType.length() - 3) - '0';
					int columnIndex = (int) boxType.charAt(boxType.length() - 1) - '0';
					addedCells.add(rowIndex + "," + columnIndex);

					// Add boxes to centerPane according to the box type
					if (boxType.substring(0, 4).equals("Wood")) // if it's wood box type
						centerPane.add(setupImage(imageWood), columnIndex, rowIndex);
					else if (boxType.substring(0, 5).equals("Empty")) // if it's empty box type
						centerPane.add(setupImage(imageEmpty), columnIndex, rowIndex);
					else if (boxType.substring(0, 6).equals("Mirror")) // if it's mirror box type
						centerPane.add(setupImage(imageMirror), columnIndex, rowIndex);
				}

				// Close the Scanner
				nextInput.close();

				// Fill the remaining boxes with the wall type box
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (!addedCells.contains(i + "," + j))
							centerPane.add(setupImage(imageWall), j, i);
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Takes an image and converts it to ImageView with 39x39 sizes
	public ImageView setupImage(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(39);
		imageView.setFitWidth(39);
		return imageView;
	}

	// Gives the type of the box
	public String getBoxType(Node box) {
		String url = ((ImageView) box).getImage().getUrl();
		if (url.contains("Wall"))
			return "Wall";
		else if (url.contains("Empty"))
			return "Empty";
		else if (url.contains("Mirror"))
			return "Mirror";
		else if (url.contains("Wood"))
			return "Wood";
		else
			return "";
	}

	// Returns the amount of points earned based on the destroyed box count
	public int getEarnedPoint(int destroyedBoxCount) {
		int earnedPoint;

		switch (destroyedBoxCount) {
		case 1:
			earnedPoint = -3;
			break;
		case 2:
			earnedPoint = -1;
			break;
		case 3:
			earnedPoint = 1;
			break;
		case 4:
			earnedPoint = 2;
			break;
		case 5:
			earnedPoint = 4;
			break;
		default:
			earnedPoint = 0;
		}

		return earnedPoint;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
