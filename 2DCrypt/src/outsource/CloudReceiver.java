/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package outsource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author admin
 */
public class CloudReceiver extends Thread
{
    CloudFrame cf;
    CloudReceiver(CloudFrame ce)
    {
        cf=ce;
    }
    
    public void run()
    {
        try
        {
            DatagramSocket ds=new DatagramSocket(9000);
            while(true)
            {
                byte data[]=new byte[1000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
                
                if(req[0].equals("EncTiles"))
                {
                    
                    cf.encTile=true;
                    int bksz=Integer.parseInt(req[1]);
                    System.out.println("Comes "+bksz);
                     int m1=0;
                    for(int i=1;i<3;i++)
                    {
                        int m2=0;
                        for(int j=1;j<3;j++)
                        {                      
                                               
                            ImageIcon ic1=new ImageIcon("encrypt//e"+j+"_"+i+".jpg");
                            JLabel lb1=new JLabel();
                            lb1.setIcon(ic1);
                            lb1.setBounds(m1, m2, bksz,bksz);
                            cf.jPanel2.add(lb1);
                            m2=m2+bksz+25;
                        }
                        m1=m1+bksz+25;
                    }
                }// EncTiles
            }
             
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
