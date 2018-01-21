package com.xsl.lerist.llibrarys.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.socks.library.KLog;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.utils.ListUtils;

import java.util.ArrayList;

/**
 * Created by Lerist on 2016/2/29, 0029.
 */
public class LFragmentContainer extends FrameLayout {

    private FragmentManager fragmentManager;
    private int enterAnimation;
    private int exitAnimation;
    private int popEnterAnimation;
    private int popExitAnimarion;
    private boolean isReplace;
    private boolean enableBackStack;
    private ArrayList<String> titles;
    private boolean enableSaveState;

    public interface OnFragmentChangedListener {
        void onFragmentChangedBefore(Fragment currentVisiblefragment, int index);

        void onFragmentChanged(Fragment currentVisibleFragment, int index);
    }

    private ArrayList<Fragment> fragments;
    private ArrayList<Fragment> addedFragments = new ArrayList<>();
    private ArrayList<OnFragmentChangedListener> onFragmentChangedListeners = new ArrayList<>();
    private int currentVisibleFragmentIndex = -1;
    private int transitionStyle = -1;
    private int transition = -1;

    public LFragmentContainer(Context context) {
        this(context, null);
    }

    public LFragmentContainer(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LFragmentContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LFragmentContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void onStart() {
        if (isReplace) {
            if (getCurrentVisibleFragment() != null) {
                try {
                    getCurrentVisibleFragment().onStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (ListUtils.isNotEmpty(fragments)) {
                for (Fragment fragment : fragments) {
                    try {
                        fragment.onStart();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onResume() {
        if (isReplace) {
            if (getCurrentVisibleFragment() != null) {
                try {
                    getCurrentVisibleFragment().onResume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (ListUtils.isNotEmpty(fragments)) {
                for (Fragment fragment : fragments) {
                    try {
                        fragment.onResume();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onPause() {
        if (isReplace) {
            if (getCurrentVisibleFragment() != null) {
                try {
                    getCurrentVisibleFragment().onPause();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (ListUtils.isNotEmpty(fragments)) {
                for (Fragment fragment : fragments) {
                    try {
                        fragment.onPause();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onStop() {
        if (isReplace) {
            if (getCurrentVisibleFragment() != null) {
                try {
                    getCurrentVisibleFragment().onStop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (ListUtils.isNotEmpty(fragments)) {
                for (Fragment fragment : fragments) {
                    try {
                        fragment.onStop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void onDestroy() {
        if (isReplace) {
            if (getCurrentVisibleFragment() != null) {
                try {
                    getCurrentVisibleFragment().onDestroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (ListUtils.isNotEmpty(fragments)) {
                for (Fragment fragment : fragments) {
                    try {
                        fragment.onDestroy();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void init() {
        if (getId() < 0)
            setId(Lerist.generateViewId());

        if (getContext() instanceof FragmentActivity) {
            fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
        } else
            KLog.e("LFragmentContainer必须使用在FragmentActivity中");

        fragments = new ArrayList<>();
        titles = new ArrayList<String>();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public LFragmentContainer addOnFragmentChangedListener(OnFragmentChangedListener onFragmentChangedListener) {
        this.onFragmentChangedListeners.add(onFragmentChangedListener);
        return this;
    }

    public LFragmentContainer addFragment(Fragment fragment) {
        if (enableSaveState == true) {
            throw new IllegalArgumentException("enableSaveState value is true, please call addFragment(String title, Fragment fragment)");
        }
        return addFragment(null, fragment);
    }

    public LFragmentContainer addFragment(String title, Fragment fragment) {
        if (fragments.contains(fragment)) return this;

        fragments.add(fragment);
        titles.add(title);
//        show();
        return this;
    }

    public LFragmentContainer enableBackStack(boolean enableBackStack) {
        this.enableBackStack = enableBackStack;
        return this;
    }

    public int getCurrentVisibleFragmentIndex() {
        return currentVisibleFragmentIndex;
    }

    public Fragment getCurrentVisibleFragment() {
        if (currentVisibleFragmentIndex == -1) return null;
        return fragments.get(currentVisibleFragmentIndex);
    }

    public String getCurrentTitle() {
        if (currentVisibleFragmentIndex == -1) return null;
        return titles.get(getCurrentVisibleFragmentIndex());
    }

    public String getTitle(int index) {
        return titles.get(index);
    }

    public void setVisibleFragmentIndex(int index) {
        for (OnFragmentChangedListener onFragmentChangedListener : onFragmentChangedListeners) {
            if (onFragmentChangedListener != null)
                onFragmentChangedListener.onFragmentChangedBefore(getCurrentVisibleFragment(), getCurrentVisibleFragmentIndex());
        }

        if (getCurrentVisibleFragmentIndex() == index) {
            return;
        }

        this.currentVisibleFragmentIndex = index;
        show();
    }

    public void setVisibleFragment(Fragment fragment) {
        setVisibleFragmentIndex(fragments.indexOf(fragment));
    }

    public LFragmentContainer setReplaceMode(boolean isReplace) {
        this.isReplace = isReplace;
        return this;
    }

    public LFragmentContainer enableSaveState(boolean enableSaveState) {
        this.enableSaveState = enableSaveState;
        return this;
    }

    public LFragmentContainer setTransitionStyle(int transitionStyle) {
        this.transitionStyle = transitionStyle;
        return this;
    }

    public LFragmentContainer setTransition(int transition) {
        this.transition = transition;
        return this;
    }

    public LFragmentContainer setCustomAnimations(@AnimRes int enter,
                                                  @AnimRes int exit) {
        return setCustomAnimations(enter, exit, 0, 0);
    }

    public LFragmentContainer setCustomAnimations(@AnimRes int enter,
                                                  @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        this.enterAnimation = enter;
        this.exitAnimation = exit;
        this.popEnterAnimation = popEnter;
        this.popExitAnimarion = popExit;
        return this;
    }

    public int getCount() {
        return this.fragments.size();
    }

    private void show() {
        if (fragmentManager == null) {
            KLog.e("fragmentManager is null.");
            return;
        }
        Fragment currentVisibleFragment = getCurrentVisibleFragment();
        if (currentVisibleFragment == null) {
            KLog.e("currentVisibleFragment is null.");
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transitionStyle != -1) transaction.setTransitionStyle(transitionStyle);
        if (transition != -1) transaction.setTransition(transition);
        transaction.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimarion);

        if (isReplace == false) {
            for (int i = 0; i < this.fragments.size(); i++) {
                Fragment fragment = this.fragments.get(i);
                if (!addedFragments.contains(fragment)) {
                    Fragment fragmentOld = fragmentManager.findFragmentByTag(fragment.getTag());
                    if (enableSaveState && fragmentOld != null) {
                        fragment = fragmentOld;
                    } else {
                        transaction.add(getId(), fragment, titles.get(i));
                        if (enableBackStack) {
                            transaction.addToBackStack(fragment.getClass().getSimpleName());
                        }
                    }
                    addedFragments.add(fragment);
                }
                transaction.hide(fragment);
            }

            try {
                transaction.show(currentVisibleFragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                transaction.replace(getId(), currentVisibleFragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (OnFragmentChangedListener onFragmentChangedListener : onFragmentChangedListeners) {
            if (onFragmentChangedListener != null)
                onFragmentChangedListener.onFragmentChanged(currentVisibleFragment, fragments.indexOf(currentVisibleFragment));
        }
    }
}
