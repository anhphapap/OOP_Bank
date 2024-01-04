package BaiTapLon;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanChinh extends TaiKhoan implements ChucNang{
    private String userName, password;
    private List<TaiKhoanKyHan> taiKhoanKH = new ArrayList<>();

    public TaiKhoanChinh() {

    }

    public TaiKhoanChinh(String ht, String qq, String cc, String gt, LocalDate ns, LocalDate nt, String ms, double st, String un, String pw) {
        super(ht, qq, cc, gt, ns, nt, ms, st);
        this.userName = un;
        this.password = pw;
    }

    public void taoTKKyHan() {
        KyHan kh = KyHan.MOT_TUAN;
        LocalDate ndh, nt;
        // nt = LocalDate.now();
        nt = this.getNgayTao();
        int lc;
        System.out.println("1.Một tuần(2%/năm)\n2.Một tháng(5.5%/năm)\n3.Sáu tháng(7.5%/năm)\n4.Một năm(7.9%/năm)\n5.Hủy");
        lc = CauHinh.choose(1, 5);
        switch(lc){
            case 1:{
                kh = KyHan.MOT_TUAN;
                break;
            }
            case 2:{
                kh = KyHan.MOT_THANG;
                break;
            }
            case 3:{
                kh = KyHan.SAU_THANG;
                break;
            }
            case 4:{
                kh = KyHan.MOT_NAM;
                break;
            }
            case 5:{
                System.out.println("\nTạo tài khoản kỳ hạn không thành công! Xin vui lòng thử lại sau!!!");
                return;
            }
        }
        System.out.print("Số tiền gửi (Tối thiểu 100,000): ");
        double st = Double.parseDouble(CauHinh.sc.nextLine());
        if(st<100000){
            System.out.print("Số tiền gửi chưa đủ để tạo tài khoản!");
            System.out.println("\nTạo tài khoản kỳ hạn không thành công! Xin vui lòng thử lại sau!!!");
            return;
        }else if(this.getSoTien()-st < 0){
            System.out.print("Số tiền trong tài khoản chính của bạn không đủ để thực hiện!");
            System.out.println("\nTạo tài khoản kỳ hạn không thành công! Xin vui lòng thử lại sau!!!");
            return;
        }else if(this.getSoTien()-st < 50000){
            System.out.print("Số tiền còn lại trong tài khoản chính của bạn phải lớn hơn 50,000!");
            System.out.println("\nTạo tài khoản kỳ hạn không thành công! Xin vui lòng thử lại sau!!!");
            return;
        }
        ndh = kh.tinhDaoHan(nt);
        String ms = String.format("%02d%02d%d%04d", nt.getDayOfMonth(),nt.getMonthValue(),nt.getYear(),dem);
        TaiKhoanKyHan tk = new TaiKhoanKyHan(getHoTen(), getQueQuan(), getCanCuoc(), getGioiTinh(), getNgaySinh(), getNgayTao(), ms, st, ndh, kh); 
        System.out.println(
                "\nChúc mừng bạn đã đăng ký thành công tài khoản kỳ hạn " + tk.getKyHan().layTen() + "!!!");
        System.out.println("Số tài khoản: " + tk.getMaSo());
        System.out.printf("Số dư: %,.0f\n", tk.getSoTien());
        this.setSoTien(this.getSoTien()-tk.getSoTien());
        System.out.printf("Số dư tài khoản chính: %,.0f\n", this.getSoTien());
        taiKhoanKH.add(tk);
    }

    public double tinhTienLai() {
        Period khoangThoiGian = getNgayTao().until(LocalDate.now());
        int soThang = khoangThoiGian.getYears() * 12 + khoangThoiGian.getMonths();
        return getSoTien() * 0.2 / 100 / 12 * soThang;
    }

    public double tinhTienLai(double x, LocalDate nt) {
        Period khoangThoiGian = nt.until(LocalDate.now());
        int soThang = khoangThoiGian.getYears() * 12 + khoangThoiGian.getMonths();
        return x * 0.2 / 100 / 12 * soThang;
    }

    public void guiTien(double x, TaiKhoanChinh t){
        this.setSoTien(getSoTien()+x);
    }

    public void rutTien(double x, TaiKhoanChinh t){
        if (x <= this.getSoTien()) {
            System.out.println("Rút tiền thành công!");
            setSoTien(getSoTien()-x);
            System.out.printf("Số dư hiện tại: %,.0f\n", getSoTien());
        } else {
            System.out.println("Số dư của bạn không đủ để thực hiện giao dịch!");
        }
    }

    public double tongSoTien() {
        double sum = this.getSoTien();
        for (var x : taiKhoanKH)
            sum += x.getSoTien();
        return sum;
    }

    public TaiKhoanKyHan traCuuTK(String s) {
        for (var x : taiKhoanKH)
            if (x.getMaSo().equals(s))
                return x;
        return null;
    }

    public void hienThiDs() {
        System.out.println("\n=========Danh sách tài khoản=========");
        System.out.println("Khách hàng: " + this.getHoTen());
        System.out.println("\n1.Tài khoản chính: ");
        this.hienThi();
        int i = 2;
        for (var x : taiKhoanKH) {
            System.out.printf("\n%d.Tài khoản kỳ hạn %s:\n", i, x.getKyHan().layTen());
            x.hienThi();
            i++;
        }
    }

    public void hienThiTraCuu() {
        System.out.println("\n=========Danh sách tài khoản=========");
        System.out.println("Khách hàng: " + this.getHoTen());
        System.out.print("\n1.Tài khoản chính: ");
        System.out.println(this);
        int i = 2;
        for (var x : taiKhoanKH) {
            System.out.printf("\n%d.Tài khoản kỳ hạn %s:", i, x.getKyHan().layTen());
            System.out.println(x);
            i++;
        }
    }

    @Override
    public String toString() {
        return String.format("\nHọ tên: %s\nGiới tính: %s\nQuê quán: %s\nCăn cước: %s\nSố tài khoản: %s\n", this.getHoTen(),
                this.getGioiTinh(), this.getQueQuan(), this.getCanCuoc(), this.getMaSo());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TaiKhoanKyHan> getTaiKhoanKH() {
        return taiKhoanKH;
    }
}
