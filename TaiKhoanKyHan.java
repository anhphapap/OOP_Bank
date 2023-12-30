package BaiTapLon;

import java.time.LocalDate;

public class TaiKhoanKyHan extends TaiKhoan{
    private LocalDate ngayDaoHan;
    private KyHan kyHan;
    
    public TaiKhoanKyHan(){

    }
    
    public TaiKhoanKyHan(String hoTen, String queQuan, String canCuoc, String gioiTinh, LocalDate ngaySinh, LocalDate ngayTao, String maSo, double soTien,LocalDate ngayDaoHan, KyHan kyHan) {
        super(hoTen, queQuan, canCuoc, gioiTinh, ngaySinh, ngayTao, maSo, soTien);
        this.ngayDaoHan = ngayDaoHan;
        this.kyHan = kyHan;
    }

    public int guiTien(double x,TaiKhoanChinh t){
        if(LocalDate.now().equals(ngayDaoHan)){
            if(t.getSoTien()-x>=0){
                super.guiTien(x);
                t.setSoTien(t.getSoTien()-x);
                return 1;
            }else{
                return 2;
            }
        }else{
            return 0;
        }
    }

    public int canRutTien(double x){
        if(LocalDate.now().equals(ngayDaoHan)){
            if(x<=this.getSoTien()) return 1;
            else return 2;
        }else{
            if(x>this.getSoTien()) return 2;
            return 0;
        }
    }

    public double tinhTienLai(){
        return this.kyHan.tinhTienLai(this.getSoTien(), this.getNgayTao());
    }

    public void hienThi(){
        super.hienThi();
        System.out.println("Kỳ hạn: "+this.kyHan.layTen());
    }

    public KyHan getKyHan() {
        return kyHan;
    }

    @Override
    public String toString(){
        return String.format("\nSố tài khoản: %s\nKỳ hạn: %s\n", this.getMaSo(), this.kyHan.layTen());
    }
}
