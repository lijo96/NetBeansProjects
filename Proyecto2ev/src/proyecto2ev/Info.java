/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2ev;

import java.util.Date;

/**
 *
 * @author dam213
 */
public class Info {
    String ipL,fqdnL,nameL,ipR,fqdnR,nameR;
    int portL, portR;
    Date date;

    public Info(String ipL, int portL, String fqdnL, String nameL, String ipR, int portR, String fqdnR, String nameR, Date date) {
        this.ipL = ipL;
        this.portL = portL;
        this.fqdnL = fqdnL;
        this.nameL = nameL;
        this.ipR = ipR;
        this.portR = portR;
        this.fqdnR = fqdnR;
        this.nameR = nameR;
        this.date = date;
    }

    public String getIpL() {
        return ipL;
    }

    public String getFqdnL() {
        return fqdnL;
    }

    public String getNameL() {
        return nameL;
    }

    public String getIpR() {
        return ipR;
    }

    public String getFqdnR() {
        return fqdnR;
    }

    public String getNameR() {
        return nameR;
    }

    public int getPortL() {
        return portL;
    }

    public int getPortR() {
        return portR;
    }

    public Date getDate() {
        return date;
    }
    
    
    
    
}
