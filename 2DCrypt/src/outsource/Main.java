/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package outsource;

/**
 *
 * @author admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CloudFrame cf=new CloudFrame();
        cf.setVisible(true);
        cf.setResizable(false);
        cf.setTitle("Cloud Server");
        
        CloudReceiver cr=new CloudReceiver(cf);
        cr.start();
        
        OutsourceFrame sf=new OutsourceFrame();
        sf.setVisible(true);
        sf.setTitle("Image OutSourcer");
        sf.setResizable(false);
        
        UserFrame uf=new UserFrame();
        uf.setVisible(true);
        uf.setTitle("Image User");
        uf.setResizable(false);
        
        UserReceiver ur=new UserReceiver(uf);
        ur.start();
    }
    
}
