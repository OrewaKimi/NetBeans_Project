/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chat_client;
import java.net.*; 
import java.io.*; 
import java.util.*;

/**
 *
 * @author kimi9
 */
public class client_frame extends javax.swing.JFrame {
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList<>();
    int port = 2222;
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    public void userAdd(String data) {
        users.add(data);
    }

    public void userRemove(String data) {
        ta_chat.append(data + " is now offline.\n");
    }

    public void writeUsers() {
        String[] tempList = new String[users.size()];
        users.toArray(tempList);
    }

    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();
        } catch (Exception e) {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    public void Disconnect() {
        try {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch (Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);
    }

    public client_frame() {
        initComponents();
    }
     
public class IncomingReader implements Runnable 
    { 
        @Override 
        public void run()  
        { 
            String[] data; 
    String stream, done = "Done", connect = "Connect", 
    disconnect = "Disconnect", chat = "Chat"; 
      try  
            { 
      while ((stream = reader.readLine()) != null)  
                { 
                    data = stream.split(":"); 
    if (data[2].equals(chat))  
    { 
    ta_chat.append(data[0] + ": " + data[1] + "\n"); 
    ta_chat.setCaretPosition(ta_chat.getDocument().getLength()); 
}  
    else if (data[2].equals(connect)) 
{ 
    ta_chat.removeAll(); 
    userAdd(data[0]); 
}  
    else if (data[2].equals(disconnect))  
{ 
    userRemove(data[0]); 
}  
    else if (data[2].equals(done))  
{ 
} 
    writeUsers(); 
    users.clear(); 
} 
}   catch(Exception ex) { } 
} 
} 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        b_annonymous = new javax.swing.JButton();
        b_send = new javax.swing.JButton();
        tf_chat = new javax.swing.JTextField();
        tf_username = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_connect.setText("Connect");

        b_disconnect.setText("Disconnect");

        b_annonymous.setText("Anonymous Login");

        b_send.setText("SEND");

        lb_username.setText("Username..");

        jLabel2.setText("CONVERSATION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lb_username)
                            .addGap(5, 5, 5)
                            .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(145, 145, 145)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_annonymous)
                            .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_username))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(b_connect)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_disconnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_annonymous, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void 
tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {                                             
}                                            

private void 
b_connectActionPerformed(java.awt.event.ActionEvent evt) { 
    if (!isConnected) { 
        username = tf_username.getText(); 
        tf_username.setEditable(false); 
        try { 
            sock = new Socket(address, port); 
            InputStreamReader streamreader = new InputStreamReader(sock.getInputStream()); 
            reader = new BufferedReader(streamreader); 
            writer = new PrintWriter(sock.getOutputStream(), true); 
            writer.println(username + ":has connected.:Connect"); 
            isConnected = true;  
        } catch (Exception ex) { 
            ta_chat.append("Cannot Connect! Try Again. \n"); 
            tf_username.setEditable(true); 
        } 
        ListenThread(); 
    } else { 
        ta_chat.append("You are already connected. \n"); 
    } 
}                                          

private void 
b_sendActionPerformed(java.awt.event.ActionEvent evt) {                                        
    if (!tf_chat.getText().isEmpty()) { 
        try { 
            writer.println(username + ":" + tf_chat.getText() + ":" + "Chat"); 
            writer.flush(); 
        } catch (Exception ex) { 
            ta_chat.append("Message was not sent. \n"); 
        } 
    } 
    tf_chat.setText(""); 
    tf_chat.requestFocus(); 
}


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_annonymous;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}