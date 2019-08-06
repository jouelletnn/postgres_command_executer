/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import com.netnumber.postgresql_command_executer.Command_Executer;
import com.jcraft.jsch.Channel;
/**
 *
 * @author jouellet
 */
public class main {
    public static void main(String args[])
    {
        Command_Executer connection_1 = new Command_Executer();
        
        Channel channel = connection_1.connect(args[1], args[2], args[3]);
        
        connection_1.send_command(channel, args[4]);
        channel = connection_1.disconnect(channel);
        
    }
}
