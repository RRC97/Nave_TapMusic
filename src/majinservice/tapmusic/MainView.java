/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majinservice.tapmusic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author root
 */
public class MainView extends View implements Runnable
{
    InputTouch touch;
    Handler handler;
    List<Element> list = new ArrayList<Element>();
    int score;
    public MainView(Context c)
    {
        super(c);
        
        float height = getResources().getDisplayMetrics().heightPixels;
            
        touch = new InputTouch(height);
        
        handler = new Handler();
        handler.postDelayed(this, 1);
    }
    
    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        
        touch.draw(c);
       
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        c.drawText("SCORE: " + score, 10, getBottom() - 10, paint);
        
        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).Draw(c);
        }
        
        invalidate();
    }
    
    public void run()
    {
        touch.update();
        
        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).Update();
        }
        
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).y + list.get(i).height > getBottom() - 80)
            {
                if(list.get(i).getIndex() == touch.getIndex())
                    score +=10;
                else
                    score = 0;
                list.remove(i);
                break;
            }
        }
        
        if(list.size() <= 0)
        {
            int selected = (int)(Math.random() * 3);
            switch(selected)
            {
                case 0:
                    list.add(new Quadrate(130,0,40,40,Color.RED));
                    break;
                case 1:
                    list.add(new Circle(130,0,40,40,Color.GREEN));
                    break;
                case 2:
                    list.add(new Triangle(130,0,40,40,Color.BLUE));
                    break;
            }
        }
        
        handler.postDelayed(this, 1);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        switch(e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                touch.setDefault(e.getX(), e.getY());
                touch.setActive(true);
                break;
            case MotionEvent.ACTION_UP:
                touch.setActive(false);
                break;
            default:
                touch.set(e.getX(), e.getY());
                break;
        }
        
        return true;
    }
}
