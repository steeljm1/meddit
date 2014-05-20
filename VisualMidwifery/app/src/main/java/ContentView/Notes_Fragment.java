package ContentView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import otago.Midwifery.R;

/**
 * Created by glenn_000 on 13/05/2014.
 */
public class Notes_Fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notes_fragment, container, false);

        TextView textView = (TextView) view.findViewById(R.id.notesTextView);
        textView.setText(getArguments().getString("text"));

        view.findViewById(R.id.notesContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
