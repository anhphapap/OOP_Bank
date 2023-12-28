package BaiTapLon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Menu {
    public static int chooseLog() {
        int n;
        System.out.println("\n========Welcome========");
        System.out.println("1.Đăng ký tài khoản");
        System.out.println("2.Đăng nhập");
        System.out.println("3.Thoát");
        System.out.println("========Welcome========");
        n = CauHinh.luaChon(0, 3);
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
        n = CauHinh.luaChon(1, 11);
        return n;
    }

    public static void processMenu(QuanLyTaiKhoan ql, TaiKhoanChinh t) {
        int n;
        while (true) {
            CauHinh.clrscr();
            System.out.printf("%s - %s", t.hoTen, t.maSo);
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
                    System.out.printf("\n1.Tiền lãi tài khoản chính(%s): %,.0f\n", t.maSo, sum);
                    int i = 2;
                    for(var x : t.getTaiKhoanKH()){
                        System.out.printf("\n%d.Tiền lãi tài khoản kỳ hạn %s(%s): %,.0f\n", i, x.getKyHan().layTen(), x.maSo, x.tinhTienLai());
                        sum+=x.tinhTienLai();
                        i++;
                    }
                    System.out.printf("\n->Tổng số tiền lãi hiện tại: %,.3f\n",sum);
                    CauHinh.scrPause();
                    break; 
                }
                case 3: {
                    int l = t.getTaiKhoanKH().size();
                    int m;
                    double tien;
                    System.out.printf("\n=========Gửi tiền========\n");
                    System.out.printf("1.Tài khoản chính (%s)\n", t.maSo);
                    for (int i = 0; i < l; i++)
                        System.out.printf("%d.Tài khoản kỳ hạn %s (%s)\n", i + 2,
                                t.getTaiKhoanKH().get(i).getKyHan().layTen(), t.getTaiKhoanKH().get(i).maSo);
                    System.out.printf("%d.Hủy\n", l + 2);
                    m = CauHinh.luaChon(1, l + 2);
                    if (m == 1) {
                        System.out.print("Nhập số tiền muốn gửi: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        t.guiTien(tien);
                        System.out.println("Gửi tiền thành công!");
                        System.out.printf("Số dư hiện tại: %,.0f\n", t.soTien);
                    } else if (m == l + 2) {
                        break;
                    } else {
                        TaiKhoanKyHan kh = t.getTaiKhoanKH().get(m - 2);
                        System.out.print("Nhập số tiền muốn gửi: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        int gt = kh.guiTien(tien, t);
                        if (gt == 1) {
                            System.out.println("Gửi tiền thành công!");
                            System.out.printf("Số dư hiện tại: %,.0f\n", kh.soTien);
                        } else if (gt == 2) {
                            System.out.println(
                                    "Số tiền trong tài khoản chính của bạn không đủ để thực hiện giao dịch này!!!");
                        } else {
                            System.out.println(
                                    "Bạn không thể gửi tiền khi chưa đến ngày đáo hạn! Vui lòng thử lại sau!!!");
                        }
                    }
                    CauHinh.scrPause();
                    break;
                }
                case 4: {
                    int l = t.getTaiKhoanKH().size();
                    int m;
                    double tien;
                    System.out.printf("\n=========Rút tiền========\n");
                    System.out.printf("1.Tài khoản chính (%s)\n", t.maSo);
                    for (int i = 0; i < l; i++)
                        System.out.printf("%d.Tài khoản kỳ hạn %s (%s)\n", i + 2,
                                t.getTaiKhoanKH().get(i).getKyHan().layTen(), t.getTaiKhoanKH().get(i).maSo);
                    System.out.printf("%d.Hủy\n", l + 2);
                    m = CauHinh.luaChon(1, l + 2);
                    if (m == 1) {
                        System.out.print("Nhập số tiền muốn rút: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        if (t.canRutTien(tien) == 1) {
                            System.out.println("Rút tiền thành công!");
                            t.soTien -= tien;
                            System.out.printf("Số dư hiện tại: %,.0f\n", t.soTien);
                        } else {
                            System.out.println("Số dư của bạn không đủ để thực hiện giao dịch!");
                        }
                    } else if (m == l + 2) {
                        break;
                    } else {
                        TaiKhoanKyHan kh = t.getTaiKhoanKH().get(m - 2);
                        System.out.print("Nhập số tiền muốn rút: ");
                        tien = Double.parseDouble(CauHinh.sc.nextLine());
                        int gt = kh.canRutTien(tien);
                        if (gt == 1) {
                            System.out.println("Rút tiền thành công!");
                            kh.soTien -= tien;
                            t.soTien += tien;
                            System.out.printf("Số dư hiện tại: %,.0f\n", kh.soTien);
                            System.out.printf("Số dư tài khoản chính: %,.0f\n", t.soTien);
                        } else if (gt == 2) {
                            System.out.println(
                                    "Số dư của bạn không đủ để thực hiện giao dịch này!!!");
                        } else {
                            System.out.println(
                                    "Bạn có muốn rút tiền trước ngày đáo hạn (Bạn chỉ được nhận lãi suất không kỳ hạn(0.2%))");
                            System.out.println("1.Có\t2.Không");
                            m = CauHinh.luaChon(1, 2);
                            if (m == 1) {
                                System.out.println("Rút tiền thành công!");
                                kh.soTien -= tien;
                                t.soTien += tien + t.tinhTienLai(tien, kh.ngayTao);
                                System.out.printf("Số dư hiện tại: %,.0f\n", kh.soTien);
                                System.out.printf("Số dư tài khoản chính: %,.0f\n", t.soTien);
                            } else {
                                break;
                            }
                        }
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
                    if (CauHinh.isNumberString(newPass, 6)) {
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
                            TaiKhoanChinh t = new TaiKhoanChinh();
                            t.dangKyFile(fs[i].getName());
                            br.write(t.getUserName() + " - " + t.getPassword() + " - " + t.maSo + "\n");
                            ql.them(t);
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
                    TaiKhoanChinh t = new TaiKhoanChinh();
                    if (t.isDangKy()) {
                        do {
                            System.out.print("Username: ");
                            t.setUserName(CauHinh.sc.nextLine());
                        } while (ql.isUser(t.getUserName()) && CauHinh.isUserName(t.getUserName()));
                        System.out.println("\nChúc mừng bạn đã đăng ký thành công tài khoản!!!");
                        System.out.println("Số tài khoản: " + t.maSo);
                        System.out.printf("Số dư: %,.0f\n", t.soTien);
                        System.out.println("Username: " + t.getUserName());
                        System.out.println("Password: " + t.getPassword());
                        System.out.println("Bạn có thể đổi mật khẩu ở lần đăng nhập tiếp theo!!!");
                        ql.them(t);
                    } else
                        TaiKhoan.dem--;
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
                    TaiKhoan.dem--;
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
