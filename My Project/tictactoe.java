import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tictactoe {
     int boardWidth = 600, boardHeight=650;
     
     JFrame frame =new JFrame("Tic-Tac-Toe Game");
     
     JLabel textLabel = new JLabel();
     JPanel textPanel = new JPanel();
     JPanel boardPanel = new JPanel();
     
     JButton[][] board = new JButton[3][3];
     String playerX = "X" ; 
     String playerY = "O" ;
     String CurrentPlayer = playerX;
     
     boolean gameOver = false;
     int turns = 0;
     tictactoe(){
         frame.setVisible(true);
         frame.setSize(boardWidth, boardHeight);
         frame.setLocationRelativeTo(null);
         frame.setResizable(false);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLayout(new BorderLayout());
         
        
         textLabel.setBackground(Color.black);
         textLabel.setForeground(Color.white);
         textLabel.setFont(new Font("Arial",Font.BOLD,50));
         textLabel.setHorizontalAlignment(JLabel.CENTER);
         textLabel.setText("Tic-Tac-Toe");
         textLabel.setOpaque(true);
         
         textPanel.setLayout(new BorderLayout());
         frame.add(textLabel,BorderLayout.NORTH);
         
         boardPanel.setLayout(new GridLayout(3, 3));
         boardPanel.setBackground(Color.white);
         frame.add(boardPanel);
         for(int i=0;i<3;i++){
             for(int j=0;j<3;j++){
                 JButton tile = new JButton();
                 board[i][j] = tile;
                 boardPanel.add(tile);
                 
                 tile.setBackground(Color.black);
                 tile.setForeground(Color.white);
                 tile.setFont(new Font("Arial",Font.BOLD,100));
                 tile.setFocusable(false);
                 
                 tile.addActionListener(new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent e){
                         if(gameOver) return;
                         JButton tile =(JButton) e.getSource();
                         if(tile.getText() == ""){
                            tile.setText(CurrentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                            if(CurrentPlayer == playerX){
                                CurrentPlayer = playerY;
                            }
                            else
                            {
                                CurrentPlayer = playerX;
                            }
                            textLabel.setText(CurrentPlayer+" turn");
                            }
                         }
                         
                     }
                 });
             }
         }
         
         JButton resetButton = new JButton("Restart");
         resetButton.setFont(new Font("Arial",Font.BOLD,25));
         resetButton.setFocusable(false);
         frame.add(resetButton, BorderLayout.SOUTH);
         
         resetButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 for(int x=0;x<3;x++){
                     for(int y=0;y<3;y++){
                         board[x][y].setText("");
                         board[x][y].setBackground(Color.black);
                         board[x][y].setForeground(Color.white);
                     }
                 }
                 
                 CurrentPlayer = playerX;
                 gameOver = false;
                 turns = 0;
                 textLabel.setText("Tic Tac Toe");
             }
         });
         
     }
     void checkWinner(){
         for(int i=0;i<3;i++){
             if(board[i][0].getText()== "") continue;
           
             if(board[i][0].getText() == board[i][1].getText() && board[i][1].getText() == board[i][2].getText()){
                 for(int j=0;j<3;j++){
                 setWinner(board[i][j]);
                }
                 gameOver = true;
                 return;
             }
         }
         
         for(int k=0;k<3;k++){
            if(board[0][k].getText()== "") continue;
            
            if(board[0][k].getText() == board[1][k].getText() && board[1][k].getText() == board[2][k].getText()){
                 for(int l=0;l<3;l++){
                 setWinner(board[l][k]);
                }
                 gameOver = true;
                 return;
             }
         }
         
         if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
             for(int m=0;m<3;m++){
                setWinner(board[m][m]);
             }
             gameOver = true;
             return;
         }
         
         if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
             setWinner(board[0][2]);
             setWinner(board[1][1]);
             setWinner(board[2][0]);
             gameOver = true;
             return;
         }
         
         if(turns == 9){
             for(int a=0;a<3;a++){
                 for(int b=0;b<3;b++){
                     setTie(board[a][b]);
                 }
             }
             gameOver = true;
         }
     }
     
     
     void setWinner(JButton tile){
         tile.setBackground(Color.black);
         tile.setForeground(Color.green);
         textLabel.setText("The Winner is : "+CurrentPlayer);
     }
     
     void setTie(JButton tile){
         tile.setBackground(Color.black);
         tile.setForeground(Color.red);
         textLabel.setText("The match is tie!");
     }
}
