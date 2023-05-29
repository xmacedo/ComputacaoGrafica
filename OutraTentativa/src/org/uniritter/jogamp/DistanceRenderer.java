package org.uniritter.jogamp;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;

public class DistanceRenderer implements GLEventListener {

    private static float distanceX = 0.0f;
    private static float distanceY = 0.0f;

    //// Cria um novo objeto DistanceRenderer

    public static void main(String[] args) {
        //
        JFrame frame = new JFrame("Treco JOGL Triangulo");
        GLCanvas canvas = new GLCanvas();
        //canvas.addGLEventListener(new JOGLTriangle());
        DistanceRenderer distanceRenderer = new DistanceRenderer(distanceX, distanceY);
        //
        //// Adiciona o objeto DistanceRenderer à lista de GLEventListeners do GLCanvas
        canvas.addGLEventListener(distanceRenderer);

        frame.getContentPane().add(canvas);
        frame.setSize(400, 400);
        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                animator.stop();
                System.exit(0);
            }
        });
        frame.setVisible(true);
        animator.start();
    }
    public DistanceRenderer(float distanceX, float distanceY) {
        this.distanceX = distanceX;
        this.distanceY = distanceY;
    }
    @Override
    public void init(GLAutoDrawable drawable) {
        // Configura a cor de fundo
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);
    }
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Configura a projeção ortográfica
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, width, 0, height, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // Configura a cor para preto
        gl.glColor3f(0.0f, 0.0f, 0.0f);

        // Configura a fonte para Arial com tamanho 12
        //Font font = new Font("Arial", Font.PLAIN, 12);
        GLUT glut = new GLUT();
        glut.glutBitmapString(2, String.format("Dis X: %.2f", distanceX));
        glut.glutBitmapString(2, String.format("Dis Y: %.2f", distanceY));
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

}
