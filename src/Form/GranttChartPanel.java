package Form;

import response.LichHenResponse;
import repository.LichHenRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class GranttChartPanel extends JPanel {

    private List<LichHenResponse> lichHenResponse;
    private Date reservationDate;
    private int cellWidth = 70; // Chiều rộng của mỗi cột (giờ)
    private int cellHeight = 50; // Chiều cao của mỗi hàng (bàn)
    private int startHour = 8; // Giờ bắt đầu hiển thị
    private int endHour = 21; // Giờ kết thúc hiển thị
    private Map<String, Integer> tableRowMap; // Bản đồ để lưu trữ vị trí hàng của mỗi bàn
    private int customerNameColumnWidth = 250; // Chiều rộng của cột tên khách hàng

    public GranttChartPanel() {
        // Không cần đặt kích thước cố định ở đây
        setPreferredSize(new Dimension(2400, 600)); // Thay đổi kích thước theo số lượng cột và hàng
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
        if (reservationDate != null) {
            LichHenRepository repo = new LichHenRepository();
            lichHenResponse = repo.getReservationsByDate(new java.sql.Date(reservationDate.getTime()));
            sortReservationsByTableName();
            mapTablesToRows();
            repaint(); // Đảm bảo giao diện được vẽ lại với dữ liệu mới
        }
    }

    private void sortReservationsByTableName() {
        if (lichHenResponse != null) {
            Collections.sort(lichHenResponse, new Comparator<LichHenResponse>() {
                @Override
                public int compare(LichHenResponse r1, LichHenResponse r2) {
                    return r1.getTen_KH().compareTo(r2.getTen_KH());
                }
            });
        }
    }

    private void mapTablesToRows() {
        tableRowMap = new HashMap<>();
        int rowIndex = 1;
        if (lichHenResponse != null) {
            for (LichHenResponse reservation : lichHenResponse) {
                if (!tableRowMap.containsKey(reservation.getTen_KH())) {
                    tableRowMap.put(reservation.getTen_KH(), rowIndex++);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (reservationDate == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ các đường lưới cho các giờ và bàn
        drawGrid(g2d);

        // Vẽ các đặt chỗ
        drawReservations(g2d);
    }

    //Chỉnh các cột
    private void drawGrid(Graphics2D g2d) {
        if (reservationDate == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        // Vẽ tiêu đề cột (giờ)
        int columnCount = (endHour - startHour) * 2; // 2 cột mỗi giờ (30 phút)
        for (int i = 0; i <= columnCount; i++) {
            int x = i * cellWidth + customerNameColumnWidth; // Đặt cột đầu tiên cho tiêu đề hàng (bàn)
            g2d.drawLine(x, 0, x, height);

            // Hiển thị giờ trên đầu cột
            if (i % 2 == 0) {
                int hour = startHour + i / 2;
                g2d.drawString(hour + ":00", x + 5, 20); // Hiển thị giờ trên đầu cột
            } else {
                int hour = startHour + i / 2;
                g2d.drawString(hour + ":30", x + 5, 20); // Hiển thị phút trên đầu cột
            }
        }

        // Vẽ tiêu đề hàng (bàn) với kích thước lớn hơn
        if (lichHenResponse != null && !lichHenResponse.isEmpty()) {
            int y = cellHeight;
            Set<String> drawnTables = new HashSet<>();
            for (int i = 0; i < lichHenResponse.size(); i++) {
                LichHenResponse lh = lichHenResponse.get(i);
                if (!drawnTables.contains(lh.getTen_KH())) {
                    g2d.drawLine(0, y, width, y);
                    g2d.setFont(new Font("Arial", Font.BOLD, 14)); // Đổi kích thước và kiểu chữ
                    g2d.drawString((i + 1) + ". " + lh.getTen_KH(), 5, y + cellHeight / 2); // Hiển thị tên bàn và số thứ tự bên trái hàng
                    y += cellHeight;
                    drawnTables.add(lh.getTen_KH());
                }
            }
        }
    }

    //Chỉnh nội dung cột và giờ
    private void drawReservations(Graphics2D g2d) {
        if (lichHenResponse == null || lichHenResponse.isEmpty()) {
            return;
        }

        // Đặt định dạng thời gian
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        // Duyệt qua danh sách các đặt chỗ và vẽ chúng
        for (LichHenResponse lhrp : lichHenResponse) {
            // Xác định chỉ số giờ từ thời gian đặt
            Calendar cal = Calendar.getInstance();
            cal.setTime(lhrp.getGioHen());
            int hourIndex = cal.get(Calendar.HOUR_OF_DAY) - startHour;
            int minuteIndex = cal.get(Calendar.MINUTE) / 30; // 0 cho phút 00, 1 cho phút 30

            // Xác định cột thời gian
            int columnIndex = hourIndex * 2 + minuteIndex;

            // Đảm bảo columnIndex nằm trong khoảng hợp lệ
            if (columnIndex >= 0 && columnIndex < (endHour - startHour) * 2) {
                // Xác định vị trí cột và hàng
                int x = columnIndex * cellWidth + customerNameColumnWidth; // Vị trí cột cho giờ
                int rowIndex = getTableRowIndex(lhrp.getTen_KH()); // Vị trí hàng cho bàn
                int y = rowIndex * cellHeight; // Vị trí hàng cho bàn
                int width = cellWidth; // Chiều rộng của thanh đặt chỗ
                int height = cellHeight - 2; // Chiều cao của thanh đặt chỗ

                // Vẽ hình chữ nhật đại diện cho đặt chỗ
                g2d.setColor(Color.BLUE); // Màu sắc của ô đặt chỗ
                g2d.fillRect(x + 1, y + 1, width - 2, height);

                // Vẽ tên khách hàng trên hình chữ nhật
                g2d.setColor(Color.WHITE); // Màu chữ trên ô đặt chỗ
                g2d.setFont(new Font("Arial", Font.PLAIN, 12)); // Đổi kích thước và kiểu chữ
                g2d.drawString(lhrp.getTen_KH(), x + 10, y + height / 2);
            }
        }
    }

    private int getTableRowIndex(String tableName) {
        if (tableRowMap == null) {
            tableRowMap = new HashMap<>();
            int rowIndex = 1;
            for (LichHenResponse lh : lichHenResponse) {
                if (!tableRowMap.containsKey(lh.getTen_KH())) {
                    tableRowMap.put(lh.getTen_KH(), rowIndex++);
                }
            }
        }
        return tableRowMap.getOrDefault(tableName, 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
