package majinservice.tapmusic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Element
{
	protected float x,y,width, height;
	protected int color;
	protected int alpha;
        protected int index;
	
	public Element(float x, float y, float w, float h, int c, int a)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.color = c;
		this.alpha = a;
	}
	
	public abstract void Draw(Canvas c);
	public abstract void Update();
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
        
        public int getIndex()
        {
            return index;
        }
}
