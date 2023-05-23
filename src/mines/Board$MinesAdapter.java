// 
// Decompiled by Procyon v0.5.36
// 

package mines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class MinesAdapter extends MouseAdapter
{
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = x / 15;
        final int n2 = y / 15;
        boolean b = false;
        if (!Board.this.inGame) {
            Board.this.newGame();
            Board.this.repaint();
        }
        if (x < Board.this.cols * 15 && y < Board.this.rows * 15) {
            if (mouseEvent.getButton() == 3) {
                if (Board.this.field[n2 * Board.this.cols + n] > 9) {
                    b = true;
                    if (Board.this.field[n2 * Board.this.cols + n] <= 19) {
                        if (Board.this.mines_left > 0) {
                            final int[] field = Board.this.field;
                            final int n3 = n2 * Board.this.cols + n;
                            field[n3] += 10;
                            final Board this$0 = Board.this;
                            --this$0.mines_left;
                            Board.this.statusbar.setText(Integer.toString(Board.this.mines_left));
                        }
                        else {
                            Board.this.statusbar.setText("No marks left");
                        }
                    }
                    else {
                        final int[] field2 = Board.this.field;
                        final int n4 = n2 * Board.this.cols + n;
                        field2[n4] -= 10;
                        final Board this$2 = Board.this;
                        ++this$2.mines_left;
                        Board.this.statusbar.setText(Integer.toString(Board.this.mines_left));
                    }
                }
            }
            else {
                if (Board.this.field[n2 * Board.this.cols + n] > 19) {
                    return;
                }
                if (Board.this.field[n2 * Board.this.cols + n] > 9 && Board.this.field[n2 * Board.this.cols + n] < 29) {
                    final int[] field3 = Board.this.field;
                    final int n5 = n2 * Board.this.cols + n;
                    field3[n5] -= 10;
                    b = true;
                    if (Board.this.field[n2 * Board.this.cols + n] == 9) {
                        Board.this.inGame = false;
                    }
                    if (Board.this.field[n2 * Board.this.cols + n] == 0) {
                        Board.this.find_empty_cells(n2 * Board.this.cols + n);
                    }
                }
            }
            if (b) {
                Board.this.repaint();
            }
        }
    }
}
