package jswingshell.jssframe;

import java.awt.Image;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import jswingshell.jssframe.resources.ResourceUtils;

/**
 *
 * @author Mathieu Brunot
 */
public class JSwingShellFrameDemo {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(JSwingShellFrameDemo.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JssFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // #####################################################################
        // Let's create our GUI
        final JssFrame appFrame = new JssFrame("JSwingShell");

        List<Image> frameIcons = ResourceUtils.createImages("application_xp_terminal.png", "JSwingShell");
        if (frameIcons != null) {
            appFrame.setIconImages(frameIcons);
        }

        ResourceUtils.addLocaleChangeListener(appFrame);

        // #####################################################################
        // Display the form
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                appFrame.setVisible(true);
            }
        });
    }

}
