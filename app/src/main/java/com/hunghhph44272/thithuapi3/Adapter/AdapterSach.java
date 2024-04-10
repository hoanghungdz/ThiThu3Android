package com.hunghhph44272.thithuapi3.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hunghhph44272.thithuapi3.Model.Sach;
import com.hunghhph44272.thithuapi3.R;
import com.hunghhph44272.thithuapi3.Service.HttpRequest;
import com.hunghhph44272.thithuapi3.Service.OnClick;

import java.util.ArrayList;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.ViewHolder>{
    private final Context context;
    private final ArrayList<Sach> list;

    HttpRequest httpRequest = new HttpRequest();

    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public AdapterSach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.item, parent, false);
        View view = inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String url = list.get(position).getHinh_anh_ph44272();
//        String newUrl = url.replace("localhost", "10.0.2.2");
//        Glide.with(context)
//                .load(newUrl)
//                .thumbnail(Glide.with(context).load(R.drawable.noimageicon))
//                .into(holder.imgHinhanh);
        holder.tvID.setText("ID: "+ list.get(position).get_id());
        holder.tvMaSach.setText("Mã sách: " + list.get(position).getMa_sach());
        holder.tvTieuDe.setText("Tiêu đề:" + list.get(position).getTieu_de());
        holder.tvTacGia.setText("Tác giả: " + list.get(position).getTac_gia());
        holder.tvNamXB.setText("Năm xuất bản: " + list.get(position).getNam_xuat_ban());
        holder.tvSoTrang.setText("Số trang: " + list.get(position).getSo_trang());
        holder.tvTheLoai.setText("Thể loại: " + list.get(position).getThe_loai());
        holder.tvID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach = list.get(position);
                Dialog_ChiTiet(sach);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = list.get(position);
                onClick.delete(sach);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = list.get(position);
                onClick.update(sach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvID,tvMaSach, tvTieuDe, tvTacGia, tvNamXB, tvSoTrang, tvTheLoai;
        //        ImageView imgHinhanh;
        ImageButton edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_id);
            tvMaSach = itemView.findViewById(R.id.tv_ma_sach);
            tvTieuDe = itemView.findViewById(R.id.tv_tieu_de);
            tvTacGia = itemView.findViewById(R.id.tv_tac_gia);
            tvNamXB = itemView.findViewById(R.id.tv_namxb);
            tvSoTrang = itemView.findViewById(R.id.tv_so_trang);
            tvTheLoai = itemView.findViewById(R.id.tv_the_loai);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
        }
    }
    private void Dialog_ChiTiet(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chitiet, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        ImageView img_hinhanh = view.findViewById(R.id.img_hinh_anh);

        TextView tv_ID = view.findViewById(R.id.tv_id);
        TextView tv_masach = view.findViewById(R.id.tv_ma_sach);
        TextView tv_tieude = view.findViewById(R.id.tv_tieu_de);
        TextView tv_tacgia = view.findViewById(R.id.tv_tac_gia);
        TextView tv_namxb= view.findViewById(R.id.tv_namxb);
        TextView tv_sotrang = view.findViewById(R.id.tv_so_trang);
        TextView tv_theloai = view.findViewById(R.id.tv_the_loai);


//        String url = xeMay.getHinh_anh_ph44272();
//        String newUrl = url.replace("localhost", "10.0.2.2");
//        Glide.with(context)
//                .load(newUrl)
//                .thumbnail(Glide.with(context).load(R.drawable.noimageicon))
//                .into(img_hinhanh);
        tv_ID.setText("ID: " + sach.get_id());
        tv_masach.setText("Mã Sách: " + sach.getMa_sach());
        tv_tieude.setText("Tiêu đề: " + sach.getTieu_de());
        tv_tacgia.setText("Tác giả: " + sach.getTac_gia());
        tv_namxb.setText("Năm XB: " + sach.getNam_xuat_ban());
        tv_sotrang.setText("Số trang: " + sach.getSo_trang());
        tv_theloai.setText("Thể loại: " + sach.getThe_loai());


        view.findViewById(R.id.btn_dong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
