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
import java.util.ArrayList;
import DBController.DatabaseController;
import Fragments.BaseFragment;
import Models.ContentFieldModel;
import otago.Midwifery.R;
import otago.Midwifery.TabConstants;

/**
 * Created on 17/05/2014.
 * This class extends normal fragment
 * the layout will contain a gridview object
 */
public class ContentGridViewFragment extends BaseFragment {

    ArrayList<ContentFieldModel> content;//an arraylist will hold all related content
    DatabaseController dataSource;//instance of database controller
    GridView gridView;//variable of gridview

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.content_grid_view, container, false);//when view created the fragment will inflate layout called content_grid_view

        Bundle bundle = getArguments();//unpack tge bundle from last fragment/activity
        int categoryID = bundle.getInt("categoryID");//get the category

        dataSource = new DatabaseController(getActivity());//init database controller
        try {
            content = dataSource.getContentOfCategory(categoryID);//index all content with category id
        }catch (Exception e){
            e.printStackTrace();//throw the exception
        }

        //set up gridview
        gridView = (GridView)v.findViewById(R.id.gridView);
        //set up on item click methods
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                bundle.putSerializable("content", content);//pack up content model and ready to pass to next fragment

                Fragment imagePager = new ImagePager();//instance and init new fragment call image pager
                imagePager.setArguments(bundle);//pass bundle to new fragment

                mActivity.pushFragments(TabConstants.TAB_CONTENT,imagePager,true,true);//push current fragment to the peek of stack and if back button clicked peek fragment will be popped
            }
        });

        gridView.setAdapter(new ImageAdapter());//set gridview adapter with inner class

        return v;
    }

    //this adapter will set grid view contains several images
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


