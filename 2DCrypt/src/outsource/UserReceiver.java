/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package outsource;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author admin
 */
public class UserReceiver extends Thread
{
    UserFrame uf;
    UserReceiver(UserFrame ue)
    {
        uf=ue;
    }
    
    public void run()
    {
        try
        {
            DatagramSocket ds=new DatagramSocket(7000);
            while(true)
            {
                byte data[]=new byte[1000];
                DatagramPacket dp=new DatagramPacket(data,0,data.length);
                ds.receive(dp);
                String str=new String(dp.getData()).trim();
                String req[]=str.split("#");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
