package com.example.sherpaatourguide.activity.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sherpaatourguide.R;

import java.util.List;


public class EmergencyNumbersAdapter extends RecyclerView.Adapter<EmergencyNumbersAdapter.ViewHolder> {

    private EmergencyNumbersActivity context;
    private List<EmergencyNumber> emergencyNumbers;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView phone, title;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            phone = view.findViewById(R.id.phone);
            title = view.findViewById(R.id.title);
        }
    }

    public EmergencyNumbersAdapter(EmergencyNumbersActivity context, List<EmergencyNumber> emergencyNumbers) {
        this.context = context;
        this.emergencyNumbers = emergencyNumbers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emergency_number, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final EmergencyNumber emergencyNumber = emergencyNumbers.get(position);

            holder.phone.setText(emergencyNumber.getPrimaryPhone());
            holder.title.setText(emergencyNumber.getTitle());

        }



    @Override
    public int getItemCount() {
        return emergencyNumbers.size();
    }
}
