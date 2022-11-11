package ML;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.zip.Adler32;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



import UI.ProgressBar;
import UI.Ui;
public class Run {
	private static final String MODEL_URL = "https://dl.dropboxusercontent.com/s/djmh91tk1bca4hz/RunEpoch_class_2_soft_10_32_1800.zip?dl=0";

    public static void main(String[] args) throws Exception {
        downloadModelForFirstTime();

        JFrame mainFrame = new JFrame();
        ProgressBar progressBar = new ProgressBar(mainFrame, true);
        progressBar.showProgressBar("Loading model, this make take several seconds!");
        Ui ui = new Ui();
        Executors.newCachedThreadPool().submit(() -> {
            try {
                ui.initUI();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                progressBar.setVisible(false);
                mainFrame.dispose();
            }
        });

    }

    private static void downloadModelForFirstTime() throws IOException {
        JFrame mainFrame = new JFrame();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        ProgressBar progressBar = new ProgressBar(mainFrame, false);
        File model = new File("resources/model.zip");
        //|| FileUtils.checksum(model, new Adler32()).getValue() != 3082129141l
        if (!model.exists() ) {
            model.delete();
            progressBar.showProgressBar("Downloading model for the first time 500MB!");
            URL modelURL = new URL(MODEL_URL);

			/*
			 * try { //FileUtils.copyURLToFile(modelURL, model); } catch (IOException e) {
			 * JOptionPane.showMessageDialog(null, "Failed to download model"); throw new
			 * RuntimeException(e); } finally { progressBar.setVisible(false);
			 * mainFrame.dispose(); }
			 */

        }
    }
}
