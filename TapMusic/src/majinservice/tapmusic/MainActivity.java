package majinservice.tapmusic;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
	MainView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        view = new MainView(this);
        setContentView(view);
        //setContentView(R.layout.main);
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	view.play();
    }

    @Override
    public void onPause()
    {
    	super.onPause();
    	view.pause();
    }
}
