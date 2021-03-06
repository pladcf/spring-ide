//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 05:47:27 PM CET 
//

package org.springframework.ide.eclipse.osgi.blueprint.internal.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

/**
 * <p>
 * Java class for Tblueprint complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Tblueprint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Tdescription" minOccurs="0"/>
 *         &lt;element name="type-converters" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Ttype-converters" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="service" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Tservice"/>
 *           &lt;element name="reference-list" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Treference-list"/>
 *           &lt;element name="bean" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Tbean"/>
 *           &lt;element name="reference" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Treference"/>
 *           &lt;any processContents='lax' namespace='##other'/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="default-activation" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Tactivation" default="eager" />
 *       &lt;attribute name="default-timeout" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Ttimeout" default="300000" />
 *       &lt;attribute name="default-availability" type="{http://www.osgi.org/xmlns/blueprint/v1.0.0}Tavailability" default="mandatory" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tblueprint", propOrder = { "description", "typeConverters", "serviceOrReferenceListOrBean" })
public class Tblueprint {

	protected Tdescription description;
	@XmlElement(name = "type-converters")
	protected TtypeConverters typeConverters;
	@XmlElementRefs({
			@XmlElementRef(name = "service", namespace = "http://www.osgi.org/xmlns/blueprint/v1.0.0", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "bean", namespace = "http://www.osgi.org/xmlns/blueprint/v1.0.0", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "reference-list", namespace = "http://www.osgi.org/xmlns/blueprint/v1.0.0", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "reference", namespace = "http://www.osgi.org/xmlns/blueprint/v1.0.0", type = JAXBElement.class, required = false) })
	@XmlAnyElement(lax = true)
	protected List<Object> serviceOrReferenceListOrBean;
	@XmlAttribute(name = "default-activation")
	protected Tactivation defaultActivation;
	@XmlAttribute(name = "default-timeout")
	protected BigInteger defaultTimeout;
	@XmlAttribute(name = "default-availability")
	protected Tavailability defaultAvailability;
	@XmlAnyAttribute
	private Map<QName, String> otherAttributes = new HashMap<QName, String>();

	/**
	 * Gets the value of the description property.
	 * 
	 * @return possible object is {@link Tdescription }
	 * 
	 */
	public Tdescription getDescription() {
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param value
	 *            allowed object is {@link Tdescription }
	 * 
	 */
	public void setDescription(Tdescription value) {
		this.description = value;
	}

	/**
	 * Gets the value of the typeConverters property.
	 * 
	 * @return possible object is {@link TtypeConverters }
	 * 
	 */
	public TtypeConverters getTypeConverters() {
		return typeConverters;
	}

	/**
	 * Sets the value of the typeConverters property.
	 * 
	 * @param value
	 *            allowed object is {@link TtypeConverters }
	 * 
	 */
	public void setTypeConverters(TtypeConverters value) {
		this.typeConverters = value;
	}

	/**
	 * Gets the value of the serviceOrReferenceListOrBean property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the serviceOrReferenceListOrBean property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getServiceOrReferenceListOrBean().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link TreferenceList }{@code >}
	 * {@link Object } {@link JAXBElement }{@code <}{@link Treference }{@code >}
	 * {@link JAXBElement }{@code <}{@link Tservice }{@code >} {@link Element }
	 * {@link JAXBElement }{@code <}{@link Tbean }{@code >}
	 * 
	 * 
	 */
	public List<Object> getServiceOrReferenceListOrBean() {
		if (serviceOrReferenceListOrBean == null) {
			serviceOrReferenceListOrBean = new ArrayList<Object>();
		}
		return this.serviceOrReferenceListOrBean;
	}

	/**
	 * Gets the value of the defaultActivation property.
	 * 
	 * @return possible object is {@link Tactivation }
	 * 
	 */
	public Tactivation getDefaultActivation() {
		if (defaultActivation == null) {
			return Tactivation.EAGER;
		} else {
			return defaultActivation;
		}
	}

	/**
	 * Sets the value of the defaultActivation property.
	 * 
	 * @param value
	 *            allowed object is {@link Tactivation }
	 * 
	 */
	public void setDefaultActivation(Tactivation value) {
		this.defaultActivation = value;
	}

	/**
	 * Gets the value of the defaultTimeout property.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getDefaultTimeout() {
		if (defaultTimeout == null) {
			return new BigInteger("300000");
		} else {
			return defaultTimeout;
		}
	}

	/**
	 * Sets the value of the defaultTimeout property.
	 * 
	 * @param value
	 *            allowed object is {@link BigInteger }
	 * 
	 */
	public void setDefaultTimeout(BigInteger value) {
		this.defaultTimeout = value;
	}

	/**
	 * Gets the value of the defaultAvailability property.
	 * 
	 * @return possible object is {@link Tavailability }
	 * 
	 */
	public Tavailability getDefaultAvailability() {
		if (defaultAvailability == null) {
			return Tavailability.MANDATORY;
		} else {
			return defaultAvailability;
		}
	}

	/**
	 * Sets the value of the defaultAvailability property.
	 * 
	 * @param value
	 *            allowed object is {@link Tavailability }
	 * 
	 */
	public void setDefaultAvailability(Tavailability value) {
		this.defaultAvailability = value;
	}

	/**
	 * Gets a map that contains attributes that aren't bound to any typed
	 * property on this class.
	 * 
	 * <p>
	 * the map is keyed by the name of the attribute and the value is the string
	 * value of the attribute.
	 * 
	 * the map returned by this method is live, and you can add new attribute by
	 * updating the map directly. Because of this design, there's no setter.
	 * 
	 * 
	 * @return always non-null
	 */
	public Map<QName, String> getOtherAttributes() {
		return otherAttributes;
	}

}
