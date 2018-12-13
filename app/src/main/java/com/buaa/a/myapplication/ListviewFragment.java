package com.buaa.a.myapplication;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static  entry now_entry;
    public static int entry_index;
    public static entryAdapter adapter;
    public static  List<entry> entryList = new ArrayList<entry>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private OnFragmentInteractionListener mListener;

    public ListviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListviewFragment newInstance(String param1, String param2) {
        ListviewFragment fragment = new ListviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
       // View view=inflater.inflate(R.layout.fragment_test2,container,false);
       //initEntry();
       //entryAdapter adapter=new entryAdapter(getActivity(),R.layout.entry_item,entryList);
       // ListView listView = (ListView) view.findViewById(R.id.List_view2);



        //listView.setAdapter(adapter);
        /*if (listView != null) {
            listView.setAdapter(adapter);
        }*/

    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listview, container, false);

    }*/
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_listview,container,false);
        initEntry();
         adapter=new entryAdapter(getActivity(),R.layout.entry_item,entryList);
         ListView listView = (ListView) view.findViewById(R.id.List_view2);


         listView.setAdapter(adapter);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
        //ListView item的点击事件

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] args = {};
                 now_entry=(entry) adapter.getItem(i);
                ViewDialogFragment entry_dialog=new ViewDialogFragment();
                entry_dialog.show(getActivity().getFragmentManager(),"EditNameDialog");
                entry_index=i;
                //adapter.notifyDataSetChanged();
                /*Uri uri_ = Uri.parse(EntryProvider.URL+"/"+String.valueOf(now_entry.get_ID()));
                int delete_num = getContext().getContentResolver().delete(uri_
                        ,"",args);
                entryList.remove(i);
                Toast.makeText(getActivity(), "delete " + delete_num, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();*/
            }
        });

        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        initEntry();
        entryAdapter adapter=new entryAdapter(getActivity(),R.layout.entry_item,entryList);
        ListView listView = (ListView) view.findViewById(R.id.List_view2);


        listView.setAdapter(adapter);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        initEntry();
        entryAdapter adapter=new entryAdapter(getActivity(),R.layout.entry_item,entryList);
        ListView listView = (ListView) view.findViewById(R.id.List_view2);


        listView.setAdapter(adapter);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
        super.onHiddenChanged(hidden);

    }



    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private  void initEntry(){
        String URL = "content://com.buaa.a.myapplication/entry";

        Uri entry = Uri.parse(URL);
        Cursor c = getActivity().getContentResolver().query(entry, null, null, null, "date");
        entryList.clear();
        if (c.moveToFirst()) {
            do{

                entry temp_entry=new entry(c.getString(c.getColumnIndex(EntryProvider._ID)),
                        c.getString(c.getColumnIndex( EntryProvider.DATE)),
                        c.getString(c.getColumnIndex( EntryProvider.MONEY)),
                        c.getString(c.getColumnIndex( EntryProvider.REMARKS)),
                        c.getString(c.getColumnIndex( EntryProvider.TYPE)));
                entryList.add(temp_entry);


            } while (c.moveToNext());
        }
    }
}
