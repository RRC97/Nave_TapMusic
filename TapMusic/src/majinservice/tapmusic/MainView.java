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
import android.media.MediaPlayer;
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
    int countError;
    int gerator;
    boolean error;
    float volume;
    float width, height;
    MediaPlayer mediaPlayer;
    private boolean running;
    public MainView(Context c)
    {
    	
        super(c);
        
        height = getResources().getDisplayMetrics().heightPixels;
        width = getResources().getDisplayMetrics().widthPixels;
        volume = 1;
        setKeepScreenOn(true);
        
        running = true;
            
        touch = new InputTouch(height);
        
        mediaPlayer = MediaPlayer.create(c, R.raw.sound1);
        mediaPlayer.start();
        
        handler = new Handler();
        handler.postDelayed(this, 1);
    }
    
    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        
        if(error)
        {
	        if(countError % 2 == 0)
	        	c.translate(3, 0);
	        else
	        	c.translate(-3, 0);
        }
        
        touch.draw(c);
        
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        c.drawText("SCORE: " + score, 10, getBottom() - 10, paint);
        
        paint.setColor(Color.rgb((int)((1 - volume) * 255), (int)(volume * 255), 0)); 
        c.drawRect(0, 10, volume * width, 20, paint);
        
        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).Draw(c);
        }
    }
    
    public void play()
    {
    	running = true;
    }
    public void pause()
    {
    	running = false;
    }
    
    @Override
    public void run()
    {
    	if(running)
    	{
    		mediaPlayer.start();
    		onUpdate();
            invalidate();
    	}
    	else
    	{ 
        	mediaPlayer.pause();
    	}
    	
		handler.postDelayed(this, 1);
    }
    
    private void onUpdate()
    {
        touch.update();
        
        for(int i = 0; i < list.size(); i++)
        {
            list.get(i).Update();
        }
        
        mediaPlayer.setVolume(volume, volume);
        
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).y + list.get(i).height > getBottom() - 80)
            {
                if(list.get(i).getIndex() == touch.getIndex())
                {
                	if(volume < 1)
                		volume += 0.1f;
                	score +=10;
                }
                else
                {
                	error = true;
                	volume -= 0.1f;
                }
                list.remove(i);
                break;
            }
        }
        
        if(gerator > 50)
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
            gerator = 0;
        }
        
        gerator++;
        
        if(error)
        {
        	countError++;
        	if(countError > 10)
        	{
        		countError = 0;
        		error = false;
        	}
        }
        
        if(volume <= 0)volume = 0;
        if(volume >= 1)volume = 1;
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
