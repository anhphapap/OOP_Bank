# OOP_Bank

Khách hàng mở tài khoản tại ngân hàng cần cung cấp các thông tin họ tên, giới tính, ngày
sinh, quê quán, số căn cước công dân, số tiền gửi (tối thiểu 50 nghìn VNĐ). Khi đó, ngân
hàng sẽ cấp cho khách hàng mã số theo định dạng **\<ngay>\<thang>\<nam>sothutu4chuso**,
chẳng hạn **120820229898**.</br></br>
Khi mở tài khoản, khách hàng có tài khoản không kỳ hạn (lãi suất 0.2%/năm) và được cung
cấp một tài khoản đăng nhập hệ thống. Khi đó, khách hàng có thể sử dụng các dịch vụ khác
của ngân hàng và có thể mở nhiều tài khoản có kỳ hạn khác. Một tài khoản có kỳ hạn bao
gồm thông tin kỳ hạn (1 tuần, 1 tháng, 12 tháng, …) và thông tin, lãi suất nhận lúc mở tài
khoản, hệ thống tự tính ngày đáo hạn khi khách hàng mở tài khoản. Khách hàng có thể gửi
tiền/rút tiền theo vào tài khoản có kỳ hạn đưa vào tài khoản thanh toán, nhưng chỉ được
phép gửi/rút vào ngày đáo hạn. Nếu khách hàng muốn rút tiền trước hạn thì khách hàng chỉ
được nhận lãi suất không kỳ hạn cho đến thời điểm rút.

#### Giả sử biểu mẫu lãi suất các kỳ hạn hiện tại như sau: <em>(ngân hàng có thể dễ dàng mở rộng các loại kỳ hạn mới)</em> </br>

<table style="margin: 0 auto">
  <tr>
    <th style="text-align: center">Kỳ hạn</th>
    <th style="text-align: center">Lãi suất (5/năm)</th>
  </tr>
  <tr>
    <td style="text-align: center">1 Tuần</td>
    <td style="text-align: center">2%</td>
  </tr>
  <tr>
    <td style="text-align: center">1 Tháng</td>
    <td style="text-align: center">5.5%</td>
  </tr>
  <tr>
    <td style="text-align: center">6 tháng</td>
    <td style="text-align: center">7.5%</td>
  </tr>
  <tr>
    <td style="text-align: center">12 tháng</td>
    <td style="text-align: center">9.5%</td>
  </tr>
</table>

#### Viết chương trình thực hiện các chức năng sau:

- Nhân viên mở tài khoản cho khách hàng và cung cấp username và password cho khách
  hàng. Trong đó username là mã số khách hàng được khách hàng cấp và mật khẩu là số
  ngẫu nhiên có 6 chữ số và khách hàng có thể đổi sau đó.
- Khách hàng đăng nhập và mở thêm tài khoản có kỳ hạn, số tiền tối thiểu để mở tài
  khoản là 100 nghìn VNĐ và đảm bảo tài khoản chính còn tối thiểu 50 nghìn VNĐ.
- Tính tiền lãi nhận được cho khách hàng dựa trên số tài khoản cung cấp.
- Khách hàng gửi tiền/rút tiền vào tài khoản chính (tài khoản không kỳ hạn)
- Xử lý khách hàng rút tiền tài khoản có kỳ hạn trước ngày đáo hạn của tài khoản có kỳ
  hạn, trả lại tiền và tiền lãi (theo không kỳ hạn) vào tài khoản chính của khách hàng.
- Tra cứu khách hàng theo họ tên và mã số khách hàng.
- Tra cứu danh sách tài khoản của một khách hàng theo mã số khách hàng.
- Sắp xếp danh sách khách hàng có tổng số tiền gửi giảm dần.</br>
  </br>**_Yêu cầu thiết kế menu cho người dùng chọn thực hiện, thiết kế chương trình sao cho dễ
  dàng mở rộng chương trình._**
