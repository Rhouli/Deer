/* Ryan Houlihan
 *
 * Class asks the user to input a file name into a JTextField and then continues the
 * program once a file name is given
 */

package Deer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*;

public class FileGetter{
    private JFrame getFile;
    private JButton buttonOK;
    private JTextField textName;

    // Creates the file getter panel
    public FileGetter(){
            getFile = new JFrame("File Getter");
            getFile.setPreferredSize(new Dimension(400, 100));
            getFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            getFile.setLocation((dim.width - getFile.getSize().width)/2, (dim.height - getFile.getSize().height)/2);

            ButtonListener b1 = new ButtonListener();

            JPanel filePanel = new JPanel();
            filePanel.setPreferredSize(new Dimension(400,100));

            filePanel.add(new JLabel("Enter the Filename: "));

            textName = new JTextField(20);
            filePanel.add(textName);

            buttonOK = new JButton("OK");
            buttonOK.addActionListener(b1);
            filePanel.add(buttonOK);

            getFile.add(filePanel);

            getFile.setVisible(true);
            getFile.pack();
    }
    // Listens for a string to be entered into the textField and once the user presses
    // okay and there is vaild input the program continues
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == buttonOK)
            {
                String name = textName.getText();
                // If textField is empty sets focus back to textField
                if (name.length() == 0)
                {
                    textName.requestFocus();
                }
                // Continues to run the program if vaild input was entered
                else
                {
                    getFile.dispose();
                    ShapeAbstraction.Run(name);
                }
            }
        }
    }

}
