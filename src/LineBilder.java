

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class LineBilder {
	public long time;
	private int sign(int x) {
		return (x > 0) ? 1 : (x < 0) ? -1 : 0;
		// возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
	}

	public  void  drawBresenhamLine(int xstart, int ystart, int xend, int yend, Graphics g)

	{	long startTime = System.nanoTime();
		int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
		
		
		dx = xend - xstart;
		dy = yend - ystart;

		incx = sign(dx);

		incy = sign(dy);

		if (dx < 0)
			dx = -dx;
		if (dy < 0)
			dy = -dy;

		if (dx > dy)

		{

			pdx = incx;
			pdy = 0;
			es = dy;
			el = dx;
		} else {
			pdx = 0;
			pdy = incy;
			es = dx;
			el = dy;
		}

		x = xstart;
		y = ystart;
		err = el / 2;
		g.fillRect(x, y, 1, 1);
		for (int t = 0; t < el; t++) {
			err -= es;
			if (err < 0) {
				err += el;
				x += incx;
				y += incy;
			} else {
				x += pdx;
				y += pdy;
			}

			g.fillRect(x, y, 1, 1);
		}
		long timeSpent = System.nanoTime() - startTime;
		time=timeSpent;
		
	}

	public void DDALine(int xstart, int ystart, int xend, int yend, Graphics g) {
		float x,y,dx,dy,steps,incrx,incry;
		int i;
		long startTime = System.nanoTime();
		dx = Math.abs(xend-xstart);
		dy = Math.abs(yend-ystart);

		if(dx>=dy)
		steps=dx;
		else
		steps=dy;

		incrx=dx/steps;
		incry=dy/steps;

		x=xstart;
		y=ystart;

		i=1;

		    while(i<=steps) {
		    	
		          g.fillRect(Math.round(x), Math.round(y),1, 1);
		          x=x+incrx;
		          y=y+incry;
		          i=i+1;
		       
		    }
		    long timeSpent = System.nanoTime() - startTime;
			time=timeSpent;
	}
	
	public void drawXiaolinLine(int x1, int y1, int x2, int y2, Graphics g) {
		long startTime = System.nanoTime();
		if (x2 < x1) {
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        if (dx == 0 || dy == 0) {
            
            g.fillRect(x1, y1, 1, 1);
            return;
        }
        float gradient = 0;
        if (dx > dy) {
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            
            g.fillRect(x1, y1, 1, 1);
            for (int x = x1; x < x2; ++x) {
                g.setColor(new Color(0, 0, 0, (int) (255 - fractionalPart(intery) * 255))); //Меняем прозрачность
                g.fillRect(x, (int)intery, 1, 1);
                g.setColor(new Color(0, 0, 0, (int) (fractionalPart(intery) * 255)));
                g.fillRect(x, (int)intery + 1, 1, 1);
                intery += gradient;
            }
            
            g.fillRect(x2, y2, 1, 1);
        } 
        else {
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            
            g.fillRect(x1, y1, 1, 1);
            for (int y = y1; y < y2; ++y) {
                g.setColor(new Color(0, 0, 0, (int) (255 - fractionalPart(interx) * 255)));
                g.fillRect((int)interx, y, 1,1);
                g.setColor(new Color(0, 0, 0, (int) (fractionalPart(interx) * 255)));
                g.fillRect((int)interx + 1, y, 1, 1);
                interx += gradient;
            }
            
            g.fillRect(x2, y2,1, 1);
        }
        long timeSpent = System.nanoTime() - startTime;
		time=timeSpent;
    }
 
    private float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }

    public void Dr_Circle(int center_x, int center_y, int radius, Graphics g){
    	long startTime = System.nanoTime();
    	int x = 0, y = radius, sigma = 0, delta = 2 - 2 * radius;
        while (y >= 0){
            //считаем для одной четверти, и симметрично заполняем остальные
            g.fillRect(center_x + x, center_y - y,1, 1);     // 1 четверть
            g.fillRect(center_x - x, center_y - y, 1,1);     // 2 четверть
            g.fillRect(center_x - x, center_y + y, 1,1);     // 3 четверть
            g.fillRect(center_x + x, center_y + y, 1,1);     // 4 четверть
            sigma = 2 * (delta + y) - 1;
            if (delta < 0 && sigma <= 0) {          //перемещение по горизонтали
                x++;
                delta += x + 1;
            } else if (delta > 0 && sigma > 0) {    //перемещение по вертикали
                y--;
                delta -= y + 1;
            } else {                                //перемещение по диагонали
                x++;
                delta += x - y;
                y--;
            }
        }
        long timeSpent = System.nanoTime() - startTime;
		time=timeSpent;
    }    
    
	public void Circle(int x, int y, int r, Graphics g) {
		long startTime = System.nanoTime();
		int p = 0, q = r;
		int d = 3 - 2 * r;

		while (p <=q ) {
			g.fillRect(x + p, y + q, 1, 1);
			g.fillRect(x - p, y + q, 1, 1);
			g.fillRect(x + p, y - q, 1, 1);
			g.fillRect(x - p, y - q, 1, 1);
			g.fillRect(x + q, y + p, 1, 1);
			g.fillRect(x - q, y + p, 1, 1);
			g.fillRect(x + q, y - p, 1, 1);
			g.fillRect(x - q, y - p, 1, 1);
			p++;
			if (d < 0) {
				d = d + 4 * p + 6;

			} else {
				q = q - 1;
				d = d + 4 * (p - q) + 10;
			}
		}

		long timeSpent = System.nanoTime() - startTime;
		time = timeSpent;
	}
	
	Stack [] stack=new Stack[1000000];
	
/*	public void FillOne(int x ,int y,Graphics g,Color oldColor , Color newColor) {
		if (oldColor == newColor)
			return;
		int stackPtr = 0;
	//	stack[stackPtr++] = new Stack(x, y);
		do
		{
			stackPtr --;
			Stack elem= stack[stackPtr];
			if ((elem.x <0 )|| (elem.x >600 ) || (elem.y<0) || (elem.y>600)) {
				continue;
			}
		if(g.()==oldColor) {
			g.(newColor, elem.x, elem.y);
			
			
		}
		
			
		} while(stackPtr > 0);
		
		
	}*/
    
}
