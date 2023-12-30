package BaiTapLon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyTaiKhoan {
    private List<TaiKhoanChinh> ds = new ArrayList<>();
    
    public QuanLyTaiKhoan(){

    }

    public void them(TaiKhoanChinh t){
        ds.add(t);
    }

    public List<TaiKhoanChinh> traCuu(String ten){
        return ds.stream().filter(p -> p.getHoTen().contains(ten)).collect(Collectors.toList());
    }

    public TaiKhoanChinh traCuuDs(String s){
        for(var x : ds)
            if(x.getMaSo().equals(s) || x.traCuuDs(s))
                return x;
        return null;
    }

    public TaiKhoanChinh traCuuUser(String user){
        return ds.stream().filter(p -> p.getUserName().equals(user)).findFirst().get();
    }

    public void sapXep(){
        ds.sort(Comparator.comparing(TaiKhoanChinh::tongSoTien).reversed());
    }

    public void hienThi(){
        for(var x : ds) System.out.print(x);
    }

    public boolean isUser(String user){
        for(var x : this.ds){
            if(x.getUserName().equals(user)){
                System.out.println("Tên tài khoản đã tồn tại! Vui lòng chọn tên khác!!!");
                return true; 
            }
        }
        return false;
    }
}
