package ContentView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import DBController.DatabaseController;
import Fragments.BaseFragment;
import Models.ContentFieldModel;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;

public class ContentGridViewFragment extends BaseFragment {

    ArrayList<ContentFieldModel> content;
    DatabaseController dataSource;
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.content_grid_view, container, false);

        Bundle bundle = getArguments();
        int categoryID = bundle.getInt("categoryID");

        dataSource = new DatabaseController(getActivity());
        try {
            content = dataSource.getContentOfCategory(categoryID);
        }catch (Exception e){
            e.printStackTrace();
        }

        gridView = (GridView)v.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                bundle.putSerializable("content", content);

                Fragment imagePager = new ImagePager();
                imagePager.setArguments(bundle);

                mActivity.pushFragments(TabConstants.TAB_CONTENT,imagePager,true,true);
            }
        });

        gridView.setAdapter(new ImageAdapter());

        return v;
    }

    public class ImageAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return content.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            ImageView imageView;
            imageView = new ImageView(getActivity());
            Bitmap image = content.get(i).getImageContent();
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(image);
            return imageView;
        }
    }

}


