package BaiTapLon;

import java.time.LocalDate;

public class TaiKhoanKyHan extends TaiKhoan{
    private LocalDate ngayDaoHan;
    private KyHan kyHan;
    
    public TaiKhoanKyHan(){

    }
    
    public TaiKhoanKyHan(String ten, String cc, String qq, String gt, LocalDate ns){
        this.hoTen = ten;
        this.canCuoc = cc;
        this.queQuan = qq;
        this.gioiTinh = gt;
        this.ngaySinh = ns;
    }
    
    public boolean dangKy(TaiKhoanChinh t){
        // this.ngayTao = LocalDate.now();
        this.ngayTao = t.ngayTao;
        int kh;
        System.out.println("1.Một tuần(2%/năm)\n2.Một tháng(5.5%/năm)\n3.Sáu tháng(7.5%/năm)\n4.Một năm(7.9%/năm)\n5.Hủy");
        kh = CauHinh.luaChon(1, 5);
        switch(kh){
            case 1:{
                kyHan = KyHan.MOT_TUAN;
                break;
            }
            case 2:{
                kyHan = KyHan.MOT_THANG;
                break;
            }
            case 3:{
                kyHan = KyHan.SAU_THANG;
                break;
            }
            case 4:{
                kyHan = KyHan.MOT_NAM;
                break;
            }
            case 5:{
                return false;
            }
        }
        System.out.print("Số tiền gửi (Tối thiểu 100,000): ");
        double st = Double.parseDouble(CauHinh.sc.nextLine());
        if(st<100000){
            System.out.print("Số tiền gửi chưa đủ để tạo tài khoản!");
            return false;
        }else if(t.soTien-st < 0){
            System.out.print("Số tiền trong tài khoản chính của bạn không đủ để thực hiện!");
            return false;
        }else if(t.soTien-st < 50000){
            System.out.print("Số tiền còn lại trong tài khoản chính của bạn phải lớn hơn 50,000!");
            return false;
        }else{
            soTien = st;
        }
        this.ngayDaoHan = kyHan.tinhDaoHan(ngayTao);
        this.maSo = String.format("%d%d%d%04d", ngayTao.getDayOfMonth(),ngayTao.getMonthValue(),ngayTao.getYear(),dem);
        return true;
    }

    public int guiTien(double x,TaiKhoanChinh t){
        if(LocalDate.now().equals(ngayDaoHan)){
            if(t.soTien-x>=0){
                super.guiTien(x);
                t.soTien -= x;
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
            if(x<=this.soTien) return 1;
            else return 2;
        }else{
            if(x>this.soTien) return 2;
            return 0;
        }
    }

    public double tinhTienLai(){
        return this.kyHan.tinhTienLai(this.soTien, this.ngayTao);
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
        return String.format("\nSố tài khoản: %s\nKỳ hạn: %s\n", this.maSo, this.kyHan.layTen());
    }
}
