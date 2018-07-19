package com.wealthbook.android.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * BaseView is a custom linear layout with some abstract functions to ensure that
 * layout are correctly inflated and initialized.
 */
public abstract class BaseView extends FrameLayout {

    protected View mInflatedLayout;
    protected Context mContext;
    private boolean isViewSetupComplete = false;

    /**
     * Constructor for view with context and layout attribute as arguments.
     *
     * @param context context of activity.
     */
    public BaseView(Context context) {
        super(context);
        setupView(context);
    }

    /**
     * Constructor for view with context and layout attributes.
     *
     * @param context context of activity.
     * @param attrs   attribute set of view.
     */
    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    /**
     * Constructor for view with context, layout attributes and style attribute as arguments.
     *
     * @param context      context of activity.
     * @param attrs        attribute set of view.
     * @param defStyleAttr style attribute if provided.
     */
    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(context);
    }

    /**
     * function would be called from constructor functions only to
     * call some abstract functions to be implemented by sub classes.
     *
     * @param iContext context of activity
     */
    private void setupView(Context iContext) {


        //set context
        mContext = iContext;
        //get inflated layout and assign to mInflatedLayout
        mInflatedLayout = inflate(mContext, getIdOfLayoutToInflate(), this);
        if (!isInEditMode()) {
            //initialize component views and variables
            init(mInflatedLayout);
            isViewSetupComplete = true;
        }
        init(mInflatedLayout);
    }

    /**
     * an abstract function which would be specifying the layout to inflate in its implementation.
     * <p/>
     * Usually calls inflateLayout with layout resource id.
     *
     * @return the id of layout to inflate
     */
    protected abstract int getIdOfLayoutToInflate();

    /**
     * an abstract function which should have the code to initialize the
     * component views inside inflated layout or any other variables.
     *
     * @param inflatedLayout the inflated layout
     */
    protected abstract void init(View inflatedLayout);

    public boolean isViewSetupComplete() {

        return isViewSetupComplete;
    }

}
