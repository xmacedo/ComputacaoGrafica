package br.edu.uniritter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class GrayScaleConversionExample {
    public static void main(String[] args) throws IOException {
        // Carrega a biblioteca OpenCV
        nu.pattern.OpenCV.loadLocally();
        System.out.println("Loaded OpenCV version " + Core.VERSION);

        //listar imagens loop
        File file = new File("in");
        File afile[] = file.listFiles();

        for (int a = 0; a < afile.length; a++) {
            File arquivos = afile[a];
            System.out.println("Carregando a imagem de nome: " + arquivos.getName());


            // Carrega a imagem de entrada
            Mat image = Imgcodecs.imread("in/" + arquivos.getName());

            // Verifica se a imagem foi carregada corretamente
            if (image.empty()) {
                System.out.println("Não foi possível carregar a imagem: " + arquivos.getName());
                return;
            }
            // Cria uma nova matriz para a imagem em escala de cinza
            Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8UC1);

            // Converte a imagem para escala de cinza
            Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

            // Exibe as dimensões da imagem
            int largura = image.width();
            int altura = image.height();
            System.out.println("Dimensões da imagem '" + arquivos.getName() + "': " + largura + "x" + altura);

            minimoMaximoPercentuaisEscalaCinza(grayImage, arquivos.getName());

            // Salva a imagem em escala de cinza
            Imgcodecs.imwrite("out/ScaleOfGray_" + arquivos.getName(), grayImage);

            System.out.println("Imagem '" + arquivos.getName() + "' convertida para escala de cinza");
            // fim loop imagens
        }

        //exibirImagem(grayImage);
    }

    private static void minimoMaximoPercentuaisEscalaCinza(Mat grayImage, String nomeImagem) {
        // Inicializa o mínimo valor de cinza com um valor alto inicial
        double minGrayValue = Double.MAX_VALUE;
        double maxGrayValue = Double.MIN_VALUE;

        Map<Integer, Integer> grayLevelCounts = new HashMap<>();

        // Percorre todos os pixels da imagem em escala de cinza
        int largura = grayImage.width();
        int altura = grayImage.height();
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                double grayValue = grayImage.get(y, x)[0];
                grayLevelCounts.put((int) grayValue, grayLevelCounts.getOrDefault((int) grayValue, 0) + 1);
                // Verifica se o valor de cinza é menor que o mínimo atual
                if (grayValue < minGrayValue) {
                    minGrayValue = grayValue;
                }
                // Verifica se o valor de cinza é Maior que o mínimo atual
                if (grayValue > maxGrayValue) {
                    maxGrayValue = grayValue;
                }
            }
        }

        //System.out.println("O mínimo valor de cinza é: " + minGrayValue);
        //System.out.println("O Maximo valor de cinza é: " + maxGrayValue);
        int totalPixels = largura * altura;
        calculaPercentuais(grayLevelCounts, totalPixels);
        calcularHistograma(grayImage, nomeImagem);
    }

    private static void calcularHistograma(Mat grayImage, String nomeImagem) {
        // Calcula o histograma da imagem em escala de cinza
        MatOfInt histSize = new MatOfInt(256); // Tamanho do histograma
        MatOfInt channels = new MatOfInt(0); // Canal de cor (0 para escala de cinza)
        MatOfFloat histRange = new MatOfFloat(0, 256); // Faixa de valores do histograma
        Mat hist = new Mat();
        Imgproc.calcHist(
                Arrays.asList(grayImage),
                channels,
                new Mat(),
                hist,
                histSize,
                histRange
        );

        // Prepara os dados do histograma para o gráfico
        hist.convertTo(hist, CvType.CV_64F);

        // Prepara os dados do histograma para o gráfico
        double[] histData = new double[256];
        hist.get(0, 0, histData);

        // Cria o dataset do histograma
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Histograma", histData, 256);

        // Cria o gráfico do histograma
        JFreeChart chart = ChartFactory.createHistogram(
                "Histograma de Escala de Cinza "+nomeImagem,
                "Valor de Cinza",
                "Frequência",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Personaliza o estilo do gráfico
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        chart.getPlot().setOutlinePaint(Color.BLACK);

        // Cria a janela de exibição do gráfico
        ChartFrame frame = new ChartFrame("Histograma de Escala de Cinza", chart);
        frame.pack();
        frame.setVisible(true);

    }

    private static void calculaPercentuais(Map<Integer, Integer> grayLevelCounts, int totalPixels) {

        Map<Integer, Double> grayLevelPercentages = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : grayLevelCounts.entrySet()) {
            int grayLevel = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / totalPixels * 100.0;
            grayLevelPercentages.put(grayLevel, percentage);
        }

        // Exibe os percentuais de cada valor de cinza
        for (Map.Entry<Integer, Double> entry : grayLevelPercentages.entrySet()) {
            int grayLevel = entry.getKey();
            double percentage = entry.getValue();
            //System.out.println("Valor de cinza: " + grayLevel + ", Percentual: " + percentage + "%");
        }
    }

    private static void exibirImagem(Mat grayImage) throws IOException {
        //Encoding the image
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", grayImage, matOfByte);

        byte[] byteArray = matOfByte.toArray();

        //Preparing the Buffered Image
        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(in);
        //Instantiate JFrame
        JFrame frame = new JFrame();

        //Set Content to the JFrame
        frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
        frame.pack();
        frame.setVisible(true);
    }
}

