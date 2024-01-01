package BaiTapLon;

import java.time.LocalDate;

public abstract class TaiKhoan{
    private String hoTen, queQuan, canCuoc, gioiTinh, maSo;
    private LocalDate ngaySinh, ngayTao;
    private double soTien;
    protected static int dem=1;
    
    public TaiKhoan(){
        
    }
    
    public TaiKhoan(String ht, String qq, String cc, String gt, LocalDate ns, LocalDate nt, String ms, double st) {
        ++dem;
        this.hoTen = ht;
        this.queQuan = qq;
        this.canCuoc = cc;
        this.gioiTinh = gt;
        this.ngaySinh = ns;
        this.ngayTao = nt;
        this.maSo = ms;
        this.soTien = st;
    }
    
    public void hienThi() {
        System.out.println("Số tài khoản: " + this.maSo);
        System.out.printf("Số dư: %,.0f\n", this.soTien);
        System.out.println("Ngày tạo: " + this.ngayTao.format(CauHinh.DATE_FORMAT));
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getQueQuan() {
        return queQuan;
    }
    
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
    
    public String getCanCuoc() {
        return canCuoc;
    }
    
    public void setCanCuoc(String canCuoc) {
        this.canCuoc = canCuoc;
    }
    
    public String getGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public double getSoTien() {
        return soTien;
    }
    
    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }
    
    public String getMaSo() {
        return maSo;
    }
    
    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }
    
    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }
}
