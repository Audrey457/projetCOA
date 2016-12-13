/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import model.TableauModele;

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
        
        TableauModele tab2 = new TableauModele(tab, 10, 10);
        VueTableau vueTab = new VueTableau(tab2);
        
        setPreferredSize(new Dimension(500, 400));
        getContentPane().add(vueTab, BorderLayout.CENTER);
        
        pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);   
    }
    
}
