package com.wealthbook.android.utils;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;

import com.wealthbook.android.R;
import com.wealthbook.android.common.AlertDialogModel;
import com.wealthbook.android.common.IDialogClickListener;
import com.wealthbook.android.widget.wp_dialog.IWPDialogListener;
import com.wealthbook.android.widget.wp_dialog.WPDialogView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Utility {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?\\d{10,14}$";
    private static WPDialogView mDialog;
    private static IDialogClickListener mListener;
    private static AlertDialogModel mAlertModel;
    private static String DATE_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'";

    /**
     * To verify email is valid or not
     * @param email Email text
     * @return True/false
     */
    public static boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * To verify pass is valid or not
     * @param pass Password text
     * @return True/false
     */
    public static boolean isPasswordValid(String pass) {
        return pass.length() >= 6;
    }

    /**
     * Create alert model object
     * @param title         Title of alert
     * @param msg           Message body
     * @param positiveBtn   Positive button
     * @param negBtn        Negative button
     * @param action        Action
     * @param isCancellable is Cancellable
     * @return Return object
     */
    public static AlertDialogModel prepareDialogObj(String title, String msg,
                                                    String positiveBtn, String negBtn,
                                                    @StringRes int action, boolean isCancellable) {
        AlertDialogModel sDialogModel = new AlertDialogModel();
        sDialogModel.setTitle(title);
        sDialogModel.setMsg(msg);
        sDialogModel.setYesBtn(positiveBtn);
        sDialogModel.setNoBtn(negBtn);
        sDialogModel.setAction(action);
        sDialogModel.setCancelable(isCancellable);
        return sDialogModel;
    }

    public static void showDialog(FragmentManager fragManager, IDialogClickListener listener, AlertDialogModel alertModel) {
        mAlertModel = alertModel;
        mListener = listener;
        if (mDialog != null && mDialog.isVisible()) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = WPDialogView.newInstance(alertModel.getTitle(),
                alertModel.getMsg(),
                alertModel.getYesBtn(),
                alertModel.getNoBtn());
        mDialog.setDialogListener(mCallback);
        mDialog.setCancelable(alertModel.isCancelable());
        if (mDialog != null && !mDialog.isVisible() && !mDialog.isAdded()) {
            mDialog.show(fragManager, "");
        }
    }

    private static IWPDialogListener mCallback = new IWPDialogListener() {
        @Override
        public void onClickPositiveButton() {
            if (mListener != null) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                    mDialog = null;
                }
                mListener.onDialogClick(mAlertModel.getAction());
            }
        }

        @Override
        public void onClickNegativeButton() {
            if (mListener != null) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                }
                mListener.onDialogClick(R.string.cancel);
            }
        }
    };

    /**
     * Create html content for displaying in web view
     * @param data String data
     * @return Return content
     */
    public static String createChartContent(String data) {
        return "<html>"
                + "<head>"
                + "<script src=\"http://code.highcharts.com/highcharts.js\"></script>"
                + "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js\"></script>"
                + "</head>"
                + "<body>"
                + "<div id=\"container\" style=\"width: 100%;\"></div>"
                + "<script type=\"text/javascript\">\n" +
                "    Highcharts.chart('container', {\n" +
                "    chart: {\n" +
                "        plotBackgroundColor: null,\n" +
                "        plotBorderWidth: null,\n" +
                "        plotShadow: false,\n" +
                "        type: 'pie'\n" +
                "    },\n" +
                "    title: {\n" +
                "        text: ''\n" +
                "    },\n" +
                "   credits: {\n" +
                "       enabled: false\n" +
                "   },\n" +
                "    tooltip: {\n" +
                "        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'\n" +
                "    },\n" +
                "    plotOptions: {\n" +
                "        pie: {\n" +
                "            allowPointSelect: true,\n" +
                "            cursor: 'pointer',\n" +
                "            dataLabels: {\n" +
                "                enabled: true,\n" +
                "                format: '{point.percentage:.1f} %',\n" +
                "                 distance: -25,\n" +
                "                style: {\n" +
                "                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    series: [{\n" +
                "        name: 'Percentage',\n" +
                "        colorByPoint: true,\n" +
                "        data: " + data +
                "    }]\n" +
                "});\n" +
                "</script>"
                + "</html>";
    }

    /**
     * Create chart for notification alert
     * @param from   Chart data
     * @param to     Chart data
     * @param legend Legend value
     * @return Return chart content
     */
    public static String createContentForNotificationAlert(String from, String to, String legend, String size1, String size2) {
        return "<html>"
                + "<head>"
                + "<script src=\"http://code.highcharts.com/highcharts.js\"></script>"
                + "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js\"></script>"
                + "<link href=\"http://wealthbook-staging.elasticbeanstalk.com/assets/action_items.css?body=1\" media=\"screen\" rel=\"stylesheet\">"
                + "</head>"
                + "<body style=\"background-color:'#f0f0f0';display: block;text-align:center; font-family: -apple-system, BlinkMacSystemFont, sans-serif; margin: 0; padding: 0\">"
                + "<div id=\"fromContainer\" style=\"" + size1 + "\"></div>"
                + "<div id=\"toContainer\" style=\"" + size2 + "\">"
                + "</div>"
                + "<script type=\"text/javascript\">\n" +
                "    Highcharts.chart('fromContainer', {\n" +
                "    chart: {\n" +
                "        plotBackgroundColor: null,\n" +
                "        plotBorderWidth: null,\n" +
                "        plotShadow: false,\n" +
                "        type: 'pie'\n" +
                "    },\n" +
                "    title: {\n" +
                "        style: {\n" +
                "            color: '#000000',\n" +
                "            fontWeight: 'bold',\n" +
                "            fontSize: '2em'\n" +
                "        },\n" +
                "        text: 'From',\n" +
                "        margin: 20,\n" +
                "    },\n" +
                "   credits: {\n" +
                "       enabled: false\n" +
                "   },\n" +
                "       legend: {\n" +
                "        padding: 10\n" +
                "    },\n" +
                "    tooltip: {\n" +
                "        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'\n" +
                "    },\n" +
                "    plotOptions: {\n" +
                "        pie: {\n" +
                "            allowPointSelect: true,\n" +
//                "size: " + size + ",\n" +
                "            cursor: 'pointer',\n" +
                "            dataLabels: {\n" +
                "                enabled: true,\n" +
                "                format: '{point.percentage:.1f} %',\n" +
                "                 distance: -25,\n" +
                "                style: {\n" +
                "                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'\n" +
                "                }\n" +
                "            },\n" +
                "               showInLegend: " + legend +
                "        }\n" +
                "    },\n" +
                "    series: [{\n" +
                "        name: 'Percentage',\n" +
                "        colorByPoint: true,\n" +
                "        data: " + from +
                "    }]\n" +
                "});\n" +
                "    Highcharts.chart('toContainer', {\n" +
                "    chart: {\n" +
                "        plotBackgroundColor: null,\n" +
                "        plotBorderWidth: null,\n" +
                "        plotShadow: false,\n" +
                "        type: 'pie'\n" +
                "    },\n" +
                "    title: {\n" +
                "        style: {\n" +
                "            color: '#000000',\n" +
                "            fontWeight: 'bold',\n" +
                "            fontSize: '2em'\n" +
                "        },\n" +
                "        text: 'To',\n" +
                "    },\n" +
                "   credits: {\n" +
                "       enabled: false\n" +
                "   },\n" +
                "    tooltip: {\n" +
                "        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'\n" +
                "    },\n" +
                "    plotOptions: {\n" +
                "        pie: {\n" +
                "            allowPointSelect: true,\n" +
//                "size: " + size + ",\n" +
                "            cursor: 'pointer',\n" +
                "            dataLabels: {\n" +
                "                enabled: true,\n" +
                "                format: '{point.percentage:.1f} %',\n" +
                "                 distance: -25,\n" +
                "                style: {\n" +
                "                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'\n" +
                "                }\n" +
                "            },\n" +
//                "               showInLegend: " + legend +
                "        }\n" +
                "    },\n" +
                "    series: [{\n" +
                "        name: 'Percentage',\n" +
                "        colorByPoint: true,\n" +
                "        data: " + to +
                "    }]\n" +
                "});\n" +
                "</script>"
                + "</html>";
    }

    /**
     * Create content of chart and return string to load on web view
     * @param data Chart series data
     */
    public static String createContentForSingleLineChart(List data) {
        return "<html>"
                + "<head>"
                + "<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\"></script>"
                + "<script src=\"https://code.highcharts.com/stock/highstock.js\"></script>"
                + "</head>"
                + "<body>"
                + "<div id=\"container\" style=\"width: 100%;\"></div>"
                + "<script type=\"text/javascript\">\n"
                + " Highcharts.stockChart('container', {\n" +
                "        rangeSelector: {\n" +
                "            selected: 1\n" +
                "        },\n" +
                "       scrollbar: {\n" +
                "        enabled: false\n" +
                "       },\n" +
                "       navigator: {\n" +
                "           enabled: false\n" +
                "       },\n" +
                "        title: {\n" +
                "            text: 'titleText'\n" +
                "        },\n" +
                "   credits: {\n" +
                "       enabled: false\n" +
                "   },\n" +
                "        series: [{\n" +
                "            name: 'titleText',\n" +
                "            data: " + data + ",\n" +
                "            tooltip: {\n" +
                "                valueDecimals: 2\n" +
                "            }\n" +
                "        }]\n" +
                "    });" +
                "</script>"
                + "</html>";
    }

    /**
     * Get date from date string
     * @param dateString Date string
     * @return Return time in millisecond
     */
    public static long getMillisecondFromDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATTER, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * Convert decimal no to no of precision
     * @param val      Number
     * @param formator Formator string
     * @return Return string
     */
    public static String decimalFormator(Double val, String formator) {
        DecimalFormat df = new DecimalFormat(formator);
        return df.format(val);
    }

    public static String maskString(String strText, int start, int end, char maskChar)
            throws Exception {

        if (strText == null || strText.equals(""))
            return "";

        if (start < 0)
            start = 0;

        if (end > strText.length())
            end = strText.length();

        if (start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if (maskLength == 0)
            return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for (int i = 0; i < maskLength; i++) {
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }
}
