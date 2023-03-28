package org.uniritter.jogamp;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

public class JOGLTriangle extends JFrame implements GLEventListener {

    private static GLCanvas canvas;

    public JOGLTriangle() {
        // Create OpenGL context
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        // Register event listeners
        canvas.addGLEventListener(this);

        // Add canvas to frame
        getContentPane().add(canvas);
        //setVisible(true);
    }

    public static void main(String[] args) {
        JOGLTriangle triangle = new JOGLTriangle();

        triangle.setSize(640, 480);
        triangle.setDefaultCloseOperation(EXIT_ON_CLOSE);
        triangle.setTitle("Treco JOGL Triangulo");
        triangle.setVisible(true);
    }

    public void init(GLAutoDrawable drawable) {
        // Initialize OpenGL
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Set the viewport
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
    }

    public void display(GLAutoDrawable drawable) {
        // Render code OpenGL
        GL2 gl = drawable.getGL().getGL2();
//        gl.glvertex2f
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
        gl.glBegin(GL.GL_TRIANGLES);
        //gl.glColor3f(1.0f, 0.0f, 0.0f);
        //gl.glVertex3f(0.5f, -0.5f, 0.0f);
        //gl.glColor3f(0.0f, 1.0f, 0.0f);
        //gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        //gl.glColor3f(0.0f, 0.0f, 1.0f);
        //gl.glVertex3f(0.0f, 0.5f, 0.0f);

        gl.glVertex2f(-0.5f, -0.5f);
        gl.glVertex2f(0.5f, -0.5f);
        gl.glVertex2f(0.0f, 0.5f);

        gl.glEnd();
    }

    public void dispose(GLAutoDrawable drawable) {
        // Dispose resources
    }
}

