import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.lang.Math;
import javafx.scene.layout.GridPane;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * This class represents the game 2048
 * @author: Maya Yagan
 */
public class SlideGame extends Application{
  //stores the array of buttons that represents the screen
  private Button[][] buttons;
  
  /**
   * creates the GUI display
   * @param primaryStage the main window
   */
  public void start(Stage primaryStage){
    //stores the width/length of the board
    int width=4;
    int length=4;
    buttons = new Button[length][width];
    GridPane grid = new GridPane();
    //initializes every button as a blank button
    for(int i = 0; i < length; i++){
      for(int j = 0; j < width; j++){
        buttons[i][j] = new Button("  ");
        buttons[i][j].setMinSize(100,100);
      }
      grid.addRow(i, buttons[i]);
    }
    //stores the two indices to start one random square off as one
    int indOne = (int)(Math.random() * width);
    int indTwo = (int)(Math.random() * length);
    buttons[indOne][indTwo].setText("2");
    
    //buttons on the left side clicked
    buttons[1][0].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveLeft(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    buttons[2][0].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveLeft(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    
    //butons on the right side clicked
    buttons[1][3].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveRight(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    buttons[2][3].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveRight(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    
    //buttons on the top clicked
    buttons[0][1].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveUp(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    buttons[0][2].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveUp(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    
    //buttons on the bottom clicked
    buttons[3][1].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveDown(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    buttons[3][2].setOnAction(e -> {
      if(isGameWon(buttonToInt(buttons)))
        gameWon();
      intToButton(moveDown(buttonToInt(buttons)));
      intToButton(addOne(buttonToInt(buttons)));
      if(isGameOver(buttonToInt(buttons)))
        gameOver();
    });
    
    //new scene for the grid
    Scene scene = new Scene(grid);
    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.setTitle("2048 Slide Game");
    primaryStage.show();
  }
  
  /**
   * This method shows a game over message when no more moves are available in the game
   */ 
  public void gameOver(){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(null);
    alert.setContentText("Game Over!" + "\n" + "No more moves available." + "\n" + "Good Luck next time");
    alert.showAndWait();
  }
  
  /**
   * This method shows a winning message when the game is won
   */ 
  public void gameWon(){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Congrats!");
    alert.setHeaderText(null);
    alert.setContentText("You Won!" + "\n" + "You reached 2048 and won the game!");
    alert.showAndWait();
  }
  
  /**
   * This method moves all the elements to the left and adds any adjacent elements in an array
   * @param array the array whose elements will be moved to the left
   * @return the new array after moving the elemnts to the left
   */ 
  public int[][] moveLeft(int[][] array){
    array = slideElements(array, 'L');
    for(int i = 0; i < array.length; i++){
      for(int j = 0; j < array[i].length; j++){
        if(array[i][j] != 0){
          if(j + 1 < array[i].length && array[i][j] == array[i][j + 1]){
            array[i][j] += array[i][j + 1];
            array[i][j + 1] = 0;
            array = slideElements(array, 'L');
          }
        }
      }
    }
    return array;
  }
  
  /**
   * This method moves all the elements to the right and adds any adjacent elements in an array
   * @param array the array whose elements will be moved to the right
   * @return the new array after moving the elemnts to the right
   */ 
  public int[][] moveRight(int[][] array){
    array = slideElements(array, 'R');
    for(int i = 0; i < array.length; i++){
      for(int j = array[i].length - 1; j >= 0; j--){
        if(array[i][j] != 0){
          if(j - 1 >= 0 && array[i][j] == array[i][j - 1]){
            array[i][j] += array[i][j - 1];
            array[i][j - 1] = 0;
            array = slideElements(array, 'R');
          }
        }
      }
    }
    return array;
  }
  
  /**
   * This method moves all the elements upward and adds any adjacent elements in an array
   * @param array the array whose elements will be moved upward
   * @return the new array after moving the elemnts upward
   */ 
  public int[][] moveUp(int[][] array){
    array = slideElements(array, 'U');
    for(int i = 0; i < array.length; i++){
      for(int j = 0; j < array[i].length; j++){
        if(array[j][i] != 0){
          if(j + 1 < array[i].length && array[j][i] == array[j + 1][i]){
            array[j][i] += array[j + 1][i];
            array[j + 1][i] = 0;
            array = slideElements(array, 'U');
          }
        }
      }
    }
    return array;
  }
  
  /**
   * This method moves all the elements downward and adds any adjacent elements in an array
   * @param array the array whose elements will be moved downward
   * @return the new array after moving the elemnts downward
   */ 
  public int[][] moveDown(int[][] array){
    array = slideElements(array, 'D');
    for(int i = 0; i < array.length; i++){
      for(int j = array[i].length - 1; j >= 0; j--){
        if(array[j][i] != 0){
          if(j - 1 >= 0 && array[j][i] == array[j - 1][i]){
            array[j][i] += array[j - 1][i];
            array[j - 1][i] = 0;
            array = slideElements(array, 'D');
          }
        }
      }
    }
    return array;
  }
  
  /**
   * Helper method that will move all the elements in an array in the given directoin
   * @param array the array whose elements will be moved 
   * @param direction the direction in which the elements will be moved
   * @return the array after moving the elements in the given direction
   */ 
  public int[][] slideElements(int[][] array, char direction){
    if(direction == 'L'){
      for(int i = 0; i < array.length; i++){
        for(int j = 0; j < array[i].length; j++){
          if(array[i][j] != 0){
            int k = j;
            while(k - 1 >= 0){
              if(array[i][k - 1] == 0){
                array[i][k - 1] = array[i][k];
                array[i][k] = 0;
              }
              k--;
            }
          }
        }
      }
    }
    if(direction == 'R'){
      for(int i = 0; i < array.length; i++){
        for(int j = array[i].length - 1; j >= 0; j--){
          if(array[i][j] != 0){
            int k = j;
            while(k + 1 < array[i].length){
              if(array[i][k + 1] == 0){
                array[i][k + 1] = array[i][k];
                array[i][k] = 0;
              }
              k++;
            }
          }
        }
      }
    }
    if(direction == 'U'){
      for(int i = 0; i < array.length; i++){
        for(int j = 0; j < array[i].length; j++){
          if(array[j][i] != 0){
            int k = j;
            while(k - 1 >= 0){
              if(array[k - 1][i] == 0){
                array[k - 1][i] = array[k][i];
                array[k][i] = 0;
              }
              k--;
            }
          }
        }
      }
    }
    if(direction == 'D'){
      for(int i = 0; i < array.length; i++){
        for(int j = array[i].length - 1; j >= 0; j--){
          if(array[j][i] != 0){
            int k = j;
            while(k + 1 < array[i].length){
              if(array[k + 1][i] == 0){
                array[k + 1][i] = array[k][i];
                array[k][i] = 0;
              }
              k++;
            }
          }
        }
      }
    }
    return array;
  }
  
  /**
   * This method checks if the game is over by checking if there are any adjacent equal elments vertically or horizontally and if all the elements are non zero
   * @param array that will be evaluated
   * @return true/false if there are no more moves in the game
   */ 
  public boolean isGameOver(int[][] array){
    boolean nonZero = (numberNonZero(array) == 16);
    for(int i = 0; i < array.length; i++){
      for(int j = 0; j < array[i].length - 1; j++){
        if(array[i][j] == array[i][j + 1])
          return false;
      }
    }
    for(int i = 0; i < array.length - 1; i++){
      for(int j = 0; j < array[i].length; j++){
        if (array[i][j] == array[i + 1][j])
          return false;
      }
    }
    return true && nonZero;
  }
  
  /**
   * This method checks if the game is won or not
   * @param array the array which will be evaluated
   * @return true/false if there 2048 has been reached
   */ 
  public boolean isGameWon(int[][] array){
    for(int i = 0; i < array.length; i++) {
      for(int j = 0; j < array[i].length; j++){
        if(array[i][j] == 2048)
          return true;
      }
    }
    return false;
  }
  
  /**
   * helper method to convert from an array of ints to an array of buttons
   * @param ints an array of ints
   */
  public void intToButton(int[][] ints){
    //loops through each of the ints to set the text on buttons
    for(int i = 0; i < ints.length; i++){
      for(int j = 0; j < ints[i].length; j++){
        if(ints[i][j] == 0)
          buttons[i][j].setText("  ");
        else
          buttons[i][j].setText(Integer.toString(ints[i][j]));
      }
    }
  }
  
  /**
   * helper method to convert an array of buttons to an array of ints
   * @param buttons an array of buttons
   * @return an array of ints
   */
  public int[][] buttonToInt(Button[][] buttons){
    //stores the final array of ints
    int[][] finalArray = new int[buttons.length][buttons[0].length];
    //runs through each button intiliaze ints based off the text
    for(int i = 0; i < buttons.length; i++){
      for(int j = 0; j < buttons[i].length; j++){
        if(buttons[i][j].getText().equals("  "))
          finalArray[i][j] = 0;
        else 
          finalArray[i][j] = Integer.parseInt(buttons[i][j].getText());
      }
    }
    return finalArray;
  }
  
  /**
   * helper method to generate an extra one
   * @param ints an array of ints
   * @return an array of ints with a new one tile
   */
  public static int[][] addOne(int[][] ints){
    int indOne = (int)(Math.random() * ints.length);
    int indTwo = (int)(Math.random() * ints[0].length);
    //stores whether or not there is an empty space
    boolean isSpace = false;
    //checks to make sure that there is an empty space
    for(int i = 0; i < ints.length; i++){
      for(int j = 0; j < ints[i].length; j++){
        if (ints[i][j] == 0)
          isSpace = true;
      }
    }
    //if there is space on the board, it will keep generating numbers until it hits a blank space
    while(ints[indOne][indTwo] != 0 && isSpace){
      indOne = (int) (Math.random() * ints.length);
      indTwo = (int) (Math.random() * ints[0].length);
    } 
    ints[indOne][indTwo] = 2;
    return ints;
  }
  
  /**
   * helper to determine the number of non-zeroes in a method, to test addOne
   * @param ints a multidimensional array
   * @return the number of non-zeros in the method
   */
  public static int numberNonZero(int[][] ints){
    int numNonZero = 0;
    //loops through the array
    for(int i = 0; i < ints.length; i++) {
      for(int j = 0; j < ints[i].length; j++){
        if(ints[i][j] != 0)
          numNonZero++;
      }
    }
    return numNonZero;
  }
   
  /**
   * The main method to run the program  
   * @param args the command line arguments  
   */ 
  public static void main(String[] args){ 
    SlideGame.launch(args);              
  }
}