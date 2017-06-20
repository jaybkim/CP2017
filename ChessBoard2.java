package chess;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
	public void printBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) System.out.print(chessBoardStatus[j][i].type+" ");
                        System.out.println();
		}
	}

	public void printBoardColor(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) System.out.print(chessBoardStatus[j][i].color+" ");
                        System.out.println();
		}
	}

	//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	




//======================================================Implement below=================================================================//		
	enum MagicType {MARK, CHECK, CHECKMATE};
	private int selX, selY;
	private boolean check, checkmate, end;
        private PlayerColor turn;
        private PlayerColor counterpart;
        private boolean selected; // if user choosed a icon to move, selected = true; not yet, selected = false;
        //private arraylist<boolean>[][] possiblemove = new arraylist[8][8];
        //private arraylist<boolean>[][] possibleattack = new arraylist[8][8];
        private boolean[][] PossibleMove = new boolean[8][8];
        private boolean[][] PossibleAttack = new boolean[8][8];

/*        void clearpossiblemove() {
            for (int i=0; i<8; i++)
                for (int j=0; j<8; j++) possiblemove[j][i].add(false);
        }*/

        void clearPossibleAttack() {
            for (int i=0; i<8; i++)
                //for (int j=0; j<8; j++) possibleattack[j][i].add(false);
                for (int j=0; j<8; j++) PossibleAttack[j][i] = false;
        }

        void NextAttackForPawn(Piece status, int x, int y) {
            if (status.color.equals(PlayerColor.black)) {
               /* if (x == 1 && geticon(x+2, y).color.equals(playercolor.none) && geticon(x+1, y).equals(playercolor.none)) { // first move and there should be nothing in front of it to move two steps
                    possibleattack[y][x+2] = true;
                }
                if ((x+1)<8)
                    if (geticon(x+1, y).color.equals(playercolor.none)) {
                        possibleattack[y][x+1] = true;*/
                if ((y-1)>=0 && (x+1)<8) {
                    if (getIcon(x+1, y-1).color.equals(PlayerColor.white))
                        PossibleAttack[y-1][x+1] = true;
                }
                if ((x+1)<8 && (y+1)<8) {
                    if (getIcon(x+1, y+1).color.equals(PlayerColor.white))
                        PossibleAttack[y+1][x+1] = true;
                }
            }
            else { //white
               /* if (x == 6 && geticon(x-2, y).color.equals(playercolor.none)) // first move
                    possibleattack[y][x-2];
                if ((x-1)>=0)
                    if (geticon(x-1, y).color.equals(playercolor.none))
                        possibleattack[y][x-1];*/
                if ((y-1)>=0 && (x-1)>=0) {
                    if (getIcon(x-1, y-1).color.equals(PlayerColor.black))
                        PossibleAttack[y-1][x-1] = true;
                }
                if ((y+1)<8 && (x-1)>=0) {
                    if (getIcon(x-1, y+1).color.equals(PlayerColor.black))
                        PossibleAttack[y+1][x-1] = true;
                }
            }
        }

        void NextAttackForRook(Piece status, int x, int y) {
            for (int i=x-1; i>=0; i--) {  // color the left positions where rook can move
                if (getIcon(i, y).color.equals(turn))
                    break;
                else if (getIcon(i,y).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[y][i] = true;
                    break;
                }
                else
                    PossibleAttack[y][i] = true;
            }

            for (int i=x+1; i<8; i++) {  // color the right position where rook can move
                if (getIcon(i, y).color.equals(turn))
                    break;
                else if (getIcon(i,y).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[y][i] = true;
                    break;
                }
                else
                    PossibleAttack[y][i] = true;
            }

            for (int i=y+1; i<8; i++) {  // color the down position where rook can move
                if (getIcon(x, i).color.equals(turn))
                    break;
                else if (getIcon(x, i).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[i][x] = true;
                    break;
                }
                else
                    PossibleAttack[i][x] = true;
            }

            for (int i=y-1; i>=0; i--) {  // color the upper position where rook can move
                if (getIcon(x, i).color.equals(turn))
                    break;
                else if (getIcon(x, i).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[i][x] = true;
                    break;
                }
                else
                    PossibleAttack[i][x] = true;
            }
        }

        void NextAttackForKnight(Piece status, int x, int y) {
            if ((x+1<8) && (y+2)<8 && !getIcon(x+1, y+2).color.equals(turn)) PossibleAttack[y+2][x+1] = true;
            if ((x+2<8) && (y+1)<8 && !getIcon(x+2, y+1).color.equals(turn)) PossibleAttack[y+1][x+2] = true;
            if ((x+2<8) && (y-1)>=0 && !getIcon(x+2, y-1).color.equals(turn)) PossibleAttack[y-1][x+2] = true;
            if ((x+1<8) && (y-2)>=0 && !getIcon(x+1, y-2).color.equals(turn)) PossibleAttack[y-2][x+1] = true;
            if (((x-1)>=0) && (y-2)>=0 && !getIcon(x-1, y-2).color.equals(turn)) PossibleAttack[y-2][x-1] = true;
            if (((x-2)>=0) && (y-1)>=0 && !getIcon(x-2, y-1).color.equals(turn)) PossibleAttack[y-1][x-2] = true;
            if (((x-2)>=0) && (y+1)<8 && !getIcon(x-2, y+1).color.equals(turn)) PossibleAttack[y+1][x-2] = true;
            if (((x-1)>=0) && (y+2)<8 && !getIcon(x-1, y+2).color.equals(turn)) PossibleAttack[y+2][x-1] = true;
        }

        void NextAttackForBishop(Piece status, int x, int y) {
            int i = 1;
            while ((x+i<8) && (y+i<8)) {
                if (getIcon(x+i, y+i).color.equals(turn))
                    break;
                else if (getIcon(x+i, y+i).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[y+i][x+i] = true;
                    break;
                }
                else
                    PossibleAttack[y+i][x+i] = true;
                i++;
            }
            
            i = 1;
            while ((x-i>=0) && (y+i<8)) {
                if (getIcon(x-i, y+i).color.equals(turn))
                    break;
                else if (getIcon(x-i, y+i).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[y+i][x-i] = true;
                    break;
                }
                else
                    PossibleAttack[y+i][x-i] = true;
                i++;
            }

            i = 1;
            while ((x-i>=0) && (y-i>=0)) {
                if (getIcon(x-i, y-i).color.equals(turn))
                    break;
                else if (getIcon(x-i, y-i).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[y-i][x-i] = true;
                    break;
                }
                else
                    PossibleAttack[y-i][x-i] = true;
                i++;
            }

            i = 1;
            while ((x+i<8) && (y-i>=0)) {
                if (getIcon(x+i, y-i).color.equals(turn))
                    break;
                else if (getIcon(x+i, y-i).color.equals(counterpart)) { // can capture enemy
                    PossibleAttack[y-i][x+i] = true;
                    break;
                }
                else
                    PossibleAttack[y-i][x+i] = true;
                i++;
            }
        }

        void NextAttackForKing(Piece status, int x, int y) {
            if ((x+1<8) && (y+1)<8 && !getIcon(x+1, y+1).color.equals(turn)) PossibleAttack[y+1][x+1] = true;
            if ((x+1<8) && !getIcon(x+1, y).color.equals(turn)) PossibleAttack[y][x+1] = true;
            if ((x+1<8) && (y-1)>=0 && !getIcon(x+1, y-1).color.equals(turn)) PossibleAttack[y-1][x+1] = true;
            if ((y-1)>=0 && !getIcon(x, y-1).color.equals(turn)) PossibleAttack[y-1][x] = true;
            if (((x-1)>=0) && ((y-1)>=0) && !getIcon(x-1, y-1).color.equals(turn)) PossibleAttack[y-1][x-1] = true;
            if (((x-1)>=0) && !getIcon(x-1, y).color.equals(turn)) PossibleAttack[y][x-1] = true;
            if (((x-1)>=0) && (y+1)<8 && !getIcon(x-1, y+1).color.equals(turn)) PossibleAttack[y+1][x-1] = true;
            if (((y+1)<8) && !getIcon(x, y+1).color.equals(turn)) PossibleAttack[y+1][x] = true;
        }

        void NextAttackForQueen(Piece status, int x, int y) {
            NextAttackForRook(status, x, y);
            NextAttackForBishop(status, x, y);
            NextAttackForKing(status, x, y);
        }

        void NextAttack(Piece status, int x, int y) { // we have to call clearnextattack() before calling this
            if (status.type.equals(PieceType.pawn)) {
                NextAttackForPawn(status, x, y);
            }
            else if (status.type.equals(PieceType.rook)) {
                NextAttackForRook(status, x, y);
            } 

            else if (status.type.equals(PieceType.knight)) {
                NextAttackForKnight(status, x, y);
            }
            else if (status.type.equals(PieceType.bishop)) {
                NextAttackForBishop(status, x, y);
            }
            else if (status.type.equals(PieceType.queen)) {
                NextAttackForQueen(status, x, y);
            }
            else if (status.type.equals(PieceType.king)) {
                NextAttackForKing(status, x, y);
            }
        }

        void NextMove(Piece status, int x, int y) { // we have to call clearnextattack() before calling this function since we assign nextattack to  nextmove
            NextAttack(status, x, y);
            PossibleMove = PossibleAttack;
            if (status.type.equals(PieceType.pawn)) { // special case of pawn : pawn has different nextmove and nextattack
                if (status.color.equals(PlayerColor.black)) {
                    if (x == 1 && getIcon(x+2, y).color.equals(PlayerColor.none) && getIcon(x+1, y).color.equals(PlayerColor.none))  // first move and there should be nothing in front of it to move two steps
                        PossibleMove[y][x+2] = true;
                    if ((x+1)<8)
                        if (getIcon(x+1, y).color.equals(PlayerColor.none))
                            PossibleMove[y][x+1] = true;
                }
                else { //white
                    if (x == 6 && getIcon(x-2, y).color.equals(PlayerColor.none) && getIcon(x-1, y).color.equals(PlayerColor.none)) // first move
                        PossibleMove[y][x-2] = true;
                    if ((x-1)>=0)
                        if (getIcon(x-1, y).color.equals(PlayerColor.none))
                            PossibleMove[y][x-1] = true;
                }
            }
        }

        void ColorWay(Piece status, int x, int y) {
            clearPossibleAttack();
            NextMove(status, x, y);
            System.out.println("-----------------------" + status.type);
            for (int i=0; i<8; i++)
                for (int j=0; j<8; j++)
                    if (PossibleMove[j][i] == true) markPosition(i, j);
        }
	
	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
                

		public void actionPerformed(ActionEvent e) {	// only modify here
			// (x, y) is where the click event occured
                    ChessBoard.this.printBoardStatus();
                    ChessBoard.this.printBoardColor();
                    Piece status = getIcon(x, y); 
                    if (selected == false) {
                        if (status.color.equals(turn)) {
                            selected = true;
                            selX = x;
                            selY = y;
                            ColorWay(status, x, y);
                        }
                    }

                    else { // if user choosed one to move(the way where can move is marked on the board)
                        if (chessBoardSquares[y][x].getBackground().equals(Color.pink)) {
                            //system.out.println("selected: ("+selx+","+sely+")");
                            //system.out.println("clicked: ("+x+","+y+")");
                            ChessBoard.this.move(selX, selY, x, y);
                            //system.out.println("seticon: ("+x+","+y+","+status.type+")");
                            ChessBoard.this.setIcon(x, y, status);
                            ChessBoard.this.setIcon(selX, selY, getIcon(selX,selY));
                            selected = false;
                            PlayerColor tmp;
                            tmp = counterpart;
                            counterpart = turn;
                            turn = tmp;
                            String s = new String(turn + "'" + "s turn");
                            if (isCheckMate())
                            	s = s + "| CheckMate";
                            else if (isCheck())
                            	s = s + "| Check";
                            setStatus(s);
                            //System.out.println("next turn: " + turn);
                        }
                        for(int i=0;i<8;i++){
                                for(int j=0;j<8;j++) unmarkPosition(i, j);
                        }
                        if (chessBoardStatus[y][x].color.equals(turn)) {
                            selX = x;
                            selY = y;
                            ColorWay(status, x, y);
                        }
                    }
		}
	}

        void move(int startX, int startY, int dstX, int dstY) {
            getIcon(dstX, dstY).type = getIcon(startX, startY).type;
            getIcon(dstX, dstY).color = getIcon(startX, startY).color;
            getIcon(startX, startY).type = PieceType.none;
            getIcon(startX, startY).color = PlayerColor.none;
        }
        
        /*boolean isCheck(Piece status, int x, int y) { // if you don't escape the king will be captured
            ClearPossibleAttack();
            NextMove(getIcon(x, y), x, y);
                for(int i=0;i<8;i++) 
                    for(int j=0;j<8;j++) {
                        if (PossibleMove[j][i].equals(true))
                            move(x, y, i, j);
                            isCheck(getIcon
           ClearPossibleAttack();
            NextAttack(status, x, y);
                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++) 
                        if (PossibleAttack[j][i] == true && getIcon(i, j).type.equals(PieceType.king) && getIcon(i, j).color.equals(counterpart)) {
                            check = true;
                            return true;
                        }
                }
            check = false;
            return false;
        } */
        
        boolean canCaptureKing(int x, int y, PlayerColor turn) {
            clearPossibleAttack();
            NextMove(getIcon(x, y), x, y);
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    if (PossibleMove[j][i] == true && getIcon(i, j).type.equals(PieceType.king) /*&& getIcon(i, j).color.equals(turn)*/) {
                    	System.out.println("!!!!!!!!!!!!!!!1" + getIcon(i, j).color);
                        return true;
                    }
                }
            }
            return false;
        }

        boolean isCheck() { // if you don't escape the king will be captured
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    if (getIcon(i, j).color.equals(counterpart)){
                        if (canCaptureKing(i, j, turn) == true) {
                            check = true;
                            return true;
                        }
                    }
                }
            }
            check = false;
            return false;
        }
        
        boolean StillChecked(int x, int y) { // move to anywhere that can go and check whether still checked
            clearPossibleAttack();
            NextMove(getIcon(x, y), x, y);
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    if (PossibleMove[j][i] == true) {
                        Piece tmp = getIcon(i, j);
                        move(x, y, i, j);
                        if (isCheck() == false) {
                            undo(x, y, i, j, tmp);
                            return false;
                        }
                        undo(x, y, i, j, tmp);
                    }
                }
            }
            return true;
        }
        
        void undo(int x, int y, int i, int j, Piece tmp) { // getICon(i, j) was overwritten by move(x, y, i, j)
            getIcon(x, y).type = getIcon(i, j).type;
            getIcon(x, y).color = getIcon(i, j).color;
            getIcon(i, j).type = tmp.type;
            getIcon(i, j).color = tmp.color;
        }
            
        boolean isCheckMate() {
            if (isCheck() == false)
                return false;
            else {
                for(int i=0;i<8;i++) {
                    for(int j=0;j<8;j++) {
                        if (getIcon(i, j).color.equals(turn))  // move all my PieceTypes
                            if (StillChecked(i, j) == false)
                                return false;
                    }
                }
                return true;
            }
        }
	
	void onInitiateBoard(){
            turn = PlayerColor.black; // black starts first
            counterpart = PlayerColor.white;
            check = false;
            checkmate = false;
            setStatus(turn + "'s turn");
	}
}
