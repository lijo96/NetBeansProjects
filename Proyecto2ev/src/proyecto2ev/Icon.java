/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author dam213
 */
public class Icon {

    public static ImageIcon cargarImagen(String path) {
        URL url;
        try {
            url = new URL(path);
            BufferedImage image = ImageIO.read(url);
            return new ImageIcon(image);
        } catch (MalformedURLException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger("").log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
