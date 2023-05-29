package org.uniritter.jogamp;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JOGLTriangle implements GLEventListener, MouseListener {
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private boolean clicked = false;
    private int countClick=0;
    private float distanceX = 0.0f;
    private float distanceY = 0.0f;
    public static void main(String[] args) {
        //
        JFrame frame = new JFrame("Treco JOGL Triangulo");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new JOGLTriangle());
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

    public void init(GLAutoDrawable drawable) {
        // Initialize OpenGL
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Set the viewport
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, width, 0, height, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        // Render code OpenGL
        GL2 gl = drawable.getGL().getGL2();

        // Define a matriz de transformação de escala
        float scaleFactor = 2.0f;
        float[] scaleMatrix = {
                scaleFactor, 0.0f,        0.0f,        0.0f,
                0.0f,        scaleFactor, 0.0f,        0.0f,
                0.0f,        0.0f,        scaleFactor, 0.0f,
                0.0f,        0.0f,        0.0f,        1.0f
        };
        gl.glPushMatrix();
        gl.glMultMatrixf(scaleMatrix, 0);

        // Desenha o triângulo
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(100.0f, 100.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(200.0f, 100.0f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(150.0f, 200.0f);
        gl.glEnd();

        gl.glPopMatrix();
        clicked=false;
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void mouseClicked(MouseEvent event){
        countClick++;
        clicked=true;
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

