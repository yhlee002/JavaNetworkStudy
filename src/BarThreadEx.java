import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BarThreadEx extends JFrame {
    MyBar bar = new MyBar(100);

    public BarThreadEx(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(null);

        bar.setBackground(Color.YELLOW);
        bar.setOpaque(true);
        bar.setLocation(20, 50);
        bar.setSize(300, 20);
        cp.add(bar);

        // 키 입력받기
        cp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bar.fill();
            }
        });

        setSize(350, 200); // JFrame의 사이즈
        setVisible(true);

        cp.requestFocus(); // 포커스 주기

        EraserThread th = new EraserThread(bar);
        th.start();
    }

    class EraserThread extends Thread {
        MyBar bar;

        public EraserThread(MyBar bar) {
            this.bar = bar;
        }

        public void run() {
            while (true) {
                try {
                    sleep(200);
                    bar.erase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void main(String[] args) {
        new BarThreadEx("아무 키보드나 빨리 눌러보세요.");
    }
}

class MyBar extends JLabel {
    int barSize = 0;
    int maxBarSize;

    public MyBar(int maxBarSize) {
        this.maxBarSize = maxBarSize;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        int width = (int) ((double) this.getWidth()) / maxBarSize * barSize;
        if (width == 0) return;
        g.fillRect(0, 0, width, this.getHeight());

    }

    synchronized public void fill() {
        if (barSize == maxBarSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        barSize++;
        repaint();
        notify(); // eraser 스레드를 깨움
    }

    synchronized public void erase() {
        if (barSize == 0) {
            try {
                wait(); // eraser 스레드를 대기시킴
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        barSize--;
        repaint();
        notify(); // 이벤트 스레드를 깨움
    }
}