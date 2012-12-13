
package com.arunhoan.aip.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.arunhoan.aip.service.pojo.PaidOrder;

@XmlRootElement(name = "getPaidOrdersResponse", namespace = "http://com.arunhoan.aip.service/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPaidOrdersResponse", namespace = "http://com.arunhoan.aip.service/")
public class GetPaidOrdersResponse {

    @XmlElement(name = "return", namespace = "")
    private List<PaidOrder> _return;

    /**
     * 
     * @return
     *     returns List<PaidOrder>
     */
    public List<PaidOrder> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<PaidOrder> _return) {
        this._return = _return;
    }

}
