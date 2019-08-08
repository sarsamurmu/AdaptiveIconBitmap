package example.sarsamurmu.adaptiveiconbitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
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

        ImageView squareIcon = (ImageView) findViewById(R.id.square);
        loadIconToView(adaptiveIconDrawable, AdaptiveIcon.PATH_SQUARE, squareIcon);

        ImageView roundedSquareIcon = (ImageView) findViewById(R.id.rounded_square);
        loadIconToView(adaptiveIconDrawable, AdaptiveIcon.PATH_ROUNDED_SQUARE, roundedSquareIcon);

        ImageView squircleIcon = (ImageView) findViewById(R.id.squircle);
        loadIconToView(adaptiveIconDrawable, AdaptiveIcon.PATH_SQUIRCLE, squircleIcon);

        ImageView circleIcon = (ImageView) findViewById(R.id.circle);
        loadIconToView(adaptiveIconDrawable, AdaptiveIcon.PATH_CIRCLE, circleIcon);

        ImageView teardropIcon = (ImageView) findViewById(R.id.teardrop);
        loadIconToView(adaptiveIconDrawable, AdaptiveIcon.PATH_TEARDROP, teardropIcon);
    }

    public void loadIconToView(AdaptiveIconDrawable drawable, int path, ImageView view) {
        Drawable foregroundDrawable = drawable.getForeground();
        Drawable backgroundDrawable = drawable.getBackground();

        AdaptiveIcon iconBitmap = new AdaptiveIcon()
                                        .setDrawables(foregroundDrawable, backgroundDrawable)
                                        .setPath(path);

        view.setImageBitmap(iconBitmap.render());
    }
}
