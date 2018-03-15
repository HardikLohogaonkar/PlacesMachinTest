package com.example.hardik.placesmachintest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hardik on 15/11/17.
 */

public class AdapterPlaces extends RecyclerView.Adapter<AdapterPlaces.ViewHolderPlaces> {

    private Context mContext;
    private ArrayList<Places>mArrayPlaces;

    public AdapterPlaces(Context Context, ArrayList<Places> ArrayPlaces) {
        this.mContext = Context;
        this.mArrayPlaces = ArrayPlaces;
    }

    @Override
    public ViewHolderPlaces onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.lay_adapter_places,null);
        ViewHolderPlaces viewHolderPlaces=new ViewHolderPlaces(view);
        return viewHolderPlaces;
    }

    @Override
    public void onBindViewHolder(ViewHolderPlaces holder, int position) {

        final Places mPlaces=mArrayPlaces.get(position);
        holder.mTxtName.setText("Name: "+mPlaces.getName());
        holder.mTxtAddress.setText("Address: "+mPlaces.getAddress());
        holder.mTxtRating.setText("Rating: "+mPlaces.getRatings());
        Picasso.with(mContext).load(mPlaces.getIcon()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mArrayPlaces.size();
    }

    public class ViewHolderPlaces extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTxtName,mTxtAddress,mTxtRating;

        public ViewHolderPlaces(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView.findViewById(R.id.imageview);
            mTxtName= (TextView) itemView.findViewById(R.id.name);
            mTxtAddress= (TextView) itemView.findViewById(R.id.address);
            mTxtRating= (TextView) itemView.findViewById(R.id.rating);

        }
    }
}
