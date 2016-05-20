package in.omerjerk.processingdemo.sketch.creators;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;

import in.omerjerk.processingdemo.ContextRetriever;
import in.omerjerk.processingdemo.FloatingActionButton;
import in.omerjerk.processingdemo.R;


public final class SubActionButtonCreator {
    private static final int SIZE = ContextRetriever.INSTANCE.getResources().getDimensionPixelSize(R.dimen.sub_floating_action_button_size);
    private static final int MARGIN = ContextRetriever.INSTANCE.getResources().getDimensionPixelSize(R.dimen.sub_floating_action_button_margin);

    private SubActionButtonCreator() {
        //no-op
    }

    public static FloatingActionButton createFrom(Context context, @ColorRes int color, @DrawableRes int drawableId, @IdRes int resId) {
        return new FloatingActionButton.Builder(context)
                .withButtonSize(SIZE)
                .withMargins(MARGIN, MARGIN, MARGIN, MARGIN)
                .withButtonColor(ContextRetriever.INSTANCE.getApplicationContext().getResources().getColor(color))
                .withGravity(Gravity.TOP | Gravity.END)
                .withDrawable(ContextCompat.getDrawable(ContextRetriever.INSTANCE.getApplicationContext(), drawableId))
                .withId(resId)
                .createInto(getActivityContentView());
    }

    private static ViewGroup getActivityContentView() {
        return (ViewGroup) ContextRetriever.INSTANCE.getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
    }
}
