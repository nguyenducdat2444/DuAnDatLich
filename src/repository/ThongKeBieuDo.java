/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.ThongKe;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import repository.ThongKeRepositoryBieuDo;

/**
 *
 * @author default
 */
public class ThongKeBieuDo {

    private ThongKeRepositoryBieuDo thongKeService = new ThongKeRepositoryBieuDo();

    public void setDateToChart1(JPanel jpnItem, int year) {
//        List<ThongKe> listIten=thongKeService.getListByLopHoc();
//        if(listIten!=null){
//        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
//            for (ThongKe item : listIten) {
//                dataset.addValue(item.getTong(), "Tiền", item.getNgayTao());
//            }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            double tong = 0;
            if (thongKeService.getTongTheoThang(i, year) == null) {
                tong = 0;
            } else {

                ThongKe tk = thongKeService.getTongTheoThang(i, year);
                tong = tk.getTong();
            }
            dataset.addValue(tong, "Tiền", "Tháng" + " " + i);
        }
        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Tổng Doanh Thu Theo Tháng",
                "Tháng", "Tổng", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }

    public void setDateToChart2(JPanel jpnItem) {
        List<ThongKe> listIten = thongKeService.getAll();
        if (listIten != null) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (ThongKe item : listIten) {
                dataset.addValue(item.getSoLuong(), "Số Lượng", item.getTen_DV());
            }
            JFreeChart chart = ChartFactory.createBarChart("Thống Kê Số Dịch Vụ Dùng Nhiều Nhất",
                    "Tên Dịch Vụ", "Số Lượng", dataset);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();

        }
    }
}
