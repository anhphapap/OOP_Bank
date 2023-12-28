package BaiTapLon;

import java.time.LocalDate;

public abstract class TaiKhoan {
    protected String hoTen, queQuan, canCuoc, maSo, gioiTinh;
    protected double soTien;
    
    protected LocalDate ngaySinh, ngayTao;
    protected static int dem=0;
    
    public TaiKhoan() {
        ++dem;
    }
    
    public void guiTien(double x) {
        this.soTien += x;
    }
    
    abstract public int canRutTien(double x);
    
    public void hienThi() {
        System.out.println("Số tài khoản: " + this.maSo);
        System.out.printf("Số dư: %,.0f\n", this.soTien);
        System.out.println("Ngày tạo: " + this.ngayTao.format(CauHinh.DATE_FORMAT));
    }
}
