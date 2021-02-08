package sarsamurmu.adaptiveicon;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.graphics.PathParser;

public class AdaptiveIcon {
    public static final int PATH_CIRCLE = 0;
    public static final int PATH_SQUIRCLE = 1;
    public static final int PATH_ROUNDED_SQUARE = 2;
    public static final int PATH_SQUARE = 3;
    public static final int PATH_TEARDROP = 4;

    private Drawable fgDrawable, bgDrawable;
    private Bitmap bgBitmap, fgBitmap;
    private Path path;
    private Rect pathSize;
    private final Paint paint;

    private double scale;
    private int size;

    public AdaptiveIcon() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);

        setScale(0.66);
        setPath(PATH_CIRCLE);
        setSize(256);
    }

    public AdaptiveIcon setForeground(Drawable drawable) {
        fgDrawable = drawable;
        return this;
    }

    public AdaptiveIcon setBackground(Drawable drawable) {
        bgDrawable = drawable;
        return this;
    }

    public AdaptiveIcon setDrawables(Drawable fgDrawableData, Drawable bgDrawableData) {
        fgDrawable = fgDrawableData;
        bgDrawable = bgDrawableData;
        return this;
    }

    @TargetApi(26)
    public AdaptiveIcon setDrawable(AdaptiveIconDrawable drawable) {
        fgDrawable = drawable.getForeground();
        bgDrawable = drawable.getBackground();
        return this;
    }

    public AdaptiveIcon setScale(double scaleData) {
        scale = scaleData;
        return this;
    }

    public AdaptiveIcon setSize(int newSize) {
        size = newSize;
        return this;
    }

    public AdaptiveIcon setPath(int pathType) {
        path = new Path();
        pathSize = new Rect(0, 0, 50, 50);
        switch (pathType) {
            case PATH_CIRCLE:
                path.arcTo(new RectF(pathSize), 0, 359);
                path.close();
                break;
            case PATH_SQUIRCLE:
                setPath("M 50,0 C 10,0 0,10 0,50 C 0,90 10,100 50,100 C 90,100 100,90 100,50 C 100,10 90,0 50,0 Z");
                break;
            case PATH_ROUNDED_SQUARE:
                setPath("M 50,0 L 70,0 A 30,30,0,0 1 100,30 L 100,70 A 30,30,0,0 1 70,100 L 30,100 A 30,30,0,0 1 0,70 L 0,30 A 30,30,0,0 1 30,0 z");
                break;
            case PATH_SQUARE:
                path.lineTo(0, 50);
                path.lineTo(50, 50);
                path.lineTo(50, 0);
                path.lineTo(0, 0);
                path.close();
                break;
            case PATH_TEARDROP:
                setPath("M 50,0 A 50,50,0,0 1 100,50 L 100,85 A 15,15,0,0 1 85,100 L 50,100 A 50,50,0,0 1 50,0 z");
                break;
        }
        return this;
    }

    public AdaptiveIcon setPath(String pathData) {
        path = PathParser.createPathFromPathData(pathData);
        pathSize = new Rect(0, 0, 100, 100);
        return this;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) return null;

        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null)
                return bitmapDrawable.getBitmap();
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0)
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        else
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public Bitmap getFgBitmap() {
        if (fgBitmap == null) {
            fgBitmap = drawableToBitmap(fgDrawable);
        }
        return fgBitmap;
    }

    public Bitmap getBgBitmap() {
        if (bgBitmap == null) {
            bgBitmap = drawableToBitmap(bgDrawable);
        }
        return bgBitmap;
    }

    private Path getScaledPath(Path origPath, Rect origRect, int width, int height) {
        Rect newRect = new Rect(0, 0, width, height);
        int origWidth = origRect.right - origRect.left;
        int origHeight = origRect.bottom - origRect.top;

        Matrix matrix = new Matrix();
        matrix.postScale((float) (newRect.right - newRect.left) / origWidth, (float) (newRect.bottom - newRect.top) / origHeight);

        Path newPath = new Path();
        origPath.transform(matrix, newPath);
        return newPath;
    }

    public Bitmap scaleBitmap(Bitmap bmp) {
        double newHeight = size / scale;
        double newWidth = bmp.getWidth() * (newHeight / bmp.getHeight());
        return Bitmap.createScaledBitmap(bmp, (int) newWidth, (int) newHeight, true);
    }

    public Bitmap render() {
        int wholeSize = (int) (size / scale);

        Bitmap mergedBmp = Bitmap.createBitmap(wholeSize, wholeSize, Bitmap.Config.ARGB_8888);
        Canvas mergedBmpCanvas = new Canvas(mergedBmp);
        Bitmap bgBmp = scaleBitmap(getBgBitmap());
        Bitmap fgBmp = scaleBitmap(getFgBitmap());
        mergedBmpCanvas.drawBitmap(bgBmp, (int) ((wholeSize - bgBmp.getWidth()) / 2), (int) ((wholeSize - bgBmp.getHeight()) / 2), paint);
        mergedBmpCanvas.drawBitmap(fgBmp, (int) ((wholeSize - fgBmp.getWidth()) / 2), (int) ((wholeSize - fgBmp.getHeight()) / 2), paint);

        int cropSize = (int) (wholeSize * scale);
        int cropPos = (wholeSize - cropSize) / 2;
        Bitmap croppedBmp = Bitmap.createBitmap(mergedBmp, cropPos, cropPos, cropSize, cropSize);

        Path scaledPath = getScaledPath(path, pathSize, croppedBmp.getWidth(), croppedBmp.getHeight());
        Paint bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setShader(new BitmapShader(croppedBmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        Bitmap iconBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas iconCanvas = new Canvas(iconBitmap);
        iconCanvas.drawPath(scaledPath, bitmapPaint);

        return iconBitmap;
    }
}
