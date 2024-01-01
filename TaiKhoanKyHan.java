package BaiTapLon;

import java.time.LocalDate;

public class TaiKhoanKyHan extends TaiKhoan implements ChucNang{
    private LocalDate ngayDaoHan;
    private KyHan kyHan;
    
    public TaiKhoanKyHan(){

    }
    
    public TaiKhoanKyHan(String ht, String qq, String cc, String gt, LocalDate ns, LocalDate nt, String ms, double st,LocalDate ndh, KyHan kh) {
        super(ht, qq, cc, gt, ns, nt, ms, st);
        this.ngayDaoHan = ndh;
        this.kyHan = kh;
    }

    public void guiTien(double x,TaiKhoanChinh t){
        if(LocalDate.now().equals(ngayDaoHan)){
            if(t.getSoTien()-x>=0){
                setSoTien(getSoTien()+x);
                t.setSoTien(t.getSoTien()-x);
                System.out.println("Gửi tiền thành công!");
                System.out.printf("Số dư hiện tại: %,.0f\n", getSoTien());
            }else{
                System.out.println("Số tiền trong tài khoản chính của bạn không đủ để thực hiện giao dịch này!!!");
            }
        }else{
            System.out.println("Bạn không thể gửi tiền khi chưa đến ngày đáo hạn! Vui lòng thử lại sau!!!");
        }
    }

    public void rutTien(double x, TaiKhoanChinh t){
        int m;
        int gt = canRutTien(x);
        if (gt == 1) {
            System.out.println("Rút tiền thành công!");
            setSoTien(getSoTien()-x);
            t.setSoTien(t.getSoTien()+x);
            System.out.printf("Số dư hiện tại: %,.0f\n", getSoTien());
            System.out.printf("Số dư tài khoản chính: %,.0f\n", t.getSoTien());
        } else if (gt == 2) {
            System.out.println(
                    "Số dư của bạn không đủ để thực hiện giao dịch này!!!");
        } else {
            System.out.println(
                    "Bạn có muốn rút tiền trước ngày đáo hạn (Bạn chỉ được nhận lãi suất không kỳ hạn(0.2%))");
            System.out.println("1.Có\t2.Không");
            m = CauHinh.choose(1, 2);
            if (m == 1) {
                System.out.println("Rút tiền thành công!");
                setSoTien(getSoTien()-x);
                t.setSoTien(t.getSoTien()+x+ t.tinhTienLai(x, getNgayTao()));
                System.out.printf("Số dư hiện tại: %,.0f\n", getSoTien());
                System.out.printf("Số dư tài khoản chính: %,.0f\n", t.getSoTien());
            }
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
