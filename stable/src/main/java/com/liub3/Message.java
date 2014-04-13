package com.liub3;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liub3 on 7/04/14.
 */
public class Message {
    public static void message(Context context, String messageIn) {
        Toast.makeText(context, messageIn, Toast.LENGTH_SHORT).show();
    }
}
