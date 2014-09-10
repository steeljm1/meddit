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
import Helper.PinchTouchContentImageView;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class Image_Fragment extends BaseFragment  {

    Bitmap image;
    String notes;
    PinchTouchContentImageView imageView;
    TextView noteTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.image_fragment, container, false);
        imageView = (PinchTouchContentImageView) v.findViewById(R.id.imageView);
        noteTextView = (TextView) v.findViewById(R.id.notesTextView);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        image = getArguments().getParcelable("image");
        notes = getArguments().getString("notes");
        imageView.setImageBitmap(image);
        noteTextView.setText(notes);
    }
}
