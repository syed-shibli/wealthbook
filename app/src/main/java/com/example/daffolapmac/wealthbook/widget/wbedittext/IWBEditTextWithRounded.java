package com.example.daffolapmac.wealthbook.widget.wbedittext;

public interface IWBEditTextWithRounded {

    /**
     * get value of edittext
     * @return
     */
    String getValue();

    /**
     * set value of edittect
     * @param value to be set
     */
    void setValue(String value);

    /**
     * set edittext hint
     * @param hint
     */
    void setHint(String hint);

    /**
     * To set max charector in edit text
     * @param maxChar
     */
    void setMaxChar(int maxChar);

    /**
     * Set text color
     * @param color Color
     */
    void setColorText(int color);

    /**
     * Set error value
     * @param value Text
     */
    void setErrorValue(String value);

    /**
     * Set visibility of error view
     * @param value True or false
     */
    void setErrorVisibility(boolean value);
}
