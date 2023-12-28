package BaiTapLon;

import java.time.LocalDate;
import java.time.Period;

public enum KyHan {
    MOT_TUAN(7,2){
        public LocalDate tinhDaoHan(LocalDate x){
            return x.plusDays(this.daoHan);
        }
        public double tinhTienLai(double x, LocalDate nt){
            Period khoangThoiGian = nt.until(LocalDate.now());
            int soTuan = (khoangThoiGian.getYears()*365+khoangThoiGian.getMonths()*30 + khoangThoiGian.getDays())/7;
            return x*this.laiSuat/100/365*7*soTuan;
        }
        public String layTen(){
            return "1 Tuần";
        }
    },
    MOT_THANG(1,5.5){
        public LocalDate tinhDaoHan(LocalDate x){
            return x.plusMonths(this.daoHan);
        }
        public double tinhTienLai(double x, LocalDate nt){
            Period khoangThoiGian = nt.until(LocalDate.now());
            int soThang = khoangThoiGian.getYears()*12+khoangThoiGian.getMonths();
            return x*this.laiSuat/100/12*soThang;
        }
        public String layTen(){
            return "1 Tháng";
        }
    },
    SAU_THANG(6,7.5){
        public LocalDate tinhDaoHan(LocalDate x){
            return x.plusMonths(this.daoHan);
        }
        public double tinhTienLai(double x, LocalDate nt){
            Period khoangThoiGian = nt.until(LocalDate.now());
            int soThang = (khoangThoiGian.getYears()*12+khoangThoiGian.getMonths())/6;
            return x*this.laiSuat/100/2*soThang;
        }
        public String layTen(){
            return "6 Tháng";
        }
    },
    MOT_NAM(1,7.9){
        public LocalDate tinhDaoHan(LocalDate x){
            return x.plusYears(this.daoHan);
        }
        public double tinhTienLai(double x, LocalDate nt){
            Period khoangThoiGian = nt.until(LocalDate.now());
            int soNam = khoangThoiGian.getYears();
            return x*this.laiSuat/100*soNam;
        }
        public String layTen(){
            return "1 Năm";
        }
    };
    protected double laiSuat;
    protected int daoHan;

    KyHan(int daoHan, double laiSuat){
        this.daoHan = daoHan;
        this.laiSuat = laiSuat; 
    }

    abstract public LocalDate tinhDaoHan(LocalDate x);
    abstract public double tinhTienLai(double x, LocalDate nt);
    abstract public String layTen();
}
