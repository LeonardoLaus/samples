package ext.android.imageloader;

import android.support.annotation.DrawableRes;
import android.support.annotation.RestrictTo;

import ext.android.imageloader.annotations.ScaleType;
import ext.android.imageloader.progress.ProgressListener;

/**
 * Created by ROOT on 2017/7/27.
 */

public class ImageLoaderOptions {
    @DrawableRes
    private int placeholder = -1;
    @DrawableRes
    private int errorDrawable = -1;
    private ImageSize imageSize;
    private boolean isCrossFade;
    private boolean asBitmap;
    private boolean asGif;
    @ScaleType
    private int scaleType;
    private ProgressListener progressListener;

    private ImageLoaderOptions(Builder builder) {
        this.placeholder = builder.placeholder;
        this.errorDrawable = builder.errorDrawable;
        this.imageSize = builder.imageSize;
        this.isCrossFade = builder.isCrossFade;
        this.asGif = builder.asGif;
        this.asBitmap = builder.asBitmap;
        this.scaleType = builder.scaleType;
        this.progressListener = builder.progressListener;
    }

    @DrawableRes
    public int getPlaceholder() {
        return placeholder;
    }

    @DrawableRes
    public int getErrorDrawable() {
        return errorDrawable;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }

    public boolean isAsGif() {
        return asGif;
    }

    @ScaleType
    public int getScaleType() {
        return scaleType;
    }

    public ProgressListener getProgressListener() {
        return progressListener;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public static class ImageSize {
        private int width = 0;
        private int height = 0;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class Builder {
        private int placeholder;
        private int errorDrawable;
        private ImageSize imageSize;
        private boolean isCrossFade;
        private boolean asBitmap;
        private boolean asGif;
        @ScaleType
        private int scaleType = ScaleType.FIT_CENTER;
        private ProgressListener progressListener;

        public Builder placeholder(@DrawableRes int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder error(@DrawableRes int errorId) {
            this.errorDrawable = errorId;
            return this;
        }

        public Builder scaleType(@ScaleType int scaleType) {
            this.scaleType = scaleType;
            return this;
        }

        public Builder imageSize(int width, int height) {
            if (this.imageSize == null) {
                this.imageSize = new ImageSize(width, height);
            } else {
                this.imageSize.width = width;
                this.imageSize.height = height;
            }
            return this;
        }

        public Builder crossFade(boolean flag) {
            this.isCrossFade = flag;
            return this;
        }

        public Builder asGif() {
            this.asGif = true;
            this.asBitmap = false;
            return this;
        }

        public Builder asBitmap() {
            this.asBitmap = true;
            this.asGif = false;
            return this;
        }

        public Builder progressListener(ProgressListener progressListener) {
            this.progressListener = progressListener;
            return this;
        }

        public ImageLoaderOptions build() {
            return new ImageLoaderOptions(this);
        }
    }
}
