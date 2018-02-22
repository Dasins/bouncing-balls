import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Simula un numero indicado por el usuario de bolas saltarinas.
     */
    public void bounce(int numBolas) {
        // Posicion de la linea del suelo
        int suelo = 400;
        
        myCanvas.setVisible(true);
        myCanvas.drawLine(50,suelo,550,suelo);
        
        ArrayList<BouncingBall> bolas = new ArrayList<>();
        // Crea tantas bolas aleatorias como se indique por parametro.
        for(int i = 0; i < numBolas; i++){  
            bolas.add(bolaAleatoria(suelo));
        }
        
        boolean finalizado =  false;
        while(!finalizado) {
            // Pequeno retraso.
            myCanvas.wait(50);
            for(BouncingBall bola : bolas) {
                bola.move();
            }
            int i = 0;
            // Detiene la bola tras moverse lo suficiente en el eje x.
            while(!finalizado && i < bolas.size()) {
                if(bolas.get(i).getXPosition() >= 550) {
                    finalizado = true;
                }
                i++;
            }
        }
    }
    
    /**
     * Devuelve una bola aleatoria.
     * @return Devuelve una bola aleatoria.
     */
    private BouncingBall bolaAleatoria(int suelo) {
        Random aleatorio = new Random();
        int diametro = aleatorio.nextInt(41) + 10;
        Color color = new Color(aleatorio.nextInt(256),aleatorio.nextInt(256),aleatorio.nextInt(256));
        return new BouncingBall(50, 50, diametro, color, suelo, myCanvas);
    }
}
