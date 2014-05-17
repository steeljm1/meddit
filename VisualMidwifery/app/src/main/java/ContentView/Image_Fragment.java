package ContentView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import otago.Midwifery.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class Image_Fragment extends Fragment {

    Bitmap image;
    String notes;

    public Image_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.image_fragment, container, false);

        image = getArguments().getParcelable("image");
        notes = getArguments().getString("notes");

        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setImageBitmap(image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment textFragment = new Notes_Fragment();

                Bundle bundle = new Bundle();
                bundle.putString("text", notes);

                textFragment.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_in_bottom, 0, 0, R.anim.slide_out_bottom);
                ft.add(R.id.GridRootContainer, textFragment, "NOTES_FRAG");
                ft.addToBackStack("NOTES_FRAG");
                ft.commit();
            }
        });

        return v;
    }
}
