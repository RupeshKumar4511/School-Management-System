package rpack;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class PlaceholderTextField extends JTextField {
    String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            // Set the font and color for the placeholder
            g.setFont(new Font("Serif", Font.PLAIN, 25));
            g.setColor(Color.GRAY);

            // Draw the placeholder text
            int padding = (getHeight() - getFont().getSize()) / 2;
            g.drawString(placeholder, getInsets().left, getHeight() - padding - 2);
        }
    }
}

