package BaiTapLon;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaiKhoanChinh extends TaiKhoan {
    private String userName, password;
    private List<TaiKhoanKyHan> taiKhoanKH = new ArrayList<>();

    public TaiKhoanChinh() {

    }

    public boolean isDangKy() {
        boolean check;
        System.out.print("Họ và tên: ");
        this.hoTen = CauHinh.sc.nextLine();
        System.out.print("Quê quán: ");
        this.queQuan = CauHinh.sc.nextLine();
        do {
            check = false;
            System.out.print("Số căn cước: ");
            this.canCuoc = CauHinh.sc.nextLine();
            if (!CauHinh.isNumberString(canCuoc, 0)) {
                check = true;
                System.out.println("Số căn cước không hợp lệ (Chỉ bao gồm các chữ số)! Vui lòng thử lại!!!");
            }

        } while (check);
        do {
            System.out.print("Giới tính (Nam/Nu): ");
            this.gioiTinh = CauHinh.sc.nextLine();
            check = gioiTinh.equals("Nam") || gioiTinh.equals("Nu");
            if (!check)
                System.out.println("Thông tin sai! Xin vui lòng thử lại!");
        } while (!check);
        this.ngaySinh = LocalDate.now();
        do {
            check = false;
            System.out.print("Ngày sinh (dd/mm/yyyy): ");
            String ns = CauHinh.sc.nextLine();
            try {
                this.ngaySinh = LocalDate.parse(ns, CauHinh.DATE_FORMAT);
            } catch (Exception ex) {
                System.out.println("Định dạng ngày sai! Xin vui lòng thử lại!");
                check = true;
            }
        } while (check);
        System.out.print("Số tiền gửi (Tối thiểu 50,000): ");
        this.soTien = Double.parseDouble(CauHinh.sc.nextLine());
        if (soTien < 50000) {
            System.out.println("Số tiền gửi không đủ để tạo tài khoản! Xin vui lòng thử lại sau!!!");
            return false;
        }
        this.ngayTao = LocalDate.now();
        this.maSo = String.format("%d%02d%d%04d", ngayTao.getDayOfMonth(), ngayTao.getMonthValue(), ngayTao.getYear(),
                dem);
        this.password = Integer.toString(CauHinh.rand.nextInt(900000) + 100000);
        return true;
    }

    public void dangKyFile(String s) {
        try {
            File fi = new File("./BaiTapLon/Account/" + s);
            Scanner scf = new Scanner(fi);
            this.hoTen = scf.nextLine();
            this.queQuan = scf.nextLine();
            this.canCuoc = scf.nextLine();
            this.gioiTinh = scf.nextLine();
            this.ngaySinh = LocalDate.parse(scf.nextLine(), CauHinh.DATE_FORMAT);
            this.ngayTao = LocalDate.parse(scf.nextLine(), CauHinh.DATE_FORMAT);
            this.soTien = Double.parseDouble(scf.nextLine());
            this.userName = scf.nextLine();
            // this.ngayTao = LocalDate.now();
            this.maSo = String.format("%d%02d%d%04d", ngayTao.getDayOfMonth(), ngayTao.getMonthValue(),
                    ngayTao.getYear(), dem);
            this.password = Integer.toString(CauHinh.rand.nextInt(900000) + 100000);
            scf.close();
        } catch (Exception e) {
            return;
        }
    }

    public void taoTKKyHan() {
        TaiKhoanKyHan tk = new TaiKhoanKyHan(hoTen, canCuoc, queQuan, gioiTinh, ngaySinh);
        boolean c = tk.dangKy(this);
        if (c == true) {
            System.out.println(
                    "\nChúc mừng bạn đã đăng ký thành công tài khoản kỳ hạn " + tk.getKyHan().layTen() + "!!!");
            System.out.println("Số tài khoản: " + tk.maSo);
            System.out.printf("Số dư: %,.0f\n", tk.soTien);
            this.soTien -= tk.soTien;
            System.out.printf("Số dư tài khoản chính: %,.0f\n", this.soTien);
            taiKhoanKH.add(tk);
        } else {
            TaiKhoan.dem--;
            System.out.println("\nTạo tài khoản kỳ hạn không thành công! Xin vui lòng thử lại sau!!!");
        }
    }

    public double tinhTienLai() {
        double tien = 0;
        Period khoangThoiGian = ngayTao.until(LocalDate.now());
        int soThang = khoangThoiGian.getYears() * 12 + khoangThoiGian.getMonths();
        tien += soTien * 0.2 / 100 * soThang;
        return tien;
    }

    public double tinhTienLai(double x, LocalDate nt) {
        Period khoangThoiGian = nt.until(LocalDate.now());
        int soThang = khoangThoiGian.getYears() * 12 + khoangThoiGian.getMonths();
        return x * 0.2 / 100 * soThang;
    }

    public int canRutTien(double x) {
        if (x <= this.soTien)
            return 1;
        else
            return 0;
    }

    public double tongSoTien() {
        double sum = this.soTien;
        for (var x : taiKhoanKH)
            sum += x.soTien;
        return sum;
    }

    public boolean traCuuDs(String s) {
        for (var x : taiKhoanKH)
            if (x.maSo.equals(s))
                return true;
        return false;
    }

    public void hienThiDs() {
        System.out.println("\n=========Danh sách tài khoản=========");
        System.out.println("Khách hàng: " + this.hoTen);
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
        System.out.println("Khách hàng: " + this.hoTen);
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
        return String.format("\nHọ tên: %s\nGiới tính: %s\nQuê quán: %s\nCăn cước: %s\nSố tài khoản: %s\n", this.hoTen,
                this.gioiTinh, this.queQuan, this.canCuoc, this.maSo);
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
