package cn.homelabs.imageloader.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import cn.homelabs.imageloader.ImageLoader;
import cn.homelabs.imageloader.ImageLoaderOptions;
import cn.homelabs.imageloader.annotations.ScaleType;

/**
 * Created by ROOT on 2017/7/27.
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void showImage(@NonNull View view, @NonNull Object model, @Nullable ImageLoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            showImageInternal(imageView, model, options);
        }
    }

    @Override
    public void resume(@NonNull Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    public void pause(@NonNull Context context) {
        Glide.with(context).pauseRequests();
    }


    private void showImageInternal(@NonNull ImageView imageView, Object resource, @Nullable ImageLoaderOptions options) {
        RequestManager requestManager = Glide.with(imageView.getContext());
        RequestBuilder builder = null;
        if (options != null) {
            if (options.isAsBitmap()) {
                builder = requestManager.asBitmap();
            }
            if (options.isAsGif()) {
                builder = requestManager.asGif();
            }
        }
        if (builder == null) {
            builder = requestManager.load(resource);
        } else {
            builder.load(resource);
        }
        RequestOptions requestOptions = loadOptions(options);
        if (requestOptions != null) {
            builder.apply(requestOptions);
        }
        if (options != null) {
            if (options.isCrossFade()) {
                builder.transition(new DrawableTransitionOptions().crossFade());
            }
        }
        builder.into(imageView);
    }

    private RequestOptions loadOptions(ImageLoaderOptions options) {
        if (options == null) return null;
        RequestOptions requestOptions = new RequestOptions();
        if (options.getPlaceholder() != -1) {
            requestOptions.placeholder(options.getPlaceholder());
        }
        if (options.getErrorDrawable() != -1) {
            requestOptions.error(options.getErrorDrawable());
        }
        if (options.getImageSize() != null) {
            final ImageLoaderOptions.ImageSize imageSize = options.getImageSize();
            requestOptions.override(imageSize.getWidth(), imageSize.getHeight());
        }
        if (options.isAsGif()) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        }

        switch (options.getScaleType()) {
            case ScaleType.FIT_CENTER:
                requestOptions.fitCenter();
                break;
            case ScaleType.CENTER_CROP:
                requestOptions.centerCrop();
                break;
            case ScaleType.CENTER_INSIDE:
                requestOptions.centerInside();
                break;
            default:
                requestOptions.fitCenter();
                break;
        }

        return requestOptions;
    }
}
