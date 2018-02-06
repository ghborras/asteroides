package fondoJuego;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class PnlFondo extends JPanel{
		
		private static final long serialVersionUID = -5864268171275789898L;
		
		public PnlFondo(){
			this.setSize(800,600);	
		}	
		
		public void paintComponent (Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setComposite(AlphaComposite.SrcOver.derive(0.2f));
			Dimension tamanio = getSize();
			ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/img/background.png"));
			g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	}

