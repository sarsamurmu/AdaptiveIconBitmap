package example.sarsamurmu.adaptiveiconbitmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

import sarsamurmu.adaptiveicon.AdaptiveIcon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadIcon();
    }

    public void loadIcon() {
        Drawable iconDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.precision_icon);
//        Drawable iconDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.app_icon);
        AdaptiveIconDrawable adaptiveIconDrawable = ((AdaptiveIconDrawable) iconDrawable);

        ImageView squareIcon = findViewById(R.id.square);
        squareIcon.setImageBitmap(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_SQUARE));

        ImageView roundedSquareIcon = findViewById(R.id.rounded_square);
        roundedSquareIcon.setImageBitmap(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_ROUNDED_SQUARE));

        ImageView squircleIcon = findViewById(R.id.squircle);
        squircleIcon.setImageBitmap(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_SQUIRCLE));

        ImageView circleIcon = findViewById(R.id.circle);
        circleIcon.setImageBitmap(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_CIRCLE));

        ImageView teardropIcon = findViewById(R.id.teardrop);
        teardropIcon.setImageBitmap(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_TEARDROP));
    }

    public void saveImages(View v) {
        Drawable iconDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.app_icon);
        AdaptiveIconDrawable adaptiveIconDrawable = ((AdaptiveIconDrawable) iconDrawable);

        saveImage(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_SQUARE), "square");
        saveImage(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_ROUNDED_SQUARE), "rounded_square");
        saveImage(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_SQUIRCLE), "squircle");
        saveImage(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_CIRCLE), "circle");
        saveImage(genBitmap(adaptiveIconDrawable, AdaptiveIcon.PATH_TEARDROP), "teardrop");
    }

    public Bitmap genBitmap(AdaptiveIconDrawable drawable, int path) {
        return new AdaptiveIcon()
                .setDrawable(drawable)
                .setPath(path)
                .render();
    }

    private void saveImage(Bitmap finalBitmap, String image_name) {
        String TAG = "AdaptiveIconBitmap-Sample";

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/AdaptiveIconBitmap");
        String fileName = image_name + ".png";
        File file = new File(myDir, fileName);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    Log.e(TAG, "Unable to create file: " + fileName);
                }
            }
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }
}
