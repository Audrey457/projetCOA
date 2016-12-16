/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import model.SerieChro;

/**
 *
 * @author utilisateur
 */
public class Fenetre extends JFrame{
    
    public Fenetre(){
        super();
        setTitle("logiGest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int[][] tab = new int[10][10];
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++){
                tab[i][j] = i + j;
            }
        }
        ArrayList<String>col1=new ArrayList<String>();
        ArrayList<Double>col2=new ArrayList<Double>();
        col1.add("jan");
        col1.add("fev");
        col1.add("mar");
        col2.add(2.2);

        SerieChro tab2 = new SerieChro("date", "prix", col1, col2);
        VueTableau vueTab = new VueTableau(tab2);
        
        setPreferredSize(new Dimension(500, 400));
        getContentPane().add(vueTab, BorderLayout.CENTER);
        
        pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);   
    }
    
}
