package com.wealthbook.android.widget.wbedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.widget.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WBEditTextWithRounded extends BaseView implements IWBEditTextWithRounded {

    @BindView(R.id.edt_text)
    EditText mEditText;

    @BindView(R.id.txv_error)
    TextView mTxvError;

    public WBEditTextWithRounded(Context context) {
        super(context);
    }

    public WBEditTextWithRounded(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs);
    }

    public WBEditTextWithRounded(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context, attrs);
    }

    /**
     * Set attribute of view
     *
     * @param context Context
     * @param attrs   Attributes
     */
    private void setAttributes(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.WBEditTextWithRounded, 0, 0);
//            String attr_font_name = mTypedArray.getString(R.styleable.WBEditTextWithRounded_WBETRFontName);
            String text = mTypedArray.getString(R.styleable.WBEditTextWithRounded_WBETRText);
            String hintText = mTypedArray.getString(R.styleable.WBEditTextWithRounded_WBETRHintText);
            int maxChar = mTypedArray.getInt(R.styleable.WBEditTextWithRounded_WBETRMaxChar, Integer.MAX_VALUE);
            int color = mTypedArray.getColor(R.styleable.WBEditTextWithRounded_WBETRColor, getResources().getColor(R.color.colorTextDefault));
            int editTextInputType = mTypedArray.getInt(R.styleable.WBEditTextWithRounded_android_inputType, InputType.TYPE_TEXT_VARIATION_NORMAL);
            //create typeface if provided , otherwise set default font
//            Typeface typeface = (attr_font_name != null && attr_font_name.length() > 0) ? Typeface.createFromAsset(context.getAssets(), attr_font_name) :
//                    Typeface.createFromAsset(context.getAssets(), getContext().getString(R.string.tt_regular_font));
//
            mTypedArray.recycle();
            //setting typeface
//            mEditText.setTypeface(typeface);
            setValue(text);
            setHint(hintText);
            setMaxChar(maxChar);
            setColorText(color);
            //set input type for edit text
            setInputType(editTextInputType);
        }
    }

    @Override
    protected int getIdOfLayoutToInflate() {
        return R.layout.wp_edit_text_with_rounded;
    }

    @Override
    protected void init(View inflatedLayout) {
        ButterKnife.bind(this);
    }

    @Override
    public String getValue() {
        return mEditText.getText().toString();
    }

    @Override
    public void setValue(String value) {
        mEditText.setText(value);
    }

    @Override
    public void setHint(String hint) {
        mEditText.setHint(hint);
    }

    @Override
    public void setMaxChar(int maxChar) {
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxChar)});
    }

    @Override
    public void setColorText(int color) {
        mEditText.setTextColor(color);
    }

    @Override
    public void setErrorValue(String value) {
        mTxvError.setText(value);
    }

    @Override
    public void setErrorVisibility(boolean value) {
        if (!value) {
            mTxvError.setVisibility(GONE);
            return;
        }
        mTxvError.setVisibility(VISIBLE);
    }

    @Override
    public void setInputType(int type) {
        mEditText.setInputType(type);
    }

    @Override
    public void clearData() {
        mEditText.setText("");
    }

    /**
     * Remove error messages if showing on typing
     */
    public void registerKeyListener() {
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    setErrorVisibility(false);
                } else {
                    setErrorVisibility(true);
                }
            }
        });
    }

    public void setError(String error) {
        mTxvError.setText(error);
    }
}
