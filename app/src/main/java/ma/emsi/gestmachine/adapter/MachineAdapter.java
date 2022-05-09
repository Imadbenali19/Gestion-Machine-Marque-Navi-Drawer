package ma.emsi.gestmachine.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.emsi.gestmachine.MainActivity;
import ma.emsi.gestmachine.R;
import ma.emsi.gestmachine.beans.Machine;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.MachineViewHolder> {

    private final String TAG = "MachineAdapter";
    private List<Machine> machines;
    private Context context;

    public MachineAdapter(Context context, List<Machine> machines) {
        this.machines = machines;
        this.context = context;
    }

    @NonNull
    @Override
    public MachineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.machine_item, parent, false);
        final MachineViewHolder holder = new MachineViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView idm = view.findViewById(R.id.idm);
                TextView ref = view.findViewById(R.id.m_ref);
                TextView date = view.findViewById(R.id.m_date);
                TextView prix = view.findViewById(R.id.m_prix);
                TextView marque = view.findViewById(R.id.m_marque);
                Intent intent = new Intent(context, MainActivity.class);

                intent.putExtra("ref", ref.getText().toString());
                intent.putExtra("date", date.getText().toString());
                intent.putExtra("prix", prix.getText().toString());
                intent.putExtra("marque", marque.getText().toString());
                intent.putExtra("ids", idm.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MachineViewHolder holder, int i) {
        Log.d(TAG, "onBindView call ! " + i);
        holder.ref.setText(machines.get(i).getReference());
        holder.date.setText(machines.get(i).getDateAchat());
        holder.prix.setText(machines.get(i).getPrix() + "");
        holder.marque.setText(machines.get(i).getMarque());
        holder.idmm.setText(machines.get(i).getId() + "");
    }

    @Override
    public int getItemCount() {
        return machines.size();
    }

    public class MachineViewHolder extends RecyclerView.ViewHolder {
        TextView idmm;
        TextView ref;
        TextView date;
        TextView prix;
        TextView marque;
        CardView parent;

        public MachineViewHolder(@NonNull View itemView) {
            super(itemView);
            idmm = itemView.findViewById(R.id.idm);
            ref = itemView.findViewById(R.id.m_ref);
            date = itemView.findViewById(R.id.m_date);
            prix = itemView.findViewById(R.id.m_prix);
            marque = itemView.findViewById(R.id.m_marque);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
