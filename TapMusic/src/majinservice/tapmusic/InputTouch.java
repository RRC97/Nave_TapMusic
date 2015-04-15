/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majinservice.tapmusic;

import android.graphics.Canvas;

/**
 *
 * @author root
 */
public class InputTouch
{
    private float x, y, lastX, lastY;
    private int index;
    private float porcent;
    private boolean active;
    private Shape[] shapes;
    
    public InputTouch(float screenHeight)
    {
        
        shapes = new Shape[3];
        
        for(int i = 0; i < shapes.length; i++)
        {
            shapes[i] = new Shape(75 + i * 75, screenHeight - 100, 150, 400, i);
            shapes[i].setPorcent(-75 + i * 75);
        }
    }
    
    public void draw(Canvas c)
    {
        //a.draw(c);
        for(int i = 0; i < shapes.length; i++)
        {
            shapes[i].draw(c);
        }
    }
    
    public void update()
    {
        if(active)
        {
            for(int i = 0; i < shapes.length; i++)
            {
                shapes[i].setPorcent(shapes[i].getPorcent() + (int)(x - lastX));
            }
            lastX = x;
        }
        else
        {
            int porcent = shapes[index].getPorcent();
            if(porcent > 0)
            {
                for(int i = 0; i < shapes.length; i++)
                {
                    shapes[i].setPorcent(shapes[i].getPorcent() - 1);

                }
            }
            else if(porcent < 0)
            {
                for(int i = 0; i < shapes.length; i++)
                {
                    shapes[i].setPorcent(shapes[i].getPorcent() + 1);
                }
            }
        }
        for(int i = 0; i < shapes.length; i++)
        {
            shapes[i].update();
        }
        
        for(int i = 0; i < shapes.length; i++)
        {
            int porcent = shapes[i].getPorcent();
            int modulePorcent = porcent < 0 ? -porcent : porcent;
            int porcent2 = shapes[index].getPorcent();
            int modulePorcent2 = porcent2 < 0 ? -porcent2 : porcent2;
            if(modulePorcent < modulePorcent2)
                index = i;
        }
    }
    
    public void setActive(boolean a)
    {
        active = a;
    }
    
    public void setDefault(float x, float y)
    {
        this.x = x;
        this.y = y;
        lastX = x;
        lastY = y;
    }
    
    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getIndex()
    {
        return index;
    }
}
