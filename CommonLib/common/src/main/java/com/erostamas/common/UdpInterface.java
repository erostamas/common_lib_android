package com.erostamas.common;

public class UdpInterface {

    private String _targetIpAddress;
    private int _targetPort;

    public UdpInterface(String targetIpAddress, int targetPort) {
        _targetIpAddress = targetIpAddress;
        _targetPort = targetPort;
    }

    public void send(String message) {
        UdpMessage msg = new UdpMessage(_targetIpAddress, _targetPort, message);
        UdpSender sender = new UdpSender();
        sender.execute(msg);
    }
}
