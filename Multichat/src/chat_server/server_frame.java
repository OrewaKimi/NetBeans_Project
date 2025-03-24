/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chat_server;
import java.io.*; 
import java.net.*; 
import java.util.*; 
/**
 *
 * @author kimi9
 */
public class server_frame extends javax.swing.JFrame {
    String username="Server", address = 
    "localhost"; 
    ArrayList clientOutputStreams; 
    ArrayList<String> users; 
    int port=2222 ; 
    PrintWriter writer; 
    BufferedReader reader; 
    Socket s; 
    Boolean isConnected = false; 
    ArrayList<String> userss = new ArrayList(); 
    private void ListenThread() { 
        throw new UnsupportedOperationException("Not supported yet.");  
} 
    private void sendDisconnect() { 
    throw new UnsupportedOperationException("Not supported yet.");  
} 
    private void Disconnect() { 
    throw new UnsupportedOperationException("Not supported yet.");  
} 
    public class ClientHandler implements Runnable  
{ 
    BufferedReader reader; 
    Socket sock; 
    PrintWriter client; 
    public ClientHandler(Socket clientSocket, 
    PrintWriter user)  
{ 
    client = user; 
    try  
{ 
    sock = clientSocket; 
    InputStreamReader isReader = new 
    InputStreamReader(sock.getInputStream()); 
    reader = new 
    BufferedReader(isReader); 
} 
    catch (Exception ex)  
{ 
    ta_chat.append("Unexpected error... \n"); 
} 
} 
    public void ListenThread()  
{
    Thread IncomingReader = new Thread(new 
    IncomingReader()); 
    IncomingReader.start(); 
} 
    public void userAdd(String data){ 
    users.add(data); 
} 
    public void userRemove(String data){ 
    ta_chat.append(data + " is now offline.\n"); 
} 
    public void writeUsers(){ 
    String[] tempList = new 
    String[(users.size())]; 
    users.toArray(tempList); 
    for (String token:tempList)  
{ 
//users.append(token + "\n"); 
} 
} 
    public void sendDisconnect(){ 
    String bye = (username + ": :Disconnect"); 
    try 
{ 
    writer.println(bye);  
    writer.flush();  
} catch (Exception e)  
{ 
    ta_chat.append("Could not send Disconnect message.\n"); 
} 
} 
    public void Disconnect() { 
    try { 
        ta_chat.append("Disconnected.\n"); 
        sock.close(); 
        isConnected = false; 
    } catch (Exception ex) { 
        ta_chat.append("Failed to disconnect.\n"); 
    }  
}   

public class IncomingReader implements Runnable { 
    @Override 
    public void run() { 
        String[] data; 
        String stream;
        String done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat"; 

        try {   
            while ((stream = reader.readLine()) != null) { 
                data = stream.split(":"); 

                if (data.length >= 3) {
                    if (data[2].equals(chat)) { 
                        ta_chat.append(data[0] + ": " + data[1] + "\n"); 
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength()); 
                    } else if (data[2].equals(connect)) { 
                        ta_chat.setText(""); 
                        userAdd(data[0]); 
                    } else if (data[2].equals(disconnect)) { 
                        userRemove(data[0]); 
                    } else if (data[2].equals(done)) { 
                        writeUsers(); 
                        userss.clear(); 
                    } 
                }
            } 
        } catch (Exception ex) { 
            ta_chat.append("Error in IncomingReader.\n"); 
            ex.printStackTrace();
        } 
    } 
}

       @Override
public void run() {
    String message;
    String connect = "Connect", disconnect = "Disconnect", chat = "Chat";
    String[] data;

    try {
        while ((message = reader.readLine()) != null) {
            ta_chat.append("Received: " + message + "\n");
            data = message.split(":");

            // Pastikan array memiliki cukup elemen sebelum mengakses indeks
            if (data.length >= 3) {
                for (String token : data) {
                    ta_chat.append(token + "\n");
                }

                if (data[2].equals(connect)) {
                    tellEveryone(data[0] + ":" + data[1] + ":" + chat);
                    userAdd(data[0]);
                } else if (data[2].equals(disconnect)) {
                    tellEveryone(data[0] + " has disconnected: " + chat);
                    userRemove(data[0]);
                } else if (data[2].equals(chat)) {
                    tellEveryone(message);
                } else {
                    ta_chat.append("No conditions were met.\n");
                }
            } else {
                ta_chat.append("Invalid message format received.\n");
            }
        }
    } catch (Exception ex) {
        ta_chat.append("Lost a connection.\n");
        ex.printStackTrace();
        clientOutputStreams.remove(client); // Menghapus langsung tanpa pengecekan tambahan
    }
}
    }

    /**
     * Creates new form server_frame
     */
    public server_frame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_send = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        b_users = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_online = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b_start.setText("START");

        b_end.setText("END");

        b_send.setText("Send");

        b_clear.setText("Clear");

        b_users.setText("Online Us..");

        jLabel1.setText("Curerently Online");

        ta_online.setColumns(20);
        ta_online.setRows(5);
        jScrollPane1.setViewportView(ta_online);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane2.setViewportView(ta_chat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(b_start)
                            .addGap(160, 160, 160)
                            .addComponent(b_end))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(b_clear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_users))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_start, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_end, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b_users, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        private void 
    b_endActionPerformed(java.awt.event.ActionEvent evt) 
    { 
        try  
{ 

    Thread.sleep(5000);    
}
    catch(InterruptedException ex) 
    {Thread.currentThread().interrupt();} 
    tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat"); 
    ta_chat.append("Server stopping... \n"); 
    ta_chat.setText(""); 
}  
    private void 
