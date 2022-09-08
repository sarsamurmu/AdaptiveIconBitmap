package example.sarsamurmu.adaptiveiconbitmap;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import sarsamurmu.adaptiveicon.AdaptiveIcon;

public class MainActivity extends AppCompatActivity {
    final static Map<String, Integer> icons = new HashMap<>();
    static Object[] iconsKeys;

    static {
        icons.put("Normal Icon", R.drawable.normal_icon);
        icons.put("Precision Icon", R.drawable.precision_icon);
        icons.put("Big Icon", R.drawable.big_icon);
        icons.put("Messed up Icon", R.drawable.messed_up_icon);
        // icons.put("Tinted Icon", R.drawable.tinted_icon);
        icons.put("Foreground only", R.drawable.foreground_only);
        icons.put("Background only", R.drawable.background_only);
        icons.put("Monochrome", R.drawable.monochrome_icon);
        iconsKeys = icons.keySet().toArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_row, iconsKeys));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadIcon(icons.get(iconsKeys[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        loadIcon(icons.get(iconsKeys[0]));
    }

    public void loadIcon(@DrawableRes int resId) {
        Drawable iconDrawable = ContextCompat.getDrawable(getApplicationContext(), resId);
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
        Drawable iconDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.normal_icon);
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
