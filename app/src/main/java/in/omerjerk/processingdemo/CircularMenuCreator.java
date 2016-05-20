package in.omerjerk.processingdemo;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;

import com.oguzdev.circularfloatingactionmenu.library.CircularMenu;

import in.omerjerk.processingdemo.R;
import in.omerjerk.processingdemo.sketch.creators.MenuButton;
import in.omerjerk.processingdemo.sketch.creators.SubActionButtonCreator;

public final class CircularMenuCreator {
    private static final int MENU_RADIUS = ContextRetriever.INSTANCE.getResources().getDimensionPixelSize(R.dimen.menu_floating_action_button_radius);
    private static final int BUTTON_SIZE = ContextRetriever.INSTANCE.getResources().getDimensionPixelSize(R.dimen.menu_floating_action_button_size);
    private static final int MARGIN = ContextRetriever.INSTANCE.getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
    private static final int MENU_COLOR = ContextRetriever.INSTANCE.getResources().getColor(R.color.colorAccent);
    private static final int START_ANGLE = 185;
    private static final int END_ANGLE = 85;

    private CircularMenuCreator() {
        //no-op
    }

    public static CircularMenu create() {
        Context context = ContextRetriever.INSTANCE.getApplicationContext();
        return new CircularMenu.Builder(context)
                .setRadius(MENU_RADIUS)
/*                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimary,
                        R.drawable.brush,
                        MenuButton.BRUSH.getId()))
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimary,
                        R.drawable.brush,
                        MenuButton.FLOWER.getId()))
            *//*   TODO uncomment this code when the feature to select different music is implemented.
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimary,
                        R.drawable.music,
                        MenuButton.MUSIC.getId()))  *//*
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimaryLight,
                        R.drawable.brush,
                        MenuButton.SHARE.getId()))
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorPrimaryLight,
                        R.drawable.brush,
                        MenuButton.SCREENSHOT.getId()))
                .addSubActionView(SubActionButtonCreator.createFrom(context,
                        R.color.colorAccent,
                        R.drawable.brush,
                        MenuButton.RESTART.getId()))*/
       /*         .attachTo(createMenuButtonWith(context,
                        R.drawable.brush,
                        MenuButton.MENU.getId()))*/
                .setStartAngle(START_ANGLE)
                .setEndAngle(END_ANGLE)
                .build();
    }

    private static FloatingActionButton createMenuButtonWith(Context context, @DrawableRes int drawableId, @IdRes int resId) {
        return new FloatingActionButton.Builder(context)
                .withButtonSize(BUTTON_SIZE)
                .withMargins(MARGIN, MARGIN, MARGIN, MARGIN)
                .withButtonColor(MENU_COLOR)
                .withGravity(Gravity.TOP | Gravity.END)
                .withDrawable(ContextCompat.getDrawable(ContextRetriever.INSTANCE.getApplicationContext(), drawableId))
                .withId(resId)
                .createInto(getActivityContentView());
    }

    private static ViewGroup getActivityContentView() {
        return (ViewGroup) ContextRetriever.INSTANCE.getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
    }

}
