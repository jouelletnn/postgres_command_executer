/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netnumber.postgresql_command_executer;
import com.jcraft.jsch.*;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
enum status {failure, success};


/**
 *
 * @author jouellet
 * @purpose to SSH into the DB Sever and run the passed script 
 * Best used on a Jenkins Server
 * @version 0.0.1
 */
public class Command_Executer {
    final InputStream in;

    
    public Command_Executer()
    {
        this.in = new PipedInputStream();
    }
    
    
    public Channel connect(String user, String password, String host)
    {
        Channel channel = null;
        try{
                JSch jsch=new JSch();

                Session session=jsch.getSession(user, host, 22);

                session.setPassword(password);

                session.connect(30000);   // making a connection with timeout.

                channel=session.openChannel("shell");
                channel.setInputStream(System.in);
                channel.setOutputStream(System.out);

                channel.setInputStream(in);
                channel.connect();
            }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return channel;
    }
    
    
    public void send_command(Channel channel, String command)
    {
        try
        {
            if(channel != null)
            {
                PipedOutputStream pin = new PipedOutputStream((PipedInputStream) in);
                pin.write((command + "\n").getBytes());
                pin.close();   
            }
            else
            {
                throw new NullPointerException("channel was null");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public Channel disconnect(Channel channel)
    {
        channel.disconnect();
        return null;
    }
}
