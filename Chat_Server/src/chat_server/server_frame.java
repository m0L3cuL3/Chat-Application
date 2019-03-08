package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This program listens to incoming clients and receives data.
 * 
 * @author Sean Myko C. Baang
 * @author Jose Miguel Gonzaga
 * @author Neil Anthony Abad
 * @author Michael Yu
 * 
 * @version 1.0
 */
public class server_frame extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;

   /**
    * An abstract class that lays some of the groundwork
    * for communicating between clients over the network.
    */
   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       /**
        * This is a constructor for handling clients
        * 
        * @param clientSocket Client's socket.
        * @param user client.
        */
       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (IOException ex) 
            {
                serverStatusText.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    serverStatusText.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        serverStatusText.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        alertServer((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        alertServer((data[0] + ": has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        alertServer(message);
                    } 
                    else 
                    {
                        serverStatusText.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (IOException ex) 
             {
                serverStatusText.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

    /**
     *
     */
    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverStatusPane = new javax.swing.JScrollPane();
        serverStatusText = new javax.swing.JTextArea();
        onlineUsersLabel = new javax.swing.JLabel();
        onlineUsersPane = new javax.swing.JScrollPane();
        onUsersText = new javax.swing.JTextArea();
        serverStatusLabel = new javax.swing.JLabel();
        functionPanel = new javax.swing.JPanel();
        serverStartBtn = new javax.swing.JButton();
        serverStopBtn = new javax.swing.JButton();
        checkUsersBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("server"); // NOI18N
        setResizable(false);

        serverStatusText.setEditable(false);
        serverStatusText.setBackground(new java.awt.Color(0, 0, 0));
        serverStatusText.setColumns(20);
        serverStatusText.setForeground(new java.awt.Color(153, 255, 0));
        serverStatusText.setRows(5);
        serverStatusPane.setViewportView(serverStatusText);

        onlineUsersLabel.setText("Online Users:");

        onUsersText.setEditable(false);
        onUsersText.setBackground(new java.awt.Color(0, 0, 0));
        onUsersText.setColumns(20);
        onUsersText.setForeground(new java.awt.Color(0, 255, 0));
        onUsersText.setRows(5);
        onlineUsersPane.setViewportView(onUsersText);

        serverStatusLabel.setText("Server Status:");

        functionPanel.setBackground(new java.awt.Color(51, 51, 51));
        functionPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        serverStartBtn.setText("Start Server");
        serverStartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverStartBtnActionPerformed(evt);
            }
        });

        serverStopBtn.setText("Stop Server");
        serverStopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverStopBtnActionPerformed(evt);
            }
        });

        checkUsersBtn.setText("Check Online Users");
        checkUsersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUsersBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear Server Text");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout functionPanelLayout = new javax.swing.GroupLayout(functionPanel);
        functionPanel.setLayout(functionPanelLayout);
        functionPanelLayout.setHorizontalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(serverStartBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(serverStopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkUsersBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        functionPanelLayout.setVerticalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverStartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkUsersBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverStopBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(serverStatusLabel)
                .addContainerGap(559, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(serverStatusPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(onlineUsersLabel)
                    .addComponent(functionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(onlineUsersPane, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serverStatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serverStatusPane, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(functionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(onlineUsersLabel)
                        .addGap(1, 1, 1)
                        .addComponent(onlineUsersPane)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method stops the server
     */
    private void serverStopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverStopBtnActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
        
        alertServer("Server:stopping server, all users will be disconnected.\n:Chat");
        serverStatusText.append("Server stopping... \n");
        
        serverStatusText.setText("");
    }//GEN-LAST:event_serverStopBtnActionPerformed

    /**
     * This method starts the server on port 2222
     */
    private void serverStartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverStartBtnActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        serverStatusText.append("[Server]: online.\n");
    }//GEN-LAST:event_serverStartBtnActionPerformed

    /**
     * This method checks the number of clients connected
     * to the server. 
     */
    private void checkUsersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUsersBtnActionPerformed

        onUsersText.setText("");
        for(String current_user : users)
        {
            onUsersText.append(current_user);
            onUsersText.append("\n");
        }    
        
    }//GEN-LAST:event_checkUsersBtnActionPerformed

    /**
     * This method clears the server's text display.
     */
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        serverStatusText.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed

    /**
     *
     * @param args
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(server_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    /**
     * This class starts the server.
     */
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
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);

                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                    serverStatusText.append("connection available. \n");
                }
            }
            catch (IOException ex)
            {
                serverStatusText.append("[Error]: establishing connection. \n");
            }
        }
    }
    
    /**
     * This method adds users/clients to the server.
     * @param data name of the user.
     */
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        serverStatusText.append("Before " + name + " added. \n");
        users.add(name);
        serverStatusText.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            alertServer(message);
        }
        alertServer(done);
    }
    
    /**
     * This method removes the users/clients who have disconnected
     * from the server.
     * @param data name of the user.
     */
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            alertServer(message);
        }
        alertServer(done);
    }
    
    /**
     * This method alerts the server if the user/client
     * is doing something like sending a message.
     * @param message message to be displayed.
     */
    public void alertServer(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		serverStatusText.append("Sending: " + message + "\n");
                writer.flush();
                serverStatusText.setCaretPosition(serverStatusText.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		serverStatusText.append("Error!. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkUsersBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JPanel functionPanel;
    private javax.swing.JTextArea onUsersText;
    private javax.swing.JLabel onlineUsersLabel;
    private javax.swing.JScrollPane onlineUsersPane;
    private javax.swing.JButton serverStartBtn;
    private javax.swing.JLabel serverStatusLabel;
    private javax.swing.JScrollPane serverStatusPane;
    private javax.swing.JTextArea serverStatusText;
    private javax.swing.JButton serverStopBtn;
    // End of variables declaration//GEN-END:variables
}
