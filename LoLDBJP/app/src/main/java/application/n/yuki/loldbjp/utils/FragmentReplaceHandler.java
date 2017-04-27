package application.n.yuki.loldbjp.utils;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import application.n.yuki.loldbjp.R;
import application.n.yuki.loldbjp.view.base.BaseFragment;

public class FragmentReplaceHandler extends PauseHandler {
    public static final int ANIMATED = 1, NON_ANIMATED = 0;

    public static final int
            BACK_STACK_NONE = 0,
            BACK_STACK_TICKET_LIST = 1,
            BACK_STACK_AUTH_CHECK = 2,
            BACK_STACK_CALENDAR = 3,
            BACK_STACK_TICKET_MANAGEMENT = 4,
            BACK_STACK_TOP = 5,
            BACK_STACK_EVENT_LIST = 6;

    public static final String
            BACK_STACK_NAME_TICKET_LIST = "ticketList",
            BACK_STACK_NAME_AUTH_CHECK = "authCheck",
            BACK_STACK_NAME_CALENDAR = "calendar",
            BACK_STACK_NAME_TICKET_MANAGEMENT = "ticketManagement",
            BACK_STACK_NAME_TOP = "top",
            BACK_STACK_NAME_EVENT_LIST = "eventList";

    public static final String[] BACK_STACK_NAMES = {
            "",
            BACK_STACK_NAME_TICKET_LIST,
            BACK_STACK_NAME_AUTH_CHECK,
            BACK_STACK_NAME_CALENDAR,
            BACK_STACK_NAME_TICKET_MANAGEMENT,
            BACK_STACK_NAME_TOP,
            BACK_STACK_NAME_EVENT_LIST,
    };

    private FragmentActivity mActivity;

    public FragmentReplaceHandler(Looper looper) {
        super(looper);
    }

    public void setActivity(FragmentActivity activity) {
        mActivity = activity;
    }

    public FragmentActivity getActivity() {
        return mActivity;
    }

    public void replaceFragment(BaseFragment fragment, @Animation int animation, @BackStackId int backStackId) {
        Message message = new Message();
        message.obj = fragment;
        message.arg1 = animation;
        message.arg2 = backStackId;
        sendMessage(message);
    }

    public void replaceFragment(BaseFragment fragment, @Animation int animation) {
        replaceFragment(fragment, animation, BACK_STACK_NONE);
    }

    public void replaceFragment(BaseFragment fragment) {
        replaceFragment(fragment, ANIMATED, BACK_STACK_NONE);
    }

    @Override
    protected boolean storeMessage(Message message) {
        return true;
    }

    @Override
    protected void processMessage(Message message) {
        final BaseFragment fragment = (BaseFragment) message.obj;
        final boolean animated = message.arg1 == ANIMATED;
        final int backStackId = message.arg2;

        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (animated) {
            transaction.setCustomAnimations(
                    R.anim.slide_in_up, R.anim.short_fade_out,
                    R.anim.short_fade_in, R.anim.slide_out_down);
        }
        transaction.replace(R.id.content_frame, fragment);

        if (backStackId > 0) {
            transaction.addToBackStack(BACK_STACK_NAMES[backStackId]);
        } else {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ANIMATED, NON_ANIMATED})
    public @interface Animation {
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            BACK_STACK_NONE,
            BACK_STACK_TICKET_LIST,
            BACK_STACK_AUTH_CHECK,
            BACK_STACK_CALENDAR,
            BACK_STACK_TICKET_MANAGEMENT,
            BACK_STACK_TOP,
            BACK_STACK_EVENT_LIST,
    })
    public @interface BackStackId {
    }
}
