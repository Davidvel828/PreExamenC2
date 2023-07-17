package com.example.preexamencorte;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> implements View.OnClickListener {
    private Application context;
    private ArrayList<Usuario> usuario;
    private ArrayList<Usuario> listaUsuarios;
    private LayoutInflater inflater;
    private View.OnClickListener listener;

    public UsuarioAdapter(ArrayList<Usuario> listaUsuarios, Application context) {
        this.listaUsuarios = listaUsuarios;
        this.usuario = new ArrayList<>(listaUsuarios);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.usuarios_items, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        if (usuario != null) {
            holder.txtNombreUsuario.setText(usuario.getNombreUsuario());
            holder.txtCorreo.setText(usuario.getCorreo());
        }
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombreUsuario;
        private TextView txtCorreo;
        private TextView txtContrasena;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreUsuario = itemView.findViewById(R.id.txtNombreUsuario);
            txtCorreo = itemView.findViewById(R.id.txtCorreo);
        }
    }

    public void setUsuarioList(ArrayList<Usuario> listaUsuarios) {
        this.usuario.clear();
        this.usuario.addAll(listaUsuarios);
        notifyDataSetChanged();
    }
}

