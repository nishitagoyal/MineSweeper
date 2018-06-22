package com.example.goyal.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    LinearLayout rootLayout;
    public int SIZE = 5;
    public ArrayList<LinearLayout> rows = new ArrayList<>();
    public MineButton[][] board;
    public static int[] X ={-1,-1,-1,0,0,1,1,1};
    public static int[] Y ={-1,0,1,-1,+1,-1,0,1};
    private boolean areMinesSet=false;
    public static final int INCOMPLETE = 1;
    public static final int COMPLETE = 0;
    public static int currentStatus=INCOMPLETE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.Mineboard);
        setupboard();
        setMines();
        setNumber();
    }


    public void setupboard()
    {
        board = new MineButton[SIZE][SIZE];
        rootLayout.removeAllViews();
        rows.clear();
        for (int i = 0; i < SIZE; i++)
        {
            LinearLayout LinearLayout = new LinearLayout(this);
            LinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            LinearLayout.setLayoutParams(LayoutParams);
            rootLayout.addView(LinearLayout);
            rows.add(LinearLayout);
        }

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                MineButton button = new MineButton(this);
                LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                button.setLayoutParams(LayoutParams);
                button.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  if (currentStatus == INCOMPLETE) {
                                                      MineButton button3 = (MineButton) view;
                                                      if (!areMinesSet) {
                                                          areMinesSet = true;
                                                          setMines();
                                                          setNumber();
                                                      }
                                                      int temp = button3.getValue();
                                                      if (temp == -1) {
                                                          button3.setText("*");
                                                          for (int m = 0; m < SIZE; m++) {
                                                              for (int n = 0; n < SIZE; n++) {
                                                                  button3.setEnabled(false);
                                                                  if (temp == -1)
                                                                      button3.setText("*");
                                                                  currentStatus = COMPLETE;

                                                              }
                                                          }
                                                          // Toast.makeText(this,"You Lose", Toast.LENGTH_LONG).show();
                                                      } else if (temp > 0) {
                                                          int x = button3.getValue();
                                                          button3.setText(Integer.toString(x));
                                                          button3.setEnabled(false);
                                                      }
                                                  }
                                              }
                                          });
                LinearLayout row = rows.get(i);
                row.addView(button);
                board[i][j] = button;
            }


            }
        }


    private void setMines() {
        Random random = new Random();
        for (int p = 0; p < 10; p++) {
            int m = random.nextInt(SIZE - 1);
            int n = random.nextInt(SIZE - 1);
            board[m][n].setValue(-1);


        }

    }

    private void setNumber() {
        for (int p = 0; p < SIZE; p++) {
            for (int q = 0; q < SIZE; q++) {
                MineButton button1 = board[p][q];
                if (button1.getValue() != -1)
                    button1.setValue(0);
            }
        }
        for (int p = 0; p < SIZE; p++) {
            for (int q = 0; q < SIZE; q++) {
                MineButton button2 = board[p][q];
                if (button2.getValue() == -1) {
                    if (p == 0 && q == 0) {
                        int temp = board[0][1].getValue();
                        if (temp != -1)
                            board[0][1].setValue(temp + 1);
                        int temp1 = board[1][0].getValue();
                        if(temp1 != -1)
                            board[1][0].setValue(temp1 + 1);
                        int temp2 = board[1][1].getValue();
                        if(temp2 != -1)
                            board[1][1].setValue(temp2 + 1);
                    }
                    else if (p == 0 && q == SIZE-1) {
                        int temp = board[0][SIZE-2].getValue();
                        if (temp != -1)
                            board[0][SIZE-2].setValue(temp + 1);
                        int temp1 = board[1][SIZE-1].getValue();
                        if(temp1 != -1)
                            board[1][SIZE-1].setValue(temp1 + 1);
                        int temp2 = board[1][SIZE-2].getValue();
                        if(temp2 != -1)
                            board[1][SIZE-2].setValue(temp2 + 1);
                    }
                    else if (p == SIZE-1 && q == 0) {
                        int temp = board[SIZE-2][0].getValue();
                        if (temp != -1)
                            board[SIZE-2][0].setValue(temp + 1);
                        int temp1 = board[SIZE-2][1].getValue();
                        if(temp1 != -1)
                            board[SIZE-2][1].setValue(temp1 + 1);
                        int temp2 = board[SIZE-1][1].getValue();
                        if(temp2 != -1)
                            board[SIZE-1][1].setValue(temp2 + 1);
                    }
                    else if (p == SIZE-1 && q == SIZE-1) {
                        int temp = board[SIZE-2][SIZE-1].getValue();
                        if (temp != -1)
                            board[SIZE-2][SIZE-1].setValue(temp + 1);
                        int temp1 = board[SIZE-2][SIZE-2].getValue();
                        if(temp1 != -1)
                            board[SIZE-2][SIZE-2].setValue(temp1 + 1);
                        int temp2 = board[SIZE-1][SIZE-2].getValue();
                        if(temp2 != -1)
                            board[SIZE-1][SIZE-2].setValue(temp2 + 1);
                    }
                    else if(p==0)
                    {
                       for(q=1;q<SIZE-1;q++)
                       {
                           int tempX=board[0][q].getValue();
                           if(tempX == -1)
                           {
                               int temp = board[p][q-1].getValue();
                               if (temp != -1)
                                   board[p][q-1].setValue(temp + 1);
                               int temp1 = board[p][q+1].getValue();
                               if(temp1 != -1)
                                   board[p][q+1].setValue(temp1 + 1);
                               int temp2 = board[p+1][q].getValue();
                               if(temp2 != -1)
                                   board[p+1][q].setValue(temp2 + 1);
                               int temp3 = board[p+1][q-1].getValue();
                               if (temp3 != -1)
                                   board[p+1][q-1].setValue(temp3 + 1);
                               int temp4 = board[p+1][q+1].getValue();
                               if(temp4 != -1)
                                   board[p+1][q+1].setValue(temp4 + 1);
                           }

                       }
                    }
                    else if(p==SIZE-1)
                    {
                        for(q=1;q<SIZE-1;q++)
                        {
                            int tempX=board[SIZE-1][q].getValue();
                            if(tempX == -1)
                            {
                                int temp = board[p][q-1].getValue();
                                if (temp != -1)
                                    board[p][q-1].setValue(temp + 1);
                                int temp1 = board[p][q+1].getValue();
                                if(temp1 != -1)
                                    board[p][q+1].setValue(temp1 + 1);
                                int temp2 = board[p-1][q].getValue();
                                if(temp2 != -1)
                                    board[p-1][q].setValue(temp2 + 1);
                                int temp3 = board[p-1][q-1].getValue();
                                if (temp3 != -1)
                                    board[p-1][q-1].setValue(temp3 + 1);
                                int temp4 = board[p-1][q+1].getValue();
                                if(temp4 != -1)
                                    board[p-1][q+1].setValue(temp4 + 1);
                            }

                        }
                    }
                    else if(q==0)
                    {
                        for(p=1;p<SIZE-1;p++)
                        {
                            int tempX=board[p][0].getValue();
                            if(tempX == -1)
                            {
                                int temp = board[p-1][q].getValue();
                                if (temp != -1)
                                    board[p-1][q].setValue(temp + 1);
                                int temp1 = board[p+1][q].getValue();
                                if(temp1 != -1)
                                    board[p+1][q].setValue(temp1 + 1);
                                int temp2 = board[p+1][q+1].getValue();
                                if(temp2 != -1)
                                    board[p+1][q+1].setValue(temp2 + 1);
                                int temp3 = board[p][q+1].getValue();
                                if (temp3 != -1)
                                    board[p][q+1].setValue(temp3 + 1);
                                int temp4 = board[p-1][q+1].getValue();
                                if(temp4 != -1)
                                    board[p-1][q+1].setValue(temp4 + 1);
                            }

                        }
                    }
                    else if(q==SIZE-1)
                    {
                        for(p=1;p<SIZE-1;p++)
                        {
                            int tempX=board[p][SIZE-1].getValue();
                            if(tempX == -1)
                            {
                                int temp = board[p-1][q].getValue();
                                if (temp != -1)
                                    board[p-1][q].setValue(temp + 1);
                                int temp1 = board[p+1][q].getValue();
                                if(temp1 != -1)
                                    board[p+1][q].setValue(temp1 + 1);
                                int temp2 = board[p+1][q-1].getValue();
                                if(temp2 != -1)
                                    board[p+1][q-1].setValue(temp2 + 1);
                                int temp3 = board[p][q-1].getValue();
                                if (temp3 != -1)
                                    board[p][q-1].setValue(temp3 + 1);
                                int temp4 = board[p-1][q-1].getValue();
                                if(temp4 != -1)
                                    board[p-1][q-1].setValue(temp4 + 1);
                            }

                        }
                    }
                    else
                        {
                        for (int i = 0; i < 8; i++)
                        {
                            int temp = board[p + X[i]][q + Y[i]].getValue();
                            if(temp!=-1)
                            {
                                board[p + X[i]][q + Y[i]].setValue(temp+1);
                            }
                        }


                    }




                }


            }

        }

    }

}


