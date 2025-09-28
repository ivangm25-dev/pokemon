package wigm.pokemon.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER)
public class ServiceFailException extends Exception
{
    public ServiceFailException(String message) {
        super(message);
    }
}