//Importações
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//Herdando a classe JPanel
public class PingPong extends JPanel implements ActionListener, KeyListener{
    
    //Pontuação
    static int score = 0;
    
    //Ball movimentation
    int UP = 0;
    int DOWN = 1;
    int RIGHT = 3;
    int LEFT = 4;
    int DIRECTION_VERT = DOWN;
    int DIRECTION_HORI = LEFT;
    
    static int ballX = 100;
    static int ballY = 100;
    
    //Movimentação do jogador
    int DIRECTION_PLAYER = DOWN; 
    static int playerX = 40;
    static int playerY = 200;
    
    //Movimentação do inimigo
    int DIRECTION_ENEMY = DOWN;
    static int enemyX = 530;
    static int enemyY = 200;
    
    //movimentação Jogador
    void playerMoviment() {
        //Colisão
        if (playerY <= 0)
        {
            DIRECTION_PLAYER = DOWN;
        } else if ((playerY+70) >= getHeight()) {
            DIRECTION_PLAYER = UP;
        }
        
        if(this.DIRECTION_PLAYER == UP)
        {
            playerY -= 5;
        } else {
            playerY += 5;
        }
    }
    
    void enemyMoviment() {

        //Colisão
        if (enemyY <= 0)
        {
            DIRECTION_ENEMY = DOWN;
        } else if ((enemyY+70) >= getHeight()) {
            DIRECTION_ENEMY = UP;
        }
        
        if(this.DIRECTION_ENEMY == UP)
        {
            enemyY -= 5;
        } else {
            enemyY += 5;
        }
    }
    
    void collisionPlayer(int x, int y) {
        if(ballX <= x && ballY >= y && ballY <= (y+70))
        {
            //Mudando a direção da bola
            if (this.DIRECTION_VERT == this.UP && this.DIRECTION_HORI == RIGHT) {
                   DIRECTION_VERT = DOWN;
                   DIRECTION_HORI = LEFT;
            } else if(this.DIRECTION_VERT == this.UP && this.DIRECTION_HORI == LEFT) {
                DIRECTION_VERT = DOWN;
                DIRECTION_HORI = RIGHT;
            } else if(this.DIRECTION_VERT == this.DOWN && this.DIRECTION_HORI == RIGHT){
                DIRECTION_VERT = UP;
                DIRECTION_HORI = LEFT;
            } else {
                DIRECTION_VERT = UP;
                DIRECTION_HORI = RIGHT;
            }
        }
        
    }
    
    void collisionEnemy(int x, int y) {
        if(ballX >= x && ballY >= y && ballY <= (y+70))
        {
            //Mudando a direção da bola
            if (this.DIRECTION_VERT == this.UP && this.DIRECTION_HORI == RIGHT) {
                   DIRECTION_VERT = DOWN;
                   DIRECTION_HORI = LEFT;
            } else if(this.DIRECTION_VERT == this.UP && this.DIRECTION_HORI == LEFT) {
                DIRECTION_VERT = DOWN;
                DIRECTION_HORI = RIGHT;
            } else if(this.DIRECTION_VERT == this.DOWN && this.DIRECTION_HORI == RIGHT){
                DIRECTION_VERT = UP;
                DIRECTION_HORI = LEFT;
            } else {
                DIRECTION_VERT = UP;
                DIRECTION_HORI = RIGHT;
            }
        }
        
    }
    
    //Função para definir a movimentação da bola
    void ballMovimetation()
    {
        //colisao da bola com a parede
        if((ballX + 25) >= getWidth()) {
            this.DIRECTION_HORI = LEFT;
            //Se bater na parede do adiversário aumenta o ponto
            score += 1;
        }
        else if((ballX ) <= 0){
            this.DIRECTION_HORI = RIGHT;
            //Se bater na parede do adiversário diminui o ponto
            score -= 1;
        }
        else if((ballY + 25) >= getHeight())
        {
            this.DIRECTION_VERT = UP;
        }
        else if((ballY ) <= 0){
            this.DIRECTION_VERT = DOWN;
        }
        
        //Mudando a direção da bola
        if (this.DIRECTION_VERT == this.UP && this.DIRECTION_HORI == RIGHT) {
            ballX += 5;
            ballY -= 5;
        } else if(this.DIRECTION_VERT == this.UP && this.DIRECTION_HORI == LEFT) {
            ballX -= 5;
            ballY -= 5;
        } else if(this.DIRECTION_VERT == this.DOWN && this.DIRECTION_HORI == RIGHT){
            ballX += 5;
            ballY += 5;
        } else {
            ballX -= 5;
            ballY +=5 ;
        }

    }
    
    
    //Construtor
    public PingPong()
    {
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        Timer clock = new Timer(10, this);
        clock.start();
    }
    
    //Sobreescrevendo o método paintComponent
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        g.setColor(Color.magenta);
        g.fillRect(playerX, playerY, 20, 70);
        
        g.setColor(Color.RED);
        g.fillRect(enemyX, enemyY, 20, 70);
        
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 25, 25);
        
        g.drawString("Score: " + score, 240, 10);
        
    }
    
    
    
    public static void main(String[] args) {
        //Criando a janela
        JFrame win = new JFrame("Ping Pong");
        //Criando o painel (usando a própria classe porque foi herdado)
        win.add(new PingPong());
        win.setSize(600, 400);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setResizable(false);
        win.setVisible(true);
        
        
    }
    
    //Lendo o teclado
     
    
    //movimentação
    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.ballMovimetation();
        this.playerMoviment();
        this.enemyMoviment();
        this.collisionEnemy(enemyX,enemyY);
        this.collisionPlayer(playerX, playerY);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int Codtecla = e.getKeyCode();
        
        
        if(Codtecla == KeyEvent.VK_W || Codtecla == KeyEvent.VK_UP)
        {
            this.DIRECTION_PLAYER = UP;
        } else if(Codtecla == KeyEvent.VK_S || Codtecla == KeyEvent.VK_DOWN) {
            this.DIRECTION_PLAYER = DOWN;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    
}
