package com.detatocargo.android.detatocargo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detatocargo.android.detatocargo.others.BookingKey;
import com.detatocargo.android.detatocargo.others.CardItemClickListener;
import com.detatocargo.android.detatocargo.R;
import com.detatocargo.android.detatocargo.activities.ViewBookingActivity;
import com.detatocargo.android.detatocargo.objects.BookingData;

import java.util.ArrayList;

/**
 * Created by Aldyaz on 7/20/2016.
 */
public class BookingListAdapter extends RecyclerView.Adapter<BookingCardItemHolder> {

    private ArrayList<BookingData> items;
    private Context context;

    BookingCardItemHolder bookingCardItemHolder;

    public BookingListAdapter(Context context, ArrayList<BookingData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BookingCardItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_booking_list, parent, false);
        bookingCardItemHolder = new BookingCardItemHolder(view);
        return bookingCardItemHolder;
    }

    @Override
    public void onBindViewHolder(BookingCardItemHolder holder, int position) {
        holder.nameTextView.setText(items.get(position).getNameBooking());
        holder.destinationTextView.setText(items.get(position).getDestinationBooking());
        holder.flightTextView.setText(items.get(position).getFlightBooking());
        holder.dateTextView.setText(items.get(position).getDateTimeInputted());

        holder.setCardItemClickListener(new CardItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(context, "Id booking : " + items.get(position).getIdBooking(), Toast.LENGTH_SHORT).show();
                Intent viewBookingIntent = new Intent(context, ViewBookingActivity.class);
                viewBookingIntent.putExtra(BookingKey.DATE_KEY, items.get(position).getDateTimeInputted());
                viewBookingIntent.putExtra(BookingKey.NAME_KEY, items.get(position).getNameBooking());
                viewBookingIntent.putExtra(BookingKey.EMAIL_KEY, items.get(position).getEmailBooking());
                viewBookingIntent.putExtra(BookingKey.PHONE_KEY, items.get(position).getPhoneBooking());
                viewBookingIntent.putExtra(BookingKey.ADDRESS_KEY, items.get(position).getAddressBooking());
                viewBookingIntent.putExtra(BookingKey.AWB_KEY, items.get(position).getAwbBooking());
                viewBookingIntent.putExtra(BookingKey.COMMODITY_KEY, items.get(position).getCommodityBooking());
                viewBookingIntent.putExtra(BookingKey.DESTINATION_KEY, items.get(position).getDestinationBooking());
                viewBookingIntent.putExtra(BookingKey.FLIGHT_KEY, items.get(position).getFlightBooking());
                viewBookingIntent.putExtra(BookingKey.PCS_KEY, items.get(position).getPcsBooking());
                viewBookingIntent.putExtra(BookingKey.WEIGHT_KEY, items.get(position).getWeightBooking());
                viewBookingIntent.putExtra(BookingKey.DIMENSION_KEY, items.get(position).getDimensionBooking());
                viewBookingIntent.putExtra(BookingKey.SHIPPER_KEY, items.get(position).getShipperBooking());
                viewBookingIntent.putExtra(BookingKey.CONSIGNEE_KEY, items.get(position).getConsigneeBooking());
                context.startActivity(viewBookingIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
