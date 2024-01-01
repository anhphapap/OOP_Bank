package BaiTapLon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static int chooseLog() {
        int n;
        System.out.println("\n========Welcome========");
        System.out.println("1.Đăng ký tài khoản");
        System.out.println("2.Đăng nhập");
        System.out.println("3.Thoát");
        System.out.println("========Welcome========");
        n = CauHinh.choose(0, 3);
        return n;
    }

    public static int chooseMenu() {
        int n;
        System.out.println("\n===================Menu===================");
        System.out.println("1.Mở tài khoản kỳ hạn");
        System.out.println("2.Tính tiền lãi");
        System.out.println("3.Gửi tiền");
        System.out.println("4.Rút tiền");
        System.out.println("5.Hiển thị danh sách tài khoản");
        System.out.println("6.Tra cứu danh sách tài khoản khách hàng");
        System.out.println("7.Tra cứu khách hàng theo họ tên");
        System.out.println("8.Tra cứu khách hàng theo mã số khách hàng");
        System.out.println("9.Sắp xếp & xem danh sách khách hàng");
        System.out.println("10.Đổi mật khẩu");
        System.out.println("11.Đăng xuất");
        System.out.println("===================Menu===================");
        n = CauHinh.choose(1, 11);
        return n;
    }

    public static void processMenu(QuanLyTaiKhoan ql, TaiKhoanChinh t) {
        int n;
        while (true) {
            CauHinh.clrscr();
            System.out.printf("%s - %s", t.getHoTen(), t.getMaSo());
            n = chooseMenu();
            switch (n) {
                case 1: {
                    System.out.println("\n=========Mở Tài Khoản Kỳ Hạn========");
                    t.taoTKKyHan();
                    CauHinh.scrPause();
                    break;
                }
                case 2: {
                    double sum = t.tinhTienLai();
                    System.out.println("\n=========Tiền lãi========");
                    System.out.printf("\n1.Tiền lãi tài khoản chính(%s): %,.0f\n", t.getMaSo(), sum);
                    int i = 2;
                    for(var x : t.getTaiKhoanKH()){
                        System.out.printf("\n%d.Tiền lãi tài khoản kỳ hạn %s(%s): %,.0f\n", i, x.getKyHan().layTen(), x.getMaSo(), x.tinhTienLai());
                        sum+=x.tinhTienLai();
                        i++;
                    }
                    System.out.printf("\n->Tổng số tiền lãi hiện tại: %,.0f\n",sum);
                    CauHinh.scrPause();
                    break; 
                }
                case 3: {
                    int l = t.getTaiKhoanKH().size();
                    int m;
                    double tien;
                    System.out.printf("\n=========Gửi tiền========\n");
                    System.out.printf("1.Tài khoản chính (%s)\n", t.getMaSo());
                    for (int i = 0; i < l; i++)
                        System.out.printf("%d.Tài khoản kỳ hạn %s (%s)\n", i + 2,
                                t.getTaiKhoanKH().get(i).getKyHan().layTen(), t.getTaiKhoanKH().get(i).getMaSo());
                    System.out.printf("%d.Hủy\n", l + 2);
                    m = CauHinh.choose(1, l + 2);
                    if (m == 1) {
                        System.out.print("Nhập số tiền muốn gửi: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        t.guiTien(tien,t);
                        System.out.println("Gửi tiền thành công!");
                        System.out.printf("Số dư hiện tại: %,.0f\n", t.getSoTien());
                    } else if (m == l + 2) {
                        break;
                    } else {
                        TaiKhoanKyHan kh = t.getTaiKhoanKH().get(m - 2);
                        System.out.print("Nhập số tiền muốn gửi: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        kh.guiTien(tien, t);
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 4: {
                    int l = t.getTaiKhoanKH().size();
                    int m;
                    double tien;
                    System.out.printf("\n=========Rút tiền========\n");
                    System.out.printf("1.Tài khoản chính (%s)\n", t.getMaSo());
                    for (int i = 0; i < l; i++)
                        System.out.printf("%d.Tài khoản kỳ hạn %s (%s)\n", i + 2,
                                t.getTaiKhoanKH().get(i).getKyHan().layTen(), t.getTaiKhoanKH().get(i).getMaSo());
                    System.out.printf("%d.Hủy\n", l + 2);
                    m = CauHinh.choose(1, l + 2);
                    if (m == 1) {
                        System.out.print("Nhập số tiền muốn rút: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        t.rutTien(tien, t);
                    } else if (m == l + 2) {
                        break;
                    } else {
                        TaiKhoanKyHan kh = t.getTaiKhoanKH().get(m - 2);
                        System.out.print("Nhập số tiền muốn rút: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        kh.rutTien(tien, t);
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 5: {
                    t.hienThiDs();
                    CauHinh.scrPause();
                    break;
                }
                case 6: {
                    String ms;
                    System.out.print("Nhập mã số khách hàng: ");
                    ms = CauHinh.sc.nextLine();
                    TaiKhoanChinh tk = ql.traCuuDs(ms);
                    if (tk != null) {
                        System.out.println("\nTra cứu thành công!");
                        tk.hienThiTraCuu();
                    } else {
                        System.out.println("\nMã khách hàng không tồn tại!!!");
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 7: {
                    String ten;
                    System.out.print("Nhập tên khách hàng: ");
                    ten = CauHinh.sc.nextLine();
                    List<TaiKhoanChinh> l = ql.traCuu(ten);
                    if (l.size() > 0) {
                        System.out.println("\n======Kết quả tra cứu======");
                        for (var x : l)
                            System.out.print(x.toString());
                    } else {
                        System.out.println("\nKhông tìm thấy kết quả nào phù hợp!!!");
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 8: {
                    String ms;
                    System.out.print("Nhập mã số khách hàng: ");
                    ms = CauHinh.sc.nextLine();
                    TaiKhoanChinh tk = ql.traCuuDs(ms);
                    if (tk != null) {
                        System.out.print("\nTra cứu thành công!");
                        System.out.print(tk.toString());
                    } else {
                        System.out.println("\nKhông tìm thấy kết quả nào phù hợp!!!");
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 9: {
                    System.out.println("\n======Danh sách khách hàng======");
                    ql.sapXep();
                    ql.hienThi();
                    CauHinh.scrPause();
                    break;
                }
                case 10: {
                    String curPass, newPass, newPass1;
                    System.out.print("Nhập mật khẩu hiện tại: ");
                    curPass = CauHinh.sc.nextLine();
                    if (!curPass.equals(t.getPassword())) {
                        System.out.println("Mật khẩu không chính xác! Vui lòng thử lại sau!!!");
                        CauHinh.scrPause();
                        break;
                    }
                    System.out.print("Nhập mật khẩu mới (6 chữ số): ");
                    newPass = CauHinh.sc.nextLine();
                    if (CauHinh.isStringNumber(newPass, 6)) {
                        System.out.print("Xác nhận mật khẩu mới: ");
                        newPass1 = CauHinh.sc.nextLine();
                        if (newPass1.equals(newPass)) {
                            System.out.println("Đổi mật khẩu thành công!!!");
                            t.setPassword(newPass1);
                        } else {
                            System.out.println("Xác nhận mật khẩu không chính xác! Vui lòng thử lại sau!!!");
                        }
                    } else {
                        System.out.println("Mật khẩu không hợp lệ! Vui lòng thử lại sau!!!");
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 11: {
                    return;
                }
            }
        }
    }

    public static void processLog(QuanLyTaiKhoan ql) throws IOException {
        int n;
        while (true) {
            CauHinh.clrscr();
            n = chooseLog();
            switch (n) {
                case 0: {
                    File file = new File("BaiTapLon/Account/pass.txt");
                    try (FileWriter clr = new FileWriter(file)) {
                        clr.write("");
                        clr.close();
                    }
                    try (FileWriter fr = new FileWriter(file, true)) {
                        BufferedWriter br = new BufferedWriter(fr);
                        File f = new File("./BaiTapLon/Account");
                        File[] fs = f.listFiles();
                        for (int i = 0; i < fs.length - 1; i++) {
                            try {
                                File fi = new File("./BaiTapLon/Account/" + fs[i].getName());
                                Scanner scf = new Scanner(fi);
                                String ht,qq,cc,gt,ms,un,pw;
                                LocalDate ns, nt;
                                double st;
                                ht = scf.nextLine();
                                qq = scf.nextLine();
                                cc = scf.nextLine();
                                gt = scf.nextLine();
                                ns = LocalDate.parse(scf.nextLine(), CauHinh.DATE_FORMAT);
                                nt = LocalDate.parse(scf.nextLine(), CauHinh.DATE_FORMAT);
                                st = Double.parseDouble(scf.nextLine());
                                un = scf.nextLine();
                                // this.ngayTao = LocalDate.now();
                                ms = String.format("%d%02d%d%04d", nt.getDayOfMonth(), nt.getMonthValue(),
                                        nt.getYear(), TaiKhoan.dem);
                                pw = Integer.toString(CauHinh.rand.nextInt(900000) + 100000);
                                TaiKhoanChinh t = new TaiKhoanChinh(ht,qq,cc,gt,ns,nt,ms,st,un,pw);
                                br.write(t.getUserName() + " - " + t.getPassword() + " - " + t.getMaSo() + "\n");
                                ql.them(t);
                                scf.close();
                            } catch (Exception e) {
                                break;
                            }
                        }
                        System.out.printf("\nThêm thành công %d tài khoản!\n", fs.length - 1);
                        br.close();
                        fr.close();
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 1: {
                    System.out.println("\n=========Đăng ký========");
                    String ht,qq,cc,gt,ms,un,pw;
                    LocalDate ns, nt;
                    double st;
                    boolean check;
                    System.out.print("Họ và tên: ");
                    ht = CauHinh.sc.nextLine();
                    System.out.print("Quê quán: ");
                    qq = CauHinh.sc.nextLine();
                    do {
                        check = false;
                        System.out.print("Số căn cước: ");
                        cc = CauHinh.sc.nextLine();
                        if (!CauHinh.isStringNumber(cc, 0)) {
                            check = true;
                            System.out.println("Số căn cước không hợp lệ (Chỉ bao gồm các chữ số)! Vui lòng thử lại!!!");
                        }

                    } while (check);
                    do {
                        System.out.print("Giới tính (Nam/Nu): ");
                        gt = CauHinh.sc.nextLine();
                        check = gt.equals("Nam") || gt.equals("Nu");
                        if (!check)
                            System.out.println("Thông tin sai! Xin vui lòng thử lại!");
                    } while (!check);
                    ns = LocalDate.now();
                    do {
                        check = false;
                        System.out.print("Ngày sinh (dd/mm/yyyy): ");
                        String ns1 = CauHinh.sc.nextLine();
                        try {
                            ns = LocalDate.parse(ns1, CauHinh.DATE_FORMAT);
                        } catch (Exception ex) {
                            System.out.println("Định dạng ngày sai! Xin vui lòng thử lại!");
                            check = true;
                        }
                    } while (check);
                    System.out.print("Số tiền gửi (Tối thiểu 50,000): ");
                    st = Double.parseDouble(CauHinh.sc.nextLine());
                    if (st < 50000) {
                        System.out.println("Số tiền gửi không đủ để tạo tài khoản! Xin vui lòng thử lại sau!!!");
                        TaiKhoan.dem--;
                        CauHinh.scrPause();
                        break;
                    }
                    nt = LocalDate.now();
                    ms = String.format("%d%02d%d%04d", nt.getDayOfMonth(), nt.getMonthValue(), nt.getYear(),TaiKhoan.dem);
                    pw = Integer.toString(CauHinh.rand.nextInt(900000) + 100000);
                    do {
                        System.out.print("Username: ");
                        un = CauHinh.sc.nextLine();
                    } while (ql.isUser(un) && CauHinh.isUserName(un));
                    TaiKhoanChinh tk = new TaiKhoanChinh(ht, qq, cc, gt, ns, nt, ms, st, un, pw);
                    System.out.println("\nChúc mừng bạn đã đăng ký thành công tài khoản!!!");
                    System.out.println("Số tài khoản: " + tk.getMaSo());
                    System.out.printf("Số dư: %,.0f\n", tk.getSoTien());
                    System.out.println("Username: " + tk.getUserName());
                    System.out.println("Password: " + tk.getPassword());
                    System.out.println("Bạn có thể đổi mật khẩu ở lần đăng nhập tiếp theo!!!");
                    ql.them(tk);
                    CauHinh.scrPause();
                    break;
                }
                case 2: {
                    boolean c = true;
                    String user, pass;
                    System.out.println("\n=========Đăng nhập========");
                    System.out.print("Username: ");
                    user = CauHinh.sc.nextLine();
                    System.out.print("Password: ");
                    pass = CauHinh.sc.nextLine();
                    TaiKhoanChinh t = new TaiKhoanChinh();
                    try {
                        t = ql.traCuuUser(user);
                        if (!t.getPassword().equals(pass))
                            c = false;
                    } catch (Exception e) {
                        c = false;
                    }
                    if (!c) {
                        System.out.println("Username hoặc Password không đúng vui lòng thử lại sau!!!");
                        CauHinh.scrPause();
                    } else {
                        processMenu(ql, t);
                    }
                    break;
                }
                case 3: {
                    System.exit(0);
                }
            }
        }
    }
}
