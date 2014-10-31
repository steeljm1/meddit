package otago.Arb;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liub3 on 15/04/14.
 * Message display class similar like MessageBox::Show("");
 */
public class MessageToast {
    public static void message(Context contextIn, String messageIn) {
        Toast.makeText(contextIn, messageIn, Toast.LENGTH_SHORT).show();
    }
}