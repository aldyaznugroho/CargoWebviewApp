package com.detatocargo.android.detatocargo.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.detatocargo.android.detatocargo.others.CardItemClickListener;
import com.detatocargo.android.detatocargo.R;

/**
 * Created by Aldyaz on 7/21/2016.
 */
public class BookingCardItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTextView, destinationTextView, flightTextView, dateTextView;
    CardView cardView;
    Context context;
    CardItemClickListener cardItemClickListener;

    public BookingCardItemHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.text_view_list_name);
        destinationTextView = (TextView) itemView.findViewById(R.id.text_view_list_destination);
        flightTextView = (TextView) itemView.findViewById(R.id.text_view_list_flight);
        dateTextView = (TextView) itemView.findViewById(R.id.text_view_list_date);
        cardView = (CardView) itemView.findViewById(R.id.card_view_booking);

        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.cardItemClickListener.onItemClick(v, getAdapterPosition());
    }

    public void setCardItemClickListener(CardItemClickListener cardItemClickListener) {
        this.cardItemClickListener = cardItemClickListener;
    }
}
