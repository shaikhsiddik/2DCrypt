/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package outsource;
import java.awt.image.BufferedImage;
import java.awt.MediaTracker;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.ArrayList;
import java.awt.image.ShortLookupTable;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Frame;
/**
 *
 * @author admin
 */
public class Encryption 
{
    long seed = 1234567890;
    
    MediaTracker tracker;
    Encryption()
    {
        
    }
    public BufferedImage processImg(BufferedImage theImage)
    {
        Random randomGenerator = new Random(seed);
        ArrayList <Short>redList = new ArrayList<Short>(256);
        ArrayList <Short>greenList = new ArrayList<Short>(256);
        ArrayList <Short>blueList = new ArrayList<Short>(256);
    
        for(int cnt = 0;cnt < 256;cnt++)
        {
            short value = (short)(randomGenerator.nextInt() & 0xFF);
            while(redList.contains(value))
            {
                
                value = (short)(randomGenerator.nextInt() & 0xFF);
            }
                redList.add(value);
      
      
            value = (short)(randomGenerator.nextInt() & 0xFF);
            while(greenList.contains(value))
            {        
                value = (short)(randomGenerator.nextInt() & 0xFF);
            }
            greenList.add(value);
      
      
            value = (short)(randomGenerator.nextInt() & 0xFF);
            while(blueList.contains(value))
            {        
                value = (short)(randomGenerator.nextInt() & 0xFF);
            }
            blueList.add(value);
      
        }
    
    
        short[] red = new short[256];
        short[] green = new short[256];
        short[] blue = new short[256];
    
        for (int cnt = 0; cnt < 256; cnt++)
        {
            red[cnt] = redList.get(cnt);
            green[cnt] = greenList.get(cnt);
            blue[cnt] = blueList.get(cnt);
        }

    
        short[][] lookupData = new short[][]{red,green,blue};
    
    
        ShortLookupTable lookupTable = new ShortLookupTable(0,lookupData);

        BufferedImageOp filterObj = new LookupOp(lookupTable,null);       
            
       
        return filterObj.filter(theImage, null);
    }
    
    BufferedImage getImage(String path)
    {
        Image rawImage = Toolkit.getDefaultToolkit().getImage(path);
        tracker = new MediaTracker(new Frame());
        tracker.addImage(rawImage,1);

        try
        {
            if(!tracker.waitForID(1,10000))
            {
                System.out.println("Timeout or Load error.");
                System.exit(1);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        if((tracker.statusAll(false) & MediaTracker.ERRORED & MediaTracker.ABORTED) != 0)
        {
            System.out.println("Load errored or aborted");
            System.exit(1);
        }

        BufferedImage buffImage = new BufferedImage(rawImage.getWidth(null), rawImage.getHeight(null), BufferedImage.TYPE_INT_RGB);        

    
        Graphics g = buffImage.getGraphics();
        g.drawImage(rawImage, 0, 0, null);

        return buffImage;
    }
    
}
