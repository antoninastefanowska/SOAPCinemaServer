package service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import model.Database;

public class IPAddressCheckHandler implements SOAPHandler<SOAPMessageContext> {

	@SuppressWarnings("unchecked")
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean isRequest = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!isRequest) {
			SOAPMessage msg = context.getMessage();
			
			Map<String, List<String>> headers = (Map<String, List<String>>)context.get(SOAPMessageContext.HTTP_REQUEST_HEADERS);
			List<String> ipList = (List<String>)headers.get("IP-Address");
			
			String ipAddress = ipList == null ? null : ipList.get(0);
			if (ipAddress != null) {
				System.out.println("IP: " + ipAddress);
				if (Database.getInstance().isIpAddressBlocked(ipAddress))
					generateError(msg, "Twój adres IP jest zablokowany.");
			} else {
				generateError(msg, "Brak adresu IP.");
			}
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}
	
	@Override
	public void close(MessageContext context) { }

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
	
	private void generateError(SOAPMessage msg, String reason) {
		try {
			SOAPBody body = msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault fault = body.addFault();
			fault.setFaultString(reason);
			throw new SOAPFaultException(fault);
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}
}
