package org.uniritter.jogamp;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import javax.swing.*;

public class ExemploAula  extends JFrame implements GLEventListener {
    public static void main(String[] args){
        //glutInit();
        ExemploAula triangle = new ExemploAula();
    }
    public void init(GLAutoDrawable drawable) {
        // Initialize OpenGL
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
    //void DesenhaCena(){
    //
    //    // Limpa a janela com a cor especificada como cor de fundo -> ver Inicio()
    //    glClear(GL_COLOR_BUFFER_BIT);
    //
    //    //glColor3f(1.0, 0.0, 0.0); //vermelho
    //    glColor3f(1.0, 1.0, 0.0); //amarelo
    //    glBegin(GL_TRIANGLES);
    //        glVertex2f(-0.5, -0.5);
    //        glVertex2f(0.5, -0.5);
    //        glVertex2f(0.0, 0.5);
    //    glEnd();
    //
    //    glutSwapBuffers();
    //}
    //
    //// Inicializa aspectos do rendering
    //void Inicio(){
    //    //glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // define a cor de fundo da janela R,G,B,alpha
    //    glClearColor(0.0f, 1.0f, 0.0f, 0.0f);
    //}
    //
    //int main(int argc, char **argv){
    //
    //    glutInit(&argc, argv); //função que inicia a GLUT
    //
    //    // Indica que deve ser usado um unico buffer para armazenamento da imagem e representacao de cores RGB
    //    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    //
    //    glViewport(0, 0, 500, 500);
    //    /*
    //    void glViewport(GLint x, GLint y, GLint larg, GLint alt)
    //    Define viewport no SRT.
    //    Os dois primeiros valores (x,y) são o canto inferior esquerdo seguido da largura e altura.
    //    */
    //    glutCreateWindow("Programa OpenGL"); // Cria uma janela com o titulo especificado
    //
    //    // Especifica para a OpenGL que funcao deve ser chamada para geracao da imagem
    //	glutDisplayFunc(DesenhaCena);
    //
    //    // Executa a inicializacao de parametros de exibicao
    //	Inicio();
    //
    //    // Dispara a "maquina de estados" de OpenGL
    //	glutMainLoop(); //inicia o gerenciamento dos eventos
    //
    //	return 0;
    //}
}
