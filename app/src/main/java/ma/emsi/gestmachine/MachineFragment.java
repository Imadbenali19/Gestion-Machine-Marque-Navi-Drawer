package ma.emsi.gestmachine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.emsi.gestmachine.adapter.MachineAdapter;
import ma.emsi.gestmachine.beans.Machine;

public class MachineFragment extends Fragment {

    private RecyclerView recyclerView;
    private MachineAdapter machineAdapter = null;
    private List<Machine> machines = new ArrayList<>();
    private JSONArray response;
    private static final String TAG = "MachineFragment";
    private static final String getUrl = "http://10.0.2.2:8090/machines/all";

    View machineView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        machineView = inflater.inflate(R.layout.machine_fragment, container, false);

        recyclerView = machineView.findViewById(R.id.recycle_view);
        getAllMachines();
        return machineView;
    }

    public void getAllMachines() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            MachineFragment.this.response = new JSONArray(response);
                            for (int i = 0; i < MachineFragment.this.response.length(); i++) {
                                JSONObject machine = MachineFragment.this.response.getJSONObject(i);
                                JSONObject marque = machine.getJSONObject("marque");
                                Log.d(TAG, machine.toString());
                                Log.d(TAG, marque.toString());
                                machines.add(new Machine(Integer.parseInt(machine.getString("id")),
                                        machine.getString("reference"),
                                        machine.getString("dateAchat"),
                                        Double.parseDouble(machine.getString("prix")),
                                        marque.getString("libelle")));
                            }

                            machineAdapter = new MachineAdapter(getContext(), machines);
                            recyclerView.setAdapter(machineAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MainActivity", error.toString());
            }
        });
        queue.add(stringRequest);
    }
}
