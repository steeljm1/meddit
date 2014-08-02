package ContentView;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import Fragments.BaseFragment;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class Image_Fragment extends BaseFragment {

    Bitmap image;
    String notes;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.image_fragment, container, false);

        image = getArguments().getParcelable("image");
        notes = getArguments().getString("notes");

        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setImageBitmap(image);

        TextView noteTextView = (TextView) v.findViewById(R.id.notesTextView);
        noteTextView.setText(notes);

        return v;
    }

}
