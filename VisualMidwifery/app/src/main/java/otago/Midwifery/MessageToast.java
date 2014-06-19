package otago.Midwifery;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liub3 on 15/04/14.
 */
public class MessageToast {
    public static void message(Context contextIn, String messageIn) {
        Toast.makeText(contextIn, messageIn, Toast.LENGTH_SHORT).show();
    }
}
