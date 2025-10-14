import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel implements KeyListener, ActionListener {

    private final int WIDTH = 400;
    private final int HEIGHT = 600;
    private Timer timer;
    private int auto1Y, auto2Y; // posiciones verticales
    private final int AUTO_SIZE = 40;
    private boolean ganador = false;

    public Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        addKeyListener(this);

        auto1Y = HEIGHT - AUTO_SIZE - 10;
        auto2Y = HEIGHT - AUTO_SIZE - 10;

        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar pista
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(80, 0, 120, HEIGHT); // carril 1
        g.fillRect(220, 0, 120, HEIGHT); // carril 2

        // Dibujar meta
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, 10);

        // Dibujar autos
        g.setColor(Color.RED);
        g.fillRect(120, auto1Y, AUTO_SIZE, AUTO_SIZE);
        g.setColor(Color.BLUE);
        g.fillRect(260, auto2Y, AUTO_SIZE, AUTO_SIZE);

        // Mostrar ganador
        if (ganador) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("¡Juego Terminado!", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!ganador) {
            // Verificar si alguien llegó a la meta
            if (auto1Y <= 10) {
                ganador = true;
                System.out.println("Jugador 1 gana la carrera!");
            } else if (auto2Y <= 10) {
                ganador = true;
                System.out.println("Jugador 2 gana la carrera!");
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!ganador) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> auto1Y -= 10; // Jugador 1
                case KeyEvent.VK_S -> auto1Y += 10;
                case KeyEvent.VK_UP -> auto2Y -= 10; // Jugador 2
                case KeyEvent.VK_DOWN -> auto2Y += 10;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Carreras para Dos Jugadores");
        Main juego = new Main();
        ventana.add(juego);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setVisible(true);
    }
}
