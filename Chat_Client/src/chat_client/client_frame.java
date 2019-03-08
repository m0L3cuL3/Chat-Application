package chat_client;

import java.awt.Component;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * This program allows the user to connect to the server.
 * 
 * @author Sean Myko C. Baang
 * @author Jose Miguel Gonzaga
 * @author Neil Anthony Abad
 * @author Michael Yu
 * 
 * @version 1.0
 */

public class client_frame extends javax.swing.JFrame 
{
    Scanner scan;
    String address;
    String username, regUser;
    String password, regPass;
    String filepath = System.getProperty("user.home") + "\\Desktop\\accounts.txt";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    private Component frame;
    
    /**
     * This method creates a thread to listen to.
     */
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    /**
     * This method adds users.
     * @param data represents the user.
     */
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    /**
     * This method removes users
     * @param data represents the user
     */
    public void userRemove(String data) 
    {
         clientTextDisplay.append(data + " is now offline.\n");
    }
    
    /**
     * This method stores the users inside the array.
     */
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    /**
     * This method displays the disconnection of a user (Server-side).
     */
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            clientTextDisplay.append("Could not send Disconnect message.\n");
        }
    }
    
    /**
     * This method displays the disconnection of a user (Client-side).
     */
    public void Disconnect() 
    {
        try 
        {
            clientTextDisplay.append("Disconnected.\n");
            sock.close();
        } catch(IOException ex) {
            clientTextDisplay.append("Failed to disconnect. \n");
        }
        isConnected = false;
        userTextBox.setEditable(true);
        passTextBox.setEditable(true);

    }
    
    /**
     * Constructor of client_frame.
     */
    public client_frame() 
    {
        initComponents();
    }
    
    /**
     * This class displays everything the client/s do.
     * For example, interacting with other clients.
     */
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        clientTextDisplay.append(data[0] + ": " + data[1] + "\n");
                        clientTextDisplay.setCaretPosition(clientTextDisplay.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        clientTextDisplay.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(IOException ex) { }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientStatusPane = new javax.swing.JScrollPane();
        clientTextDisplay = new javax.swing.JTextArea();
        clientMsgBox = new javax.swing.JTextField();
        sendBtn = new javax.swing.JButton();
        connPanel = new javax.swing.JPanel();
        addressLabel = new javax.swing.JLabel();
        addressTextBox = new javax.swing.JTextField();
        portLabel = new javax.swing.JLabel();
        portTextBox = new javax.swing.JTextField();
        loginPanel = new javax.swing.JPanel();
        connServBtn = new javax.swing.JButton();
        disconnServBtn = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        userTextBox = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        passTextBox = new javax.swing.JPasswordField();
        connLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        registerLabel = new javax.swing.JLabel();
        regPanel = new javax.swing.JPanel();
        userRegLabel = new javax.swing.JLabel();
        userRegTextBox = new javax.swing.JTextField();
        passRegLabel = new javax.swing.JLabel();
        passRegTextBox = new javax.swing.JPasswordField();
        registerBtn = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        loginHelpBtn = new javax.swing.JButton();
        connHelpBtn = new javax.swing.JButton();
        regHelpBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("client"); // NOI18N
        setResizable(false);

        clientTextDisplay.setEditable(false);
        clientTextDisplay.setBackground(new java.awt.Color(0, 0, 0));
        clientTextDisplay.setColumns(20);
        clientTextDisplay.setForeground(new java.awt.Color(51, 255, 0));
        clientTextDisplay.setRows(5);
        clientStatusPane.setViewportView(clientTextDisplay);

        clientMsgBox.setBackground(new java.awt.Color(0, 0, 0));
        clientMsgBox.setForeground(new java.awt.Color(0, 255, 255));

        sendBtn.setText("Send");
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        connPanel.setBackground(new java.awt.Color(108, 108, 108));

        addressLabel.setForeground(new java.awt.Color(255, 255, 255));
        addressLabel.setText("Address: ");

        addressTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextBoxActionPerformed(evt);
            }
        });

        portLabel.setForeground(new java.awt.Color(255, 255, 255));
        portLabel.setText("Port:");

        portTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portTextBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout connPanelLayout = new javax.swing.GroupLayout(connPanel);
        connPanel.setLayout(connPanelLayout);
        connPanelLayout.setHorizontalGroup(
            connPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(portLabel)
                    .addComponent(addressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(connPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(portTextBox)
                    .addComponent(addressTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        connPanelLayout.setVerticalGroup(
            connPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(connPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portLabel)
                    .addComponent(portTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loginPanel.setBackground(new java.awt.Color(108, 108, 108));

        connServBtn.setText("Connect / Login to Server");
        connServBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connServBtnActionPerformed(evt);
            }
        });

        disconnServBtn.setText("Disconnect / Logout to Server");
        disconnServBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnServBtnActionPerformed(evt);
            }
        });

        userLabel.setForeground(new java.awt.Color(255, 255, 255));
        userLabel.setText("Username:");

        userTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextBoxActionPerformed(evt);
            }
        });

        passLabel.setForeground(new java.awt.Color(255, 255, 255));
        passLabel.setText("Password: ");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(disconnServBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passLabel)
                            .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userTextBox)
                            .addComponent(passTextBox)))
                    .addComponent(connServBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(userTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connServBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnServBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        connLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        connLabel.setText("Connection Settings:");

        loginLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginLabel.setText("Login:");

        registerLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        registerLabel.setText("Register:");

        regPanel.setBackground(new java.awt.Color(108, 108, 108));

        userRegLabel.setForeground(new java.awt.Color(255, 255, 255));
        userRegLabel.setText("Username:");

        passRegLabel.setForeground(new java.awt.Color(255, 255, 255));
        passRegLabel.setText("Password:");

        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout regPanelLayout = new javax.swing.GroupLayout(regPanel);
        regPanel.setLayout(regPanelLayout);
        regPanelLayout.setHorizontalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regPanelLayout.createSequentialGroup()
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passRegLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userRegLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userRegTextBox)
                            .addComponent(passRegTextBox)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(registerBtn)))
                .addContainerGap())
        );
        regPanelLayout.setVerticalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userRegLabel)
                    .addComponent(userRegTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passRegLabel)
                    .addComponent(passRegTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        infoPanel.setBackground(new java.awt.Color(108, 108, 108));

        loginHelpBtn.setText("Login Help");
        loginHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginHelpBtnActionPerformed(evt);
            }
        });

        connHelpBtn.setText("Connection Help");
        connHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connHelpBtnActionPerformed(evt);
            }
        });

        regHelpBtn.setText("Register Help");
        regHelpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regHelpBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginHelpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(connHelpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(regHelpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginHelpBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connHelpBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regHelpBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginLabel)
                    .addComponent(connLabel)
                    .addComponent(connPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerLabel)
                    .addComponent(regPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientMsgBox, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clientStatusPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(connLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(registerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientStatusPane, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientMsgBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addressTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextBoxActionPerformed
       
    }//GEN-LAST:event_addressTextBoxActionPerformed

    private void portTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portTextBoxActionPerformed
   
    }//GEN-LAST:event_portTextBoxActionPerformed

    private void userTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextBoxActionPerformed
    
    }//GEN-LAST:event_userTextBoxActionPerformed

    /**
     * This method allows the client/s to connect to the server.
     */
    
    private void connServBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connServBtnActionPerformed
        
        if (isConnected == false) 
        {
            username = userTextBox.getText();
            password = passTextBox.getText();

            verifyUser(username, password, filepath);
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            clientTextDisplay.append("You are already connected. \n");
        }
    }//GEN-LAST:event_connServBtnActionPerformed

    /**
     * This method allows the client to login to the server.
     * 
     * @param username name of the user.
     * @param password password of the user.
     * @param filepath filepath where the username and password is stored.
     */
    public void verifyUser(String username, String password, String filepath)
    {
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";
        try
        {
            scan = new Scanner(new File(filepath));
            scan.useDelimiter("[,\n]");
            
            while(scan.hasNext() && !found)
            {
		tempUsername = scan.next();
		tempPassword = scan.next();
				
		if(tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim()))
		{
                    try 
                    {
                        address = addressTextBox.getText();
                        sock = new Socket(address, port);
                        InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                        reader = new BufferedReader(streamreader);
                        writer = new PrintWriter(sock.getOutputStream());
                        writer.println(username + ":has connected.:Connect");
                        writer.flush(); 
                        isConnected = true; 
                        break;
                    } 
                    catch (IOException ex) 
                    {
                        clientTextDisplay.append("Cannot Connect! Try Again. \n");
                        userTextBox.setEditable(true);
                    }
		}
                else
                {
                    // do nothing
                    
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e)
        {
            
        }
        
    }
    
    /**
     * This method allows the client/s to disconnect to the server. 
     */
    private void disconnServBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnServBtnActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_disconnServBtnActionPerformed

    /**
     * This method allows the client to send the message to other clients. 
     */
    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
       String nothing = "";
        if ((clientMsgBox.getText()).equals(nothing)) {
            clientMsgBox.setText("");
            clientMsgBox.requestFocus();
        } else {
            try {
               writer.println(username + ":" + clientMsgBox.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                clientTextDisplay.append("Message was not sent. \n");
            }
            clientMsgBox.setText("");
            clientMsgBox.requestFocus();
        }

        clientMsgBox.setText("");
        clientMsgBox.requestFocus();
    }//GEN-LAST:event_sendBtnActionPerformed

    /**
     * This method allows users to register an account.
     */
    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // register
        regUser = userRegTextBox.getText();
        regPass = passRegTextBox.getText();

        registerUser(regUser, regPass, filepath);
        
    }//GEN-LAST:event_registerBtnActionPerformed

    /**
     * This method gives users information on how to login.
     */
    private void loginHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginHelpBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(frame,"\nStep 1: Setup connection."
                + "\nStep 2: Enter Login details." 
                + "\nStep 3: Click Connect/Login button.");
    }//GEN-LAST:event_loginHelpBtnActionPerformed

    /**
     * This method gives users information on how to connect to the server. 
     */
    private void connHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connHelpBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(frame,"\nStep 1: Enter connection details." + "\nStep 2: Login to your account.");
    }//GEN-LAST:event_connHelpBtnActionPerformed

    /**
     * This method gives users information on how to register an account.
     */
    private void regHelpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regHelpBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(frame,"\nStep 1: Enter desired details." + "\nStep 2: Click register button (click only once).");
    }//GEN-LAST:event_regHelpBtnActionPerformed

    /**
     * This method allows the user to register an account.
     * @param username given username.
     * @param password given password.
     * @param filepath filepath of the stored usernames and passwords.
     */
    public void registerUser(String username, String password, String filepath)
    {
        try
        {          
            FileWriter writer = new FileWriter(filepath, true);
            
            writer.write(username);
            writer.write(",");
            writer.write(password);
            writer.write(System.getProperty("line.separator"));
            writer.close();
            JOptionPane.showMessageDialog(frame,"Account Created!");
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(frame,"File does not found!");
        }
    }
    
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
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextBox;
    private javax.swing.JTextField clientMsgBox;
    private javax.swing.JScrollPane clientStatusPane;
    private javax.swing.JTextArea clientTextDisplay;
    private javax.swing.JButton connHelpBtn;
    private javax.swing.JLabel connLabel;
    private javax.swing.JPanel connPanel;
    private javax.swing.JButton connServBtn;
    private javax.swing.JButton disconnServBtn;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JButton loginHelpBtn;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel passRegLabel;
    private javax.swing.JPasswordField passRegTextBox;
    private javax.swing.JPasswordField passTextBox;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextBox;
    private javax.swing.JButton regHelpBtn;
    private javax.swing.JPanel regPanel;
    private javax.swing.JButton registerBtn;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JButton sendBtn;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel userRegLabel;
    private javax.swing.JTextField userRegTextBox;
    private javax.swing.JTextField userTextBox;
    // End of variables declaration//GEN-END:variables
}
