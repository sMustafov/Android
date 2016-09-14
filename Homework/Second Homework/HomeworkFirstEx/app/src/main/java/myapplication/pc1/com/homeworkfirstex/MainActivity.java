package myapplication.pc1.com.homeworkfirstex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button[][] buttons;
    private String[][] board;
    private TextView winner;

    private int turn;
    private String mX = "X"; // always starts with X
    private String mO = "O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[3][3];
        board = new String[3][3];

        //initialize board with letter E for empty
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "E";
            }
        }

        winner = (TextView)findViewById(R.id.textView2);

        buttons[0][0] = (Button) findViewById(R.id.button);
        buttons[0][1] = (Button) findViewById(R.id.button2);
        buttons[0][2] = (Button) findViewById(R.id.button3);
        buttons[1][0] = (Button) findViewById(R.id.button4);
        buttons[1][1] = (Button) findViewById(R.id.button5);
        buttons[1][2] = (Button) findViewById(R.id.button6);
        buttons[2][0] = (Button) findViewById(R.id.button7);
        buttons[2][1] = (Button) findViewById(R.id.button8);
        buttons[2][2] = (Button) findViewById(R.id.button9);

        buttons[0][0].setOnClickListener(this);
        buttons[0][1].setOnClickListener(this);
        buttons[0][2].setOnClickListener(this);
        buttons[1][0].setOnClickListener(this);
        buttons[1][1].setOnClickListener(this);
        buttons[1][2] .setOnClickListener(this);
        buttons[2][0].setOnClickListener(this);
        buttons[2][1].setOnClickListener(this);
        buttons[2][2].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        turn++;
        if(v.getId() == R.id.button){
            setTextToButton(buttons[0][0], 0, 0);
        }else if(v.getId() == R.id.button2){
            setTextToButton(buttons[0][1], 0, 1);
        }else if(v.getId() == R.id.button3){
            setTextToButton(buttons[0][2], 0, 2);
        }else if(v.getId() == R.id.button4){
            setTextToButton(buttons[1][0], 1, 0);
        }else if(v.getId() == R.id.button5){
            setTextToButton(buttons[1][1], 1, 1);
        }else if(v.getId() == R.id.button6){
            setTextToButton(buttons[1][2], 1, 2);
        }else if(v.getId() == R.id.button7){
            setTextToButton(buttons[2][0], 2, 0);
        }else if(v.getId() == R.id.button8){
            setTextToButton(buttons[2][1], 2, 1);
        }else if(v.getId() == R.id.button9){
            setTextToButton(buttons[2][2], 2 , 2);
        }
    }

    private void setTextToButton(Button btn, int x, int y){
        if(btn.getText() != mX && btn.getText() != mO) {
            if (turn % 2 == 0) {
                board[x][y] = mX;
                if(checkWinner(3, mX, x, y)){
                    winner.setText("X is Winner");
                    disableButtons();
                }else if(turn == 9){
                    winner.setText("Draw");
                    disableButtons();
                }
                btn.setText(mX);
            } else {
                board[x][y] = mO;
                if(checkWinner(3, mO, x, y)){
                    winner.setText("O is Winner");
                    disableButtons();
                }else if(turn == 9){
                    winner.setText("Draw");
                    disableButtons();
                }
                btn.setText(mO);
            }
        }
        else{
            turn--;
        }
    }

    private boolean checkWinner(int size, String player, int x, int y) {
       //check colomn for winner
        if(board[0][y] == board[1][y] && board[0][y] == board[2][y]){
            return true;
        }

        //check row for winner
        if(board[x][0] == board[x][1] && board[x][0] == board[x][2]){
            return true;
        }

        //check for right diagonal winner
        if((x == 0 && y == 0) ||
                (x == 2 && y == 2)) {
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
                return true;
            }
        }

        //check for left diagonal winner
        if((x == 0 && y == 2) ||
                (x == 2 && y == 0)){
            if(board[0][2] == board[1][1] && board[0][2] == board[2][0]){
                return true;
            }
        }

        return false;
    }

    // Disable buttons when the game finish
    private void disableButtons(){
        buttons[0][0].setEnabled(false);
        buttons[0][1].setEnabled(false);
        buttons[0][2].setEnabled(false);
        buttons[1][0].setEnabled(false);
        buttons[1][1].setEnabled(false);
        buttons[1][2].setEnabled(false);
        buttons[2][0].setEnabled(false);
        buttons[2][1].setEnabled(false);
        buttons[2][2].setEnabled(false);
    }
}
