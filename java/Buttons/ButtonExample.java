import java.awt.event.*;  
import javax.swing.*;    
public class ButtonExample {  
  public static void main(String[] args) {  
    JFrame f=new JFrame("Button Example");  
    final JTextField tf=new JTextField();  
    tf.setBounds(50,50, 1000,20);  
    JButton b=new JButton("Click Here");  
    b.setBounds(50,100,305,30);  
    b.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){  
        tf.setText(e.toString());  
      }  
    });  
    f.add(b);f.add(tf);  
    f.setSize(1300,600);  
    f.setLayout(null);  
    f.setVisible(true);   
  }  
}  