b_startActionPerformed(java.awt.event.ActionEvent 
evt) {                                         
Thread starter = new Thread(new 
ServerStart()); 
starter.start(); 
ta_chat.append("Server started...\n"); 
if (isConnected == false) 
{ 
username ="Server"; 
try 
{ 
s = new Socket(address, port); 
InputStreamReader sr = new 
InputStreamReader(s.getInputStream()); 
reader = new BufferedReader(sr); 
writer = new 
PrintWriter(s.getOutputStream()); 
writer.println(username + ":has connected.:Connect"); 
writer.flush(); 
isConnected = true; 
} 
catch (Exception ex) 
{ 
ta_chat.append("Cannot Connect! Try Again. \n"); 
} 
ListenThread(); 
} else if (isConnected == true) 
{ 
ta_chat.append("You are already connected. \n"); 
}
}                                        
private void 
b_usersActionPerformed(java.awt.event.ActionEvent 
evt) {                                         
ta_online.append("\n"); 
for (String current_user : users) 
{ 
ta_online.append(current_user); 
ta_online.append("\n"); 
}     
}                                        
private void 
b_clearActionPerformed(java.awt.event.ActionEvent 
evt) {                                         
ta_chat.setText(""); 
ta_online.setText(""); 
}                                        
private void 
b_sendActionPerformed(java.awt.event.ActionEvent 
evt) {                                        
// TODO add your handling code here: 
String nothing = ""; 
if ((tf_chat.getText()).equals(nothing)) { 
tf_chat.setText(""); 
tf_chat.requestFocus(); 
} else { 
try { 
writer.println(username + ":" + 
tf_chat.getText() + ":" + "Chat"); 
writer.flush(); 
} catch (Exception ex) {
     ta_chat.append("Message was not sent. \n"); 
            } 
            tf_chat.setText(""); 
            tf_chat.requestFocus(); 
        } 
 
        tf_chat.setText(""); 
        tf_chat.requestFocus(); 
    }                                       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
     public class ServerStart implements Runnable  
    { 
        @Override 
        public void run()  
        { 
            clientOutputStreams = new ArrayList(); 
            users = new ArrayList();   
 
            try  
            { 
                ServerSocket serverSock = new ServerSocket(2222); 
 
                while (true) 
                    { 
    Socket clientSock = 
serverSock.accept(); 
    PrintWriter writer = new 
PrintWriter(clientSock.getOutputStream()); 
    clientOutputStreams.add(writer); 
 
    Thread listener = new Thread(new 
ClientHandler(clientSock, writer)); 
    listener.start(); 
    ta_chat.append("Got a connection. \n"); 
                } 
            } 
            catch (Exception ex) 
            { 
                ta_chat.append("Error making a connection. \n"); 
            } 
        } 
    } 
     
    public void userAdd (String data)  
    { 
String message, add = ": :Connect", done = "Server: :Done", name = data; 
ta_chat.append("Before " + name + " added. \n"); 
        users.add(name); 
ta_chat.append("After " + name + " added. \n"); 
        String[] tempList = new 
String[(users.size())]; 
        users.toArray(tempList); 
 
        for (String token:tempList)  
        { 
            message = (token + add); 
            tellEveryone(message); 
        } 
        tellEveryone(done);
         } 
     
    public void userRemove (String data)  
    { 
        String message, add = ": :Connect", done = 
"Server: :Done", name = data; 
        users.remove(name); 
        String[] tempList = new 
String[(users.size())]; 
        users.toArray(tempList); 
 
        for (String token:tempList)  
        { 
            message = (token + add); 
            tellEveryone(message); 
        } 
        tellEveryone(done); 
    } 
     
    public void tellEveryone(String message)  
    { 
 Iterator it = clientOutputStreams.iterator(); 
 
        while (it.hasNext())  
        { 
            try  
            { 
                PrintWriter writer = (PrintWriter) 
it.next(); 
  writer.println(message); 
  ta_chat.append("Sending: " + message + "\n"); 
                writer.flush(); 
                
ta_chat.setCaretPosition(ta_chat.getDocument().getLength()); 
 
            }  
            catch (Exception ex)  
            { 
                ta_chat.append("Error telling everyone. \n"); 
} 
}  
} 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_send;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextArea ta_online;
    private javax.swing.JTextField tf_chat;
    // End of variables declaration//GEN-END:variables
}
