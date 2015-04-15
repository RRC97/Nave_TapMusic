/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majinservice.tapmusic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

/**
 *
 * @author root
 */
public class Shape
{
    
    protected RectF rect;
    protected RectF defaultRect;
    private int type;
    protected int alpha, porcent;
    
    public Shape(float x,float y,float dX,float dY, int t)
    {
        rect = new RectF(x, y, 50, 50);
        defaultRect = new RectF(dX, dY, 50, 50);
        alpha = 100;
        type = t;
    }
    
    public void draw(Canvas c)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(rect.right/15);
        switch(type)
        {
            case 0:
                paint.setColor(Color.RED);
                paint.setAlpha((int)(alpha * 2.55));
                c.drawRect(rect.left - rect.right/2,
                        rect.top - rect.bottom/2,
                        rect.left + rect.right/2,
                        rect.top + rect.bottom/2, paint);
                break;
            case 1:
                paint.setColor(Color.GREEN);
                paint.setAlpha((int)(alpha * 2.55));
                c.drawCircle(rect.left, rect.top, rect.right/2, paint);
                break;
            case 2:
                paint.setColor(Color.BLUE);
                paint.setAlpha((int)(alpha * 2.55));
                Path lines = new Path();
                lines.moveTo(rect.left - rect.right/2, rect.top + rect.bottom/2);
                lines.lineTo(rect.left + rect.right/2, rect.top + rect.bottom/2);
                lines.lineTo(rect.left, rect.top - rect.bottom/2);
                lines.lineTo(rect.left - rect.right/2, rect.top + rect.bottom/2);
                lines.close();
                c.drawPath(lines, paint);
                break;
        }
    }
    
    public void update()
    {
        
        if(porcent > 125)porcent -= 225;
        if(porcent < -125)porcent += 225;
        
        int modulePorcent = porcent < 0 ? -porcent : porcent;
        
        rect.left = defaultRect.left + porcent;
        
        rect.right = (int)(((125 - (modulePorcent))/ 1.25) * 0.5);
        rect.bottom = rect.right;
        
        alpha = (int)((125 - (modulePorcent))/ 1.25);
    }
    
    public void setPosition(float x, float y)
    {
        rect.left = x;
        rect.top = y;
    }
    
    public void setPorcent(int p)
    {
        porcent = p;
    }
    
    public int getPorcent()
    {
        return porcent;
    }
    
    public float getY()
    {
        return rect.top;
    }
    
    public float getX()
    {
        return rect.left;
    }
}
