package oop.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMenuGui {
    private JFrame frame;

    // Hàm hiển thị menu, nhận một hành động (Runnable) để chạy khi nhấn "Bắt đầu"
    public void showMenu(Runnable onStartGame) {
        frame = new JFrame("BombermanMenu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Tạo bảng chứa nút
        JPanel panel = new BackgroundPanel();
        panel.setLayout(new BorderLayout());

        // Tạo panel cho các nút với FlowLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Sắp xếp các nút theo hàng
        buttonPanel.setOpaque(false); // Đặt độ trong suốt cho buttonPanel

        // Tạo nút "Bắt đầu" và "Thoát"
        JButton startButton = createButton("res/menu/Play.png", "res/menu/Play2.png");
        JButton quitButton = createButton("res/menu/Exit.png", "res/menu/Exit2.png");

        // Thêm sự kiện bấm nút
        startButton.addActionListener(e -> {
            frame.dispose(); // Đóng menu
            onStartGame.run(); // Chạy game chính
        });

        quitButton.addActionListener(e -> {
            System.exit(0); // Thoát ứng dụng
        });

        // Thêm các nút vào buttonPanel
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        // Thêm buttonPanel vào panel ở vị trí SOUTH
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm panel vào frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Hàm tạo nút với hình ảnh hover
    private JButton createButton(String normalImagePath, String hoverImagePath) {
        JButton button = new JButton(new ImageIcon(normalImagePath));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(245, 66));

        // Thêm MouseListener để thay đổi hình ảnh khi di chuột
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(new ImageIcon(hoverImagePath)); // Đổi hình ảnh khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(new ImageIcon(normalImagePath)); // Đổi lại hình ảnh khi chuột rời khỏi
            }
        });

        return button;
    }

    // Lớp vẽ nền (ảnh nền menu)
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Đường dẫn tới ảnh nền
            backgroundImage = new ImageIcon("res/menu/background.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Vẽ hình ảnh nền
        }
    }
}
