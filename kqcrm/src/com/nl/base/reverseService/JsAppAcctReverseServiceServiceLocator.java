/**
 * JsAppAcctReverseServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.nl.base.reverseService;

public class JsAppAcctReverseServiceServiceLocator extends org.apache.axis.client.Service implements com.nl.base.reverseService.JsAppAcctReverseServiceService {

    public JsAppAcctReverseServiceServiceLocator() {
    }


    public JsAppAcctReverseServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public JsAppAcctReverseServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for JsAppAcctReverseService  http://10.33.208.143:8080/uap/services/JsAppAcctReverseService
    private java.lang.String JsAppAcctReverseService_address = "";

    public java.lang.String getJsAppAcctReverseServiceAddress() {
        return JsAppAcctReverseService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String JsAppAcctReverseServiceWSDDServiceName = "JsAppAcctReverseService";

    public java.lang.String getJsAppAcctReverseServiceWSDDServiceName() {
        return JsAppAcctReverseServiceWSDDServiceName;
    }

    public void setJsAppAcctReverseServiceWSDDServiceName(java.lang.String name) {
        JsAppAcctReverseServiceWSDDServiceName = name;
    }

    public com.nl.base.reverseService.JsAppAcctReverseService_PortType getJsAppAcctReverseService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(JsAppAcctReverseService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getJsAppAcctReverseService(endpoint);
    }

    public com.nl.base.reverseService.JsAppAcctReverseService_PortType getJsAppAcctReverseService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nl.base.reverseService.JsAppAcctReverseServiceSoapBindingStub _stub = new com.nl.base.reverseService.JsAppAcctReverseServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getJsAppAcctReverseServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setJsAppAcctReverseServiceEndpointAddress(java.lang.String address) {
        JsAppAcctReverseService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nl.base.reverseService.JsAppAcctReverseService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nl.base.reverseService.JsAppAcctReverseServiceSoapBindingStub _stub = new com.nl.base.reverseService.JsAppAcctReverseServiceSoapBindingStub(new java.net.URL(JsAppAcctReverseService_address), this);
                _stub.setPortName(getJsAppAcctReverseServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("JsAppAcctReverseService".equals(inputPortName)) {
            return getJsAppAcctReverseService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
	public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName(JsAppAcctReverseService_address, "JsAppAcctReverseServiceService");
    }

    private java.util.HashSet ports = null;

    @Override
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(JsAppAcctReverseService_address, "JsAppAcctReverseService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("JsAppAcctReverseService".equals(portName)) {
            setJsAppAcctReverseServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
