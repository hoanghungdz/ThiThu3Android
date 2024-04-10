package com.hunghhph44272.thithuapi3.Model;

public class Sach {
    String _id,ma_sach,tieu_de,tac_gia,nam_xuat_ban,so_trang,the_loai;

    public Sach() {
    }

    public Sach(String ma_sach, String tieu_de, String tac_gia, String nam_xuat_ban, String so_trang, String the_loai) {
        this.ma_sach = ma_sach;
        this.tieu_de = tieu_de;
        this.tac_gia = tac_gia;
        this.nam_xuat_ban = nam_xuat_ban;
        this.so_trang = so_trang;
        this.the_loai = the_loai;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMa_sach() {
        return ma_sach;
    }

    public void setMa_sach(String ma_sach) {
        this.ma_sach = ma_sach;
    }

    public String getTieu_de() {
        return tieu_de;
    }

    public void setTieu_de(String tieu_de) {
        this.tieu_de = tieu_de;
    }

    public String getTac_gia() {
        return tac_gia;
    }

    public void setTac_gia(String tac_gia) {
        this.tac_gia = tac_gia;
    }

    public String getNam_xuat_ban() {
        return nam_xuat_ban;
    }

    public void setNam_xuat_ban(String nam_xuat_ban) {
        this.nam_xuat_ban = nam_xuat_ban;
    }

    public String getSo_trang() {
        return so_trang;
    }

    public void setSo_trang(String so_trang) {
        this.so_trang = so_trang;
    }

    public String getThe_loai() {
        return the_loai;
    }

    public void setThe_loai(String the_loai) {
        this.the_loai = the_loai;
    }
}
