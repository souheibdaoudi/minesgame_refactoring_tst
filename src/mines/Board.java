// 
// Decompiled by Procyon v0.5.36
// 

package mines;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.JPanel;

public class Board extends JPanel
{
    private static final long serialVersionUID = 6195235521361212179L;
    private final int NUM_IMAGES = 13;
    private final int CELL_SIZE = 15;
    private final int COVER_FOR_CELL = 10;
    private final int MARK_FOR_CELL = 10;
    private final int EMPTY_CELL = 0;
    private final int MINE_CELL = 9;
    private final int COVERED_MINE_CELL = 19;
    private final int MARKED_MINE_CELL = 29;
    private final int DRAW_MINE = 9;
    private final int DRAW_COVER = 10;
    private final int DRAW_MARK = 11;
    private final int DRAW_WRONG_MARK = 12;
    private int[] field;
    private boolean inGame;
    private int mines_left;
    private Image[] img;
    private int mines;
    private int rows;
    private int cols;
    private int all_cells;
    private JLabel statusbar;
    
    public Board(final JLabel statusbar) {
        this.mines = 40;
        this.rows = 16;
        this.cols = 16;
        this.statusbar = statusbar;
        this.img = new Image[13];
        for (int i = 0; i < 13; ++i) {
            this.img[i] = new ImageIcon(this.getClass().getClassLoader().getResource(invokedynamic(makeConcatWithConstants:(I)Ljava/lang/String;, i))).getImage();
        }
        this.setDoubleBuffered(true);
        this.addMouseListener((MouseListener)new Board.MinesAdapter(this));
        this.newGame();
    }
    
    public void newGame() {
        final Random random = new Random();
        this.inGame = true;
        this.mines_left = this.mines;
        this.all_cells = this.rows * this.cols;
        this.field = new int[this.all_cells];
        for (int i = 0; i < this.all_cells; ++i) {
            this.field[i] = 10;
        }
        this.statusbar.setText(Integer.toString(this.mines_left));
        int j = 0;
        while (j < this.mines) {
            final int n = (int)(this.all_cells * random.nextDouble());
            if (n < this.all_cells && this.field[n] != 19) {
                final int n2 = n % this.cols;
                this.field[n] = 19;
                ++j;
                if (n2 > 0) {
                    final int n3 = n - 1 - this.cols;
                    if (n3 >= 0 && this.field[n3] != 19) {
                        final int[] field = this.field;
                        final int n4 = n3;
                        ++field[n4];
                    }
                    final int n5 = n - 1;
                    if (n5 >= 0 && this.field[n5] != 19) {
                        final int[] field2 = this.field;
                        final int n6 = n5;
                        ++field2[n6];
                    }
                    final int n7 = n + this.cols - 1;
                    if (n7 < this.all_cells && this.field[n7] != 19) {
                        final int[] field3 = this.field;
                        final int n8 = n7;
                        ++field3[n8];
                    }
                }
                final int n9 = n - this.cols;
                if (n9 >= 0 && this.field[n9] != 19) {
                    final int[] field4 = this.field;
                    final int n10 = n9;
                    ++field4[n10];
                }
                final int n11 = n + this.cols;
                if (n11 < this.all_cells && this.field[n11] != 19) {
                    final int[] field5 = this.field;
                    final int n12 = n11;
                    ++field5[n12];
                }
                if (n2 >= this.cols - 1) {
                    continue;
                }
                final int n13 = n - this.cols + 1;
                if (n13 >= 0 && this.field[n13] != 19) {
                    final int[] field6 = this.field;
                    final int n14 = n13;
                    ++field6[n14];
                }
                final int n15 = n + this.cols + 1;
                if (n15 < this.all_cells && this.field[n15] != 19) {
                    final int[] field7 = this.field;
                    final int n16 = n15;
                    ++field7[n16];
                }
                final int n17 = n + 1;
                if (n17 >= this.all_cells || this.field[n17] == 19) {
                    continue;
                }
                final int[] field8 = this.field;
                final int n18 = n17;
                ++field8[n18];
            }
        }
    }
    
    public void find_empty_cells(final int n) {
        final int n2 = n % this.cols;
        if (n2 > 0) {
            final int n3 = n - this.cols - 1;
            if (n3 >= 0 && this.field[n3] > 9) {
                final int[] field = this.field;
                final int n4 = n3;
                field[n4] -= 10;
                if (this.field[n3] == 0) {
                    this.find_empty_cells(n3);
                }
            }
            final int n5 = n - 1;
            if (n5 >= 0 && this.field[n5] > 9) {
                final int[] field2 = this.field;
                final int n6 = n5;
                field2[n6] -= 10;
                if (this.field[n5] == 0) {
                    this.find_empty_cells(n5);
                }
            }
            final int n7 = n + this.cols - 1;
            if (n7 < this.all_cells && this.field[n7] > 9) {
                final int[] field3 = this.field;
                final int n8 = n7;
                field3[n8] -= 10;
                if (this.field[n7] == 0) {
                    this.find_empty_cells(n7);
                }
            }
        }
        final int n9 = n - this.cols;
        if (n9 >= 0 && this.field[n9] > 9) {
            final int[] field4 = this.field;
            final int n10 = n9;
            field4[n10] -= 10;
            if (this.field[n9] == 0) {
                this.find_empty_cells(n9);
            }
        }
        final int n11 = n + this.cols;
        if (n11 < this.all_cells && this.field[n11] > 9) {
            final int[] field5 = this.field;
            final int n12 = n11;
            field5[n12] -= 10;
            if (this.field[n11] == 0) {
                this.find_empty_cells(n11);
            }
        }
        if (n2 < this.cols - 1) {
            final int n13 = n - this.cols + 1;
            if (n13 >= 0 && this.field[n13] > 9) {
                final int[] field6 = this.field;
                final int n14 = n13;
                field6[n14] -= 10;
                if (this.field[n13] == 0) {
                    this.find_empty_cells(n13);
                }
            }
            final int n15 = n + this.cols + 1;
            if (n15 < this.all_cells && this.field[n15] > 9) {
                final int[] field7 = this.field;
                final int n16 = n15;
                field7[n16] -= 10;
                if (this.field[n15] == 0) {
                    this.find_empty_cells(n15);
                }
            }
            final int n17 = n + 1;
            if (n17 < this.all_cells && this.field[n17] > 9) {
                final int[] field8 = this.field;
                final int n18 = n17;
                field8[n18] -= 10;
                if (this.field[n17] == 0) {
                    this.find_empty_cells(n17);
                }
            }
        }
    }
    
    @Override
    public void paint(final Graphics graphics) {
        int n = 0;
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                int n2 = this.field[i * this.cols + j];
                if (this.inGame && n2 == 9) {
                    this.inGame = false;
                }
                if (!this.inGame) {
                    if (n2 == 19) {
                        n2 = 9;
                    }
                    else if (n2 == 29) {
                        n2 = 11;
                    }
                    else if (n2 > 19) {
                        n2 = 12;
                    }
                    else if (n2 > 9) {
                        n2 = 10;
                    }
                }
                else if (n2 > 19) {
                    n2 = 11;
                }
                else if (n2 > 9) {
                    n2 = 10;
                    ++n;
                }
                graphics.drawImage(this.img[n2], j * 15, i * 15, this);
            }
        }
        if (n == 0 && this.inGame) {
            this.inGame = false;
            this.statusbar.setText("Game won");
        }
        else if (!this.inGame) {
            this.statusbar.setText("Game lost");
        }
    }
}
