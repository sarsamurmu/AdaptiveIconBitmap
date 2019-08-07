package example.sarsamurmu.adaptiveiconbitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import sarsamurmu.adaptiveicon.AdaptiveIcon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadIcon();
    }

    public void loadIcon() {
        Drawable iconDrawable = getApplicationContext().getDrawable(R.drawable.my_adaptive_icon);
        AdaptiveIconDrawable adaptiveIconDrawable = ((AdaptiveIconDrawable) iconDrawable);

        AdaptiveIcon iconBitmap = new AdaptiveIcon();
        Drawable foregroundDrawable = adaptiveIconDrawable.getForeground();
        Drawable backgroundDrawable = adaptiveIconDrawable.getBackground();

        iconBitmap.setForeground(foregroundDrawable);
        iconBitmap.setBackground(backgroundDrawable);
        // Instead of setting drawable separately you can use this
        iconBitmap.setDrawables(foregroundDrawable, backgroundDrawable);
        iconBitmap.setPath(AdaptiveIcon.PATH_CIRCLE);
        iconBitmap.setScale(0.6);
        Bitmap myIco = iconBitmap.render();

        ImageView iconHolder = (ImageView) findViewById(R.id.iconHolder);
        iconHolder.setImageBitmap(myIco);
    }
}
